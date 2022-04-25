package Partie;

import Output.Colors;
import Main.Logo;

import java.util.Scanner;

public class Regle {
    public static void afficherRegles() {
        System.out.println(Logo.getLogo());
        System.out.println(getRegle());
        System.out.println("Appuyez sur entrée pour continuer...");
        new Scanner(System.in).nextLine();
    }

    private static String getRegle() {
        return "	Vous êtes des étudiants dans un iut, votre but: " + Colors.toGreen("avoir votre diplôme et avoir une vie étudiante épanouie\n")
                + "Comment faire vous allez me demander ? C'est très simple\n"
                + "il suffit de recolter assez de points de révision pour reussir l'examen final puis ramener votre diplôme à la sortie tout en ayant assez de points de fun à la fin\n\n\n"
                + "Voila comment se passe un tour:\n"
                + "Vous allez lancer un dé (différent en fonction de votre type d'étudiant) pour vous déplacer dans les couloirs, ensuite vous choisirez si vous voulez rentrer ou non dans la salle puis vous vous placerez au fond ou non\n"
                + "Arriver dedans une situation se déclenche mettez vous au fond ou devant pour changer les bonus gagnés\n\n\n"
                + "Dans les salles vous pouvez y trouver des: \n\n"
                + " 	-Des cours (attention les profs vont influés sur les gains de points)\n"
                + " 	-Des fêtes étudiantes (beaucoup de fun mais attention au conséquences)\n"
                + " 	-Des examens surprises (il faut les reussir pour éviter des malus ou se placer au fond)\n"
                + " 	-Des évenements qui influeront sur tous les joueurs\n\n"
                + "Voici les différents types d'étudiants et leur attributs:\n\n"
                + "	-Le Bosseur(mov 6): Il gagne + de point de révision mais moins de points de fun\n"
                + "	-Le Fétard(mov 4): Il gagne + de point de fun lors d'une fete mais moins de points de révision\n"
                + "	-Le Retardataire(mov 5): Il a 1 chance sur 2 de pouvoir esquiver le cours ou la fête en rentrant dans une salle\n"
                + "	-Le membre du BDE(mov 3): Il possède plusieurs pouvoirs: La création d'un wei, Le petit dej et aussi l'annulation du cours\n";
    }
}