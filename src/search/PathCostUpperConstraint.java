package search;


/**
 * Path Cost Upper Constraint is an upper bound constraint on the total cost of the ancestral action path specified by a node
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class PathCostUpperConstraint implements PathConstraint {

	private final int lengthLimit;

	public PathCostUpperConstraint(int lengthLimit) {
		if (lengthLimit <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.lengthLimit = lengthLimit;
		}		
	}

	@Override
	public boolean isSatisfiedBy(Node node) {
		return (node.pathCost < this.lengthLimit);
	}

	@Override
	public boolean isSatisfiableByExtending(Node node) {
		return isSatisfiedBy(node);
	}

}
