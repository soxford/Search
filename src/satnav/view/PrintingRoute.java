package satnav.view;

import satnav.model.LocationOnJourneyState;
import satnav.model.Road;
import search.Action;
import search.Printing;
import search.State;

public class PrintingRoute extends Printing {

	public PrintingRoute() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void print(Action action) {
		System.out.print("drive for ");
		System.out.print(((Road)action).distance);
		System.out.print("miles to");
	}

	@Override
	public void print(State state) {
		System.out.println(((LocationOnJourneyState)state).getLocation().getName());

	}

}
