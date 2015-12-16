package de.wild.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Address;
import de.wild.persistence.entities.Communication;
import de.wild.persistence.entities.Customer;

/*
 * Lebenszyklus eines Customers abgebilden.
 */
@ViewScoped
@Named("customers")
public class CustomerBean implements Serializable{
	
	public static final String OUTCOME_INDEX = "/index?faces-redirect=true";
	
	public static final String OUTCOME_UPDATED ="/details?faces-redirect=true&amp;id=%s";
	
	public static final String OUTCOME_DELETED = "/index?faces-redirect=true";
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CustomerDAO customerDAO;
	
	private Customer customer; 
	
	private int customerId;
	
	//Repräsentationen Frontend-Beans
	private List<AddressBean> addresses = new ArrayList<AddressBean>();
	
	private List<CommunicationBean> coms = new ArrayList<CommunicationBean>();
	
	public void initCustomer(){
		
		if(null!= customer){
			return; 
		}
		if(customerId > 0){
			customer = customerDAO.getCustomer(customerId);
		}
		if(customer == null){
			customer = new Customer();
		}
		
		addresses.clear();
		for(Address address : customer.getAddresses()){
			addresses.add(new AddressBean(address));
		}
		
		coms.clear();
		for(Communication com : customer.getCommunication()){
			coms.add(new CommunicationBean(com));
		}
	}
	
	private void prepareCustomer(){
		
		customer.getAddresses().clear();
		
		for(AddressBean avm : addresses){
			customer.getAddresses().add(avm.getAddress());
		}
		
		customer.getCommunication().clear();
		
		for(CommunicationBean cvm : coms){
			customer.getCommunication().add(cvm.getCom());
		}
	}
	
	public String create(){
		prepareCustomer();
		
		customerDAO.create(customer);
		
		return OUTCOME_INDEX;
	}
	
	public String update(){
		prepareCustomer();
		
		customerDAO.update(customer);
		
		return String.format(OUTCOME_UPDATED, customerId);
		
	}
	
	public String delete(){
		customerDAO.remove(customer.getId());
		return OUTCOME_DELETED;
	}
	
	public void addAddress(){
		AddressBean avm = new AddressBean(new Address());
		avm.setEditing(true);
		
		addresses.add(avm);
	}
	
	public void addCommunication(){
		CommunicationBean cvm = new CommunicationBean(new Communication());
		cvm.setEditing(true);
		
		coms.add(cvm);
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<AddressBean> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressBean> addresses) {
		this.addresses = addresses;
	}

	public List<CommunicationBean> getComs() {
		return coms;
	}

	public void setComs(List<CommunicationBean> coms) {
		this.coms = coms;
	}

	public void removeAddress(AddressBean tobeRemoved){
		addresses.remove(tobeRemoved);
	}
	public void removeCommunication(CommunicationBean tobeRemoved){
		coms.remove(tobeRemoved);
	}
	
	public String getBirthdayFormatted(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
		return dataFormat.format(customer.getBirthday());
	}

	public String getRelationship(){
		
		if(null == customer.getRealtionship()){
			return "Unbekannt";			
		}
		switch(customer.getRealtionship()){
		case Colleague: return "Kollege";
		case Friend: return "Freund";
		case Family: return "Familie"; 
		case Job: return "Arbeit";
		default: return "Unbekannt";
		
		}
	}
}
