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
	  private ObjectOutputStream objectOutputStream;
	  private FileInformation f= new FileInformation();
	  private static Map<String,String> customers = new HashMap<String,String>();
	 


	  protected ClientHandler(Server server, Socket clientSocket) {
	    this.server = server;
	    this.clientSocket = clientSocket;
	    System.out.println("w");
	    //f.read();
	    System.out.println("w");
	    try {
	      fromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
	      objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
	      //toClient = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
	      System.out.println("w");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	 /* protected void sendToClient(String string) {
	    try {
	    	toClient.write(string);
	    	toClient.newLine();
	    	toClient.flush();
	    }
	    catch(Exception e) {
	    	
	    }
	  }*/
	  
	  


	  @Override
	  public void run() {
	    String input;
	    
	    try {
	    	System.out.println("s");
	      //input = fromClient.readLine();
	      //System.out.println(input);
	    	
	    	//System.out.println(f.getLimit());
	   
	      //f.read();
	      objectOutputStream.writeObject(f.getItem());
	      objectOutputStream.flush();
	      System.out.println(f.getLimit());
	      System.out.println("clientrun");
	      while ((input = fromClient.readLine()) != null) {	        
	        //server.processRequest(input);
	    	  System.out.println(input);
	    	  process(input);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  public void process(String s) {
		  String arr[] = s.split(" ");
		  String toSend;
		  try {
		  if(arr[0].equals("login")) {
			  if(customers.containsKey(arr[1])) {
				  if(customers.get(arr[1]).equals(arr[2])) {
					  toSend = "login success";
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
				  else {
					  toSend = "login fail";
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
			  }
			  else {
				  toSend = "login success";
				  objectOutputStream.writeObject(toSend);
				  objectOutputStream.flush();
				  customers.put(arr[1], arr[2]);
			  }
		  }
		  else if(arr[0].equals("buy")){
			  Double price = Double.parseDouble(arr[3]);
			  String itemName = arr[2];
			  if(f.getName().containsKey(itemName)) {
				  if(f.getName().get(itemName)<=price) {
					  toSend = "buy success "+ arr[1]+arr[2]+arr[3]+arr[4];
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
				  else {
					  toSend = "buy fail low "+ arr[1]+arr[2]+arr[3]+arr[4];
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
			  }
		  }
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  		
	
	  }
	  @Override
	  public void update(Observable o, Object arg) {
	    //this.sendToClient((String) arg);
	  }
	}