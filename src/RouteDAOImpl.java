import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    private List<Route> routes = new ArrayList<>();

    @Override
    public List<Route> getAllRoutes() {
        return new ArrayList<>(routes);
    }

    @Override
    public List<Route> getRoutesBySourceAndDestination(String source, String destination) {
        List<Route> matchingRoutes = new ArrayList<>();
        for (Route route : routes) {
            if (route.getSource().equalsIgnoreCase(source) && route.getDestination().equalsIgnoreCase(destination)) {
                matchingRoutes.add(route);
            }
        }
        return matchingRoutes;
    }

    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }
}
