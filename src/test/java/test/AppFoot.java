package test;



import java.time.LocalDate;
import java.util.Scanner;

import Config.Context;
import model.*;





public class AppFoot {

	public static int saisieInt(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static double saisieDouble(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextDouble();
	}


	public static String saisieString(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}


	private static void menuPrincipal() {
		System.out.println("\nFoot FC");
		System.out.println("Choisir un menu :");
		System.out.println("1 - Login");
		System.out.println("2 - Visiteur");
		System.out.println("3 - Cree un Compte");
		System.out.println("4 - Exit AppFoot");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:menuLogin();break;
		case 2:visiteur();break;
		case 3:creeCompte();break;
		case 4:System.exit(0);break;
		}

		menuPrincipal();
	}

	private static void creeCompte() {

		System.out.println("Pour cr�er votre compte merci d'indiquer les informations suivantes ");

		String nom= saisieString("nom");
		String prenom= saisieString("prenom");

		String login = null;

		do {
			login= saisieString("login");
		} while (Context.getInstance().getDaoCompte().SelectByLogin(login) != null);



		String password= saisieString("password");
		double solde= saisieDouble("solde");
		Integer num_voie= saisieInt("num_voie");
		String voie= saisieString("voie");
		String code_postal= saisieString("code_postal");
		String ville= saisieString("ville");

		Adresse adresse = new Adresse(num_voie, voie, code_postal,ville);
		Adherent adh = new Adherent(nom, prenom, login, password, solde, adresse);

		Context.getInstance().getDaoCompte().ajouter(adh);

		System.out.println("Compte cree!");
		menuPrincipal();

	}



	//Menus visiteurs
	private static void visiteur() {
		System.out.println("Choisir un menu :");
		System.out.println("1 - Consulter Evenements");
		System.out.println("2 - Consulter Articles");
		System.out.println("3 - Consulter Panier");
		System.out.println("4 - Exit AppFoot");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:consulterEvenemets();break;
		case 2:consulterArticles();break;
		case 3:consulterPanier();break;
		case 4:System.exit(0);break;
		}

