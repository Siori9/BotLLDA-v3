package bot.llda.botlldav3.discord.button;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import reactor.core.publisher.Mono;

public interface ButtonEvent {
    String getCustomId();

    Mono<Void> handle(ButtonInteractionEvent event);
}
