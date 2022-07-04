package dao.interfcaes;

import model.GeoPointValue;

import java.util.Collection;

public interface IGeoPointValueDao {
    public Collection<GeoPointValue> findGeoPointByName(String name);
}
