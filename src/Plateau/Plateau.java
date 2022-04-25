package Plateau;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {
	private final int taille;
	private final ArrayList<Salle> cases;

	public Plateau(int taille) {
		this.taille = taille;
		this.cases = getRandomCases();
	}

	private ArrayList<Salle> getRandomCases() {
		ArrayList<Salle> salles = new ArrayList<Salle>();

		Random r = new Random();

		for(int i=0;i<taille;i++) {
			salles.add(new Salle(TypeSalle.values()[r.nextInt(TypeSalle.values().length)]));
		}

		return salles;
	}

	public int getTaille() {
		return taille;
	}
	
	public Salle getCase(int indice) {
		return this.cases.get(indice);
	}

	public String toString() {
		final String SPACE = "  ";
		final String SPACE_LARGE = "      ";
		StringBuilder res = new StringBuilder("PLAN DE L'IUT\n" + SPACE_LARGE + "  ");
		int idxCase = 0;
		int lengthSide = taille/4;
		final int NB_COTES=4;
		
		if(taille%NB_COTES==0) {
			while(idxCase<lengthSide) {
				res.append(getCase(idxCase).toString()).append(SPACE);
				idxCase++;
			}
			
			res.append("\n");
			int idxCaseInv=taille-1;
			StringBuilder SEPARATOR= new StringBuilder();

			for(int i =0;i<lengthSide;i++) {
				SEPARATOR.append(SPACE_LARGE + SPACE);
			}
			
			while(idxCase/(NB_COTES/2)!=lengthSide) {
				res.append(getCase(idxCaseInv).toString()).append(SEPARATOR).append(SPACE).append(getCase(idxCase).toString()).append("\n");
				idxCaseInv--;
				idxCase++;
			}
			
			res.append(SPACE_LARGE + SPACE);
			for(int i=0;i<lengthSide;i++) {
				res.append(getCase((lengthSide * 3) - i - 1)).append(SPACE);
			}
			
			res.append("\n");
		} else res = new StringBuilder("ERREUR AFFICHAGE (la taille du tableau n'est ni divisible par 4)");

		return res.toString();
	}
	
	public void afficherPlateau() {
		System.out.println(this.toString());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
