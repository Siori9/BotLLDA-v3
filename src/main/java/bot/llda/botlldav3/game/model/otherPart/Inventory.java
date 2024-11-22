package bot.llda.botlldav3.game.model.otherPart;

import bot.llda.botlldav3.game.model.worldPart.Item;

import java.util.List;
import java.util.UUID;

public class Inventory {
    private UUID id;
    private List<Item> items;
    private Integer money;

    public Inventory(UUID id, List<Item> items, Integer money) {
        this.id = id;
        this.items = items;
        this.money = money;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
