package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GeoPointValueId implements Serializable {
    private static final long serialVersionUID = -8386908480867253516L;
    @Column(name = "osm_id", nullable = false)
    private Long osmId;

    @Column(name = "tag", nullable = false)
    private String tag;

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoPointValueId entity = (GeoPointValueId) o;
        return Objects.equals(this.osmId, entity.osmId) &&
                Objects.equals(this.tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osmId, tag);
    }

}