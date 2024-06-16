package com.bambafinetech.secuejb.metier;

import java.util.List;

import com.bambafinetech.secuejb.entites.CompteBank;
import com.bambafinetech.secuejb.entites.User;

import jakarta.ejb.Local;

@Local
public interface ISecuritySession {
	
	public boolean log(String username, String password);

	public void login(User user);

	public CompteBank addCompte(CompteBank cp);

	public CompteBank getCompte(Long code);

	public List<CompteBank> listeComptes();

	public void verser(Long code, double mtn);

	public void retirer(Long code, double mtn);

	public void virement(Long cp1, Long cp2, double mtn);

}
