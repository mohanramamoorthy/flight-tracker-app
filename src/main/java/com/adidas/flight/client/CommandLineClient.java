package com.adidas.flight.client;

import java.util.Scanner;

import com.adidas.flight.core.FlightException;
import com.adidas.flight.core.StringUtil;
import com.adidas.flight.service.RouteService;
import com.adidas.flight.service.impl.RouteServiceImplementation;

/**
 * The command line client takes input and displays output in the command line.
 * 
 * 
 * @author mohanramamoorthy
 *
 */
public class CommandLineClient {
	/**
	 * Flight Route Service instance
	 */
	private RouteService routeService = null;

	/**
	 * Constructor with the fight route service
	 * 
	 * @param routeService
	 */
	public CommandLineClient(RouteService routeService) {
		this.routeService = routeService;
	}

	/**
	 * Process the price for the given connection
	 * 
	 * @param scanner
	 */
	public void processConnectionPrice(Scanner scanner) {
		try {
			System.out.println("Format : AAA-BBB-CCC");
			System.out.print("What is the price of the connection ? : ");
			String connection = scanner.next(StringUtil.PATTERN_ALPHA_HYPHEN);
			System.out.println(connection);
			System.out.println(routeService.getConnectionPrice(connection));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}
	}

	/**
	 * Find the cheapest route between two stations
	 * 
	 * @param scanner
	 */
	public void processCheapestConnectionBetween(Scanner scanner) {
		try {
			System.out.println("Format : From : AAA To : BBB");
			System.out.println("What is the cheapest connnection between two stations ?");
			System.out.print(StringUtil.STR_FROM);
			String originStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print(StringUtil.STR_TO);
			String destinationStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.println(routeService.getCheapestConnectionBetween(originStation, destinationStation));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}

	}

	/**
	 * Find all connections between two stations below the given price
	 * 
	 * @param scanner
	 */
	public void processRouteForMaxPriceBetween(Scanner scanner) {
		try {
			System.out.println("Format : From : AAA To : BBB Max Price : 160");
			System.out.println(
					"What are the different connections available between two stations with lesser the given price ?");
			System.out.print(StringUtil.STR_FROM);
			String originStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print(StringUtil.STR_TO);
			String destinationStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print("Max Price : ");
			int maxPrice = scanner.nextInt();
			System.out.println(routeService.getRouteForMaxPriceBetween(originStation, destinationStation, maxPrice));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}
	}

	/**
	 * Find the number of connections between two station with the maximum stop
	 * over of the given number
	 * 
	 * @param scanner
	 */
	public void processMaxStopOversBetween(Scanner scanner) {
		try {
			System.out.println("Format : From : AAA To : BBB Max no of stops : 2");
			System.out.println(
					"How many number of connections available between two stations with the given maximum stop-over ?");
			System.out.print(StringUtil.STR_FROM);
			String originStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print(StringUtil.STR_TO);
			String destinationStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print("Max no of stops : ");
			int maxNoOfStops = scanner.nextInt();
			System.out.println(routeService.getMaxStopOversBetween(originStation, destinationStation, maxNoOfStops));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}
	}

	/**
	 * Find the number of connections between two station with the minimum stop
	 * over of the given number
	 * 
	 * @param scanner
	 */
	public void processMinStopOversBetween(Scanner scanner) {
		try {
			System.out.println("Format : From : AAA To : BBB Min no of stops : 2");
			System.out.println(
					"How many number of connections available between two stations with the given minimum stop-over ?");
			System.out.print(StringUtil.STR_FROM);
			String originStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print(StringUtil.STR_TO);
			String destinationStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print("Min no of stops : ");
			int minNoOfStops = scanner.nextInt();
			System.out.println(routeService.getMinStopOversBetween(originStation, destinationStation, minNoOfStops));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}
	}

	/**
	 * Find the number of connections between two stations with the exact stop
	 * over of the given number
	 * 
	 * @param scanner
	 */
	public void processExactStopOversBetween(Scanner scanner) {
		try {
			System.out.println("Format : From : AAA To : BBB No of stops : 2");
			System.out.println("How many number of connections available between two stations with the given stop-over ?");
			System.out.print(StringUtil.STR_FROM);
			String originStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print(StringUtil.STR_TO);
			String destinationStation = scanner.next(StringUtil.PATTERN_STATION_CODE);
			System.out.print("No of stops : ");
			int noOfStops = scanner.nextInt();
			System.out.println(routeService.getExactStopOversBetween(originStation, destinationStation, noOfStops));
		} catch (FlightException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}
	}

	/**
	 * The main method gives the option to execute the queries
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println(
					"Note : The connection should starts with \"Connections:\". The format should be source(XXX)-destination(XXX)-price(XXXX) and comma seperated.");
			System.out.print("Enter the comma seperated connection string in this format : ");

			scanner.useDelimiter("\\n");
			String connections = scanner.next(StringUtil.PATTERN_CONNECTION);
			if (!connections.isEmpty()) {
				CommandLineClient commandLineClient = new CommandLineClient(
						new RouteServiceImplementation(connections));

				System.out.println("Options");
				System.out.println("1. Find the price for the given connection");
				System.out.println("2. Find the cheapest connnection between two stations");
				System.out.println("3. Find the number of connections between two stations with the maximum stop-over");
				System.out.println("4. Find the number of connections between two stations with the minimum stop-over");
				System.out.println("5. Find the number of connections between two stations with the exact stop-over");
				System.out.println(
						"6. Find all the different connections available between two stations with lesser the given price");
				System.out.println("7. Exit");

				while (true) {
					System.out.println("--------------------------------------------------------------------------");
					System.out.print("Enter the option : ");

					int option = scanner.nextInt();

					switch (option) {
					case 1:
						commandLineClient.processConnectionPrice(scanner);
						break;
					case 2:
						commandLineClient.processCheapestConnectionBetween(scanner);
						break;
					case 3:
						commandLineClient.processMaxStopOversBetween(scanner);
						break;
					case 4:
						commandLineClient.processMinStopOversBetween(scanner);
						break;
					case 5:
						commandLineClient.processExactStopOversBetween(scanner);
						break;
					case 6:
						commandLineClient.processRouteForMaxPriceBetween(scanner);
						break;
					default:
						System.out.println("-------------------------    THANK YOU !!    -----------------------------");
						System.exit(0);
					}
				}
			}
		} catch (Exception e) {
			// handle the exception to loop again
			System.out.println(StringUtil.STR_VERIFY_INPUT);
		}

	}

}
