package search;
/**
 * The A* function provides an implementation of a NodeFunction which will be used for A* search
 * The heuristic function used will need to be admissible and consistent
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class AStarFunction implements NodeFunction {
	NodeFunction heuristicFunction;
	
	public AStarFunction(NodeFunction heuristicFunction) {
		if (heuristicFunction == null){
			throw new IllegalArgumentException();
		} else {
			this.heuristicFunction = heuristicFunction;
		}
	}

	@Override
	//A* function returns g(node) + h(node) = cost to get to node + heuristic estimate of cost to get to goal
	public int function(Node node) {
		return node.pathCost + this.heuristicFunction.function(node);
	}

}
