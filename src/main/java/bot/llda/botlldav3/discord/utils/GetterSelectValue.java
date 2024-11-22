package bot.llda.botlldav3.discord.utils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public abstract class GetterSelectValue {

    static Logger LOGGER = LoggerFactory.getLogger(GetterSelectValue.class);
    public static Mono<String> getSelectSingleValue(GatewayDiscordClient gateway, String idSelect) {

        return gateway.on(SelectMenuInteractionEvent.class)
                .map(eventMenu -> {
                    LOGGER.info("Je suis là !");
                    return eventMenu;
                })
                .filter(eventMenu -> eventMenu.getCustomId().equals(idSelect))
                .take(1)
                .singleOrEmpty()
                .map(interaction -> {
                    LOGGER.info(interaction.getValues().getFirst());
                    return interaction.getValues().getFirst();
                });
    }


    public static Mono<String> getAllSelectValue(GatewayDiscordClient gateway, String idSelect) {

        return gateway.on(SelectMenuInteractionEvent.class)
                .filter(eventMenu -> eventMenu.getCustomId().equals(idSelect))
                .take(1)
                .singleOrEmpty()
                .map(interaction -> {
                    LOGGER.info(interaction.getValues().getFirst());
                    return interaction.getValues().getFirst();
                });
    }
}
