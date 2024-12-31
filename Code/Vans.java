
public class Vans extends Queue {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";
	public static final String ANSI_GREEN = "\u001B[32m";

	public Vans() {
		super();
	}

	public Queue shipOrder(PQofOrders orders) {
		Van v = (Van) dequeue();
		Queue shipped = orders.shippedOrders();
		System.out.println(ANSI_GREEN + "The van " + v.vanid + " is in his way to ship the orders!" + ANSI_RESET);
		enqueue(v);
		return shipped;
	}

	public void insertVan(int platenum) {
		Van v = new Van(platenum);
		enqueue(v);

	}

	public int searchVan(int platenum) {
		int num = -1;
		if (isEmpty()) {
			return num;
		} else {
			Queue t = new Queue();
			while (!isEmpty()) {
				Van v = (Van) dequeue();
				if (v.reg_plate == platenum) {
					num = v.vanid;
				}
				t.enqueue(v);
			}
			while (!t.isEmpty()) {
				enqueue(t.dequeue());
			}
			return num;
		}
	}

	public void deleteVan(int platenum) {
		Queue t = new Queue();
		while (!isEmpty()) {
			Van v = (Van) dequeue();
			if (v.reg_plate != platenum) {
				t.enqueue(v);
			}
		}
		while (!t.isEmpty()) {
			enqueue(t.dequeue());
		}
	}

	public void display() {
		Queue t = new Queue();
		while (!isEmpty()) {
			Van v = (Van) dequeue();
			t.enqueue(v);
			System.out.println(v.toString());
		}
		while (!t.isEmpty()) {
			enqueue(t.dequeue());
		}
	}
}
