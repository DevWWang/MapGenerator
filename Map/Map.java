package Map;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author		Wei Wang
 * @version		1.0
 * @since		2015-02-28
 */

public class Map{

	private int widthOfMap, heightOfMap;
	private Tile[][] mapTile;

	private boolean validityOfMap, validityOfPathInput, validityOfPath, validityOfPathLength;

	/**
	 * Map generator
	 * 
	 * @param widthOfMap 	The horizontal number of MapTile (x-axis)
	 * @param heightOfMap	The vertical number of MapTile (y-axis)
	 */
	public Map(int widthOfMap, int heightOfMap){
		this.widthOfMap = widthOfMap;
		this.heightOfMap = heightOfMap;

		if (widthOfMap > 0 && heightOfMap > 0){
			mapTile = new Tile[widthOfMap][heightOfMap];
			
			//Initialize the Map
			for (int i = 0 ; i < widthOfMap; i++){
				for (int j = 0; j < heightOfMap; j++){
					mapTile[i][j] = new MapTile(i,j);
				}
			}
			validityOfMap = true;
		}
		else {
			validityOfMap = false;
		}
	}

	/**
	 * Set the width of Map (horizontal number of MapTile)
	 * 
	 * @param widthOfMap
	 */
	public void setWidthOfMap(int widthOfMap){
		this.widthOfMap = widthOfMap;
	}

	/**
	 * Set the height of Map (vertical number of Tile)
	 * 
	 * @param heightOfMap
	 */
	public void setHeightOfMap(int heightOfMap){
		this.heightOfMap = heightOfMap;
	}

	/**
	 * 
	 * @return the width of Map
	 */
	public int getWidthOfMap(){
		return widthOfMap;
	}

	/**
	 * 
	 * @return the height of Map
	 */
	public int getHeightOfMap(){
		return heightOfMap;
	}

	/**
	 * Retrieve the MapTile
	 * 
	 * @param x		X-coordinate
	 * @param y		Y-coordinate
	 * @return		the content of MapTile
	 */
	public Tile getTile(int x, int y){
		try {
			return this.mapTile[x][y];
		} catch(Exception e){
			
		}
		return null;
	}

	/**
	 * Place Entry Point on Map and change the tile's type  to Entry
	 * 
	 * @param x		X-coordinate of Entry point
	 * @param y		Y-coordinate of Entry point
	 */
	public void placeEntry(int x, int y){
		mapTile[x][y].setType("-Entry-");
	}

	/**
	 * Place Exit point on Map and change the tile's type to Exit
	 * 
	 * @param x		X-coordinate of Exit point
	 * @param y		Y-coordinate of Exit point
	 */
	public void placeExit(int x, int y){
		mapTile[x][y].setType("-Exit!-");
	}

	/**
	 * Place the path point on the Map and change to the tile's type to -------
	 * 
	 * @param x		X-coordinate of assigned PathTile
	 * @param y		Y-coordinate of assigned PathTile
	 */
	public void placePoint(int x, int y){
		mapTile[x][y] = new PathTile(x, y);
	}

	/**
	 * Read multiple xy-coordinates from a string which is required at least two sets
	 * Split the coordinates and insert them into a queue
	 * 
	 * @param s		a set of PathTile inputs as a string
	 * @return all path coordinates in a Queue
	 */
	public Queue<PathTile> multipleCoordinatesSplit(String s){
		Queue<PathTile> path = new LinkedList<PathTile>();
		String[] arr = s.split("\\)\\s*"); 
		for (String anArr : arr){
			String x = anArr.substring(1,anArr.indexOf(",")).trim();
			String y = anArr.substring(anArr.indexOf(",") + 1, anArr.length()).trim();
			
			PathTile p = new PathTile(Integer.valueOf(x), Integer.valueOf(y));
			path.add(p);
		}

		if (path.size() >= 2){
			validityOfPathLength = true;
		}
		return path;
	}

