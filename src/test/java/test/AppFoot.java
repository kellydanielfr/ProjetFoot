package test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Config.Context;
import model.*;

public class AppFoot {
	private static Panier panier = new Panier();
	private static Compte connected =null;
	
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

		Compte.creatAdherent(num_voie, voie, code_postal,ville,nom, prenom, login, password, solde);

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
		case 1:Evenement.showAllEvenement();break;
		case 2:consulterArticles();break;
		case 3:consulterPanier();break;
		case 4:menuPrincipal();break;
		default: visiteur(); break;
		}

		visiteur();
	}

	private static void consulterArticles() {
		Article.showAllArticle();
		
		
		String choix = saisieString("Voulez-vous effectuer un achat ?");
		if(choix.equals("Y")) {
			int num_article = saisieInt("Entrez le numero de l'article");
			Article article = Context.getInstance().getDaoArticle().selectById(num_article);
			boolean error =false;
			do{
				try {
					Integer qte = saisieInt("Entrez votre quantite");
					panier.ajouterPanier(article,qte);error=false;
				} catch (qteInsiffisante e) {
					System.out.println(e.getMessage());error=true;
				}
			}while(error);
		}
	}

	private static void consulterPanier() {
		
		Panier.showPanier(panier);
		
		
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
	}

	private static void editPanier() {
		Panier.showPanier(panier);
		
		Integer num_article = saisieInt("Choisir l'article à modifier:");
		
		System.out.println("Que voulez-vous faire:");
		System.out.println("1 - Changer la quantite");
		System.out.println("2 - Supprimer l'article");
		System.out.println("3 - Abandonner");
		
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:int qte = saisieInt("Entrez la qte"); panier.miseAJourQteProduit(num_article,qte);break;
		case 2:panier.supprimerProduit(num_article);break;
		case 3:consulterArticles();break;
		default: editPanier(); break;
		}
	}

	private static void payer() {
		System.out.println("Comment voulez-vous payer vos achats ?");
		System.out.println("1 - Carte");
		if(connected instanceof Adherent) {
			System.out.println("2 - Solde");
		}
		System.out.println("3 - Abandonner");
		
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:panier.payerCarte();break;
		case 2:panier.payerSolde(connected);break;
		case 3:consulterPanier();break;
		default: payer(); break;
		}
	}
	
	//Connection
	private static void menuLogin() {

		String login=saisieString("Saisir votre login");
		String password=saisieString("Saisir votre password");

		connected=Context.getInstance().getDaoCompte().SelectByLoginMdp(login, password); 

		if(connected==null) 
		{
			System.out.println("Mauvais identifiants");
			menuPrincipal();
		}
		else if(connected instanceof Admin) 
		{
			menuAdmin();
		}
		else if(connected instanceof Adherent)
		{
			menuAdherent();
		}
	}

	//Menu Admin
	private static void menuAdmin() {
		System.out.println("1 - Gestion des comptes");
		System.out.println("2 - Gestion des evenementes");
		System.out.println("3 - Gestion des articles");
		System.out.println("4 - Gestion des tickets");
		System.out.println("5 - Valider les paris");
		System.out.println("6 - Deconnection");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:editerCompte();break;
		case 2:editerEvenemets();break;
		case 3:editerArticles();break;
		case 4:editerTickets();break;
		case 5:validerParis();break;
		case 6:menuPrincipal();;break;
		
		default: menuAdmin(); break;
		}

		menuAdmin();
	}

	private static void validerParis() {
		for(SiteParis p : Context.getInstance().getDaoSiteParis().selectAllEndded()) 
		{
			for(Pari pari : Context.getInstance().getDaoPari().selectPari(p.getId_match(), p.getResults())) {
				//TODO: Finir
			}
		}
	}

	//Manipulation Comptes
	private static void editerCompte() {
		System.out.println("1 - Afficher les comptes");
		System.out.println("2 - Modifier un compte");
		System.out.println("3 - Supprimer un compte");
		System.out.println("4 - Retour");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:Compte.showAllCompte();break;
		case 2:
			Compte.showAllCompte();
			int choix_compte=saisieInt("Choisir un compte:");
			Compte c = Context.getInstance().getDaoCompte().selectById(choix_compte);
			modifierCompte(c);break;
		case 3:supprimerCompte();break;
		case 4:menuAdmin();break;
		default: editerCompte(); break;
		}	

		editerCompte();
	}

	private static void modifierCompte(Compte c) {

		String old_login = c.getLogin();
		
		String login = null;
		do {
			login= saisieString("Entrez le login:");
		} while (Context.get_instance().getDaoCompte().SelectByLogin(login) != null && !login.equals(old_login));
		
		String nom= saisieString("Entrez le nom:");
		String prenom= saisieString("Entrez le prenom:");
		String password= saisieString("Entrez le password:");
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setLogin(login);
		c.setPassword(password);
		
		if ( c instanceof Adherent) {
			
			double solde= saisieDouble("Entrez le solde:");
			
			System.out.println("Entrez maintenant les données de votre adresse: ");
			Integer num_voie= saisieInt("Numero de la voie:");
			String voie= saisieString("Nom de la voie");
			String code_postal= saisieString("Code postal");
			String ville= saisieString("Ville");


			((Adherent) c).setSolde(solde);
			((Adherent) c).getAdresse().setNum_voie(num_voie);
			((Adherent) c).getAdresse().setVoie(voie);
			((Adherent) c).getAdresse().setCode_postal(code_postal);
			((Adherent) c).getAdresse().setVille(ville);
		}	

		Context.getInstance().getDaoCompte().modifier(c);
	}

	private static void supprimerCompte() {
		Compte.showAllCompte();	
		int choix=saisieInt("Choisir un compte");

		Context.getInstance().getDaoCompte().supprimer(choix);

		System.out.println("Le compte a été supprimé !!");

	}

	//Manipulation Evenement
	private static void editerEvenemets() {
		System.out.println("1 - Afficher les evenements");
		System.out.println("2 - Ajouter un evenement");
		System.out.println("3 - Modifier un evenement");
		System.out.println("4 - Supprimer un evenement");
		System.out.println("5 - Retour");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:Evenement.showAllEvenement();break;
		case 2:ajouterEvenemets();break;
		case 3:modifierEvenemets();break;
		case 4:supprimerEvenemets();break;
		case 5:menuAdmin();break;

		default: editerEvenemets(); break;
		}		

		editerEvenemets();
	}

	private static void ajouterEvenemets() {
		System.out.println("Créer Evenement ");

		String d= saisieString("Entrez la date de l'evenement");
		LocalDate date = LocalDate.parse(d);

		String titre= saisieString("Entrez un titre");
		String description= saisieString("Entrez une description de l'evenement");

		Evenement.creatEvenement(date, titre, description);

		System.out.println("L'evenement a été crée!");
		menuAdmin();	
	}

	private static void modifierEvenemets() {
		Evenement.showAllEvenement();
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
		Evenement.showAllEvenement();

		int choix=saisieInt("Choisir un compte");

		Context.getInstance().getDaoEvenement().supprimer(choix);

		System.out.println("L'evenement a été supprimé !!");

	}

	//Manipulation Articles
	private static void editerArticles() {
		System.out.println("1 - Afficher les articles");
		System.out.println("2 - Ajouter un nouvel article");
		System.out.println("3 - Modifier un article");
		System.out.println("4 - Supprimer un article");
		System.out.println("5 - Retour");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:Article.showAllArticle();break;
		case 2:ajouterArticles();break;
		case 3:modifierArticles();break;
		case 4:supprimerArticles();break;
		case 5:menuAdmin();break;

		default: editerArticles(); break;
		}			
		editerArticles();
	}	

	private static void ajouterArticles() {
		System.out.println("Creer un article ");

		String nom= saisieString("Entrez le nom");
		double prix = saisieDouble("Entrez le prix");
		Integer quantite = saisieInt("Entrez une quantitee");
		String taille= saisieString("Entrez une taille");
		String description= saisieString("Entrez la description");

		Article.creatArticle(nom, prix, quantite, taille, description);
		
		System.out.println("L'article a été crée!");
		menuAdmin();	
	}

	private static void modifierArticles() {
		Article.showAllArticle();
		
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
		Article.showAllArticle();

		int choix=saisieInt("Choisir un article");

		Context.getInstance().getDaoArticle().supprimer(choix);

		System.out.println("Article supprimer!!");	
	}

	//Manipulation tickets
	private static void editerTickets() {
		System.out.println("1 - Afficher les tickets");
		System.out.println("2 - Ajouter des tickets");
		System.out.println("3 - Modifier des tickets");
		System.out.println("4 - Supprimer des tickets");
		System.out.println("5 - Retour");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:Ticket.showAllTicket();break;
		case 2:ajouterTickets();break;
		case 3:modifierTickets();break;
		case 4:supprimerTickets();break;
		case 5:menuAdmin();break;

		default: editerTickets(); break;	
		}
	}

	private static void ajouterTickets() {
		System.out.println("Creer des tickets ");

		double prix = saisieDouble("Entrez le prix");
		Integer quantite = saisieInt("Entrez un qantite");

		String d= saisieString("Entrez une date");
		LocalDate date = LocalDate.parse(d);

		String lieu= saisieString("Entrez un lieu");


		Ticket t = new Ticket(prix, quantite, date, lieu);


		Context.getInstance().getDaoTicket().ajouter(t);

		System.out.println("Les tickets ont été crées!");
		menuAdmin();		
	}

	private static void modifierTickets() {
		Ticket.showAllTicket();
		int choix=saisieInt("Choisir un ticket");

		Ticket t = Context.getInstance().getDaoTicket().selectById(choix);	
		System.out.println(t);

		double prix = saisieDouble("Modifier le prix");
		Integer quantite = saisieInt("Modifier la quantite");
		String d= saisieString("Modifier la date");
		LocalDate date = LocalDate.parse(d);
		String lieu= saisieString("Modifier le lieu");

		
		t.setQuantite(quantite);
		t.setPrix(prix);
		t.setDate(date);
		t.setLieu(lieu);

		Context.getInstance().getDaoTicket().modifier(t);		
	}

	private static void supprimerTickets() {
		Ticket.showAllTicket();

		int choix=saisieInt("Choisir un ticket");

		Context.getInstance().getDaoTicket().supprimer(choix);

		System.out.println("Les tickets ont été supprimés!!");	

	}

	//Menu Client
	private static void menuAdherent() {

		System.out.println("Choisir un menu :");
		System.out.println("1 - Consulter les evenements");
		System.out.println("2 - Consulter les articles");
		System.out.println("3 - Consulter les paries");
		System.out.println("4 - Consulter les tickets");
		System.out.println("5 - Consulter mon panier");
		System.out.println("6 - Modifier mon compte");
		System.out.println("7 - Afficher mon solde");
		System.out.println("8 - Crediter mon compte");
		System.out.println("9 - Deconnexion");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:Evenement.showAllEvenement();break;
		case 2:consulterArticles();break;
		case 3:consulterParies();break;
		case 4:consulterTickets();break;
		case 5:consulterPanier();break;
		case 6:modifierCompte(connected);break;
		case 7:afficheSolde();break;
		case 8:creditSolde();break;
		case 9:menuPrincipal();;break;
		default: menuAdherent(); break;
		}

		menuAdherent();
	}


	private static void creditSolde() {
		boolean error = false;
		do {
			double credit = saisieDouble("Combien voulez-vous crediter ?");
			try {
				((Adherent) connected).creditSolde(credit);
				error= false;
			} catch (creditNotValid e) {
				System.out.println(e.getMessage());
				error = true;
			}
		}while(error);
	}

	private static void afficheSolde() {
		System.out.println(((Adherent) connected).getSolde());
	}

	private static void consulterTickets() {
		Ticket.showAllTicket();
		
		String choix = saisieString("Voulez-vous choisir un ticket ?");
		if(choix.equals("Y")) {
			int num_ticket = saisieInt("Entrez le numero du ticket");
			Ticket ticket = Context.getInstance().getDaoTicket().selectById(num_ticket);
			System.out.println(ticket);
			String choix_ticket = saisieString("Voulez-vous ajouter le ticket au panier ?");
			if (choix_ticket.equals("Y")) {
				boolean error =false;
				do{
					try {
						Integer qte = saisieInt("Entrez votre quantite");
						panier.ajouterPanier(ticket,qte);error=false;
					} catch (qteInsiffisante e) {
						System.out.println(e.getMessage());error=true;
					}
				}while(error);
			}
		}
	}

	//Paries
	private static void consulterParies() {
		SiteParis.showAllParis();

		System.out.println("Voulez vous prendre un pari? [Y/N]");

		String choix = saisieString("");
		switch(choix) 
		{
		case "Y":prisePari();break;
		case "N":menuAdherent();break;
		default : consulterParies();break;
		}
	}
	
	private static void prisePari() {
		int num_match = saisieInt("Entrez le numero du pari");
		SiteParis match = Context.getInstance().getDaoSiteParis().selectById(num_match);
		System.out.println(match);
		String resultat = saisieString("Sur quelle équipe voulez-vous parier ? (team1,nul,team2)");
		double mise = saisieDouble("Quelle est votre mise");
		
		Pari pari = new Pari(match, mise, resultat);
		Context.getInstance().getDaoPari().ajouter(pari);
		
		//payer();
		((Adherent) connected).addPari(pari);
		
		Context.getInstance().getDaoCompte().modifier(connected);
	}

	public static void main(String[] args) {

		menuPrincipal();
	}
}

