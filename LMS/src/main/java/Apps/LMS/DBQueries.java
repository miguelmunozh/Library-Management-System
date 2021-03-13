package Apps.LMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class DBQueries {
    /**
     * These two variables are used to perform Sql queries
     */
    PreparedStatement pst;
    ResultSet rs;

    /**
     * these strings represent the table names in the local database
     */
    String books = "books";
    String users = "users";

    /**
     * keeps track of the logged user in the system.
     */
    User loggedUser;

    public DBQueries(String books, String users) {
	this.books = books;
	this.users = users;
    }

    /**
     * useful getters and setters for testing in a test database
     */
    public String getBooks() {
	return books;
    }

    public void setBooks(String books) {
	this.books = books;
    }

    public String getUsers() {
	return users;
    }

    public void setUsers(String users) {
	this.users = users;
    }

    public User getLoggedUser() {
	return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
	this.loggedUser = loggedUser;
    }

    /**
     * populateBooksTable populates a table with information from the database
     * 
     * @param table represents the table to insert the information from the database
     */
    public void populateBooksTable(JTable table) {
	try {
	    pst = DBConnection.getConnection()
		    .prepareStatement("SELECT ISBN,title,author,year,Publisher FROM " + getBooks());
	    rs = pst.executeQuery();

	    table.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * populateUsersTable populates a table with information from the database
     * 
     * @param userstable represents the table to insert the information from the
     *                   database
     */
    public void populateUsersTable(JTable userstable) {
	try {
	    pst = DBConnection.getConnection().prepareStatement("SELECT username, usertype FROM " + getUsers());
	    rs = pst.executeQuery();
	    
	    userstable.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (SQLException e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }

    /**
     * insertUserIntoDB inserts a user into the database
     * 
     * @param username represents the username of a user
     * @param password represents the password provided to an authorized user
     */
    public boolean insertUserIntoDB(String username, String password, String type) {
	try {
	    pst = DBConnection.getConnection()
		    .prepareStatement("INSERT INTO " + getUsers() + "(username,password, usertype) VALUES(?,?,?)");
	    pst.setString(1, username);
	    pst.setString(2, password);
	    pst.setString(3, type);

	    int i = pst.executeUpdate();
	    if (i > 0) {
		return true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * insertBookIntoDB inserts a book into the database
     * 
     * @param isbn    represents the isbn of a book to insert into the database
     * @param title   represents the title of a book to insert into the database
     * @param author  represents the author of a book to insert into the database
     * @param year    represents the year of a book to insert into the database
     * @param publish represents the publish of a book to insert into the database
     */
    public boolean insertBookIntoDB(String isbn, String title, String author, String year, String publish) {
	boolean flag = false;
	try {
	    pst = DBConnection.getConnection().prepareStatement(
		    "INSERT INTO " + getBooks() + "(ISBN,title,author,year,Publisher) VALUES(?,?,?,?,?)");
	    pst.setString(1, isbn);
	    pst.setString(2, title);
	    pst.setString(3, author);
	    pst.setString(4, year);
	    pst.setString(5, publish);

	    int i = pst.executeUpdate();
	    if (i > 0) {
		flag = true;
	    } else {
		flag = false;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return flag;
    }

    /**
     * selectInfoFromBooks selects info from database based on book title and show
     * the result in a table
     * 
     * @param bookTitle represents the title of the book to look for in the database
     * @param table     table where the found information is shown
     */
    public void selectInfoFromBooks(String bookTitle, JTable table) {
	try {
	    pst = DBConnection.getConnection().prepareStatement(
		    "SELECT ISBN,title,author,year,Publisher FROM " + getBooks() + " WHERE title = ?");
	    pst.setString(1, bookTitle);
	    rs = pst.executeQuery();

	    table.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * advancedSearchBooks Performs and advanced search for books
     * 
     * @param title  represents the title of a book
     * @param isbn   represents the isbn of a book
     * @param author represents the author of a book
     * @param year   represents the year of a book
     * @param table  represents the table of a book
     */
    public void advancedSearchBooks(String title, String isbn, String author, String year, JTable table) {

	try {
	    pst = DBConnection.getConnection().prepareStatement("SELECT ISBN,title,author,year,Publisher FROM "
		    + getBooks() + " WHERE title = ? OR ISBN = ? OR author = ? OR year = ?");
	    pst.setString(1, title);
	    pst.setString(2, isbn);
	    pst.setString(3, author);
	    pst.setString(4, year);
	    rs = pst.executeQuery();

	    table.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * userCheck method checks if the user was logged in successfully
     * 
     * @param username name of the user trying to log in to the system
     * @param password password of the user trying to log in to the system
     * @return a boolean value that determines if the user was logged in
     *         successfully
     */
    public boolean userCheck(String username, String password) {
	try {
	    pst = DBConnection.getConnection().prepareStatement(
		    "SELECT username, usertype FROM " + getUsers() + " WHERE username = ? AND password = ?");
	    pst.setString(1, username);
	    pst.setString(2, password);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		loggedUser = new User(username, rs.getString("usertype"));
		return true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * bookCheck checks if a book is in the db
     * 
     * @param title represents title of the book to look for in the database
     * @param isbn  represents the isbn of the book to look for in the database
     * @return boolean value depending on if the book is in the db or not
     */
    public boolean bookCheck(String title, String isbn) {
	try {
	    pst = DBConnection.getConnection().prepareStatement(
		    "SELECT ISBN,title,author,year,Publisher FROM " + getBooks() + " WHERE title = ? OR ISBN = ?");
	    pst.setString(1, title);
	    pst.setString(2, isbn);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		return true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * removeUser method removes an user from the database
     * 
     * @param username represents the username of a user
     */
    public boolean removeUser(String username) {
	try {
	    pst = DBConnection.getConnection().prepareStatement("DELETE FROM " + getUsers() + " WHERE username = ?");
	    pst.setString(1, username);
	    int i = pst.executeUpdate();
	    if (i > 0) {
		return true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * removeBook method removes a book from the database
     * 
     * @param title represents the title of the book for removal
     * @param isbn  represents the ISBN of a book for removal
     */
    public boolean removeBook(String title, String isbn) {
	boolean flag = false;

	try {
	    pst = DBConnection.getConnection()
		    .prepareStatement("DELETE FROM " + getBooks() + " WHERE title = ? OR ISBN = ?");
	    pst.setString(1, title);
	    pst.setString(2, isbn);

	    int i = pst.executeUpdate();
	    if (i > 0) {
		flag = true;
	    } else {
		flag = false;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return flag;
    }
}