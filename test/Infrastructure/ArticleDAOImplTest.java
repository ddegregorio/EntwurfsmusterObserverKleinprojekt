package Infrastructure;

import Domain.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDAOImplTest {
    private ArticleDAOImpl articleDAO;
    private Article a;

    @BeforeEach
    void setUp() {
        articleDAO = new ArticleDAOImpl();
        a = findArticleThatDoesNotExistInDB();
    }

    @Test
    void createNewArticle() {
        if(a != null)
        {
            articleDAO.createNewArticle(a);

            // Check if the article has been created in
            // the Database by retrieving it from the Database
            Article articleCreated = articleDAO.getArticleById(a.getA_id());

            assertTrue(a.isDiscount() == articleCreated.isDiscount());
            assertTrue(a.getPrice() == articleCreated.getPrice());
            assertTrue(a.getArticleName().equals(articleCreated.getArticleName()));
            assertTrue(a.getA_id() == articleCreated.getA_id());

            articleDAO.deleteArticle(a);
        }
        else{
            assertFalse(true);
        }

    }

    /**
     * Tries to find an article which does not exist in the Database
     *
     * @return Article that does not exist in the Database
     * null if it could not a find an article that does not
     * exist in the Database
     */
    private Article findArticleThatDoesNotExistInDB(){
        Article article;

        // try three times
        for(int i=0; i<3; i++){
            article = new Article(3000+i, "TestArticle", 3.5, true);

            if(articleDAO.getArticleById(article.getA_id()) == null){
                return article;
            }
        }

        return null;
    }

    @Test
    void deleteArticle() {
        // Run test only if an article has been
        // found that does not exist in the Database
        if(a != null){
            articleDAO.createNewArticle(a);
            articleDAO.deleteArticle(a);

            // Check if the article exists in the Database
            assertTrue(articleDAO.getArticleById(a.getA_id()) == null);
        }
        else{
            assertFalse(true);
        }
    }

    @Test
    void updateArticle() {
        Article articleUpdate = findArticleThatDoesNotExistInDB();
        articleUpdate.setArticleName("Testing");
        articleUpdate.setPrice(10.2);
        articleUpdate.setDiscount(false);

        if(articleUpdate != null && a != null){
             articleDAO.createNewArticle(a);

             articleDAO.updateArticle(articleUpdate);

             Article articleFromDB = articleDAO.getArticleById(articleUpdate.getA_id());

             assertTrue(articleFromDB.getA_id() == articleUpdate.getA_id());
             assertTrue(articleFromDB.getArticleName().equals("Testing"));
             assertTrue(articleFromDB.getPrice() == 10.2);
             assertTrue(articleFromDB.isDiscount() == false);

             articleDAO.deleteArticle(articleUpdate);
        }
    }

    @Test
    void readAllArticles() {
        if(a != null){
            List<Article> listArticles1 = articleDAO.readAllArticles();

            articleDAO.createNewArticle(a);

            // List that has one article more
            List<Article> listArticles2 = articleDAO.readAllArticles();

            for(Article a1 : listArticles1){

                // the second list must at least have all the articles
                // from the first list
                if(! listArticles2.contains(a1))
                {
                    assertFalse(true);
                }
            }

            // Plus the one we inserted before
            assertTrue(listArticles2.contains(a));

            articleDAO.deleteArticle(a);
        }
        else{
            assertFalse(true);
        }

    }

    @Test
    void getArticleById() {
        if(a != null){
            articleDAO.createNewArticle(a);

            Article articleFromDB = articleDAO.getArticleById(a.getA_id());

            // Check if it was able to retrieve the right article, we have
            // inserted before, from the Database
            assertTrue(articleFromDB.getA_id() == a.getA_id());
            assertTrue(articleFromDB.getArticleName().equals(a.getArticleName()));
            assertTrue(articleFromDB.getPrice() == a.getPrice());
            assertTrue(articleFromDB.isDiscount() == a.isDiscount());

            articleDAO.deleteArticle(a);
        }
        else{
            assertFalse(true);
        }
    }



}