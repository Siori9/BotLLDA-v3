package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.game.repositories.TotoRepository;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class DatabaseCommand implements SlashCommand {
    public DatabaseCommand(TotoRepository totoRepository) {
        this.totoRepository = totoRepository;
    }

    @Override
    public String getName() {
        return "toto";
    }

    private final TotoRepository totoRepository;

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply("Toto : " + totoRepository.findAll())
                .withEphemeral(true);
    }

    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("toto")
                .description("Get all toto")
                .build());
    }
}
