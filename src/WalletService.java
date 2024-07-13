import java.util.HashMap;
import java.util.Map;

public class WalletService {
    private Map<Integer, Double> wallets; // Map to store user ID and wallet balance

    public WalletService() {
        this.wallets = new HashMap<>();
    }

    // Method to add amount to user's wallet
    public void addToWallet(int userId, double amount) {
        if (wallets.containsKey(userId)) {
            double currentBalance = wallets.get(userId);
            wallets.put(userId, currentBalance + amount);
        } else {
            wallets.put(userId, amount);
        }
    }

    // Method to deduct amount from user's wallet
    public boolean deductFromWallet(int userId, double amount) {
        if (wallets.containsKey(userId)) {
            double currentBalance = wallets.get(userId);
            if (currentBalance >= amount) {
                wallets.put(userId, currentBalance - amount);
                return true; // Deduction successful
            } else {
                return false; // Insufficient funds
            }
        } else {
            return false; // User not found in wallet (should ideally not happen in well-formed system)
        }
    }

    // Method to get wallet balance of a user
    public double getWalletBalance(int userId) {
        return wallets.getOrDefault(userId, 0.0);
    }

    // Method to add balance to wallet
    public void addBalance(int userId, double amount) {
        addToWallet(userId, amount);
    }

    // Method to get balance from wallet
    public double getBalance(int userId) {
        return getWalletBalance(userId);
    }

	public void deductBalance(int id, double reservationAmount) {
		// TODO Auto-generated method stub
		
	}

	public void deductAmount(int id, double fare) {
		// TODO Auto-generated method stub
		
	}

	public void addAmount(int id, double amount) {
		// TODO Auto-generated method stub
		
	}

	public void updateWalletBalance(User user) {
		// TODO Auto-generated method stub
		
	}
}
