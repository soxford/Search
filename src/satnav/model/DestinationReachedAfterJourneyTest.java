package satnav.model;

import search.GoalTest;
import search.State;

public class DestinationReachedAfterJourneyTest implements GoalTest {
	private final Junction destination;
	
	public DestinationReachedAfterJourneyTest(Junction destination) {
		this.destination = destination;
	}

	@Override
	public boolean isGoal(State state) {
		LocationOnJourneyState candidateJourney = (LocationOnJourneyState) state;
		//the goal is to have reached the destination having journeyed
		return ( destination.equals(candidateJourney.getLocation()) && candidateJourney.journeyStarted());
	}

}
