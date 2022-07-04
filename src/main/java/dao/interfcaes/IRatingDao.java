package dao.interfcaes;

import dao.exceptions.RatingNotFoundException;
import dao.exceptions.UserNotFoundException;
import model.GeoPoint;
import model.Rating;
import model.User;

import java.util.Collection;

public interface IRatingDao {
    public Rating createNewRating(User user, GeoPoint geoPoint, int grade, String txt);
    public void deleteRating(int ratingId) throws RatingNotFoundException;
}
