package Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Partie.Partie;
import Plateau.Plateau;
import Plateau.TypeSalle;
import Plateau.Salle;
import Output.Colors;

public class Joueur {
	private Type type;
	private int position, ptsDeRevision, ptsDeFun, vitesseDeplacement;
	private String nom;

	public Joueur(String name,int ptsDeRevision,int ptsDeFun) {
		this.nom = name;
		this.ptsDeFun = ptsDeFun;
		this.ptsDeRevision = ptsDeRevision;
	}

	public Joueur(Type type, String nom) {
		this.type = type;
		this.ptsDeRevision=ptsDeFun=position=0; 
		this.vitesseDeplacement=this.type.getVitesseDeplacement();
		this.nom = nom;
	}

	public Type getType() {
		return type;
	}

	public String getNom() {
		return nom;
	}

	public int calculScore() {
		return this.ptsDeFun+this.ptsDeRevision;
	}

	@Override
	public String toString() {
		return nom + " " + type.getIcone() +"\nScore Total : "+calculScore();
	}

	public int getPtsDeRevision() {
		return ptsDeRevision;
	}

	public int getPtsDeFun() {
		return ptsDeFun;
	}

	public int lancerDe() {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		int res=0;
		System.out.println("Lances le dé ! (Enter)");
		if(sc.nextLine().equals("")) {
			System.out.println("Dé lancé !");
			res = r.nextInt(vitesseDeplacement)+1;
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("C'est un "+res+" !");
		}
		return res;
	}

