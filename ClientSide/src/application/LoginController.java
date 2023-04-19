package application;

import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
	public Client c;
	public String username;
	public BiddingController biddingController;
	GridPane grid = new GridPane();
	Stage biddingStage = new Stage();
	
	
	public LoginController() {
		c = new Client();

		//biddingController = new BiddingController();
		grid = new GridPane();
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(grid);
		scroll.setPannable(true);
		Scene scene = new Scene(scroll, 600, 600);
		biddingStage.setX(0);
		biddingStage.setY(0);
		biddingStage.setTitle("Bidding Page");
		biddingStage.setScene(scene);
		grid.setLayoutX(0);
		grid.setLayoutY(0);
		grid.setHgap(100);
		grid.setVgap(5);
		Button update = new Button("update");
		grid.add(update, 1, 0);
		update.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
			@Override
			public void handle(ActionEvent event) {
				try {
					//biddingController.addGUI(grid, c);
				}
				catch(Exception e) {
					
				}
			}
		});
	}
	
	public void setBiddingController(FileInformation f) {
		biddingController = new BiddingController(f);
	}
	
	public void sendMessage(String s) {
		c.sendToServer(s);
	}
	
	public boolean receiveMessage(String s, VBox v) {
		if(s.equals("username valid")) {
			return true;
		}
		else {
			Text t = new Text("Invalid");
			v.getChildren().add(t);
			return false;
		}
	}
	
	public void showBidding() {		
		//BiddingController biddingController = new BiddingController();
		biddingController.addGUI(grid,c);
		biddingStage.show();
	}
	
	
	public void showNotification(String info,String item) {
		biddingController.receiveMessage(info, item, grid);
	}
	
}
