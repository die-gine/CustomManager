package de.wild.web.bean;

import java.io.Serializable;

import de.wild.persistence.entities.Communication;
import de.wild.persistence.enums.Kind;

public class CommunicationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Communication com; 
	
	private boolean isEditing; 
	
	public CommunicationBean(){}
	
	public CommunicationBean(Communication com){
		this.setCom(com); 
	}

	public Communication getCom() {
		return com;
	}

	public void setCom(Communication com) {
		this.com = com;
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}
	
	public String getCommunicationKind(){
		if(com.getKind() == null || com.getKind() == Kind.Unknown){
			return "Unbekannt";
		}
		if(com.getKind() == Kind.Business){
			return "Geschäftlich";
		}
		return "Privat";
	}
	
	public String getCommunicationType(){
		if(com.getCommunicationType() == null){
			return "Nicht angegeben";
		}
		switch (com.getCommunicationType()) {
		case Email:return "E-Mail";
		case Fax:return "Fax";
		case Mobile: return "Mobile";
		case Telephone: return "Festnetz";
		case Web: return "Web";
		default: return "Unbekannt";
		
		}
	}

	public void toggleEditing(){
		isEditing = !isEditing;
	}
}
