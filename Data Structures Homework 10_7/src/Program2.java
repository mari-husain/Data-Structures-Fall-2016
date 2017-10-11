/**
 *  A tester class for the TwoStackQueue class. Tests the enqueue, dequeue, and isEmpty methods.
 * @author Mari Husain
 * @version October 4th, 2016
 */
public class Program2 {
	public static void main(String[] args) {
		TwoStackQueue<Integer> testQueue = new TwoStackQueue<Integer>();
		System.out.println("The size of the queue at the beginning is: "+ testQueue.size());

		testQueue.enqueue(0);
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		testQueue.enqueue(3);
		System.out.println("Dequeued: " + testQueue.dequeue());
		testQueue.enqueue(4);
		testQueue.enqueue(5);
		System.out.println("Dequeued: " + testQueue.dequeue());
		testQueue.enqueue(6);
		System.out.println("The size of the queue now is: "+ testQueue.size());

		System.out.println("This is what we have left in the queue:");
		while(!testQueue.isEmpty()) {
		    System.out.println(testQueue.dequeue());
	    }
	}
}