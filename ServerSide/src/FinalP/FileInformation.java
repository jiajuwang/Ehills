package FinalP;

import java.io.*;
import java.util.*;

public class FileInformation {
	private Set<Item> items;
	private double highLimit;;
	public FileInformation() {
		items = new HashSet<Item>();
		highLimit = 0.0;
	}
	
	public Set<Item> getItem(){
		return this.items;
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
					items.add(new Item(ntemp,stemp,ptemp));
					
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
		f.read();
		Set<Item> items = f.getItem();
		System.out.print(items.size());
	}
}
