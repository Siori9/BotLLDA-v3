package bot.llda.botlldav3.discord.utils;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.channel.ThreadChannel;
import discord4j.core.object.entity.channel.TopLevelGuildMessageChannel;
import discord4j.core.spec.StartThreadWithoutMessageSpec;
import reactor.core.publisher.Mono;

public abstract class ThreadCreator {
    public static Mono<ThreadChannel> createThread(ChatInputInteractionEvent event, String name){
        return event.deferReply()
                .withEphemeral(true)
                .then(event.getInteraction().getChannel())
                .ofType(TopLevelGuildMessageChannel.class) // TextChannel or NewsChannel
                .flatMap(ch -> ch.startThread(StartThreadWithoutMessageSpec.builder()
                        .name(name)
                        .type(ThreadChannel.Type.GUILD_PUBLIC_THREAD)
                        .build()));
    }
}
