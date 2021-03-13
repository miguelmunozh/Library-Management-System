package Apps.LMS;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBConnectionTest extends DBConnection{

    @BeforeEach
    void setUp() throws Exception {
	Connect();
    }

    @Test
    void testConnect() {
	try {
	    assertTrue(getConnection().isValid(2000));
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Test
    void testGetConnection() {
	assertTrue(getConnection() != null);
    }

    @Test
    void testCloseConnection() {

	DBConnection.closeConnection();
	try {
	    assertTrue(getConnection().isClosed());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
