package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.worldPart.Crystal;
import bot.llda.botlldav3.game.model.worldPart.Equipment;

import java.util.UUID;

public class Stuff {
    private UUID id;
    private Equipment arms;
    private Equipment shield;
    private Equipment head;
    private Equipment body;
    private Equipment hands;
    private Equipment legs;
    private Equipment feet;
    private Equipment earrings;
    private Equipment necklace;
    private Equipment bracelets;
    private Equipment ring;
    private Crystal crystal1;
    private Crystal crystal2;
    private Crystal crystal3;

    public Stuff(UUID id, Equipment arms, Equipment shield, Equipment head, Equipment body, Equipment hands, Equipment legs, Equipment feet, Equipment earrings, Equipment necklace, Equipment bracelets, Equipment ring, Crystal crystal1, Crystal crystal2, Crystal crystal3) {
        this.id = id;
        this.arms = arms;
        this.shield = shield;
        this.head = head;
        this.body = body;
        this.hands = hands;
        this.legs = legs;
        this.feet = feet;
        this.earrings = earrings;
        this.necklace = necklace;
        this.bracelets = bracelets;
        this.ring = ring;
        this.crystal1 = crystal1;
        this.crystal2 = crystal2;
        this.crystal3 = crystal3;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Equipment getArms() {
        return arms;
    }

    public void setArms(Equipment arms) {
        this.arms = arms;
    }

    public Equipment getShield() {
        return shield;
    }

    public void setShield(Equipment shield) {
        this.shield = shield;
    }

    public Equipment getHead() {
        return head;
    }

    public void setHead(Equipment head) {
        this.head = head;
    }

    public Equipment getBody() {
        return body;
    }

    public void setBody(Equipment body) {
        this.body = body;
    }

    public Equipment getHands() {
        return hands;
    }

    public void setHands(Equipment hands) {
        this.hands = hands;
    }

    public Equipment getLegs() {
        return legs;
    }

    public void setLegs(Equipment legs) {
        this.legs = legs;
    }

    public Equipment getFeet() {
        return feet;
    }

    public void setFeet(Equipment feet) {
        this.feet = feet;
    }

    public Equipment getEarrings() {
        return earrings;
    }

    public void setEarrings(Equipment earrings) {
        this.earrings = earrings;
    }

    public Equipment getNecklace() {
        return necklace;
    }

    public void setNecklace(Equipment necklace) {
        this.necklace = necklace;
    }

    public Equipment getBracelets() {
        return bracelets;
    }

    public void setBracelets(Equipment bracelets) {
        this.bracelets = bracelets;
    }

    public Equipment getRing() {
        return ring;
    }

    public void setRing(Equipment ring) {
        this.ring = ring;
    }

    public Crystal getCrystal1() {
        return crystal1;
    }

    public void setCrystal1(Crystal crystal1) {
        this.crystal1 = crystal1;
    }

    public Crystal getCrystal2() {
        return crystal2;
    }

    public void setCrystal2(Crystal crystal2) {
        this.crystal2 = crystal2;
    }

    public Crystal getCrystal3() {
        return crystal3;
    }

    public void setCrystal3(Crystal crystal3) {
        this.crystal3 = crystal3;
    }
}
