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



import java.util.Observable;

class ClientHandler implements Runnable, Observer {

	  private Server server;
	  private Socket clientSocket;
	  private BufferedReader fromClient;
	  private BufferedWriter toClient;
	  private ObjectOutputStream objectOutputputStream;
	  private FileInformation f= new FileInformation();


	  protected ClientHandler(Server server, Socket clientSocket) {
	    this.server = server;
	    this.clientSocket = clientSocket;
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
	      f.read();
	      //objectOutputputStream.writeObject(f);
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
		  String name = arr[0];
		  double price = Double.parseDouble(arr[1]);
		  if(f.getName().containsKey(name)) {
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
	  }
	  @Override
	  public void update(Observable o, Object arg) {
	    this.sendToClient((String) arg);
	  }
	}