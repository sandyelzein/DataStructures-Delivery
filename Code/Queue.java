
public class Queue extends LinkedList {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";

	public Queue() {
		super();
	}

	public void enqueue(Object a) {
		insertAtBack(a);
	}

	public void enqueue(Object d, Object d2) {
		insertAtBack(d, d2);
	}

	public Object dequeue() {
		return deleteFromFront();
	}

	public Object top() {
		return first.data;
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

	public void Qdisplay() {
		if (isEmpty()) {
			System.out.println("The queue is still empty!");
			return;
		}
		Queue temp = new Queue();
		while (!isEmpty()) {
			Node current = first;
			Order O = (Order) current.data;
			Customer c = (Customer) O.customer;
			LinkedList L = (LinkedList) O.foodorders;
			L.display3();
			System.out.println(c.toString2() + " "); // customer
			System.out.println("");
			temp.enqueue(O);
			;
			dequeue();
		}
		while (!temp.isEmpty()) {
			enqueue(temp.top());
			temp.dequeue();
		}
	}
}
