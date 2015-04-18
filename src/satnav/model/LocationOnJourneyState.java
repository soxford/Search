package satnav.model;

import java.util.Objects;
import java.util.Set;

import search.Action;
import search.State;

/**
 * This class is used for representing a location on a journey
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class LocationOnJourneyState implements State {
	private Junction currentLocation;
	private boolean journeyStarted = false; //to indicate that we have left the start location
	
	public LocationOnJourneyState(Junction location, boolean journeyStarted) {
		if(location == null){
			throw new IllegalArgumentException();
		}
		this.currentLocation = location;
		this.journeyStarted = journeyStarted;
	}

	@Override
	public Set<? extends Action> getApplicableActions() {
		return currentLocation.getApplicableActions();
	}

	@Override
	public State getActionResult(Action action) {
		Junction nextLocation = currentLocation.getActionResult(action);
		return new LocationOnJourneyState(nextLocation, true); //true because the journey has now started
	}

	public Junction getLocation() {
		return currentLocation;
	}

	public boolean journeyStarted() {
		return this.journeyStarted;
	}
	
	@Override
	public boolean equals(Object that){
		if(that == null){
			return false;
		} else if (this == that) {
			return true;
		} else if (!(that instanceof LocationOnJourneyState)) {
			return false;
		} else {
			LocationOnJourneyState thatState = (LocationOnJourneyState) that;
			return (this.getLocation().equals(thatState.getLocation()) && (this.journeyStarted() == thatState.journeyStarted()));
		}
	}
	@Override
	public int hashCode(){
		return Objects.hash(this.currentLocation, this.journeyStarted);
	}


}
