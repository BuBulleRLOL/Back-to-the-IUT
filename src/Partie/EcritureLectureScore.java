package Partie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Joueur.Joueur;

public class EcritureLectureScore {
	
	private ArrayList<Joueur>joueurs;
	
	public EcritureLectureScore() {
		this.joueurs = this.Scores();
		this.rangerParScore();
	}
	
	public EcritureLectureScore(ArrayList<Joueur>joueurs) {
		this.ecritureScore();
	}
	
	public ArrayList<Joueur>Scores(){
		ArrayList<Joueur>joueurs = new ArrayList<Joueur>();
		try {
			FileReader fr = new FileReader(new File("res/score.csv"));
			BufferedReader br = new BufferedReader(fr);
			String tampon = br.readLine();
			while(tampon != null) {
				ArrayList<String> infoScore = new ArrayList<String>();
				String res ="";
				for(int i = 0; i < tampon.length();i++) {
					if(tampon.charAt(i)==';') {
						infoScore.add(res);
						res="";
					}else {
						res+=tampon.charAt(i);
					}
				}
				joueurs.add(new Joueur(infoScore.get(0),Integer.valueOf(infoScore.get(1)),Integer.valueOf(infoScore.get(2))));
				tampon = br.readLine();			
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return joueurs;      
	}
	
	
	private void rangerParScore() {
		for(int i = (this.joueurs.size()-1); i > 0; i--) {
			if(this.joueurs.get(i).calculScore() > this.joueurs.get(i-1).calculScore()) {
				Joueur tempo = this.joueurs.get(i);
				this.joueurs.set(i,this.joueurs.get(i-1));
				this.joueurs.set(i-1,tempo);
				i = this.joueurs.size()-1;
			}
		}	
	}
	
	
	
	
	public ArrayList<Joueur>getList(){
		return this.joueurs;
	}
	
	private void ecritureScore() {
		FileWriter fr;
		try {
			fr = new FileWriter(new File("res/score.csv"),true);
			BufferedWriter br = new BufferedWriter(fr);
			for(Joueur j : this.joueurs) {
				br.write(j.getNom()+";"+j.getPtsDeRevision()+";"+j.getPtsDeFun()+";\n");
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
