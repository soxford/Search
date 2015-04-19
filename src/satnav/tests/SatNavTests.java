package satnav.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import satnav.SatNav;
import satnav.model.RoadMap;

public class SatNavTests {
	
	private SatNav navigator;

	@Before
	public void setUp() throws Exception {
		RoadMap roadMap = new RoadMap();
		
		// Test Data AB5, BC4, CD7, DC8, DE6, AD5, CE2, EB3, AE7
		
		roadMap.addRoad("A", "B", 5);
		roadMap.addRoad("B", "C", 4);
		roadMap.addRoad("C", "D", 7);
		roadMap.addRoad("D", "C", 8);
		roadMap.addRoad("D", "E", 6);
		roadMap.addRoad("A", "D", 5);
		roadMap.addRoad("C", "E", 2);
		roadMap.addRoad("E", "B", 3);
		roadMap.addRoad("A", "E", 7);
		
		navigator = new SatNav(roadMap);
	}

	//TESTS
	
	//	1. Distance for route A-B-C. Expected output 9
	@Test
	public void routeLength_ABC() {
		String[] route = {"A", "B", "C"};
		int expectedLength = 9;
		
		assertEquals(expectedLength, navigator.getRouteLength(route));
	}
	
	//	2. Distance for route A-D. Expected output 5
	@Test
	public void routeLength_AD(){
		String[] route = {"A", "D"};
		int expectedLength = 5;
		
		assertEquals(expectedLength, navigator.getRouteLength(route));
	}
	
	//	3. Distance for route A-D-C. Expected output 13
	@Test
	public void routeLength_ADC(){
		String[] route = {"A", "D", "C"};
		int expectedLength = 13;
		
		assertEquals(expectedLength, navigator.getRouteLength(route));
	}
	
	//	4. Distance for route A-E-B-C-D. Expected output 21
	@Test
	public void routeLength_AEBCD() {
		String[] route = {"A", "E", "B", "C", "D"};
		int expectedLength = 21;
		
		assertEquals(expectedLength, navigator.getRouteLength(route));
	}
	
	//	5. Distance for route A-E-D. Expected output NO SUCH ROUTE
	@Test
	public void routeLength_AED() {

		String[] route = {"A", "E", "D"};
		int expectedLength = Integer.MAX_VALUE; //Flag value for no such route
		
		assertEquals(expectedLength, navigator.getRouteLength(route));
	}
	
	/*	6. The number of routes starting at C and ending at C with a maximum of 3 junctions. 
	 *	In the sample data below, there are two such routes: C-D-C (2 junctions) and C-E-B-C (3 junctions). Expected output 2
	 */
	@Test
	public void routeCount_C_C_3JunctionMaximum() {
		String originName= "C";
		String destinationName = "C";
		int maxJunctionCountOnRoute = 3;
		int expectedCount = 2;
		
		assertEquals(expectedCount, navigator.getRouteCountWithJunctionCountAtMostUpperLimit(originName, destinationName, maxJunctionCountOnRoute));
	}
	
	/*	7. The number of routes starting at A and ending at C with exactly 4 junctions.
	 * 	In the sample data below, there are three such routes: A to C (via B,C,D); A 
	 * 	to C (via D,C,D); and A to C (via D,E,B). Expected output 3
	 */
	@Test
	public void routeCount_A_C_4JunctionsExactly(){
		String originName= "A";
		String destinationName = "C";
		int junctionCountOnRoute = 4;
		int expectedCount = 3;
		
		assertEquals(expectedCount, navigator.getRouteCountWithJunctionCount(originName, destinationName, junctionCountOnRoute));
	}
	
	//	8. The length of the shortest route (in terms of distance to travel) from A to C. Expected output 9
	@Test
	public void shortestRoute_A_C(){
		String originName = "A";
		String destinationName = "C";
		int expectedLength = 9;
		
		assertEquals(expectedLength, navigator.getShortestRouteLength(originName, destinationName));
	}
	
	//	9. The length of the shortest route (in terms of distance to travel) from B to B. Expected output 9
	@Test
	public void shortestRoute_B_B(){
		String originName = "B";
		String destinationName = "B";
		int expectedLength = 9;
		
		assertEquals(expectedLength, navigator.getShortestRouteLength(originName, destinationName));
	}
	
	/*	10. The number of different routes from C to C with a distance of less than 30. In the test data, the 
	 * 	trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC, CDEBCEBC, CEBCDEBC. Expected output 9
	 */
	@Test
	public void routeCount_C_C_30LengthMaximum(){
		String originName = "C";
		String destinationName = "C";
		int maxRouteLength = 30;
		int expectedCount = 9;
		
		assertEquals(expectedCount, navigator.getRouteCountWithRouteLengthLessThanUpperLimit(originName, destinationName, maxRouteLength));
	}
}
