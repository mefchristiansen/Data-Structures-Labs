public class Database {

	private Node head;
	private int numEntries;

	public Database() {
		head = null;
		numEntries = 0;
	}

	public void insertNode(Node newNode) {
		// Database is empty.
		if (head == null) {
			head = newNode;

		// New entry's lastname is alphabetically before current database head.
		} else if (newNode.compareNodeLastName(head) < 0) {
			newNode.setNextNode(head);
			head = newNode;

		// New entry is after current head node.
		} else {
			Node curr, next;
			curr = head;
			next = head.getNextNode();

			while (next != null && newNode.compareNodeLastName(next) > 0) {
				curr = next;
				next = curr.getNextNode();
			}
			// New entry belongs to the end of the database
			curr.setNextNode(newNode);
			newNode.setNextNode(next);
		}

		numEntries++;
	}

	public void findLastName(String lastName) {
		Node curr = head;
		int numEntries = 0;

		while (curr != null && lastName.compareTo(curr.getData().getLastName()) >= 0) {
			if (lastName.compareTo(curr.getData().getLastName()) == 0) {
				curr.printNodeData();
				numEntries++;
			}
			curr = curr.getNextNode();
		}

		if (numEntries == 0) {
			System.out.println("No entries with the last name " + lastName + " were found");
		}
	}

	public void searchAgeRange(int lowAge, int highAge) {
		Node curr = head;
		int numEntries = 0;

		while (curr != null) {
			int currNodeAge = curr.getData().getAge();

			if (currNodeAge >= lowAge && currNodeAge <= highAge) {
				curr.printNodeData();
				numEntries++;
			}

			curr = curr.getNextNode();
		}

		if (numEntries == 0) {
			System.out.println("No entries within that age range were found");
		}
	}

    public void printStudents() {
    	Node curr = head;
    	int numEntries = 0;

    	while (curr != null) {
    		if (curr.getData().getIsStudent()) {
    			curr.printNodeData();
    			numEntries++;  
    		}
    		curr = curr.getNextNode();
    	}

    	if (numEntries == 0) {
    		System.out.println("No student entries were found");
    	}
    }

	public void print() {
		Node curr = head;

		while (curr != null) {
			curr.printNodeData();
			curr = curr.getNextNode();
		}
	}

	public boolean assertSorted() {
		Node curr = head;
		Node next = head.getNextNode();

		while (next != null) {
			if (curr.compareNodeLastName(next) > 0) {
				return false;
			}
			curr = next;
			next = curr.getNextNode();
		}

		return true;
	}
}
