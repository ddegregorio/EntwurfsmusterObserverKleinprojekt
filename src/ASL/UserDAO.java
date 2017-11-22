package ASL;

import Domain.User;

import java.util.List;

/**
 * Represents an interface for the Data access object
 * which is used to retrieve user objects from
 * the Database
 */
public interface UserDAO {

    void createNewUser(User u);

    List<User> readAllUsers();

    void deleteUser(int id);

    User getUserByID(int u_id);
}
