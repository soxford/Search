package search;


/**
 * The path distance returns function gives the total cumulative cost of the actions along the node's ancestral action path
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class PathCostFunction implements NodeFunction {

	public PathCostFunction() {
	}

	@Override
	public int function(Node node) {
		//Our node function gives us the journey distance along this node's parent node path
		return node.pathCost;
	}

}
