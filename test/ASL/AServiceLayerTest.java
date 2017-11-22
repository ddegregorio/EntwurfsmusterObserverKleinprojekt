package ASL;

import Domain.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AServiceLayerTest {
    AServiceLayer asl;

    @BeforeEach
    void setUp() {
        // TODO create test admin user that is allowed to use the ASL

        asl =  asl.getASLInstance("Sabine", "testitest");
    }

    @Test
    void storeNewArticleInDB() {
        if(asl != null){
            asl.storeNewArticleInDB(5000, "articleTest", 5.8, false);

            Article a = asl.getArticleById(5000);

            assertTrue(a.getA_id()==5000);
            assertTrue(a.getArticleName().equals("articleTest"));
            assertTrue(a.getPrice() == 5.8);
            assertFalse(a.isDiscount());

            asl.deleteArticleInDB(5000, "articleTest", 5.8, false);
        }
        else{
            assertFalse(true);
        }
    }

    @Test
    void deleteArticleInDB() {
        if(asl != null){
            asl.storeNewArticleInDB(6000, "articleTest", 5.8, false);
            asl.deleteArticleInDB(6000, "articleTest", 5.8, false);

            assertTrue(asl.getArticleById(6000) == null);
        }
        else{
            assertFalse(true);
        }

    }

    @Test
    void setDiscountOnArticle() {
        if(asl != null){
            asl.storeNewArticleInDB(7000, "articleTest", 5.8, false);
            asl.setDiscountOnArticle(7000);

            Article a = asl.getArticleById(7000);

            assertTrue(a.isDiscount());

            asl.deleteArticleInDB(7000, "articleTest", 5.8, false);
        }
        else{
            assertFalse(true);
        }
    }

    @Test
    void getArticleById() {
        if(asl != null){
            asl.storeNewArticleInDB(8000, "articleTest", 5.8, false);

            Article a = asl.getArticleById(8000);

            assertTrue(a.getA_id() == 8000);
            assertTrue(a.getArticleName().equals("articleTest"));
            assertTrue(a.getPrice() == 5.8);
            assertTrue(!a.isDiscount());

            asl.deleteArticleInDB(8000, "articleTest", 5.8, false);
        }
    }

}