package ASL;

import Domain.Article;
import Domain.User;
import Domain.UserArticleMapping;
import Infrastructure.ArticleDAOImpl;
import Infrastructure.UserArticleMappingImpl;
import Infrastructure.UserDAOImpl;
import java.util.List;

/**
 * Represents an application service layer which
 * is used to manipulate the data in
 * the table Articles from the Database shopkonditoreidb
 *
 * The application service layer can only be used by
 * users who have admin rights.
 */
public class AServiceLayer {
    private static ArticleDAO articleDAO;
    private static UserDAO userDAO;
    private static UserArticleMappingDAO uaDAO;

    private static User userUsingSystem;

    private AServiceLayer() {

    }

    /**
     * Returns an instance from this class
     * Only users who have admin rights will
     * get an instance of the
     * ApplicationServiceLayer-object
     *
     * @param username username
     * @param password password
     * @return an instance of the
     * ApplicationServiceLayer-object or
     * null if the provided credentials dont't belong
     * to a user with admin rights.
     */
    public static AServiceLayer getASLInstance(String username, String password){
        articleDAO = new ArticleDAOImpl();
        userDAO = new UserDAOImpl();
        uaDAO = new UserArticleMappingImpl();

        if (checkIfUserIsAdmin(username, password))
        {
            User userWantingToUseSystem = new User(username, password, true);

            // Get the reference of the user who wants to use
            // the ServiceLayer and set him as the user who
            // currently uses the service layer.
            for (User u: userDAO.readAllUsers())
            {
                if (u.equals(userWantingToUseSystem)){
                    userUsingSystem = u;
                }
            }

            loadUserToArticleMappingFromDB();

            return new AServiceLayer();
        }
        else{
            return null;
        }
    }

    /**
     * Check if the user is an admin.
     * @param username provided username
     * @param password provided password
     * @return boolean true if the provided credentials are valid
     */
    private static boolean checkIfUserIsAdmin(String username, String password)
    {
        boolean userIsAdmin = false;

        for(User u : userDAO.readAllUsers())
        {
            // Find the user with the provided username password combination
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
            {
                // Check if the user is an admin
                if(u.isAdmin()){
                    userIsAdmin = true;
                }
            }
        }

        return userIsAdmin;
    }

    /**
     * Loads the UserArticleMappings from the table userarticlemappings
     * and registers the user on those articles they are interested in.
     */
    private static void loadUserToArticleMappingFromDB(){
        User u;
        Article a;

        for (UserArticleMapping ua : uaDAO.readAllUserArticleMappings())
        {
            u = userDAO.getUserByID(ua.getU_id());
            if(u == null){
                System.out.println("Could not find the corresponding user from the Mapping: u_id="+ ua.getU_id());
                continue;
            }

            a = articleDAO.getArticleById(ua.getA_id());
            if(a == null){
                System.out.println("Could not find the corresponding article from the Mapping: a_id=" + ua.getA_id() );
                continue;
            }

            a.register(u);
        }
    }

    /**
     * Stores an article in the Database
     *
     * @param uid uid of the new article
     * @param articlename articlename of the new article
     * @param price price of the new article
     * @param discount whether the article is on sale or not
     */
    public void storeNewArticleInDB(int uid, String articlename, double price, boolean discount){
        Article a = new Article(uid, articlename, price, discount);

        articleDAO.createNewArticle(a);
    }

    /**
     * Deletes an article in the Database
     *
     * @param uid uid of the article that should be deleted
     * @param articlename articlename of the article that should be deleted
     * @param price price of the article that should be deleted
     * @param discount whether the deleted article is on sale or not
     */
    public void deleteArticleInDB(int uid, String articlename, double price, boolean discount)
    {
        Article a = new Article(uid, articlename, price, discount);

        articleDAO.deleteArticle(a);
    }

    /**
     * Prints all the articles from the Database.
     */
    public void printAllArticlesFromDB()
    {
        System.out.println("Nr.\t\t\t\t\t\t\t\tDescription\t\t\t\tPrice\t\tDiscount");
        for(Article a : articleDAO.readAllArticles())
        {
            System.out.println(a);
        }
    }

    /**
     * Sets the article on discount, but only if
     * it is not already set on discount.
     *
     * @param uid the uid of the article which should be set on
     *            discount.
     */
    public void setDiscountOnArticle(int uid)
    {
        Article a = articleDAO.getArticleById(uid);

        Article withDiscount = new Article(a.getA_id(), a.getArticleName(), a.getPrice(), true);

        if(a!=null){
            if(a.isDiscount()){
                System.out.println("Article is already on sale!");
            }else{
                articleDAO.updateArticle(withDiscount);
                System.out.println("Article with Articlenr "+ uid + " has been set on sale.");
            }
        }
        else{
            System.out.println("Can not find article with Nr." +uid);
        }

    }

    /**
     * Returns an article from the Database by its
     * id.
     *
     * @param a_id the id of the article
     *
     * @return article object if the article with the provided id
     * has been found in the Database
     * null if the article has not been found in the Database
     */
    public Article getArticleById(int a_id)
    {
        return articleDAO.getArticleById(a_id);
    }
}
