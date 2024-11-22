package bot.llda.botlldav3.discord.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * A simple interface defining our slash command class contract.
 * a getName() method to provide the case-sensitive name of the command.
 * and a handle() method which will house all the logic for processing each command.
 */
public interface SlashCommand {

    String getName();

    Mono<Void> handle(ChatInputInteractionEvent event);

    default Optional<ApplicationCommandRequest> data() {
        return Optional.empty();
    }
}
