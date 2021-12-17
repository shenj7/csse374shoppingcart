import java.awt.Image;

public class Item {
	public String name;
	public double price;
	public String description;
	public Image image;
	
	public double addPrice(double total) {
		return total + price;
	}
}
