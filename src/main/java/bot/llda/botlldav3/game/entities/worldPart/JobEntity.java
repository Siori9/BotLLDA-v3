package bot.llda.botlldav3.game.entities.worldPart;

import bot.llda.botlldav3.game.model.worldPart.Job;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job")
public class JobEntity {
    @Id
    private UUID id;

    private String name;

    public JobEntity(Job job){
        this.id = job.getId();
        this.name = job.getName();
    }

    public JobEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job toJob(){
        return new Job(
                this.id,
                this.name
        );
    }
}
