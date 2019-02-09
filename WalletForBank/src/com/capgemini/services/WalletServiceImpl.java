package com.capgemini.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;
import com.capgemini.beans.Wallet;
import com.capgemini.exceptions.ErrorDuringTransaction;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.MobileNumbeDoesNotExist;
import com.capgemini.exceptions.MobileNumberAlreadyExist;
import com.capgemini.repo.WalletRepo;

public class WalletServiceImpl implements WalletService {
	WalletRepo walrepo;
	Customer customer;
	Wallet wallet;
	Transaction transaction;
	
	public WalletServiceImpl(WalletRepo walrepo) {
		super();
		this.walrepo = walrepo;
	}

	public Customer createAccount(String name, String mobileno, BigDecimal amount) throws MobileNumberAlreadyExist {
		List<Transaction> list=new ArrayList<>();
		
		customer=new Customer();
		wallet=new Wallet();
		if(walrepo.findOne(mobileno)!=null)
		{
			throw new MobileNumberAlreadyExist();
		}
		customer.setName(name);
		customer.setMobileno(mobileno);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		customer.setList(list);
		walrepo.save(customer);
		return customer;
	}

	public Customer showBalance(String mobileno) throws MobileNumbeDoesNotExist {
		// TODO Auto-generated method stub
		if(walrepo.findOne(mobileno)==null)
		{
			throw new MobileNumbeDoesNotExist();
		}
		return walrepo.findOne(mobileno);
	}

	public List<Customer> fundTransfer(String sourcemobileno, String destinationmobileno, BigDecimal amount) throws MobileNumbeDoesNotExist, InsufficientBalance, ErrorDuringTransaction {
		List<Customer> l=new ArrayList<>();
		Customer c1=showBalance(sourcemobileno);
		Wallet w1=c1.getWallet();
		Customer c2=showBalance(destinationmobileno);
		Wallet w2=c2.getWallet();
		//Checking exception
		int res=w1.getBalance().compareTo(amount);
		if(res==-1)
		{
			throw new InsufficientBalance();
		}
		
		w1.setBalance(w1.getBalance().subtract(amount));
		c1.setWallet(w1);
		w2.setBalance(w2.getBalance().add(amount));
		c2.setWallet(w2);
		l.add(c1);
		l.add(c2);
		Transaction transaction=new Transaction();
		transaction=createTransaction("withdraw", sourcemobileno, amount, c1);
		if(transaction==null)
		{
			throw new ErrorDuringTransaction();
		}
		walrepo.saveTransaction(transaction, c1);
		transaction=createTransaction("deposit", destinationmobileno, amount, c2);
		if(transaction==null)
		{
			throw new ErrorDuringTransaction();
		}
		walrepo.saveTransaction(transaction,c2);
		return l;
	}

	public Customer depositAmount(String mobileno, BigDecimal amount) throws MobileNumbeDoesNotExist {
		customer=new Customer();
		customer =showBalance(mobileno);
		wallet=customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(amount));
		customer.setWallet(wallet);
		Transaction transaction=new Transaction();
		transaction=createTransaction("deposit", mobileno, amount,  customer);
		walrepo.saveTransaction(transaction,customer);
	return customer;
	}

	public Customer withdrawAmount(String mobileno, BigDecimal amount) throws MobileNumbeDoesNotExist, InsufficientBalance {
		// TODO Auto-generated method stub
		customer =showBalance(mobileno);
		wallet=customer.getWallet();
		int res=wallet.getBalance().compareTo(amount);
		if(res==-1)
		{
			throw new InsufficientBalance();
		}
		wallet.setBalance(wallet.getBalance().subtract(amount));
		customer.setWallet(wallet);
		Transaction transaction=new Transaction();
		transaction=createTransaction("withdraw", mobileno, amount,  customer);
		walrepo.saveTransaction(transaction,customer);
		
		
	return customer;
	}
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.services.T#printTransaction(java.lang.String)
	 */
	public List<Transaction> printTransaction(String mobileno) throws MobileNumbeDoesNotExist
	{
		List<Transaction> list=new ArrayList<>();
		list=walrepo.findTransaction(mobileno);
		if(list.size()!=0)
		{
			return list;
		}
		return null;
	}
	
	private static Transaction createTransaction(String str, String mobileno, BigDecimal amount, Customer customer)
	{
		Transaction transaction=new Transaction();
		switch(str)
		{
		case "deposit":	transaction.setMobileno(mobileno);
						transaction.setDeposit(amount);
						transaction.setTotal(customer.getWallet().getBalance());
						break;
		case "withdraw": transaction.setMobileno(mobileno);
						 transaction.setWithdraw(amount);
						 transaction.setTotal(customer.getWallet().getBalance());
						 break;
		}
				
		return transaction;
	}
}
