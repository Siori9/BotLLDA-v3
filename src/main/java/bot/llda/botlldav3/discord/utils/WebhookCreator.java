package bot.llda.botlldav3.discord.utils;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.InteractionCreateEvent;
import discord4j.core.object.entity.Webhook;
import discord4j.discordjson.json.WebhookCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Optional;

public abstract class WebhookCreator {

    public static Mono<Webhook> createWebhook(InteractionCreateEvent event, String name, String urlAvatar, String reason){
        return event.getClient().getRestClient().getWebhookService().createWebhook(event.getInteraction().getChannelId().asLong(),
                WebhookCreateRequest.builder().name(name).avatarOrNull(urlAvatar).build(),
                reason
        )
        .flatMap(webhookData ->
                event.getClient().getWebhookById(Snowflake.of(webhookData.id().asLong()))
        );
    }

}
