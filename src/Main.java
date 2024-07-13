import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static UserDAO userDAO = new UserDAOImpl();
    private static UserService userService = new UserService(userDAO);
    private static BusDAO busDAO = new BusDAOImpl();
    private static BusService busService = new BusService(busDAO);
    private static RouteDAO routeDAO = new RouteDAOImpl();
    private static RouteService routeService = new RouteService(routeDAO);
    private static ReservationDAO reservationDAO = new ReservationDAOImpl();
    private static ReservationService reservationService = new ReservationService(reservationDAO);
    private static WalletService walletService = new WalletService();

    private static Scanner scanner = new Scanner(System.in);
    private static int currentUserId;
    private static double currentReservationAmount;

    public static void main(String[] args) {
        while (true) {
        	System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Bus Availability Enquiry");
            System.out.println("4. Add Route");
            System.out.println("5. Add Bus");
            System.out.println("6. Make Reservation");
            System.out.println("7. Cancel Reservation");
//            System.out.println("8. Print Ticket");
            System.out.println("8. Wallet Payment");
            System.out.println("9. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    busAvailabilityEnquiry();
                    break;
                case 4:
                    addRoute();
                    break;
                case 5:
                    addBus();
                    break;
                case 6:
                    makeReservation();
                    break;
                case 7:
                    cancelReservation();
                    break;
//                case 8:
//                    printTicket();
//                    break;
                case 8:
                    walletPayment();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        User newUser = new User(userDAO.getAllUsers().size() + 1, name, email, password, false);
        userService.registerUser(newUser);
        System.out.println("User registered successfully!");
        System.out.println();
    }

    private static void loginUser() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        User user = userService.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful!");
            currentUserId = user.getId();
            if (user.isAdmin()) {
                adminMenu();
            } else {
                userMenu();
            }
        } else {              
            System.out.println("Invalid email or password.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Bus");
            System.out.println("2. Add Route");
            System.out.println("3. View Reservations");
            System.out.println("4. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    addRoute();
                    break;
                case 3:
                    // Implement view reservations functionality
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Bus Availability Enquiry");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
//            System.out.println("4. Print Ticket");
            System.out.println("4. Wallet Payment");
            System.out.println("5. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    busAvailabilityEnquiry();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
//                case 4:
//                    printTicket();
//                    break;
                case 4:
                    walletPayment();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void busAvailabilityEnquiry() {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        List<Route> routes = routeService.getRoutesBySourceAndDestination(source, destination);
        if (routes == null || routes.isEmpty()) {
            System.out.println("No routes found from " + source + " to " + destination);
            return;
        }

        boolean busesFound = false;
        for (Route route : routes) {
            List<Bus> buses = busService.getBusesByRouteId(route.getId());
            if (buses == null || buses.isEmpty()) {
                System.out.println("No buses found for route from " + source + " to " + destination);
            } else {
                busesFound = true;
                System.out.println("Available buses for route from " + source + " to " + destination + ":");
                for (Bus bus : buses) {
                    System.out.println("Bus ID: " + bus.getId() + ", Bus Number: " + bus.getBusNumber() + ", Type: " + bus.getType() + ", Total Seats: " + bus.getTotalSeats());
                }
            }
        }

        if (!busesFound) {
            System.out.println("No buses found for route from " + source + " to " + destination);
        }
    }

    private static void addBus() {
        System.out.print("Enter Bus Number: ");
        String busNumber = scanner.nextLine();
        System.out.print("Enter Bus Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // List available routes
        List<Route> routes = routeService.getAllRoutes();
        if (routes.isEmpty()) {
            System.out.println("No routes available. Please add a route first.");
            return;
        }

        System.out.println("Available routes:");
        for (Route route : routes) {
            System.out.println(route.getId() + ". " + route.getSource() + " to " + route.getDestination());
        }
        
        int routeId;
        while (true) {
            System.out.print("Enter Route ID to associate with the bus: ");
            routeId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

         // Validate the Route ID
            boolean isValidRouteId = false;
            for (Route route : routes) {
                if (route.getId() == routeId) {
                    isValidRouteId = true;
                    break;
                }
            }
                
                if (isValidRouteId) {
                    break; // Exit the loop if Route ID is valid
                } else {
                    System.out.println("Invalid Route ID. Please try again.");
                }
            }
           
        Bus newBus = new Bus(busDAO.getAllBuses().size() + 1, busNumber, type, totalSeats, routeId);
        busService.addBus(newBus);
        System.out.println("Bus added successfully!");
        System.out.println();
    }

    private static void addRoute() {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        Route newRoute = new Route(routeDAO.getAllRoutes().size() + 1, source, destination);
        routeService.addRoute(newRoute);
        System.out.println("Route added successfully!");
        System.out.println();

        // List routes after adding to verify
        List<Route> routes = routeService.getAllRoutes();
        System.out.println("Current routes:");
        for (Route route : routes) {
            System.out.println(route.getId() + ". " + route.getSource() + " to " + route.getDestination());
        }
    }

    private static void makeReservation() {
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        User user = userService.getUserByName(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        currentUserId = user.getId();  // Store current user ID for reference

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        if (!isValidDate(date)) {
            System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
            return;
        }

        List<Route> routes = routeService.getRoutesBySourceAndDestination(source, destination);
        if (routes == null || routes.isEmpty()) {
            System.out.println("No routes found from " + source + " to " + destination);
            return;
        }

        boolean busesFound = false;
        for (Route route : routes) {
            List<Bus> buses = busService.getBusesByRouteId(route.getId());
            if (buses == null || buses.isEmpty()) {
                System.out.println("No buses found for route from " + source + " to " + destination);
            } else {
                busesFound = true;
                System.out.println("Available buses for route from " + source + " to " + destination + ":");
                for (Bus bus : buses) {
                    System.out.println("Bus ID: " + bus.getId() + ", Bus Number: " + bus.getBusNumber() + ", Type: " + bus.getType() + ", Total Seats: " + bus.getTotalSeats());
                }
            }
        }

        if (!busesFound) {
            System.out.println("No buses found for route from " + source + " to " + destination);
            return;
        }

        System.out.print("Enter Bus ID to make a reservation: ");
        int busId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Bus bus = busService.getBusById(busId);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        List<Reservation> reservations = reservationService.getReservationsByBusAndRouteAndDate(busId, routes.get(0).getId(), date);
        int totalSeats = bus.getTotalSeats();
        int availableSeats = totalSeats - reservations.size();
        if (availableSeats <= 0) {
            System.out.println("No available seats for this bus on the selected date.");
            return;
        }

        System.out.println("Available seats: " + availableSeats);
        System.out.print("Enter Seat Numbers (comma-separated): ");
        String seatNumbersInput = scanner.nextLine();
        String[] seatNumbersArray = seatNumbersInput.split(",");
        List<Integer> seatNumbers = new ArrayList<>();
        Set<Integer> seatNumbersSet = new HashSet<>();

        for (String seatNumber : seatNumbersArray) {
            int seatNum = Integer.parseInt(seatNumber.trim());
            if (seatNum <= 0 || seatNum > totalSeats) {
                System.out.println("Seat number " + seatNum + " is out of the range of available seats.");
                return;
            }
            if (!seatNumbersSet.add(seatNum)) {
                System.out.println("Duplicate seat number " + seatNum + " found.");
                return;
            }
            seatNumbers.add(seatNum);
        }

        for (int seatNumber : seatNumbers) {
            for (Reservation reservation : reservations) {
                if (reservation.getSeatNumber() == seatNumber) {
                    System.out.println("Seat number " + seatNumber + " is already reserved. Please try again.");
                    return;
                }
            }
        }

        double totalReservationAmount = calculateReservationAmount(bus) * seatNumbers.size();
        double walletBalance = walletService.getWalletBalance(user.getId());

        if (walletBalance >= totalReservationAmount) {
            for (int seatNumber : seatNumbers) {
                double reservationAmount = calculateReservationAmount(bus);
                walletService.deductFromWallet(user.getId(), reservationAmount);
                walletBalance -= reservationAmount;

                Reservation newReservation = new Reservation(reservationDAO.getAllReservations().size() + 1, user.getId(), busId, routes.get(0).getId(), date, seatNumber);
                reservationService.addReservation(newReservation);

                // Display the reservation details
                System.out.println();
                System.out.println("Reservation Details:");
                System.out.println("Reservation ID: " + newReservation.getId());
                System.out.println("Reservation Name: " + userName);
                System.out.println("Bus ID: " + newReservation.getBusId());
                System.out.println("Route ID: " + newReservation.getRouteId());
                System.out.println("Date: " + newReservation.getDate());
                System.out.println("Seat Number: " + newReservation.getSeatNumber());
                System.out.println();
            }
            System.out.println("Reservation made successfully!");
            System.out.println("Remaining Wallet Balance: " + walletBalance);
        } else {
            System.out.println("Insufficient balance in wallet. Please add funds or use another payment method.");
        }
    }

    private static double calculateReservationAmount(Bus bus) {
        switch (bus.getType().toLowerCase()) {
            case "ac":
                return 800;
            case "non-ac":
                return 450;
            case "sleeper":
                return 1500;
            default:
                return 0; // Default fare in case of an unknown bus type
        }
    }
    
    private static boolean isValidDate(String date) {
	    try {
	        LocalDate parsedDate = LocalDate.parse(date);
	        LocalDate today = LocalDate.now();
	        return parsedDate.isAfter(today);
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	    
    }
	   
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }




	private static void cancelReservation() {
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine().trim();
        User user = userService.getUserByName(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        List<Reservation> userReservations = reservationService.getReservationsByUserId(user.getId());
        if (userReservations.isEmpty()) {
            System.out.println("No reservations found for user: " + userName);
            return;
        }

        System.out.println("Reservations for " + userName + ":");
        for (Reservation reservation : userReservations) {
            System.out.println("Reservation ID: " + reservation.getId() + ", Bus ID: " + reservation.getBusId() +
                               ", Route ID: " + reservation.getRouteId() + ", Date: " + reservation.getDate() +
                               ", Seat Number: " + reservation.getSeatNumber());
        }

        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservationToCancel = userReservations.stream()
                .filter(r -> r.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservationToCancel == null) {
            System.out.println("Reservation not found.");
            return;
        }

        reservationService.cancelReservation(reservationId);
        System.out.println("Reservation with ID " + reservationId + " canceled successfully.");
        System.out.println();

        // Show remaining reservations
        userReservations = reservationService.getReservationsByUserId(user.getId());
        if (userReservations.isEmpty()) {
            System.out.println("No remaining reservations for user: " + userName);
        } else {
            System.out.println("Remaining Reservations for " + userName + ":");
            for (Reservation reservation : userReservations) {
                System.out.println("Reservation ID: " + reservation.getId() + ", Bus ID: " + reservation.getBusId() +
                                   ", Route ID: " + reservation.getRouteId() + ", Date: " + reservation.getDate() +
                                   ", Seat Number: " + reservation.getSeatNumber());
            }
        }
    }
    
	
    
    
 
    
    


    
//	private static void printTicket() {
//	    System.out.print("Enter Reservation Name: ");
//	    String reservationName = scanner.nextLine().trim();
//
//	    Reservation reservation = reservationService.getReservationByName(reservationName);
//	    if (reservation == null) {
//	        System.out.println("Reservation not found.");
//	        return;
//	    }
//
//	    User user = userService.getUserById(reservation.getUserId());
//	    if (user == null) {
//	        System.out.println("User not found.");
//	        return;
//	    }
//
//	    Bus bus = busService.getBusById(reservation.getBusId());
//	    Route route = routeService.getRouteById(reservation.getRouteId());
//
//	    System.out.println("Ticket Details:");
//	    System.out.println("Reservation Name: " + reservation.getName());
//	    System.out.println("User Name: " + user.getName());
//	    System.out.println("Bus Number: " + bus.getBusNumber());
//	    System.out.println("Bus Type: " + bus.getType());
//	    System.out.println("Route: " + route.getSource() + " to " + route.getDestination());
//	    System.out.println("Date: " + reservation.getDate());
//	    System.out.println("Seat Number: " + reservation.getSeatNumber());
//	}




    private static void walletPayment() {
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        User user = userService.getUserByName(userName);
        if (user == null) {
            System.out.println("User not found. Please register first.");
            return;
        }

        // Check if the user making the reservation is the same as the wallet user
        if (user.getId() != currentUserId) {
            System.out.println("Invalid user for wallet payment. Please use the same user as in reservation.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        walletService.addToWallet(user.getId(), amount);
        System.out.println("Amount added to wallet successfully.");
        System.out.println("Current Wallet Balance: " + walletService.getWalletBalance(user.getId()));
    }
    
    
    

}
        
