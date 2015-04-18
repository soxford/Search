package search;

/**
 * Inference Goal Tests extend Goal Tests to provide the opportunity to check whether a given state could possibly lead to a goal state.
 * If not then this allows a search to be pruned earlier than otherwise, thus improving search times.
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public interface InferenceGoalTest extends GoalTest {
	//checks to see whether the given state could ever be extended to reach the goal
	public boolean isViable(State state);
}
