package com.jpmorgan.stocks.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility date class.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class DateUtils {

	private static final int FIFTEEN = 15;
	
	/**
	 * Check if a date given has less than fifteen minutes from the current date.
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean hasLessThan15Minutes(Date date) {
		return hasLessThan(date, FIFTEEN);
	}
	
	/**
	 * Get a date and a quantity of minutes and check if the date is lower than the 
	 * current date less the minutes passed.
	 * 
	 * @param date
	 * @param minutes
	 * @return boolean
	 */
	private static boolean hasLessThan(Date date, int minutes) {
		if(minutes < 0) { 
			return false;
		}
		
		Calendar dateToCompare = Calendar.getInstance();
		dateToCompare.add(Calendar.MINUTE, minutes);
		
		return date.getTime() < dateToCompare.getTimeInMillis();
	}
}
