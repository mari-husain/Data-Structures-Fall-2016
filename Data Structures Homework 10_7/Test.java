/* this program tests the symbol balancer's reaction to a mismatched " character.
   It will consider the rest of the line to be part of the string (since you can't
   have a multi-line string). If we're printing out more than one error, then since 
   one of the parentheses is ignored since it's considered part of the string, 
   there will appear to be a mismatched } and an unmatched {  */

public class Test {
	public static void main(String[] args) {
		System.out.println("hello. is anybody out there?);
		System.out.println();
	}

	public int what() {
		int hello[] = { 0 };
		return (hello[0] + 1);
	}
}