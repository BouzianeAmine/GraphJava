package build.graphe;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

//import org.graphstream.algorithm.ConnectedComponents.ConnectedComponent;
//import org.graphstream.algorithm.ConnectedComponents;
import org.miv.graphstream.algorithm.coloring.WelshPowell;
import org.miv.graphstream.graph.Graph;
import org.miv.graphstream.graph.Node;
import org.miv.graphstream.graph.CheckedGraph;

import data.Data;
import modeleGraphe.Arc;
import modeleGraphe.Noeud;

/**
 *
 * @author aamine
 */
public class GraphDraw extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6891562343551522098L;
	int width;
    int height;
    Graph g = new CheckedGraph("Initial");
    ArrayList<Noeud> nodes;
    ArrayList<Arc> edges;

    public GraphDraw() { 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		width = 30;
		height = 30;
    }

    public void addNode(String name, int x, int y,Data d) { 
		//meme que l'arc ,juste ici on ajoute un noeud
		nodes.add(new Noeud(name,d.getG()));
		this.repaint();
    }
    public void addEdge(String froma, String tob,int poids,Data d ) {
		//ajouter un arc entre froma a tob avec le poids poids est l'affecter au graph directement ,puis repaint
		edges.add(new Arc(froma,tob,poids,d.getG()));
		this.repaint();
    }
    
    public Graph paint(Data d) {	
    	//on récupére les noeud cérer
		d.getUsers().stream().forEach(e->{
			//on créer chaque noeud de tye NOde de graph stream pour pouvoir le dessigner
			Node x=g.addNode(e.getNom());
			x.addAttribute("label", e.getNom());//comme le nom
			x.addAttribute("color",Color.BLACK);
	        x.addAttribute("width",5);// la taille du noued
		});
		//pour les arcs meme que noeud
		d.getArcs().stream().forEach(e->{
			if(e.getextrem().equals(e.getorigine())) System.out.println("Already supported");
			else g.addEdge(e.getorigine().getNom()+"->"+e.getextrem().getNom(), e.getorigine().getNom(), e.getextrem().getNom(), true);
		});
	    // Populate the graph.
	    g.display(true);
		return g;
    }
                  
    public void welsh() {
    	//l'algorthime de welsh déja prêt dans le package graohestream
    	WelshPowell welsh=new WelshPowell(g, true,"color");
		welsh.compute();
    }
    
    public  void communities() {
    	// apres welsh (coloration)
    	g.algorithm().communities("color").values().stream().forEach(e->{
    		//on répere quep our chaque communauté y'a une couleur
			Graph com = new CheckedGraph("Communities");
			e.stream().forEach(el->{
				Node x=com.addNode(el.getAttribute("label").toString());
				x.addAttribute("label", el.getAttribute("label").toString());
				x.addAttribute("color",el.getAttribute("color"));
		        x.addAttribute("width",5);
			});
			//apres la recupéation des noeuds on créer pour chaque communauté une interface
			com.display(true);
		});
    }
    
}
