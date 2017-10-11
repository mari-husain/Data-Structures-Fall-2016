import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Written {
	public static void main(String[] args) {
		/**
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();

		l1.add(1); // 0
		l1.add(2); // 1
		l1.add(18); // 2
		l1.add(20); // 3
		l1.add(47); // 4

		l2.add(1); // 0
		l2.add(6); // 1
		l2.add(18); // 2
		l2.add(40); // 3
		l2.add(47); // 4
		l2.add(110); // 5
		l2.add(127); // 6
		l2.add(444); // 7
		l2.add(889); // 8
		l2.add(1000); // 9

		ArrayList<Integer> results = intersection(l1, l2);
		for (int num : results) {
			System.out.print(num + " ");
		} **/
		
		List<Integer> l = new ArrayList<Integer>();
		List<Integer> p = new ArrayList<Integer>();
		
		l.add(0);
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		l.add(8);
		l.add(9);
		
		p.add(2);
		p.add(4);
		p.add(6);
		p.add(8);
		p.add(10);
		
		ArrayList<Integer> intersection = intersection(p, l);
		for(int i: intersection)
			System.out.println(i);
	}

	/**
	 * 
	 * @param l
	 *            - the list of integers
	 * @param p
	 *            - the list of indices we want to print out
	 */
	public static void printLots(List<Integer> l, List<Integer> p) {
		// create two iterators to traverse the lists
		Iterator<Integer> pIterator = p.iterator();
		Iterator<Integer> lIterator = l.iterator();

		// keep track of our current indices
		int lIndex = 0;
		int pIndex = 0;

		// if p isn't empty, get the first index to search for
		if (pIterator.hasNext()) {
			pIndex = pIterator.next();
		} else { // if p is empty, print an error
			System.out.println("Oh no! Looks like p is empty.");
		}

		// traverse our two lists simultaneously
		while (lIterator.hasNext()) { // traverse l once (since p is sorted)
			if (lIndex == pIndex) { // if we're at a target index,
				// print what's at the target index,
				System.out.println("Index " + pIndex + ": " + lIterator.next()); 
				lIndex++; // increment our index in l
				if (pIterator.hasNext()) {
					pIndex = pIterator.next(); // and update to the next index
												// to search for
				} else {
					break; // stop traversing l when there are no more indices
							// to search for
				}
			} else { // if this isn't a target index,
				lIndex++; // increment our l index
				lIterator.next(); // and move on.
			}
		}
	}

	public static <AnyType extends Comparable<AnyType>> ArrayList<AnyType> intersection(List<AnyType> l1, List<AnyType> l2) {
		ArrayList<AnyType> intersection = new ArrayList<AnyType>(); //create list for the intersection

		// start at the beginning of both lists
		int index1 = 0;
		int index2 = 0;

		// traverse the two lists until one of them ends
		while (index1 < l1.size() && index2 < l2.size()) {
			
			// store references to the items at the indices we're checking
			AnyType thing1 = l1.get(index1);
			AnyType thing2 = l2.get(index2);
			
			// compare the objects at our indices.
			// if they're equal, add them to the intersection and move on to two new indices.
			// if not, increment the index of the smaller and compare again
			if (thing1.equals(thing2)) {
				intersection.add(thing1); 
				index1++; 
				index2++;
			} else if (thing1.compareTo(thing2) < 0) {
				index1++; 
			} else {
				index2++;
			}
		}

		return intersection;
	}
	
	
	public class TwoInOneStack<AnyType> {
		@SuppressWarnings("unchecked")
		AnyType[] myStack = (AnyType[]) new Object[100]; 
		
		//keep track of our stack tops on either end of the array
		int top1 = -1;
		int top2 = myStack.length;
		
		public void push(AnyType thing, int stack) {
			//push the thing onto the appropriate stack (either 1 or 2)
			if(stack == 1) {
				//move to the next space
				top1++;
				
				//check if that space is actually available
				if(top1 == top2) {
					System.out.println("Stack overflow - cannot add onto stack 1");
					top1--; //move top back, since we aren't adding anything there
					throw new StackOverflowError();
				} else {
					//add the thing to the stack if the space is free
					myStack[top1] = thing;
				}
			} else {
				//do the same here, except at the other end
				top2--;
				
				if(top1 == top2) {
					System.out.println("Stack overflow - cannot add onto stack 2");
					top1++; //move top back
					throw new StackOverflowError();
				} else {
					myStack[top2] = thing;
				}
			}
		}
		
		public AnyType pop(int stack) {
			//make sure the stack we want isn't empty
			if(!isEmpty(stack)) {
				//pop from the appropriate stack
				if(stack == 1) {
					//return the top item, then decrement top
					return myStack[top1--];
				} else {
					//ditto for stack 2
					return myStack[top2++];
				}
			} else {
				System.out.println("Stack underflow error on stack " + stack);
				return null;
			}
		}
		
		public AnyType peek(int stack) {
			//peek at the appropriate stack
			if(stack == 1) {
				//if the stack isn't empty, return the top item
				if(top1 > -1) {
					return myStack[top1];
				} else {
					//if it's empty, return an error
					System.out.println("Error - empty stack");
				}
			} else {
				//do the same for stack 2
				if(top2 < myStack.length) {
					return myStack[top2];
				} else {
					System.out.println("Error - empty stack");
				}
			} return null;
		}
		
		//return empty if top pointers point to impossible locations
		public boolean isEmpty(int stack){
			if(stack == 1) {
				return top1 > -1;
			} else {
				return top2 < myStack.length;
			}
		}
		
		//solve for the size of the given stack
		public int size(int stack) {
			if(stack == 1) {
				return top1 + 1;
			} else {
				return myStack.length - top2;
			}
		}
	}
	
	/**
	 * a.
	 * put 4 on s3
	 * put 3 on s2
	 * put 1 on output
	 * put 8 on s1
	 * put 2 on output
	 * move 3 to output
	 * move 4 to output
	 * put 7 on s2
	 * put 6 on s2
	 * put 9 on s3
	 * put 5 on output
	 * put 6 on output
	 * put 7 on output
	 * put 8 on output
	 * put 9 on output
	 * 
	 * b.
	 * 678915432
	 * 
	 * You will be forced to put 2, 3, and 4 onto the stack tracks. You'll then be forced to put 5 onto either 2,
	 * 3, or 4, trapping it, since you can't swap cars around on the stack tracks.
	 */
}
