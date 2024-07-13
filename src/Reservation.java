import java.time.LocalDate;

public class Reservation {
    private int id;
    private int userId;
    private int busId;
    private int routeId;
    private String date;
    private String name;
    private int seatNumber;

    public Reservation(int id, int userId, int busId, int routeId, String date, int seatNumber) {
        this.id = id;
        this.userId = userId;
        this.busId = busId;
        this.routeId = routeId;
        this.date = date;
        this.name = name != null ? name : "";
        this.seatNumber = seatNumber;
    }



    public Reservation(int i, String userName, int busId2, int seatNumber2, LocalDate dateOfJourney) {
		// TODO Auto-generated constructor stub
	}
    
    



	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", busId=" + busId +
                ", routeId=" + routeId +
                ", date='" + date + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }





	public String getDateOfJourney() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getSeatNumbers() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getName() {
		 this.name = name != null ? name : "";
		return null;
	}
	
	public String name () {
		return null;
	}




}
