package com.teksystems.order;

import java.util.LinkedList;
import java.util.List;

public class Order {

	private List<OrderLine> orderLines;

	public Order() {
		// OrderLines Object is created to avoid NullPointerException
		this.orderLines = new LinkedList<OrderLine>();
	}

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}

}
