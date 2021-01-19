package jan19_snackBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SnackBar {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SnackBar sb = new SnackBar();

		Item coffee = new Item("Coffee", 5.2);
		Item tea = new Item("Tea", 3.0);
		Item water = new Item("Water", 0.7);
		Item cupNoodles = new Item("Cup Noodles", 1.2);
		Item bread = new Item("Bread", 2.1);
		Item sausage = new Item("Sausage", 1.5);

		AvailableItems allItems = new AvailableItems();
		allItems.addItems(coffee);
		allItems.addItems(tea);
		allItems.addItems(water);
		allItems.addItems(cupNoodles);
		allItems.addItems(bread);
		allItems.addItems(sausage);

		sb.go(scanner, allItems);
		scanner.close();
	}

	private void go(Scanner scanner, AvailableItems allItems) {

		// get and store customer's name and email
		Customer customer = inputCustomerInfo(scanner);

		// get available items, then display the menu with all available items
		ArrayList<Item> items = allItems.getAllItems();
		displayMenu(items);

		// create a new order
		Order order = new Order();
		// get item id to order and its quantity, then add to the current order
		getItemOrder(scanner, items, order);

		// checkout
		displayBill(customer, order);

		// make another order?
		checkIfRestart(scanner, allItems);
	}

	private void checkIfRestart(Scanner scanner, AvailableItems allItems) {
		System.out.println("Enter 'y' to make a new order");
		System.out.println("else, enter any other key to quit");
		String newOrder = scanner.nextLine();

		// if nothing entered, assume do not restart
		try {
			newOrder.charAt(0);
		} catch (Exception e) {
			newOrder = "no";
		}

		// check if new order
		switch (newOrder.charAt(0)) {
		case 'y':
			// restart
			go(scanner, allItems);
			break;
		case 'Y':
			// restart
			go(scanner, allItems);
		default:
			// simply return, thus ending program
			System.out.println("Service ended.");
			break;
		}
	}

	private void displayBill(Customer customer, Order order) {
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("                     SNACK BAR");
		System.out.println("-----------------------------------------------------");
		System.out.println("Customer Name: " + customer.getName());
		System.out.println("Email address: " + customer.getEmail());
		System.out.println("-----------------------------------------------------");
		System.out.println("Item            Price       Quantity       Subtotal");
		System.out.println("-----------------------------------------------------");

		HashMap<Item, Integer> itemsOrdered = order.getOrder();
		for (Item i : itemsOrdered.keySet()) {
			System.out.printf("%-15s", i.getItem()); // item name
			System.out.printf("%5.2f", i.getPrice()); // item price
			System.out.print("           ");
			System.out.printf("%-5d", itemsOrdered.get(i)); // quantity
			System.out.print("         ");
			System.out.printf("%-9.2f", i.getPrice() * itemsOrdered.get(i)); // subtotal
			System.out.println();
		}

		double totalPrice = order.calculatePrice();
		System.out.println("-----------------------------------------------------");
		System.out.print("Grand Total                                  ");
		System.out.printf("%-5.2f", totalPrice);
		System.out.println();
		System.out.println("-----------------------------------------------------");

		System.out.println("            Thank you! Do visit again!");
		System.out.println();
	}

	private void checkIfAddItems(Scanner scanner, ArrayList<Item> items, Order order) {
		System.out.println("Enter 'y' to add another item");
		System.out.println("else, enter any other key to proceed to checkout");
		scanner.nextLine(); // skipping one scanner line
		String more = scanner.nextLine();

		// if nothing entered, assume no additional items
		try {
			more.charAt(0);
		} catch (Exception e) {
			more = "no";
		}

		// check if continue to add items to order
		switch (more.charAt(0)) {
		case 'y':
			// get items to order again
			getItemOrder(scanner, items, order);
			break;
		case 'Y':
			// get items to order again
			getItemOrder(scanner, items, order);
		default:
			// do not add items, simply return
			break;
		}
	}

	private void getItemOrder(Scanner scanner, ArrayList<Item> items, Order order) {
		int id = getItemID(scanner, items);
		int qty = getQuantity(scanner);

		// add item and quantity input to the order
		order.addItem(items.get(id - 1), qty);

		// check with customer if they want to add more items to the order
		checkIfAddItems(scanner, items, order);
	}

	private int getQuantity(Scanner scanner) {
		System.out.println("Quantity of purchase:");
		int qty = 0;

		// if none integer entered, try again
		try {
			qty = scanner.nextInt();
		} catch (Exception e) {
			// skipping one scanner line
			scanner.nextLine();
			System.out.println("Invalid value, please enter again: ");
			qty = getQuantity(scanner);
		}

		// check if quantity input is valid
		if (qty < 0) {
			System.out.println("Invalid value, please enter again: ");
			// get quantity input again if invalid
			qty = getQuantity(scanner);
		}

		return qty;
	}

	private int getItemID(Scanner scanner, ArrayList<Item> items) {
		System.out.println("ID of the item you would like to purchase:");
		int id;

		// if none integer entered, try again
		try {
			id = scanner.nextInt();
		} catch (Exception e) {
			// skipping one scanner line
			scanner.nextLine();
			System.out.println("Invalid ID, please enter again: ");
			id = getItemID(scanner, items);
		}

		// check if id input is valid
		if (id < 1 || id > items.size()) {
			System.out.println("Invalid ID, please enter again: ");
			// get id input again if invalid
			id = getItemID(scanner, items);
		}

		return id;
	}

	private void displayMenu(ArrayList<Item> items) {
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("   ID        Items                            Price");
		System.out.println("-----------------------------------------------------");
		for (int i = 0; i < items.size(); i++) {
			int id = i + 1;
			System.out.print("   " + id + ".       ");
			System.out.printf("%-15s", items.get(i).getItem());
			System.out.print("                  ");
			System.out.printf("%5.2f", items.get(i).getPrice());
			System.out.println();
		}
		System.out.println("-----------------------------------------------------");
		System.out.println();
	}

	private Customer inputCustomerInfo(Scanner scanner) {
		// get customer information
		System.out.println("Please enter your name:");
		String custName = scanner.nextLine();
		System.out.println("Please enter your email address:");
		String email = scanner.nextLine();
		Customer customer = new Customer(custName, email);
		return customer;
	}

}
