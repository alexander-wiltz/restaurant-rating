package dao;

import dao.exceptions.RatingNotFoundException;
import dao.exceptions.UserNotFoundException;
import dao.interfcaes.IRatingDao;
import model.GeoPoint;
import model.Rating;
import model.User;
import utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.Collection;

public class RatingDaoImpl implements IRatingDao {

    private static RatingDaoImpl instance;
    private final EntityManagerProvider entityManagerProvider;

    private static class InstanceHolder {
        private static RatingDaoImpl instance = new RatingDaoImpl();
    }

    public static RatingDaoImpl getInstance() {
        if(instance == null) {
            instance = RatingDaoImpl.InstanceHolder.instance;
        }
        return instance;
    }

    private RatingDaoImpl() {
        entityManagerProvider = EntityManagerProvider.getInstance();
    }

    @Override
    public Rating createNewRating(User user, GeoPoint geoPoint, int grade, String txt) {
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setOsm(geoPoint);
        rating.setGrade(grade);
        rating.setTxt(txt);

        EntityManager em = entityManagerProvider.getNewEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(rating);
            em.flush();
            em.refresh(rating);
            em.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return rating;
    }

    @Override
    public void deleteRating(int ratingId) throws RatingNotFoundException {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Rating rating = em.createNamedQuery("Rating.findById", Rating.class).setParameter("ratingId", ratingId).getSingleResult();
        em.getTransaction().begin();
        try {
            em.remove(rating);
            em.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
