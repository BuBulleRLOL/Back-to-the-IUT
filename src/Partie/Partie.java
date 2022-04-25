package Partie;

import java.util.ArrayList;
import java.util.List;

import Input.Input;
import Joueur.Joueur;
import Joueur.Type;
import Output.ClearScreen;
import Plateau.Plateau;
import Output.Colors;
import Main.Logo;

public class Partie {
	private final int MAX_TURNS = 6;
	private final int MAX_SCORE = 20;
	private int turnCurrent = 1;
	private Plateau plateau;
	private List<Joueur> joueurs;
	private int[] scoreJoueurs;

	public Partie(Plateau plateau, boolean afficherRegles) {
		this.plateau = plateau;
		this.joueurs = new ArrayList<>();

		if (afficherRegles) Regle.afficherRegles();
	}

	public void lancerPartie() {
		Joueur winner = null;

		this.joueurs = createPlayers(plateau.getTaille());
		this.scoreJoueurs = new int[this.joueurs.size()];

		boolean victory = false;

		do {
			ClearScreen.clear();
			System.out.println(Logo.getLogo());
			System.out.println("\nTOUR " + turnCurrent + ", " + (MAX_TURNS - turnCurrent) + " AVANT " + Colors.toRed("L'EXAMEN FINAL"));
			plateau.afficherPlateau();
			indiquerPositionJoueurs();

			for (Joueur joueur : joueurs) {
				joueur.tour(plateau, this);
				ClearScreen.clear();
			}

			turnCurrent++;
			int cptWin = 0;
			while (cptWin < joueurs.size()) {
				if (joueurs.get(cptWin).calculScore() >= MAX_SCORE) {
					winner = joueurs.get(cptWin);
					victory = true;
				}
				cptWin++;
			}
		} while (!victory && turnCurrent < MAX_TURNS);


		if (turnCurrent == MAX_TURNS) {
			winner = lancerExamFinal();
			System.out.println("Victoire de " + winner.getNom() + " " + Colors.toGreen("par victoire au score") + " avec" + winner.getPtsDeFun() + " points de révision !");
		} else {
			System.out.println("Victoire de " + winner.getNom() + " " + Colors.toGreen("par victoire au temps") + " avec" + winner.calculScore() + " points !");
		}


		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	private Joueur lancerExamFinal() {
		ClearScreen.clear();
		System.out.println("L'heure tant redoutée est arrivée...c'est l'heure de " + Colors.toRed("l'examen final") + "...La tension est à son comble...");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Joueur winner = joueurs.get(0);
		for (int i = 1; i < joueurs.size(); i++) {
			if (joueurs.get(i).getPtsDeRevision() > winner.getPtsDeRevision()) {
				winner = joueurs.get(i);
			} else if (joueurs.get(i).getPtsDeRevision() == winner.getPtsDeRevision()) {
				if (joueurs.get(i).getPtsDeFun() >= winner.getPtsDeFun()) {
					winner = joueurs.get(i);
				} else {
					System.out.println(Colors.toRed("C'EST INNACCEPTABLE ! IL N'Y A RIEN DE PIRE QU'UNE EGALITE"));
				}
			}
		}
		return winner;
	}

	private void indiquerPositionJoueurs() {
		String res = "POSITION DES JOUEURS :\n";
		for (Joueur i : joueurs) {
			res += i.toString() + ", se trouve devant la salle " + plateau.getCase(i.getPosition()).toString() + " (position " + (i.getPosition() + 1) + ")\n";
		}
		System.out.println(res);
	}

	private List<Joueur> createPlayers(int joueurMax) {
		Input<Integer> nbJoueurs = new Input<Integer>("Erreur : le nombre saisi doit être supérieur à zéro.") {
			@Override
			public boolean valid(Integer input) {
				return input > 0;
			}

			@Override
			public Integer convert(String input) {
				return Integer.parseInt(input);
			}
		};

		Input<Integer> choixClasse = new Input<Integer>("Erreur: vous devez choisir un nombre entre 1 et 4.") {
			@Override
			public boolean valid(Integer input) {
				return input >= 1 && input <= 4;
			}

			@Override
			public Integer convert(String input) {
				return Integer.parseInt(input);
			}
		};

		Input<String> nomJoueur = new Input<String>("Erreur : ce nom est déjà utilisé.") {
			@Override
			public boolean valid(String input) {
				for (Joueur joueur : Partie.this.getJoueurs()) {
					if (joueur.getNom().equals(input)) return false;
				}
				return true;
			}

			@Override
			public String convert(String input) {
				return input;
			}
		};


		System.out.println("Bien le bonjour jeunes étudiants, combien va-t-il y a voir de joueurs ?");
		nbJoueurs.get();

		for (int i = 0; i < nbJoueurs.value() && i < joueurMax; i++) {
			System.out.println("Joueur " + (i + 1) + ", choisis ta classe :\n- Bosseur (1)\n- Fêtard (2)\n- Membre du BDE (3)\n- Retardataire (4)");
			int numeroClasse = choixClasse.get() - 1;

			System.out.println("Choisis ton nom :");
			String nom = nomJoueur.get();
			joueurs.add(new Joueur(Type.values()[numeroClasse], nom));
		}

		return joueurs;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public Plateau getPlateau() {
		return plateau;
	}
}