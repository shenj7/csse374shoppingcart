
public class Location {
	private String state;
	private int zip;
	
	public double calculateTax(double subtotal) {
		if (this.state.equals("California")) {
			if (this.zip == 92130) {
				
			}
		} else if (this.state.equals("Indiana")) {
			if (this.zip == 47803) {
				
			}
		}
		return 0.0;
	}
}
