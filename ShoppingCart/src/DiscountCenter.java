import java.util.ArrayList;

public class DiscountCenter {
	private ArrayList<Discount> discounts;
	
	public DiscountCenter() {
		discounts = new ArrayList<Discount>();
	}
	
	public Discount checkDiscountCode(String code) {
		for (Discount discount: discounts) {
			if (discount.getCode().equals(code)) {
				return discount;
			}
		}
		return null;
	}
}
