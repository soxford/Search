package search;


/**
 * Path Action Count Upper Constraint is a upper bound constraint on the total number of actions on the ancestral action path specified by a given node
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class PathActionCountUpperConstraint implements PathConstraint {

	private final int countLimit;

	public PathActionCountUpperConstraint(int countLimit) {
		if (countLimit < 0) {
			throw new IllegalArgumentException();
		} else {
			this.countLimit = countLimit;
		}		
	}

	@Override
	public boolean isSatisfiedBy(Node node) {
		return (node.pathLength <= this.countLimit);
	}

	@Override
	public boolean isSatisfiableByExtending(Node node) {
		return isSatisfiedBy(node);
	}

}