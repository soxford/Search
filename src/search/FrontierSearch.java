package search;

import java.util.HashSet;
import java.util.Set;


/**
 * Abstract class providing the structure of search as performed in graph and tree search and similar Frontier based search algorithms.
 * The Frontier stores and supplies nodes for exploration
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 */
public abstract class FrontierSearch extends Search {
	protected final Frontier frontier;
	protected int lastSearchNodeCount = -1; //flag value for no searches run

	public FrontierSearch(Frontier frontier) {
		if (frontier == null){
			throw new IllegalArgumentException();
		} else {
			this.frontier = frontier;
		}	
	}

	@Override
	//Search using the goal test from the root
	public Node findSolution(State root, GoalTest goalTest) {
		//validate input
		if(root == null || goalTest == null){
			throw new IllegalArgumentException();
		}

		lastSearchNodeCount = 0;
		//INITIALIZE the search 
		initializeSearch(root);

		//search frontier until empty
		while(!frontier.isEmpty()){
			//find next candidate
			Node nextCandidate = frontier.remove();
			//check if the candidate is a solution
			if(goalTest.isGoal(nextCandidate.state)){
				return nextCandidate;
			} else { 
				//else update any sets tracking the search
				updateSearchSets(nextCandidate.state);

				//EXPAND the state to add the undiscovered results of possible actions to the frontier
				expandState(nextCandidate);				
			}
		}
		//if no goal is found then return null
		return null;
	}

	@Override
	//Search with inference using the goal test from the root
	public Node findSolution(State root, InferenceGoalTest inferenceGoalTest) {
		//validate input
		if(root == null || inferenceGoalTest == null){
			throw new IllegalArgumentException();
		}
		lastSearchNodeCount = 0;

		//INITIALIZE the search 
		initializeSearch(root);

		//search frontier until empty
		while(!frontier.isEmpty()){
			//find next candidate
			Node nextCandidate = frontier.remove();
			//check if the candidate is a solution
			if(inferenceGoalTest.isGoal(nextCandidate.state)){
				return nextCandidate;
			} else { 
				//else update any sets tracking the search
				updateSearchSets(nextCandidate.state);

				//if state is still potentially viable EXPAND the state to add the undiscovered results of possible actions to the frontier
				if(inferenceGoalTest.isViable(nextCandidate.state)){
					expandState(nextCandidate);				
				}
			}
		}
		//if no goal is found then return null
		return null;
	}
	
	/**
	 * This method must be implemented to initialize the search. This includes initializing the frontier's state and any search sets used for tracking the search
	 * @param root - the initial starting state for the search
	 */
	protected abstract void initializeSearch(State root);

	/**
	 * This method must be implemented to update any sets being used to track the search as part of the algorithm.
	 * @param state - the State with which to update the search sets
	 */
	protected abstract void updateSearchSets(State state);

	/**
	 * This method must be implemented to update the frontier's state based on the outcomes possible from starting at the nextCandidate solution and taking the possible actions that its terminal State allows
	 * @param nextCandidate
	 */
	protected abstract void expandState(Node nextCandidate);

	@Override
	public int getLastSearchNodeCount() {
		return lastSearchNodeCount;
	}


	public Set<Node> findAllSolutions(State root, GoalTest goalTest, PathConstraint pathConstraint){

		//validate input
		if (root == null || goalTest == null || pathConstraint == null) {
			throw new IllegalArgumentException();
		}
		
		//create the set to contain all solutions
		Set<Node> solutions = new HashSet<Node>();

		//confirm that the root node might extend to meet the required constraint, if so 
		//initialize our search from the root
		if (pathConstraint.isSatisfiableByExtending(new Node(null, null, root))) {
			initializeSearch(root);
		}

		//iterate through all possible solutions until the frontier has no further candidates
		while(!frontier.isEmpty()){
			//get the next candidate node
			Node nextCandidate = frontier.remove();

			//check whether it is a solution and satisfies the constraint
			if(goalTest.isGoal(nextCandidate.state) && pathConstraint.isSatisfiedBy(nextCandidate)){
				solutions.add(nextCandidate);
			}

			//expand the nextCandidate's subsequent nodes
			for(Action a: nextCandidate.state.getApplicableActions()){
				//check whether the subsequentNodes might still extend satisfy the constraint and if so add them to the frontier
				Node subsequentNode = new Node(nextCandidate, a, nextCandidate.state.getActionResult(a));
				if (pathConstraint.isSatisfiableByExtending(subsequentNode)){
					frontier.add(subsequentNode);
				}
			}

		}

		//return the solutions on completion of the search
		return solutions;
	}


}
