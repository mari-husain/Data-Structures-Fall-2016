import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A class that keeps track of the k best items in a data set.
 * @author Mari Husain
 * @version November 21st, 2016
 *
 * @param <T> the datatype of the dataset
 */
public class KBestCounter <T extends Comparable<? super T>> {
	PriorityQueue<T> heap; // the heap that keeps track of k best items 
    int k; // the number of best items we want to keep track of

    /**
     * Initialize a new KBestCounter
     * @param k - the number of best items to keep track of
     */
    public KBestCounter(int k) {
    	// initialize instance variables
    	this.k = k;
        heap = new PriorityQueue<T>();
    }

    /**
     * Processes the next element in the dataset.
     * @param x - the element we want to process
     */
    public void count(T x) {
    	// if we have fewer than k elements, add x to the heap, since
    	// obviously it must be one of the k best.
    	
    	// Otherwise, if it's larger than the smallest of the k best, 
    	// add x in and remove the smallest element in the heap.
    	
    	if(heap.size() < k) {
    		heap.add(x);
    	} else if (x.compareTo(heap.peek()) > 0) {
    		heap.add(x);
    		heap.poll();
    	}
    }

    /**
     * Return a list of the k best items in the dataset.
     * @return
     */
    public List<T> kbest() {
    	// copy our heap over to a temporary variable to preserve
    	// the heap
        PriorityQueue<T> temp = new PriorityQueue<T>(heap);
        LinkedList<T> kbest = new LinkedList<T>(); // data to return
        
        // if the heap size is less than k, return however many we have.
        // otherwise, we'll return k items.
        int numToGet = heap.size();
        
        // add to the list, maintaining ordering from largest to smallest.
        for(int i = 0; i < numToGet; i++) {
        	kbest.addFirst(temp.poll());
        }
        
        return kbest;
    }
}
