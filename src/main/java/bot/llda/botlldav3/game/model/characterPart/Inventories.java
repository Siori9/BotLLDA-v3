package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Inventory;

import java.util.UUID;

public class Inventories {
    private UUID id;
    private Inventory inventory;
    private Inventory bankVault;

    public Inventories(UUID id, Inventory inventory, Inventory bankVault) {
        this.id = id;
        this.inventory = inventory;
        this.bankVault = bankVault;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getBankVault() {
        return bankVault;
    }

    public void setBankVault(Inventory bankVault) {
        this.bankVault = bankVault;
    }
}
