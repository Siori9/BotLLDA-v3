package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Symbol;
import bot.llda.botlldav3.game.model.worldPart.Pnj;

import java.util.List;
import java.util.UUID;

public class Collections {
    private UUID id;
    private List<Symbol> symbols;
    private List<Pnj> pnjs;
    private List<Character> friends;

    public Collections(UUID id, List<Symbol> symbols, List<Pnj> pnjs, List<Character> friends) {
        this.id = id;
        this.symbols = symbols;
        this.pnjs = pnjs;
        this.friends = friends;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public List<Pnj> getPnjs() {
        return pnjs;
    }

    public void setPnjs(List<Pnj> pnjs) {
        this.pnjs = pnjs;
    }

    public List<Character> getFriends() {
        return friends;
    }

    public void setFriends(List<Character> friends) {
        this.friends = friends;
    }
}
