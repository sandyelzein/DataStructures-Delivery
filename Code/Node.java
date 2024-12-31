
public class Node {
	public Object data;
	public Object data2;
	public int priority;
	public Node next;
	public Node previous;
	private FoodItem item;

	public Node() {
		next = null;
		previous = null;
	}

	public Node(Object d) {
		data = d;
		next = null;
		previous = null;
	}

	public Node(FoodItem item) {
		this.item = item;
		next = null;
	}

	public Node(Object d, Object d2) {
		data = d;
		data2 = d2;
		next = null;
		previous = null;
	}

	public Node(Object d, Object d2, int p) {
		data = d;
		data2 = d2;
		priority = p;
		next = null;
		previous = null;
	}

	public FoodItem getItem() {
		return item;
	}

	public void setItem(FoodItem item) {
		this.item = item;
	}

}
