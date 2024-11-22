package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class StateCommand implements SlashCommand {
    @Override
    public String getName() {
        return "state";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        return event.reply()
                .withContent("En cours de développememt");
    }
}
