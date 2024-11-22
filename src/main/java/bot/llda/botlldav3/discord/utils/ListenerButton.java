package bot.llda.botlldav3.discord.utils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.InteractionCallbackSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public abstract class ListenerButton {

    public static Logger LOGGER = LoggerFactory.getLogger(ListenerButton.class);

    public static Mono<Button> getButtonActivate(GatewayDiscordClient gateway,
                                                 Message message,
                                                 List<Button> buttons,
                                                 Duration duration,
                                                 Function<ButtonInteractionEvent, Mono<Void>> function) {
        return gateway.on(ButtonInteractionEvent.class)
                .flatMap(event ->
                        Flux.fromIterable(buttons)
                                .filter(button -> button
                                        .getCustomId()
                                        .map(id -> id.equals(event.getCustomId()))
                                        .orElse(false)
                                ).flatMap(button ->
                                        function.apply(event).thenReturn(button)
                                )
                ).next();
    }

    public static <T> Flux<T> getButtonInList(GatewayDiscordClient gateway,
                                              Message message,
                                              List<Button> buttons,
                                              Duration duration,
                                              Function<ButtonInteractionEvent, Mono<T>> function) {
        return gateway.on(ButtonInteractionEvent.class)
                .flatMap(event ->
                        Flux.fromIterable(buttons)
                                .filter(button -> button
                                        .getCustomId()
                                        .map(id -> id.equals(event.getCustomId()))
                                        .orElse(false)
                                ).flatMap(button ->
                                        function.apply(event)
                                )
                );
    }
}
