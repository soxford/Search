package search;
/** 
 * A Frontier encapsulates the collection of search Nodes (not States!) that are on the frontier of a frontier search 
 * (i.e. they have been discovered but not explored/processed to check whether they are a solution)
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 */
public interface Frontier {

	/**
	 * Adds the provided node to the frontier.
	 * @param node - the node to add to the frontier
	 */
	public void add(Node node);
	
	/**
	 * Clears the contents of a frontier so that it becomes empty.
	 */
	public void clear();
	
	/**
	 * Checks whether the frontier is empty
	 * @return true iff the frontier is empty
	 */
	public boolean isEmpty();
	
	//
	/**
	 * If the frontier is not empty, this method removes the next node from the frontier. If the frontier is empty the behaviour is undefined
	 * @return the next node on the frontier
	 */
	public Node remove();
	
	/**
	 * A method that returns the maximum number of nodes stored on the frontier since the frontier was created
	 * @return The maximum number of nodes stored on the frontier since the frontier was created
	 */
	public int getMaxFrontierSize();
}
