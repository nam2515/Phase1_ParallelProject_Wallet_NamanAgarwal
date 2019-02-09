package com.capgemini.beans;

import java.util.List;

public class Customer {
	private String name;
	private String mobileno;
	private Wallet wallet;
	private List<Transaction> list;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public List<Transaction> getList() {
		return list;
	}
	public void setList(List<Transaction> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "name:" + name + "\n" 
			  +"mobileno:" + mobileno + "\n"
			  +"wallet:" + wallet+"\n" ;
	}
	
}
