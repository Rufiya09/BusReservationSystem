import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    // Other methods as needed
	User getUserByName(String name);
}


