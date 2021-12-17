
public class System {
	public Cart cart;
	public Stock stock;
	public Response response;
	public DiscountCenter discountCenter;
	
	public Response receive(Request req) {
		req.cart = cart;
		if (req instanceof ViewCartRequest) {
			return new ViewResponse(cart);
		} else if (req instanceof ModifyCartRequest){
			if (stock.checkStock(((ModifyCartRequest) req).item, ((ModifyCartRequest) req).amount)) {
				((ModifyCartRequest) req).handleModifyCart();
				return new ModificationResponse(true);
			} else {
				return new ModificationResponse(false);
			}
		} else if (req instanceof AddItemRequest) {
			if (stock.checkStock(((AddItemRequest) req).item, ((AddItemRequest) req).amount)) {
				((AddItemRequest) req).handleAddItem();
				return new ModificationResponse(true);
			} else {
				return new ModificationResponse(false);
			}
		} else if (req instanceof ApplyDiscountRequest) {
			((ApplyDiscountRequest) req).discountCenter = discountCenter;
			if (discountCenter.checkDiscountCode(((ApplyDiscountRequest) req).discountCode) != null) {	
				((ApplyDiscountRequest) req).handleApplyDiscount();
				return new ModificationResponse(true);
			} else {
				return new ModificationResponse(false);
			}
		}
		return null;
	}
}
