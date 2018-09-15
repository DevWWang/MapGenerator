# Map Generator

Map is designed as a grid, where each grid cell is called “Tile”. In each tile, it stores its xy-coordinate, starting by (0, 0), and its type.
First, all the tiles are set as default after generating a map. The values for map’s width can height have to be positive integers. Second, by using a method buildPath() to insert selected positions, the tile becomes part of path and change its type. The path can only has one entry point and one exit point by using method of placeEntry() and placeExit(). In addition, the map class have a method multipleCoordinatesSplit() to store all the assigned path positions in a list, and another method linkTwoPoints() to link together by straight lines. Last, it is necessary to verify all assigned points are valid and linked properly because a valid map must satisfy the above specifications.

## Game Rules
1. The input values of width, number of tiles in a row, and height, number of tiles in a column, must be positive integers from 5 to 50
2. In order to design a path, users enters a string with multiple set of coordinates. First set is the Entry Point of the path, and the last one is the Exit Point.
- Note that the path can only ve connected by straight lines, not diagonal ones.
- For example, a valid string of path inputs on a 4 x 4 Map: (0, 0) (0, 2) (2, 2) (3, 2) (3, 3)
  Entry Point is at (0, 0) and Exit Point is at (3, 3)
3. Path must jave at least two inputs
