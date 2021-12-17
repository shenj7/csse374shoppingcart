
public class PercentageDiscount extends Discount {

	private double percent;
	
	@Override
	public double calculateTotal(double total) {
		return total*this.percent/100;
	}

}
