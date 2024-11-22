package bot.llda.botlldav3.discord.button;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomIdButton implements ButtonEvent {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getCustomId() {
        return "custom";
    }

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        return event.reply("I'm a response to the button");
    }
}
