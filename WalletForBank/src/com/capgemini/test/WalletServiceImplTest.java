package com.capgemini.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exceptions.ErrorDuringTransaction;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.MobileNumbeDoesNotExist;
import com.capgemini.exceptions.MobileNumberAlreadyExist;
import com.capgemini.repo.WalletRepoImpl;

import com.capgemini.services.WalletServiceImpl;

public class WalletServiceImplTest {

	WalletRepoImpl wri;
	WalletServiceImpl wsi;
	@Before
	public void setUp() throws Exception {
		new WalletServiceImpl(wri);
	}

	@Test(expected=com.capgemini.exceptions.MobileNumberAlreadyExist.class)
	public void whenTheMobileNumberEnteredAtTheTimeOfCreatingTheAccountAlreadyExist() throws MobileNumberAlreadyExist
	{
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		BigDecimal amount=new BigDecimal(500);
		wsi.createAccount("Naman", "9690095195", amount);
		wsi.createAccount("Nitin", "9690095195", amount);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalance.class)
	public void whenAtTheTimeOfFundTransferTheSourceWalletDoesNotHaveEnoughAmountToTransfer() throws MobileNumberAlreadyExist, MobileNumbeDoesNotExist, InsufficientBalance, ErrorDuringTransaction
	{
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		wsi.createAccount("Naman", "9690095195", amount);
		wsi.createAccount("Nitin", "7351117070", amount);
		wsi.fundTransfer("9690095195", "7351117070", am);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalance.class)
	public void atTheTimeOfWithdrawIfTheBalanceIsNotSufficient() throws MobileNumberAlreadyExist, MobileNumbeDoesNotExist, InsufficientBalance
	{
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		wsi.createAccount("Naman", "9690095195", amount);
		wsi.withdrawAmount("9690095195", am);
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumbeDoesNotExist.class)
	public void atTheTimeOfDepositIfMobileNumberNotFound() throws MobileNumberAlreadyExist, MobileNumbeDoesNotExist
	{
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		wsi.createAccount("Naman", "9690095195", amount);
		wsi.depositAmount("7351117070", am);
	}
	
	@Test
	public void whenEveryInformationProvidedIsCorrect() throws MobileNumberAlreadyExist, MobileNumbeDoesNotExist, InsufficientBalance
	{
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		wsi.createAccount("Naman", "9690095195", am);
		wsi.depositAmount("9690095195", amount);
		wsi.withdrawAmount("9690095195", amount);
		wsi.showBalance("9690095195");
		
	}
}
