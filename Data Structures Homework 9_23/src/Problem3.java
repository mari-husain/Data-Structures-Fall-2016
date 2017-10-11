/**
 * Mari Husain
 * mh3685
 * Problem2.java - A response to Homework 1, Coding Problem #3. 
 * Implements and calculates the runtime of three prewritten code fragments on increasingly large inputs
 * to test Big-O.
 * 
 * @author Maryam Husain
 * @version 9/15/16
 */
public class Problem3 {
	public static void main(String[] args) {
		//run algorithms 1 and 2 on increasingly large sets of data
		for(int alg = 1; alg <= 2; alg++) {
			System.out.println("Algorithm #" + alg);
			
			System.out.println("n = 1, t = " + runFragment(alg, 1));
			System.out.println("n = 10, t = " + runFragment(alg, 10));
			System.out.println("n = 100, t = " + runFragment(alg, 100));
			System.out.println("n = 500, t = " + runFragment(alg, 500));
			System.out.println("n = 1000, t = " + runFragment(alg, 1000));
			System.out.println("n = 10000, t = " + runFragment(alg, 10000));
		}
		
		//run algorithm 3 on increasingly large sets of data (larger than those for 1 and 2 because it's fast)
		System.out.println("Algorithm #3");
		
		System.out.println("n = 1000, t = " + runFragment(3, 1000));
		System.out.println("n = 1000000, t = " + runFragment(3, 1000000));
		System.out.println("n = 100000000, t = " + runFragment(3, 100000000));
		System.out.println("n = 500000000, t = " + runFragment(3, 500000000));
		System.out.println("n = 1000000000, t = " + runFragment(3, 1000000000));
	}
	
	/**
	 * Runs the given fragment of code with the given amount of data and returns the time it took to complete
	 * the algorithm.
	 * 
	 * @param fragmentNumber - which code fragment (1, 2, or 3) to run
	 * @param n - the size of the input to give the chosen algorithm
	 * @return the time it took to run the data through the algorithm
	 */
	public static long runFragment(int fragmentNumber, int n) {
		//record start time so we can calculate elapsed time
		long startTime = System.nanoTime();
		
		//run the algorithm
		if(fragmentNumber == 1) {
			int sum = 0;
			for ( int i = 0; i < 23; i ++)
			    for ( int j = 0; j < n ; j ++)
			        sum = sum + 1;
		} else if(fragmentNumber == 2) {
			int sum = 0;
			for ( int i = 0; i < n ; i ++)
			    for ( int k = i ; k < n ; k ++)
			        sum = sum + 1;
		} else if(fragmentNumber == 3) {
			foo(n, 2);
		} else {
			System.out.println("Invalid fragment requested.");
		}
		
		//calculate the elapsed time and return it
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
	
	public static int foo(int n, int k) {
		try { 
			Thread.sleep(10);
		} catch(Exception e) {
			System.out.println(e); 
		}
	    if(n<=k)
	        return 1;
	    else
	        return foo(n/k,k) + 1;
	}
}
