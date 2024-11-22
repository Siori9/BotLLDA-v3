package bot.llda.botlldav3.game.model.characterPart;

import java.util.UUID;

public class Characteristics {
    private UUID id;
    private Integer strength;
    private Integer vitality;
    private Integer resistance;
    private Integer magic;
    private Integer speed;
    private Integer intelligence;
    private Integer precision;

    public Characteristics(UUID id, Integer strength, Integer vitality, Integer resistance, Integer magic, Integer speed, Integer intelligence, Integer precision) {
        this.id = id;
        this.strength = strength;
        this.vitality = vitality;
        this.resistance = resistance;
        this.magic = magic;
        this.speed = speed;
        this.intelligence = intelligence;
        this.precision = precision;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}
