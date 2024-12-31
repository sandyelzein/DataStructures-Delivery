
public class PriorityQueue extends LinkedList {

	public PriorityQueue() {
		super();
	}

	public Object top1() {
		return first.data;
	}

	public int top3() {
		return first.priority;
	}

	public void enqueue(Object d, int pr) {
		Node newNode = new Node(d);
		newNode.priority = pr;
		priorityEnqueue(newNode);
	}

	public void enqueue2(Object d, int pr) {
		insertAtBackprio(d, pr);
	}

	public Object dequeue() {
		return deleteFromFront();
	}

	public boolean searched(Object O) {
		return super.search(O);
	}

}
