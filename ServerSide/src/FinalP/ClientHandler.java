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
import java.sql.*;
import java.util.Observer;
import java.util.Scanner;

import NecessaryClass.FileInformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class ClientHandler implements Runnable, Observer {

	  private Server server;
	  private Socket clientSocket;
	  private BufferedReader fromClient;
	  private BufferedWriter toClient;
	  private ObjectOutputStream objectOutputStream;
	  private String customerName;
	  private static Object lock = new Object();
	  private Connection SQLitems;
	  
	  public ClientHandler(Server server, Socket clientSocket) {
	    this.server = server;
	    this.clientSocket = clientSocket;
	    
	    System.out.println("w");
	    System.out.println("w");
	    try {
	      SQLitems = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root", "WJj25127358");
	      fromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
	      objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
	      System.out.println("w");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  



	  @Override
	  public void run() {
		//read and process all message sent from client
	    String input;
	    try {
	      System.out.println("clientrun");
	   	PreparedStatement check = SQLitems.prepareStatement("SELECT * FROM items WHERE ItemID = ?");
			check.setString(1, "1");
			ResultSet rs = check.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("Name"));
				if(rs.getString("Name").equals("0")) {
					System.out.println("update");
				synchronized(lock) {
					read();
					}
				}
					
					
					
				
			}
			else {
				
				System.out.println("not empty");
				
			}
			
	      while ((input = fromClient.readLine()) != null) {	        
	    	  System.out.println(input);
	    	  synchronized(lock) {
	    		  process(input);
	    	  }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }


public void read() {
	try {		
	//Connection SQLitems = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root", "WJj25127358");;
	PreparedStatement psInsert = null;
	Scanner sc = new Scanner(new File ("FileToRead"));
	String command[] = sc.nextLine().split(" ");
	int length = command.length;
	int j = 1;
	while(!(command[0].equals("END"))) {
		
		if(command[0].equals("name")) {
			//initial = command[1];
			psInsert = SQLitems.prepareStatement("update items set Name=? where ItemID =?");
			psInsert.setString(1, command[1]);
			psInsert.setString(2, String.valueOf(j));
			psInsert.executeUpdate();
			System.out.println(command[1]);
			command = sc.nextLine().split(" ");
		}
		else if(command[0].equals("description")) {
			String s = "";
			for(int i=1;i<command.length;i++) {
				s = s+command[i]+" ";
			}
			System.out.println(s);
			psInsert = SQLitems.prepareStatement("update items set Description=? where ItemID =?");
			psInsert.setString(1, s);
			psInsert.setString(2, String.valueOf(j));
			psInsert.executeUpdate();
			command = sc.nextLine().split(" ");
		}
		else if (command[0].equals("price")) {
			psInsert = SQLitems.prepareStatement("update items set currentPrice=?, LimitPrice=?, Duration=? where ItemID =?");
			psInsert.setString(1, command[1]);
			psInsert.setString(2, command[2]);
			psInsert.setString(3, command[3]);
			psInsert.setString(4, String.valueOf(j));
			psInsert.executeUpdate();
			j++;
			System.out.println(command[1]+command[2]+command[3]);
			command = sc.nextLine().split(" ");
		}
		
	}
 }
catch(Exception e) {
	e.printStackTrace();
}
}

	  public void process(String s) {
		  String arr[] = s.split(" ");
		  String toSend;
		  String sendToUpdate;
		  ResultSet resultSet = null;
		  PreparedStatement check;
		  PreparedStatement print;
		  try {
	      //check message sent from login page
		  if(arr[0].equals("login")) {
			  
			  check = SQLitems.prepareStatement("SELECT * FROM customers WHERE username = ?");
			  check.setString(1, arr[1]);
			  resultSet = check.executeQuery();
			  System.out.println("login");
			  if(!(resultSet.isBeforeFirst())) {
				  System.out.println("login");
				  toSend = "login success";
				  print = SQLitems.prepareStatement("INSERT INTO customers (username, password) VALUES (?, ?)");
				  print.setString(1, arr[1]);
				  print.setString(2, arr[2]);
				  print.executeUpdate();
				  objectOutputStream.writeObject(toSend+" "+arr[1]);
				  objectOutputStream.flush();
			  }
			  else {
				  //System.out.println("login");
				  while(resultSet.next()) {
					  String pass = resultSet.getString("password");
					  if(pass.equals(arr[2])) {
						  toSend = "login success";
						  objectOutputStream.writeObject(toSend+" "+arr[1]);
						  objectOutputStream.flush();
					  }
					  else {
						  toSend = "login fail";
						  objectOutputStream.writeObject(toSend+" "+arr[1]);
						  objectOutputStream.flush();
					  }
				  }
			  }
		  }
		  //check message sent from bidding page
		  else if(arr[0].equals("buy")){
			  double currentPrice = Double.parseDouble(arr[3]);
			  double cp = 0;
			  double lp = 0;
			  String itemName = arr[2];
			  System.out.println(itemName);
			  check = SQLitems.prepareStatement("SELECT * FROM items WHERE Name = ?");
			  check.setString(1, itemName);
			  resultSet = check.executeQuery();
			  System.out.println(itemName);
			  String msg = "";
			  PreparedStatement insert = SQLitems.prepareStatement("INSERT INTO history (customer, item, price) VALUES (?, ?, ?)");
			  
			  if(resultSet.isBeforeFirst()) {
				  //item in the database
				  while(resultSet.next()) {
					 //read current price and highest price from database
					 cp = Double.parseDouble(resultSet.getString("CurrentPrice"));
					 lp = Double.parseDouble(resultSet.getString("LimitPrice"));					  
				  }
				  if((currentPrice>=cp)&&(currentPrice<lp)){
					  //join bidding
					  System.out.println("here");
					  toSend = "buy success "+ arr[4]+" "+arr[1]+" "+arr[2]+" "+arr[3];
					  check = SQLitems.prepareStatement("update items set currentPrice=? where ItemID =?");
					  check.setString(1, arr[3]);
					  check.setString(2, arr[1]);
					  check.executeUpdate();
					  insert.setString(1, arr[4]);
					  insert.setString(2, arr[2]);
					  insert.setString(3, arr[3]);
					  insert.executeUpdate();
					  sendToUpdate = "update success "+arr[4]+" "+arr[1]+" "+arr[2]+" "+arr[3];
					  server.processRequest(sendToUpdate);
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
				  else if(currentPrice<cp) {
					  //can't join because low price
					  toSend = "buy fail "+ "low "+arr[1]+" "+arr[2]+" "+arr[3];
					  System.out.println("fuck");
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
				  else {
					  //Get the item 
					  toSend = "buy complete "+ arr[4]+" "+arr[1]+" "+arr[2]+" "+arr[3];
					  check = SQLitems.prepareStatement("update items set Name=?, Description=?, currentPrice=?, LimitPrice=?, Duration=? where ItemID =?");
					  check.setString(1, arr[4]);
					  check.setString(2, "sold");
					  check.setString(3, arr[3]);
					  check.setString(4, arr[3]);
					  check.setString(5, "0");
					  check.setString(6, arr[1]);
					  check.executeUpdate();
					  insert.setString(1, arr[4]);
					  insert.setString(2, arr[2]);
					  insert.setString(3, arr[3]);
					  insert.executeUpdate();
					  sendToUpdate = "update complete "+arr[4]+" "+arr[1]+" "+arr[2]+" "+arr[3];
					  server.processRequest(sendToUpdate);
					  objectOutputStream.writeObject(toSend);
					  objectOutputStream.flush();
				  }
			  }
			  else {
				  //item not in the datatbase
				  //send "buy fail" 
				  System.out.println(itemName);
				  toSend = "buy fail "+ "sold "+arr[1]+" "+arr[2]+" "+arr[3];
				  objectOutputStream.writeObject(toSend);
				  objectOutputStream.flush();
			  }
			  
		  }
		  
	 }
		  catch(Exception e) {
			  e.printStackTrace();
			  return;
		  }
	 }
			  


				  
				  
				  
				 
			  

		  
		 
	  @Override
	  public void update(Observable o, Object arg) {
	    //this.sendToClient((String) arg);
		  try {
			objectOutputStream.writeObject((String)arg);
			objectOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
	  }
	}