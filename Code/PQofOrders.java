
public class PQofOrders extends PriorityQueue {
	static int id = 0;
	boolean shipped;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";

	public PQofOrders() {
		super();
		id++;
	}

	public void insert_order(Order customerorder, int district) {
		enqueue(customerorder, district);
	}

	public boolean search_by_ID(int orderID) {
		if (first == null) {
			System.out.println(ANSI_RED + "Couldn't find order!" + ANSI_RESET);
			return false;
		}
		Node current = first;
		while (current != null) {
			Order O = (Order) current.data;
			int id = O.id;
			if (id == orderID) {
				System.out.println("Your order is: ");
				LinkedList L = O.foodorders;
				L.display3(orderID);
				return true;
			}
			current = current.next;
		}
		System.out.println(ANSI_RED + "Couldn't find order!" + ANSI_RESET);
		return false;
	}

	public Order searchorder_by_ID(int orderID) {
		if (first == null) {
			System.out.println(ANSI_RED + "Couldn't find order!" + ANSI_RESET);
			return null;
		}
		Node current = first;
		while (current != null) {
			Order O = (Order) current.data;
			int id = O.id;
			if (id == orderID) {
				return O;
			}
			current = current.next;
		}
		System.out.println(ANSI_RED + "Couldn't find order!" + ANSI_RESET);
		return null;
	}

	public boolean delete_by_ID(int orderID) {
		if (first == null) {
			System.out.println("Couldn't find order!");
			return false;
		}

		if (((Order) first.data).id == orderID) {
			first = first.next;
			return true;
		}

		Node previous = first;
		Node current = first.next;

		while (current != null) {
			Order O = (Order) current.data;
			if (O.id == orderID) {
				previous.next = current.next;
				return true;
			}
			previous = current;
			current = current.next;
		}

		System.out.println(ANSI_RED + "Couldn't find order!" + ANSI_RESET);
		return false;
	}

	public Queue shippedOrders() {
		Queue shipped = new Queue();

		while (!isEmpty()) {
			Order O = (Order) first.data;
			O.shipped = true;
			shipped.enqueue(O);
			dequeue();
		}

		return shipped;
	}

	public void orderdisplay(int district) {
		if (isEmpty()) {
			return;
		}
		PQofOrders temp = new PQofOrders();
		while (!isEmpty()) {
			Node current = first;

			Order O = (Order) current.data;
			Customer c = (Customer) O.customer;
			if (c.getDistrict() == district) {
				LinkedList L = (LinkedList) O.foodorders;
				L.display2();
				System.out.println(c + " "); // customer
			}
			temp.enqueue2(current.data, current.priority);
			dequeue();
		}
		while (!temp.isEmpty()) {
			enqueue2(temp.top1(), temp.top3());
			temp.dequeue();
		}
	}

	public double placeorder(int id, LinkedList menu, LinkedList foodorders, int quantity, double total_price) {
		FoodItem f1 = menu.searchForMenuItemByID(id);
		if (quantity <= f1.quantityInStock) {
			f1.quantityInStock = f1.quantityInStock - quantity;
			f1.setChosen_quantity(quantity);
			f1.setAvailable(f1.quantityInStock);
			while (quantity-- > 0) {
				total_price += f1.price;
				foodorders.insertAtBack(f1);
			}
		} else {
			System.out.println(ANSI_RED + "Sorry we are out of stock of " + f1.name + ANSI_RESET);
			return 0;
		}
		return total_price;
	}

	public void pqueuedisplay() {
		if (isEmpty()) {
			return;
		}
		PQofOrders temp = new PQofOrders();
		while (!isEmpty()) {
			Node current = first;
			Order O = (Order) current.data;
			Customer c = (Customer) O.customer;
			LinkedList L = (LinkedList) O.foodorders;
			L.display2();
			System.out.println(c + " "); // customer
			System.out.println("");
			temp.enqueue2(O, current.priority);
			dequeue();
		}
		while (!temp.isEmpty()) {
			enqueue2(temp.top1(), temp.top3());
			temp.dequeue();
		}
	}

}