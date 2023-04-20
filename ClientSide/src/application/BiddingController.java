package application;

import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BiddingController implements Initializable{
	@FXML 
	private ScrollPane ScrollPane;
	
	@FXML
	private VBox vBox;
	FileInformation f;
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		
	}
	
	
	
	public void setBiddingController(FileInformation f) {
		this.f = f;
	}
	
	public void showPage() {
		Pane newLoadedPane;
		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("Item.fxml"));
			vBox.getChildren().add(newLoadedPane);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();\
			System.out.println("method");
		}
	}
	
	
	
	
	/*private static Map<Item,Double> s = new HashMap<Item,Double>();
	private Item itemToUpdate = new Item(null,null);
	private double price;
	
	public BiddingController (FileInformation f) {
		s = f.getItem();
	}
	
	public void addGUI(GridPane grid,Client c) {
		int j = 1;			
		for(Map.Entry<Item,Double> entry : s.entrySet()) {
			grid.add(new Text("Description:\n "+entry.getKey().getDescription()), 0, j);
			grid.add(new Text("CurrentPrice: "+entry.getValue()), 1, j);
			Button product = new Button("Buy "+entry.getKey().getName());
			j++;
			grid.add(product,1, j);
			PasswordField p = new PasswordField();
			grid.add(p, 0, j);
			Item temp = new Item(entry.getKey().getName(),entry.getKey().getDescription());
			try {
				product.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
					@Override
					public void handle(ActionEvent event) {
						String price = p.getText();
						p.clear();
						String temp[] = product.getText().split(" ");
						String toWrite = temp[1];
						c.sendToServer(toWrite+" "+price);
					}
				});
			}
			catch(Exception e) {
				System.out.println("Please enter a valid number");
			}
			
			
			j++;
		}
	}
	

	public void receiveMessage(String info, String object, GridPane grid) {
		try {
		synchronized(grid) {
		if(info.equals("low")) {
			Text temp = new Text("Not High Enough");
			grid.add(temp, 0, 0);
			TimeUnit.SECONDS.sleep(5);			
			grid.getChildren().remove(temp);
		}
		else if(info.equals("sold")) {
			Text temp = new Text("Object sold");
			grid.add(temp, 0, 0);
			TimeUnit.SECONDS.sleep(5);
			grid.getChildren().remove(temp);
		}
		else {
			Text temp = new Text("Congradulations");
			grid.add(temp, 0, 0);
			
		}
		}
	}
		catch (Exception e){
			
		}
}*/
	
	
}
