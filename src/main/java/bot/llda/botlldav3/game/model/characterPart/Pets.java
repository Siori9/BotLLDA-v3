package bot.llda.botlldav3.game.model.characterPart;

import java.util.List;
import java.util.UUID;

public class Pets {
    private UUID id;
    private Pet mainPet;
    private List<Pet> pets;

    public Pets(UUID id, Pet mainPet, List<Pet> pets) {
        this.id = id;
        this.mainPet = mainPet;
        this.pets = pets;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pet getMainPet() {
        return mainPet;
    }

    public void setMainPet(Pet mainPet) {
        this.mainPet = mainPet;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
