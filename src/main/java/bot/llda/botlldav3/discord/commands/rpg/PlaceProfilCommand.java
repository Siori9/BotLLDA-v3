package bot.llda.botlldav3.discord.commands.rpg;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.CreateWebhook;
import bot.llda.botlldav3.game.entities.worldPart.PlaceEntity;
import bot.llda.botlldav3.game.repositories.worldPart.PlaceRepository;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.component.ActionRow;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class PlaceProfilCommand implements SlashCommand {

    Logger LOGGER = Logger.getLogger(PlaceProfilCommand.class.getName());

    public PlaceProfilCommand(PlaceRepository placeRepository, GatewayDiscordClient gateway) {
        this.placeRepository = placeRepository;
        this.gateway = gateway;
    }

    private final PlaceRepository placeRepository;
    private final GatewayDiscordClient gateway;

    @Override
    public String getName() {
        return "profil-place";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return showPlace(UUID.fromString(event.getOption("id").flatMap(ApplicationCommandInteractionOption::getValue).map(ApplicationCommandInteractionOptionValue::asString).get())).flatMap(message ->
                event.reply().withContent("*Le monde de Maled - Lieux :*")
                        .withEphemeral(true)
                        .then(CreateWebhook.getWebhook("Clara", event)
                                .flatMap(webhook -> webhook.execute()
                                        .withEmbeds(message.build())
                                        .withWaitForMessage(true)
                                        .flatMap(m -> webhook.delete().thenReturn(m))
                                )
                        )
        ).then();
    }

    public Mono<EmbedCreateSpec.Builder> showPlace(UUID placeID) {
        Optional<PlaceEntity> placeEntity = placeRepository.findById(placeID);
        EmbedCreateSpec.Builder messageBuilder = EmbedCreateSpec.builder();

        if(placeEntity.isPresent()) {
            messageBuilder.title(placeEntity.get().getName());
            messageBuilder.thumbnail(placeEntity.get().getImageUrl());
            messageBuilder.author(placeEntity.get().getId().toString(),"","");

            if(!placeEntity.get().getNearbyPlaces().isEmpty()) {
                String nearbyPlaceMessage = String.valueOf(placeEntity.get().getNearbyPlaces().stream().map(id -> {
                    Optional<PlaceEntity> nearbyPlace = placeRepository.findById(id);
                    if (nearbyPlace.isPresent()) {
                        return nearbyPlace.get().getName();
                    } else {
                        return "";
                    }
                }).reduce((str1, str2) -> str1 + "\n" + str2));
                messageBuilder.footer("Les environs: \n" + nearbyPlaceMessage, "");
            }
        }

        LOGGER.info(messageBuilder.toString());

        return Mono.just(messageBuilder);
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("profil-place")
                .description("Viewing place profile")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("id")
                        .description("Id place")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build()
                )
                .build());
    }
}