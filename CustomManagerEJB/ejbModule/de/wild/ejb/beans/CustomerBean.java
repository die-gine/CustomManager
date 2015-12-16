package de.wild.ejb.beans;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.wild.ejb.interfaces.CustomerDAO;
import de.wild.persistence.entities.Customer;
//DataLayer
@Stateless
@Remote(CustomerDAO.class)
public class CustomerBean implements CustomerDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Customer create(Customer customer) {
		em.persist(customer);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		return em.merge(customer);
	}

	@Override
	public void remove(int id) {
		Customer toDelete = getCustomer(id);
		em.remove(toDelete);
	}

	@Override
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		//Nicht benannte Abfrage; auf groﬂ und klein achten!
		//return em.createQuery("SELECT c FROM Customer c", Customer.class)
			//	.getResultList();
		
		//benannte Abfrage, in der Entity definiert; Vorteil: Abfrage an zentraler Stelle beim Customer
		return em.createNamedQuery(Customer.QUERY_GETALL, Customer.class)
				.getResultList();
	}

	@Override
	public List<Customer> getCustomersHavingBirthday() {
		Calendar todayCalendar = Calendar.getInstance();
		return em.createNamedQuery(Customer.QUERY_BIRTHDAY, Customer.class)
				.setParameter(1, todayCalendar.get(Calendar.DAY_OF_MONTH))
				.setParameter(2,todayCalendar.get(Calendar.MONTH)).getResultList();
	}

}
