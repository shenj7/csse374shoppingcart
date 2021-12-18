
public class ApplyDiscountRequest extends Request {
	public DiscountCenter discountCenter;
	public String discountCode;
	
	public ApplyDiscountRequest(DiscountCenter discountCenter, String code) {
		this.discountCenter = discountCenter;
		this.discountCode = code;
	}

	public void handleApplyDiscount() {
		cart.addDiscount(discountCenter.checkDiscountCode(discountCode));
	}
}
