package com.bambafinetech.secuejb.metier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bambafinetech.secuejb.entites.CompteBank;
import com.bambafinetech.secuejb.entites.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class SecuritySession implements ISecuritySession {
	@PersistenceContext(unitName = "bambaEJB")
	private EntityManager em;

	private static final Logger logger = LoggerFactory.getLogger(SecuritySession.class);

	@Override
	public boolean log(String username, String password) {
		
		return (!username.isBlank() || !password.isBlank()) && verifyLogin(username, password);
	}

	private boolean verifyLogin(String username, String password) {
		return (username.equals("bootcamp") && password.equals("passer123@"));
	}

	@Override
	public CompteBank addCompte(CompteBank cp) {
		em.persist(cp);
		logger.info("compte bancaire ajouté {} ", cp.toString());
		return cp;
	}

	@Override
	public CompteBank getCompte(Long code) {
		CompteBank cp = em.find(CompteBank.class, code);
		if (cp == null) {
			logger.info("le compte est : {} ", cp.toString());
		}
		return cp;
	}

	@Override
	public List<CompteBank> listeComptes() {
		Query req = em.createQuery("select c from compte c");
		logger.info("ma liste de compte");
		return req.getResultList();
	}

	@Override
	public void verser(Long code, double mtn) {
		logger.info("Versement de {} sur le compte avec le code: {}", mtn, code);
		CompteBank cp = getCompte(code);
		cp.setMontant(cp.getMontant() + mtn);

	}

	@Override
	public void retirer(Long code, double mtn) {
		CompteBank cp = getCompte(code);
		if (cp.getMontant() < mtn)
			throw new RuntimeException("Solde insuffisant!");
		cp.setMontant(cp.getMontant() - mtn);
	}

	@Override
	public void virement(Long cp1, Long cp2, double mtn) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			retirer(cp1, mtn);
			verser(cp2, mtn);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void login(User user) {
	    try {
	        em.persist(user);
	        logger.info("Insertion réussie pour l'utilisateur: {} {}", user);
	    } catch (Exception e) {
	        logger.error("Échec de l'insertion pour l'utilisateur: {}. Erreur: {}", user, e.getMessage());
	    }
	}


}
