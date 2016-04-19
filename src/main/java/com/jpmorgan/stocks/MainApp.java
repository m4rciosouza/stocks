package com.jpmorgan.stocks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jpmorgan.stocks.bo.SuperSimpleStocksBO;
import com.jpmorgan.stocks.bo.impl.SuperSimpleStocksBOImpl;
import com.jpmorgan.stocks.entities.Trade;

/**
 * Main application console screen class.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class MainApp 
{
	// All the trades are stored in memory
	private final List<Trade> trades = new ArrayList<Trade>();
	private String stockSymbol;
	private String marketPrice;
	private SuperSimpleStocksBO stocksBO = new SuperSimpleStocksBOImpl();
	
	/**
	 * Get the input data from console screen to be processed.
	 * 
	 * @param in
	 */
	private void getInputData(Scanner in) {
		
		do {
			System.out.println("Enter the stock symbol (TEA, POP, ALE, GIN, JOE): ");
			stockSymbol = in.nextLine();
		} while(!validateStockSymbol(stockSymbol));
		
		do {
			System.out.println("Enter the market price: ");
			marketPrice = in.nextLine();
		} while(!validateMarketPrice(marketPrice));
			
	}

	/**
	 * Calculate and show all the calculated values.
	 */
	private void process() {
		BigDecimal marketPriceValue = new BigDecimal(marketPrice);
		stocksBO.recordTrade(marketPriceValue, trades);
		System.out.println("\nDividend Yield: " + stocksBO.calculateDividendYield(stockSymbol, marketPriceValue));
		System.out.println("P/E Ratio: " + stocksBO.calculatePERatio(stockSymbol, marketPriceValue));
		System.out.println("Volume Weighted Stock Price: " + stocksBO.calculateVolumeWeighted(trades));
		System.out.println("GBCE: " + stocksBO.calculateGBCE(trades));
		System.out.println("\nCtrl+C to cancel or try again");
	}
	
	/**
	 * Validate the imput console value for the stock symbol value.
	 * 
	 * @param stockSymbol
	 * @return boolean
	 */
	private boolean validateStockSymbol(String stockSymbol) {
		for(String symbol: SuperSimpleStocksBOImpl.STOCK_SYMBOLS) {
			if(symbol.equals(stockSymbol)) {
				return true;
			}
		}
		System.out.println("Invalid stock symbol.");
		return false;
	}

	/**
	 * Validate the input console value for the market price
	 * 
	 * @param marketPrice
	 * @return boolean
	 */
    private boolean validateMarketPrice(String marketPrice) {
		try {
			new BigDecimal(marketPrice);
			return true;
		} catch(Exception e) {
			System.out.println("Invalid market price");
		}
		return false;
	}
    
    /**
	 * Manage the console and application workflow execution.
	 */
	private void run() {
		System.out.println("### Super Simple Stocks ###\n");
		Scanner in = new Scanner(System.in);
		while(true) {
			getInputData(in);
			process();
		}
	}

	/**
	 * Run the main application console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
    	MainApp app = new MainApp();
    	app.run();
    }
}
