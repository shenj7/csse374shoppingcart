
public class System {
	public Cart cart;
	public Stock stock;
	
	public Response receive(Request req) {
		if (req instanceof ViewCartRequest) {			
			return new ViewResponse();
		} else {
			return new ModificationResponse();
		}
	}
	
	public void handleModifyCart(Item item, int amount) {
		cart.modifyCart(item, amount);
	}
	
	public void handleAddDiscount(Discount discount) {
		cart.addDiscount(discount);
	}
}
