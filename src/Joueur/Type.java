package Joueur;

public enum Type {
    FETARD(4,2,0.5,"🥳"),RETARDATAIRE(5,1,1,"👀	"),BDE(3,2.5,0.5,"😎"),BOSSEUR(6,0.75,1.5,"👨‍💻");

    private final int vitesseDeplacement;
    private final double multiplicateurFun, multiplicateurRevision;
    private final String icone;


    Type(int vitesse,double multiplicateurFun,double multiplicateurRevision, String icone) {
        this.vitesseDeplacement = vitesse;
        this.multiplicateurFun = multiplicateurFun;
        this.multiplicateurRevision = multiplicateurRevision;
        this.icone = icone;
    }

    public double getMultiplicateurFun() {
        return multiplicateurFun;
    }

    public double getMultiplicateurRevision() {
        return multiplicateurRevision;
    }

    public int getVitesseDeplacement() {
        return this.vitesseDeplacement;
    }

    public String getIcone() {
        return icone;
    }

}
