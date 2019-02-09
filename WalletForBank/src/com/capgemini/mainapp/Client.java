package com.capgemini.mainapp;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.ErrorDuringTransaction;
import com.capgemini.exceptions.InsufficientBalance;
import com.capgemini.exceptions.MobileNumbeDoesNotExist;
import com.capgemini.exceptions.MobileNumberAlreadyExist;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;
import com.capgemini.services.WalletServiceImpl;

public class Client {
	static Customer customer=new Customer();
	static WalletRepo wri=new WalletRepoImpl();
	static WalletServiceImpl wsi=new WalletServiceImpl(wri);
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) throws MobileNumbeDoesNotExist, InsufficientBalance, MobileNumberAlreadyExist, ErrorDuringTransaction {
		// TODO Auto-generated method stub
		String mobileno, sourcemobileno, destinationmobileno;
		String name; 
		BigDecimal amount;
		int ch;
		
		while(true)
		{
			System.out.println("1. Create Account");
			System.out.println("2. Show Balance");
			System.out.println("3. Fund Transfer");
			System.out.println("4. Deposit Amount");
			System.out.println("5. Withdraw Amount");
			System.out.println("6. Show your Transactions");
			System.out.println("7. Exit");
			System.out.println("Enter your choice: ");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:	System.out.println("Enter the Name: ");
					sc.nextLine();
					name=sc.nextLine();
					name=validateName(name);
					System.out.println("Enter the mobile number: ");
					mobileno=sc.nextLine();
					validateMobileNumber(mobileno);
					System.out.println("Enter the amount you want to add in wallet: ");
					amount=validateAmount(sc.nextBigDecimal());
					wsi.createAccount(name, mobileno, amount);
					break;
					
			case 2:  
					System.out.println("Enter your mobile number: ");
					mobileno=sc.next();
					validateMobileNumber(mobileno);
					System.out.println(wsi.showBalance(mobileno));
					break;
					
			case 3: System.out.println("Enter the source mobile number: ");
			 		sourcemobileno=sc.next();
			 		validateMobileNumber(sourcemobileno);
			 		System.out.println("Enter the target moble number: ");
			 		destinationmobileno=sc.next();
			 		validateMobileNumber(destinationmobileno);
			 		System.out.println("Enter the amount you want to transfer: ");
			 		amount=sc.nextBigDecimal();
			 		validateAmount(amount);
			 		System.out.println(wsi.fundTransfer(sourcemobileno, destinationmobileno, amount));
			 		break;
			 		
			case 4: System.out.println("Enter the targeted mobile number: ");
			 		destinationmobileno=sc.next();
			 		validateMobileNumber(destinationmobileno);
			 		System.out.println("Enter the amount to be deposited: ");
			 		amount=sc.nextBigDecimal();
			 		validateAmount(amount);
			 		System.out.println(wsi.depositAmount(destinationmobileno, amount));
			 		break;
			 		
			case 5: System.out.println("Enter the targeted mobile number: ");
			 		destinationmobileno=sc.next();
			 		validateMobileNumber(destinationmobileno);
			 		System.out.println("Enter the amount to credit: ");
			 		amount=sc.nextBigDecimal();
			 		validateAmount(amount);
			 		System.out.println(wsi.withdrawAmount(destinationmobileno, amount));
			 		break;
			case 6: System.out.println("Enter the mobile number of which you want to print the transaction" );
			        mobileno=sc.next();
					System.out.println(wsi.printTransaction(mobileno));
			 		
			case 7: System.exit(0);
			
			default: System.out.println("You entered the wrong choice..!!");
					
			}
		}
	}
	
	private static BigDecimal validateAmount(BigDecimal amount) {
		if(amount==null)
		{
			System.out.println("Please Enter the amount..");
			validateAmount(amount);
		}
		return amount;	
	}
	private static String validateMobileNumber(String mobileno) {
		boolean flag=mobileno.matches("[0-9]+");
		if(flag==false) {
		System.out.println("Number contains characters..");
		System.out.println("Enter mobile number again");
		mobileno=sc.next();
		validateMobileNumber(mobileno);
		}
		
		if(mobileno.length()!=10)
		{
			System.out.println("Length is wrong");
			System.out.println("Enter mobile number again");
			mobileno=sc.next();
			validateMobileNumber(mobileno);
		}
		return mobileno;
	}
	private static String validateName(String name) {
		boolean flag=name.matches("[A-Z, a-z]+");
		if(flag==false || name.equals(null) || name.equals(" "))
		{
			System.out.println("Eithe the block is empty or numbers exist..");
			System.out.println("Enter name again: ");
			name=sc.nextLine();
			validateName(name);
		}
		return name;
	}


}
