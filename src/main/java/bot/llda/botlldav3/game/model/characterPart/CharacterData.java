package bot.llda.botlldav3.game.model.characterPart;

import bot.llda.botlldav3.game.model.User;
import bot.llda.botlldav3.game.model.otherPart.Families;
import bot.llda.botlldav3.game.model.otherPart.Lvl;
import bot.llda.botlldav3.game.model.worldPart.Place;

import java.util.List;
import java.util.UUID;

public class CharacterData {
    private UUID id;
    private String name;
    private String avatarUrl;
    private Families family;
    private Guide guide;
    private User owner;
    private Lvl lvl;
    private Integer xp;
    private Boolean selected;
    private List<JobCharact> jobs;
    private UUID locationID;

    public CharacterData(UUID id, String name, String avatarUrl, Families family, Guide guide, User owner, Lvl lvl, Integer xp, List<JobCharact> jobs, UUID locationID, Boolean selected) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.family = family;
        this.guide = guide;
        this.owner = owner;
        this.lvl = lvl;
        this.xp = xp;
        this.jobs = jobs;
        this.locationID = locationID;
        this.selected = selected;
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

    public Families getFamily() {
        return family;
    }

    public void setFamily(Families family) {
        this.family = family;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public List<JobCharact> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobCharact> jobs) {
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

    @Override
    public String toString() {
        return "CharacterData{" +
                "name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", family=" + family +
                ", guide=" + guide +
                ", owner=" + owner +
                ", lvl=" + lvl +
                ", xp=" + xp +
                ", jobs=" + jobs +
                ", locationID=" + locationID +
                '}';
    }
}
