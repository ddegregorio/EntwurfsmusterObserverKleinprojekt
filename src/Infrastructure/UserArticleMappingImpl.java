package Infrastructure;

import ASL.UserArticleMappingDAO;
import Domain.UserArticleMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Implementation of the interface
 * UserArticleMappingDAO
 * This class is used to manipulate the table userarticlemappings from
 * the Database shopkonditoreidb
 */
public class UserArticleMappingImpl implements UserArticleMappingDAO{
    List<UserArticleMapping> userArticleMappings;

    public UserArticleMappingImpl(){
        userArticleMappings = new ArrayList<>();
        readAllUserArticleMappings();
    }

    /**
     * Creates a new UserArticle mapping in the Database
     * and in memory
     * @param uaMapping mapping between user and article
     */
    @Override
    public void createNewUserArticleMapping(UserArticleMapping uaMapping) {
        String sql = "INSERT INTO `userarticlemappings`(`u_id`, `a_id`) VALUES (?, ?);";

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, uaMapping.getU_id());
            preparedStatement.setInt(2, uaMapping.getA_id());
            preparedStatement.execute();

            userArticleMappings.add(uaMapping);
        }
        catch(SQLException e)
        {
            System.out.println("Could not create new UserArticleMapping.");
        }
        finally {
            Database.closeConnection();
        }
    }

    /**
     * Synchronizes the user article mappings in memory with those from
     * the Database
     * @return a list with the user article mappings objects that the DAO holds currently in memory
     */
    @Override
    public List<UserArticleMapping> readAllUserArticleMappings() {
        String sql = "SELECT * FROM userarticlemappings;";
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
                UserArticleMapping uaToInsert = new UserArticleMapping(rs.getInt("u_id"), rs.getInt("a_id"));

                for(UserArticleMapping ua : userArticleMappings){

                    // insert only if the mapping is not already in the list
                    if(uaToInsert.getA_id() == ua.getA_id()
                            && uaToInsert.getU_id() == ua.getU_id()){

                        isInList = true;
                    }
                }

                if(! isInList){
                    userArticleMappings.add(uaToInsert);
                }

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not read all the userArticleMappings.");
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

        return userArticleMappings;
    }

    /**
     * Deletes a new UserArticle mapping in the Database
     * and in memory
     * @param u_id u_id from the table users that takes part in the mapping
     * @param a_id a_id from the table articles that takes part in the mapping
     */
    @Override
    public void deleteUserArticleMapping(int u_id, int a_id)
    {
        String sql = "DELETE FROM `userarticlemappings` WHERE u_id=? AND a_id=?";

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, u_id);
            preparedStatement.setInt(2,a_id);
            preparedStatement.execute();

            // remove also from memory
            for(UserArticleMapping ua : userArticleMappings){
                if(ua.getU_id() == u_id && ua.getA_id()== a_id){
                    userArticleMappings.remove(ua);
                    break;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not delete UserArticleMapping.");
        }
        finally {
            Database.closeConnection();
        }
    }
}
