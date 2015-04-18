package search;

/** 
 * Node class which encapsulates the concept of a node in a search that has a current state that has been reached from a parent node's state by taking a particular action 
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class Node implements Comparable<Node>{
	/**
	 * Parent node
	 */
	public final Node parent;
	/**
	 * Action taken to reach the current state from the parent
	 */
	public final Action action;
	/**
	 * Current State defined by this node
	 */
	public final State state;
	/**
	 * The value of this node
	 */
	public int nodeValue = Integer.MAX_VALUE;
	/**
	 * Total cumulative cost of the actions along the node's ancestral action path
	 */
	public final int pathCost;
	/**
	 * Total number of actions along the node's ancestral action path
	 */
	public final int pathLength;
	
	public Node(Node parent, Action action, State state) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		if (parent == null){
			pathCost = 0;
			pathLength = 0;
		} else if (action == null) {
			throw new IllegalArgumentException();
		} else {
			pathCost = parent.pathCost + action.getCost();
			pathLength = parent.pathLength + 1;
		}
	}

	@Override
	public int compareTo(Node that) {
		return (this.nodeValue < that.nodeValue ? -1
												: (this.nodeValue > that.nodeValue 	? 1
																					: 0));
	}
	
}
