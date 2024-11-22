package bot.llda.botlldav3.game.repositories.characterPart;

import bot.llda.botlldav3.game.entities.UserEntity;
import bot.llda.botlldav3.game.entities.characterPart.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {
    List<CharacterEntity> findAllByOwner(UserEntity owner);

    CharacterEntity findByOwnerAndSelected(UserEntity owner, Boolean selected);
}
