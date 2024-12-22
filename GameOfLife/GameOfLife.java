package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GameOfLife extends JPanel {

	private int gridSize;
	private int cellSize;
	private Cell[][] cells;
	private boolean changeState; // flag if the cell's state needs to be changed
	private Queue<Cell> queue; // to hold the cells that need to have their state changed

	/**
	 * Constructor
	 * 
	 * @param gridSize the size of the grid
	 * @param cellSize the size of each cell
	 */
	public GameOfLife(int gridSize, int cellSize) {
		this.gridSize = gridSize;
		this.cellSize = cellSize;
		this.cells = new Cell[gridSize][gridSize];
		this.queue = new Queue<Cell>();

		initializeGrid(); // Initialize the grid with the cells

	}

	/**
	 * initializeGrid method initializes the grid with cells, with their states
	 * randomized in the Cell constructor.
	 */
	private void initializeGrid() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				cells[i][j] = new Cell(); // initialized with random state, 0 or 1.
			}
		}
	}

	/**
	 * updateGrid updates the grid based on the rules of Game of Life
	 */
	public void updateGrid() {

		// Loop through each cell in the grid
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				// Get the amount of alive neighbors for each cell
				int neighborsAlive = countNeighbors(i, j, cells);
				// Determine if the state of the cell needs to be changed
				changeState = calculateChangeState(i, j, neighborsAlive, cells);
				// If the state of the cell needs to change, enqueue the cell
				if (changeState) {
					queue.enqueue(cells[i][j]);
				}

			}
		}

		// Flip the state of the cells that need to be changed
		while (!queue.isEmpty()) {
			Cell c = queue.dequeue();
			c.toggleState();
		}
	}

	/**
	 * countNeighbors method counts the amount of alive neighbors around the cell at
	 * position (i, j)
	 * 
	 * @param i
	 * @param j
	 * @param cells
	 * @return neighborsAlive the amount of alive neighbors
	 */
	public static int countNeighbors(int i, int j, Cell[][] cells) {

		int neighborsAlive = 0;

		int rows = cells.length; // the number of rows in the grid
		int cols = cells[0].length; // the number of columns in the grid

		// the offset for the 8 neighboring cells of each cell
		int[] rowOffsets = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int[] colOffsets = { -1, 0, 1, 1, 1, 0, -1, -1 };

		// loop through the 8 neighbors around a cell
		for (int k = 0; k < 8; k++) {

			// calculate the row and column for a toroidal grid
			int neighborRow = (i + rowOffsets[k] + rows) % rows;
			int neighborCol = (j + colOffsets[k] + cols) % cols;

			// if the neighbor is alive, increment the neighborsAlive count
			if (cells[neighborRow][neighborCol].getState() == 1) {
				neighborsAlive++;
			}

		}
		return neighborsAlive;
	}

	/**
	 * calculateChangeState calculates if a cell's state should change based on the
	 * amount of alive neighbors it has
	 * 
	 * @param i
	 * @param j
	 * @param neighborsAlive
	 * @param cells
	 * @return
	 */
	public static boolean calculateChangeState(int i, int j, int neighborsAlive, Cell[][] cells) {

		int state = cells[i][j].getState(); // the current state of the cell

		// if the cell is dead and has 3 alive neighbors, it state needs to change
		if (state == 0 && neighborsAlive == 3) {
			return true;
		}
		// if the state is alive, and either has less than 2 neighbors or more than 3
		// neighbors, its state needs to change
		else if (state == 1 && (neighborsAlive < 2 || neighborsAlive > 3)) {
			return true;
		} else {
			// otherwise, the state of the cell can remain the same
			return false;
		}

	}

	/**
	 * the painComponent method takes care of drawing the grid on the screen
	 */
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		// loop through each cell and draw it on the screen
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				// if the cell's state is alive, it's white
				if (cells[i][j].getState() == 1) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK); // Dead cells are black
				}

				// draw the actual cell
				g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
				// draw the grid lines
				g.setColor(Color.GRAY);   //set the drawing color to gray
				g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
			}
		}
	}

}
