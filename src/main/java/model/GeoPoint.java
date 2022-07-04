package model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "geo_point")
@NamedQueries({
        @NamedQuery(name = "GeoPoint.findAll", query = "SELECT g FROM GeoPoint g"),
        @NamedQuery(name="GeoPoint.findByPoiType", query = "SELECT g FROM GeoPoint g WHERE g.poiType LIKE :poiType"),
        @NamedQuery(name="GeoPoint.findById", query = "SELECT g FROM GeoPoint g WHERE g.id = :osmId")
})
public class GeoPoint {
    @Id
    @Column(name = "osm_id", nullable = false)
    private long id;

    @Column(name = "poi_type", nullable = false)
    private String poiType;

    @OneToMany(mappedBy = "geoPoint")
    private Set<GeoPointValue> geoPointValues = new LinkedHashSet<>();
    @OneToMany(mappedBy = "osm")
    private Set<Rating> ratings = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public Set<GeoPointValue> getGeoPointValues() {
        return geoPointValues;
    }

    public void setGeoPointValues(Set<GeoPointValue> geoPointValues) {
        this.geoPointValues = geoPointValues;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Transient
    public String getGeoPointName() {
        for (GeoPointValue tag : this.getGeoPointValues()) {
            if (tag.getId().getTag().equals("name")) {
                return tag.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "GeoPoint{id=" + id + ", poiType='" + poiType + "'}";
    }

}