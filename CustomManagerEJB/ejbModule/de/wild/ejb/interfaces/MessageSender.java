package de.wild.ejb.interfaces;

import java.util.concurrent.Future;

import de.wild.persistence.entities.Customer;

public interface MessageSender {
	
	//asynchrone Methode -> Future
	public Future<Boolean> send(String subject, String body, Customer customer);

}
