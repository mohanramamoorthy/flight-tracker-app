/**
 * 
 */
package com.adidas.flight.service.impl;

import java.util.Set;

import com.adidas.flight.core.FlightException;
import com.adidas.flight.core.StringUtil;

/**
 * 
 * Flight Route Object
 * 
 * @author mohanramamoorthy
 *
 */
public class Route implements Comparable<Route> {
	private Station origin = null;

	private Station destination = null;

	private int price = 0;

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param price
	 */
	public Route(String connectionString, Set<Station> stationList) throws FlightException {
		try {
			String[] route = connectionString.split("-");
			this.origin = getStation(route[0], stationList);
			this.destination = getStation(route[1], stationList);
			this.price = Integer.parseInt(route[2]);
		} catch (Exception e) {
			throw new FlightException("Invalid connection string !");
		}
	}

	/**
	 * 
	 * @param stationName
	 * @param stationList
	 * @return
	 */
	private Station getStation(String stationName, Set<Station> stationList) {
		if (stationName == null || stationName.isEmpty()) {
			return null;
		}

		for (Station station : stationList) {
			if (station.getName().equals(stationName)) {
				return station;
			}
		}

		return null;
	}

	/**
	 * @return the origin
	 */
	public Station getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(Station origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public Station getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(Station destination) {
		this.destination = destination;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
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
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + price;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Route other = (Route) obj;

		if (price != other.price) {
			return false;
		}

		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}

		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringUtil.buildHyphenSeperatedString(this.origin.toString(), this.destination.toString(),
				String.valueOf(this.price));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Route route) {
		return toString().compareTo(route.toString());
	}

}
