package dao;

import dao.interfcaes.IGeoPointValueDao;
import model.GeoPointValue;
import utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.Collection;

public class GeoPointValueDaoImpl implements IGeoPointValueDao {

    private static GeoPointValueDaoImpl instance;
    private final EntityManagerProvider entityManagerProvider;

    private static class InstanceHolder {
        private static GeoPointValueDaoImpl instance = new GeoPointValueDaoImpl();
    }

    public static GeoPointValueDaoImpl getInstance() {
        if(instance == null) {
            instance = InstanceHolder.instance;
        }
        return instance;
    }

    private GeoPointValueDaoImpl() {
        entityManagerProvider = EntityManagerProvider.getInstance();
    }

    @Override
    public Collection<GeoPointValue> findGeoPointByName(String name) {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Collection<GeoPointValue> result = em.createNamedQuery("GeoPointValue.findByName", GeoPointValue.class).setParameter("name","%" + name + "%").getResultList();
        em.close();

        return result;
    }

}
