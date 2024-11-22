package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Lvl;
import bot.llda.botlldav3.game.model.worldPart.Pnj;

import java.util.List;
import java.util.UUID;

public class Guide {
    private UUID id;
    private Pnj pnj;
    private Lvl lvl;
    private Integer xp;
    private Integer pv;
    private Integer pm;
    private List<AttackCharact> attacks;
    private List<AttackCharact> attacksSelect;

    public Guide(UUID id, Pnj pnj, Lvl lvl, Integer xp, Integer pv, Integer pm, List<AttackCharact> attacks, List<AttackCharact> attacksSelect) {
        this.id = id;
        this.pnj = pnj;
        this.lvl = lvl;
        this.xp = xp;
        this.pv = pv;
        this.pm = pm;
        this.attacks = attacks;
        this.attacksSelect = attacksSelect;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pnj getPnj() {
        return pnj;
    }

    public void setPnj(Pnj pnj) {
        this.pnj = pnj;
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

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public List<AttackCharact> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<AttackCharact> attacks) {
        this.attacks = attacks;
    }

    public List<AttackCharact> getAttacksSelect() {
        return attacksSelect;
    }

    public void setAttacksSelect(List<AttackCharact> attacksSelect) {
        this.attacksSelect = attacksSelect;
    }
}
