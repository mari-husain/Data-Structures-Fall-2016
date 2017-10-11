import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Graph {

  // Keep a fast index to nodes in the map
  private Map<Integer, Vertex> vertexNames;

  /**
   * Construct an empty Graph with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Graph() {
    vertexNames = new HashMap<>();
  }

  /**
   * Adds a vertex to the graph. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the graph
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the graph
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the graph
   */
  public Collection<Vertex> getVertices() {
    return vertexNames.values();
  }

  /**
   * Gets the vertex object with the given name
   * 
   * @param name
   *          (String) name of the vertex object requested
   * @return (Vertex) vertex object associated with the name
   */
  public Vertex getVertex(String name) {
    return vertexNames.get(name);
  }

  /**
   * Adds a directed edge from vertex u to vertex v
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addEdge(int nameU, int nameV, Double cost) {
    if (!vertexNames.containsKey(nameU))
      throw new IllegalArgumentException(nameU + " does not exist. Cannot create edge.");
    if (!vertexNames.containsKey(nameV))
      throw new IllegalArgumentException(nameV + " does not exist. Cannot create edge.");
    Vertex sourceVertex = vertexNames.get(nameU);
    Vertex targetVertex = vertexNames.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Adds an undirected edge between vertex u and vertex v by adding a directed
   * edge from u to v, then a directed edge from v to u
   * 
   * @param name
   *          (String) name of vertex u
   * @param name2
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(int name, int name2, double cost) {
    addEdge(name, name2, cost);
    addEdge(name2, name, cost);
  }


  /**
   * Computes the euclidean distance between two points as described by their
   * coordinates
   * 
   * @param ux
   *          (double) x coordinate of point u
   * @param uy
   *          (double) y coordinate of point u
   * @param vx
   *          (double) x coordinate of point v
   * @param vy
   *          (double) y coordinate of point v
   * @return (double) distance between the two points
   */
  public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
  }

  /**
   * Computes euclidean distance between two vertices as described by their
   * coordinates
   * 
   * @param u
   *          (Vertex) vertex u
   * @param v
   *          (Vertex) vertex v
   * @return (double) distance between two vertices
   */
  public double computeEuclideanDistance(Vertex u, Vertex v) {
    return computeEuclideanDistance(u.x, u.y, v.x, v.y);
  }

  /**
   * Calculates the euclidean distance for all edges in the map using the
   * computeEuclideanCost method.
   */
  public void computeAllEuclideanDistances() {
    for (Vertex u : getVertices())
      for (Edge uv : u.adjacentEdges) {
        Vertex v = uv.target;
        uv.distance = computeEuclideanDistance(u.x, u.y, v.x, v.y);
      }
  }



  // STUDENT CODE STARTS HERE

  /**
   * Generates a series of n random vertices with coordinates from
   * 0-100, inclusive.
   * @param n - the number of vertices to generate
   */
  public void generateRandomVertices(int n) {
    vertexNames = new HashMap<>(); // reset the vertex hashmap
    Random randomGenerator = new Random();
    
    // generate random x, y coordinates, create new vertices, add them to the map
    for(int i = 0; i < n; i++) {
    	int x = randomGenerator.nextInt(101);
    	int y = randomGenerator.nextInt(101);
    	
    	vertexNames.put(i, new Vertex(i, x, y));
    }
    
    // generate edges between all vertices
    int numVertices = vertexNames.size();
    for(int i = 0; i < numVertices; i++) {
    	for(int j = 0; j < numVertices; j++) {
    		if(i != j) {
    			vertexNames.get(i).addEdge(new Edge(vertexNames.get(i), vertexNames.get(j), 1.0));
    		}
    	}
    }
    
    computeAllEuclideanDistances(); // compute distances
  }

  /**
   * Applies the nearest neighbor algorithm to the vertex map.
   * @return the shortest path, according to the nearest neighbor algorithm
   */
  public List<Edge> nearestNeighborTsp() {
	  
	  // reset all vertices' known values to false
	  Set<Integer> vertexIndices = vertexNames.keySet();
	  for(Integer num: vertexIndices) {
		  vertexNames.get(num).known = false;
	  }
	 
	  double shortestPathLength = Double.MAX_VALUE;
	  LinkedList<Edge> shortestPath = null;
	  
	  // iterate through all points as starting points, as per Piazza
	  for(Integer num: vertexIndices) {
		  LinkedList<Edge> path = new LinkedList<Edge>();
		  
		  // keep track of the length of the path
		  double thisPathLength = 0;
		  
		  // select the starting point
		  Vertex currentVertex = vertexNames.get(num);
		  currentVertex.known = true;
		  
		  // find the nearest neighbor for each successive vertex
		  // repeat n-1 times
		  for(int i = 0; i < vertexNames.size() - 1; i++) {
			 Edge shortestEdge = new Edge(new Vertex(0,0,0), new Vertex(0,0,0), Double.MAX_VALUE);
			 
			 // find the nearest neighbor of the current vertex
			 for(Edge edge: currentVertex.adjacentEdges) {
				 if(!edge.target.known && edge.distance < shortestEdge.distance) {
					 shortestEdge = edge;
				 }
			 }
			 
			 // mark nearest neighbor as known and add shortest edge to path
			 shortestEdge.target.known = true;
			 path.add(shortestEdge);
			 
			 // add edge length to total
			 thisPathLength += shortestEdge.distance;
			 
			// move on to the next vertex
			 currentVertex = shortestEdge.target; 
		 }
		  
		// connect the loop
		path.add(new Edge(path.getLast().target, path.getFirst().source, 
				computeEuclideanDistance(path.getLast().target, path.getFirst().source)));
		
		// compare this path length to shortest path length; update if necessary
		 if(thisPathLength < shortestPathLength) {
			 shortestPathLength = thisPathLength;
			 shortestPath = path;
		 }
	  }
    return shortestPath;
  }

  public List<Edge> bruteForceTsp() {
	  // initialize max value variables
	  LinkedList<Edge> shortestPath = null;
	  double shortestPathLength = Double.MAX_VALUE;
	  
	  // get all possible permutations of numbers 0 through n-1 and check every possible path,
	  // saving the 
	  LinkedList<LinkedList<Integer>> allPermutations = getAllPermutations(vertexNames.size());
	  for(LinkedList<Integer> nodePath: allPermutations) {
		  LinkedList<Edge> path = new LinkedList<Edge>();
		  
		  Vertex currentVertex = vertexNames.get(nodePath.poll());
		  while(!nodePath.isEmpty()) {
			  path.add(new Edge(currentVertex, vertexNames.get(nodePath.peek()), 
					  computeEuclideanDistance(currentVertex, vertexNames.get(nodePath.peek()))));
			  currentVertex = vertexNames.get(nodePath.poll());
		  }
		  
		  // connect the loop
		  path.add(new Edge(path.getLast().target, path.getFirst().source, 
				  computeEuclideanDistance(path.getLast().target, path.getFirst().source)));
		  
		  double distance = 0;
		  for(Edge edge: path) {
			  distance += edge.distance;
		  }
		  
		  if(distance < shortestPathLength) {
			  shortestPathLength = distance;
			  shortestPath = path;
		  }
	  }
	  
	  return shortestPath;
  }
  
  /**
   * Get all permutations of numbers 0 through n-1
   * @param n - the number of numbers to get permutations on
   * @return a list of all permutations of numbers 0 through n-1
   */
  private LinkedList<LinkedList<Integer>> getAllPermutations(int n) {
	  LinkedList<Integer> numberList = new LinkedList<Integer>();
	  
	  // build the initial number list to get permutations on
	  for(int i = 0; i < n; i++) {
		  numberList.addLast(i);
	  }
	  
	  // get all permutations on this list
	  return getAllPermutations(numberList);
  }
  
  /**
   * Get all permutations of the numbers in a list
   * @param n - the list of numbers to get permutations on
   * @return a list of all permutations of all numbers in the list
   */
  private LinkedList<LinkedList<Integer>> getAllPermutations(LinkedList<Integer> numbers) {
	  // initialize the list of permutations
	  LinkedList<LinkedList<Integer>> permutations = new LinkedList<LinkedList<Integer>>();
	  
	  // base case: list size is 1
	  if(numbers.size() == 1) {
		  permutations.add(numbers);
	  } else {
		  // choosing each number as first, find all permutations of the other numbers
		  for(int num: numbers) {
			  LinkedList<Integer> subNumbers = new LinkedList<Integer>();
			  
			  for(int otherNum: numbers) {
				  if(otherNum != num) {
					  subNumbers.add(otherNum);
				  }
			  }
			  
			  LinkedList<LinkedList<Integer>> subPermutations = getAllPermutations(subNumbers);
			  
			  // build permutations
			  for(LinkedList<Integer> subPermutation: subPermutations) {
				  LinkedList<Integer> permutation = new LinkedList<Integer>();
				  permutation.addLast(num);
				  for(int i: subPermutation) {
					  permutation.addLast(i);
				  }
				  permutations.add(permutation);
			  }
		  }
	  }
	  
	  return permutations;
  }

  // STUDENT CODE ENDS HERE



  /**
   * Prints out the adjacency list of the graph for debugging
   */
  public void printAdjacencyList() {
    for (int u : vertexNames.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertexNames.get(u).adjacentEdges) {
        sb.append(e.target.name);
        sb.append("(");
        sb.append(e.distance);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }
}
