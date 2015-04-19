package search;

/**
 * The Action interface encapsulates the concept of an action taken from a particular State. Each Action has a cost which is assumed to be non-negative.
 * @author Simon Campbell <simonhmcampbell@gmail.com> with credit to Professor Peter Jeavons, Oxford
 */
public interface Action {
	//returns the cost of an action which is assumed to be non-negative
	/**
	 * This method returns the cost of the Action, which is assumed to be non-negative
	 * @return The cost of the Action
	 */
	public int getCost();
}
