public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    public User loginUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
    
  
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addToWallet(int userId, double amount) {
		// TODO Auto-generated method stub
		
	}
	

	public void deductWalletBalance(int id, double totalFare) {
		// TODO Auto-generated method stub
		
	}



}
