import java.util.Date;

public abstract class Discount {
	public String code; // string?
	public Date expiryDate;
	
	public String getCode() {
		return this.code;
	}
	
	public abstract double calculateTotal(double total);
}
