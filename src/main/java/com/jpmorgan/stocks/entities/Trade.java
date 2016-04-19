package com.jpmorgan.stocks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Trade entity class.
 * 
 * @author Marcio Casale de Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class Trade implements Serializable {

	private static final long serialVersionUID = -6910236257201752562L;
	
	private Date date;
	private int quantity;
	private Indicator indicator;
	private BigDecimal price;

	public Trade() {
	}

	public Trade(Date date, int quantity, Indicator indicator, BigDecimal price) {
		this.date = date;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indicator == null) ? 0 : indicator.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (indicator != other.indicator)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [date=" + date + ", quantity=" + quantity + ", indicator=" + indicator + ", price=" + price + "]";
	}

}