	/**
	 * Link all the path tile together
	 * First input is an Entry point
	 * Last input is an Exit point
	 * 
	 * @param newPath		a Queue contains all the path locations
	 */
	public void buildPath(Queue <PathTile> newPath){
		//Store the first input coordinate values
		PathTile entry = newPath.peek(), current, next;

		if (newPath.isEmpty()){
			validityOfPathLength = false;
			return;
		}

		if (newPath.size() >= 2){
			validityOfPathLength = true;
		}
		current = newPath.remove();
		next = current;

		if (current.getX() < getWidthOfMap() && current.getY() < getHeightOfMap()){
			mapTile[next.getX()][next.getY()] = new PathTile(next.getX(), next.getY());

			while (!newPath.isEmpty()){
				next = newPath.remove();
				linkTwoPoints(current, next);
				current = next;
			}

			validityOfPathInput = true;
			
			//Indicates the Entry and Exit of the path
			placeEntry(entry.getX(), entry.getY());
			placeExit(current.getX(), current.getY());
		}
		else {
			validityOfPathInput = false;
		}
	}

	/**
	 * Link the path from a specific PathTile to another one
	 * Two points must have to share the same x position or y position
	 * If two points have the same x-coordinate, then create a vertical line to connect them together
	 * Otherwise, create a horizontal line
	 * 
	 * @param startPoint	The first point (From this PathTile)
	 * @param endPoint		The second point (To this PathTile)
	 */
	public void linkTwoPoints(PathTile startPoint, PathTile endPoint){
		int dx, dy;

		int x1 = startPoint.getX();
		int y1 = startPoint.getY();

		int x2 = endPoint.getX();
		int y2 = endPoint.getY();

		if (x1 != x2 && y1 != y2){
			placePoint(x1, y1);
			placePoint(x2, y2);
			
			validityOfPath = false;
		}
		else {

		PathTile currentPoint = (PathTile) mapTile[x1][y1];

		//If two inputs have the same x-axis, calculate the different of y and create a vertical line to connect two points
		if (x1 == x2){
			dy = y2 - y1;

			if (dy == 0){
				return;
			}
			//If the difference is positive, link them from bottom to top
			//Otherwise, link them from top to bottom
			else if (dy > 0){
				for (int i = 1 ; i <= dy; i++){
					mapTile[x1][y1 + i] = new PathTile(x1, y1 + i);
					currentPoint.setNextTile((PathTile)mapTile[x1][y1 + i]);
					currentPoint = (PathTile)mapTile[x1][y1 + i];
				}
			}
			else {
				for (int i = -1 ; i >= dy; i--){
					mapTile[x1][y1 + i] = new PathTile(x1, y1 + i);
					currentPoint.setNextTile((PathTile)mapTile[x1][y1 + i]);
					currentPoint = (PathTile)mapTile[x1][y1 + i];
				}
			}
			validityOfPath = true;
		}
		//If two inputs have the same y-axis, calculate the different of x and create a horizontal line to connect two points
		else if (y1 == y2){
			dx = x2 - x1;

			if (dx == 0){
				return;
			}
			//If the difference is positive, link them from left to right
			//Otherwise, link them from right to left
			else if (dx > 0){
				for (int i = 1 ; i <= dx; i++){
					mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
					currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
					currentPoint = (PathTile)mapTile[x1 + i][y1];
				}
			}
			else {
				for (int i = -1 ; i >= dx; i--){
					mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
					currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
					currentPoint = (PathTile)mapTile[x1 + i][y1];
				}
			}
			validityOfPath = true;
		}}
	}

	/**
	 * Verify whether the design map is valid according to the game rules
	 * 
	 * @return validity
	 */
	public boolean ValidityOfMap(){
		boolean validity;
		if (validityOfMap && validityOfPathInput && validityOfPath && validityOfPathLength){
			validity = true;
		}
		else {
			if (!validityOfMap){
				System.out.println("Invalid Map - wrong values of width/height!");
			}
			if (!validityOfPathInput){
				System.out.println("Invalid Input Value - input does not exist on the grid!");
			}
			if (!validityOfPath){
				System.out.println("Invalid Path Link - inputs do not share the same x or y axis!");
			}
			if (!validityOfPathLength){
				System.out.println("Invalid Path - path is too short!");
			}
			validity = false;
		}
		return validity;

	}

	/**
	 * Print the Map
	 */
	public String toString(){
		String s = "\n";
		for (int j = getHeightOfMap() - 1; j >= 0; j--){
			for (int i = 0; i < getWidthOfMap(); i++){
				s+=(getTile(i,j) + " ");
			}
			s +="\n";
		}
		return s;
	}
}
