
public class AddItemRequest extends Request{
	public Item item;
	public int amount;
	
	public AddItemRequest(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
	
	public void handleAddItem() {
		cart.addItem(item, amount);
	}	
}
