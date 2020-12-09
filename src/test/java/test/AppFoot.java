package test;

import java.time.LocalDate;
import java.util.Scanner;

import Config.Context;
import model.*;

public class AppFoot {
	private static Panier panier = new Panier();
	
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
		System.out.println("\nBienvenue sur le site de Foot FC");
		System.out.println("Choisir un menu :");
		System.out.println("1 - Connection");
		System.out.println("2 - Visiter le site");
		System.out.println("3 - Créer un Compte");
		System.out.println("4 - Sortir");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:menuLogin();break;
		case 2:visiteur();break;
		case 3:creeCompte();break;
		case 4:System.exit(0);break;
		default: menuPrincipal(); break;
		}

		menuPrincipal();
	}

	private static void creeCompte() {

		System.out.println("Pour créer votre compte merci d'indiquer les informations suivantes: ");

		String login = null;
		do {
			login= saisieString("Entrez votre login");
		} while (Context.getInstance().getDaoCompte().SelectByLogin(login) != null);
		String password= saisieString("Entrez votre password");
		
		String nom= saisieString("Votre nom:");
		String prenom= saisieString("Votre prenom:");
		double solde= saisieDouble("Votre solde");
		
		System.out.println("Entrez maintenant les données de votre adresse: ");
		Integer num_voie= saisieInt("Numero de voie");
		String voie= saisieString("Nom de la voie");
		String code_postal= saisieString("Code postal");
		String ville= saisieString("Ville");

		Adresse adresse = new Adresse(num_voie, voie, code_postal,ville);

		Context.getInstance().getDaoCompte().ajouter(new Adherent(nom, prenom, login, password, solde, adresse));

		System.out.println("Votre compte a été crée!");
	}



	//Menus visiteurs
	private static void visiteur() {
		System.out.println("Choisir un menu :");
		System.out.println("1 - Consulter les evenements");
		System.out.println("2 - Consulter les articles");
		System.out.println("3 - Consulter mon panier");
		System.out.println("4 - Retour à l'accueil");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:consulterEvenemets();break;
		case 2:consulterArticles();break;
		case 3:consulterPanier();break;
		case 4:menuPrincipal();break;
		default: visiteur(); break;
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
		
		String choix = saisieString("Voulez-vous choisir un article ?");
		if(choix.equals("Y")) {
			int num_article = saisieInt("Entrez le numero de l'article");
			Article article = Context.getInstance().getDaoArticle().selectById(num_article);
			System.out.println(article);
			String choix_article = saisieString("Voulez-vous ajouter l'article au panier ?");
			if (choix_article.equals("Y")) {
				Integer qte = saisieInt("Ajoutez votre quantite");
				panier.ajouterPanier(article,qte);
			}
		}
	}

	private static void consulterPanier() {
		System.out.println(panier.getPanier());
		
		System.out.println("Voulez-vous:");
		System.out.println("1 - Editer le panier");
		System.out.println("2 - Payer");
		System.out.println("3 - Continuer vos achats");
		
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:editPanier();break;
		case 2:payer();break;
		case 3:consulterArticles();break;
		default: consulterPanier(); break;
		}
		
		consulterPanier();
		
		//TODO: function editerPanier
		//TODO: Fonction Payment
	}

	private static void editPanier() {
		System.out.println(panier.getPanier());
		
		int choix = saisieInt("Choisir l'article à modifier:");
		
	}

	private static void payer() {
		// TODO Auto-generated method stub
		
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
			menuAdherent((Adherent) connected);
		}
	}

	//Menu Admin
	private static void menuAdmin(Compte connected) {
		System.out.println("1 - Gestion des comptes");
		System.out.println("2 - Gestion des evenementes");
		System.out.println("3 - Gestion des articles");
		System.out.println("4 - Gestion des tickets");
		System.out.println("5 - Deconnection");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:editerCompte(connected);break;
		case 2:editerEvenemets(connected);break;
		case 3:editerArticles(connected);break;
		case 4:editerTickets(connected);break;
		case 5:menuPrincipal();;break;
		default: menuAdmin(connected); break;
		}

		menuAdmin(connected);
	}

	//Manipulation Comptes
	private static void editerCompte(Compte connected) {
		System.out.println("1 - Afficher les comptes");
		System.out.println("2 - Modifier un compte");
		System.out.println("3 - Supprimer un compte");
		System.out.println("4 - Retour");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:afficherComptes();break;
		case 2:modifierCompte();break;
		case 3:supprimerCompte();break;
		case 4:menuAdmin(connected);break;
		default: editerCompte(connected); break;
		}	

		editerCompte(connected);
	}

	private static void afficherComptes() {
		System.out.println("\nListe des Comptes : \n");
		for(Compte c : Context.getInstance().getDaoCompte().selectAll()) 
		{
			System.out.println(c);
		}	
	}

	private static void modifierCompte() {
		afficherComptes();

		int choix=saisieInt("Choisir un compte");

		Adherent c = (Adherent) Context.getInstance().getDaoCompte().selectById(choix);

		String login = null;
		do {
			login= saisieString("Entrez le login:");
		} while (Context.get_instance().getDaoCompte().SelectByLogin(login) != null);
		
		String nom= saisieString("Entrez le nom:");
		String prenom= saisieString("Entrez le prenom:");

		String password= saisieString("Entrez le password:");
		double solde= saisieDouble("Entrez le solde:");
		
		System.out.println("Entrez maintenant les données de votre adresse: ");
		Integer num_voie= saisieInt("Numero de la voie:");
		String voie= saisieString("Nom de la voie");
		String code_postal= saisieString("Code postal");
		String ville= saisieString("Ville");

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
		afficherComptes();	
		int choix=saisieInt("Choisir un compte");

		Context.getInstance().getDaoCompte().supprimer(choix);

		System.out.println("Le compte a été supprimé !!");

	}

	//Manipulation Evenement
	private static void editerEvenemets(Compte connected) {
		System.out.println("1 - Afficher les evenements");
		System.out.println("2 - Ajouter un evenement");
		System.out.println("3 - Modifier un evenement");
		System.out.println("4 - Supprimer un evenement");
		System.out.println("5 - Retour");
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
		System.out.println("Créer Evenement ");

		String d= saisieString("Entrez la date de l'evenement");
		LocalDate date = LocalDate.parse(d);

		String titre= saisieString("Entrez un titre");
		String description= saisieString("Entrez une description de l'evenement");


		Evenement ev = new Evenement(date, titre, description);


		Context.getInstance().getDaoEvenement().ajouter(ev);

		System.out.println("L'evenement a été crée!");
		menuAdmin(connected);	
	}

	private static void modifierEvenemets() {

		int choix=saisieInt("Choisir un evenement");

		Evenement ev = Context.getInstance().getDaoEvenement().selectById(choix);	

		String d= saisieString("Entrez la date de l'evenement");
		LocalDate date = LocalDate.parse(d);

		String titre= saisieString("Entrez un titre");
		String description= saisieString("Entrez une description de l'evenement");

		ev.setDate(date);
		ev.setTitre(titre);
		ev.setDescription(description);

		Context.getInstance().getDaoEvenement().modifier(ev);
	}

	private static void supprimerEvenemets() {
		consulterEvenemets();

		int choix=saisieInt("Choisir un compte");

		Context.getInstance().getDaoEvenement().supprimer(choix);

		System.out.println("L'evenement a été supprimé !!");

	}

	//Manipulation Articles
	private static void editerArticles(Compte connected) {
		System.out.println("1 - Afficher les articles");
		System.out.println("2 - Ajouter un nouvel article");
		System.out.println("3 - Modifier un article");
		System.out.println("4 - Supprimer un article");
		System.out.println("5 - Retour");
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
		System.out.println("Creer un article ");

		String nom= saisieString("Entrez le nom");
		double prix = saisieDouble("Entrez le prix");
		Integer quantite = saisieInt("Entrez une quantitee");
		String taille= saisieString("Entrez une taille");
		String description= saisieString("Entrez la description");

		Article a = new Article(nom, prix, quantite, taille, description);

		Context.getInstance().getDaoArticle().ajouter(a);

		System.out.println("L'article a été crée!");
		menuAdmin(connected);	
	}

	private static void modifierArticles() {
		int choix=saisieInt("Choisir un article");

		Article a = Context.getInstance().getDaoArticle().selectById(choix);	

		String nom= saisieString("Modifier le nom");
		double prix = saisieDouble("Modifier le prix");
		Integer quantite = saisieInt("Modifier une quantitee");
		String taille= saisieString("Modifier la taille");
		String description= saisieString("Modifier la description");

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
		System.out.println("1 - Afficher les tickets");
		System.out.println("2 - Ajouter des tickets");
		System.out.println("3 - Modifier des tickets");
		System.out.println("4 - Supprimer des tickets");
		System.out.println("5 - Retour");
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
		System.out.println("Creer des tickets ");

		double prix = saisieDouble("Entrez le prix");
		Integer quantite = saisieInt("Entrez un qantite");

		String d= saisieString("Entrez une date");
		LocalDate date = LocalDate.parse(d);

		String lieu= saisieString("Entrez un lieu");


		Ticket t = new Ticket(prix, quantite, date, lieu);


		Context.getInstance().getDaoTicket().ajouter(t);

		System.out.println("Les tickets ont été crées!");
		menuAdmin(connected);		
	}

	private static void modifierTickets() {
		int choix=saisieInt("Choisir un evenement");

		Ticket t = Context.getInstance().getDaoTicket().selectById(choix);	

		double prix = saisieDouble("Modifier le prix");
		Integer quantite = saisieInt("Modifier la quantite");

		String d= saisieString("Modifier la date");
		LocalDate date = LocalDate.parse(d);

		String lieu= saisieString("Modifier le lieu");

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

		System.out.println("Les tickets ont été supprimés!!");	

	}

	//Menu Client
	private static void menuAdherent(Adherent connected) {

		System.out.println("Choisir un menu :");
		System.out.println("1 - Consulter les evenements");
		System.out.println("2 - Consulter les articles");
		System.out.println("3 - Consulter les paries");
		System.out.println("4 - Consulter les tickets");
		System.out.println("5 - Consulter mon panier");
		System.out.println("6 - Modifier mon compte");
		System.out.println("7 - Deconnexion");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:consulterEvenemets();break;
		case 2:consulterArticles();break;
		case 3:consulterParies(connected);break;
		case 4:afficherTickets();break;
		case 5:consulterPanier();break;
		//TODO:modifCompte
		case 6:modifCompte();break;
		case 7:menuPrincipal();;break;
		default: menuAdherent(connected); break;
		}

		menuAdherent(connected);
	}


	//Paries
	private static void consulterParies(Adherent connected) {
		afficherParie();

		System.out.println("Voulez vous prendre un pari? [Y/N]");

		String choix = saisieString("");
		switch(choix) 
		{
		case "Y":prisePari();break;
		case "N":menuAdherent(connected);break;
		default : consulterParies(connected);break;
		}

		//TODO:function prise de parie
		//TODO: function ajouter Panier
	}
	
	private static void prisePari() {
		//TODO: Selectioner pari
		//TODO: ajouter au panier
	}

	private static void afficherParie() {
		// TODO: afficher les paris		
	}

	public static void main(String[] args) {
		menuPrincipal();
	}
}

