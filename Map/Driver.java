package Map;

/**
 * For Testing purposes
 * 
 * @author		Wei Wang
 * @version		1.0
 * @since		2015-02-28
 */

public class Driver {
	
	/**
	 * Test Cases
	 * Case 1: 
	 * Width: 15
	 * Height: 20
	 * Inputs: (0,18) (3,18) (3,15) (8,15) (8,11) (11,11) (11,8) (6,8) (6,4) (2,4) (2,1) (14,1)
	 * Valid Map
	 * 
	 * Case 2:
	 * Width: 10
	 * Height: 6
	 * Inputs: (0, 0) (0, 4) (3, 4) (3, 2) (7, 3) (8, 2) (9, 4)
	 * Invalid Map
	 */
	public static void main(String[] args){
		
		User user = new User();
		
		//Enter the value of width and height of the map
		user.createMap();
		user.drawOriginalMap();
		
		//Put a series of xy-coordinates as a String in form of (x1,y1) (x2,y2) etc
		user.setPathCoordinates();
		user.drawMapWithPath();
		
		//List down all the path coordinates after being linked together
		user.drawPathCoordinates();
		
		//Verify whether the design map is valid
		user.checkValidity();
		
	}
}
