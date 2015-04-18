package satnav.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import search.Action;
import search.State;

/**
 * A Junction is an geographic state/location in a road map. This State has a set of possible actions (outgoing roads to other junctions).
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class Junction implements State {
	protected final String name;
	protected Set<Road> outgoingRoads;

	public Junction(String name) {
		this.name = name;
		this.outgoingRoads = new LinkedHashSet<Road>();
	}
	public String getName() {
		return name;
	}
	
	@Override
	public Set<Road> getApplicableActions() {
		Set<Road> copyOfOutgoingRoads = new LinkedHashSet<Road>();
		copyOfOutgoingRoads.addAll(outgoingRoads);
		return copyOfOutgoingRoads;
	}

	@Override
	public Junction getActionResult(Action action) {
		//Check that we have and instance of Road
		if(!(action instanceof Road)){
			throw new IllegalArgumentException();
		}
		Road road = (Road) action;
		
		//check that the road starts at this state, this state should only be queried about it's own actions
		if(!road.sourceJunction.equals(this)){
			throw new IllegalArgumentException();
		}
		
		return road.targetJunction;
	}
	
	public boolean equals(Object that){
		if(that == null){
			return false;
		} else if (this == that) {
			return true;
		}else if(!(that instanceof Junction)){
			return false;
		} else {
			Junction thatJunction = (Junction) that;
			return (thatJunction.name.equals(this.name) && thatJunction.outgoingRoads.equals(this.outgoingRoads));
		}
	}
	
	public int hashCode(){
		return Objects.hash(this.name, this.outgoingRoads);
	}
	/**
	 * The method checks whether a given junction neighbours this junction (i.e. it is the terminal junction on an outgoing road from this)
	 * @param nextJunction - the destination junction to check
	 * @return true iff the nextJunction is he terminal junction on an outgoing road from this
	 * @throws IllegalArgumentException if nextJunction is null
	 */
	public boolean isNeigbourTo(Junction nextJunction) {
		if(nextJunction == null){
			throw new IllegalArgumentException();
		} else {
			for(Road r: this.outgoingRoads){
				//check to see whether the road terminates at the required nextJunction and return true if so
				if(nextJunction.equals(r.targetJunction)){
					return true;
				}
			}
			//if none of the roads terminated in the required nextJunction return false
			return false;
		}
	}
	
	/**
	 * This method returns the distance to the given neighbouring Junction.
	 * @param neighbouringJunction - the neighbouring junction whose distance along the outgoing road we require
	 * @return the length of the outgoing road to the given neigbouring junction. If the Junction is not a neigbour we return Integer.MAX_VALUE
	 * @throws IllegalArgumentException on null input
	 */
	public int distanceToNeighbour(Junction neighbouringJunction) {
		if (neighbouringJunction == null){
			throw new IllegalArgumentException();
		} else {
			for(Road r: this.outgoingRoads){
				//check to see whether the road terminates at the required neighbouringJunction and return the distance if so
				if(neighbouringJunction.equals(r.targetJunction)){
					return r.getCost();
				}
			}
			//if neighbouringJunction not discovered then return the Flag value
			return Integer.MAX_VALUE;
		}
	}

}
