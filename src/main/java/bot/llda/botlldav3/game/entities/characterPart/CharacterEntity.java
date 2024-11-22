package bot.llda.botlldav3.game.entities.characterPart;

import bot.llda.botlldav3.game.entities.UserEntity;
import bot.llda.botlldav3.game.entities.otherPart.LvlEntity;
import bot.llda.botlldav3.game.entities.worldPart.PlaceEntity;
import bot.llda.botlldav3.game.model.characterPart.CharacterData;
import bot.llda.botlldav3.game.model.otherPart.FamiliesFunctions;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "characters")
public class CharacterEntity {

    @Id
    private UUID id;
    private String name;
    private String avatarUrl;
    private String family;

    @OneToOne(cascade = CascadeType.ALL)
    private GuideEntity guide;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lvl_id")
    private LvlEntity lvl;

    private Integer xp;
    private Boolean selected;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobCharactEntity> jobs;

    private UUID locationID;

    public CharacterEntity(CharacterData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.avatarUrl = data.getAvatarUrl();
        this.family = FamiliesFunctions.familyToString(data.getFamily());
        this.guide = new GuideEntity(data.getGuide());
        this.owner = new UserEntity(data.getOwner());
        this.lvl = new LvlEntity(data.getLvl());
        this.xp = data.getXp();
        this.jobs = new HashSet<JobCharactEntity>();
        this.locationID = data.getLocation();
        this.selected = data.getSelected();
    }

    public CharacterEntity() {

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public GuideEntity getGuide() {
        return guide;
    }

    public void setGuide(GuideEntity guide) {
        this.guide = guide;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
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

    public Set<JobCharactEntity> getJobs() {
        return jobs;
    }

    public void setJobs(Set<JobCharactEntity> jobs) {
        this.jobs = jobs;
    }

    public UUID getLocation() {
        return locationID;
    }

    public void setLocation(UUID locationID) {
        this.locationID = locationID;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public CharacterData toCharacterData() {
        return new CharacterData(
                this.id,
                this.name,
                this.avatarUrl,
                FamiliesFunctions.stringToFamily(this.family),
                this.guide.toGuide(),
                this.owner.toUser(),
                this.lvl.toLvl(),
                this.xp,
                this.jobs.stream().map(
                        JobCharactEntity::toJobCharact
                ).collect(Collectors.toList()),
                this.locationID,
                this.selected
        );
    }
}
