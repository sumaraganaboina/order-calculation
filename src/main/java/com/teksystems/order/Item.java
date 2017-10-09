package com.teksystems.order;

/*
 * represents an item, contains a price and a description.
 *
 */
public class Item {

	private String description;
	private float price;

	public Item(String description, float price) throws Exception {
		// To check the presence of valid description
		if (description == null || description.trim().length() == 0) {
			System.err.println("ERROR - Item description NULL");
			throw new Exception("description is NULL");
		}
		// To check the valid price entry
		if (price <= 0f) {
			System.err.println("ERROR - price is Invalid");
			throw new Exception("price is Invalid");
		}
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

}
