package com.finetechWeb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bambafinetech.secuejb.entites.CompteBank;
import com.bambafinetech.secuejb.entites.User;
import com.bambafinetech.secuejb.metier.ISecuritySession;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "addAccount", urlPatterns = { "/addAccount" })
public class AddAccountServlet extends HttpServlet {

	@EJB
	private ISecuritySession metier;
	private static final long serialVersionUID = 1L;
	private List<CompteBank> cp;
	private static final Logger logger = LoggerFactory.getLogger(AddAccountServlet.class);

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		cp = metier.listeComptes();
		logger.info("MY LOGS LISTES COMPTE {} ", cp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listCompte", cp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double montant = 0.0;
		try {
			if (req.getParameter("montant") != null && !req.getParameter("montant").isBlank()) {
				montant = Double.parseDouble(req.getParameter("montant"));
			}
		} catch (NumberFormatException e) {
			throw new ServletException("Montant invalide", e);
		}

		String datecreationStr = req.getParameter("datecreation");
		Date datecreation = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			if (datecreationStr != null && !datecreationStr.isBlank()) {
				datecreation = dateFormat.parse(datecreationStr);
			}
		} catch (Exception e) {
			throw new ServletException("Date de création invalide", e);
		}

		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			throw new ServletException("Utilisateur non connecté");
		}

		CompteBank cp = new CompteBank(montant, datecreation, user);
		List<CompteBank> comptes = metier.listeComptes();
		for (CompteBank compteBank : comptes) {
			System.out.println(compteBank);
		}
		metier.addCompte(cp);
		req.setAttribute("comptes", comptes);
		req.getRequestDispatcher("welcome.jsp").forward(req, resp);

	}
}
