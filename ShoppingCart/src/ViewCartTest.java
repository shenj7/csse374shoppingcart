import static org.junit.Assert.*;

import org.junit.Test;

public class ViewCartTest {
	private Location testLocation = new Location();
	private System testSystem = new System();
	private Cart testCart = new Cart(testLocation);
	private Item testItem1 = new Item("Sponge", 1.1, "A normal sponge", null);
	private Item testItem2 = new Item("Apple", 3.14, "Apple not pie", null);
	private Item testItem3 = new Item("Beef", 76.5, "I like steak", null);
	private Request testRequest = new ViewCartRequest();
	private ViewResponse testResponse = new ViewResponse();
	
	private void setUpCart() {
		testCart.modifyCart(testItem1, 1);
		testCart.modifyCart(testItem2, 5);
		testCart.modifyCart(testItem3, 3);
		testSystem.cart = testCart;
	}
	
	private void takeDownCart() {
		testLocation = new Location();
		testSystem = new System();
		testCart = new Cart(testLocation);	
	}
	
	@Test
	public void standardView() {
		setUpCart();
		testResponse = new ViewResponse();
		testResponse.cart = testCart;
		assertEquals(testResponse, testSystem.receive(testRequest));
		takeDownCart();
	}

}
