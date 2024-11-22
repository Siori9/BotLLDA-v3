package bot.llda.botlldav3.game.model.characterPart;

public class Character {

    private CharacterData character;

    public Character(CharacterData character) {
        this.character = character;
    }

    public CharacterData getCharacter() {
        return character;
    }

    public void setCharacter(CharacterData character) {
        this.character = character;
    }

    public Inventories getInventories(){
        return null;
    }

    public Pets getPets(){
        return null;
    }

    public Collections getCollections(){
        return null;
    }

    public Combat getCombat(){
        return null;
    }

    public Missions getMissions(){
        return null;
    }

    public Stats getStats(){
        return null;
    }

    public Stuff getStuff(){
        return null;
    }
}
