package bot.llda.botlldav3.game.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String discordId;
    private TypesUser type;

    public User(UUID id, String discordId, TypesUser type) {
        this.id = id;
        this.discordId = discordId;
        this.type = type;
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

    public TypesUser getType() {
        return type;
    }

    public void setType(TypesUser type) {
        this.type = type;
    }
}
