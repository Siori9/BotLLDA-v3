package bot.llda.botlldav3.game.entities.otherPart;

import bot.llda.botlldav3.game.model.otherPart.Attack;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "attack")
public class AttackEntity {
    @Id
    private UUID id;

    private String name;
    private Integer rank;

    public AttackEntity(Attack attack){
        this.id = attack.getId();
        this.name = attack.getName();
        this.rank = attack.getRank();
    }

    public AttackEntity() {

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

    public Attack toAttack(){
        return new Attack(
                this.id,
                this.name,
                this.rank
        );
    }
}
