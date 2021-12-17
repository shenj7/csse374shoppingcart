
public abstract class Discount {
	public String code; // string?
	
	public String getCode() {
		return this.code;
	}
	
	public abstract double calculateTotal(double total);
}
