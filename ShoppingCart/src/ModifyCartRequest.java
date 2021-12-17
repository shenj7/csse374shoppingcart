
public class ModifyCartRequest extends Request {
	public Item item;
	public int amount;
	
	public ModifyCartRequest(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
	
	public void handleModifyCart() {
		cart.modifyCart(item, amount);
	}	
}
