package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.otherPart.Lvl;
import bot.llda.botlldav3.game.model.worldPart.Job;

import java.util.UUID;

public class JobCharact {
    private UUID id;
    private Job job;
    private Lvl lvl;
    private Integer xp;

    public JobCharact(UUID id, Job job, Lvl lvl, Integer xp) {
        this.id = id;
        this.job = job;
        this.lvl = lvl;
        this.xp = xp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Lvl getLvl() {
        return lvl;
    }

    public void setLvl(Lvl lvl) {
        this.lvl = lvl;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }
}
