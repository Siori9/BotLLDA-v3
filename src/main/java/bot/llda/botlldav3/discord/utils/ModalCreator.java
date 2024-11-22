package bot.llda.botlldav3.discord.utils;

import discord4j.core.event.domain.interaction.DeferrableInteractionEvent;
import discord4j.core.object.component.LayoutComponent;
import discord4j.core.spec.InteractionPresentModalSpec;
import reactor.core.publisher.Mono;

import java.util.Collection;

public abstract class ModalCreator {

    public static Mono<Void> createModal(DeferrableInteractionEvent event, String name, String id, Collection<LayoutComponent> components) {
        return event.presentModal(
                InteractionPresentModalSpec.builder()
                        .title(name)
                        .customId(id)
                        .addAllComponents(components)
                        .build()

        );
    }

}
