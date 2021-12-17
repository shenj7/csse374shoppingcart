import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
	private double total;
	private Location location;
	private HashMap<Item, Integer> cart;
	private ArrayList<Discount> discounts;
	
	public Cart(Location location) {
		this.location = location;
	}
	
	public void modifyCart(Item item, int amount) {
		if (cart.containsKey(item)) {
			cart.put(item, cart.get(item) + amount);
		} else {
			if (amount == 0) {
				cart.remove(item);
			} else {				
				cart.put(item, amount);
			}
		}
		updateCart();
	}
	
	public void addDiscount(Discount discount) {
		discounts.add(discount);
		updateCart();
	}
	
	public void updateCart() {
		this.total = 0;
		for (Item item: cart.keySet()) {
			total = item.addPrice(total)*cart.get(item);
			//sum items
		}
		for (Discount discount: discounts) {
			total = discount.calculateTotal(total);
			//calculate discounts
		}
		total = location.calculateTax(this.total);
	}
}
