package application;

import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;

//import FinalP.FileInformation;
//import FinalP.Item; 

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import NecessaryClass.FileInformation;
//import FinalP.FileInformation;
import javafx.scene.layout.VBox;

public class Client {

	  private static String host = "127.0.0.1";
	  //private static Set<Client> clients = new HashSet<Client>();
	  //private BufferedReader fromServer;
	  private BufferedWriter toServer;
	  //private Scanner consoleInput = new Scanner(System.in);
	  private ObjectInputStream objectInputStream;
	  //private LoginController l;
	  private Socket socket;
	  private LoginController login;
	  private List<ItemController> items = new ArrayList<ItemController>();
	  //private FileInformation toPass;
	  private String CustomerName;
	  private static Object lock = new Object();
	  private Connection C;
	  private Stage primaryStage;



	  public void setUpNetworking() throws Exception {
	    //connects the server and show the login page
	    this.socket = new Socket(host, 4242);
	    System.out.println("Connecting to... " + socket);

	    toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    objectInputStream = new ObjectInputStream(socket.getInputStream());
	    
	    Platform.runLater(() -> {
	    	try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        loader.load();
        login = loader.getController();
        BorderPane root = loader.getRoot();
        Scene scene = new Scene(root,600,400);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    });
	  
	    
            	
	    
	   
            	
            	
      
	   
	    
	    
	    read.start();
	    write.start();
	  }
	  
	
	
	  Thread read =  new Thread(new Runnable() {
		  //read messages from clienthandler
	      @Override
	      public void run() {
	    	  System.out.println("read");
	    	  
	    	try {	
	    		 
	    	  C = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root", "WJj25127358"); 
	          while(socket.isConnected()) {

	        	  Object input = objectInputStream.readObject();
	        	  
	        	  if(input == null) {
	        		  continue;
	        	  }
	        	  if(input instanceof FileInformation) {
	        		 // toPass = (FileInformation)input;
	        		 // System.out.println("file passed");
	        	  }
	        	  else {
	        		  
	        		  while((input!=null)&&(input instanceof String)) {
	        			  System.out.println((String)input);
	        			  {
	        			  //login fail
	        			  //login success
	        			  //buy 0 success 
	        			  String[] temp = ((String)input).split(" ");
	        			  if(temp[0].equals("login")) {
	        				  CustomerName = temp[2];
	        				  login.process(primaryStage,temp,C,items);
	        			  }
	        			  else  {
	        				  synchronized(lock) {
	        				  Integer index = Integer.parseInt(temp[3]);
	        				  index--;
	        				  items.get(index).process(temp);
	        				  }
	        			  }
	        			  }
	        			  input = objectInputStream.readObject();
	        		  }
	        	  }
	        	  
	        }
	    	}
	        	  catch (Exception e) {
	    	          e.printStackTrace();
	    	        }
	        	  }
	      }
	    );
	  	
	  
	  Thread write = new Thread(new Runnable() {
		  //check if any button is pressed, send the message
		  @Override
		  public void run() {
			  System.out.println("write");
			 try {
				  while(socket.isConnected()) {
				  
					  //if button pressed, send
						//traverse all the items to see if their button are pressed
					  while((login==null)){
						  
					  }
				  if(login.isMessageSent()) {
					 
					 System.out.println("username sent");
					 toServer.write(login.writeMessage());
					 login.setMessageSent(false);
					 toServer.newLine();
					 toServer.flush();
					 
				  }
				  else{
					  synchronized(lock) {
					  for(int i =0;i<items.size();i++) {
						  if(items.get(i)!=null) {
							  if(items.get(i).isMessageSent()) {
								  toServer.write(items.get(i).writeMessage()+" "+CustomerName);
								  items.get(i).setMessageSent(false);
								  toServer.newLine();
								  toServer.flush();
							  }
						  }
					  }
				  }
				  }
				  }
					  
					  
				  }
				  
		  
			 
					  catch(Exception e) {
						  e.printStackTrace();
						  
						  
					  }
				  }
			  
			  }
		  
	  );
	   
	  

	   
	  
	  
	  
}