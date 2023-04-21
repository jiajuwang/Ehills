package application;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import FinalP.FileInformation;
import FinalP.Item;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class BiddingController implements Initializable{
	@FXML 
	private ScrollPane ScrollPane;
	
	@FXML
	private VBox vBox;
	//FileInformation f;
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		
	}
	
	
	
	/*public static void setBiddingController(FileInformation f) {
		this.f = f;
	}*/
	
	public void showPage(FileInformation f,List<ItemController> items) {
		try {
			/*Stage primaryStage = new Stage();
			//Pane root = FXMLLoader.load(getClass().getResource("Item.fxml"));	
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Item.fxml"));
			//vBox.getChildren().add(newLoadedPane);
			//Parent root = ScrollPane;
			//VBox tested = new VBox();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("BiddingPage.fxml"));
			VBox v = new VBox();
			v.getChildren().add(newLoadedPane);
			Pane n = FXMLLoader.load(getClass().getResource("Item.fxml"));
			v.getChildren().add(n);
			Scene scene = new Scene(v,600,400);			
			primaryStage.setScene(scene);
			primaryStage.show();*/
			System.out.println("show scene");
			Map<Item,Double> temp= f.getItem();
			//Pane root = FXMLLoader.load(getClass().getResource("Item.fxml"));
			int j = 0;
			VBox v = new VBox();
			for(Map.Entry<Item,Double> entry : temp.entrySet()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
			loader.load();
			ItemController it = loader.getController();
			it.getIndex().setText(String.valueOf(j));
			it.getItemName().setText(entry.getKey().getName());
			it.getItemDescription().setText(entry.getKey().getDescription());
			it.getItemPrice().setText(String.valueOf(entry.getValue()));
			Pane newLoadedPane = loader.getRoot();
			v.getChildren().add(newLoadedPane);
			items.add(it);
			j++;
			}
			Stage biddingStage = new Stage();
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("BiddingPage.fxml"));
			Scene scene = new Scene(v,600,400);			
			biddingStage.setScene(scene);
			biddingStage.show();
			
		} catch (Exception e) {
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
