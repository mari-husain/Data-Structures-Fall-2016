import java.util.Arrays;

/**
 * Mari Husain
 * mh3685
 * Problem1.java - A response to Homework 1, Coding Problem #1.
 * Implements and tests a prewritten findMax method on the Rectangle class. 
 * 
 * @author Maryam Husain
 * @version 9/13/2016
 */
public class Problem1 {
	public static void main(String[] args) {
		//create an array of rectangles with randomly set sides to test the findMax method on
		Rectangle[] rectangles = {
				new Rectangle((int)(Math.random() * 9 + 1), (int)(Math.random() * 9 + 1)), 
				new Rectangle((int)(Math.random() * 9 + 1), (int)(Math.random() * 9 + 1)),
				new Rectangle((int)(Math.random() * 9 + 1), (int)(Math.random() * 9 + 1)),
				new Rectangle((int)(Math.random() * 9 + 1), (int)(Math.random() * 9 + 1)),
				new Rectangle((int)(Math.random() * 9 + 1), (int)(Math.random() * 9 + 1)) };
		
		//find the largest
		Rectangle largest = Problem1.findMax(rectangles);
		
		//print out the list of rectangles and indicate the largest
		System.out.println("Here are our rectangles: " + Arrays.toString(rectangles));
		System.out.println("The rectangle with the largest perimeter is " + largest);
	}
	
	/**
	 * This code was provided by the assignment. Finds the largest item in a given array.
	 * 
	 * @param arr - an array of items
	 * @return the largest item in the array
	 */
	public static <AnyType extends Comparable<AnyType>>  AnyType findMax(AnyType[] arr) {
		  int maxIndex = 0;
		  for (int i = 1; i < arr.length; i++) {
		    if ( arr[i].compareTo(arr[maxIndex]) > 0 ) {
		       maxIndex = i;
		    }
		  }
		  return arr[maxIndex];
	}
}
