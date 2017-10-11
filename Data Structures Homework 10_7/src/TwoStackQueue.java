import java.util.NoSuchElementException;

/**
 * Implements a queue made out of two separate stacks.
 * @author Mari Husain
 * @version October 2nd, 2016
 */
public class TwoStackQueue<AnyType> implements MyQueue<AnyType> {
	
	//our eponymous two stacks
	MyStack<AnyType> s1 = new MyStack<AnyType>();
	MyStack<AnyType> s2 = new MyStack<AnyType>();

	/**
	 * Adds an item to the queue.
	 * @param x - the item to be added
	 */
	public void enqueue(AnyType x) {
		s1.push(x);
	}
	
	/**
	 * Gets the next item in the queue.
	 * @return the next item in the queue
	 */
	public AnyType dequeue() {
		AnyType myObject;
		
		if(s1.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		//use the slinky method to get the the bottom of the stack
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
		} 
		
		//save the first item in the queue
		myObject = s2.pop();
		
		//put everything back
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		
		return myObject;
	}
	
	/**
	 * Returns whether or not the queue is empty.
	 * @return whether or not the queue is empty
	 */
	public boolean isEmpty() {
		return s1.isEmpty();
	}
	
	/**
	 * Returns the size of the queue.
	 * @return the size of the queue
	 */
	public int size() {
		return s1.size();
	}
}
