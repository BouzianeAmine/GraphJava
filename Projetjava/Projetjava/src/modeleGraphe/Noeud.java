package modeleGraphe;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

//Pour repsenter l'aspect d'un sommet
public class Noeud implements Serializable{

	private static final long serialVersionUID = 1L;
	String nom;
	   public String getNom() {
	    return nom;
	   }

	   Graphe graphe;
	   //pour créer un noued normal
	   public Noeud(String nom) {
		   this.nom=nom;
	   }
	   
	   //pour le crer est l'affecter a un graphe
	   public Noeud (String nom, Graphe g){
	    this.nom = nom;
	    this.graphe = g;
	    graphe.nodes.add(this);
	   }

	   public String toString(){
	    return new String("   noeud: "+ this.getNom());
	   }
	   
	   public void setTographe(Graphe graphe) {
		   graphe.nodes.add(this);
	   }
	   
	   //pour recuperer les successeurs d'un sommet
	   public Object[] getsuccesseur( Noeud noeud){
	    LinkedList<Noeud> successeur = new LinkedList<Noeud> ();
	    ListIterator<Arc> itArcs = graphe.arcs.listIterator();
	    while (itArcs.hasNext()){
	       Arc arcCourant = itArcs.next();
	       if (arcCourant.getorigine().getNom().equals(noeud.getNom())){
	        successeur.add(arcCourant.getextrem());
	       }
	    }
	    return successeur.toArray();
	   }

	   public Boolean hassuccesseur(){
	    return (getsuccesseur(this).length != 0 );
	   }
}
