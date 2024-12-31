import java.util.Scanner;

public class FoodItem {
	public String name;
	public double price;
	public String description;
	public boolean isAvailable;
	public int quantityInStock;
	public int id = 0;
	public static int itemsnum = 0;
	private int chosen_quantity = 0;

	public FoodItem() {
		id = itemsnum + 1;
		itemsnum++;
	}

	public FoodItem(String name, double price, String description, int quantityInStock) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.isAvailable = isAvailable();
		id = itemsnum + 1;
		itemsnum++;
	}

	public FoodItem iteminfo(Scanner in) {
		System.out.println("Enter the item's name: ");
		String n = in.next();
		System.out.println("Enter the item's price: ");
		double p = in.nextDouble();
		System.out.println("Enter the item's description: ");
		String des = in.nextLine();
		in.nextLine();
		System.out.println("Enter the quantity in stock: ");
		int quantity = in.nextInt();
		FoodItem item = new FoodItem(n, p, des, quantity);
		return item;
	}

	public boolean isAvailable() {
		return quantityInStock > 0;
	}

	public void setAvailable(int quantityInStock) {
		if(isAvailable())
			isAvailable = true;
		else
			isAvailable = false;
	}

	public int getChosen_quantity() {
		return chosen_quantity;
	}

	public void setChosen_quantity(int chosen_quantity) {
		this.chosen_quantity = chosen_quantity;
	}

	public String toString() {
		String format = "| %-15s | %-20s | %-10s | %-30s | %-12s | %-15s |%n";
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(format, "item ID", "Name", "Price", "Description", "Available", "Stock Quantity"));

		sb.append(String.format(format, id, name, price, description, isAvailable, quantityInStock));

		return sb.toString();
	}

	public String toString2() {
		String format = "| %-10s | %-10s | %-15s | %-10s | %-20s %n";
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(format, "Quantity", "item ID", "Name", "Price", "Description"));

		sb.append(String.format(format, getChosen_quantity(), id, name, price, description));

		return sb.toString();
	}

	public String toString2(int OrderID) {
		String format = "| %-10s | %-10s | %-10s | %-15s | %-10s | %-20s %n";
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(format, "Order ID", "Quantity", "item ID", "Name", "Price", "Description"));

		sb.append(String.format(format, OrderID, getChosen_quantity(), id, name, price, description));

		return sb.toString();
	}

}