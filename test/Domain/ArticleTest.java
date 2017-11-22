package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    private Article a;

    @BeforeEach
    void setUp() {
        a = new Article(1000, "Sachertorte", 3.4, true);
    }

    @Test
    void getA_id() {
        assertEquals(1000, a.getA_id());
    }

    @Test
    void getArticleName() {
        assertEquals("Sachertorte", a.getArticleName());
    }

    @Test
    void setArticleName() {
        a.setArticleName("");
        a.setArticleName("Sacher");
        assertEquals("Sacher", "Sacher");
    }

    @Test
    void getPrice() {
        assertEquals(3.4, a.getPrice());
    }

    @Test
    void setPrice() {
        a.setPrice(3.2);
        assertEquals(3.2, a.getPrice());
    }

    @Test
    void isDiscount() {
        assertTrue(a.isDiscount());
    }

    @Test
    void setDiscount() {
        a.setDiscount(false);
        assertTrue(!a.isDiscount());

        a.setDiscount(true);
        assertTrue(a.isDiscount());
    }
}