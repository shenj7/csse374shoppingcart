
public class PercentageDiscount extends Discount {

	@Override
	public double calculateTotal(double total) {
		return total*this.percent/100;
	}

}
