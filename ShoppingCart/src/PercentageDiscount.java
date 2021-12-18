import java.util.Date;

public class PercentageDiscount extends Discount {
	private double percent;
	
	public PercentageDiscount(String code, int i, Date date) {
		this.expiryDate = date;
		this.code = code;
		this.percent = i;
	}

	@Override
	public double calculateTotal(double total) {
		return total*(100-this.percent)/100;
	}

}
