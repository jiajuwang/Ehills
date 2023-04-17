package FinalP;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

import com.google.gson.Gson;

class ServerS extends Observable {

  public static void main(String[] args) {
    new ServerS().runServer();
  }

  private void runServer() {
    try {
      setUpNetworking();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }

  private void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
    ServerSocket serverSock = new ServerSocket(4242);
    while (true) {
      Socket clientSocket = serverSock.accept();
      System.out.println("Connecting to... " + clientSocket);

      ClientHandlerS handler = new ClientHandlerS(this, clientSocket);
      this.addObserver(handler);

      Thread t = new Thread(handler);
      t.start();
    }
  }

  protected void processRequest(String input) {
    String output = "Error";
    Gson gson = new Gson();
    MessageS message = gson.fromJson(input, MessageS.class);
    try {
      String temp = "";
      switch (message.type) {
        case "upper":
          temp = message.input.toUpperCase();
          break;
        case "lower":
          temp = message.input.toLowerCase();
          break;
        case "strip":
          temp = message.input.replace(" ", "");
          break;
      }
      output = "";
      for (int i = 0; i < message.number; i++) {
        output += temp;
        output += " ";
      }
      this.setChanged();
      this.notifyObservers(output);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  

}