package com.adidas.flight;

import org.junit.BeforeClass;
import org.junit.Test;

import com.adidas.flight.core.FlightException;
import com.adidas.flight.service.RouteService;
import com.adidas.flight.service.impl.RouteServiceImplementation;

/**
 * Test Client
 * 
 * @author mohanramamoorthy
 *
 */
public class FlightRouteServiceTest {

	private static RouteService routeService = null;

	/**
	 * Set the connection string for the test
	 */
	@BeforeClass
	public static void runOnceBeforeClass() {
		try {
			routeService = new RouteServiceImplementation(
					"Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test get connection price
	 */
	@Test
	public void testGetConnectionPrice1() {
		try {
			int price = routeService.getConnectionPrice("NUE-FRA-LHR");
			System.out.println("#1: " + price);
		} catch (FlightException e) {
			System.out.println("#1: " + e.getMessage());
		}
	}

	/**
	 * Test get connection price
	 */
	@Test
	public void testGetConnectionPrice2() {
		try {
			int price = routeService.getConnectionPrice("NUE-AMS-LHR");
			System.out.println("#2: " + price);
		} catch (FlightException e) {
			System.out.println("#2: " + e.getMessage());
		}
	}

	/**
	 * Test get connection price
	 */
	@Test
	public void testGetConnectionPrice3() {
		try {
			int price = routeService.getConnectionPrice("NUE-FRA-LHR-NUE");
			System.out.println("#3: " + price);
		} catch (FlightException e) {
			System.out.println("#3: " + e.getMessage());
		}
	}

	/**
	 * Test get the cheapest connection between two stations
	 */
	@Test
	public void testCheapestConnectionBetween1() {
		try {
			String connection = routeService.getCheapestConnectionBetween("NUE", "AMS");
			System.out.println("#4: " + connection);
		} catch (FlightException e) {
			System.out.println("#4: " + e.getMessage());
		}
	}

	/**
	 * Test get the cheapest connection between two stations
	 */
	@Test
	public void testCheapestConnectionBetween2() {
		try {
			String connection = routeService.getCheapestConnectionBetween("AMS", "FRA");
			System.out.println("#5: " + connection);
		} catch (FlightException e) {
			System.out.println("#5: " + e.getMessage());
		}
	}

	/**
	 * Test get the cheapest connection between two stations
	 */
	@Test
	public void testCheapestConnectionBetween3() {
		try {
			String connection = routeService.getCheapestConnectionBetween("LHR", "LHR");
			System.out.println("#6: " + connection);
		} catch (FlightException e) {
			System.out.println("#6: " + e.getMessage());
		}
	}

	/**
	 * Test get the max stop overs between two stations
	 */
	@Test
	public void testMaxStopOversBetween1() {
		try {
			int routeCount = routeService.getMaxStopOversBetween("NUE", "FRA", 3);
			System.out.println("#7: " + routeCount);
		} catch (FlightException e) {
			System.out.println("#7: " + e.getMessage());
		}
	}

	/**
	 * Test get the exact stop overs between two stations
	 */
	@Test
	public void testExactStopOversBetween1() {
		try {
			int routeCount = routeService.getExactStopOversBetween("LHR", "AMS", 1);
			System.out.println("#8: " + routeCount);
		} catch (FlightException e) {
			System.out.println("#8: " + e.getMessage());
		}
	}

	/**
	 * Test get the min stop overs between two stations
	 */
	@Test
	public void testMinStopOversBetween1() {
		try {
			int routeCount = routeService.getMinStopOversBetween("LHR", "AMS", 1);
			System.out.println("#9: " + routeCount);
		} catch (FlightException e) {
			System.out.println("#9: " + e.getMessage());
		}
	}

	/**
	 * Test get the route for max price between two stations
	 */
	@Test
	public void testRouteForMaxPriceBetween1() {
		try {
			String connection = routeService.getRouteForMaxPriceBetween("NUE", "LHR", 170);
			System.out.println("#10: " + connection);
		} catch (FlightException e) {
			System.out.println("#10: " + e.getMessage());
		}
	}

}
