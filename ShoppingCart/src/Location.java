
public class Location {
	private String state;
	private int zip;
	
	public Location(String state, int zip) {
		this.state = state;
		this.zip = zip;
	}
	
	public double calculateTax(double subtotal) {
		if (this.state.equals("California")) {
			if (this.zip == 92130) {
				return subtotal;
			}
		} else if (this.state.equals("Indiana")) {
			if (this.zip == 47803) {
				return Math.pow(subtotal, 2);
			}
		}
		return -1;
	}
}
