package com.capgemini.services;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;
import com.capgemini.exceptions.ErrorDuringTransaction;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.MobileNumbeDoesNotExist;
import com.capgemini.exceptions.MobileNumberAlreadyExist;

public interface WalletService {
	public Customer createAccount(String name,String mobileno, BigDecimal amount) throws MobileNumberAlreadyExist;
	public Customer showBalance(String mobileno) throws MobileNumbeDoesNotExist;
	public List<Customer> fundTransfer(String sourceMobileno, String destinationMobileno, BigDecimal amount) throws MobileNumbeDoesNotExist, InsufficientBalance, ErrorDuringTransaction;
	public Customer depositAmount(String mobileno, BigDecimal amount) throws MobileNumbeDoesNotExist;
	public Customer withdrawAmount(String mobileno, BigDecimal amount) throws MobileNumbeDoesNotExist, InsufficientBalance;
	List<Transaction> printTransaction(String mobileno) throws MobileNumbeDoesNotExist;
}
