
public class Location {
	private String state;
	private int zip;
	
	public double calculateTax(double subtotal) {
		if (this.state.equals("California")) {
			if (this.zip == 92130) {
				return subtotal * 1.08;
			}
		} else if (this.state.equals("Indiana")) {
			if (this.zip == 47803) {
				return Math.pow(subtotal, 2);
			}
		}
		return 0.0;
	}
}
