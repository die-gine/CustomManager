package de.wild.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.wild.persistence.enums.CommunicationType;
import de.wild.persistence.enums.Kind;

/*
 * Mögliche Art der Kommunikation mit einem Kunden.
 */

@Entity
public class Communication implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(max=100)
	private String value; 
	
	@NotNull
	@Size(max=20)
	private String name; 
	
	//kein nummerischer Wert sondern String
	@Enumerated(EnumType.STRING)
	private CommunicationType communicationType;
	
	@Enumerated(EnumType.STRING)
	private Kind kind;
	
	@Version
	private Timestamp lastChanged; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

}
