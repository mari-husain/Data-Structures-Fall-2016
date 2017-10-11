import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Solution to Homework 4, Problem #1
 * Implements a spellchecker that constructs a dictionary from a dictionary
 * textfile. Can check a textfile for spelling errors.
 * 
 * The dictionary and the textfile to check are passed into the main method
 * via the command-line arguments.
 * @author Maryam Husain
 * @version November 21st, 2016
 */
public class SpellChecker {
	// the dictionary the spellchecker uses to check words in the file
	private HashSet<String> dictionary;
	
	/**
	 * Tester method that takes in two arguments via the command line.
	 * args[0] should be the name of the dictionary and args[1] should
	 * be the name of the file to check.
	 * @param args - the command line arguments
	 */
	public static void main(String[] args) {
		String dictionaryName;
		String fileToCheck;
		
		try {
			dictionaryName = args[0];
			fileToCheck = args[1];
		
			SpellChecker mySpellChecker = new SpellChecker(dictionaryName);
			mySpellChecker.checkFile(fileToCheck);
		} catch(Exception e) {
			System.out.println("Error: Please enter a dictionary name and a file name.");
		}
	}
	
	/**
	 * Creates a spellchecker mapped to a given dictionary.
	 * @param dictionaryName - the name of the textfile containing the words in 
	 * the dictionary.
	 */
	public SpellChecker(String dictionaryName) {
		// initialize hashtable
		dictionary = new HashSet<String>();
		
		// read in the dictionary file
    	System.out.println("Constructing dictionary from file: " + dictionaryName);
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(dictionaryName));
    		String line = reader.readLine();

    		//read in the dictionary file line by line
    		while (line != null) {
    			addLineToDictionary(line);
    			line = reader.readLine();
    		}

    		//close the reader
    		reader.close();
    	} catch (IOException e) {
    		System.out.println("Error: Dictionary not found");
    	}
    	System.out.println("Dictionary successfully created.");
    	System.out.println();
	}
	
	/**
	 * Check every word in the file against the words in the dictionary.
	 * @param fileToCheck - the name of the text file to check
	 */
	public void checkFile(String fileToCheck) {
		//read in the file
    	System.out.println("Checking file: " + fileToCheck);
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(fileToCheck));
    		String line = reader.readLine(); //initialize line variable with first line
    		
    		int lineNumber = 1; //start counting line numbers at the first line

    		//check each line 
    		while (line != null) {
    			checkLine(line, lineNumber);
    			line = reader.readLine();
    			lineNumber++;
    		}

    		//close the reader
    		reader.close();
    	} catch (IOException e) {
    		System.out.println("Error: File not found");
    	}
    	
    	System.out.println("Finished spellcheck.");
    	System.out.println();
	}
	
	/**
	 * Add a line in a dictionary file to the spellchecker's dictionary.
	 * @param line - the line of the dictionary file to add
	 */
	private void addLineToDictionary(String line) {
		String[] words = line.split(" "); // split the words in a line at spaces
		if(words.length == 0)
			return;
		for(String word:words) {
			// clean the word of caps and punctuation and add it to the dictionary
			word = word.toLowerCase();
			word = cleanWord(word);
			dictionary.add(word);
		}
	}
	
	/**
	 * Check that all the words in a line are spelled correctly.
	 * @param line - the line of words to check
	 * @param lineNumber - the line number in the text file
	 */
	private void checkLine(String line, int lineNumber) {
		String[] words = line.split(" "); // split the words in a line at spaces
		
		for(String word:words) {
			if(word.length() > 0) {
				// clean the word of caps and punctuation
				word = word.toLowerCase();
				word = cleanWord(word);
				
				//check if the word is in the dictionary - if not, print an error and list
				//correction suggestions
				if(!dictionaryContainsWord(word)) {
					System.out.println("Misspelled word on line " + lineNumber + ": " + word);
					System.out.println("Suggestions: " + getSuggestions(word));
				}
			}
		}
	}
	
	/**
	 * Checks if the dictionary contains a given word. If not, gives possible
	 * alternative spellings.
	 * @param word - the word to check
	 * @return whether or not the word is a word in the dictionary or a number
	 */
	private boolean dictionaryContainsWord(String word) {
		try {
			//if the word is a number, return true
			Integer.parseInt(word);
			return true;
		} catch (Exception e) {}
		
		return dictionary.contains(word);
	}
	
	private LinkedList<String> getSuggestions(String word) {
		LinkedList<String> suggestionList = new LinkedList<String>();
		String possibleWord = "";
		
		//check if adding a letter would work
		for(int i = 0; i < word.length(); i++) {
			for(int j = 'a'; j <= 'z'; j++) {
				possibleWord = word.substring(0, i) + (char)j + word.substring(i, word.length());
				if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
					suggestionList.add(possibleWord);
				}
			}
		}
		
		for(int j = 'a'; j <= 'z'; j++) {
			possibleWord = word + (char)j;
			if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
				suggestionList.add(possibleWord);
			}
		}
		
		//check if removing a letter would work
		for(int i = 0; i < word.length() - 1; i++) {
			possibleWord = word.substring(0, i) + word.substring(i + 1, word.length());
			if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
				suggestionList.add(possibleWord);
			}
		}
		
		possibleWord = word.substring(0, word.length() - 1);
		if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
			suggestionList.add(possibleWord);
		}
		
		//check if swapping two adjacent letters would work (for 3+ letter words)
		for(int i = 1; i < word.length() - 1; i++) {
			char[] wordArray = word.toCharArray();
				
			//swap the current character with the previous character
			char charAtI = wordArray[i];
			wordArray[i] = wordArray[i-1];
			wordArray[i-1] = charAtI;
				
			possibleWord = new String(wordArray);
				
			if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
				suggestionList.add(possibleWord);
			}
			
			//swap the current character with the next character
			wordArray = word.toCharArray();
			wordArray[i] = wordArray[i+1];
			wordArray[i+1] = charAtI;
			
			possibleWord = new String (wordArray);
			
			if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
				suggestionList.add(possibleWord);
			}
		}
		
		//for two-letter words, check if swapping would work
		if(word.length() == 2) {
			char[] wordArray = word.toCharArray();
			char charAt0 = wordArray[0];
			wordArray[0] = wordArray[1];
			wordArray[1] = charAt0;
			
			possibleWord = new String(wordArray);
			
			if(dictionaryContainsWord(possibleWord) && !suggestionList.contains(possibleWord)) {
				suggestionList.add(possibleWord);
			}
		}
		
		return suggestionList;
	}
	
	/**
	 * Cleans spaces, punctuation, and leading quotes off of a word.
	 * @param word - the word to clean
	 * @return the cleaned word
	 */
	private String cleanWord(String word) {
    	word = word.trim();
    	word = word.replaceAll("[!\"#$%&()*+,-./:;<=>?@\\^_`{|}~]", "");
    	word = word.replaceAll("\\[", "");
    	word = word.replaceAll("\\]", "");
    	
    	if(word.charAt(0) == '\'' && word.length() > 1)
    		word = word.substring(1);
    	if(word.charAt(word.length() - 1) == '\'' && word.length() > 1)
    		word = word.substring(0, word.length() - 1);
    	
    	return word;
    }
}
