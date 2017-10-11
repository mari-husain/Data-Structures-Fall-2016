/**
 * Tester class for problem 2 on homework #3. Takes a command line argument
 * for a text file and catalogues all its words using an AVL tree.
 * 
 * @author Maryam Husain
 * @version 10/31/2016
 */
public class Problem2 {
	public static void main(String[] args) {
		try {
			AvlTree myTree = new AvlTree(args[0]);
			myTree.printIndex();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
