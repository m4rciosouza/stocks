package com.jpmorgan.stocks.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.stocks.bo.SuperSimpleStocksBO;
import com.jpmorgan.stocks.entities.Indicator;
import com.jpmorgan.stocks.entities.Stock;
import com.jpmorgan.stocks.entities.StockType;
import com.jpmorgan.stocks.entities.Trade;
import com.jpmorgan.stocks.utils.DateUtils;

/**
 * Implementation class of all stocks calculations.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class SuperSimpleStocksBOImpl implements SuperSimpleStocksBO {

	public static String[] STOCK_SYMBOLS = {"TEA", "POP", "ALE", "GIN", "JOE"};
	private final Map<String, Stock> stocks = new HashMap<String, Stock>();
	
	public SuperSimpleStocksBOImpl() {
		this.stocks.put(STOCK_SYMBOLS[0], new Stock(STOCK_SYMBOLS[0], StockType.COMMOM, 0, 0, 100));
		this.stocks.put(STOCK_SYMBOLS[1], new Stock(STOCK_SYMBOLS[1], StockType.COMMOM, 8, 0, 100));
		this.stocks.put(STOCK_SYMBOLS[2], new Stock(STOCK_SYMBOLS[2], StockType.COMMOM, 23, 0, 60));
		this.stocks.put(STOCK_SYMBOLS[3], new Stock(STOCK_SYMBOLS[3], StockType.PREFERRED, 8, 2, 100));
		this.stocks.put(STOCK_SYMBOLS[4], new Stock(STOCK_SYMBOLS[4], StockType.COMMOM, 13, 0, 250));
	}

	/**
	 * {@inheritDoc}
	 */
	public void recordTrade(BigDecimal marketPrice, List<Trade> trades) {
		Trade trade = new Trade(new Date(), 1, Indicator.BUY, marketPrice);
		trades.add(trade);
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal marketPrice) {
		Stock stock = stocks.get(stockSymbol);
		if(stock == null) {
			throw new IllegalArgumentException("Invalid stock.");
		}
		
		if(stock.getType().equals(StockType.COMMOM)) {
			return new BigDecimal(stock.getLastDividend()).divide(marketPrice).setScale(2, BigDecimal.ROUND_UP);
		} else {
			BigDecimal dividend = new BigDecimal(((double)stock.getFixedDividend() / 100) * stock.getParValue());
			return dividend.divide(marketPrice).setScale(2, BigDecimal.ROUND_UP);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal calculatePERatio(String stockSymbol, BigDecimal marketPrice) {
		BigDecimal dividendYield = calculateDividendYield(stockSymbol, marketPrice);
		
		try {
			return marketPrice.divide(dividendYield).setScale(2, BigDecimal.ROUND_UP);
		}catch(ArithmeticException e) {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal calculateVolumeWeighted(List<Trade> trades) {
		if(trades == null) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal quantity = BigDecimal.ZERO;
		BigDecimal tradePrice = BigDecimal.ONE;
		for(Trade trade: trades) {
			if(DateUtils.hasLessThan15Minutes(trade.getDate())) {
				quantity = quantity.add(BigDecimal.ONE);
				tradePrice = tradePrice.add(trade.getPrice());
			}
		}
		return tradePrice.multiply(quantity).divide(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal calculateGBCE(List<Trade> trades) {
		if(trades == null) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal tradePrice = BigDecimal.ONE;
		for(Trade trade: trades) {
			tradePrice = tradePrice.multiply(trade.getPrice());
		}
		return new BigDecimal(Math.pow(tradePrice.doubleValue(), 1.0 / trades.size())).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
