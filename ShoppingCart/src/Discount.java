
public abstract class Discount {
	private String code; // string?
	
	public String getCode() {
		return this.code;
	}
	
	public abstract double calculateTotal(double total);
}
