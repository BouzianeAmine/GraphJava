package database;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


//La classe sert a stocker la mtrice d'ajacence , les users meme si c'est 10000 c'est pas grave , mais la matrice c'est 10000*10000 c'est pour ca en la stock 
public class Database {
	static final String file_name="./data/matrice.txt";
	static final File f=new File(file_name);
	public static void sauver(ArrayList<ArrayList<Integer>> adjacency) {
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(adjacency);
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@SuppressWarnings({ "unchecked", "resource" })
	//pour recuperer le nombre de ligne quand veut
	public static ArrayList<ArrayList<Integer>> charger(int n) {
		try {
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(f)) ;
			ArrayList<ArrayList<Integer>> adjacency=(ArrayList<ArrayList<Integer>>) ois.readObject();
			ArrayList<ArrayList<Integer>> adjacenc=new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < n; i++) {
				adjacenc.add(i,adjacency.get(i));
			}
			return adjacenc;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<ArrayList<Integer>> charger() {
		try {
			@SuppressWarnings("resource")
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(f)) ;
			@SuppressWarnings("unchecked")
			ArrayList<ArrayList<Integer>> adjacency=(ArrayList<ArrayList<Integer>>) ois.readObject();
			
			return adjacency;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
