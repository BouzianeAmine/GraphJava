package modeleGraphe;
import java.io.Serializable;
import java.util.ListIterator;

//Class to represente the aspect of Arcs 
public class Arc implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int poids;
	   public int getPoids() {
		return poids;
	   }
	   Noeud origine;
	   public Noeud getorigine() {
	    return origine;
	   }

	   Noeud extrem;
	   public Noeud getextrem() {
	    return extrem;
	   }

	   public Arc(String nomorigine, String nomextrem, int poids ,Graphe graphe){
		this.poids=poids;
		
	    // find origine noeud avec son nom
	    ListIterator<Noeud> noueds = graphe.nodes.listIterator();
	    Boolean findOrg = false;
	    while (noueds.hasNext()&&!findOrg) {
	       Noeud actuel = noueds.next();
	       if (nomorigine.equals(actuel.getNom())){
	        origine = actuel;
	        findOrg = true;
	       }
	    }
	    
	    // find  extremite noeud avec son nom
	    noueds = graphe.nodes.listIterator();
	    Boolean trouveExtremite = false;
	    while (noueds.hasNext()&&!trouveExtremite) {
	       Noeud actuel = noueds.next();
	       if (nomextrem.equals(actuel.getNom())){
	        extrem = actuel;
	        trouveExtremite = true;
	       }        
	    }
	    if (findOrg && trouveExtremite) {
	       graphe.arcs.add(this);
	    } 
	   }

	   public String toString(){
	    return new String("   arc: ( "
	        + this.getorigine().getNom()
	        + " -> "
	        + this.getextrem().getNom()
	        + " poids : "
	        + this.getPoids()
	    );
	   }

}
