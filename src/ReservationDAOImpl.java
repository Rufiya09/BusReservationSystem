import java.util.ArrayList;
import java.util.List;

  
public class ReservationDAOImpl implements ReservationDAO {
    private List<Reservation> reservations = new ArrayList<>();

        @Override
        public List<Reservation> getReservationsByUserId(int userId) {
            List<Reservation> userReservations = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (reservation.getUserId() == userId) {
                    userReservations.add(reservation);
                }
            }
            return userReservations;
        }

        @Override
        public Reservation getReservationByName(String reservationName) {
            for (Reservation reservation : reservations) {
                if (reservation.getName().equalsIgnoreCase(reservationName)) {
                    return reservation;
                }
            }
            return null;
        }

        @Override
        public void cancelReservation(int reservationId) {
            reservations.removeIf(r -> r.getId() == reservationId);
        }


    @Override
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> getReservationsByBusAndRouteAndDate(int busId, int routeId, String date) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getBusId() == busId && reservation.getRouteId() == routeId && reservation.getDate().equals(date)) {
                result.add(reservation);
            }
        }
        return result;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

 

	@Override
	public void deleteReservation(int reservationId) {
		// TODO Auto-generated method stub
		
	}

	public static List<Reservation> getOrDefault(String userName, ArrayList<Object> arrayList) {
		// TODO Auto-generated method stub
		return null;
	}

}
