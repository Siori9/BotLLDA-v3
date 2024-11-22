package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.game.repositories.TotoRepository;
import bot.llda.botlldav3.game.entities.Toto;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class TotoCreationCommand implements SlashCommand {
    @Override
    public String getName() {
        return "create-toto";
    }

    public TotoCreationCommand(TotoRepository totoRepository) {
        this.totoRepository = totoRepository;
    }

    private final TotoRepository totoRepository;

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Optional<Toto> maybeToto = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(name -> new Toto(name.asString()));

        return Mono.justOrEmpty(maybeToto)
                .map(totoRepository::save)
                .flatMap(toto -> event.reply(toto.toString()).withEphemeral(true));
    }
}
