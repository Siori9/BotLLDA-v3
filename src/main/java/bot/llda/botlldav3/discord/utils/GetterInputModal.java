package bot.llda.botlldav3.discord.utils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.object.component.TextInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public abstract class GetterInputModal {

    public static Logger LOGGER = LoggerFactory.getLogger(GetterInputModal.class);

    public static Mono<String> getInputContain(GatewayDiscordClient gateway, String modalId, String inputId, Duration duration) {
        return gateway.on(ModalSubmitInteractionEvent.class)
                .filter(event -> event.getCustomId().equals(modalId))
                .flatMap(event -> event.deferEdit()
                        .thenMany(Flux.fromIterable(event.getComponents(TextInput.class)))
                        .filter(component -> component.getData().customId()
                                .toOptional().map(id -> id.equals(inputId))
                                .orElse(false))
                        .flatMap(component -> Mono.justOrEmpty(component.getData().value().toOptional())))
                .take(duration)
                .take(1)
                .singleOrEmpty();
    }

    public static Mono<List<String>> getAllInputModal(GatewayDiscordClient gateway, String modalId, Duration duration, int nbInput) {
        return gateway.on(ModalSubmitInteractionEvent.class)
                .filter(event -> event.getCustomId().equals(modalId))
                .flatMap(event -> event.deferEdit()
                        .thenMany(Flux.fromIterable(event.getComponents(TextInput.class)))
                        .flatMap(component -> Mono.justOrEmpty(component.getData().value().toOptional()))
                )
                .take(duration)
                .take(nbInput)
                .collectSortedList();
    }
}
