package bot.llda.botlldav3.game.repositories.worldPart;

import bot.llda.botlldav3.game.entities.worldPart.PnjEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PnjRepository extends JpaRepository<PnjEntity, UUID> {
}
