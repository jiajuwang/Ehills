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
	
	ArrayList<Item> s;
	public BiddingPage(ArrayList<Item> s) {
		this.s = s;
	}

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
	int j = 0;
	for(int i = 0;i<s.size();i++) {
		grid.add(new Text("Name: "+s.get(i).getName()), 0, j);
		grid.add(new Text("CurrentPrice: "+s.get(i).getPrice()), 1, j);
		j++;
		grid.add(new Text("Description:\n "+s.get(i).getDescription()), 0, j);
		j++;
		grid.add(new TextField("Enter Price: "),0,j);
		grid.add(new Button("BUY!"), 1, j);
		j++;
	}
	}
	
}

