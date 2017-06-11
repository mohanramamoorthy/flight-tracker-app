package com.adidas.flight.core;

import java.util.List;

import com.adidas.flight.service.impl.EvaluatedRoute;
import com.adidas.flight.service.impl.Route;

/**
 * The string utility method
 * 
 * @author mohanramamoorthy
 *
 */
public final class StringUtil {
	public static final String PATTERN_STATION_CODE = "[A-Z]{3}";
	public static final String PATTERN_FLIGHT_ROUTES = "([A-Z]{3})-([A-Z]{3})-(\\d+)";
	public static final String PATTERN_SINGLE_DIGIT = "//d";
	public static final String PATTERN_MORE_DIGIT = "//d+";
	public static final String PATTERN_ALPHA_HYPHEN = "([A-Z-]+)";

	public static final String STR_FROM = "From : ";
	public static final String STR_TO = "To : ";
	public static final String STR_VERIFY_INPUT = "Please verify the input format";
	public static final String STR_NO_CONNECTION = "No such connection found!";

	/**
	 * Hide the public constructor
	 */
	private StringUtil() {
	}

	/**
	 * Build hyphen separated string
	 * 
	 * @param str
	 * @return
	 */
	public static String buildHyphenSeperatedString(String... str) {
		StringBuilder stringBuilder = new StringBuilder(str[0]);
		for (int i = 1; i < str.length; i++) {
			stringBuilder.append("-");
			stringBuilder.append(str[i]);
		}
		return stringBuilder.toString();
	}

	/**
	 * Build comma separated string from List
	 * 
	 * @param strList
	 * @return
	 */
	public static String buildCommaSeperatedString(List<EvaluatedRoute> routeList, boolean includePrice) {
		if (routeList == null || routeList.isEmpty()) {
			return null;
		}

		String path = null;
		if (includePrice) {
			path = routeList.get(0).getRouteWithPrice();
		} else {
			path = routeList.get(0).getRouteStr();
		}

		StringBuilder stringBuilder = new StringBuilder(path);
		for (int i = 1; i < routeList.size(); i++) {
			stringBuilder.append(", ");
			if (includePrice) {
				path = routeList.get(i).getRouteWithPrice();
			} else {
				path = routeList.get(i).getRouteStr();

			}
			stringBuilder.append(path);
		}

		return stringBuilder.toString();
	}

	/**
	 * Build Route String from the route list.
	 * 
	 * 
	 * @param routeList
	 * @param includePrice
	 * @return
	 */
	public static String buildRouteString(List<Route> routeList, boolean includePrice) {
		if (routeList == null || routeList.isEmpty()) {
			return null;
		}

		StringBuilder stringBuilder = new StringBuilder();
		Route lastRoute = null;
		int cumulativePrice = 0;
		for (Route route : routeList) {
			cumulativePrice += route.getPrice();
			stringBuilder.append(route.getOrigin().getName());
			stringBuilder.append("-");
			lastRoute = route;
		}
		if (lastRoute != null) {
			stringBuilder.append(lastRoute.getDestination().getName());

			if (includePrice) {
				stringBuilder.append("-");
				stringBuilder.append(String.valueOf(cumulativePrice));
			}
		}
		return stringBuilder.toString();
	}
}
