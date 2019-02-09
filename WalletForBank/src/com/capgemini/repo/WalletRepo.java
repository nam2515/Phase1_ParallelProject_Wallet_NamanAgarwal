package com.capgemini.repo;

import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;

public interface WalletRepo {

	boolean save(Customer customer);

	Customer findOne(String mobileno);
	
	List<Transaction> findTransaction(String mobileno);

	boolean saveTransaction(Transaction transaction, Customer customer);
}