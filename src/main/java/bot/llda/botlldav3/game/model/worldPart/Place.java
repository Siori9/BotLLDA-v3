package bot.llda.botlldav3.game.model.worldPart;

import java.util.List;
import java.util.UUID;

public class Place {
    private UUID id;
    private String name;
    private String imageUrl;
    private List<UUID> nearbyPlacesID;

    public Place(UUID id, String name, String imageUrl, List<UUID> nearbyPlacesID) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.nearbyPlacesID = nearbyPlacesID;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<UUID> getNearbyPlacesID() {
        return nearbyPlacesID;
    }

    public void setNearbyPlacesID(List<UUID> nearbyPlacesID) {
        this.nearbyPlacesID = nearbyPlacesID;
    }
    public void addPlacesID(UUID id) {this.nearbyPlacesID.add(id);}
}
