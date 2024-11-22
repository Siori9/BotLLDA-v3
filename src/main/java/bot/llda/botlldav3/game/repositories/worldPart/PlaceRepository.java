package bot.llda.botlldav3.game.repositories.worldPart;

import bot.llda.botlldav3.game.entities.worldPart.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<PlaceEntity, UUID> {
}
