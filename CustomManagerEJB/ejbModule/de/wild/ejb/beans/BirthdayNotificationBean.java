package de.wild.ejb.beans;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Customer;

//MessageQueue die mit allen Nutzern gefüttert werden die heute Geburtstag haben
@Singleton
@Startup
@JMSDestinationDefinitions(
		{
			@JMSDestinationDefinition(
					name="java:global/jms/birthdayMailQueue",
					interfaceName="javax.jms.Queue")
		}
		)
public class BirthdayNotificationBean {
	
	@EJB
	private CustomerDAO customerDAO;
	
	@Inject
	private JMSContext context;
	
	@Resource(mappedName ="java:global/jms/birthdayMailQueue")
	private Destination birthdayDestination;
	
	@Schedule(minute="*")
	private void checkBirthday(){
		List<Customer> birthdays = customerDAO.getCustomersHavingBirthday();
		
		for(Customer customer : birthdays){
			context.createProducer().send(birthdayDestination, customer);
		}
	}
}
