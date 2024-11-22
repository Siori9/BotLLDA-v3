package bot.llda.botlldav3.game.model.characterPart;

import java.util.UUID;

public class Stats {
    private UUID id;
    private Integer vito;
    private Characteristics characteristics;

    public Stats(UUID id, Integer vito, Characteristics characteristics) {
        this.id = id;
        this.vito = vito;
        this.characteristics = characteristics;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVito() {
        return vito;
    }

    public void setVito(Integer vito) {
        this.vito = vito;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }
}
