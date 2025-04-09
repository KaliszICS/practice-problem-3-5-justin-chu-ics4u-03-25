public class PracticeProblem {

	public static void main(String args[]) {

	}

	public static int searchMazeMoves(String[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return Integer.MAX_VALUE;
		}

		int rows = maze.length;
		int cols = maze[0].length;
		int startRow = -1;
		int startCol = -1;

		//find starting position "S" (bottom left)
		if (rows > 0 && cols > 0 && "S".equals(maze[rows - 1][0])) {
			startRow = rows - 1;
			startCol = 0;
		} else {
			//fallback
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if ("S".equals(maze[i][j])) {
						startRow = i;
						startCol = j;
						break;
					}
				}
				if (startRow != -1) break;
			}
		}

		//if "S" cant be found
		if (startRow == -1) {
			return Integer.MAX_VALUE; //cant start
		}

		boolean[][] visited = new boolean[rows][cols];
		int result = searchMazeMovesHelper(maze, startRow, startCol, visited);

		return result;
	}

	private static int searchMazeMovesHelper(String[][] maze, int r, int c, boolean[][] visited) {
		int rows = maze.length;
		int cols = maze[0].length;

		//base cases
		if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c]) {
			return Integer.MAX_VALUE;
		}
		if ("F".equals(maze[r][c])) {
			return 0; //found "F", 0 moves needed
		}

		//mark square as visited
		visited[r][c] = true;

		//recurse step
		int minMoves = Integer.MAX_VALUE;

		//go up
		int up = searchMazeMovesHelper(maze, r - 1, c, visited);
		if (up != Integer.MAX_VALUE) {
			minMoves = Math.min(minMoves, up + 1);
		}

		//go right
		int right = searchMazeMovesHelper(maze, r, c + 1, visited);
		if (right != Integer.MAX_VALUE) {
			minMoves = Math.min(minMoves, right + 1);
		}

		//backtrack and unmark cell
		visited[r][c] = false;

		//return min moves found
		return minMoves;
	}

	public static int noOfPaths(String[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return 0;
		}

		int rows = maze.length;
		int cols = maze[0].length;
		int startRow = -1;
		int startCol = -1;

		//find start pos "S", bottom left
		if (rows > 0 && cols > 0 && "S".equals(maze[rows - 1][0])) {
			startRow = rows - 1;
			startCol = 0;
		} else {
			//fallback
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if ("S".equals(maze[i][j])) {
						startRow = i;
						startCol = j;
						break;
					}
				}
				if (startRow != -1) break;
			}
		}

		if (startRow == -1) {
			return 0;
		}

		boolean[][] visited = new boolean[rows][cols];
		return noOfPathsHelper(maze, startRow, startCol, visited);
	}

	private static int noOfPathsHelper(String[][] maze, int r, int c, boolean[][] visited) {
		int rows = maze.length;
		int cols = maze[0].length;

		//base cases
		if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c]) {
			return 0; //no paths this way
		}
		if ("F".equals(maze[r][c])) {
			return 1; //found a path
		}

		//mark square visited
		visited[r][c] = true;

		//recurse step
		int pathCount = 0;

		pathCount += noOfPathsHelper(maze, r - 1, c, visited); //up
		pathCount += noOfPathsHelper(maze, r, c + 1, visited); //right

		//backtrack and unmark cell as visited
		visited[r][c] = false;

		//return total paths found
		return pathCount;
	}

	

}
