import java.util.ArrayList;
import java.util.Date;

public class DiscountCenter {
	private ArrayList<Discount> discounts;
	
	public DiscountCenter() {
		discounts = new ArrayList<Discount>();
	}
	
	public Discount checkDiscountCode(String code) {
		for (Discount discount: discounts) {
			if (discount.getCode().equals(code) && discount.expiryDate.compareTo(new Date()) < 0) {
				return discount;
			}
		}
		return null;
	}
	
	public void setDiscount(Discount discount) {
		discounts.add(discount);
	}
}
