package bot.llda.botlldav3.game.repositories.worldPart;

import bot.llda.botlldav3.game.entities.worldPart.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
