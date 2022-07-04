package dao;

import dao.exceptions.GeoPointNotFoundException;
import dao.exceptions.UserNotFoundException;
import dao.interfcaes.IGeoPointDao;
import model.GeoPoint;
import model.Rating;
import utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public class GeoPointDaoImpl implements IGeoPointDao {

    private static GeoPointDaoImpl instance;
    private final EntityManagerProvider entityManagerProvider;

    private static class InstanceHolder {
        private static GeoPointDaoImpl instance = new GeoPointDaoImpl();
    }

    public static GeoPointDaoImpl getInstance() {
        if(instance == null) {
            instance = GeoPointDaoImpl.InstanceHolder.instance;
        }
        return instance;
    }

    private GeoPointDaoImpl() {
        entityManagerProvider = EntityManagerProvider.getInstance();
    }

    @Override
    public Collection<GeoPoint> findUnratedGeoPointsFromUser(int userId) throws UserNotFoundException {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Collection<GeoPoint> allGeoPoints = em.createNamedQuery("GeoPoint.findAll", GeoPoint.class).getResultList();
        Collection<Rating> ratingsFromUserId = em.createNamedQuery("Rating.findByUser", Rating.class).setParameter("userId",userId).getResultList();
        em.close();

        for(Rating r : ratingsFromUserId) {
            allGeoPoints.remove(r.getOsm());
        }

        return allGeoPoints;
    }

    @Override
    public List<GeoPoint> findGeoPointById(long osmId) {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        List<GeoPoint> geoPoints = em.createNamedQuery("GeoPoint.findById", GeoPoint.class).setParameter("osmId",osmId).getResultList();
        em.close();

        return geoPoints;
    }
}
