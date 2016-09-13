package by.epam.hostelbeta.domain.entity;

import java.time.LocalDate;

/**
 * The Class CurrencyRate. POJO for currency rate
 */
public class CurrencyRate {
	
	/** The code. */
	private String code;
	
	/** The date. */
	private LocalDate date;
	
	/** The rate. */
	private double rate;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
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
		CurrencyRate other = (CurrencyRate) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate)) {
			return false;
		}
		return true;
	}
}
