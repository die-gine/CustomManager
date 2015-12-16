package de.wild.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.wild.persistence.enums.Gender;
import de.wild.persistence.enums.Relationship;

/*
 * Entity repräsentiert den Kunden
 */

@Entity
@NamedQuery(
		name=Customer.QUERY_GETALL,
		query="SELECT c FROM Customer c")
@NamedNativeQuery(
		name=Customer.QUERY_BIRTHDAY,
		query="SELECT * FROM Customer WHERE DAY(birthday) = ? AND MONTH(birthday) = ?",
		resultClass = Customer.class)
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String QUERY_GETALL="Customer.GetAll";
	public static final String QUERY_BIRTHDAY="Customer.GetByBirthday";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min=1, max=50)
	private String firstName;
	
	@NotNull
	@Size(min=1, max=100)
	private String lastName; 
	
	private Gender gender;
	
	private Relationship realtionship;
	
	private Date birthday;
	
	//1:n CascadeType.ALL - automatische Speicherung bei Änderungen an untergeordneten Objekt; orphanRemoval=true - alle untergeordneten Datensätze werden gelöscht
	//FetchType.EAGER schaltet Lazy Loading aus
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name ="customerid") //dbschema anpassen
	private List<Address> addresses = new ArrayList<Address>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name ="customerid")
	private List<Communication> communication = new ArrayList<Communication>();;
	
	@Version
	private Timestamp lastChanged; 
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Relationship getRealtionship() {
		return realtionship;
	}

	public void setRealtionship(Relationship realtionship) {
		this.realtionship = realtionship;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Communication> getCommunication() {
		return communication;
	}

	public void setCommunication(List<Communication> communication) {
		this.communication = communication;
	}
}
