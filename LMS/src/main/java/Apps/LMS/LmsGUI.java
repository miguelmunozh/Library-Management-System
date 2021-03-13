package Apps.LMS;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class LmsGUI {

    private JFrame searchFrame;
    private JFrame logInFrame;
    private JFrame aboutFrame;
    private JFrame userFrame;
    private JFrame addUserFrame;
    private JFrame delUserFrame;
    private JFrame addBookFrame;
    private JFrame delBookFrame;
    private JFrame advancedSearchFrame;

    private JTable booksTable;
    private JTable userstable;

    private JScrollPane scrollPane;

    DBQueries queries = new DBQueries("books", "users");

    /**
     * Create the GUI application.
     */
    public LmsGUI() {
	DBConnection.Connect();
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	// create base windows and add components to them
	createSearchWindowAndComponents();
	createLogInWindowAndComponents();
	createAboutWindowAndComponents();
	createAdvancedSearchWindowAndComponents();
	createUserWindowAndComponents();
	createAddUserWindowAndComponents();
	createRemUserWindowAndComponents();
	createRemBookWindowAndComponents();
	createAddBookWindowAndComponents();
    }

    /**
     * Creates a base window
     * 
     * @param seen used to set if the window is visible or not
     * @return
     */
    public JFrame createBaseFrame(boolean seen) {
	JFrame frame = new JFrame("Library Management System");
	frame.getContentPane().setBackground(Color.WHITE);
	frame.setBounds(100, 100, 1150, 678);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(seen);

	JMenuBar menuBar = new JMenuBar();
	menuBar.setBackground(new Color(179, 217, 255));
	frame.setJMenuBar(menuBar);

	JButton loginButton = new JButton("Log In");
	loginButton.setBackground(new Color(179, 217, 255));
	loginButton.setFocusPainted(false);
	loginButton.setBorderPainted(false);
	loginButton.setToolTipText("Log In");
	menuBar.add(loginButton);
	loginButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		logInFrame.setVisible(true);
		searchFrame.setVisible(false);
		aboutFrame.setVisible(false);
		userFrame.setVisible(false);
		delBookFrame.setVisible(false);
		delUserFrame.setVisible(false);
		advancedSearchFrame.setVisible(false);
	    }
	});

	JButton aboutButton = new JButton("About");
	aboutButton.setBackground(new Color(179, 217, 255));
	aboutButton.setFocusPainted(false);
	aboutButton.setBorderPainted(false);
	aboutButton.setToolTipText("About Section");
	menuBar.add(aboutButton);
	aboutButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		aboutFrame.setVisible(true);
		searchFrame.setVisible(false);
		logInFrame.setVisible(false);
		userFrame.setVisible(false);
		delBookFrame.setVisible(false);
		delUserFrame.setVisible(false);
		advancedSearchFrame.setVisible(false);
	    }
	});

	JButton searchButton = new JButton("Back to Search");
	searchButton.setBackground(new Color(179, 217, 255));
	searchButton.setFocusPainted(false);
	searchButton.setBorderPainted(false);
	searchButton.setToolTipText("Go back to search section");

	menuBar.add(searchButton);
	searchButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		searchFrame.setVisible(true);
		aboutFrame.setVisible(false);
		logInFrame.setVisible(false);
		userFrame.setVisible(false);
		delBookFrame.setVisible(false);
		delUserFrame.setVisible(false);
		advancedSearchFrame.setVisible(false);
	    }
	});
	return frame;
    }

    /**
     * creates the search window and its components
     */
    public void createSearchWindowAndComponents() {

	searchFrame = createBaseFrame(true);

	displayAppTitle(searchFrame);

	// search panel
	JPanel panel = new JPanel();
	panel.setBackground(Color.WHITE);
	panel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
		TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	panel.setBounds(32, 99, 1073, 77);
	searchFrame.getContentPane().add(panel);
	panel.setLayout(null);

	JLabel lblNewLabel_1 = new JLabel("Book Title");
	lblNewLabel_1.setBounds(20, 29, 85, 23);
	panel.add(lblNewLabel_1);

	JTextField textField = new JTextField();
	textField.setBounds(105, 28, 388, 26);
	panel.add(textField);
	textField.setColumns(10);

	JButton searchButton = new JButton("Search");
	searchButton.setPreferredSize(new Dimension(100, 100));
	searchButton.setBounds(500, 29, 90, 23);
	searchButton.setBackground(new Color(179, 217, 255));
	searchButton.setFocusPainted(false);
	searchButton.setBorderPainted(false);
	searchButton.setToolTipText("Search");
	panel.add(searchButton);
	searchButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String bookTitle = textField.getText();

		if (bookTitle.isBlank()) {
		    // handle no update
		    queries.populateBooksTable(booksTable);
		} else {
		    queries.selectInfoFromBooks(bookTitle, booksTable);
		}
	    }
	});

	JButton resetButton = new JButton("Reset");
	resetButton.setPreferredSize(new Dimension(100, 100));
	resetButton.setBounds(600, 29, 70, 23);
	resetButton.setBackground(new Color(179, 217, 255));
	resetButton.setFocusPainted(false);
	resetButton.setBorderPainted(false);
	resetButton.setToolTipText("Reset Search");
	panel.add(resetButton);
	resetButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		queries.populateBooksTable(booksTable);
	    }
	});

	JButton advancedSeaButton = new JButton("Adv. Search");
	advancedSeaButton.setPreferredSize(new Dimension(100, 100));
	advancedSeaButton.setBounds(680, 29, 115, 23);
	advancedSeaButton.setBackground(new Color(179, 217, 255));
	advancedSeaButton.setFocusPainted(false);
	advancedSeaButton.setBorderPainted(false);
	advancedSeaButton.setToolTipText("Advanced Search");
	panel.add(advancedSeaButton);
	advancedSeaButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		advancedSearchFrame.setVisible(true);
		userFrame.setVisible(false);
		searchFrame.setVisible(false);
		delBookFrame.setVisible(false);
		delUserFrame.setVisible(false);
	    }
	});

	scrollPane = new JScrollPane();
	scrollPane.setBounds(34, 198, 1069, 413);
	searchFrame.getContentPane().add(scrollPane);

	// table to display the info from the db
	booksTable = new JTable();
	scrollPane.setViewportView(booksTable);

	// after initializing the table and its components populate the jtable
	queries.populateBooksTable(booksTable);
	searchFrame.setVisible(true);
    }

    /**
     * creates the log in window and its components
     */
    public void createLogInWindowAndComponents() {
	logInFrame = createBaseFrame(false);
	pinkSquareDesign(logInFrame);

	// log in frame
	displayAppTitle(logInFrame);

	// panel to contain the form
	JPanel logiJPanel = new JPanel();
	logiJPanel.setBackground(Color.WHITE);
	logiJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Log In",
		TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	logiJPanel.setBounds(450, 99, 650, 500);
	logInFrame.getContentPane().add(logiJPanel);
	logiJPanel.setLayout(null);

	JLabel usernameJLabel = new JLabel("Username :");
	usernameJLabel.setBounds(100, 100, 300, 23);
	logiJPanel.add(usernameJLabel);

	JTextField usertextField = new JTextField();
	usertextField.setBounds(200, 100, 300, 26);
	logiJPanel.add(usertextField);
	usertextField.setColumns(10);

	JLabel passJLabel = new JLabel("Password :");
	passJLabel.setBounds(100, 150, 300, 23);
	logiJPanel.add(passJLabel);

	JTextField passtextField = new JTextField();
	passtextField.setBounds(200, 150, 300, 26);
	logiJPanel.add(passtextField);
	passtextField.setColumns(10);
	// log in button
	JButton loginButton = new JButton("Log In");
	loginButton.setPreferredSize(new Dimension(100, 100));
	loginButton.setBounds(275, 200, 100, 25);
	loginButton.setBackground(new Color(179, 217, 255));
	loginButton.setFocusPainted(false);
	loginButton.setBorderPainted(false);
	loginButton.setToolTipText("Log in");
	logiJPanel.add(loginButton);
	loginButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String username = usertextField.getText();
		String password = passtextField.getText();

		if (queries.userCheck(username, password)) {
		    userFrame.setVisible(true);
		    logInFrame.setVisible(false);

		    // populate users table
		    queries.populateUsersTable(userstable);

		    // and display personalized message in User window
		    JLabel userLabel = new JLabel("Welcome " + queries.loggedUser.getUsername());
		    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		    userLabel.setFont(new Font("MS PGothic", Font.PLAIN, 30));
		    userLabel.setBounds(200, 30, 728, 30);
		    userFrame.getContentPane().add(userLabel);

		    usertextField.setText("");
		    passtextField.setText("");
		} else {
		    // handle no update
		    usertextField.setText("");
		    passtextField.setText("");
		    JOptionPane.showMessageDialog(logiJPanel, "Incorrect or empty credentials");
		}
	    }
	});
    }

    /**
     * creates the about window and its components
     */
    public void createAboutWindowAndComponents() {
	aboutFrame = createBaseFrame(false);
	pinkSquareDesign(aboutFrame);
	displayAppTitle(aboutFrame);

	// panel to contain the form
	JPanel panel = new JPanel();
	panel.setBackground(Color.WHITE);
	panel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		"About This Software", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	panel.setBounds(450, 99, 650, 500);
	aboutFrame.getContentPane().add(panel);
	panel.setLayout(null);

	JTextArea textArea = new JTextArea();
	textArea.setFont(new Font("Serif", Font.ITALIC, 17));
	textArea.setLineWrap(true);
	textArea.setEditable(false);
	textArea.setText(
		"This Graphical User Interface Library Management System application is written in java and MySql by Miguel Munoz. "
			+ "The porpouse is to simulate a Library Management System that we would find if we go to a library "
			+ "in real life. Library Management System application utilizes a local database created with MySql, "
			+ "the database was populated with a csv file available at \n"
			+ "'https://data.world/divyanshj/users-books-dataset/workspace/file?filename=BX-Books.csv' \nand "
			+ "with help of a third party library called `rs2xml.jar` to populate a table in the application with"
			+ " the database information.\n\n You can find more about me at\n https://miguelmunozh.github.io/");
	textArea.setBounds(140, 50, 350, 400);
	panel.add(textArea);

    }

    /**
     * creates the user window and its components
     */
    public void createUserWindowAndComponents() {
	userFrame = createBaseFrame(false);

	JPanel userJPanel = new JPanel();
	userJPanel.setBackground(Color.WHITE);
	userJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		"User Panel", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	userJPanel.setBounds(32, 99, 1073, 100);
	userFrame.getContentPane().add(userJPanel);
	userJPanel.setLayout(null);

	createPanelMenu(userJPanel);

	// users view table
	scrollPane = new JScrollPane();
	scrollPane.setBounds(34, 200, 1069, 300);
	userFrame.getContentPane().add(scrollPane);

	// table to display the info from the db
	userstable = new JTable();
	scrollPane.setViewportView(userstable);
    }

    /**
     * creates the `add user` window and its components
     */
    public void createAddUserWindowAndComponents() {
	addUserFrame = createBaseFrame(false);

	// add User frame
	// panel for the form
	JPanel addUserJPanel = new JPanel();
	addUserJPanel.setBackground(Color.WHITE);
	addUserJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add User",
		TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	addUserJPanel.setBounds(32, 99, 1073, 400);
	addUserFrame.getContentPane().add(addUserJPanel);
	addUserJPanel.setLayout(null);

	createPanelMenu(addUserJPanel);

	JLabel addUsernameJLabel = new JLabel("Name");
	addUsernameJLabel.setBounds(270, 100, 300, 23);
	addUserJPanel.add(addUsernameJLabel);

	JTextField usernametextField = new JTextField();
	usernametextField.setBounds(350, 100, 400, 26);
	addUserJPanel.add(usernametextField);
	usernametextField.setColumns(10);

	JLabel addPasswordLabel = new JLabel("Password");
	addPasswordLabel.setBounds(270, 140, 300, 23);
	addUserJPanel.add(addPasswordLabel);

	JTextField passwordtextField = new JTextField();
	passwordtextField.setBounds(350, 140, 400, 26);
	addUserJPanel.add(passwordtextField);
	passwordtextField.setColumns(10);

	JLabel addTypeLabel = new JLabel("User type");
	addTypeLabel.setBounds(270, 180, 300, 23);
	addUserJPanel.add(addTypeLabel);

	JTextField typetextField = new JTextField();
	typetextField.setBounds(350, 180, 400, 26);
	addUserJPanel.add(typetextField);
	typetextField.setColumns(10);

	JButton addUserButton = new JButton("Add");
	addUserButton.setPreferredSize(new Dimension(100, 100));
	addUserButton.setBounds(485, 240, 100, 25);
	addUserButton.setBackground(new Color(179, 217, 255));
	addUserButton.setFocusPainted(false);
	addUserButton.setBorderPainted(false);
	addUserButton.setToolTipText("Add User");
	addUserJPanel.add(addUserButton);
	addUserButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (queries.loggedUser.getType().equalsIgnoreCase("admin")) {
		    // check if the type is admin first to add something to the db, if not pop up a
		    // joption message
		    if (usernametextField.getText().equals("") || passwordtextField.getText().equals("")
			    || typetextField.getText().equals("")) {
			JOptionPane.showMessageDialog(addUserJPanel, "You need to fill all the fields!");
		    } else {
			// encapsulate in method
			queries.insertUserIntoDB(usernametextField.getText(), passwordtextField.getText(),
				typetextField.getText());
			queries.populateUsersTable(userstable);

			// clear fields
			usernametextField.setText("");
			passwordtextField.setText("");
			typetextField.setText("");
			// display success message
			JOptionPane.showMessageDialog(addUserJPanel, "User successfuly added into the database!");
		    }
		} else {
		    usernametextField.setText("");
		    passwordtextField.setText("");
		    JOptionPane.showMessageDialog(addUserJPanel,
			    "Only an administrator can add users to the library database!");
		}
		// make right window visible
		addUserFrame.setVisible(false);
		userFrame.setVisible(true);
	    }
	});
    }

    /**
     * creates the `add book` window and its components
     */
    public void createAddBookWindowAndComponents() {
	addBookFrame = createBaseFrame(false);
	// add book frame
	JPanel addBookJPanel = new JPanel();
	addBookJPanel.setBackground(Color.WHITE);
	addBookJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Book",
		TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	addBookJPanel.setBounds(32, 99, 1073, 500);
	addBookFrame.getContentPane().add(addBookJPanel);
	addBookJPanel.setLayout(null);

	createPanelMenu(addBookJPanel);

	JLabel addBookTitleJLabel = new JLabel("Title");
	addBookTitleJLabel.setBounds(270, 100, 300, 23);
	addBookJPanel.add(addBookTitleJLabel);

	JTextField bookTitletextField = new JTextField();
	bookTitletextField.setBounds(350, 100, 400, 26);
	addBookJPanel.add(bookTitletextField);
	bookTitletextField.setColumns(10);

	JLabel addISBNLabel = new JLabel("ISBN");
	addISBNLabel.setBounds(270, 140, 300, 23);
	addBookJPanel.add(addISBNLabel);

	JTextField ISBNtextField = new JTextField();
	ISBNtextField.setBounds(350, 140, 400, 26);
	addBookJPanel.add(ISBNtextField);
	ISBNtextField.setColumns(10);

	JLabel addAuthorJLabel = new JLabel("Author");
	addAuthorJLabel.setBounds(270, 180, 300, 23);
	addBookJPanel.add(addAuthorJLabel);

	JTextField authortextField = new JTextField();
	authortextField.setBounds(350, 180, 400, 26);
	addBookJPanel.add(authortextField);
	authortextField.setColumns(10);

	JLabel addYearLabel = new JLabel("Year");
	addYearLabel.setBounds(270, 220, 300, 23);
	addBookJPanel.add(addYearLabel);

	JTextField yeartextField = new JTextField();
	yeartextField.setBounds(350, 220, 400, 26);
	addBookJPanel.add(yeartextField);
	yeartextField.setColumns(10);

	JLabel addPublisherjLabel = new JLabel("Publisher");
	addPublisherjLabel.setBounds(270, 260, 300, 23);
	addBookJPanel.add(addPublisherjLabel);

	JTextField publishertextField = new JTextField();
	publishertextField.setBounds(350, 260, 400, 26);
	addBookJPanel.add(publishertextField);
	publishertextField.setColumns(10);

	JButton addBookButton = new JButton("Add");
	addBookButton.setPreferredSize(new Dimension(100, 100));
	addBookButton.setBounds(485, 300, 100, 25);
	addBookButton.setBackground(new Color(179, 217, 255));
	addBookButton.setFocusPainted(false);
	addBookButton.setBorderPainted(false);
	addBookButton.setToolTipText("Add Book");
	addBookJPanel.add(addBookButton);
	addBookButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (queries.loggedUser.getType().equalsIgnoreCase("admin")) {

		    queries.insertBookIntoDB(ISBNtextField.getText(), bookTitletextField.getText(),
			    authortextField.getText(), yeartextField.getText(), publishertextField.getText());
		    // to update books table
		    queries.populateBooksTable(booksTable);
		    // clear text fields, get into a method?
		    ISBNtextField.setText("");
		    bookTitletextField.setText("");
		    authortextField.setText("");
		    yeartextField.setText("");
		    publishertextField.setText("");
		    JOptionPane.showMessageDialog(addBookJPanel, "Book added successfuly into the database!");

		} else {
		    // clear text fields
		    ISBNtextField.setText("");
		    bookTitletextField.setText("");
		    authortextField.setText("");
		    yeartextField.setText("");
		    publishertextField.setText("");
		    JOptionPane.showMessageDialog(addBookJPanel,
			    "Only an administrator can add books to the library database!");
		}

		userFrame.setVisible(true);
		addBookFrame.setVisible(false);
	    }
	});
    }

    /**
     * creates the `advanced search` window and its components
     */
    public void createAdvancedSearchWindowAndComponents() {
	advancedSearchFrame = createBaseFrame(false);

	JPanel advancedSearchJPanel = new JPanel();
	advancedSearchJPanel.setBackground(Color.WHITE);
	advancedSearchJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		"Advanced Search", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	advancedSearchJPanel.setBounds(32, 99, 1073, 500);
	advancedSearchFrame.getContentPane().add(advancedSearchJPanel);
	advancedSearchJPanel.setLayout(null);

	JLabel bookTitleJLabel = new JLabel("Title");
	bookTitleJLabel.setBounds(270, 100, 300, 23);
	advancedSearchJPanel.add(bookTitleJLabel);

	JTextField bookTitleTextField = new JTextField();
	bookTitleTextField.setBounds(350, 100, 400, 26);
	advancedSearchJPanel.add(bookTitleTextField);
	bookTitleTextField.setColumns(10);

	JLabel ISBNLabel = new JLabel("ISBN");
	ISBNLabel.setBounds(270, 140, 300, 23);
	advancedSearchJPanel.add(ISBNLabel);

	JTextField ISBNTextField = new JTextField();
	ISBNTextField.setBounds(350, 140, 400, 26);
	advancedSearchJPanel.add(ISBNTextField);
	ISBNTextField.setColumns(10);

	JLabel authorJLabel = new JLabel("Author");
	authorJLabel.setBounds(270, 180, 300, 23);
	advancedSearchJPanel.add(authorJLabel);

	JTextField authorTextField = new JTextField();
	authorTextField.setBounds(350, 180, 400, 26);
	advancedSearchJPanel.add(authorTextField);
	authorTextField.setColumns(10);

	JLabel yearLabel = new JLabel("Year");
	yearLabel.setBounds(270, 220, 300, 23);
	advancedSearchJPanel.add(yearLabel);

	JTextField yearTextField = new JTextField();
	yearTextField.setBounds(350, 220, 400, 26);
	advancedSearchJPanel.add(yearTextField);
	yearTextField.setColumns(10);

	JButton advancedSearchButton = new JButton("Search");
	advancedSearchButton.setPreferredSize(new Dimension(100, 100));
	advancedSearchButton.setBounds(485, 300, 100, 25);
	advancedSearchButton.setBackground(new Color(179, 217, 255));
	advancedSearchButton.setFocusPainted(false);
	advancedSearchButton.setBorderPainted(false);
	advancedSearchButton.setToolTipText("Search");
	advancedSearchJPanel.add(advancedSearchButton);
	advancedSearchButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (bookTitleTextField.getText().equals("") && ISBNTextField.getText().equals("")
			&& authorTextField.getText().equals("") && yearTextField.getText().equals("")) {
		    // do nothing
		    searchFrame.setVisible(true);
		    delBookFrame.setVisible(false);
		    delUserFrame.setVisible(false);
		    advancedSearchFrame.setVisible(false);
		} else {
		    // perform query
		    queries.advancedSearchBooks(bookTitleTextField.getText(), ISBNTextField.getText(),
			    authorTextField.getText(), yearTextField.getText(), booksTable);
		    searchFrame.setVisible(true);
		    delBookFrame.setVisible(false);
		    delUserFrame.setVisible(false);
		    advancedSearchFrame.setVisible(false);

		    // clear text fields
		    bookTitleTextField.setText("");
		    ISBNTextField.setText("");
		    authorTextField.setText("");
		    yearTextField.setText("");
		}
	    }
	});
    }

    /**
     * creates the `remove user` window and its components
     */
    public void createRemUserWindowAndComponents() {
	delUserFrame = createBaseFrame(false);

	JPanel removeuserJPanel = new JPanel();
	removeuserJPanel.setBackground(Color.WHITE);
	removeuserJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		"Remove User", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	removeuserJPanel.setBounds(32, 99, 1073, 500);
	delUserFrame.getContentPane().add(removeuserJPanel);
	removeuserJPanel.setLayout(null);

	createPanelMenu(removeuserJPanel);

	JLabel usernameJLabel = new JLabel("Username");
	usernameJLabel.setBounds(270, 100, 300, 23);
	removeuserJPanel.add(usernameJLabel);

	JTextField remUserTextField = new JTextField();
	remUserTextField.setBounds(350, 100, 400, 26);
	removeuserJPanel.add(remUserTextField);
	remUserTextField.setColumns(10);

	JButton removeUserButton = new JButton("Rem User");
	removeUserButton.setPreferredSize(new Dimension(100, 100));
	removeUserButton.setBounds(485, 300, 100, 25);
	removeUserButton.setBackground(new Color(179, 217, 255));
	removeUserButton.setFocusPainted(false);
	removeUserButton.setBorderPainted(false);
	removeUserButton.setToolTipText("Remove User");
	removeuserJPanel.add(removeUserButton);
	removeUserButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (queries.loggedUser.getType().equalsIgnoreCase("admin")) {
		    if (remUserTextField.getText().equals("")) {
			// do nothing
			JOptionPane.showMessageDialog(removeuserJPanel, "You need to fill the field");
		    } else {
			// perform query

			// check if the user exists first
			// remove the user and then re-populate the table
			queries.removeUser(remUserTextField.getText());
			queries.populateUsersTable(userstable);

			userFrame.setVisible(true);
			advancedSearchFrame.setVisible(false);
			searchFrame.setVisible(false);
			addUserFrame.setVisible(false);
			delUserFrame.setVisible(false);
			addBookFrame.setVisible(false);
			delBookFrame.setVisible(false);

			// clear text fields
			remUserTextField.setText("");
			JOptionPane.showMessageDialog(removeuserJPanel, "User was removed successfuly!");
		    }
		} else {
		    JOptionPane.showMessageDialog(removeuserJPanel,
			    "Only an administrator can remove users from the library database!");
		    userFrame.setVisible(true);
		    delUserFrame.setVisible(false);
		}
	    }
	});
    }

    /**
     * creates the `remove book` window and its components
     */
    public void createRemBookWindowAndComponents() {
	delBookFrame = createBaseFrame(false);

	JPanel removeBookJPanel = new JPanel();
	removeBookJPanel.setBackground(Color.WHITE);
	removeBookJPanel.setBorder(new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		"Remove Book", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
	removeBookJPanel.setBounds(32, 99, 1073, 500);
	delBookFrame.getContentPane().add(removeBookJPanel);
	removeBookJPanel.setLayout(null);

	createPanelMenu(removeBookJPanel);

	JLabel bookTitleJLabel = new JLabel("Book title");
	bookTitleJLabel.setBounds(270, 100, 300, 23);
	removeBookJPanel.add(bookTitleJLabel);

	JTextField bookTitleTextField = new JTextField();
	bookTitleTextField.setBounds(350, 100, 400, 26);
	removeBookJPanel.add(bookTitleTextField);
	bookTitleTextField.setColumns(10);

	JLabel bookIsbnJLabel = new JLabel("Book ISBN");
	bookIsbnJLabel.setBounds(270, 200, 300, 23);
	removeBookJPanel.add(bookIsbnJLabel);

	JTextField bookIsbnTextField = new JTextField();
	bookIsbnTextField.setBounds(350, 200, 400, 26);
	removeBookJPanel.add(bookIsbnTextField);
	bookIsbnTextField.setColumns(10);

	JButton removeBookButton = new JButton("Rem Book");
	removeBookButton.setPreferredSize(new Dimension(100, 100));
	removeBookButton.setBounds(485, 300, 100, 25);
	removeBookButton.setBackground(new Color(179, 217, 255));
	removeBookButton.setFocusPainted(false);
	removeBookButton.setBorderPainted(false);
	removeBookButton.setToolTipText("Remove Book");
	removeBookJPanel.add(removeBookButton);
	removeBookButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (queries.loggedUser.getType().equalsIgnoreCase("admin")) {
		    if (bookIsbnTextField.getText().equals("") && bookTitleTextField.getText().equals("")) {
			// do nothing
			JOptionPane.showMessageDialog(removeBookJPanel, "You need to fill at least one of the fields");
		    } else {
			// perform query

			// check if the book exists first
			// remove the user and then re-populate the table
			if (!queries.removeBook(bookTitleTextField.getText(), bookIsbnTextField.getText())) {
			    JOptionPane.showMessageDialog(removeBookJPanel,
				    "The book you entered doesn't exist, try again!");
			} else {
			    queries.populateBooksTable(booksTable);

			    userFrame.setVisible(true);
			    advancedSearchFrame.setVisible(false);
			    searchFrame.setVisible(false);
			    addUserFrame.setVisible(false);
			    delUserFrame.setVisible(false);
			    addBookFrame.setVisible(false);
			    delBookFrame.setVisible(false);

			    // clear text fields
			    bookIsbnTextField.setText("");
			    bookTitleTextField.setText("");
			    JOptionPane.showMessageDialog(removeBookJPanel, "Book was removed successfuly!");
			}
		    }
		} else {
		    JOptionPane.showMessageDialog(removeBookJPanel,
			    "Only an administrator can remove books from the library database!");
		    userFrame.setVisible(true);
		    delBookFrame.setVisible(false);
		}
	    }
	});
    }

    /**
     * creates a JPanel with button options for logged users
     * 
     * @param panel used to hold buttons for logged users.
     */
    public void createPanelMenu(JPanel panel) {
	JButton logoutButton = new JButton("Log out");
	logoutButton.setPreferredSize(new Dimension(100, 100));
	logoutButton.setBounds(250, 29, 90, 23);
	logoutButton.setBackground(new Color(179, 217, 255));
	logoutButton.setFocusPainted(false);
	logoutButton.setBorderPainted(false);
	logoutButton.setToolTipText("Log out");
	panel.add(logoutButton);
	logoutButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		userFrame.setVisible(false);
		searchFrame.setVisible(true);
		addUserFrame.setVisible(false);
		addBookFrame.setVisible(false);

	    }
	});

	JButton viewUsersButton = new JButton("Users");
	viewUsersButton.setPreferredSize(new Dimension(100, 100));
	viewUsersButton.setBounds(350, 29, 90, 23);
	viewUsersButton.setBackground(new Color(179, 217, 255));
	viewUsersButton.setFocusPainted(false);
	viewUsersButton.setBorderPainted(false);
	viewUsersButton.setToolTipText("Users Window View");
	panel.add(viewUsersButton);
	viewUsersButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		userFrame.setVisible(true);
		addUserFrame.setVisible(false);
		addBookFrame.setVisible(false);
	    }
	});

	JButton addUserButton = new JButton("Add User");
	addUserButton.setPreferredSize(new Dimension(100, 100));
	addUserButton.setBounds(450, 29, 90, 23);
	addUserButton.setBackground(new Color(179, 217, 255));
	addUserButton.setFocusPainted(false);
	addUserButton.setBorderPainted(false);
	addUserButton.setToolTipText("Add User");
	panel.add(addUserButton);
	addUserButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		addUserFrame.setVisible(true);
		userFrame.setVisible(false);
		addBookFrame.setVisible(false);
	    }
	});

	JButton delUserButton = new JButton("Rem. User");
	delUserButton.setPreferredSize(new Dimension(100, 100));
	delUserButton.setBounds(550, 29, 90, 23);
	delUserButton.setBackground(new Color(179, 217, 255));
	delUserButton.setFocusPainted(false);
	delUserButton.setBorderPainted(false);
	delUserButton.setToolTipText("Remove User");
	panel.add(delUserButton);
	delUserButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		delUserFrame.setVisible(true);
		addUserFrame.setVisible(false);
		userFrame.setVisible(false);
		addBookFrame.setVisible(false);
	    }
	});

	JButton addBookButton = new JButton("Add Book");
	addBookButton.setPreferredSize(new Dimension(100, 100));
	addBookButton.setBounds(650, 29, 90, 23);
	addBookButton.setBackground(new Color(179, 217, 255));
	addBookButton.setFocusPainted(false);
	addBookButton.setBorderPainted(false);
	addBookButton.setToolTipText("Add Book");
	panel.add(addBookButton);
	addBookButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		addBookFrame.setVisible(true);
		userFrame.setVisible(false);
		addUserFrame.setVisible(false);
		delBookFrame.setVisible(false);
		delUserFrame.setVisible(false);
	    }
	});

	JButton delBookButton = new JButton("Rem. Book");
	delBookButton.setPreferredSize(new Dimension(100, 100));
	delBookButton.setBounds(750, 29, 90, 23);
	delBookButton.setBackground(new Color(179, 217, 255));
	delBookButton.setFocusPainted(false);
	delBookButton.setBorderPainted(false);
	delBookButton.setToolTipText("Remove Book");
	panel.add(delBookButton);
	delBookButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		delBookFrame.setVisible(true);
		delUserFrame.setVisible(false);
		addUserFrame.setVisible(false);
		userFrame.setVisible(false);
		addBookFrame.setVisible(false);
	    }
	});
    }

    /**
     * creates a pink JPanel to enhance the design
     * 
     * @param frame is the frame to where the pink JPanel is added
     */
    private void pinkSquareDesign(JFrame frame) {
	// pink panel to make the forms look better
	JPanel panel = new JPanel();
	panel.setBounds(32, 99, 410, 500);
	frame.getContentPane().add(panel);
	panel.setLayout(null);
	panel.setBackground(new Color(247, 230, 255));
    }

    /**
     * creates a Label to show a title
     * 
     * @param frame is the frame to where the title is added.
     */
    private void displayAppTitle(JFrame frame) {
	// log in frame
	JLabel loginLabel = new JLabel("Library Management System");
	loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
	loginLabel.setFont(new Font("MS PGothic", Font.PLAIN, 30));
	loginLabel.setBounds(200, 30, 728, 30);
	frame.getContentPane().add(loginLabel);
    }
}
