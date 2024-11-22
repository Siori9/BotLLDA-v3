package bot.llda.botlldav3.game.model.characterPart;

import java.util.List;
import java.util.UUID;

public class Missions {
    private UUID id;
    private List<QuestCharact> quests;

    public Missions(UUID id, List<QuestCharact> quests) {
        this.id = id;
        this.quests = quests;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<QuestCharact> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestCharact> quests) {
        this.quests = quests;
    }
}
