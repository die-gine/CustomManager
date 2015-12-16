package de.wild.ejb.interfaces;

import java.util.List;

import de.wild.persistence.entities.Customer;


public interface CustomerDAO {
	
	public Customer create(Customer customer);
	
	public Customer update(Customer customer);
	
	public void remove(int id);
	
	public Customer getCustomer(int id);
	
	public List<Customer> getAllCustomers();

	public List<Customer> getCustomersHavingBirthday();

}
