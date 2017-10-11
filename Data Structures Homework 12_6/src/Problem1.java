/**
 * mh3685 Homework 5 Problem1.java
 * 
 * This class is a response to Homework 5, Programming Problem #1. Implements an interative mergesort
 * and tests that sort on three test arrays.
 * @author Mari Husain
 * @version December 6th, 2016
 */
public class Problem1 {
	
	/**
	 * Tests the mergeSort method on three test arrays.
	 * @param args - command line args, variable not used
	 */
	public static void main(String[] args) {
		Integer[] myArray = {1,9,4,2,8,7,3,7,8};
		System.out.print("Initial array: ");
		printArray(myArray);
		mergeSortB(myArray);
		
		Integer[] myArray2 = {5,4,3,2,1,6,9,8,7,4,5,6,7,4,2};
		System.out.print("Initial array: ");
		printArray(myArray2);
		mergeSortB(myArray2);
		
		Integer[] myArray3 = {};
		System.out.print("Initial array: ");
		printArray(myArray3);
		mergeSortB(myArray3);
	}
	
	/**
	 * A method to iteratively mergesort a given array.
	 * @param inputArray - the array to mergesort
	 */
	public static void mergeSortB(Integer[] inputArray) {
		// if empty array, print an error and stop.
		if(inputArray.length == 0) {
			System.out.println("Error: Empty array. Nothing to sort");
		} else if(inputArray.length > 1) { // array of length 1 is already sorted, so no need to do anything.
			
			// sort the array in blocks, merging sets of two adjacent blocks together.
			// block size doubles on each pass through the array.
			for(int blockSize = 1; blockSize < inputArray.length; blockSize *= 2) {
				for (int leftPos = 0; leftPos + blockSize < inputArray.length; leftPos += 2 * blockSize) {
					int rightPos = leftPos + blockSize;
					int rightEnd = Math.min(rightPos + blockSize - 1, inputArray.length - 1);
					
					merge(inputArray, new Integer[inputArray.length], leftPos, rightPos, rightEnd);
				}
			}
		}
		
		System.out.print("Sorted array: ");
		printArray(inputArray);
	}
	
	/**
	* Internal method that merges two sorted halves of a subarray.
	* Taken from the Weiss Code.
	* @param a an array of Comparable items.
	* @param tmpArray an array to place the merged result.
	* @param leftPos the left-most index of the subarray.
	* @param rightPos the index of the start of the second half.
	* @param rightEnd the right-most index of the subarray.
	*/
	private static void merge(Integer[] a, Integer[] tmpArray,
	int leftPos, int rightPos, int rightEnd ) {
	   int leftEnd = rightPos - 1;
	   int tmpPos = leftPos;
	   int numElements = rightEnd - leftPos + 1;
	  
	   // Main loop
	   while( leftPos <= leftEnd && rightPos <= rightEnd )
	   if( a[ leftPos ] < a[ rightPos ] )
		   tmpArray[ tmpPos++ ] = a[ leftPos++ ];
	   else
		   tmpArray[ tmpPos++ ] = a[ rightPos++ ];	   
	  
	   while( leftPos <= leftEnd ) // Copy rest of first half
		   tmpArray[ tmpPos++ ] = a[ leftPos++ ];
	  
	   while( rightPos <= rightEnd ) // Copy rest of right half
		   tmpArray[ tmpPos++ ] = a[ rightPos++ ];
	  
	   // Copy tmpArray back
	   for( int i = 0; i < numElements; i++, rightEnd-- )
		   a[ rightEnd ] = tmpArray[ rightEnd ];
	 }
	
	public static void printArray(Integer[] myArray) {
		System.out.print("[ ");
		for(Integer num: myArray)
			System.out.print(num + " ");
		System.out.println("]");
	}
}
