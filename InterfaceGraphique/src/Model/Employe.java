package Model;

import java.util.*;

public class Employe extends Personne {

    private String role;
    private Employe gerant;
    private Magasin magasin;
    private Vector<Vente> ventes = new Vector<>();
    private Vector<Employe> list_employe = new Vector<>();
    
    public Employe(String name, String role) {
    	super(name);
    	this.role=role;
   }
    
    public void ajouterVente(Vente vente) {
        ventes.add(vente);
    }
    
    public Vector<Vente> getVentes() {
    	return ventes;
    }
    
    public Vector<Employe> getList_employe(){
    	return list_employe;
    } 
    
    public void ajouterEmploye(Employe employe) {
        list_employe.add(employe);
    }

}
