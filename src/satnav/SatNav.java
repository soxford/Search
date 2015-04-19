package satnav;


import satnav.model.DestinationReachedAfterJourneyTest;
import satnav.model.Junction;
import satnav.model.LocationOnJourneyState;
import satnav.model.RoadMap;
import search.DepthFirstFrontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;
import search.NodeFunction;
import search.PathActionCountConstraint;
import search.PathActionCountUpperConstraint;
import search.PathConstraint;
import search.PathCostFunction;
import search.PathCostUpperConstraint;
import search.Search;
import search.UniformCostGraphSearch;

/**
 * A SatNav represents an instance of a satellite navigation system. It has a geographic road map (passed in the form of a RoadMap instance) and has methods for route finding
 * @author Simon Campbell <simonhmcampbell@gmail.com>
 *
 */
public class SatNav {
	private RoadMap roadMap;

	public SatNav(RoadMap roadMap) {
		if(roadMap == null){
			throw new IllegalArgumentException();
		}
		
		this.roadMap = roadMap;
	}

	/**
	 * This method gets the length of the given route
	 * @param route - an array of junctions to visit in order
	 * @return Length of the route specified by the junctions in route, taken in order. If a road from a junction to its successor in the route does not exist (i.e. the route is not possible) then this method returns Integer.MAX_VALUE as a flag value
	 * @throws IllegalArgumentException if a null or zero element array is passed as the route or if a junction is included that is not stored in the satnav's RoadMap
	 */
	public int getRouteLength(String[] route) {

		//validate input
		if(route == null || route.length == 0){
			throw new IllegalArgumentException();

		} else {
			//integer to store the cumulative length of the route
			int totalLength = 0;

			//iterate through the elements of the route to get the length of each leg
			for(int i = 0; i < route.length - 1 ; i++){

				//validate that the junctionNames at i and i+1 are non-null, if not throw exception
				if(route[i] == null || route[i + 1] == null){
					throw new IllegalArgumentException();
				}

				//get the junctions from the roadMap
				Junction currentJunction = roadMap.getJunction(route[i]);
				Junction nextJunction = roadMap.getJunction(route[i + 1]);

				//check that the Junctions that were named exist in the map, if not throw exception
				if(currentJunction == null || nextJunction == null){
					throw new IllegalArgumentException();
				}

				//get the distance to the next Junction
				int additionalLength = currentJunction.distanceToNeighbour(nextJunction);

				//check for the flag value to indicate that the next junction has not been found neighbouring the current junction
				if(additionalLength == Integer.MAX_VALUE){
					totalLength = Integer.MAX_VALUE;
					break;

				} else { //update the total length
					totalLength += additionalLength;
				}
			}

			//return the total accumulated length of the route
			return totalLength;
		}
	}


	/**
	 * This method finds the shortest route from the origin to the destination if such a route exists.
	 * @param originName - the origin point for the planned route
	 * @param destinationName - the destination point for the planned route	
	 * @return the length of the shortest route from origin to destination. Returns Integer.MAX_VALUE if the location exists but there is not a route between them.
	 * @throws IllegalArgumentException if an unknown location is used in input
	 */
	public int getShortestRouteLength(String originName, String destinationName) {
		//validate the input
		if (originName == null || destinationName == null){
			throw new IllegalArgumentException();
		}

		Junction origin = roadMap.getJunction(originName);
		Junction destination = roadMap.getJunction(destinationName);

		//validate that the names provided correspond to junctions in the map
		if (origin == null || destination == null){
			throw new IllegalArgumentException();
		}

		//Construct goalTest
		GoalTest goalTest = new DestinationReachedAfterJourneyTest(destination);

		//construct the search - using Uniform Cost Search to get the shortest distance
		NodeFunction pathDistanceFunction = new PathCostFunction();
		Search search = new UniformCostGraphSearch(pathDistanceFunction);

		//search for the solution route
		Node solution = search.findSolution(new LocationOnJourneyState(origin, false), goalTest);

		//return the path cost to the solution unless no solution was found, in which case return the flag value
		return (solution == null? Integer.MAX_VALUE : solution.pathCost);
	}


