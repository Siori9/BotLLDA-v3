package bot.llda.botlldav3.discord.commands.rpg;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.CreateWebhook;
import bot.llda.botlldav3.discord.utils.ListenerButton;
import bot.llda.botlldav3.discord.utils.WebhookCreator;
import bot.llda.botlldav3.game.entities.UserEntity;
import bot.llda.botlldav3.game.entities.characterPart.CharacterEntity;
import bot.llda.botlldav3.game.repositories.UserRepository;
import bot.llda.botlldav3.game.repositories.characterPart.CharacterRepository;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Webhook;
import discord4j.discordjson.json.ApplicationCommandRequest;
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
public class CharacterSelectCommand implements SlashCommand {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public CharacterSelectCommand(CharacterRepository charactereRepository, UserRepository userRepository, GatewayDiscordClient gateway) {
        this.charactereRepository = charactereRepository;
        this.userRepository = userRepository;
        this.gateway = gateway;
    }

    private final CharacterRepository charactereRepository;
    private final UserRepository userRepository;

    private final GatewayDiscordClient gateway;

    @Override
    public String getName() {
        return "select";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        UserEntity userEntity = userRepository.findByDiscordId(event.getInteraction().getUser().getId().toString());
        List<CharacterEntity> listCharacter = charactereRepository.findAllByOwner(userEntity);

        List<Button> buttons = new ArrayList<>();
        for (CharacterEntity character : listCharacter) {
            buttons.add(Button.primary(character.getId().toString(), character.getName() + " - " + character.getFamily() + " [ Niv : " + character.getLvl().getLvl() + " ]"));
        }

        return event.reply("*Le monde de Maled - Sélection du personnage de *" + event.getInteraction().getUser().getMention())
                .withEphemeral(true)
                .then(CreateWebhook.getWebhook("Clara",event))
                .flatMap(webhook -> webhook.execute()
                                    .withComponents(
                                            buttons.stream()
                                            .map(ActionRow::of)
                                            .collect(Collectors.toList())
                                    ).withWaitForMessage(true)
                                    .map(message -> buttonAction(webhook, message, buttons, listCharacter, event))
                ).map(Webhook::delete).then();
    }

    private Webhook buttonAction(Webhook webhook, Message message, List<Button> buttons, List<CharacterEntity> listCharacter, ChatInputInteractionEvent event) {
        return ListenerButton.getButtonActivate(
                gateway,
                message,
                buttons,
                Duration.ofHours(1),
                buttonInteractionEvent -> {
                    listCharacter.forEach(characterEntity -> characterEntity.setSelected(false));
                    charactereRepository.saveAll(listCharacter);
                    return charactereRepository.findById(UUID.fromString(buttonInteractionEvent.getCustomId()))
                            .map(characterEntity -> {
                                characterEntity.setSelected(true);
                                return charactereRepository.save(characterEntity);
                            }).map(characterEntity -> event.getInteraction().getChannel().flatMap(messageChannel -> messageChannel.createMessage(characterEntity.getName() + " a bien été selectionné !"))).get()
                            .then(buttonInteractionEvent.getInteraction().getMessage().get().delete());
                }
        ).thenReturn(webhook).block();
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("select")
                .description("Selecting character")
                .build());
    }

}
