package bot.llda.botlldav3.game.repositories;

import bot.llda.botlldav3.game.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public UserEntity findByDiscordId(String discordId);
}
