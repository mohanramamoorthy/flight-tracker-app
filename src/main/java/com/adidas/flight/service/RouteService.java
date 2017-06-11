package com.adidas.flight.service;

import com.adidas.flight.core.FlightException;

/**
 * The Flight Route Service
 * 
 * @author mohanramamoorthy
 *
 */
public interface RouteService {

	/**
	 * Process the price for the given connection
	 * 
	 * @param connectionString
	 * @return
	 */
	public int getConnectionPrice(String connectionString) throws FlightException;

	/**
	 * Find the cheapest route between two stations
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 * @throws FlightException
	 */
	public String getCheapestConnectionBetween(String origin, String destination) throws FlightException;

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
	public String getRouteForMaxPriceBetween(String origin, String destination, int maxPrice)
			throws FlightException;

	/**
	 * Find the number of connections between two station with the maximum stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param maxNoOfStops
	 * @return
	 */
	public int getMaxStopOversBetween(String origin, String destination, int maxNoOfStops)
			throws FlightException;

	/**
	 * Find the number of connections between two station with the minimum stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param minNoOfStops
	 * @return
	 */
	public int getMinStopOversBetween(String origin, String destination, int minNoOfStops)
			throws FlightException;

	/**
	 * Find the number of connections between two station with the exact stop
	 * over of the given number
	 * 
	 * @param origin
	 * @param destination
	 * @param noOfStops
	 * @return
	 * @throws FlightException
	 */
	public int getExactStopOversBetween(String origin, String destination, int noOfStops)
			throws FlightException;

}
