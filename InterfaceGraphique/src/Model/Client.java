package Model;

import java.util.*;

public class Client extends Personne {
	
    private Magasin magasin;
    private Vector<Vente> achats = new Vector<>();
	
    public Client(String name, String adr, String numT, String email) {
    	super(name, adr, numT, email);
    }

    public void consulterHistoriqueAchats() {
        for (Vente vente : achats) {
            System.out.println("Date: " + vente.getDate());
        }
    }

	public Vector<Vente> getAchats() {
		return achats;
	}
}
