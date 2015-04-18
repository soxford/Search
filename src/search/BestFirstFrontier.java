package search;

import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * A Frontier that implements the concept of providing the "best" node among those in the frontier as it's next output node. 
 * "Best" is defined by reference to a NodeFunction which provides a value for any given node.
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 */

public class BestFirstFrontier implements EditableFrontier {
	private PriorityQueue<Node> frontier = new PriorityQueue<Node>();
	private NodeFunction nodeFunction;
	private int maxFrontierSize = 0;
	
	public BestFirstFrontier( NodeFunction nodeFunction) {
		if(nodeFunction == null){
			throw new IllegalArgumentException();
		} else {
			this.nodeFunction = nodeFunction;
		}
	}

	@Override
	public void add(Node node) {
		//calculate the node value before adding so that the ordering is maintained
		node.nodeValue = this.nodeFunction.function(node);
		frontier.add(node);
		
		//update max frontier size if needed
		if (frontier.size() > this.maxFrontierSize){
			this.maxFrontierSize = frontier.size();
		}
	}

	@Override
	public void clear() {
		frontier.clear();
	}

	@Override
	public boolean isEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public Node remove() {
		return frontier.poll();
	}

	@Override
	public int getMaxFrontierSize() {
		return this.maxFrontierSize;
	}

	@Override
	public Node remove(State state) {
		Iterator<Node> it = frontier.iterator();
		while(it.hasNext()){
			Node n = it.next();
			if ( state.equals(n.state) ){
				it.remove();
				return n;
			}
		}
		return null;
	}

}
