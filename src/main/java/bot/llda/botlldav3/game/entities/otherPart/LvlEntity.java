package bot.llda.botlldav3.game.entities.otherPart;

import bot.llda.botlldav3.game.model.otherPart.Lvl;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "lvls")
public class LvlEntity {

    @Id
    private UUID id;

    private Integer lvl;
    private Integer xpNextLvl;

    public LvlEntity(Lvl lvl){
        this.id = lvl.getId();
        this.lvl = lvl.getLvl();
        this.xpNextLvl = lvl.getXpNextLvl();
    }

    public LvlEntity() {

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

    public Lvl toLvl(){
        return new Lvl(
                this.id,
                this.lvl,
                this.xpNextLvl
        );
    }

}
