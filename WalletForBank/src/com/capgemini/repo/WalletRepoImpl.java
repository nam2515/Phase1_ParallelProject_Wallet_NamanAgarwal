package com.capgemini.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;

public class WalletRepoImpl implements WalletRepo {
	Customer customer=new Customer();
	HashMap<String, Customer> map=new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.capgemini.repo.WalletRepo#save(com.capgemini.beans.Customer)
	 */
	@Override
	public boolean save(Customer customer) {
		if(map.put(customer.getMobileno(), customer)!=null)
			return true;
		return false;
				
	}

	/* (non-Javadoc)
	 * @see com.capgemini.repo.WalletRepo#findOne(java.lang.String)
	 */
	@Override
	public Customer findOne(String mobileno) {
		// TODO Auto-generated method stub
		if(map.containsKey(mobileno))
			return map.get(mobileno);	
		return null;
	}

	@Override
	public boolean saveTransaction(Transaction transaction, Customer customer) {
		List<Transaction> list=customer.getList();
		list.add(transaction);
		customer.setList(list);
		return true;
	}

	@Override
	public List<Transaction> findTransaction(String mobileno) {
		List<Transaction> list = new ArrayList<>();
		Customer customer = new Customer();
		customer = map.get(mobileno);
		list=customer.getList();
		Collections.reverse(list);
		List<Transaction>l=new ArrayList<>();
		if(list.size()<10)
		{
			return list;
		}
		else
		{
			for(int i=0;i<10;i++)
			{
				l.add(customer.getList().get(i));
			}
			Collections.reverse(l);
			return l;
		}
	}
}
