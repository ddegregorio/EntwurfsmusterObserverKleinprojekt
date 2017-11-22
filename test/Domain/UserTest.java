package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User u;

    @BeforeEach
    void setUp() {
        u = new User("Testuser", "Testpassword", true);
        u.setU_id(1000);
    }


    @Test
    void getU_id() {
        assertEquals(1000, u.getU_id());
    }

    @Test
    void setU_id() {
        u.setU_id(2000);
        assertEquals(2000, u.getU_id());
    }

    @Test
    void getUsername() {
        assertEquals("Testuser", u.getUsername());
    }

    @Test
    void setUsername() {
        u.setUsername("Testuser2");
        assertEquals("Testuser2", u.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("Testpassword", u.getPassword());
    }

    @Test
    void setPassword() {
        u.setPassword("Testpassword2");
        assertEquals("Testpassword2", u.getPassword());
    }

    @Test
    void setAdmin(){
        u.setAdmin(false);
        assertTrue(u.isAdmin() == false);

        u.setAdmin(true);
        assertTrue(u.isAdmin() == true);
    }

    @Test
    void isAdmin(){
        assertTrue(u.isAdmin() == true);
    }
}