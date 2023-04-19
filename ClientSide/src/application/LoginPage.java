package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginPage extends Application{
	public void start(Stage Login) {		
		TextField userName = new TextField("User Name:");
		PasswordField password = new PasswordField();
		Button enter = new Button("ENTER");
		
		VBox vbox = new VBox(userName,password,enter);
		vbox.setAlignment(Pos.TOP_RIGHT);
		Scene scene = new Scene(vbox, 600, 600);
		Login.setX(0);
		Login.setY(0);
		Login.setTitle("Login Page");
		Login.setScene(scene);
		LoginController controller = new LoginController();		
		enter.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
			@Override
			public void handle(ActionEvent event) {
				controller.sendMessage(userName.getText()+" "+password.getText());				
			}
		});
		Login.show();		
	}
		
	public static void main(String[] args) {
		launch(args);

	}

	
		
}
