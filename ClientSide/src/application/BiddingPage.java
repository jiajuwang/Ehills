package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.Stage;


public class BiddingPage extends Application{

	
	public void start(Stage Controller) {
		
	GridPane grid = new GridPane();
	ScrollPane scroll = new ScrollPane();
	scroll.setContent(grid);
	scroll.setPannable(true);
	Scene scene = new Scene(scroll, 600, 600);
	Controller.setX(0);
	Controller.setY(0);
	Controller.setTitle("Bidding Page");
	Controller.setScene(scene);
	grid.setLayoutX(0);
	grid.setLayoutY(0);
	grid.setHgap(100);
	grid.setVgap(5);
	//BiddingController c = new BiddingController();
	//c.addGUI(grid);
	Controller.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	
	
}

