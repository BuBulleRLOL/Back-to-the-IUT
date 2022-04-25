package Plateau;

public class Salle {
	private TypeSalle typeSalle;
	private static int numCase = 0;
	private boolean decouvert;
	private String nomSalle;
	
	public Salle(TypeSalle typeSalle) {
		Salle.numCase++;
		this.nomSalle = this.initNomSalle();
		this.typeSalle = typeSalle;
		this.decouvert = false;
	}

	public String initNomSalle() {
		if(Salle.numCase < 10)return "0a0"+Salle.numCase;
		return "0a"+Salle.numCase;
	}
	
	public String toString() {
	if(!this.decouvert)return "["+this.nomSalle+"]";	
	return "["+ this.nomSalle+" "+this.typeSalle.toString()+"]";
	}
	
	public void caseDecouverte() {
		this.decouvert=true;
	}
	
	public void cacherDecouverte() {
		this.decouvert=false;
	}

	public TypeSalle getTypeSalle() {
		return typeSalle;
	}

	public void setTypeSalle(TypeSalle typeSalle) {
		this.typeSalle = typeSalle;
	}
	
}
