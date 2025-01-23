package Model;


public class Personne {
	
	protected String nom;
	protected String adresse;
	protected String numTel;
	protected String mail;
	
    public Personne(String name, String adr, String numT, String email) {
    	nom=name;
    	adresse=adr;
    	numTel=numT;
    	mail=email;
    }
    
    public Personne(String name) {
		nom=name;
	}

	public String getNom() {
    	return nom;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getEmail() {
        return mail;
    }
    
    public void modifierNum(String nouveauNum) {
    	numTel=nouveauNum;
    }
    
    
    public void modifierAdresse(String nouvelleAdresse) {
        adresse = nouvelleAdresse;
    }
    
    
    public void modifierEmail(String nouvelEmail) {
        mail = nouvelEmail;
    }

}
