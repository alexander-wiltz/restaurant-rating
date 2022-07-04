package dao.interfcaes;

import dao.exceptions.UserNotFoundException;
import model.User;

import java.util.Collection;

public interface IUserDao {
    public Collection<User> getAllUser();
    public Collection<User> getUserByName(String username) throws UserNotFoundException;
    public Collection<User> getUserByMail(String email) throws UserNotFoundException;
    public User createUser(String username, String email, String password);
    public void deleteUser(String username);
}
