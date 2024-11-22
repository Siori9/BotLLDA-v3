package bot.llda.botlldav3.game.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "toto")
public class Toto {
    @Id
    @Column(name = "toto_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID totoId;

    private String name;

    protected Toto() {}

    @Override
    public String toString() {
        return "Toto{" +
                "totoId=" + totoId +
                ", name='" + name + '\'' +
                '}';
    }

    public Toto(UUID totoId, String name) {
        this.totoId = totoId;
        this.name = name;
    }

    public Toto(String name) {
        this.name = name;
    }

    public UUID getTotoId() {
        return totoId;
    }

    public void setTotoId(UUID totoId) {
        this.totoId = totoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
