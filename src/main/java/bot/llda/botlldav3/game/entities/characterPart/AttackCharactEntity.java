package bot.llda.botlldav3.game.entities.characterPart;

import bot.llda.botlldav3.game.entities.otherPart.AttackEntity;
import bot.llda.botlldav3.game.entities.otherPart.LvlEntity;
import bot.llda.botlldav3.game.model.characterPart.AttackCharact;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "attack_charact")
public class AttackCharactEntity {

    @Id
    private UUID id;

    @ManyToOne
    private AttackEntity attack;

    @ManyToOne
    @JoinColumn(name = "lvl_id")
    private LvlEntity lvl;

    private Integer xp;

    public AttackCharactEntity(AttackCharact attackCharact) {
        this.id = attackCharact.getId();
        this.attack = new AttackEntity(attackCharact.getAttack());
        this.lvl = new LvlEntity(attackCharact.getLvl());
        this.xp = attackCharact.getXp();
    }

    public AttackCharactEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AttackEntity getAttack() {
        return attack;
    }

    public void setAttack(AttackEntity attack) {
        this.attack = attack;
    }

    public LvlEntity getLvl() {
        return lvl;
    }

    public void setLvl(LvlEntity lvl) {
        this.lvl = lvl;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public AttackCharact toAttackCharact() {
        return new AttackCharact(
                this.id,
                this.attack.toAttack(),
                this.lvl.toLvl(),
                this.xp
        );
    }
}
