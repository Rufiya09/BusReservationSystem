import java.util.List;

public class RouteService {
    private RouteDAO routeDAO;

    public RouteService(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public List<Route> getAllRoutes() {
        return routeDAO.getAllRoutes();
    }

    public List<Route> getRoutesBySourceAndDestination(String source, String destination) {
        return routeDAO.getRoutesBySourceAndDestination(source, destination);
    }

    public void addRoute(Route route) {
        routeDAO.addRoute(route);
    }

	public Route getRouteById(int routeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
