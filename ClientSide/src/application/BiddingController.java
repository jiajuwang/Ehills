package application;


import java.io.IOException;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import NecessaryClass.FileInformation;
import NecessaryClass.Item;

//import FinalP.FileInformation;
//import FinalP.Item;

import java.util.*;

import javafx.application.Platform;
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
	
	@FXML 
	private Button quit;
	
	@FXML
	private Button history;
	
	@FXML
	private TextField searchField;
	
	@FXML
	private Label result;
	
	@FXML
	private Button searchButton;
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		quit.setOnAction(new EventHandler<ActionEvent>() { 
			// quit the page
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
		
		searchButton.setOnAction(new EventHandler<ActionEvent>() { 
			// search through the database to find the indexes of the item
			@Override
			public void handle(ActionEvent event) {
				try {
					Connection C = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root", "WJj25127358");
					PreparedStatement check = C.prepareStatement("SELECT * FROM items WHERE Name = ?");
					  check.setString(1, searchField.getText());
					  ResultSet resultSet = check.executeQuery();
					  System.out.println(searchField.getText());
					  TimeUnit t = TimeUnit.SECONDS;
					  String s = "Not Exist";
					  if(resultSet.isBeforeFirst()) {
						  //item in the database
						  while(resultSet.next()) {
							 //read current price and highest price from database
							 s = resultSet.getString("ItemID");
							 
						  }
						result.setText("item at "+s);
					  }
					  else {
						  result.setText("item at "+s);
					  }
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//return;
				}
			}
		});
		
		history.setOnAction(new EventHandler<ActionEvent>() { 
			// search through the database and show a new page detail the bidding history
			@Override
			public void handle(ActionEvent event) {
				
					 Platform.runLater(()->{
						 try {
					Connection C = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root", "WJj25127358");
					String msg = "";
					      String query = "SELECT * FROM history";

					     
					      Statement st = C.createStatement();
					      
					      
					      ResultSet rs = st.executeQuery(query);
					      
					     
					      VBox p = new VBox();
					      ScrollPane s = new ScrollPane();
					      s.setContent(p);
					      
					      Stage stage2 = new Stage();
					      stage2.setTitle("History");
					      Scene scene = new Scene(s,600,400);
					      stage2.setScene(scene);
					      while (rs.next())
					      {
					    	  System.out.println(msg);
					        msg = rs.getString("customer")+" buy "+rs.getString("item")+ " for "+rs.getString("price");
					        Text t = new Text(msg);
					        p.getChildren().add(t);
					      }
					      st.close();
					      //System.out.println(msg);
					      
			
					      
					      
					      
					      stage2.show();
						 }
					      catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
					 });
			}
		});
	}
					    
				
				   
					
				
			

		

		
		
		
	
	
	
	
	
	
	public void showPage(Connection c,List<ItemController> items,String customer) {
		try {
			//add all item to a vBox and adds them to the scrollpane
			System.out.println("show scene");
			FXMLLoader bidloader = new FXMLLoader(getClass().getResource("BiddingPage.fxml"));
			bidloader.load();
			BiddingController bidController = bidloader.getController();
			VBox v = new VBox();
			
			
			int j = 1;
			for(j=1;j<=9;j++) {
			PreparedStatement pst = c.prepareStatement("select Name, Description, CurrentPrice, LimitPrice, Duration from items where ItemID = ?");
			pst.setString(1, String.valueOf(j));
			ResultSet rs = pst.executeQuery();
			if(rs.next()==true) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
				loader.load();
				ItemController it = loader.getController();
				it.getIndex().setText(String.valueOf(j));
				it.getItemName().setText(rs.getString(1));
				it.getItemDescription().setText(rs.getString(2));
				it.getItemPrice().setText(rs.getString(3));
				it.getBiddingPrice().setText(rs.getString(4));
				it.getDuration().setText(rs.getString(5));
				Pane newLoadedPane = loader.getRoot();					
				v.getChildren().add(newLoadedPane);
				items.add(it);
				
			}
			}
			
			
			
			
			bidController.getScrollPane().setContent(v);
			
			Stage biddingStage = new Stage();
			Pane root = bidloader.getRoot();
			Scene scene = new Scene(root,600,400);
			biddingStage.setTitle("welcome to Bidding "+customer);
			biddingStage.setScene(scene);
			biddingStage.show();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public ScrollPane getScrollPane() {
		return ScrollPane;
	}



	public void setScrollPane(ScrollPane scrollPane) {
		ScrollPane = scrollPane;
	}



	public VBox getvBox() {
		return vBox;
	}



	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}
	
	
	
	
	
}
