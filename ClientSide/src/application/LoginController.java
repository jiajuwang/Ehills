package application;

import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private Button LoginButton;
	
	@FXML 
	private TextField PasswordField;
	
	@FXML
	private TextField UsernameField;
	
	@FXML
	private Label ErrorShow;
	
	@FXML
	private AnchorPane main;
	
	//private Client c;
	private boolean messageSent = false;
	
	private String CustomerName;
	
	
	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public boolean isMessageSent() {
		return messageSent;
	}

	public void setMessageSent(boolean messageSent) {
		this.messageSent = messageSent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//c = new Client();
		
		LoginButton.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
			@Override
			public void handle(ActionEvent event) {
				try {
					String userName = UsernameField.getText();
					String passWord = PasswordField.getText();
					//ErrorShow.setText("Please Enter again.");
					//UserNameField.clear();
					//c.sendToServer(userName+" "+passWord);
					//Text t = new Text("Please Enter again.");
					//main.getChildren().add(t);
					
					//Stage primaryStage = new Stage();
					//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("BiddingPage.fxml"));
					//Scene scene = new Scene(root,600,400);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					//primaryStage.setScene(scene);
					//primaryStage.show();
					messageSent = true;
					//showBiddingPage();
					System.out.println("received");
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		/*public void nextStage(String s, FileInformation f) {
			if(s.equals("username valid")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						showBiddingPage(f);
					}
				});
				
				
				
			}
			else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						ErrorShow.setText("Please Enter again.");
						UsernameField.clear();
						PasswordField.clear();
					}
				});
				
				
			}
		}*/
		
		  //login fail
		  //login success
		  //buy 0 success 
		public void process(String[] msg, FileInformation f, List<ItemController> items) {
			if(msg[1].equals("fail")) {
				ErrorShow.setText("Please Enter again.");
				UsernameField.clear();
				PasswordField.clear();
			}
			else {
				showBiddingPage(f,items);
			}
		}
		
		public void showBiddingPage(FileInformation f,List<ItemController> items) {
			
		
			try {
			/*Stage primaryStage = new Stage();
			//Pane root = FXMLLoader.load(getClass().getResource("Item.fxml"));	
			VBox v = new VBox();
			Pane tested = new Pane();
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Item.fxml"));
			v.getChildren().add(newLoadedPane);
			v.getChildren().add(tested);
			Scene scene = new Scene(v,600,400);			
			primaryStage.setScene(scene);
			primaryStage.show();*/
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BiddingPage.fxml"));	
			Parent root = loader.load();
			BiddingController b = loader.getController();
			b.showPage(f,items);
			}
			catch(Exception e) {
				System.out.println("s");
				e.printStackTrace();
			}
			
		}
		
		public Button getLoginButton() {
			return LoginButton;
		}

		public void setLoginButton(Button loginButton) {
			LoginButton = loginButton;
		}

		public TextField getPasswordField() {
			return PasswordField;
		}

		public void setPasswordField(TextField passwordField) {
			PasswordField = passwordField;
		}

		public TextField getUsernameField() {
			return UsernameField;
		}

		public void setUsernameField(TextField usernameField) {
			UsernameField = usernameField;
		}

		public Label getErrorShow() {
			return ErrorShow;
		}

		public void setErrorShow(Label errorShow) {
			ErrorShow = errorShow;
		}

		public AnchorPane getMain() {
			return main;
		}

		public void setMain(AnchorPane main) {
			this.main = main;
		}

		public String writeMessage() {
			String userN = getUsernameField().getText(); 
			String pass = getPasswordField().getText();
			String toSent = "login "+userN + " "+pass;
			CustomerName = userN;
			return toSent;
		}
		
		
		
	
}






/*public class LoginController {
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
	
}*/


