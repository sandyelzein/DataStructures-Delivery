
public class LinkedList {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";
	public static final String ANSI_GREEN = "\u001B[32m";

	public Node first;
	int orderID = 0;

	public LinkedList() {
		first = null;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	// pqoforders and q
	public void insertAtBack(Object d) {
		Node N = new Node(d);
		if (isEmpty())
			first = N;
		else {
			Node current = first;
			while (current.next != null) {
				current = current.next;
			}
			current.next = N;
		}
	}

	// Queue
	public void insertAtBack(Object d, Object d2) {
		Node N = new Node(d, d2);
		if (isEmpty())
			first = N;
		else {
			Node current = first;
			while (current.next != null) {
				current = current.next;
			}
			current.next = N;
		}
	}

	// pqoforders
	public void insertAtBackprio(Object d, int p) {
		Node n = new Node(d, p);
		if (isEmpty() || first.priority > p) {
			n.next = first;
			first = n;
		} else {
			Node current = first;
			while (current.next != null && current.next.priority <= p) {
				current = current.next;
			}
			n.next = current.next;
			current.next = n;
		}
	}

	public void priorityEnqueue(Node newNode) {
		if (isEmpty() || newNode.priority < first.priority) {
			newNode.next = first;
			first = newNode;
		} else {
			Node current = first;
			Node previous = null;

			while (current != null && current.priority <= newNode.priority) {
				previous = current;
				current = current.next;
			}

			if (current == null) {
				// Insert at the end
				previous.next = newNode;
			} else {
				// Insert in the middle
				previous.next = newNode;
				newNode.next = current;
			}
		}

	}

	public int top3() {
		return first.priority;
	}

	public Object deleteFromFront() {
		Object temp = first.data;
		if (!isEmpty()) {
			temp = first.data;
			first = first.next;
		}
		return temp;
	}

	public void display2() {
		if (isEmpty()) {
			return;
		}
		Node current = first;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
	}

	public void display3() {
		if (isEmpty()) {
			return;
		}
		Node current = first;
		FoodItem f = (FoodItem) current.data;
		System.out.println(f.toString2());
		while (current.next != null) {
			f = (FoodItem) current.data;
			current = current.next;
			if (!f.equals(current.data)) {
				FoodItem f2 = (FoodItem) current.data;
				System.out.println(f2.toString2());
			}
		}
	}

	public void display3(int id) {
		if (isEmpty()) {
			return;
		}
		Node current = first;
		FoodItem f = (FoodItem) current.data;
		System.out.println(f.toString2(id));
		while (current.next != null) {
			f = (FoodItem) current.data;
			current = current.next;
			if (!f.equals(current.data)) {
				FoodItem f2 = (FoodItem) current.data;
				System.out.println(f2.toString2(id));
			}
		}
	}

	public boolean search(Object O) {
		if (first == null) {
			return false;
		}
		Node current = first;
		while (current != null) {
			if (current.data.equals(O)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public void addItem(FoodItem item) { // insert at back
		Node newNode = new Node(item);
		if (first == null) {
			first = newNode;
		} else {
			Node current = first;
			while (current.next != null) {
				current = current.next;
			}
			current.next = (newNode);
		}
	}

	public void displayMenu() {
		Node current = first;
		int count = 1;
		while (current != null) {
			FoodItem item = current.getItem();
			System.out.println("Menu Item " + (count) + ": ");
			System.out.println("Title: " + item.name);
			System.out.println("Price: $" + item.price);
			if (item.isAvailable)
				System.out.println(ANSI_GREEN + "Availabile in Stock" + ANSI_RESET);
			else
				System.out.println(ANSI_RED + "Unavailable in Stock" + ANSI_RESET);
			System.out.println("---------------------------");
			current = current.next;
			count++;
		}
	}

	public boolean removeItem(int id) {
		if (first == null) {
			return false;
		}
		if (first.getItem().id == id) {
			first = first.next;
			return true;
		}
		Node current = first;
		while (current.next != null) {
			if (current.next.getItem().id == id) {
				current.next = (current.next.next);
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public void searchForMenuItemByName(String name) {
		if (first == null) {
			return;
		}
		Node current = first;
		while (current != null) {
			if (current.getItem().name.equalsIgnoreCase(name)) {
				FoodItem item = current.getItem();
				System.out.println(ANSI_GREEN + name + " is present on the menu!" + ANSI_RESET);
				System.out.println("---------------------------");
				System.out.println("Title: " + item.name);
				System.out.println("Price: $" + item.price);
				if (item.isAvailable)
					System.out.println(ANSI_GREEN + "Availabile in Stock" + ANSI_RESET);
				else
					System.out.println(ANSI_RED + "Unavailable in Stock" + ANSI_RESET);
				System.out.println("---------------------------");
				return;
			}
			current = current.next;
		}
		if (name.equals("water"))
			System.out.println(ANSI_RED + "Akalet 7elo w baddak toshrab may kamen!" + ANSI_RESET);
		System.out.println(ANSI_RED + name + " is unfortunately not present on the menu!" + ANSI_RESET);
	}

	public FoodItem searchForMenuItemByID(int id) {
		Node current = first;
		while (current != null) {
			if (current.getItem().id == id) {
				FoodItem item = current.getItem();
				return item;
			}
			current = current.next;
		}
		return first.getItem();
	}

}
