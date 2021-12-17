
public class ApplyDiscountRequest extends Request {
	public DiscountCenter discountCenter;
	public String discountCode;
	
	public ApplyDiscountRequest(DiscountCenter discountCenter) {
		this.discountCenter = discountCenter;
	}

	public void handleApplyDiscount() {
		cart.addDiscount(discountCenter.checkDiscountCode(discountCode));
	}
}
