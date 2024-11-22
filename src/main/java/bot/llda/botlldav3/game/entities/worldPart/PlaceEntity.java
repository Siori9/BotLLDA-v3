package bot.llda.botlldav3.game.entities.worldPart;

import bot.llda.botlldav3.game.model.worldPart.Place;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    private UUID id;

    private String name;

    private String imageUrl;

    @ElementCollection
    private List<UUID> nearbyPlaces;

    public PlaceEntity(Place place){
        this.id = place.getId();
        this.name = place.getName();
        this.imageUrl = place.getImageUrl();
        this.nearbyPlaces = place.getNearbyPlacesID();
    }

    public PlaceEntity() {

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

    public List<UUID> getNearbyPlaces() {
        return nearbyPlaces;
    }

    public void setNearbyPlaces(List<UUID> nearbyPlaces) {
        this.nearbyPlaces = nearbyPlaces;
    }

    public Place toPlace(){
        return new Place(
                this.id,
                this.name,
                this.imageUrl,
                this.nearbyPlaces
        );
    }
}
