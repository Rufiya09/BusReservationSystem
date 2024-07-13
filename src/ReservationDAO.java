import java.util.List;

public interface ReservationDAO {
    void addReservation(Reservation reservation);
    void deleteReservation(int reservationId);
    Reservation getReservationById(int reservationId);
    List<Reservation> getAllReservations();
    // Other methods as needed
	List<Reservation> getReservationsByBusAndRouteAndDate(int busId, int routeId, String date);
	void cancelReservation(int reservationId);
	Reservation getReservationByName(String reservationName);
	List<Reservation> getReservationsByUserId(int userId);
	
}
