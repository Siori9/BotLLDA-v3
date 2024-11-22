package bot.llda.botlldav3.discord.utils;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.Webhook;
import reactor.core.publisher.Mono;

public abstract class CreateWebhook {

    public static Mono<Webhook> getWebhook(String name, ChatInputInteractionEvent event){
        return switch (name){
            case "Clara":
                yield WebhookCreator.createWebhook(event, "Clara", "https://i.pinimg.com/564x/61/68/18/616818d09f2687401e4d67a970792130.jpg", "Pnj character creator");
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        };
    }
}
