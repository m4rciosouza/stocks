package com.jpmorgan.stocks.bo;

import java.math.BigDecimal;
import java.util.List;

import com.jpmorgan.stocks.entities.Trade;

/**
 * Main interface used to do all the stocks calculations.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public interface SuperSimpleStocksBO {
	
	/**
	 * Add a new trade entry to the trades list.
	 * 
	 * @param marketPrice
	 * @param trades
	 */
	public void recordTrade(BigDecimal marketPrice, List<Trade> trades);

	/**
	 * Calculate the Dividend Yield. 
	 * 
	 * @param stockSymbol
	 * @param marketPrice
	 * @return dividend yield value
	 */
	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal marketPrice);
	
	/**
	 * Calculate the P/E Ratio.
	 * 
	 * @param stockSymbol
	 * @param marketPrice
	 * @return P/E Ratio value
	 */
	public BigDecimal calculatePERatio(String stockSymbol, BigDecimal marketPrice);
	
	/**
	 * Calculate the Volume Weighted Stock Price based on trades in past 15 minutes.
	 * 
	 * @param trades
	 * @return Volume Weighted Stock Price value
	 */
	public BigDecimal calculateVolumeWeighted(List<Trade> trades);
	
	/**
	 * Calculate the GBCE using the geometric mean of prices for all stocks.
	 * 
	 * @param trades
	 * @return GBCE value
	 */
	public BigDecimal calculateGBCE(List<Trade> trades);
}
