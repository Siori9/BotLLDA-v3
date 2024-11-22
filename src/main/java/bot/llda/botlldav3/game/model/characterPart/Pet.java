package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Lvl;
import bot.llda.botlldav3.game.model.worldPart.Creature;

import java.util.UUID;

public class Pet {
    private UUID id;
    private Creature creature;
    private String name;
    private Lvl lvl;
    private Integer xp;

    public Pet(UUID id, Creature creature, String name, Lvl lvl, Integer xp) {
        this.id = id;
        this.creature = creature;
        this.name = name;
        this.lvl = lvl;
        this.xp = xp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lvl getLvl() {
        return lvl;
    }

    public void setLvl(Lvl lvl) {
        this.lvl = lvl;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }
}
