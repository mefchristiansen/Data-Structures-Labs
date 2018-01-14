import java.util.*;

public class HashTable {
	private int numBuckets;
	private List<List<Entry>> hashTable;
	private int numEntries;

	public HashTable() {
		numBuckets = 0;
		hashTable = new ArrayList<List<Entry>>(numBuckets);
		numEntries = 0;
	}

	public HashTable(int numBuckets) {
		this.numBuckets = numBuckets;
		hashTable = new ArrayList<List<Entry>>(numBuckets);
		for (int i = 0; i < numBuckets; i++) {
			// System.out.println("YOYO");
			hashTable.add(new ArrayList<Entry>());
		}
		numEntries = 0;
	}

	public Entry insert(GameBoard board, int value) {
		Entry newEntry = new Entry(board, value);

		int hashCode = board.hashCode();
		int hashIndex = compress(hashCode);

		hashTable.get(hashIndex).add(newEntry);

		numEntries++;

		return newEntry;
	}

	public Entry find(GameBoard board) {
		int hashCode = board.hashCode();
		int hashIndex = compress(hashCode);
		List<Entry> bucket = hashTable.get(hashIndex);

		for (Entry entry : bucket) {
			if (entry.getBoard().equals(board)) {
				return entry;
			}
		}

		return null;
	}

	public Entry remove(GameBoard board) {
		int hashCode = board.hashCode();
		int hashIndex = compress(hashCode);
		List<Entry> bucket = hashTable.get(hashIndex);
		int numBucketEntries = bucket.size();

		for (int i = 0; i < numBucketEntries; i++) {
			if (bucket.get(i).getBoard().equals(board)) {
				Entry removeEntry = bucket.get(i);
				bucket.remove(i);
				numEntries--;
				return removeEntry;
			}
		}

		return null;
	}

	public boolean isEmpty() {
		return numEntries == 0;
	}

	public double getLoadFactor() {
		return numEntries * 1.0 / numBuckets;
	}

	private int compress(int hashCode) {
		return (((13 * hashCode) + 37) % 16908799) % numBuckets;
	}

	public double calcVariance() {
		double sampleMean = getLoadFactor();

		double sumOfSquares = 0.0;

		for (int i = 0; i < hashTable.size(); i++) {
			double difference = hashTable.get(i).size() - sampleMean;
			sumOfSquares += difference * difference;
		}

		return sumOfSquares / (numBuckets - 1);
	}
}