public class LinkedList {

	private Node head;
	private int numNodes;

	public LinkedList() {
		head = null;
		numNodes = 0;
	}

	public void insertNode(Node newNode) {
		if (newNode == null) {
			return;
		}

		newNode.setNext(head);
		head = newNode;
		numNodes++;
	}

	public void print() {
		Node curr = head;

		System.out.println();
		System.out.println("List (starting with the head): ");
		while(curr != null) {
			System.out.println(curr.toString() + " ");
			curr = curr.getNext();
		}
		System.out.println();
		System.out.println();
	}

	public boolean empty() {
		return head == null;
	}
}