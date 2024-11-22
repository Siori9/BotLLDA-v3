package bot.llda.botlldav3.discord.commands.rpg;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.CreateWebhook;
import bot.llda.botlldav3.discord.utils.ListenerButton;
import bot.llda.botlldav3.discord.utils.WebhookCreator;
import bot.llda.botlldav3.game.entities.UserEntity;
import bot.llda.botlldav3.game.model.characterPart.CharacterData;
import bot.llda.botlldav3.game.repositories.UserRepository;
import bot.llda.botlldav3.game.repositories.characterPart.CharacterRepository;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Webhook;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.util.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CharacterProfilCommand implements SlashCommand {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public CharacterProfilCommand(CharacterRepository charactereRepository, UserRepository userRepository, GatewayDiscordClient gateway) {
        this.charactereRepository = charactereRepository;
        this.userRepository = userRepository;
        this.gateway = gateway;
    }

    private final CharacterRepository charactereRepository;
    private final UserRepository userRepository;

    private final GatewayDiscordClient gateway;

    @Override
    public String getName() {
        return "profil";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        UserEntity userEntity = userRepository.findByDiscordId(event.getInteraction().getUser().getId().toString());

        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.primary("pets", "Pets"));
        buttons.add(Button.danger("X","X"));

        return profilMessage(userEntity).flatMap(message ->
                event.reply().withContent("*Le monde de Maled - Menu du personnage de *" + event.getInteraction().getUser().getMention())
                        .withEphemeral(true)
                        .then(CreateWebhook.getWebhook("Clara", event)
                                .flatMap(webhook -> webhook.execute()
                                        .withEmbeds(message.build())
                                        .withComponents(
                                                buttons.stream()
                                                        .map(ActionRow::of)
                                                        .collect(Collectors.toList())
                                        ).withWaitForMessage(true)
                                        .flatMap(m -> webhook.delete().thenReturn(m))
                                        .flatMap(m -> ListenerButton.getButtonActivate(
                                                        gateway,
                                                        m,
                                                        buttons,
                                                        Duration.ofHours(1),
                                                        buttonInteractionEvent ->
                                                                switch (buttonInteractionEvent.getCustomId()) {
                                                                case "pets":
                                                                    yield CreateWebhook.getWebhook("Clara",event)
                                                                            .flatMap(webhook1 -> webhook1.execute().withContent("Les pets du personnage !")
                                                                                    .then(buttonInteractionEvent.getMessage().get().delete().then(webhook1.delete()))
                                                                            );
                                                                default:
                                                                    yield buttonInteractionEvent.getMessage().get().delete();
                                                        }
                                                ).thenReturn(webhook)
                                        )
                                ).then()));
    }

    public Mono<EmbedCreateSpec.Builder> profilMessage(UserEntity userEntity) {
        CharacterData characterData = charactereRepository.findByOwnerAndSelected(userEntity, true).toCharacterData();

        EmbedCreateSpec.Builder messageBuilder = EmbedCreateSpec.builder();
        messageBuilder.title(characterData.getName());
        messageBuilder.thumbnail(characterData.getAvatarUrl());

        switch (characterData.getFamily()) {
            case RUBIS -> {
                messageBuilder.color(Color.RED);
                messageBuilder.addField("Famille :", "Rubis", false);
            }
            case SAPHIR -> {
                messageBuilder.color(Color.BLUE);
                messageBuilder.addField("Famille :", "Saphir", false);
            }
            case EMERAUDE -> {
                messageBuilder.color(Color.GREEN);
                messageBuilder.addField("Famille :", "Emeraude", false);
            }
            case AMETHYSTE -> {
                messageBuilder.color(Color.DEEP_LILAC);
                messageBuilder.addField("Famille :", "Amethyste", false);
            }
        }

        messageBuilder.addField("Niveau :", "Nv : " + characterData.getLvl().getLvl().toString(), true);
        messageBuilder.addField("\u200B", "Exp : " + characterData.getXp() + " / " + characterData.getLvl().getXpNextLvl(), true);
        messageBuilder.addField("\u200B", "\u200B", true);

        messageBuilder.addField("Guide :", characterData.getGuide().getPnj().getName(), true);
        messageBuilder.addField("\u200B", "Nv : " + characterData.getGuide().getLvl().getLvl(), true);
        messageBuilder.addField("\u200B", "Exp : " + characterData.getGuide().getXp() + " / " + characterData.getGuide().getLvl().getXpNextLvl(), true);

//        messageBuilder.footer(characterData.getLocation().getName(), characterData.getLocation().getImageUrl());

        return gateway.getUserById(Snowflake.of(characterData.getOwner().getDiscordId().substring(10, characterData.getOwner().getDiscordId().length() - 1)))
                .map(user -> messageBuilder.author("Personnage de " + user.getUsername(), null, user.getAvatarUrl()));
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("profil")
                .description("Viewing character profile")
                .build());
    }
}
