package bot.llda.botlldav3.game.repositories.characterPart;

import bot.llda.botlldav3.game.entities.characterPart.AttackCharactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttackCharactRepository extends JpaRepository<AttackCharactEntity, UUID> {
}
