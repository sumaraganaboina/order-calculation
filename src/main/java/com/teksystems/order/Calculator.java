package com.teksystems.order;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {

	public static double rounding(double value) {
		// The casting to (int) makes everything an integer even after the
		// divide by 100.
		// To make the result as double, 100 is changed to 100.0)
		return ((int) (value * 100)) / 100.0;
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's price * quantity
	 * * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 */
	public double calculate(Map<String, Order> orders) {

		// This is the fix to avoid null pointer exception if Cart has null
		// orders
		if (orders == null) {
			throw new IllegalArgumentException(
					"Orders are invalid. Input is null");
		}
		// GrandTotal is initialized outside the loop to have the grand total
		double grandTotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : orders.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
			Order order = entry.getValue();
			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			/*
			 * In lists indexes starts with 0 and ends with its size-1. We
			 * should not include indexes which are not exist,it will leads to
			 * ArrayIndexOutofBoundException. Hence logical operator = is
			 * removed
			 */
			for (int i = 0; i < order.size(); i++) {

				// Calculate the taxes
				double tax = 0;

				Item item = order.get(i).getItem();

				tax = calculateTax(item);

				// Calculate the total price
				double totalPrice = item.getPrice() + tax;

				// Print out the item's total price
				// Wrong way of rounding off here it is fixed with the help of
				// BigDecimal
				System.out.println(item.getDescription() + ": "
						+ roundToTwoDecimal(totalPrice));

				// Keep a running total
				totalTax += tax;
				total += item.getPrice();
			}

			// Print out the total taxes
			// Wrong way of rounding off here it is fixed with the help of
			// BigDecimal
			System.out
					.println("Sales Tax: " + roundToTwoDecimal(totalTax) /*
																		 * Math.
																		 * floor
																		 * (
																		 * totalTax
																		 * )
																		 */);

			// Fix to Total. Total should not include Tax
			// total = total + totalTax;

			// Print out the total amount
			// Wrong way of rounding off here it is fixed with the help of
			// BigDecimal
			System.out.println("Total: " + roundToTwoDecimal(total) /*
																	 * Math.floor
																	 * (total *
																	 * 100) /
																	 * 100
																	 */);
			grandTotal += total;
		}

		// grandtotal = Math.floor(grandtotal * 100) / 100;
		// Bug Fix: 10 (Wrong way of rounding. To meet the requirements, we
		// should be using BigDecimal.)
		System.out.println("Sum of orders: " + roundToTwoDecimal(grandTotal));

		return grandTotal;
	}

	public static double roundToTwoDecimal(Double value) {
		return BigDecimal.valueOf(value)
				.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	public static double calculateTax(Item item) {
		double tax;
		// Here description is converted to upper case to ignore the case
		if (item.getDescription().toUpperCase().contains("IMPORTED")) {
			// imported items
			tax = roundToTwoDecimal(item.getPrice() * 0.15); // Extra 5% tax on
		} else {
			tax = roundToTwoDecimal(item.getPrice() * 0.10);
		}
		return tax;
	}
}
