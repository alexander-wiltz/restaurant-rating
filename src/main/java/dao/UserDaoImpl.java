package dao;

import dao.exceptions.UserNotFoundException;
import dao.interfcaes.IUserDao;
import model.User;
import utils.EntityManagerProvider;
import utils.Password;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;

public class UserDaoImpl implements IUserDao {

    private static UserDaoImpl instance;
    private final EntityManagerProvider entityManagerProvider;

    private static class InstanceHolder {
        private static UserDaoImpl instance = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        if(instance == null) {
            instance = UserDaoImpl.InstanceHolder.instance;
        }
        return instance;
    }

    private UserDaoImpl() {
        entityManagerProvider = EntityManagerProvider.getInstance();
    }

    @Override
    public Collection<User> getAllUser() {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Collection<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.close();

        return users;
    }

    @Override
    public Collection<User> getUserByName(String username) throws UserNotFoundException {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Collection<User> users = em.createNamedQuery("User.findByName", User.class).setParameter("username",username).getResultList();
        em.close();

        if(users.isEmpty()) {
            throw new UserNotFoundException("User: " + username + " not found!");
        }

        return users;
    }

    @Override
    public Collection<User> getUserByMail(String email) throws UserNotFoundException {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        Collection<User> users = em.createNamedQuery("User.findByMail", User.class).setParameter("email",email).getResultList();
        em.close();

        if(users.isEmpty()) {
            throw new UserNotFoundException("User with address " + email + " not found!");
        }

        return users;
    }

    @Override
    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        try {
            final byte[] salt = Password.generateSalt();
            final byte[] pwd = Password.generatePasswordHash(password, salt);

            user.setPasswordSalt(salt);
            user.setPasswordHash(pwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while creating user: " + e.getMessage());
        }

        EntityManager em = entityManagerProvider.getNewEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.flush();
            em.refresh(user);
            em.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return user;
    }

    @Override
    public void deleteUser(String username) {
        EntityManager em = entityManagerProvider.getNewEntityManager();
        User user = em.createNamedQuery("User.findByName", User.class).setParameter("username", username).getSingleResult();

        em.getTransaction().begin();
        try {
            em.remove(user);
            em.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
