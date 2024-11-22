package bot.llda.botlldav3.discord.commands.other;

import java.time.*;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.component.SelectMenu;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EmbedCommand implements SlashCommand {

    @Override
    public String getName() {
        return "embed";
    }

    EmbedCreateSpec embed = EmbedCreateSpec.builder()
            .color(Color.BLUE)
            .title("Title")
            .url("https://discord4j.com")
            .author("Some Name", "https://discord4j.com", "https://i.imgur.com/F9BhEoz.png")
            .description("a description")
            .thumbnail("https://i.imgur.com/F9BhEoz.png")
            .addField("field title", "value", false)
            .addField("\u200B", "\u200B", false)
            .addField("inline field", "value", true)
            .addField("inline field", "value", true)
            .addField("inline field", "value", true)
            .image("https://i.imgur.com/F9BhEoz.png")
            .timestamp(Instant.now())
            .footer("footer", "https://i.imgur.com/F9BhEoz.png")
            .build();

    Button button = Button.success("custom", "Action");
    Button button2 = Button.danger("custom-id2", "Action 2");

    SelectMenu select = SelectMenu.of("custom-id3",
            SelectMenu.Option.of("label", "value"),
            SelectMenu.Option.of("label2", "value2"),
            SelectMenu.Option.of("label3", "value3"));

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply().withEmbeds(embed)
                .withComponents(ActionRow.of(button, button2), ActionRow.of(select));
    }
}

