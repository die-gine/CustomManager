package de.wild.web.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Customer;

//Rest-Service
@Path("/customers")
@Stateless //damit Dependency Injection funktioniert
public class CustomersEndpoint {
	
	@EJB
	private CustomerDAO customerDAO;
	
	//gibt Liste aller Kunden im Format JSON zurück
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAll(){
		return customerDAO.getAllCustomers();
	}

}
