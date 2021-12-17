import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
	private double total;
	private Location location;
	private HashMap<Item, Integer> cart;
	private ArrayList<Discount> discounts;
	
	public Cart(Location location) {
		this.cart = new HashMap<Item, Integer>();
		this.discounts = new ArrayList<Discount>();
		this.location = location;
	}
	
	public void addItem(Item item, int amount) {
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
	
	public void modifyCart(Item item, int amount) {
		if (cart.containsKey(item)) {
			cart.put(item, amount);
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
			total = item.addPrice(getTotal())*cart.get(item);
			//sum items
		}
		for (Discount discount: discounts) {
			total = discount.calculateTotal(getTotal());
			//calculate discounts
		}
		this.total = location.calculateTax(this.getTotal());
	}

	public double getTotal() {
		return total;
	}
}
