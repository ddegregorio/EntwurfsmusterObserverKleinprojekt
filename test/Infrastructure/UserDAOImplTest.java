package Infrastructure;

import Domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private UserDAOImpl userDAO;
    private User u;


    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl();
        u = findUserThatDoesNotExistInDB();
    }

    @Test
    void createNewUser() {
        if(u != null)
        {
            userDAO.createNewUser(u);

            // Check if the user has been created in
            // the Database by retrieing it from the the Database
            User userCreatedFromDB = userDAO.getUserByID(u.getU_id());

            assertTrue(userCreatedFromDB.getUsername().equals("Testuser"));
            assertTrue(userCreatedFromDB.getPassword().equals("Testpassword"));
            assertTrue(userCreatedFromDB.isAdmin());
            assertTrue(userCreatedFromDB.getU_id()==u.getU_id());

            userDAO.deleteUser(u.getU_id());
        }
    }


    /**
     * Tries to find a user which does not exist
     *
     * @return User that does not exist in the Database
     * null if it could not find a user that does not exist
     * in the Database
     */
    private User findUserThatDoesNotExistInDB() {
        User user;

        // try three times
        for(int i=0; i<3; i++){
             user = new User("Testuser", "Testpassword", true);
             user.setU_id(1000+i);

             // Check if this user exists in the Database
             if(userDAO.getUserByID(user.getU_id()) == null){
                 return user;
             }
        }

        return null;
    }

    @Test
    void readAllUsers() {
        if(u != null){
            List<User> listUsers1 = userDAO.readAllUsers();

            userDAO.createNewUser(u);

            // List that has one user more
            List<User> listUsers2 = userDAO.readAllUsers();

            for(User u1 : listUsers1){

                // the second list must have at least all the articles
                // from the first list
                if(! listUsers2.contains(u1)){
                    assertFalse(true);
                }
            }

            // Plus the one we have inserted before
            assertTrue(listUsers2.contains(u));

            userDAO.deleteUser(u.getU_id());
        }
        else{
            assertFalse(true);
        }

    }

    @Test
    void deleteUser() {
        // Run test only if a user has been
        // found that does not exist in the Database
        if(u != null){
            userDAO.createNewUser(u);
            userDAO.deleteUser(u.getU_id());

            // Check if the article exists in the Database
            assertTrue(userDAO.getUserByID(u.getU_id()) == null);
        }
        else{
            assertFalse(true);
        }
    }

    @Test
    void getUserByID() {
        if(u != null){
            userDAO.createNewUser(u);

            User userFromDB = userDAO.getUserByID(u.getU_id());

            // Check if it was able to retrieve the right user, we have
            // inserted before, from the Database
            assertTrue(userFromDB.getU_id() == u.getU_id());
            assertTrue(userFromDB.getUsername().equals(u.getUsername()));
            assertTrue(userFromDB.getPassword().equals(u.getPassword()));
            assertTrue(userFromDB.isAdmin());

            userDAO.deleteUser(u.getU_id());
        }
        else{
            assertFalse(true);
        }
    }

}