package bot.llda.botlldav3.game.entities.worldPart;

import bot.llda.botlldav3.game.model.otherPart.FamiliesFunctions;
import bot.llda.botlldav3.game.model.worldPart.Pnj;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pnjs")
public class PnjEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String avatarUrl;
    private String family;
    private String description;

    public PnjEntity(Pnj pnj){
        this.id = getId();
        this.name = pnj.getName();
        this.avatarUrl = pnj.getAvatarUrl();
        this.family = FamiliesFunctions.familyToString(pnj.getFamily());
        this.description = pnj.getDescription();
    }

    public PnjEntity() {

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pnj toPnj(){
        return new Pnj(
                this.id,
                this.name,
                this.avatarUrl,
                FamiliesFunctions.stringToFamily(this.family),
                this.description
        );
    }

}
