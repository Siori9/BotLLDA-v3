package bot.llda.botlldav3.game.entities.characterPart;

import bot.llda.botlldav3.game.entities.otherPart.LvlEntity;
import bot.llda.botlldav3.game.entities.worldPart.JobEntity;
import bot.llda.botlldav3.game.model.characterPart.JobCharact;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job_charact")
public class JobCharactEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;

    @ManyToOne
    @JoinColumn(name = "lvl_id")
    private LvlEntity lvl;

    private Integer xp;

    public JobCharactEntity(JobCharact jobCharact){
        this.id = jobCharact.getId();
        this.job = new JobEntity(jobCharact.getJob());
        this.lvl = new LvlEntity(jobCharact.getLvl());
        this.xp = jobCharact.getXp();
    }

    public JobCharactEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public LvlEntity getLvl() {
        return lvl;
    }

    public void setLvl(LvlEntity lvl) {
        this.lvl = lvl;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public JobCharact toJobCharact(){
        return new JobCharact(
                this.id,
                this.job.toJob(),
                this.lvl.toLvl(),
                this.xp
        );
    }
}
