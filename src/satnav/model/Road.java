package satnav.model;

import search.Action;

public class Road implements Action {
	public final Junction sourceJunction;
	public final Junction targetJunction;
	public final int distance;

	//Constructor assumes distance is positive
	public Road(Junction sourceJunction, Junction targetJunction, int distance) {
		this.sourceJunction = sourceJunction;
		this.targetJunction = targetJunction;
		this.distance = distance;
	}

	@Override
	public int getCost() {
		return distance;
	}

}
