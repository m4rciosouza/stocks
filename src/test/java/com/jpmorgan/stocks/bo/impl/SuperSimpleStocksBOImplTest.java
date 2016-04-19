package com.jpmorgan.stocks.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.stocks.bo.SuperSimpleStocksBO;
import com.jpmorgan.stocks.entities.Indicator;
import com.jpmorgan.stocks.entities.Trade;

import junit.framework.TestCase;

/**
 * Super simple stock test class.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class SuperSimpleStocksBOImplTest extends TestCase {
	
	private SuperSimpleStocksBO stocksBO;
	private List<Trade> trades;

	protected void setUp() throws Exception {
		super.setUp();
		this.stocksBO = new SuperSimpleStocksBOImpl();
		this.trades = new ArrayList<Trade>();
	}

	/**
	 * Test record a new trade.
	 */
	public void testRecordTrade() {
		BigDecimal marketPrice1 = new BigDecimal("100");
		BigDecimal marketPrice2 = new BigDecimal("200");
		stocksBO.recordTrade(marketPrice1, trades);
		stocksBO.recordTrade(marketPrice2, trades);
		
		assertEquals(2, trades.size());
		assertEquals(marketPrice1, trades.get(0).getPrice());
		assertEquals(Indicator.BUY, trades.get(0).getIndicator());
		assertEquals(1, trades.get(0).getQuantity());
		assertEquals(marketPrice2, trades.get(1).getPrice());
	}

	/**
	 * Test calculate common dividend yield.
	 */
	public void testCalculateCommonDividendYield() {
		assertEquals(new BigDecimal("0.00"), this.stocksBO.calculateDividendYield("TEA", new BigDecimal(100)));
		assertEquals(new BigDecimal("0.08"), this.stocksBO.calculateDividendYield("POP", new BigDecimal(100)));
		assertEquals(new BigDecimal("0.13"), this.stocksBO.calculateDividendYield("JOE", new BigDecimal(100)));
	}
	
	/**
	 * Test calculate preferred dividend yield.
	 */
	public void testCalculatePreferredDividendYield() {
		assertEquals(new BigDecimal("0.02"), this.stocksBO.calculateDividendYield("GIN", new BigDecimal(100)));
	}

	/**
	 * Test calculate the P/E Ratio.
	 */
	public void testCalculatePERatio() {
		assertEquals(BigDecimal.ZERO, this.stocksBO.calculatePERatio("JOE", new BigDecimal(100)));
	}

	/**
	 * Test calculate the geometric mean GBCE.
	 */
	public void testCalculateGBCE() {
		BigDecimal marketPrice1 = new BigDecimal("100");
		BigDecimal marketPrice2 = new BigDecimal("200");
		stocksBO.recordTrade(marketPrice1, trades);
		stocksBO.recordTrade(marketPrice2, trades);
		
		assertEquals(new BigDecimal("141.42"), stocksBO.calculateGBCE(trades));
	}

	/**
	 * Test calculate the volume weighted stock price.
	 */
	public void testCalculateVolumeWeighted() {
		BigDecimal marketPrice1 = new BigDecimal("100");
		BigDecimal marketPrice2 = new BigDecimal("200");
		stocksBO.recordTrade(marketPrice1, trades);
		stocksBO.recordTrade(marketPrice2, trades);
		
		assertEquals(new BigDecimal("301.00"), stocksBO.calculateVolumeWeighted(trades));
	}

}
