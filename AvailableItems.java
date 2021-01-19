package jan19_snackBar;

import java.util.ArrayList;

public class AvailableItems {
	private ArrayList<Item> availableItems;

	public AvailableItems() {
		availableItems = new ArrayList<Item>();
	}

	public void addItems(Item item) {
		availableItems.add(item);
	}
	
	public ArrayList<Item> getAllItems(){
		return availableItems;
	}
}
