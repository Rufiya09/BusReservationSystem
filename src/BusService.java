import java.util.List;

public class BusService {
    private BusDAO busDAO;

    public BusService(BusDAO busDAO) {
        this.busDAO = busDAO;
    }

    public List<Bus> getBusesByRouteId(int routeId) {
        List<Bus> buses = busDAO.getBusesByRouteId(routeId);
        return buses != null ? buses : List.of(); // Return an empty list if null
    }

    public void addBus(Bus bus) {
        busDAO.addBus(bus);
    }

    public Bus getBusById(int busId) {
        return busDAO.getBusById(busId);
    }

	public double calculateFare(int busId, int length) {
		// TODO Auto-generated method stub
		return 0;
	}
}
