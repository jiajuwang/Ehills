package FinalP;

import java.util.Objects;



public class Item {
	private String name;
	private String description;
	private double price;
	int hashCode;
	public Item(String n, String d,double p) {
		name = n;
		description = d;
		price = p;
		this.hashCode = Objects.hash(name, description,price);
    }
    @Override
    public boolean equals(Object p) {
    	Item temp = (Item)p;
    	return (this.name.equals(temp.name))&&(this.description.equals(temp.description))&&(this.price==temp.price);
    }
    
    public String toString() {
    	String s = new String();
    	s = "Name: "+name+"\nDescription: "+description+"\nPrice: "+price+"\n";
    	return s;
    }
    
    @Override
    public int hashCode() {
        return this.hashCode;
    }
	}

