public class Node {

	private Node nextNode;
	private Data nodeData;

	public Node() {
		nextNode = null;
		nodeData = null;
	}

	public Node(Data nodeData) {
		nextNode = null;
		this.nodeData = nodeData;
	}

	public void print() {
		System.out.print(nodeData.toString());
	}

	public String toString() {
		return "Node[data = " + nodeData.toString() + "]";
	}

	public Data getData() {
		return nodeData;
	}
	
	public void setData(Data nodeData) {
		this.nodeData = nodeData;
	}
	
	public Node getNext() {
		return nextNode;
	}
	
	public void setNext(Node next) {
		this.nextNode = next;
	}
}