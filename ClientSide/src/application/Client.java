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
	    //fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    //l = new LoginController();
	  }
	  
	  public Client() {
		  try {
		  this.socket = new Socket(host, 4242);
		   System.out.println("Connecting to... " + socket);
		   toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		   clients.add(this);
		  }
		  catch(Exception e) {
			  System.out.println("can't build");
		  }
	  }
	  
	  
	  public void read(VBox vbox){
	    new Thread(new Runnable() {
	      @Override
	      public void run() {
	    	  try {
	    		    		
	          while(socket.isConnected()) {
	        	  Object input = objectInputStream.readObject();
	        	  if(input instanceof FileInformation) {
	        		  FileInformation itemInfo = (FileInformation)input;
	        	  }
	        	  if ((input instanceof String)&&(login((String)input, vbox))){
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
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    });
	    }
	  

	   /* public void write() {
	    new Thread(new Runnable() {
	      @Override
	      public void run() {
	        while (true) {
	          String input = consoleInput.nextLine();
	          String[] variables = input.split(",");
	          Message request = new Message(variables[0], variables[1], Integer.valueOf(variables[2]));
	          GsonBuilder builder = new GsonBuilder();
	          Gson gson = builder.create();
	          sendToServer(gson.toJson(request));
	          
	        }
	      }
	    }).start();

	    
	  }*/
	  
	  public void clientStart() {
		  try {
		      new Client().setUpNetworking();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	  }
	  /*protected void processRequest(String input) {
	    return;
	  }*/

	  public void sendToServer(String string) {
	    System.out.println("Sending to server: " + string);
	    try {
			toServer.write(string);
			toServer.newLine();
			toServer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	  }

	  public boolean login(String s, VBox v) {		  
		  return l.receiveMessage(s, v);
	}
	  
	  public void getItems(Object o) {
		  //if(fromServer.read)
		  try {
				l.setBiddingController((FileInformation)o); 
			} 
		 catch (Exception e) {
			e.printStackTrace();
		}
	  }
}