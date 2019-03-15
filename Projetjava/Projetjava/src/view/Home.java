package view;




import build.graphe.GraphDraw;
import data.Data;
import database.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Interface graphique
public class Home extends Application {
	
	Data data;
	GraphDraw gd=new GraphDraw();
	public static void main(String[] args) {
		Application.launch(Home.class,args);
	}
	//scene principal
	public void start(Stage primaryStage) throws Exception {
		 Scene scene = construire();
	     primaryStage.setScene(scene);
	     primaryStage.setTitle("Graph manipilator");
	     primaryStage.show();
	     instructions(primaryStage);
	}
	//le home création de la scene , les button ,la zone text
	private Scene construire() {
		GridPane grid = new GridPane();
		Label labelt = new Label("Nombre of data you want: ");
		TextField nombre = new TextField();
		Button button1 = new Button("Bouton1");
		button1.setText("Get data");
		Button button2 = new Button("Bouton2");
		button2.setText("Welsh powell");
		Button button3 = new Button("Bouton3");
		button3.setText("Clustring");
		Button button4 = new Button("Bouton4");
		button4.setText("Visualise");
		
		//Button pour le chargement de données
		button1.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent arg0) {
				data=new Data(new Integer(nombre.getText()));
				data.fromAjacencetoGraphe(Database.charger(new Integer(nombre.getText())));
			}
		});
		
		//pour le dessin normal du graph
		button4.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent arg0) {
				gd.paint(data);
				
			}
		});
		
		//pour appliquer welsh sur le graph créer
		button2.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent arg0) {
				gd.welsh();
				
			}
		});
		
		//pour récuperer les communities
		button3.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent arg0) {
				gd.communities();		
			}
		});
		HBox hBox = new HBox(3);
		hBox.getChildren().addAll(labelt, nombre);
		HBox au=new HBox(3);
		HBox contenubox=new HBox(3);
		contenubox.getChildren().addAll(button1,button4,button2,button3);
		GridPane.setConstraints(hBox, 0, 0);
		grid.getChildren().add(hBox);
		GridPane.setConstraints(au, 0, 1);
		grid.getChildren().add(au);
		GridPane.setConstraints(contenubox, 0, 3);
		grid.getChildren().add(contenubox);
		return new Scene(grid);
	}
	
	//presente le premier frame qui vous donnes la procédure a suivre pour l'exécution (popup)
	private Scene instructions(Stage primaryStage) {
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         dialog.initOwner(primaryStage);
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text("Svp charger les données et visualiser avant de lancer welsh "));
         dialogVbox.getChildren().add(new Text("lancer Welsh avant clustring "));
         Scene dialogScene = new Scene(dialogVbox, 500, 200);
         dialog.setScene(dialogScene);
         dialog.show();
		return dialogScene;
	}
}

