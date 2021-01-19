package jan19_snackBar;

public class Item {
	protected String item;
	protected double price;
	
	public Item (String item, double price) {
		this.price = price;
		this.item = item;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItem() {
		return item;
	}
}
