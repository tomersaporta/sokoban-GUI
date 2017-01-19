package model.data.SokoElements;

import java.io.Serializable;

/**
 * <h1>Position<h2>
 * defines the general elements position on the level board
 *
 */

public class Position implements Serializable {

		private int x;
		private int y;
	
		public Position() {
			this.x=0;
			this.y=0;
		}
		
		public Position(int x, int y) {
			this.x= x;
			this.y= y;
		}
	
	@Override
	public String toString() {
		return x+ " "+y;
	}
	
	//getters-setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
