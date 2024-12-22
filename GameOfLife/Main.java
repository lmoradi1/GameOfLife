package GameOfLife;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {

	final static int GRID_SIZE = 20;
	final static int CELL_SIZE = 30;

	public static void main(String[] args) {

		// Start up the game and the GUI
		JFrame frame = createGameFrame();

		// Set up the timer
		setUpTimer(frame);
	}

	/**
	 * createGameFrame method creates the JFrame with the grid size and cell size
	 * 
	 * @return frame the JFrame it created
	 */
	public static JFrame createGameFrame() {
		int height = GRID_SIZE * CELL_SIZE;
		int width = GRID_SIZE * CELL_SIZE;

		JFrame frame = new JFrame("Game of Life");
		GameOfLife game = new GameOfLife(GRID_SIZE, CELL_SIZE);
		// add the game panel to the frame
		frame.add(game);
        //set the size of the frame
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center the frame
		frame.setLocationRelativeTo(null);
		// make the frame visible
		frame.setVisible(true);
		return frame;
	}

	/**
	 * setUpTimer sets up a timer.Every 300 milliseconds, the grid will be updated and repainted
	 * to reflect the new states
	 * @param frame  
	 */
	public static void setUpTimer(JFrame frame) {
	    //get the gameOfLife panel from the frame
		GameOfLife game = (GameOfLife) frame.getContentPane().getComponent(0);
		Timer timer = new Timer(300, e -> {
			game.updateGrid();
			game.repaint();
		});
		timer.start();
	}
}
