package bot.llda.botlldav3.discord.commands.rpg;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.CreateWebhook;
import bot.llda.botlldav3.game.entities.worldPart.PlaceEntity;
import bot.llda.botlldav3.game.model.worldPart.Place;
import bot.llda.botlldav3.game.repositories.worldPart.PlaceRepository;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.component.ActionRow;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class PlaceCreateCommand implements SlashCommand {

    Logger LOGGER = Logger.getLogger(PlaceCreateCommand.class.getName());

    public PlaceCreateCommand(PlaceRepository placeRepository, GatewayDiscordClient gateway) {
        this.placeRepository = placeRepository;
        this.gateway = gateway;
    }

    private final PlaceRepository placeRepository;
    private final GatewayDiscordClient gateway;

    @Override
    public String getName() {
        return "create-place";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String name = event.getOption("name").isPresent()? event.getOption("name").get().getValue().get().asString() : "";
        String imageUrl = event.getOption("image-url").isPresent()? event.getOption("image-url").get().getValue().get().asString() : "";
        String listString = event.getOption("list-string").isPresent()? event.getOption("list-string").get().getValue().get().asString() : "";
        List<String> list = !Objects.equals(listString, "") ? Arrays.asList(listString.split(",")) : new ArrayList<>();
        List<UUID> listId = list.stream().map(UUID::fromString).toList();
        placeRepository.save(new PlaceEntity(new Place(UUID.randomUUID(),name,imageUrl,listId )));
        return event.reply("Creation ok").then();
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("create-place")
                .description("Creating place")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("name")
                        .description("Name place")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build()
                )
                .addOption(ApplicationCommandOptionData.builder()
                        .name("image-url")
                        .description("Image URL of the place")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build()
                )
                .addOption(ApplicationCommandOptionData.builder()
                        .name("list-string")
                        .description("List id of the nearby places of the place")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(false)
                        .build()
                )
                .build());
    }
}