import java.util.List;

public interface RouteDAO {
    List<Route> getAllRoutes();
    void addRoute(Route route);
	List<Route> getRoutesBySourceAndDestination(String source, String destination);
}
