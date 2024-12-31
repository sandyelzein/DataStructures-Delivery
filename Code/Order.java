
public class Order {
	public int id = 0;
	public static int ordernum = 0;
	boolean shipped;
	boolean completed;
	double price;
	LinkedList foodorders;
	Customer customer;

	public Order() {
		id = ordernum + 1;
		ordernum++;
		shipped = false;
		completed = false;
	}

	public Order(LinkedList foodlist, Customer c1) {
		id = ordernum + 1;
		ordernum++;
		foodorders = foodlist;
		customer = c1;
	}

	public boolean orderShipped(int id, Queue shipped) {
		Order order = shipped.searchorder_by_ID(id);
		return order.shipped;
	}

	public Order completeOrder(int id, Queue shipped) {
		if (shipped.isEmpty()) {
			return null;
		}
		Order order = shipped.searchorder_by_ID(id);
		order.completed = true;
		return order;
	}

}
