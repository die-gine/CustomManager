package de.wild.web.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Customer;

@RequestScoped
@Named("mailSender")
public class SendMailBean {

	private static final String OUTCOME_DETAILS = "/pages/deatils?faces-redirect=true&amp;id=%s";
	
	@Inject
	private MailSender mailSender; 
	
	@EJB
	private CustomerDAO customerDAO;
	
	private int customerId;
	
	private Customer customer; 
	
	private String subject; 
	
	private String message; 
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void initialize(){
		
		if(customerId>0){
			customer = customerDAO.getCustomer(customerId);
		}
		
		if(customer == null){
			customer = new Customer();
		}
	}
	
	public String send(){
		mailSender.sendMail(message, subject, customerId);
		String response = String.format(OUTCOME_DETAILS, customerId);
		
		return response; 
	}
	
}
