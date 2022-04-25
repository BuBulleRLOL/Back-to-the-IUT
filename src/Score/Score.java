package Score;

import java.io.Serializable;

import Joueur.Joueur;
import Joueur.Type;

public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nomJoueur;
	private int scoreJoueur;
	private Type type;
	
	
	public Score(String nomJoueur, int scoreJoueur, Type type) {
		this.nomJoueur = nomJoueur;
		this.scoreJoueur = scoreJoueur;
		this.type = type;
	}
	
	public Score(Joueur j) {
		this(j.getNom(), j.calculScore(), j.getType());
	}

	public String getNomJoueur() {
		return nomJoueur;
	}
	
	public int getScoreJoueur() {
		return scoreJoueur;
	}
	
	@Override
	public String toString() {
		return nomJoueur + " : " + scoreJoueur;
	}
	
	public void setScoreJoueur(int scoreJoueur) {
		this.scoreJoueur = scoreJoueur;
	}
}