		visiteur();
	}

	private static void consulterEvenemets() {
		System.out.println("\nListe des Evenement : \n");
		for(Evenement e : Context.getInstance().getDaoEvenement().selectAll()) 
		{
			System.out.println(e);
		}	
	}

	private static void consulterArticles() {
		System.out.println("\nListe des Articles : \n");
		for(Article a : Context.getInstance().getDaoArticle().selectAll()) 
		{
			System.out.println(a);
		}	
	}

	private static void consulterPanier() {
		//function afficher panier 
		//function editerPanier
		//Payment

	}

	//Connection
	private static void menuLogin() {

		String login=saisieString("Saisir votre login");
		String password=saisieString("Saisir votre password");

		Compte connected=Context.getInstance().getDaoCompte().SelectByLoginMdp(login, password); 

		if(connected==null) 
		{
			System.out.println("Mauvais identifiants");
			menuPrincipal();
		}
		else if(connected instanceof Admin) 
		{
			menuAdmin((Admin) connected);
		}
		else if(connected instanceof Adherent)
		{
			menuClient((Adherent) connected);
		}

	}

	//Menu Admin
	private static void menuAdmin(Compte connected) {
		System.out.println("1 - Editer Compte");
		System.out.println("2 - Editer Evenementes");
		System.out.println("3 - Editer Articles");
		System.out.println("4 - Editer Tickets");
		System.out.println("5 - Exit AppFoot");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:editerCompte(connected);break;
		case 2:editerEvenemets(connected);break;
		case 3:editerArticles(connected);break;
		case 4:editerTickets(connected);break;
		case 5:System.exit(0);break;
		default: menuAdmin(connected); break;
		}

		menuAdmin(connected);
	}

	//Manipulation Comptes
	private static void editerCompte(Compte connected) {
		System.out.println("1 - Afficher Compte");
		System.out.println("2 - Modifier Compte");
		System.out.println("3 - Supprimer Compte");
		System.out.println("4 - Return");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:afficherCompte();break;
		case 2:modifierCompte();break;
		case 3:supprimerCompte();break;
		case 4:menuAdmin(connected);break;

		default: editerCompte(connected); break;
		}	

		editerCompte(connected);
	}

	private static void afficherCompte() {
		System.out.println("\nListe des Comptes : \n");
		for(Compte c : Context.getInstance().getDaoCompte().selectAll()) 
		{
			System.out.println(c);
		}	
	}

	private static void modifierCompte() {
		afficherCompte();

		int choix=saisieInt("Choisir un compte");

		Adherent c = (Adherent) Context.getInstance().getDaoCompte().selectById(choix);


		String nom= saisieString("nom");
		String prenom= saisieString("prenom");

		String login = null;

		do {
			login= saisieString("login");
		} while (Context.get_instance().getDaoCompte().SelectByLogin(login) != null);



		String password= saisieString("password");
		double solde= saisieDouble("solde");
		Integer num_voie= saisieInt("num_voie");
		String voie= saisieString("voie");
		String code_postal= saisieString("code_postal");
		String ville= saisieString("ville");

		c.setNom(nom);
		c.setPrenom(prenom);
		c.setLogin(login);
		c.setPassword(password);
		c.setSolde(solde);
		c.getAdresse().setNum_voie(num_voie);
		c.getAdresse().setVoie(voie);
		c.getAdresse().setCode_postal(code_postal);
		c.getAdresse().setVille(ville);



		Context.getInstance().getDaoCompte().modifier(c);



	}

	private static void supprimerCompte() {
		afficherCompte();	
		int choix=saisieInt("Choisir un compte");


		Context.getInstance().getDaoCompte().supprimer(choix);

		System.out.println("Compte supprimer!!");

	}

	//Manipulation Evenement
	private static void editerEvenemets(Compte connected) {
		System.out.println("1 - Afficher Evenemets");
		System.out.println("2 - Ajouter un Evenemet");
		System.out.println("3 - Modifier Evenemets");
		System.out.println("4 - Supprimer Evenemets");
		System.out.println("5 - Return");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:consulterEvenemets();break;
		case 2:ajouterEvenemets(connected);break;
		case 3:modifierEvenemets();break;
		case 4:supprimerEvenemets();break;
		case 5:menuAdmin(connected);break;

		default: editerEvenemets(connected); break;
		}		

		editerEvenemets(connected);
	}

	private static void ajouterEvenemets(Compte connected) {
		System.out.println("Creer Evenement ");

		String d= saisieString("Date");
		LocalDate date = LocalDate.parse(d);

		String titre= saisieString("Titre");
		String description= saisieString("Description");


		Evenement ev = new Evenement(date, titre, description);


		Context.getInstance().getDaoEvenement().ajouter(ev);

		System.out.println("Evenement cree!");
		menuAdmin(connected);	
	}

	private static void modifierEvenemets() {

		int choix=saisieInt("Choisir un evenement");

		Evenement ev = Context.getInstance().getDaoEvenement().selectById(choix);	

		String d= saisieString("Date");
		LocalDate date = LocalDate.parse(d);

		String titre= saisieString("Titre");
		String description= saisieString("Description");

		ev.setDate(date);
		ev.setTitre(titre);
		ev.setDescription(description);

		Context.getInstance().getDaoEvenement().modifier(ev);



	}

	private static void supprimerEvenemets() {
		consulterEvenemets();

		int choix=saisieInt("Choisir un compte");

		Context.getInstance().getDaoEvenement().supprimer(choix);

		System.out.println("Evenement supprimer!!");

	}

	//Manipulation Articles
	private static void editerArticles(Compte connected) {
		System.out.println("1 - Afficher Articles");
		System.out.println("2 - Ajouter un nouveau Article");
		System.out.println("3 - Modifier Articles");
		System.out.println("4 - Supprimer Articles");
		System.out.println("5 - Return");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:afficherArticles();break;
		case 2:ajouterArticles(connected);break;
		case 3:modifierArticles();break;
		case 4:supprimerArticles();break;
		case 5:menuAdmin(connected);break;

		default: editerArticles(connected); break;
		}			
		editerArticles(connected);
	}

	private static void afficherArticles() {
		System.out.println("\nListe des Articles : \n");
		for(Article a : Context.getInstance().getDaoArticle().selectAll()) 
		{
			System.out.println(a);
		}	
	}	

	private static void ajouterArticles(Compte connected) {
		System.out.println("Creer Article ");

		String nom= saisieString("nom");
		double prix = saisieDouble("prix");
		Integer quantite = saisieInt("Quantite");
		String taille= saisieString("taille");
		String description= saisieString("description");

		Article a = new Article(nom, prix, quantite, taille, description);

		Context.getInstance().getDaoArticle().ajouter(a);

		System.out.println("Article cree!");
		menuAdmin(connected);	
	}

	private static void modifierArticles() {
		int choix=saisieInt("Choisir un article");

		Article a = Context.getInstance().getDaoArticle().selectById(choix);	

		String nom= saisieString("nom");
		double prix = saisieDouble("prix");
		Integer quantite = saisieInt("Quantite");
		String taille= saisieString("taille");
		String description= saisieString("description");

		a.setNom(nom);
		a.setPrix(prix);
		a.setQuantite(quantite);
		a.setTaille(taille);
		a.setDescription(description);

		Context.getInstance().getDaoArticle().modifier(a);
	}

	private static void supprimerArticles() {
		afficherArticles();

		int choix=saisieInt("Choisir un article");


		Context.getInstance().getDaoArticle().supprimer(choix);

		System.out.println("Article supprimer!!");	
	}

	//Manipulation tickets
	private static void editerTickets(Compte connected) {
		System.out.println("1 - Afficher Tickets");
		System.out.println("2 - Ajouter des Tickets");
		System.out.println("3 - Modifier Tickets");
		System.out.println("4 - Supprimer Tickets");
		System.out.println("5 - Return");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:afficherTickets();break;
		case 2:ajouterTickets(connected);break;
		case 3:modifierTickets();break;
		case 4:supprimerTickets();break;
		case 5:menuAdmin(connected);break;

		default: editerTickets(connected); break;	
		}
	}

	private static void afficherTickets() {
		System.out.println("\nListe des Tickets : \n");
		for(Ticket t : Context.getInstance().getDaoTicket().selectAll()) 
		{
			System.out.println(t);
		}	
	}

	private static void ajouterTickets(Compte connected) {
		System.out.println("Creer Ticket ");

		double prix = saisieDouble("prix");
		Integer quantite = saisieInt("Quantite");

		String d= saisieString("Date");
		LocalDate date = LocalDate.parse(d);

		String lieu= saisieString("Lieu");


		Ticket t = new Ticket(prix, quantite, date, lieu);


		Context.getInstance().getDaoTicket().ajouter(t);

		System.out.println("Ticket cree!");
		menuAdmin(connected);		
	}

	private static void modifierTickets() {
		int choix=saisieInt("Choisir un evenement");

		Ticket t = Context.getInstance().getDaoTicket().selectById(choix);	

		double prix = saisieDouble("prix");
		Integer quantite = saisieInt("Quantite");

		String d= saisieString("Date");
		LocalDate date = LocalDate.parse(d);

		String lieu= saisieString("Lieu");

		t.setPrix(prix);
		t.setQuantite(quantite);
		t.setDate(date);
		t.setLieu(lieu);

		Context.getInstance().getDaoTicket().modifier(t);		
	}

	private static void supprimerTickets() {
		afficherTickets();

		int choix=saisieInt("Choisir un ticket");

		Context.getInstance().getDaoTicket().supprimer(choix);

		System.out.println("Ticket supprimer!!");	

	}

	//Menu Client
	private static void menuClient(Adherent connected) {

		System.out.println("Choisir un menu :");
		System.out.println("1 - Consulter Evenements");
		System.out.println("2 - Consulter Articles");
		System.out.println("3 - Consulter Paries");
		System.out.println("4 - Consulter Tickets");
		System.out.println("5 - Consulter Panier");
		System.out.println("6 - Exit AppFoot");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:consulterEvenemets();break;
		case 2:consulterArticles();break;
		case 3:consulterParies(connected);break;
		case 4:afficherTickets();break;
		case 5:consulterPanier();break;
		case 6:System.exit(0);break;
		default: menuClient(connected); break;
		}

		menuClient(connected);
	}


	//Paries
	private static void consulterParies(Adherent connected) {
		afficherParie();

		System.out.println("Voulez vous prendre un pari? [Y/N]");

		String choix = saisieString("");
		switch(choix) 
		{
		case "Y":prisePari();break;
		case "N":menuClient(connected);break;
		default : consulterParies(connected);break;
		}



		//function prise de parie
		//function ajouterPanier

	}
	
	private static void prisePari() {
		//Selectioner pari
		//ajouter au panier
	}

	private static void afficherParie() {
		// afficher le Planning		
	}

	public static void main(String[] args) {
		menuPrincipal();
	}
}

