package bot.llda.botlldav3.game.repositories;

import bot.llda.botlldav3.game.entities.Toto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TotoRepository extends JpaRepository<Toto, UUID> {}
