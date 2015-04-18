package search;

/**
 * A Path Action Count Constraint is a constraint on the precise number of actions along a node's ancestral action path
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class PathActionCountConstraint implements PathConstraint {

	private final int countTarget;

	public PathActionCountConstraint(int countTarget) {
		if (countTarget < 0) {
			throw new IllegalArgumentException();
		} else {
			this.countTarget = countTarget;
		}		
	}

	@Override
	public boolean isSatisfiedBy(Node node) {
		return (node.pathLength == this.countTarget);
	}

	@Override
	public boolean isSatisfiableByExtending(Node node) {
		return (node.pathLength <= this.countTarget);
	}

}
