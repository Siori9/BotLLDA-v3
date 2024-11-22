package bot.llda.botlldav3.game.model.otherPart;

import java.util.UUID;

public class Attack {
    private UUID id;
    private String name;
    private Integer rank;

    public Attack(UUID id, String name, Integer rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
