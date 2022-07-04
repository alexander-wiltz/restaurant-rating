package model;

import javax.persistence.*;

@Entity
@Table(name = "geo_point_value")
@NamedQueries({
        @NamedQuery(name="GeoPointValue.findAll", query = "SELECT gv FROM GeoPointValue gv WHERE gv.id.tag = 'name'"),
        @NamedQuery(name="GeoPointValue.findByName", query = "SELECT gv FROM GeoPointValue gv WHERE gv.id.tag = 'name' AND gv.value LIKE :name"),
        @NamedQuery(name="GeoPointValue.findById", query = "SELECT gv FROM GeoPointValue gv WHERE gv.id = :osmId"),
        @NamedQuery(name="GeoPointValue.getGPVNames", query = "SELECT gv FROM GeoPointValue gv WHERE gv.id.tag = 'name'")
})
public class GeoPointValue {
    @EmbeddedId
    private GeoPointValueId id;

    @MapsId("osmId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "osm_id", nullable = false)
    private GeoPoint geoPoint;

    @Column(name = "value", nullable = false)
    private String value;

    public GeoPointValueId getId() {
        return id;
    }

    public void setId(GeoPointValueId id) {
        this.id = id;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GeoPointValue{id=" + id + ", value='" + value + "'}";
    }
}