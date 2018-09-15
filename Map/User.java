package Map;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author		Wei Wang
 * @version		1.0
 * @since		2015-02-28
 */

public class User {
	
	private static Scanner input = new Scanner(System.in);
	
	public Map originalMap, mapWithPath;
	private int x, y;
	
	/**
	 * Enter the width and height for a map
	 * If the input values are not positive integers,
	 * it defines the design map as invalid and ask to re-enter the inputs
	 */
	public void createMap(){
		
		System.out.print("Map's Width: ");
		int width = Integer.valueOf(input.nextLine());
		System.out.print("Map's Height: ");
		int height = Integer.valueOf(input.nextLine());

		originalMap = new Map(width, height);
		mapWithPath = new Map(width, height);	
	}
	
	/**
	 * Draw out the design empty map
	 */
	public void drawOriginalMap(){
		System.out.println(originalMap);
	}
	
	/**
	 * Assign the path locations by giving a set of xy-coordinates as a String
	 * The Coordinates are separated by ")" with a space after, xy-coordinate are separated by ","
	 * The first position is the Entry and the last one is the Exit
	 */
	public void setPathCoordinates(){
		
		Queue<PathTile> path = new LinkedList<PathTile>();
		
		System.out.println("Enter path in form of (x1,y1) (x1,y2) ...: ");
		path = mapWithPath.multipleCoordinatesSplit(input.nextLine());
		
		x = path.peek().getX();
		y = path.peek().getY();
		
		mapWithPath.buildPath(path);
	}
	
	/**
	 * List down all the PathTile
	 */
	public void drawPathCoordinates(){
		PathTile tile = (PathTile) mapWithPath.getTile(x,y);
		
		System.out.println("Path: ");
		while (tile != null){
			System.out.println(tile.getX() + "," + tile.getY());
			tile = tile.getNextTile();
		}
	}
	
	/**
	 * Draw the map with the assign path
	 */
	public void drawMapWithPath(){
		System.out.println(mapWithPath);
	}
	
	public void checkValidity(){
		System.out.println("\n" + "Is it a valid map?");
		System.out.println(mapWithPath.ValidityOfMap());
	}

}
