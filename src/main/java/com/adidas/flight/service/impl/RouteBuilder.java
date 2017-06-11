package com.adidas.flight.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adidas.flight.core.FlightException;
import com.adidas.flight.core.StringUtil;

/**
 * Building all the possible routes for the given inputs.
 * 
 * The connection path will be built at the constructor level. The paths will be
 * iterated during the runtime based on the inputs given.
 * 
 * 
 * @author mohanramamoorthy
 *
 */
public class RouteBuilder {
	private Set<Route> flightRouteSet = new TreeSet<>();
	private List<Route> routeList = null;
	private Set<EvaluatedRoute> listOfEvaluatedRoutes = null;

	/**
	 * Constructor which builds the stations and flight routes
	 * 
	 * @param connectionString
	 * @throws FlightException
	 */
	public RouteBuilder(String connectionString) throws FlightException {
		// find out the list of stations
		Set<Station> stationSet = new TreeSet<>();
		Pattern p1 = Pattern.compile(StringUtil.PATTERN_STATION_CODE);
		Matcher m1 = p1.matcher(connectionString);
		while (m1.find()) {
			stationSet.add(new Station(m1.group(0)));
		}

		// find out the list of routes
		Pattern p2 = Pattern.compile(StringUtil.PATTERN_FLIGHT_ROUTES);
		Matcher m2 = p2.matcher(connectionString);
		while (m2.find()) {
			flightRouteSet.add(new Route(m2.group(0), stationSet));
		}

	}

	/**
	 * Iterate all the possible routes (recursive)
	 * 
	 * @param origin
	 * @param destination
	 * @param routePrice
	 * @throws FlightException
	 */

	public void iterateAllPossibleRoutes(String source, String destination, int routePrice) throws FlightException {
		int cumlativePrice = routePrice;
		// loop through flight routes
		for (Route route : flightRouteSet) {
			Station originStation = route.getOrigin();
			Station destinationStation = route.getDestination();

			int newPrice = cumlativePrice + route.getPrice();
			if (!originStation.isVisited() && originStation.getName().equals(source)) {
				cumlativePrice = newPrice;
				originStation.setVisited(true);
				routeList.add(route);

				if (destinationStation.getName().equals(destination)) {
					listOfEvaluatedRoutes.add(new EvaluatedRoute(cumlativePrice, new ArrayList<>(routeList)));
				} else if (!destinationStation.isVisited()) {
					iterateAllPossibleRoutes(destinationStation.getName(), destination, cumlativePrice);
				}

				cumlativePrice -= route.getPrice();
				originStation.setVisited(false);
				routeList.remove(routeList.size() - 1);
			}
		}
	}

	/**
	 * Get all the possible routes for the given origin and destination
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 * @throws FlightException
	 */
	public List<EvaluatedRoute> getAllPossibleRoutes(String origin, String destination) throws FlightException {
		routeList = new ArrayList<>();
		this.listOfEvaluatedRoutes = new TreeSet<>();
		iterateAllPossibleRoutes(origin, destination, 0);
		return new ArrayList<>(listOfEvaluatedRoutes);
	}

	/**
	 * Iterate all the possible routes (recursive) for max price
	 * 
	 * @param origin
	 * @param destination
	 * @param maxPrice
	 * 
	 * @throws FlightException
	 */

	public void iterateAllPossibleRoutes(String source, String destination, int routePrice, int maxPrice)
			throws FlightException {
		int cumulativePrice = routePrice;

		for (Route route : flightRouteSet) {
			Station originStation = route.getOrigin();
			Station destinationStation = route.getDestination();

			int newPrice = cumulativePrice + route.getPrice();
			if (originStation.getName().equals(source) && newPrice <= maxPrice) {
				cumulativePrice = newPrice;
				routeList.add(route);

				if (destinationStation.getName().equals(destination)) {
					listOfEvaluatedRoutes.add(new EvaluatedRoute(cumulativePrice, new ArrayList<>(routeList)));
				}

				iterateAllPossibleRoutes(destinationStation.getName(), destination, cumulativePrice, maxPrice);

				cumulativePrice -= route.getPrice();
				routeList.remove(routeList.size() - 1);
			}
		}
	}

	/**
	 * Get all the possible routes for the given origin, destination and max
	 * price
	 * 
	 * @param origin
	 * @param destination
	 * @param maxPrice
	 * 
	 * @return
	 * @throws FlightException
	 */
	public List<EvaluatedRoute> getAllPossibleRoutes(String origin, String destination, int maxPrice)
			throws FlightException {
		routeList = new ArrayList<>();
		this.listOfEvaluatedRoutes = new TreeSet<>();
		iterateAllPossibleRoutes(origin, destination, 0, maxPrice);
		return new ArrayList<>(listOfEvaluatedRoutes);
	}
}
