package search;

/**
 * An Editable Frontier is a Frontier from which you can remove nodes according to their terminal state as well as according to the defined remove operation
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public interface EditableFrontier extends Frontier {
	/**
	 * If the frontier is not empty, and contains a node with the provide State as its terminus, the node is removed. If there are multiple copies of the state in the frontier only one is removed. Otherwise, returns null.
	 * @param state - the state to find in the frontier and remove
	 * @return the Node removed
	 */
	public Node remove(State state);
}
