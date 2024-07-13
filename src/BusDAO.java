import java.util.List;

public interface BusDAO {
    void addBus(Bus bus);
    Bus getBusById(int id);
    List<Bus> getAllBuses();
    List<Bus> getBusesByRouteId(int routeId);
}