	/**
	 * This method gets the number of routes available from the origin to the destination which visits at most the provided maximum junction count
	 * @param originName - the origin point for any counted route
	 * @param destinationName - the destination point for any counted route	
	 * @param maxJunctionCountOnRoute - the maximum number of junctions visited on any route
	 * @return The number of routes meeting the criteria
	 * @throws IllegalArgumentException if the origin or destination junctions are not found in the map and if the maxJunctionCountOnRoute is non-positive
	 */
	public int getRouteCountWithJunctionCountAtMostUpperLimit(String originName, String destinationName,
			int maxJunctionCountOnRoute) {
		//validate the input
		if (originName == null || destinationName == null || maxJunctionCountOnRoute < 0){
			throw new IllegalArgumentException();
		}

		Junction origin = roadMap.getJunction(originName);
		Junction destination = roadMap.getJunction(destinationName);

		//validate that the names provided correspond to junctions in the map
		if (origin == null || destination == null){
			throw new IllegalArgumentException();
		}

		//Construct goalTest
		GoalTest goalTest = new DestinationReachedAfterJourneyTest(destination);
		
		//Instantiate a Search to use
		Search search = new GraphSearch(new DepthFirstFrontier());
		
		//instantiate the constraint and get the number of solutions meeting the constraint
		PathConstraint constraint = new PathActionCountUpperConstraint(maxJunctionCountOnRoute);
		
		return (search.findAllSolutions(new LocationOnJourneyState(origin, false), goalTest, constraint)).size();

	}

	/**
	 * This method gets the number of routes available from the origin to the destination which cover less than the provided maximum route length
	 * @param originName - the origin point for any counted route
	 * @param destinationName - the destination point for any counted route	
	 * @param routeLengthUpperBound - the (exclusive) upper bound on any counted route
	 * @return The number of routes meeting the criteria
	 * @throws IllegalArgumentException if the origin or destination junctions are not found in the map and if the maxRouteLength is non-positive
	 */
	public int getRouteCountWithRouteLengthLessThanUpperLimit(String originName,
			String destinationName, int routeLengthUpperBound) {
		//validate the input
		if (originName == null || destinationName == null || routeLengthUpperBound <= 0){
			throw new IllegalArgumentException();
		}

		Junction origin = roadMap.getJunction(originName);
		Junction destination = roadMap.getJunction(destinationName);

		//validate that the names provided correspond to junctions in the map
		if (origin == null || destination == null){
			throw new IllegalArgumentException();
		}

		//Construct goalTest
		GoalTest goalTest = new DestinationReachedAfterJourneyTest(destination);
		
		//Instantiate a Search to use
		Search search = new GraphSearch(new DepthFirstFrontier());
		
		//instantiate the constraint and get the number of solutions meeting the constraint
		PathConstraint constraint = new PathCostUpperConstraint(routeLengthUpperBound);
		
		return (search.findAllSolutions(new LocationOnJourneyState(origin, false), goalTest, constraint)).size();

	}

	/**
	 * /**
	 * This method gets the number of routes available from the origin to the destination which visits exactly the provided junction count
	 * @param originName - the origin point for any counted route
	 * @param destinationName - the destination point for any counted route	
	 * @param junctionCountOnRoute - the maximum number of junctions visited on any route
	 * @return The number of routes meeting the criteria
	 * @throws IllegalArgumentException if the origin or destination junctions are not found in the map and if the JunctionCountOnRoute is non-positive
	 */
	public int getRouteCountWithJunctionCount(String originName,
			String destinationName, int junctionCountOnRoute) {
		//validate the input
			if (originName == null || destinationName == null || junctionCountOnRoute < 0){
				throw new IllegalArgumentException();
			}

			Junction origin = roadMap.getJunction(originName);
			Junction destination = roadMap.getJunction(destinationName);

			//validate that the names provided correspond to junctions in the map
			if (origin == null || destination == null){
				throw new IllegalArgumentException();
			}

			//Construct goalTest
			GoalTest goalTest = new DestinationReachedAfterJourneyTest(destination);
			
			//Instantiate a Search to use
			Search search = new GraphSearch(new DepthFirstFrontier());
			
			//instantiate the constraint and get the number of solutions meeting the constraint
			PathConstraint constraint = new PathActionCountConstraint(junctionCountOnRoute);
			
			return (search.findAllSolutions(new LocationOnJourneyState(origin, false), goalTest, constraint)).size();

	}

}
