/**
 * Tester class for problem 1 on homework #3. Tries two expressions, one bad
 * and one good, and attempts to create an expression tree out of them and
 * then evaluate it.
 * 
 * @author Maryam Husain
 * @version 10/31/2016
 */
public class Problem1 {
	public static void main(String[] args) {
		// good example
		String expressionString = "-1 2 + 3 * 8 - -27 + 4 /";
		System.out.println("Expression string: " + expressionString);
		
		ExpressionTree myTree = new ExpressionTree(expressionString);
		
		System.out.println("Expression evaluated: " + myTree.eval());
		System.out.println("Postfix: " + myTree.postfix());
		System.out.println("Prefix: " + myTree.prefix());
		System.out.println("Infix: " + myTree.infix());
		
		System.out.println();
		
		// bad example
		String expressionString2 = "1 2 + 3 * 8 - 27 + 4/";
		System.out.println("Expression string: " + expressionString2);
				
		myTree = new ExpressionTree(expressionString2);
		
		System.out.println("Expression evaluated: " + myTree.eval());
		System.out.println("Postfix: " + myTree.postfix());
		System.out.println("Prefix: " + myTree.prefix());
		System.out.println("Infix: " + myTree.infix());
	}
	
	/**
OUTPUT

Expression string: -1 2 + 3 * 8 - -27 + 4 /
Expression evaluated: -8
Postfix: -1 2 + 3 * 8 - -27 + 4 /
Prefix: / + - * + -1 2 3 8 -27 4
Infix: (((-1 + 2) * 3 - 8) + -27) / 4

Expression string: 1 2 + 3 * 8 - 27 + 4/
Error: Invalid operand 4/
Error: Invalid postfix expression
Error: Bad expression - cannot evaluate
Expression evaluated: 0
Postfix: Bad Expression - cannot print
Prefix: Bad Expression - cannot print
Infix: Bad Expression - cannot print

	 */
}
