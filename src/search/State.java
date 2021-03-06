package search;

import java.util.Set;

/**
 * State encapsulates the notion of a state which has potential actions that transition to other states.
 * We must define what it means for two states to be equivalent
 * @author Simon Campbell <simonhmcampbell@gmail.com> with credit to Professor Peter Jeavons, Oxford
 */
public interface State {
	
	Set<? extends Action> getApplicableActions();
	
	State getActionResult(Action action);
	
	boolean equals(Object that);
	
	int hashCode();
}
