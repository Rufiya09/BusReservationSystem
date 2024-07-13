import java.util.ArrayList;
import java.util.List;


public class BusDAOImpl implements BusDAO {
    private List<Bus> buses = new ArrayList<>();

    @Override
    public List<Bus> getAllBuses() {
        return new ArrayList<>(buses);
    }

    @Override
    public void addBus(Bus bus) {
        buses.add(bus);
    }

    @Override
    public Bus getBusById(int busId) {
        for (Bus bus : buses) {
            if (bus.getId() == busId) {
                return bus;
            }
        }
        return null;
    }

    @Override
    public List<Bus> getBusesByRouteId(int routeId) {
        List<Bus> result = new ArrayList<>();
        for (Bus bus : buses) {
            if (bus.getRouteId() == routeId) {
                result.add(bus);
            }
        }
        return result;
    }
}

