package Score;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Joueur.Joueur;
import Joueur.Type;

public class HighestScore {
	private static final String PATH = "highestScore.hs";
	private static ArrayList<Score> highestScore;

	static {
		load(PATH);
	}

	public static boolean add(Score e) {
		boolean result = highestScore.add(e);
		return result;
	}

	public static void sort() {
		highestScore.sort(new ScoreComparator());
	}
	
	public static void main(String[] args) {
		Score s = new Score(new Joueur(Type.BDE, "A"));
		s.setScoreJoueur(10);
		add(s);
		
		s = new Score(new Joueur(Type.BDE, "B"));
		s.setScoreJoueur(3);
		add(s);
		
		s = new Score(new Joueur(Type.BDE, "A"));
		s.setScoreJoueur(20);
		add(s);

		
		sort();

		afficherRecord(5);
		save();
		load(PATH);

	}

	public static boolean save(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(highestScore);
		    oos.close();
		    return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean save() {
		return save(PATH);
	}

	public static void load(String path) {
		try {
			FileInputStream fos = new FileInputStream(path);
		    ObjectInputStream oos = new ObjectInputStream(fos);
			highestScore = (ArrayList<Score>) oos.readObject();
		} catch (ClassNotFoundException | IOException e) {
			if(highestScore == null)
				highestScore = new ArrayList<Score>();
		}
	}

	public static void afficherRecord(int nb) {
		System.out.println("██   ██ ██  ██████  ██   ██ ███████  ██████  ██████  ██████  ███████ \n" +
							"██   ██ ██ ██       ██   ██ ██      ██      ██    ██ ██   ██ ██      \n" +
							"███████ ██ ██   ███ ███████ ███████ ██      ██    ██ ██████  █████   \n" +
							"██   ██ ██ ██    ██ ██   ██      ██ ██      ██    ██ ██   ██ ██      \n" +
							"██   ██ ██  ██████  ██   ██ ███████  ██████  ██████  ██   ██ ███████ \n");

		for(int i = 0; i < nb && i < highestScore.size(); i++) {
			System.out.println(highestScore.get(i));
		}

		System.out.println("Appuyez sur entrée pour continuer...");
		new Scanner(System.in).nextLine();
	}
}
