package modeleGraphe;
import java.util.ArrayList;
//to repesente the aspect of Graph 
public class Graphe {
	   ArrayList<Noeud> nodes = new ArrayList<Noeud> ();
	   ArrayList<Arc> arcs = new ArrayList<Arc> ();

	   public Object[] getNoeuds(){
	    return nodes.toArray();
	   }

	   public Object[] getArcs(){
	    return arcs.toArray();
	   }
	   public void addNoeud(Noeud e) {
		   this.nodes.add(e);
	   }
	   
	   public void addArc(Arc e) {
		   this.arcs.add(e);
	   }
	   
	   
	   public void setnodes(ArrayList<Noeud> nodes) {
		this.nodes = nodes;
	   }

	   public void setarcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	   }
}
