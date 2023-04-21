package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene scene = new Scene(root,600,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			new Client().setUpNetworking();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
		
		
		
		
	}
}
