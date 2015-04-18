package search;

import java.util.Set;

/**
 * The Search interface encapsulates the notion of a search algorithm which seeks a solution for reaching a State that satisfies a Goal Test 
 * by starting from a root State and pursuing actions to reach new states until a goal State is found or until no further new States can be reached.
 * The algorithm can also instantiate methods which find all solutions satisfying given constraints
 */
public interface Search {
	/**
	 * A method that, given a root State and a goal test, returns a node containing a solution or null if no solution can be found.
	 * @param root - the initial starting state for the search
	 * @param goalTest - the goal test which determines whether any given state is a goal state
	 * @return A Node which defines a solution path ending in a goal or if non-such was found, null
	 * @throws IllegalArgumentException if either argument is null
	 */
	public abstract Node findSolution(State root, GoalTest goalTest);
	
	// 
	/**
	 * A method that, given a root State and an inference goal test, returns a node containing a solution or null if no solution can be found.
	 * @param root - the initial starting state for the search
	 * @param inferenceGoalTest - the goal test which determines whether any given state is a goal state and also whether any given state may lead to a goal state
	 * @return A Node which defines a solution path ending in a goal or if non-such was found, null
	 * @throws IllegalArgumentException if either argument is null
	 */
	public abstract Node findSolution(State root, InferenceGoalTest inferenceGoalTest);
	
	/**
	 * A method that returns the number of nodes generated during the last search, returns -1 if no search has been run
	 * @return the number of nodes generated during the last search
	 */
	public abstract int getLastSearchNodeCount();
	
	/**
	 * (Optional) An optional method that, given a root State, a goal test, and a constraint on any solution path, returns the set of possible solutions satisfying the constraint
	 * @param root - the initial starting state for the search
	 * @param goalTest - the goal test which determines whether any given state is a goal state
	 * @param pathConstraint - a constraint of the solution path's that can be returned
	 * @return A Node which defines a solution path ending in a goal or if non-such was found, null
	 * @throws UnsupportedOperationException if the method is not implemented
	 * @throws IllegalArgumentException if either argument is null and the operation is supported
	 */
	public abstract Set<Node> findAllSolutions(State root, GoalTest goalTest, PathConstraint pathConstraint); 
}
