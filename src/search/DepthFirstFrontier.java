package search;

import java.util.Stack;

/** 
 * Implementation of a Depth First Frontier providing Nodes in a LIFO order
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class DepthFirstFrontier implements Frontier {
	
	private Stack<Node> stack = new Stack<Node>();
	private int maxFrontierSize = 0;
	
	public DepthFirstFrontier() {
	}

	@Override
	public void add(Node node) {
		stack.push(node);
		if (stack.size() > maxFrontierSize){
			maxFrontierSize = stack.size();
		}
	}

	@Override
	public void clear() {
		stack.clear();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public Node remove() {
		return stack.pop();
	}

	@Override
	public int getMaxFrontierSize() {
		return maxFrontierSize;
	}

}
