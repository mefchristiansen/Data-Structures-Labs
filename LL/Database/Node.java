public class Node {
	private Data nodeData;
	private Node nextNode;

	public Node() {
		nodeData = null;
		nextNode = null;
	}

	public Node(Data nodeData) {
		this.nodeData = nodeData;
	}

	public void setData(Data nodeData) {
		this.nodeData = nodeData;
	}

	public Data getData() {
		return nodeData;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void printNodeData() {
		System.out.println(nodeData.printData());
	}

	public int compareNodeLastName(Node compareNode) {
		String currNodeLastName = nodeData.getLastName();
		String compareNodeLastName = compareNode.nodeData.getLastName();
		return currNodeLastName.compareTo(compareNodeLastName);
	}
}