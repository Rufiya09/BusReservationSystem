public class Bus {
    private int id;
    private String busNumber;
    private String type;
    private int totalSeats;
    private int routeId;

    // Constructor
    public Bus(int id, String busNumber, String type, int totalSeats, int routeId) {
        this.id = id;
        this.busNumber = busNumber;
        this.type = type;
        this.totalSeats = totalSeats;
        this.routeId = routeId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getType() {
        return type;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

	public Bus get(int busId) {
		// TODO Auto-generated method stub
		return null;
	}
}
