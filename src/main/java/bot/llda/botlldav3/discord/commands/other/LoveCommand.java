package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoveCommand implements SlashCommand {

    private final String[] loveMessage = {"Je t'aime fort ", "Tu es un rayon de soleil ", "Tu m'éblouis de ta beauté ", "Je sais pas ce que je ferai sans toi "};

    @Override
    public String getName() {
        return "love";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        var name = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(event.getInteraction().getUser().getMention());


        //Reply to the slash command, with the name the user supplied
        return event.reply()
//                .withEphemeral(true)
                .withContent(loveMessage[(int) (Math.random() * (loveMessage.length - 1))] + name);
    }
}
