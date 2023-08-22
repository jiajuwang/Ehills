package application;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;



//import FinalP.FileInformation;
//import FinalP.Item;

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
	private PasswordField PasswordField;
	
	@FXML
	private TextField UsernameField;
	
	@FXML
	private Label ErrorShow;
	
	@FXML
	private AnchorPane main;
	
	@FXML
	private Button quit;
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
					//if button pressed, notify client to write
					String userName = UsernameField.getText();
					String passWord = PasswordField.getText();
					
					messageSent = true;
					
										
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		quit.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
	}
	

		
		  //login fail
		  //login success
		  //buy 0 success 
		public void process(Stage stage,String[] msg, Connection f, List<ItemController> items) {
			//if login success, show bidding page
			Platform.runLater(()->{
			if(msg[1].equals("fail")) {
				ErrorShow.setText("Please Enter again.");
				UsernameField.clear();
				PasswordField.clear();
				
			}
			else {
				CustomerName = msg[2];
				stage.close();
				showBiddingPage(f,items);
			}
			});
		
		}
		
		public void showBiddingPage(Connection f,List<ItemController> items) {
			
			Platform.runLater(()->{
				try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BiddingPage.fxml"));	
			Parent root = loader.load();
			BiddingController b = loader.getController();
			b.showPage(f,items,CustomerName);
			}
			catch(Exception e) {
				System.out.println("s");
				e.printStackTrace();
			}
			});
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

		public void setPasswordField(PasswordField passwordField) {
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
			//CustomerName = userN;
			return toSent;
		}
		
		
		
	
}









