package de.wild.web.bean;

import java.io.Serializable;

import de.wild.persistence.entities.Address;
import de.wild.persistence.enums.Kind;

public class AddressBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Address address; 
	
	private boolean isEditing = false; 
	
	public AddressBean(){}
	
	public AddressBean(Address address){
		this.setAddress(address);
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getAddressKind(){
		if(address.getKind() == null || address.getKind() == Kind.Unknown){
			return "Unbekannt";
		}
		if(address.getKind() == Kind.Business){
			return "Geschäftsadresse";
		}
		return "Privatadresse";
		
	}
	
	public void toggleEditing(){
		isEditing = !isEditing;
	}

}
