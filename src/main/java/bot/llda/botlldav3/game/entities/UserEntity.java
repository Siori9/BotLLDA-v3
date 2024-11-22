package bot.llda.botlldav3.game.entities;

import bot.llda.botlldav3.game.model.TypesUserFunctions;
import bot.llda.botlldav3.game.model.User;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    private String discordId;

    private String type;

    public UserEntity(User user) {
        this.id = user.getId();
        this.discordId = user.getDiscordId();
        this.type = TypesUserFunctions.typeToString(user.getType());
    }

    public UserEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User toUser(){
        return new User(
                this.id,
                this.discordId,
                TypesUserFunctions.stringToType(this.type)
        );
    }
}
