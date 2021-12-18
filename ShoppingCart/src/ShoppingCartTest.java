import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ShoppingCartTest {
	private Location testLocation = new Location("California", 92130);
	private System testSystem = new System();
	private Cart testCart = new Cart(testLocation);
	private Stock testStock = new Stock();
	private DiscountCenter testDiscountCenter = new DiscountCenter();
	private Item testItem1 = new Item("Sponge", 1.1, "A normal sponge", null);
	private Item testItem2 = new Item("Apple", 3.14, "Apple not pie", null);
	private Item testItem3 = new Item("Beef", 76.5, "I like steak", null);
	private Discount testDiscount1 = new PercentageDiscount("abc123", 10, new Date(0));
	private Discount testDiscount2 = new PercentageDiscount("abc1232", 10, new Date()); // creates a discount after expiration, since instantiated after the test
	
	private void setUp() {
		testStock.stock.put(testItem1, 1);
		testStock.stock.put(testItem2, 5);
		testStock.stock.put(testItem3, 3);
		testCart.modifyCart(testItem1, 1);
		testCart.modifyCart(testItem2, 5);
		testSystem.cart = testCart;
		testSystem.stock = testStock;
		testSystem.discountCenter = testDiscountCenter;
	}
	
	private void takeDown() {
		testSystem = new System();
		testCart = new Cart(testLocation);	
	}
	/*
	 * Use case 0
	 */
	@Test
	public void standardView() {
		setUp();
		ViewResponse testResponse = new ViewResponse(testCart);
		testResponse.cart = testCart;
		Request testRequest = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest);
		assertEquals(testResponse.success, testSystem.receive(testRequest).success);
		//check success of viewing cart
		assertEquals(16.8, actual.cart.getTotal(), 0.1);
		//check cart total if accurate
		takeDown();
	}
	/*
	 * Use case 1
	 */
	@Test
	public void standardAddItem() {
		setUp();
		AddItemRequest testRequest = new AddItemRequest(testItem3, 3);
		Response resp = testSystem.receive(testRequest);
		//check success of adding items
		assertTrue(resp.success);
		Request testRequest2 = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest2);
		//check price after adding items
		assertEquals(1164.3, actual.cart.getTotal(), 0.1);
		takeDown();
	}

	@Test
	public void addItemNotInStock() {
		setUp();
		AddItemRequest testRequest = new AddItemRequest(testItem3, 10);
		assertFalse(testSystem.receive(testRequest).success);
		takeDown();
	}
	
	@Test
	public void addItemAlreadyInCart() {
		setUp();
		Request testRequest3 = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest3);
		//check price before adding
		assertEquals(21.2, actual.cart.getTotal(), 0.1);
		AddItemRequest testRequest = new AddItemRequest(testItem3, 2);
		AddItemRequest testRequest2 = new AddItemRequest(testItem3, 1);
		testSystem.receive(testRequest);
		assertTrue(testSystem.receive(testRequest2).success);
		Request testRequest4 = new ViewCartRequest();
		ViewResponse actual2 = (ViewResponse) testSystem.receive(testRequest4);
		//check price after adding
		assertEquals(293.1, actual2.cart.getTotal(), 0.1);
		takeDown();
	}
	/*
	 * Use case 2
	 */
	@Test
	public void standardApplyDiscount() {
		setUp();
		AddItemRequest testRequest = new AddItemRequest(testItem3, 3);
		Response resp = testSystem.receive(testRequest);
		//check success of adding items
		assertTrue(resp.success);
		Request testRequest2 = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest2);
		//check price after adding items
		assertEquals(1168.7, actual.cart.getTotal(), 0.1);
		testDiscountCenter.setDiscount(testDiscount1);
		ApplyDiscountRequest testRequest4 = new ApplyDiscountRequest(testDiscountCenter, "abc123");
		assertTrue(testSystem.receive(testRequest4).success);
		assertEquals(1051.83, actual.cart.getTotal(), 0.1);
		takeDown();
		
	}
	
	@Test
	public void applyDiscountExpired() {
		setUp();
		AddItemRequest testRequest = new AddItemRequest(testItem3, 3);
		testSystem.receive(testRequest);
		testDiscountCenter.setDiscount(testDiscount2);
		ApplyDiscountRequest testRequest4 = new ApplyDiscountRequest(testDiscountCenter, "abc1232");
		assertFalse(testSystem.receive(testRequest4).success);
		takeDown();
	}
	
	
	/*
	 * Use case 3
	 */
	@Test
	public void standardModifyCartSubtract() {
		setUp();
		Request testRequest3 = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest3);
		//check price before adding
		assertEquals(21.2, actual.cart.getTotal(), 0.1);
		AddItemRequest testRequest = new AddItemRequest(testItem3, 2);
		ModifyCartRequest testRequest2 = new ModifyCartRequest(testItem3, 1);
		testSystem.receive(testRequest);
		assertEquals(791.7, actual.cart.getTotal(), 0.1);
		//after adding items
		testSystem.receive(testRequest2);
		assertEquals(403.7, actual.cart.getTotal(), 0.1);
		//after lowering the quantity of some items
		assertTrue(testSystem.receive(testRequest2).success);
		takeDown();
	}
	
	@Test
	public void modifyQuantityToZero() {
		setUp();
		Request testRequest3 = new ViewCartRequest();
		ViewResponse actual = (ViewResponse) testSystem.receive(testRequest3);
		ModifyCartRequest testRequest = new ModifyCartRequest(testItem1, 0);
		ModifyCartRequest testRequest2 = new ModifyCartRequest(testItem2, 0);
		testSystem.receive(testRequest);
		testSystem.receive(testRequest2);
		assertEquals(0, actual.cart.getTotal(), 0.1);
		//after removing items
		assertTrue(testSystem.receive(testRequest2).success);
		takeDown();
	}
	
	@Test
	public void modifyQuantityToMoreThanStock() {
		setUp();
		ModifyCartRequest testRequest = new ModifyCartRequest(testItem3, 100);
		assertFalse(testSystem.receive(testRequest).success);
		takeDown();
	}
	
	@Test
	public void stockBecameTooLow() {
		setUp();
		AddItemRequest testRequest = new AddItemRequest(testItem3, 3);
		Response resp = testSystem.receive(testRequest);
		//check success of adding items
		assertTrue(resp.success);
		testSystem.stock.stock.put(testItem3, 0);
		resp = testSystem.receive(testRequest);
		//should return failure
		assertFalse(resp.success);
		takeDown();
		
	}
}
