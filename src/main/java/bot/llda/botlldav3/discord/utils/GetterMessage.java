package bot.llda.botlldav3.discord.utils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.time.Duration;

public abstract class GetterMessage {

    public static boolean isResponse(Message nextMessage, Message message) {
        return nextMessage.getReferencedMessage().filter(msg -> message.getId().equals(msg.getId())).isPresent();
    }

    public static reactor.core.publisher.Flux<Message> getMessages(GatewayDiscordClient gateway, Message message) {
        return message
                .getChannel()
                .flatMapMany(channel ->
                        gateway.on(MessageCreateEvent.class).filter(messageCreateEvent ->
                                messageCreateEvent.getMessage().getChannelId().equals(channel.getId())
                        ).map(MessageCreateEvent::getMessage));
    }

    public static Mono<Message> getFirstResponseToMessage(GatewayDiscordClient gateway, Message message, Duration duration) {
        return getMessages(gateway, message).filter(nextMessage -> isResponse(nextMessage, message))
                .take(duration)
                .take(1)
                .singleOrEmpty();
    }
}
