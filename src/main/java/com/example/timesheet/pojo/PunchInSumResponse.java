package com.example.timesheet.pojo;

import java.math.BigDecimal;

public class PunchInSumResponse {

	public BigDecimal count9To10;
	public BigDecimal count10To11;
	public BigDecimal countAfter11;

	public BigDecimal getCount9To10() {
		return count9To10;
	}

	public void setCount9To10(BigDecimal count9To10) {
		this.count9To10 = count9To10;
	}

	public BigDecimal getCount10To11() {
		return count10To11;
	}

	public void setCount10To11(BigDecimal count10To11) {
		this.count10To11 = count10To11;
	}

	public BigDecimal getCountAfter11() {
		return countAfter11;
	}

	public void setCountAfter11(BigDecimal countAfter11) {
		this.countAfter11 = countAfter11;
	}

}
