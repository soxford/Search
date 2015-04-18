package search;

/**
 * An Interface that encapsulates the concept of a function on Nodes which can be used to provide heuristic functions amongst other things.
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public interface NodeFunction {
	public int function(Node node);
}
