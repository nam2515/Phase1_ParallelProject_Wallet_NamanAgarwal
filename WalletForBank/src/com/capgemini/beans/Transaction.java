package com.capgemini.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	String mobileno;
	BigDecimal deposit;
	BigDecimal withdraw;
	BigDecimal total;
	
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Transaction: \n"
			   +"mobileno: " + mobileno +" \n"
			   +"deposit: " + deposit + "\n"
			   +"withdraw:" + withdraw +"\n"
			   +"total:" + total + "\n";
	}
}
