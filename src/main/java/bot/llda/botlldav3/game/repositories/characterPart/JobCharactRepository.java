package bot.llda.botlldav3.game.repositories.characterPart;

import bot.llda.botlldav3.game.entities.characterPart.JobCharactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobCharactRepository extends JpaRepository<JobCharactEntity, UUID> {
}
