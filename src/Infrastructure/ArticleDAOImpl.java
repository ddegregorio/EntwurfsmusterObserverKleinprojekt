package Infrastructure;

import ASL.ArticleDAO;
import Domain.Article;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Implementation of the interface
 * ArticleDAO
 * This class is used to manipulate the table articles from
 * the Database userarticlemappings
 */
public class ArticleDAOImpl  implements ArticleDAO {
    List<Article> articles;

    public ArticleDAOImpl(){
        articles = new ArrayList<>();
        readAllArticles();
    }


    /**
     * 1. Creates a new article in the list articles (in memory)
     * 2. Writes the article to the Database
     * @param a article that should be written to the database and stored in memory
     */
    @Override
    public void createNewArticle(Article a)
    {
        String sql = "INSERT INTO `articles`(`a_id`, `articlename`, `price`, `discount`) VALUES (?, ?, ?, ?);";

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, a.getA_id());
            preparedStatement.setString(2, a.getArticleName());
            preparedStatement.setDouble(3, a.getPrice());
            preparedStatement.setBoolean(4, a.isDiscount());
            preparedStatement.execute();

            articles.add(a);
        }
        catch(SQLException e)
        {
            System.out.println("Could not create new article.");
        }
        finally {
            Database.closeConnection();
        }
    }

    /**
     * Deletes the article from the Database and from the list articles
     * @param a article that should be deleted
     */
    @Override
    public void deleteArticle(Article a)
    {
        String sql = "DELETE FROM `articles` WHERE a_id=?";

        try{
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, a.getA_id());
            preparedStatement.execute();

            articles.remove(a);
        }
        catch(SQLException e)
        {
            System.out.println("Could not delete article.");
        }
        finally {
            Database.closeConnection();
        }
    }

    /**
     * Updates an article in memory and in the Database
     * @param a article that should be updated
     */
    public void updateArticle(Article a)
    {
        String sql = "UPDATE `articles` " +
                "SET `a_id`=?, `articlename`=?,`price`=?, `discount`=? WHERE a_id=?;";

        try
        {
            Connection conn = Database.getDatabaseConnectionInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, a.getA_id());
            preparedStatement.setString(2, a.getArticleName());
            preparedStatement.setDouble(3, a.getPrice());
            preparedStatement.setBoolean(4, a.isDiscount());
            preparedStatement.setInt(5, a.getA_id());
            preparedStatement.execute();

            // update also in memory
            for(Article article : articles){

                if(article.getA_id() == a.getA_id()){
                    article.setA_id(a.getA_id());
                    article.setDiscount(a.isDiscount());
                    article.setPrice(a.getPrice());
                    article.setArticleName(a.getArticleName());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not update article");
        }
        finally{
            Database.closeConnection();
        }
    }

    /**
     * Synchronizes the articles in memory with those from
     * the Database
     * @return a list with the articles that the DAO holds currently in memory
     */
    public List<Article> readAllArticles()
    {
        String sql = "SELECT * FROM articles;";
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
                Article articleToInsert = new Article(rs.getInt("a_id"), rs.getString("articlename"), rs.getDouble("price"), rs.getBoolean("discount"));

                for(Article a : articles){

                    // insert only if article is not in list
                    if(articleToInsert.equals(a))
                    {
                        isInList = true;
                    }
                }

                if(! isInList){
                    articles.add(articleToInsert);
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not read all the articles.");
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

        return articles;
    }

    /**
     * Gets an article by its id from the Database and returns
     * it as an article object.
     * @param a_id article identifier which should be searched for in the Database
     * @return article object which matches the provided id
     * null if the article with the provided a_id does not exist in the
     * Database.
     */
    @Override
    public Article getArticleById(int a_id) {
        List<Article> articles = readAllArticles();

        for(Article a : articles)
        {
            if(a.getA_id() == a_id)
            {
                return a;
            }
        }

        return null;
    }
}
