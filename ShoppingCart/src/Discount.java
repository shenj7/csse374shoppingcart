
public abstract class Discount {
	private String code; // string?
	protected double percent;
	
	public boolean checkApplies() {
		return false;
	}
	
	public abstract double calculateTotal(double total);
}
