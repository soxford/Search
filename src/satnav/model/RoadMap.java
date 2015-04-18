package satnav.model;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
 * the RoadMap Class is an abstraction of a geographic road map with a set of Junction instances (locations) stored by name.
 */
public class RoadMap {

protected final Map<String, Junction> junctionsByName;
	
	public RoadMap() {
		junctionsByName = new LinkedHashMap<String, Junction>();
	}
	
	public Junction getJunction(String JunctionName) {
		return junctionsByName.get(JunctionName);
	}
	public void addRoad(String Junction1Name, String Junction2Name, int distance) {
		Junction Junction1 = getJunctionCreateIfMissing(Junction1Name);
		Junction Junction2 = getJunctionCreateIfMissing(Junction2Name);
		Junction1.outgoingRoads.add(new Road(Junction1, Junction2, distance));
	}
	protected Junction getJunctionCreateIfMissing(String JunctionName) {
		Junction inJunction = junctionsByName.get(JunctionName);
		if (inJunction == null) {
			inJunction = new Junction(JunctionName);
			junctionsByName.put(JunctionName, inJunction);
		}
		return inJunction;
	}
	public Set<Junction> getAlljunctions() {
		return new LinkedHashSet<Junction>(junctionsByName.values());
	}
	
}
