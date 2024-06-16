package com.bambafinetech.secuejb.entites;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "User")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	
	@OneToOne(mappedBy = "user")
	private CompteBank compteBank;
	
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return " " + username ;
	}

	public CompteBank getCompteBank() {
		return compteBank;
	}

	public void setCompteBank(CompteBank compteBank) {
		this.compteBank = compteBank;
	}
	
	
	
	
}
