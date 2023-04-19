package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Observer;
import java.util.Observable;

class ClientHandler implements Runnable, Observer {

  private Server server;
  private Socket clientSocket;
  private BufferedReader fromClient;
  private BufferedWriter toClient;
  private ObjectOutputStream objectOutputputStream;


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
    objectOutputputStream = 
      while ((input = fromClient.readLine()) != null) {
        //System.out.println("From client: " + input);
        server.processRequest(input);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    this.sendToClient((String) arg);
  }
}