package Apps.LMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    
    User user;
    
    @BeforeEach
    void setUp() throws Exception {
	user = new User("miguel munoz", "password");
    }

    @Test
    @DisplayName("Checks if the constructor builds an object correctly")
    void testUser() {
	assertTrue(user != null);
	assertFalse(user == null);
	assertEquals("miguel munoz", user.getUsername());
	user.setType("normal");
	assertEquals("normal", user.getType());
    }

    @Test
    @DisplayName("Checks if getUsername returns the username correctly")
    void testGetUsername() {
	assertTrue(user.getUsername().equals("miguel munoz"));
    }

    @Test
    @DisplayName("Checks if setUsername sets the username correctly")
    void testSetUsername() {
	user.setUsername("mike");
	assertEquals("mike", user.getUsername());
    }

    @Test
    @DisplayName("Checks if getType returns the type correctly")
    void testGetType() {
	user.setType("admin");
	assertEquals(user.getType(), "admin");
    }

    @Test
    @DisplayName("Checks if setType sets the type correctly")
    void testSetType() {
	user.setType("normal");
	assertEquals("normal", user.getType());
    }

}
