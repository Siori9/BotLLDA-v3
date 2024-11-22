package bot.llda.botlldav3.discord.listeners;

import bot.llda.botlldav3.discord.button.ButtonEvent;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
public final class ButtonListener {
    private final List<ButtonEvent> buttons;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ButtonListener(List<ButtonEvent> buttonEvents, GatewayDiscordClient client) {
        buttons = buttonEvents;

        client.on(ButtonInteractionEvent.class, this::handle).subscribe();
    }

    public Mono<Void> handle(ButtonInteractionEvent event) {
        return Flux.fromIterable(buttons)
                .filter(buttonEvent -> buttonEvent.getCustomId().equals(event.getCustomId()))
                .next()
                .flatMap(buttonEvent -> buttonEvent.handle(event));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ButtonListener) obj;
        return Objects.equals(this.buttons, that.buttons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buttons);
    }

    @Override
    public String toString() {
        return "ButtonListener[" +
                "buttonEvents=" + buttons + ']';
    }


}
