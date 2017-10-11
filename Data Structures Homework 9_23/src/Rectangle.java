/**
 * Mari Husain
 * mh3685
 * Rectangle.java - A rectangle class that is comparable by perimeter. 
 * 
 * @author Maryam Husain
 * @version 9/13/2016
 */
public class Rectangle implements Comparable<Rectangle> {
	private int length;
	private int width;
	
	/**
	 * Default constructor for Rectangle. Sets both length and width to 0.
	 */
	public Rectangle() {
		length = 0;
		width = 0;
	}
	
	/**
	 * Constructor for Rectangle that takes a length and a width.
	 * 
	 * @param myLength - length of the rectangle
	 * @param myWidth - width of the rectangle
	 */
	public Rectangle(int myLength, int myWidth) {
		length = myLength;
		width = myWidth;
	}
	
	/**
	 * Get the length of the rectangle.
	 * 
	 * @return the rectangle's length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Get the width of the rectangle.
	 * 
	 * @return the rectangle's width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Calculate and return the perimeter of the rectangle.
	 * 
	 * @return the rectangle's perimeter
	 */
	public int getPerimeter() {
		return 2 * length + 2 * width;
	}
	
	/* (non-Javadoc)
	 * Implementation of compareTo method as per the Comparable interface. Compares by perimeter.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Rectangle other) {
		return this.getPerimeter() - other.getPerimeter();
	}
	
	/* (non-Javadoc)
	 * Implementation of toString. Returns a representation of the rectangle's dimensions.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[" + length + "x" + width + "]";
	}
}
