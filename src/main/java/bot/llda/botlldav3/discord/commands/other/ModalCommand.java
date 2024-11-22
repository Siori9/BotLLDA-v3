package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.GetterInputModal;
import bot.llda.botlldav3.discord.utils.ModalCreator;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.*;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

@Component
public class ModalCommand implements SlashCommand {

    public static Logger LOGGER = LoggerFactory.getLogger(ModalCommand.class);

    @Override
    public String getName() {
        return "create-modal";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Collection<LayoutComponent> components = new ArrayList<>(
                List.of(
                        ActionRow.of(
                                TextInput.small("name", "Nom du personnage").required(false)
                        ),
                        ActionRow.of(
                                TextInput.small("avatar", "Lien de l'avatar du personnage").required(false)
                        )
                )
        );


        return ModalCreator.createModal(event, "Modal de test !", "testmodal", components)
                .then(GetterInputModal.getInputContain(event.getClient(), "testmodal", "name", Duration.ofSeconds(10)))
                .map(value -> {
                    LOGGER.info(value);
                    return value;
                })
                .flatMap(value -> event
                        .getInteraction()
                        .getChannel()
                        .flatMap(
                                channel -> channel.createMessage(value)
                        )
                )
                .then();
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("create-modal")
                .description("create a modal")
                .build()
        );
    }
}
