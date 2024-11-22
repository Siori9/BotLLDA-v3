package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.worldPart.Quest;

import java.util.UUID;

public class QuestCharact {
    private UUID id;
    private Quest quest;

    public QuestCharact(UUID id, Quest quest) {
        this.id = id;
        this.quest = quest;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
