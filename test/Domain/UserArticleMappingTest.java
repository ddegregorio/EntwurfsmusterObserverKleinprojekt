package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserArticleMappingTest {
    private UserArticleMapping ua;

    @BeforeEach
    void setUp() {
        ua = new UserArticleMapping(1000, 2000);
    }

    @Test
    void getU_id() {
        assertEquals(1000, ua.getU_id());
    }

    @Test
    void setU_id() {
        ua.setU_id(2000);
        assertEquals(2000, ua.getU_id());
    }

    @Test
    void getA_id() {
        assertEquals(2000, ua.getA_id());
    }

    @Test
    void setA_id() {
        ua.setA_id(3000);
        assertEquals(3000, ua.getA_id());
    }

}