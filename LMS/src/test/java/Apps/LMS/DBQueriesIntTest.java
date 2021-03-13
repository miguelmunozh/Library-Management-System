package Apps.LMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBQueriesIntTest {

    // if I do not use mock, then it is an integration test
    DBQueries queries;

    @BeforeEach
    void setUp() throws Exception {

	DBConnection.Connect();
	queries = new DBQueries("bookstest", "userstest");
	// create books and user objects per test
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
	DBConnection.closeConnection();
    }

    @Test
    void testPopulateBooksTable() {
//	assertTrue(queries.populateBooksTable(new JTable()));
    }

    @Test
    void testPopulateUsersTable() {
//	assertTrue(queries.populateUsersTable(new JTable()));
    }

    @Test
    void testInsertUserIntoDB() {
	queries.insertUserIntoDB("user", "password", "normal");
	// cheks if the user is in the db, and creates user obj
	queries.userCheck("user", "password");

	assertEquals("user", queries.loggedUser.getUsername());
	assertEquals("normal", queries.loggedUser.getType());
    }

    @Test
    void testInsertBookIntoDB() {
	assertTrue(queries.insertBookIntoDB("0123456789", "title2", "author2", "1995", "publisher2"));
    }

//    @Test
//    void testSelectInfoFromBooks() {
//	queries.insertBookIntoDB("9999999", "title999", "mm", "1995", "publisher");
//	assertTrue(queries.selectInfoFromBooks("title999", new JTable()));
//    }

    @Test
    void testAdvancedSearchBooks() {
//	assertTrue(queries.insertBookIntoDB("9999999", "mybuk", "mm", "1995", "publisher"));
//	assertTrue(queries.advancedSearchBooks("9999999", "mybuk", "mm", "1995", new JTable()));
//	assertFalse(queries.advancedSearchBooks("fakebook", "fakebook", "fakebook", "fakebook", new JTable()));
    }

    @Test
    void testLoginCheck() {
	queries.insertUserIntoDB("user7", "password", "normal");
	// cheks if the user is in the db, and creates user obj
	assertTrue(queries.userCheck("user7", "password"));
    }

    @Test
    void testRemoveUser() {

	assertTrue(queries.insertUserIntoDB("user9", "password", "normal"));

	assertTrue(queries.removeUser("user9"));
    }

    @Test
    void testRemoveBook() {
	assertTrue(queries.insertBookIntoDB("9999999", "mybuk", "mm", "1995", "publisher"));
	assertTrue(queries.removeBook("mybuk", "9999999"));

	assertFalse(queries.bookCheck("9999999", "mybuk"));
    }

}
