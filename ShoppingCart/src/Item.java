import java.awt.Image;

public class Item {
	public String name;
	public double price;
	public String description;
	public Image image;
	
	public Item() {
		
	}
	
	public Item(String name, double price, String description, Image image) { // for testing
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
	}
	
	public double addPrice(double total) {
		return total + price;
	}
}
