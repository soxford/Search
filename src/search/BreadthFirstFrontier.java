package search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of a Breadth First Frontier which provides nodes in FIFO order
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class BreadthFirstFrontier implements Frontier {
	private Queue<Node> queue = new LinkedList<Node>();
	private int maxFrontierSize = 0;
	
	public BreadthFirstFrontier() {
	}

	@Override
	public void add(Node node) {
		queue.add(node);
		if (queue.size() > maxFrontierSize) { //update the max size if needed
			maxFrontierSize = queue.size();
		}
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public Node remove() {
		return queue.remove();
	}

	@Override
	public int getMaxFrontierSize() {
		return maxFrontierSize;
	}

}
