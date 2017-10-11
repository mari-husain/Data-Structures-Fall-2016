import java.util.Arrays;

/**
 * Mari Husain
 * mh3685
 * Problem2.java - A response to Homework 1, Coding Problem #2. 
 * Implements and tests a recursive binary search on the Rectangle class.
 * 
 * @author Maryam Husain
 * @version 9/13/2016
 */
public class Problem2 {
	public static void main(String[] args) {
		//create an array of rectangles to test binarySearch method on
		Rectangle[] rectangles = {
				new Rectangle(1, 1), 
				new Rectangle(1, 2), 
				new Rectangle(1, 3), 
				new Rectangle(1, 4), 
				new Rectangle(1, 5) };
		
		//sort the rectangles so they can be searched more efficiently
		Arrays.sort(rectangles);
		
		//test the method by searching for every rectangle in the array
		System.out.println("Here are our rectangles: " + Arrays.toString(rectangles));
		for(int i = 0; i < 5; i++) {
			int indexOfRect = Problem2.binarySearch(rectangles, rectangles[i]);
			System.out.println("The index of " + rectangles[i] + " is " + indexOfRect);
		}
		
		//test the method by searching for a rectangle that isn't in the array
		System.out.println("There is no [0x0] rectangle, so the index of that one is " + 
				Problem2.binarySearch(rectangles, new Rectangle()));
	}
	
	/**
	 * Searches an array for a given item using binary search.
	 * 
	 * @param a - the array to search
	 * @param x - the item to search for
	 * @return the index of the searched-for item, or -1 if the item cannot be found
	 */
	public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[] a, AnyType x) {
		return Problem2.binarySearchHelper(a, x, 0, a.length - 1);
	}
	
	/**
	 * A helper method for BinarySearch that implements a recursive binary search algorithm.
	 * 
	 * @param a - the array to search
	 * @param x - the item to search for
	 * @param start - the starting index for the area to search
	 * @param end - the ending index for the area to search
	 * @return the index of the searched-for item, or -1 if the item cannot be found
	 */
	private static <AnyType extends Comparable<AnyType>> int binarySearchHelper(AnyType[] a, AnyType x, int start, int end) {
		//while the midpoints still refer to a valid section of the array
		if(start <= end) {
			//find the midpoint of the area to be searched
			int midpoint = (start + end) / 2;

			if(a[midpoint].compareTo(x) > 0) { //if midpoint is greater than x, search the lower half of the array
				return Problem2.binarySearchHelper(a, x, start, midpoint - 1);
			} else if( a[midpoint].compareTo(x) < 0) { //if midpoint is smaller than x, search the upper half of the array
				return Problem2.binarySearchHelper(a, x, midpoint + 1, end);
			} else { //if x is neither larger nor smaller than the midpoint, it must be the midpoint
				return midpoint; 
			}
		}
		
		//if x cannot be located, return -1
		return -1;
	}
}
