package application;

import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.scene.layout.VBox;

class Client {

	  private static String host = "localhost";
	  private static Set<Client> clients = new HashSet<Client>();
	  private BufferedReader fromServer;
	  private BufferedWriter toServer;
	  //private Scanner consoleInput = new Scanner(System.in);
	  private ObjectInputStream objectInputStream;
	  //private LoginController l;
	  private Socket socket;
	  private LoginController login;
	  private List<ItemController> items;
	  private FileInformation toPass;
	  private String CustomerName;

	  public static void main(String[] args) {
	    try {
	      new Client().setUpNetworking();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  private void setUpNetworking() throws Exception {
	    //@SuppressWarnings("resource")
	    this.socket = new Socket(host, 4242);
	    System.out.println("Connecting to... " + socket);
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
	    login = loader.getController();
	    toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    
	    read.start();
	    write.start();
	  }
	  
	
	
	  Thread read =  new Thread(new Runnable() {
	      @Override
	      public void run() {
	    	  try {
	    		    		
	          while(socket.isConnected()) {
	        	  Object input = objectInputStream.readObject();
	        	  if(input == null) {
	        		  continue;
	        	  }
	        	  if(input instanceof FileInformation) {
	        		  //pass in biddingitems
	        		  toPass = (FileInformation)input;
	        	  }
	        	  else {
	        		  while((input!=null)&&(input instanceof String)) {
	        			  //login fail
	        			  //login success
	        			  //buy 0 success 
	        			  String[] temp = ((String)input).split(" ");
	        			  if(temp[0].equals("login")) {
	        				  login.process(temp,toPass,items);
	        			  }
	        			  else if(temp[0].equals("buy")) {
	        				  Integer index = Integer.parseInt(temp[1]);
	        				  items.get(index).process(temp);
	        			  }
	        			  input = objectInputStream.readObject();
	        		  }
	        	  }
	        	  /*if ((input instanceof String)&&(login((String)input, vbox))){
	        		  l.showBidding();
	        		  input = objectInputStream.readObject();
	        		  l.setBiddingController(null);
	        		  while(input != null) {
	        			  if(input instanceof String) {
	        				  String[] temp = ((String)input).split(" ");
	        				  l.showNotification(temp[0], temp[1]);
	        			  }
	        			  else if(input instanceof FileInformation) {
	        				  l.setBiddingController((FileInformation)input);
	        			  }
	        		  }
	        	  }
	          }*/
	        }} catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    });
	  	
	  
	  Thread write = new Thread(new Runnable() {
		  @Override
		  public void run() {
			  try {
				  while(socket.isConnected()) {
				  if(login.isMessageSent()) {
					  //if login button pushed, then write
					 
					 toServer.write(login.writeMessage()+" "+CustomerName);
					 login.setMessageSent(false);
					 toServer.newLine();
					 toServer.flush();
				  }
				  else {
					  //traverse all the items to see if their button are pressed
					  for(ItemController i: items) {
						  if(i!=null) {
							  if(i.isMessageSent()) {
								  //String user = login.getUsernameField().getText(); 
								  //String pass = login.getPasswordField().getText();
								  toServer.write(i.writeMessage()+" "+CustomerName);
								  login.setMessageSent(false);
								  toServer.newLine();
								  toServer.flush();
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