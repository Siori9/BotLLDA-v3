package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Attack;
import bot.llda.botlldav3.game.model.otherPart.Lvl;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

import java.util.UUID;

public class AttackCharact {

    private UUID id;
    private Attack attack;
    private Lvl lvl;
    private Integer xp;

    public AttackCharact(UUID id, Attack attack, Lvl lvl, Integer xp) {
        this.id = id;
        this.attack = attack;
        this.lvl = lvl;
        this.xp = xp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
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
