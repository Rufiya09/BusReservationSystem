public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    public User(int id, String name, String email, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public User getUserByName(String name) {
        for (User user : getAllUsers()) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }


    private User[] getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	// Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

	public double getWalletBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setWalletBalance(double d) {
		// TODO Auto-generated method stub
		
	}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
