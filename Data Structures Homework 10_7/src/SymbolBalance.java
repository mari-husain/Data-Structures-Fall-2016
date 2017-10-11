import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * A class that can read in a file and check whether {, [, (, ", /*, and their
 * corresponding closing symbols are balanced using a stack.
 * 
 * @author Mari Husain
 * @version October 2nd, 2016
 */
public class SymbolBalance {

	MyStack<Character> symbolStack;

	boolean commentIgnore; //are we inside a comment?
	
	//for the homework, we only need to print the first error, as far
	//as I know - but if we need to print all of them, just set
	//printOnlyOneError to false.
	boolean printOnlyOneError = true; //do we print out only the first error?
	boolean stillSearching; //are we still looking for an error?
	
	public SymbolBalance() {
		symbolStack = new MyStack<Character>();
		commentIgnore = false;
		stillSearching = true;
	}
	
	public void reset() {
		symbolStack = new MyStack<Character>();
		commentIgnore = false;
		stillSearching = true;
	}

	public static void main(String[] args) {

		// run the test case
		SymbolBalance balancer = new SymbolBalance();

		if(args.length > 0)
			balancer.readFile(args[0]);
		
	}

	/**
	 * Reads in a whole file and checks it line by line for symbol matches.
	 * 
	 * The code for the BufferedReader was written with reference to the
	 * following Stack Overflow question:
	 * http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
	 * See response by user Knubo: http://stackoverflow.com/a/4716623
	 * 
	 * @param filename - the name of the file to be read
	 **/
	public void readFile(String filename) {
		//reset our instance variables
		this.reset();
		
		
		//read in the file
		try {
			System.out.println("Reading file: " + filename);
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();

			//check it line by line
			//if we're only printing out the first error, only continue reading until
			//we've found our error, then stop!
			while (line != null && (!printOnlyOneError || stillSearching)) {
				readLine(line);
				line = reader.readLine();
			}

			//close the reader, check the stack for any unmatched symbols
			reader.close();
			finalCheck();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Reads a line from a file and checks the characters it contains.
	 * 
	 * @param line - the line to be checked
	 **/
	public void readLine(String line) {
		
		boolean stringIgnore = false; //are we in the middle of a string literal?

		//read through the line character by character
		for (int index = 0; index < line.length(); index++) {
			char character = line.charAt(index);

			try {

				// if we're not skipping this text because it's a string or a comment, 
				// check if we should add it or match it
				if (!stringIgnore && !commentIgnore) {
					// check if we should add characters to the stack
					if (character == '{' || character == '(' || character == '[') {
						symbolStack.push(character);
					} else if (character == '"') { // if this is an opening quote
						stringIgnore = true;
						symbolStack.push(character);
					} else if (character == '/') { // check if there's an open comment
						if (index + 1 < line.length()) {
							if (line.charAt(index + 1) == '*') {
								symbolStack.push('c'); // substitute a 'c'
														// character for a
														// comment
								commentIgnore = true;
							}
						}
					}

					// check if we should match characters off the stack
					else if (character == '}') {
						if (symbolStack.pop() != '{') {
							System.out.println("Error! Character " + character + " is mismatched.");
							stillSearching = false;
						}
					} else if (character == ')') {
						if (symbolStack.pop() != '(') {
							System.out.println("Error! Character " + character + " is mismatched.");
							stillSearching = false;
						}
					} else if (character == ']') {
						if (symbolStack.pop() != '[') {
							System.out.println("Error! Character " + character + " is mismatched.");
							stillSearching = false;
						}
					} else if (character == '*') {
						if (index + 1 < line.length()) {
							if (line.charAt(index + 1) == '/') {
								index++;
								if (symbolStack.pop() != 'c') {
									System.out.println("Error! Character */ is mismatched.");
									stillSearching = false;
								} else {
									commentIgnore = false;
								}
							}
						}
					}
				}

				// if we're inside a string, ignore all aside from a closing "
				else if (stringIgnore) {
					if (character == '"') {
						if (symbolStack.pop() != '"') {
							System.out.println("Error! Character " + character + " is mismatched.");
							stillSearching = false;
						} else {
							stringIgnore = false;
						}
					}
				}

				// if we're inside a comment, ignore all aside from a closing */
				else if (commentIgnore) {
					if (character == '*') {
						if (index + 1 < line.length()) {
							if (line.charAt(index + 1) == '/') {
								index++;
								if (symbolStack.pop() != 'c') {
									System.out.println("Error! Character " + character + " is mismatched.");
									stillSearching = false;
								} else {
									commentIgnore = false;
								}
							}
						}
					}
				}
			} catch (NoSuchElementException e) {
				// if there is a closing symbol with no opening symbol
				System.out.println("Error! There is a closing " + character + " with no opening character.");
				stillSearching = false;
			}
		}

		// if we've reached the end of the line and we still have an open
		// string, print the error and
		// pop it off the stack since strings can't be multi-line
		if (stringIgnore) {
			System.out.println("Error! Unmatched \"");
			symbolStack.pop();
			stringIgnore = false;
			stillSearching = false;
		}
	}

	/**
	 * Performs a final check on the symbol stack to check if there are any
	 * unmatched characters remaining.
	 */
	public void finalCheck() {
		
		//if the stack still has unmatched characters remaining on it, give errors
		if (!symbolStack.isEmpty() && (!printOnlyOneError || stillSearching)) {
			//if it's a comment, replace the 'c' char before printing
			while (!symbolStack.isEmpty()) {
				if (symbolStack.peek() == 'c') {
					System.out.println("Error! Unmatched /*");
					symbolStack.pop();
					stillSearching = false;
				} else {
					System.out.println("Error! Unmatched " + symbolStack.pop());
					stillSearching = false;
				}
			}
			System.out.println("End of file.");
		} else {
			System.out.println("End of file.");
		}
	}
}
