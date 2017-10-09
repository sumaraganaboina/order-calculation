package com.teksystems.order;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

	@Rule
	public ExpectedException expect = ExpectedException.none();

	@Test
	public void testInvalidItemDescription() throws Exception {
		expect.expect(Exception.class);
		expect.expectMessage("description is NULL");
		Item item = new Item(null, 0f);

	}

	@Test
	public void testInvalidItemPrice() throws Exception {
		expect.expect(Exception.class);
		expect.expectMessage("price is Invalid");
		Item item = new Item("imported box of chocolate", 0f);
	}

	@Test
	public void testInvalidOrderLineItem() throws Exception {
		expect.expect(Exception.class);
		expect.expectMessage("Item is NULL");
		new OrderLine(null, 1);

	}

	@Test
	public void testInvalidQuantity() throws Exception {
		expect.expect(Exception.class);
		expect.expectMessage("quantity is Invalid");
		new OrderLine(new Item("imported box of chocolate", 10), 0);
	}

	@Test
	public void testTaxForImportedGoods() throws Exception {
		expect.expect(Exception.class);
		expect.expectMessage("quantity is Invalid");
		new OrderLine(new Item("imported box of chocolate", 10), 0);
	}

	@Test
	public void testImportedItems() throws Exception {
		Item item = new Item("imported box of chocolate", (float) 12.49);
		assertTrue(Calculator.calculateTax(item) == 1.87);

	}

	@Test
	public void testNonImportedItems() throws Exception {
		Item item = new Item("imported Perfume", (float) 15.25);
		assertTrue(Calculator.calculateTax(item) == 2.29);
	}

	@Test
	public void testroundToTwoDecimal() throws Exception {
		assertTrue(Calculator.roundToTwoDecimal(3.289) == 3.29);
	}

}