	public void tour(Plateau plateau, Partie p) {
		this.move(p.getPlateau().getTaille());
		if(this.enter(plateau.getCase(position))) {
			this.act(plateau.getCase(position),p.getJoueurs());
		}else {
			this.ability(p.getJoueurs());
			plateau.getCase(position).cacherDecouverte();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void ability(List<Joueur> joueurs) {
		System.out.println("Vous pouvez alors utiliser une capacité.");
		switch(this.type) {
		case BDE:
			System.out.println("Le membre du BDE peut lancer un évènement avec les joueurs adjacents\nLancer la capacité ?");
			if(yesOrNo()) {
				List<Joueur> adjJoueurs = getAdjJoueurs(joueurs);
				lancerEvenement(adjJoueurs);
			}
			break;
		case BOSSEUR:
			System.out.println("Le bosseur peut réviser tranquillement dans le couloir\nLancer la capacité ?");
			if(yesOrNo()) setBonusMalus(1, 0);
			break;
		case FETARD:
			System.out.println("Le fêtard peut vivre sa meilleure vie sur le ponton\nLancer la capacité ?");
			if(yesOrNo()) setBonusMalus(0, 1);
			break;
		case RETARDATAIRE:
			System.out.println("Le retardataire peut interpeler les joueurs proches ce qui leur fera rater le prochain cours\nLancer la capacité ?");
			if(yesOrNo()) {
				if(yesOrNo()) {
					List<Joueur> adjJoueurs = getAdjJoueurs(joueurs);
					for(int i=0;i<adjJoueurs.size();i++) {
						adjJoueurs.get(i).ptsDeRevision--;
					}
				}
			}
			break;
		default:
			System.out.println("ERREUR : TYPE/CLASSE NON TROUVEE");
			break;
		
		}
	}

	private List<Joueur> getAdjJoueurs(List<Joueur> joueurs) {
		List<Joueur> adjJoueurs = new ArrayList<>();
		for(int i=0;i<joueurs.size();i++) {
			if(joueurs.get(i).getPosition()==this.getPosition()+1 || joueurs.get(i).getPosition()==this.getPosition()-1) adjJoueurs.add(joueurs.get(i)); 
		}
		return adjJoueurs;
	}

	private boolean enter(Salle salle) {	
		System.out.println("Souhaites-tu entrer dans cette salle de cours ?\n");
		return yesOrNo();
	}

	private boolean yesOrNo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter / oui = oui ; N'importe quoi d'autre = non");
		String answer = sc.nextLine();
		if(answer.equals("") || answer.equals("oui") || answer.equals("Oui")) {
			
			return true;
		}else{
			
			return false;
		}
	}

	private void move(int lengthPlateau) {
		System.out.println("C'est au tour de "+this.nom+" de jouer !\nDéplacement de "+this.nom);
		int move = this.lancerDe();
		for(int i=0;i<move;i++) {
			try {
				Thread.sleep(700);
				System.out.print("... ");

				this.position++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.position>lengthPlateau) {
				this.position-=lengthPlateau;
			}
			System.out.println();
		}
	}

	public void act(Salle salle, List<Joueur> joueurs) {
		System.out.println("Entrée dans la salle.");
		try {
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		boolean front = getPlace();
		if(salle.getTypeSalle()== TypeSalle.COURS) {
			lancerCours(front);
		}else if(salle.getTypeSalle()== TypeSalle.EVENEMENT) {
			lancerEvenement(joueurs);
		}else if(salle.getTypeSalle()==TypeSalle.EXAM) {
			lancerExam(front);
		}else if(salle.getTypeSalle()==TypeSalle.FETE) {
			lancerFete(front);
		}
		
		salle.caseDecouverte();
	}

	private void lancerCours(boolean front) {
		Random r = new Random();
		System.out.println("Tu es entré dans une salle de COURS ! Voyons voir qui est le professeur...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch(r.nextInt(8)+1) {
		case 1: 
			System.out.println("C'est un cours de SYSTEMES avec M.BEAUFILS !\nJe ne donne pas cher de votre peau si vous vous êtes placés au fond...");
			if(front) {
				setBonusMalus(3,0);
			}else {
				setBonusMalus(-3,-4);
			}
			break;
		case 2:
			System.out.println("\"Cours\" de Mathématiques avec M.CHLEBOWSKI, détendez-vous et profitez de ses blagues (réchauffées chaque année)");
			if(front) {
				setBonusMalus(2,1);
			}else {
				setBonusMalus(1,3);
			}
			break;
		case 3:
			System.out.println("Fantastique...Un cours de PéPéPé...");
			if(front) {
				setBonusMalus(2,1);
			}else {
				setBonusMalus(1,2);
			}
			break;
		case 4:
			System.out.println("Oh non c'est un cours de BDD avec Mme Hajmi, vous êtes viré sans même n'avoir rien fait");
			if(front) {
				setBonusMalus(0,1);
			}else {
				setBonusMalus(0,2);
			}
			break;
		case 5:
			System.out.println("C'est un amphi de C, profitez pour faire une sieste ou faire un basket");
			if(front) {
				setBonusMalus(0,1);
			}else {
				setBonusMalus(0,3);
			}
			break;
		case 6:
			System.out.println("Oh tranquille c'est un cours d'anglais avec Bourgeois la boss!!! Attention elle n'aime pas les fayots ");
			if(front) {
				setBonusMalus(1,1);
			}else {
				setBonusMalus(2,3);
			}
			break;
		case 7:
			System.out.println("C'est Carle !!! Attention à ne pas vous endormir");
			if(front) {
				setBonusMalus(2,0);
			}else {
				setBonusMalus(0,2);
			}
			break;
		case 8:
			System.out.println("Mais NAN ?! c'est un cours de FORG go SIMULAND");
			if(front) {
				setBonusMalus(1,0);
			}else {
				setBonusMalus(2,2);
			}
			break;
		}
		
	}

	private void lancerFete(boolean front) {
		System.out.println("Petit chanceux ! Te voilà dans une "+Colors.toGreen("fête BDE ")+"! Profites-en :smirk:");
		if(front) setBonusMalus(-1, 4);
		else setBonusMalus(0, 2);
	}

	private void lancerExam(boolean front) {
		System.out.println("Tu arrives dans une "+Colors.toRed("salle d'examen")+"...");
		if(this.ptsDeRevision>3 || !front) {
			System.out.println("\u001b[48;31;255;0;0mPetit malin, tu as bien fait de te mettre au fond car tu as pu en profiter pour tricher...\033[0m");
			setBonusMalus(1,0);
		}
		else setBonusMalus(-1, -3);
	}

	private void lancerEvenement(List<Joueur> joueurs) {
		Random r = new Random();
		System.out.println("\u001b[1;34;0mEVENEMEEEEEENT !!!\033[0m");
		int funPts = 0;
		int revPts = 0;
		switch(r.nextInt(8)+1) {
		case 1: 
			System.out.println("Bienvenue au weekend de "+Colors.toRed("désintégration")+" ! J'espère que vous aimez la farine et les oeufs...");
			revPts=0;
			funPts=5;
			break;
		case 2:
			System.out.println("Ah bah bravo ! Vous êtes "+Colors.toRed("en retard en maths")+" !\n Vous avez de la chance...après avoir rassemblé vos dernières pièces vous avez réussi à soudoyer Chlebow.");
			revPts=-1;
			funPts=0;
			break;
		case 3:
			System.out.println("Le BDE est endetté à cause du WEI alors ils vendent de petits croissants dans le hall, pourquoi s'en priver ?");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("Enfin il aurait fallu regarder l'heure de temps en temps, "+Colors.toRed("vous êtes tous en retards pour le prochain cours..."));
			revPts=-2;
			funPts=0;
			break;
		case 4:
			System.out.println(Colors.toRed("Beaufils est en colère !!!! Fuyez pauvre fou !!!! Partez loin et vous ne retournez pas"));
			revPts=0;
			funPts=-4;
			break;
		case 5:
			System.out.println("Semaine de révision !!!! Profitez pour revoir vos cours et pour "+Colors.toGreen("boire un coup")+" :)");
			revPts=3;
			funPts=1;
			break;
		case 6:
			System.out.println("Chlebow rembourse tout les cafés payés par les étudiants ... "+Colors.toRed("TOURNEE DE CAFE"));
			revPts=0;
			funPts=2;
			break;
		case 7:
			System.out.println("Mr Soupex distribue de la bonne sousoupe!!!");
			revPts=1;
			funPts=2;
			break;
		case 8:
			System.out.println("On retourne en "+Colors.toRed("confinement")+" c'est ... dommage");
			revPts=-2;
			funPts=1;
			break;
		}
		for(int i=0;i<joueurs.size();i++) {
			joueurs.get(i).ptsDeRevision+=revPts*joueurs.get(i).type.getMultiplicateurRevision();
			joueurs.get(i).ptsDeFun+=funPts*joueurs.get(i).type.getMultiplicateurRevision();
		}
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Tous les joueurs ont reçu "+revPts+" points de révisions et "+funPts+" points de fun\n(Sans oublier les multiplicateurs)");
		}

	private void setBonusMalus(int reviewPts, int funPts) {
		this.ptsDeRevision+=reviewPts*this.type.getMultiplicateurRevision();
		this.ptsDeFun+=funPts*this.type.getMultiplicateurFun();
		System.out.println("Résultat de la rencontre : "+reviewPts*this.type.getMultiplicateurRevision()+" points de révision et "+funPts*this.type.getMultiplicateurFun()+" point de fun");
	}

	private boolean getPlace() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Décides-tu de te placer au "+Colors.toGreen("premier rang (1)")+" ou "+Colors.toRed("au fond (2)")+" ?");
		String tmp = sc.nextLine();
		if(tmp.equals("1")) return true;
		else return false;
	}

	public int getPosition() {
		return this.position;
	}
	
}
