package com.adidas.flight.service.impl;

import java.util.List;

import com.adidas.flight.core.StringUtil;

/**
 * Evaluated Route will be holding the list of routes with the total price. The
 * objects are compared based on the price for the sorting. The routes with the
 * same price all will be listed.
 * 
 * @author mohanramamoorthy
 *
 */
public class EvaluatedRoute implements Comparable<EvaluatedRoute> {
	private int price = 0;

	private List<Route> routes = null;

	private String routeWithPrice = null;

	private String routeStr = null;

	/**
	 * 
	 * @param price
	 * @param routes
	 */
	public EvaluatedRoute(int price, List<Route> routes) {
		super();
		this.price = price;
		this.routes = routes;
		this.routeStr = StringUtil.buildRouteString(routes, false);
		this.routeWithPrice = StringUtil.buildHyphenSeperatedString(routeStr, String.valueOf(price));
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the routes
	 */
	public List<Route> getRoutes() {
		return routes;
	}

	/**
	 * @return the routeWithPrice
	 */
	public String getRouteWithPrice() {
		return routeWithPrice;
	}

	/**
	 * @return the routeStr
	 */
	public String getRouteStr() {
		return routeStr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + ((routeStr == null) ? 0 : routeStr.hashCode());
		result = prime * result + ((routeWithPrice == null) ? 0 : routeWithPrice.hashCode());
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		EvaluatedRoute aEvaluatedRoute = (EvaluatedRoute) obj;
		if (this.routeWithPrice != null) {
			return this.routeWithPrice.equals(aEvaluatedRoute.getRouteWithPrice());
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvaluatedRoute [price=" + price + ", routes=" + routes + ", routeWithPrice=" + routeWithPrice
				+ ", routeStr=" + routeStr + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EvaluatedRoute aEvaluatedRoute) {
		if (price < aEvaluatedRoute.price) {
			return -1;
		} else if (price == aEvaluatedRoute.price && routes.equals(aEvaluatedRoute.getRoutes())) {
			return 0; // return 0 only for the exact match
		}
		return 1;
	}

}
