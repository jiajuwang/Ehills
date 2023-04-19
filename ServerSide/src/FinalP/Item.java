package FinalP;

import java.util.Objects;



public class Item {
	private String name;
	private String description;
	//private double price;
	int hashCode;
	public Item(String n, String d) {
		name = n;
		description = d;
		//price = p;
		this.hashCode = Objects.hash(name, description);
    }
    @Override
    public boolean equals(Object p) {
    	Item temp = (Item)p;
    	return (this.name.equals(temp.name))&&(this.description.equals(temp.description));
    }
    
    public String toString() {
    	String s = new String();
    	s = "Name: "+name+"\nDescription: "+description+"\n";
    	return s;
    }
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
    public int hashCode() {
        return this.hashCode;
    }
	}

