package bot.llda.botlldav3.game.model.otherPart;

import java.util.UUID;

public class Lvl {
    private UUID id;
    private Integer lvl;
    private Integer xpNextLvl;

    public Lvl(UUID id, Integer lvl, Integer xpNextLvl) {
        this.id = id;
        this.lvl = lvl;
        this.xpNextLvl = xpNextLvl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Integer getXpNextLvl() {
        return xpNextLvl;
    }

    public void setXpNextLvl(Integer xpNextLvl) {
        this.xpNextLvl = xpNextLvl;
    }
}
