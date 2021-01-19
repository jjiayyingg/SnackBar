package jan19_snackBar;

import java.util.HashMap;

public class Order {
	private HashMap<Item, Integer> itemsOrdered;

	public Order() {
		itemsOrdered = new HashMap<Item, Integer>();
	}

	public void addItem(Item item, int quantity) {
		boolean added = false;
		// check if item already exist in order
		for (Item i : itemsOrdered.keySet()) {
			if (i.getItem().equals(item.getItem())) {
				int newQuantity = itemsOrdered.get(i) + quantity;
				// if already exist, increment the total quantity
				itemsOrdered.replace(i, newQuantity);
				added = true;
			}
		}
		
		// if item does not already exist in order, add
		if (!added) {
			itemsOrdered.put(item, quantity);
		}
	}

	public double calculatePrice() {
		double total = 0;
		for (Item i : itemsOrdered.keySet()) {
			double price = i.getPrice();
			total += price * itemsOrdered.get(i);
		}
		return total;
	}

	public HashMap<Item, Integer> getOrder() {
		return itemsOrdered;
	}
}
