import java.util.*;

// Interface and class generic used as absence can lead to run time errors undetected by compiler. 
// Generic interface or class must be of single generic type
public class Stack<E> implements StackType<E> {
	private ArrayList<E> stack;

	public Stack() {
		stack = new ArrayList<E> ();
	}

	public Stack(int numElements) {
		// If the arraylist is allocated with an initial size of numElements, you can immediately access the indicies 0 to numElements - 1
		stack = new ArrayList<E> (numElements);
	}

	public void push(E element) {
		stack.add(element);
	}

	public E pop() {
		// Can call class functions within class. Java assumes reference to the current object
		if (isEmpty()) {
			return null;
		}

		E topElement = top();
		stack.remove(size() - 1);
		return topElement;
	}

	public E top() {
		if (isEmpty()) {
			return null;
		}

		return stack.get(size() - 1);
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return stack.size();
	}

	public void clear() {
		stack.clear();
	}
}