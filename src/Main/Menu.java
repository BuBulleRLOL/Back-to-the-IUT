package Main;

import Input.Input;
import Partie.Partie;
import Partie.Regle;
import Plateau.Plateau;
import Score.HighestScore;

public class Menu {
    public static boolean menu() {
        afficherMenu();

        Input<Integer> choixMenu = new Input<Integer>("Erreur: vous devez choisir un nombre entre 1 et 4.") {
            @Override
            public boolean valid(Integer input) {
                return input >= 1 && input <= 4;
            }

            @Override
            public Integer convert(String input) {
                return Integer.parseInt(input);
            }
        };

        switch(choixMenu.get()) {
            case 1:
                Partie partie = new Partie(new Plateau(16), true);
                partie.lancerPartie();
            case 2:
                Regle.afficherRegles();
            case 3:
                HighestScore.afficherRecord(5);
            case 4:
                return false;
        }
        return true;
    }

    public static void afficherMenu() {
        System.out.println(Logo.getLogo());
        System.out.println(getMenu());
    }

    public static String getMenu() {
        return ("Menu\n" +
                "1. Jouer\n" +
                "2. Lire les règles\n" +
                "3. Voir les record\n" +
                "4. Quitter\n" +
                "Que voulez vous faire ? \n");
    }
}
