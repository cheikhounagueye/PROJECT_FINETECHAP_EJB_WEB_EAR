package com.bambafinetech.secuejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Entity implementation class for Entity: CompteBank
 *
 */
@Entity(name = "compte")
public class CompteBank implements Serializable {
	// private static final Logger logger =
	// LoggerFactory.getLogger(CompteBank.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	private double montant;

	private Date dateCreation;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private static final long serialVersionUID = 1L;

	public CompteBank() {
		super();
		// logger.info("création compte CompteBank code est {} : , {} : , {} :", code,
		// montant, dateCreation);
	}

	public CompteBank(double montant, Date dateCreation, User user) {
		super();
		this.montant = montant;
		this.dateCreation = dateCreation;
		this.user = user;
	}

	public Long getCode() {
		return this.code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CompteBank [montant=" + montant + ", dateCreation=" + dateCreation + ", user=" + user + "]";
	}
	
	
	
	
}
