package Apps.LMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to manage the connection to the database
 * 
 * @author Miguel Munoz
 *
 */
public class DBConnection {
    static Connection connection;

    // connect and close connections for big apps
    public static void Connect() {
	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "expresarte369");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * getter to return a database connection
     * 
     * @return returns a connection to the database
     */
    public static Connection getConnection() {
	return connection;
    }

    /**
     * this method closes the connection to the database
     */
    public static void closeConnection() {
	try {
	    connection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
