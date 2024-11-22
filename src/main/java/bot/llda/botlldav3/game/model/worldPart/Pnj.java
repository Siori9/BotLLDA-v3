package bot.llda.botlldav3.game.model.worldPart;

import bot.llda.botlldav3.game.model.otherPart.Families;

import java.util.UUID;

public class Pnj {
    private UUID id;
    private String name;
    private String avatarUrl;
    private Families family;
    private String description;

    public Pnj(UUID id, String name, String avatarUrl, Families family, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.family = family;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Families getFamily() {
        return family;
    }

    public void setFamily(Families family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
