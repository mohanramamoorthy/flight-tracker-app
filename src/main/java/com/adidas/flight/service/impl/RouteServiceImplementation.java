package com.adidas.flight.service.impl;

import java.util.List;

import com.adidas.flight.core.FlightException;
import com.adidas.flight.core.StringUtil;
import com.adidas.flight.service.RouteService;

/**
 * The flight service implementation
 * 
 * @author mohanramamoorthy
 *
 */
public class RouteServiceImplementation implements RouteService {
	private RouteBuilder routeBuilder = null;

	/**
	 * 
	 * @param connectionString
	 */
	public RouteServiceImplementation(String connectionString) throws FlightException {
		routeBuilder = new RouteBuilder(connectionString);
	}

	/**
	 * Process the price for the given connection
	 * 
	 * @param connectionString
	 * @return
	 */
	@Override
	public int getConnectionPrice(String connectionString) throws FlightException {
		String origin = connectionString.substring(0, connectionString.indexOf('-'));
		String destination = connectionString.substring(connectionString.lastIndexOf('-') + 1);

		List<EvaluatedRoute> listOfRoutes = routeBuilder.getAllPossibleRoutes(origin, destination);

		for (EvaluatedRoute possibleRoute : listOfRoutes) {
			String path = possibleRoute.getRouteStr();

			if (path.equals(connectionString)) {
				return possibleRoute.getPrice();
			}
		}
		throw new FlightException(StringUtil.STR_NO_CONNECTION);
	}

	/**
	 * Find the cheapest route between two stations
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 * @throws FlightException
	 */
	@Override
	public String getCheapestConnectionBetween(String origin, String destination) throws FlightException {
		List<EvaluatedRoute> listOfRoutes = routeBuilder.getAllPossibleRoutes(origin, destination);

		String shortestPath = null;
		int cheapestPrice = Integer.MAX_VALUE;

		for (EvaluatedRoute possibleRoute : listOfRoutes) {
			int price = possibleRoute.getPrice();
			if (price < cheapestPrice) {
				cheapestPrice = price;
				shortestPath = possibleRoute.getRouteWithPrice();
			}
		}

		if (shortestPath == null) {
			throw new FlightException(StringUtil.STR_NO_CONNECTION);
		}

		return shortestPath;
	}

	/**
	 * 
	 * Get route for the given origin and destination with the max price
	 * 
	 * @param origin
	 * @param destination
	 * @param maxPrice
	 * @return
	 * @throws FlightException
	 */
	@Override
	public String getRouteForMaxPriceBetween(String origin, String destination, int maxPrice) throws FlightException {
		List<EvaluatedRoute> listOfEvaluatedRoutes = routeBuilder.getAllPossibleRoutes(origin, destination, maxPrice);

		if (listOfEvaluatedRoutes == null) {
			throw new FlightException(StringUtil.STR_NO_CONNECTION);
		}
		return StringUtil.buildCommaSeperatedString(listOfEvaluatedRoutes, true);
	}

	/**
	 * Find the number of connections between two station with the maximum stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param maxNoOfStops
	 * @return
	 */
	@Override
	public int getMaxStopOversBetween(String origin, String destination, int maxNoOfStops) throws FlightException {
		List<EvaluatedRoute> listOfRoutes = routeBuilder.getAllPossibleRoutes(origin, destination);

		int counter = 0;
		for (EvaluatedRoute possibleRoute : listOfRoutes) {
			int stopOvers = possibleRoute.getSize() - 1;
			if (stopOvers <= maxNoOfStops) {
				counter++;
			}
		}

		return counter;
	}

	/**
	 * Find the number of connections between two station with the minimum stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param maxNoOfStops
	 * @return
	 */
	@Override
	public int getMinStopOversBetween(String origin, String destination, int minNoOfStops) throws FlightException {
		List<EvaluatedRoute> listOfRoutes = routeBuilder.getAllPossibleRoutes(origin, destination);

		int counter = 0;
		for (EvaluatedRoute possibleRoute : listOfRoutes) {
			int stopOvers = possibleRoute.getSize() - 1;
			if (stopOvers >= minNoOfStops) {
				counter++;
			}
		}

		return counter;
	}

	/**
	 * Find the number of connections between two station with the exact stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param maxNoOfStops
	 * @return
	 */
	@Override
	public int getExactStopOversBetween(String origin, String destination, int noOfStops) throws FlightException {
		List<EvaluatedRoute> listOfRoutes = routeBuilder.getAllPossibleRoutes(origin, destination);

		int counter = 0;
		for (EvaluatedRoute possibleRoute : listOfRoutes) {
			int stopOvers = possibleRoute.getSize() - 1;
			if (stopOvers == noOfStops) {
				counter++;
			}
		}

		return counter;
	}

}
