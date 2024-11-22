package bot.llda.botlldav3.game.repositories.otherPart;

import bot.llda.botlldav3.game.entities.otherPart.LvlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LvlRepository extends JpaRepository<LvlEntity, UUID> {
}
