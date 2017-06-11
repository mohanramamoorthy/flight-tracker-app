/**
 * 
 */
package com.adidas.flight.core;

/**
 * @author mohanramamoorthy
 *
 */
public class FlightException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5384546632674783649L;

	/**
	 * 
	 */
	public FlightException() {
		super();
	}

	/**
	 * @param message
	 */
	public FlightException(String message) {
		super(message);		
	}

	/**
	 * @param cause
	 */
	public FlightException(Throwable cause) {
		super(cause);		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FlightException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FlightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);	
	}

}
