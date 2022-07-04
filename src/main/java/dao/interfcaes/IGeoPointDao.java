package dao.interfcaes;

import dao.exceptions.GeoPointNotFoundException;
import dao.exceptions.UserNotFoundException;
import model.GeoPoint;

import java.util.Collection;
import java.util.List;

public interface IGeoPointDao {
    public Collection<GeoPoint> findUnratedGeoPointsFromUser(int userId) throws UserNotFoundException;
    public List<GeoPoint> findGeoPointById(long osmId);
}
