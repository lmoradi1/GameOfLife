package GameOfLife;

import java.util.Random;

public class Cell {

	private int state;

	public Cell() {
		Random random = new Random();
		this.state = random.nextInt(2); // randomized state, either 1 or 0
	}

	public int getState() {
		return state;
	}

	/**
	 * toggleState changes the current state of the cell to the other state option
	 */
	public void toggleState() {
		if (this.state == 1) {
			this.state = 0;
		} else {
			this.state = 1;
		}
	}
 


}
