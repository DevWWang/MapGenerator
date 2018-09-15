package Map;

import java.util.*;

/**
 * Alternative Driver which is used to generate a map and assign the path
 * 
 * @author		Wei Wang
 * @version		1.0
 * @since		2015-02-28
 */

public class Test {
	
	public static void main(String[] args){
		
		Map map = new Map(15, 20);
		
		Queue<PathTile> path = new LinkedList<PathTile>();
		
		path = map.multipleCoordinatesSplit("(0,18) (3,18) (3,15) (8,15) (8,11) (11,11) (11,8) (6,8) (6,4) (2,4) (2,1) (14,1)");

		int x = path.peek().getX();
		int y = path.peek().getY();
				
		map.buildPath(path);
		System.out.println(map);
		
		PathTile tile = (PathTile) map.getTile(x,y);
		
		System.out.println("Path: ");
		while (tile != null){
			System.out.println(tile.getX() + "," + tile.getY() + " " + tile);
			tile = tile.getNextTile();
		}
		System.out.println("\n" + "Is the map valid?" + " " + map.ValidityOfMap());
	}
}
