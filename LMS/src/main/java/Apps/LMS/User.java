package Apps.LMS;

/**
 * Class to represent the logged in user in the system.
 * 
 * @author miguel munoz
 *
 */
public class User {
    private String username;
    private String type;

    public User(String username, String type) {
	this.username = username;
	this.type = type;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }
}
