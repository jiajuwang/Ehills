package FinalP;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Server extends Observable {

  /*public static void main(String[] args) {
    new Server().runServer();
  }*/

  public void runServer() {
    try {
      setUpNetworking();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
  
  public Server() {
	  
  }

  public void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
    ServerSocket serverSock = new ServerSocket(4242);
    while (true) {
      Socket clientSocket = serverSock.accept();
      System.out.println("Connecting to... " + clientSocket);

      ClientHandler handler = new ClientHandler(this, clientSocket);
      System.out.println("build handler");
      this.addObserver(handler);
      //handler.sendToClient("s");
      Thread t = new Thread(handler);
      t.start();
    }
  }

  public void processRequest(String input) {
	  
      this.setChanged();
      this.notifyObservers(input);
    
  }
  
  

}