package Infrastructure;

import ASL.UserDAO;
import Domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Implementation of the interface
 * UserDAO
 * This class is used to manipulate the table users from
 * the Database shopkonditoreidb
 */
public class UserDAOImpl implements UserDAO {
    List<User> users;

    public UserDAOImpl() {
        users = new ArrayList<>();
        readAllUsers();
    }

    /**
     * 1. Creates a new user in the list users (in memory)
     * 2. Writes the user to the Database
     * @param u user that should be written to the database and stored in memory
     */
    @Override
    public void createNewUser(User u) {
        String sql = "INSERT INTO `users`(`u_id`, `u_username`, `u_password`, `admin`) VALUES (?, ?, ?, ?);";

        try{
            // TODO Check if the user already exists
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, u.getU_id());
            preparedStatement.setString(2, u.getUsername());
            preparedStatement.setString(3, u.getPassword());
            preparedStatement.setBoolean(4, u.isAdmin());
            preparedStatement.execute();

            users.add(u);
        }
        catch(SQLException e)
        {
            System.out.println("Could not create new user.");
        }
        finally {
            Database.closeConnection();
        }
    }

    /**
     * Synchronizes the users in memory with those from
     * the Database
     * @return a list with the users that the DAO holds currently in memory
     */
    @Override
    public List<User> readAllUsers() {
        String sql = "SELECT * FROM users;";
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            boolean isInList;
            while (rs.next())
            {
                isInList = false;
                User userToInsert = new User(rs.getString("u_username"), rs.getString("u_password"), rs.getBoolean("admin"));
                userToInsert.setU_id(rs.getInt("u_id"));

                for(User userInList : users){

                    // insert only if user is not in list
                    if(userToInsert.equals(userInList))
                    {
                        isInList = true;
                    }
                }

                if(! isInList){
                    users.add(userToInsert);
                }

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not read all the users.");
        }
        finally {
            try {
                if(rs != null){
                    rs.close();
                }

                if(stmt != null){
                    stmt.close();
                }

                Database.closeConnection();
            }
            catch(SQLException e){
                System.out.println("Could not close the DB Connection");
            }
        }

        return users;
    }

    /**
     * Deletes the user from the Database and from the list articles
     * @param u_id u_id from user that should be deleted
     */
    @Override
    public void deleteUser(int u_id) {
        String sql = "DELETE FROM `users` WHERE u_id=?";

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, u_id);
            preparedStatement.execute();

            for(User u : users){
                if(u.getU_id() == u_id){
                    users.remove(u);
                    break;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not delete user.");
        }
        finally {
            Database.closeConnection();
        }
    }

    /**
     * Gets a user by its id from the Database and returns
     * it as an user object.
     * @param u_id article identifier which should be searched for in the Database
     * @return user object which matches the provided id
     */
    @Override
    public User getUserByID(int u_id) {
        List<User> users = readAllUsers();

        for(User u : users)
        {
            if(u.getU_id() == u_id)
            {
                return u;
            }
        }

        return null;
    }

}
