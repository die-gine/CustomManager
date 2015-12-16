package de.wild.ejb.beans;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Address;
import de.wild.persistence.entities.Communication;
import de.wild.persistence.entities.Customer;
import de.wild.persistence.enums.CommunicationType;
import de.wild.persistence.enums.Gender;
import de.wild.persistence.enums.Kind;

@Singleton
@Startup
public class InitializationBean {
	
	@EJB
	private CustomerDAO customerDAO;
	
	@PostConstruct
	private void initialize(){
		if(customerDAO.getAllCustomers().size()==0){
			Customer customer = new Customer();
			customer.setFirstName("Ina");
			customer.setLastName("Mueller");
			customer.setGender(Gender.Female);
			
			Calendar birthday = Calendar.getInstance();
			customer.setBirthday(birthday.getTime());
			
			//adresse hinzufügen
			Address address = new Address();
			address.setCity("Berlin");
			address.setCountry("D");
			address.setStreet("Musterstrasse");
			address.setZip("12345");
			address.setKind(Kind.Business);
			customer.getAddresses().add(address);
			
			//Email adresse hinzufügen
			Communication com = new Communication();
			com.setName("Geschäft");
			com.setValue("regina.wild@init.de");
			com.setCommunicationType(CommunicationType.Email);
			com.setKind(Kind.Business);
			customer.getCommunication().add(com);
			
			customerDAO.create(customer);
			
		}
	}

}
