package de.wild.web.bean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.ejb.interfaces.MessageSender;
import de.wild.persistence.entities.Customer;

@ApplicationScoped
public class MailSender {
	
	@EJB
	private MessageSender messageSender; 
	
	@EJB
	private CustomerDAO customerDAO;
	
	@Resource(name = "DefaultManagedExecutorService")
	private ManagedExecutorService executor;
	
	public void sendMail(final String message, final String subject, final int recipientId){
		
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				//Nachrichtenversand
				Customer recipient = customerDAO.getCustomer(recipientId);
				Future<Boolean> result = messageSender.send(subject, message, recipient);
				
				try {
					result.get();
				} catch (InterruptedException |ExecutionException e) {
					e.printStackTrace();
				} 
			}
		};
		
		executor.submit(task);
		
	}

}
