import java.util.HashMap;

public class Stock {
	public HashMap<Item, Integer> stock = new HashMap<Item, Integer>();
	
	public boolean checkStock(Item item, int amount) {
		return amount <= stock.get(item); // return how many are in stock
	}
}
