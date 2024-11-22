package bot.llda.botlldav3.game.repositories.characterPart;

import bot.llda.botlldav3.game.entities.characterPart.GuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuideRepository extends JpaRepository<GuideEntity, UUID> {
}
