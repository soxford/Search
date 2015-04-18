package search;

/**
 * Path Constraints encapsulate the concept of a constraint on the path that is defined by a node. 
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public interface PathConstraint {
	/**
	 *  A method which, given a node, returns false iff the node does not satisfy the required constraint
	 *  @throws IllegalArgumentException if a node provided is null
	 */
	public boolean isSatisfiedBy(Node node);
	
	/**
	 * A method which, given a node, returns false only if the node cannot be extended in any way to give a node that satisfies the required constraint
	 * @param node - the node to check
	 * @return false only if the node cannot be extended in any way to give a node that satisfies the required constraint
	 */
	public boolean isSatisfiableByExtending(Node node);
}
