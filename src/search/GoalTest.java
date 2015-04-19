package search;

/** 
 * An interface encapsulating the concept of a test that checks whether a given state is a valid goal state.
 * @author Simon Campbell <simonhmcampbell@gmail.com> with credit to Professor Peter Jeavons, Oxford
 *
 */
public interface GoalTest {
	boolean isGoal(State state);
}
