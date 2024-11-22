package bot.llda.botlldav3.game.model.characterPart;

import java.util.List;
import java.util.UUID;

public class Combat {
    private UUID id;
    private Integer pv;
    private Integer pm;
    private List<AttackCharact> attacksSelect;
    private List<AttackCharact> attacks;

    public Combat(UUID id, Integer pv, Integer pm, List<AttackCharact> attacksSelect, List<AttackCharact> attacks) {
        this.id = id;
        this.pv = pv;
        this.pm = pm;
        this.attacksSelect = attacksSelect;
        this.attacks = attacks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<AttackCharact> getAttacksSelect() {
        return attacksSelect;
    }

    public void setAttacksSelect(List<AttackCharact> attacksSelect) {
        this.attacksSelect = attacksSelect;
    }

    public List<AttackCharact> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<AttackCharact> attacks) {
        this.attacks = attacks;
    }
}
