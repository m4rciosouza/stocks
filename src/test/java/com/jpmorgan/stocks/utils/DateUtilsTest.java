package com.jpmorgan.stocks.utils;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

/**
 * Utility date test class.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class DateUtilsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test if the date has less than fifteen minutes
	 */
	public void testDateHasLessThan15Minutes() {
		Calendar dateLess5Minutes = Calendar.getInstance();
		dateLess5Minutes.add(Calendar.MINUTE, -5);
		
		Calendar dateLess10Minutes = Calendar.getInstance();
		dateLess10Minutes.add(Calendar.MINUTE, -5);
		
		Calendar dateLess15Minutes = Calendar.getInstance();
		dateLess15Minutes.add(Calendar.MINUTE, -5);
		
		assertTrue("Current date", DateUtils.hasLessThan15Minutes(new Date()));
		assertTrue("Current date less 5 minutes", DateUtils.hasLessThan15Minutes(dateLess5Minutes.getTime()));
		assertTrue("Current date less 10 minutes", DateUtils.hasLessThan15Minutes(dateLess10Minutes.getTime()));
		assertTrue("Current date less 15 minutes", DateUtils.hasLessThan15Minutes(dateLess15Minutes.getTime()));
	}

	/**
	 * Test if the date has more than fifteen minutes
	 */
	public void testDateHasMoreThan15Minutes() {
		Calendar dateLess16Minutes = Calendar.getInstance();
		dateLess16Minutes.add(Calendar.MINUTE, -16);
		
		assertTrue("Current date less 10 minutes", DateUtils.hasLessThan15Minutes(dateLess16Minutes.getTime()));
	}
}
