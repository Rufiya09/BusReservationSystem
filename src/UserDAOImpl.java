import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
    // Other methods as needed

	
	public User getUserByName(String name) {
	    // Logic to retrieve the user by name from the data source
	    for (User user : getAllUsers()) {
	        if (user.getName().equals(name)) {
	            return user;
	        }
	    }
	    return null;
	}


}
