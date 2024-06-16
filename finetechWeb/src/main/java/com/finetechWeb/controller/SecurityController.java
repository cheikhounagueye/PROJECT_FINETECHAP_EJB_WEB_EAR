package com.finetechWeb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bambafinetech.secuejb.entites.CompteBank;
import com.bambafinetech.secuejb.entites.User;
import com.bambafinetech.secuejb.metier.ISecuritySession;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "login", value = "/login", urlPatterns = { "*.php" })
public class SecurityController extends HttpServlet {

	@EJB
	private ISecuritySession metier;
	private static final long serialVersionUID = 1L;
	// private static final Logger logger =
	// LoggerFactory.getLogger(SecurityController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", metier.log("bootcampe", "passer123@"));
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		// resp.getWriter().print("bamba est: "+metier.log("bootcamps", "passer123@"));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginEmail = req.getParameter("logemail");
		String loginPassword = req.getParameter("logpass");
		String action = req.getParameter("action");
		String actionAddCompte = req.getParameter("ajouterCompte");

		double montant = 0.0;
		try {
			// Vérifiez que le paramètre montant n'est pas nul et n'est pas vide
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
		User user = new User(loginEmail, loginPassword);
		CompteBank cp = new CompteBank(montant, datecreation, user);
		/*
		 * System.out.println("Votre login est"+ loginEmail+" votre password "+
		 * loginPassword + " votre action est "+action); try { metier.addCompte(new
		 * CompteBank(58000.0, new Date())); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		if (action != null) {
			if (loginEmail.isBlank() && loginPassword.isBlank()) {
				throw new ServletException("login et password incorrects");
			} else {

				metier.login(user);
				// req.getRequestDispatcher("welcome.jsp").forward(req, resp);
				// Connexion réussie
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("welcome.jsp");
				System.out.println("Utilisateur connecté !");
			}

		} 
		
		if (actionAddCompte != null) {
			System.out.println("compte bien ajouté  connecté !");
			metier.addCompte(cp);
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("welcome.jsp");
			
			req.setAttribute("accountUser", cp);

		}
	}
}
