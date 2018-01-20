import java.util.*;
import java.io.*;

public class Dictionary {
	List<String> dictionary;

	public Dictionary() {
		Scanner dictionaryScanner;

		do {
			dictionaryScanner = getDictionaryFile();
			if (dictionaryScanner != null) {
				break;
			}
		} while(true);

		dictionary = readDictionary(dictionaryScanner);
		dictionaryScanner.close();

		if (dictionary == null) {
			System.out.println("There was an error reading that dictionary file.");
		} else {
			System.out.println("Dictionary was successfully processed.");
			System.out.println();
		}
	}

	private Scanner getDictionaryFile() {
		Scanner scan = new Scanner(System.in);
		String fileName;

		do {
			System.out.print("Please enter the name of the dictionary file without the .txt extension: "); 
			try {
				fileName = scan.next();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error parsing your input. Please try again.");
			}
		} while(true);

		String filePath = "files/" + fileName + ".txt";

		try {
			scan = new Scanner(new File(filePath));
		} catch (Exception e) {
			System.out.println("Could not find or open that dictionary file. Please try again.");
			return null;
		}

		return scan;
	}

	private List<String> readDictionary(Scanner dictionaryScanner) {
		// File is empty
		if (!dictionaryScanner.hasNextLine()) {
			System.out.println("Error: Dictionary is empty.");
			return null;
		}

		List<String> dictionary = new ArrayList<String>();

		while(dictionaryScanner.hasNextLine()) {
			String word = dictionaryScanner.nextLine();
			dictionary.add(word);
		}

		return dictionary;
	}

	public boolean binarySearch(String word) {
		int startIndex, endIndex, midIndex;
		String midWord;
		startIndex = 0;
		endIndex = dictionary.size() - 1;

		while (startIndex <= endIndex) {
			midIndex = (endIndex + startIndex) / 2;
			midWord = dictionary.get(midIndex);

			if (word.equals(midWord)) {
				return true;
			} else if (word.compareTo(midWord) < 0) {
				endIndex = midIndex - 1;
			} else {
				startIndex = midIndex + 1;
			}
		}

		return false;
	}

	public boolean isPrefix(String prefix) {
		int startIndex, endIndex, midIndex;
		String midWord, midSubstring;
		startIndex = 0;
		endIndex = dictionary.size() - 1;

		while (startIndex <= endIndex) {
			midIndex = (endIndex + startIndex) / 2;
			midWord = dictionary.get(midIndex);

			if (prefix.length() <= midWord.length() && prefix.equals(midWord.substring(0, prefix.length()))) {
				return true;
			} else if (prefix.compareTo(midWord) < 0) {
				endIndex = midIndex - 1;
			} else {
				startIndex = midIndex + 1;
			}
		}

		return false;
	}

	public String get(int index) {
		return dictionary.get(index);
	}
	
	public int size() {
		return dictionary.size();
	}
}