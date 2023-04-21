package FinalP;

import java.io.*;
import java.util.*;

public class FileInformation implements Serializable{
	private Map<Item,Double> items;
	private double highLimit;
	private Map<String, Double> names;
	public FileInformation() {
		items = new HashMap<Item,Double>();
		highLimit = 0.0;
		names = new HashMap<String, Double>();
		try {
			Scanner sc = new Scanner(new File ("FileToRead"));
			String command[] = sc.nextLine().split(" ");
			int length = command.length;
			int i = 0;
			while(!(command[i].equals("END"))) {
				if(command[i].equals("NAME")) {
					String ntemp = "";
					while(i<(length)-1) {
						i++;
						ntemp = ntemp + command[i] + " ";
					}
					command = sc.nextLine().split(" ");
					length = command.length;
					String stemp = "";
					i = 0;
					while(i<(length-1)) {
						i++;
						stemp = stemp + command[i] + " ";
					}
					command = sc.nextLine().split(" ");
					double ptemp = Double.parseDouble(command[1]);
					//items.add(new Item(ntemp,stemp,ptemp));
					items.put(new Item(ntemp,stemp), ptemp);
					names.put(ntemp, ptemp);
					command = sc.nextLine().split(" ");
					length = command.length;
					i = 0;
				}
				else  {
					 Double d = Double.parseDouble(command[1]);
					highLimit = d;
					command = sc.nextLine().split(" ");
					length = command.length;
					i = 0;
				}
				
			}
		 }
		 catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} 
	}
	
	public Map<Item,Double> getItem(){
		return this.items;
	}
	
	public Map<String,Double> getName(){
		return this.names;
	}
	
	public double getLimit(){
		return this.highLimit;
	}
	
	public void read() {

		 try {
			Scanner sc = new Scanner(new File ("FileToRead"));
			String command[] = sc.nextLine().split(" ");
			int length = command.length;
			int i = 0;
			while(!(command[i].equals("END"))) {
				if(command[i].equals("NAME")) {
					String ntemp = "";
					while(i<(length)-1) {
						i++;
						ntemp = ntemp + command[i] + " ";
					}
					command = sc.nextLine().split(" ");
					length = command.length;
					String stemp = "";
					i = 0;
					while(i<(length-1)) {
						i++;
						stemp = stemp + command[i] + " ";
					}
					command = sc.nextLine().split(" ");
					double ptemp = Double.parseDouble(command[1]);
					//items.add(new Item(ntemp,stemp,ptemp));
					items.put(new Item(ntemp,stemp), ptemp);
					names.put(ntemp, ptemp);
					command = sc.nextLine().split(" ");
					length = command.length;
					i = 0;
				}
				else  {
					 Double d = Double.parseDouble(command[1]);
					highLimit = d;
					command = sc.nextLine().split(" ");
					length = command.length;
					i = 0;
				}
				
			}
		 }
		 catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} 
}	
	
	
	public static void main(String args[]) {
		FileInformation f= new FileInformation();		
		double items = f.getLimit();
		System.out.print(items);
	}
}
