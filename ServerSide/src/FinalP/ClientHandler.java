package FinalP;

/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;*/
import java.io.*;
import java.net.Socket;
import java.util.Observer;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

class ClientHandler implements Runnable, Observer {

	  private Server server;
	  private Socket clientSocket;
	  private BufferedReader fromClient;
	  private BufferedWriter toClient;
	  private ObjectOutputStream objectOutputputStream;
	  private FileInformation f= new FileInformation();
	  private static Map<String,String> customers = new HashMap<String,String>();
	 


	  protected ClientHandler(Server server, Socket clientSocket) {
	    this.server = server;
	    this.clientSocket = clientSocket;
	    
	    f.read();
	    try {
	      fromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
	      toClient = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  protected void sendToClient(String string) {
	    try {
	    	toClient.write(string);
	    	toClient.newLine();
	    	toClient.flush();
	    }
	    catch(Exception e) {
	    	
	    }
	  }
	  
	  


	  @Override
	  public void run() {
	    String input;
	    
	    try {
	      input = fromClient.readLine();
	      objectOutputputStream.writeObject(f);
	      while ((input = fromClient.readLine()) != null) {	        
	        //server.processRequest(input);
	    	  process(input);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  public void process(String s) {
		  String arr[] = s.split(" ");
		  //String name = arr[0];
		  //double price = Double.parseDouble(arr[1]);
		 /* if(f.getName().containsKey(name)) {
			  if(price>f.getName().get(name)) {
				  sendToClient("Congradulations");
				  synchronized(f) {
					  f.getName().remove(name);
				  }
			  }
			  else {
				  sendToClient("low");
			  }
		  }
		  else {
			  sendToClient("sold");
		  }
	  }*/
		  String toSend;
		  if(arr[0].equals("login")) {
			  if(customers.containsKey(arr[1])) {
				  if(customers.get(arr[1]).equals(arr[2])) {
					  toSend = "login success";
					  sendToClient(toSend);
				  }
				  else {
					  toSend = "login fail";
					  sendToClient(toSend);
				  }
			  }
			  else {
				  customers.put(arr[1], arr[2]);
			  }
		  }
		  else if(arr[0].equals("buy")){
			  Double price = Double.parseDouble(arr[3]);
			  String itemName = arr[2];
			  if(f.getName().containsKey(itemName)) {
				  if(f.getName().get(itemName)<=price) {
					  toSend = "buy success "+ arr[1]+arr[2]+arr[3]+arr[4];
					  sendToClient(toSend);
				  }
				  else {
					  toSend = "buy fail low "+ arr[1]+arr[2]+arr[3]+arr[4];
					  sendToClient(toSend);
				  }
			  }
		  }
		  		
	
	  }
	  @Override
	  public void update(Observable o, Object arg) {
	    this.sendToClient((String) arg);
	  }
	}