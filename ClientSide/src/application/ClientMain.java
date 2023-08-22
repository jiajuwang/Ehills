package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class ClientMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//show GUI main page
			new Client().setUpNetworking();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
		
		
		
		
	}
}

