package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

    private static EntityManagerProvider instance;

    private static class InstanceHolder {
        private static EntityManagerProvider instance = new EntityManagerProvider();
    }

    public static EntityManagerProvider getInstance() {
        if(instance == null) {
            instance = InstanceHolder.instance;
        }
        return instance;
    }

    private final EntityManagerFactory factory;

    private EntityManagerProvider() {
        factory = Persistence.createEntityManagerFactory("jpa-rating");
    }

    public EntityManager getNewEntityManager() {
        return factory.createEntityManager();
    }
}
