package search;

/** 
 * An interface encapsulating the concept of a test that checks whether a given state is a valid goal state.
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public interface GoalTest {
	boolean isGoal(State state);
}
