package bot.llda.botlldav3.game.entities.characterPart;

import bot.llda.botlldav3.game.entities.otherPart.LvlEntity;
import bot.llda.botlldav3.game.entities.worldPart.PnjEntity;
import bot.llda.botlldav3.game.model.characterPart.Guide;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "guides")
public class GuideEntity {

    @Id
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pnj_id")
    private PnjEntity pnj;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lvl_id")
    private LvlEntity lvl;

    private Integer xp;
    private Integer pv;
    private Integer pm;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AttackCharactEntity> attacks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AttackCharactEntity> attacksSelect;

    public GuideEntity(Guide guide) {
        this.id = guide.getId();
        this.pnj = new PnjEntity(guide.getPnj());
        this.lvl = new LvlEntity(guide.getLvl());
        this.xp = guide.getXp();
        this.pv = guide.getPv();
        this.pm = guide.getPm();
        this.attacks = guide.getAttacks().stream().map(AttackCharactEntity::new).collect(Collectors.toSet());
        this.attacksSelect = guide.getAttacksSelect().stream().map(AttackCharactEntity::new).collect(Collectors.toSet());
    }

    public GuideEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PnjEntity getPnj() {
        return pnj;
    }

    public void setPnj(PnjEntity pnj) {
        this.pnj = pnj;
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

    public Set<AttackCharactEntity> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<AttackCharactEntity> attacks) {
        this.attacks = attacks;
    }

    public Set<AttackCharactEntity> getAttacksSelect() {
        return attacksSelect;
    }

    public void setAttacksSelect(Set<AttackCharactEntity> attacksSelect) {
        this.attacksSelect = attacksSelect;
    }

    public Guide toGuide() {
        return new Guide(
                this.id,
                this.pnj.toPnj(),
                this.lvl.toLvl(),
                this.xp,
                this.pv,
                this.pm,
                this.attacks.stream().map(
                        AttackCharactEntity::toAttackCharact
                ).collect(Collectors.toList()),
                this.attacksSelect.stream().map(
                        AttackCharactEntity::toAttackCharact
                ).collect(Collectors.toList())
        );
    }
}
