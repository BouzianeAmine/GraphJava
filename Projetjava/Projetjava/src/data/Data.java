package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import database.Database;
import modeleGraphe.Arc;
import modeleGraphe.Graphe;
import modeleGraphe.Noeud;

//la lecture de données et la céreation du graph a partir la matrice d'ajacence quand lu ici
public class Data {
	 private Graphe g;
	 private ArrayList<Noeud> users;
	 private ArrayList<Arc> arcs;
	 public Data(int n) {
		 read_Matrix(n);
		 read_Users(n);
	 }
	 public  void read_Matrix(int n) {
		 	ArrayList<String[]> adjacence=new ArrayList<String[]>();
	        String csvFile = "./data/StructuralAdjUSA.csv";
	        BufferedReader br = null;
	        List<String> retweet=new ArrayList<String>();
	        ArrayList<ArrayList<Integer>> matrix=new ArrayList<ArrayList<Integer>>();
	        try {
	            br = new BufferedReader(new FileReader(csvFile));
	            int m=0;
	            for (String line = br.readLine();line != null && m < n;line = br.readLine()) {
	                retweet.add(line);	              
	                m++;
	            }

	            for(int l=0;l<n;l++) {
	            	adjacence.add(l,retweet.get(l).split("\n"));
	            }
	            for (int i = 0; i < adjacence.size(); i++) {
	            	ArrayList<Integer> temp=new ArrayList<Integer>();
	            	for (int k = 0; k < n; k++) {
	            		temp.add(k,new Integer( adjacence.get(i)[0].split(",")[k]));
					}
	            	matrix.add(i,temp);
				}
	            Database.sauver(matrix);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    }
	 public  void read_Users(int n) {

	        String csvFile = "./data/nodesUSA.csv";
	        BufferedReader br = null;
	        users=new ArrayList<Noeud>();
	        try {
	            int i=0;
	            br = new BufferedReader(new FileReader(csvFile));
	            for (String line = br.readLine();line != null && i<n;line = br.readLine()) {
	            	users.add(new Noeud(line));       
	            	i++;
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    }
	 //apartir des données on crée la matrice d'ajacence
	 public void fromAjacencetoGraphe( ArrayList<ArrayList<Integer>> x) {
		 g=new Graphe();
		 ArrayList<ArrayList<Integer>> matriceadj=x;
		 arcs=new ArrayList<Arc>();
		 g.setnodes(users);
		 for (int i = 0; i < matriceadj.size(); i++) {
			for (int j = 0; j < matriceadj.get(i).size(); j++) {
				if(matriceadj.get(i).get(j) > 0) {
					arcs.add(new Arc(users.get(i).getNom(),users.get(j).getNom(),matriceadj.get(i).get(j),g));
				}
			}
		}
	 }
	 	 
	public Graphe getG() {
		return g;
	}
	public void setG(Graphe g) {
		this.g = g;
	}
	public ArrayList<Noeud> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<Noeud> users) {
		this.users = users;
	}
	public ArrayList<Arc> getArcs() {
		return arcs;
	}
	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}
}
