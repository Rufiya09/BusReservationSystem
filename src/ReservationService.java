import java.util.ArrayList;
import java.util.List;

 
    public class ReservationService {
        private ReservationDAO reservationDAO;

        public ReservationService(ReservationDAO reservationDAO) {
            this.reservationDAO = reservationDAO;
        }
        
        
        
        public void addReservation(Reservation reservation) {
            reservationDAO.addReservation(reservation);
        }

        public Reservation getReservationById(int id) {
            return reservationDAO.getReservationById(id);
        }

        public List<Reservation> getReservationsByUserId(int userId) {
            return reservationDAO.getReservationsByUserId(userId);
        }

        public void cancelReservation(int reservationId) {
            reservationDAO.cancelReservation(reservationId);
        }
        
        

//        public List<Reservation> getReservationsByUserId(int userId) {
//            List<Reservation> reservations = reservationDAO.getReservationsByUserId(userId);
//            return reservations != null ? reservations : new ArrayList<>();
//        }

        public Reservation getReservationByName(String reservationName) {
            return reservationDAO.getReservationByName(reservationName);
        }


   
 
    public List<Reservation> getReservationsByBusAndRouteAndDate(int busId, int routeId, String date) {
        return reservationDAO.getReservationsByBusAndRouteAndDate(busId, routeId, date);
    }





  
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

	public void createReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

}
