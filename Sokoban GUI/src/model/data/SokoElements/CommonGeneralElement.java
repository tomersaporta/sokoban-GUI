package model.data.SokoElements;

import java.io.Serializable;
/**
 *<h1> Common General Element</h1>
 *defines the common data of all general elements
 */
public abstract class CommonGeneralElement implements GeneralElement,Serializable {
	
	private Position position;
	private char representor;
	
	public CommonGeneralElement() {
		this.position=null;
		this.representor=' ';
	}
	
	public CommonGeneralElement(Position position,char representor) {
		this.position=position;
		this.representor=representor;
	}
	
	//getters-setters
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public char getRepresntive() {
		return representor;
	}
	public void setRepresntive(char represntive) {
		this.representor = represntive;
	}
	
	@Override
	public String toString() {
		String s = String.valueOf(this.representor);
		return s;
	}
}
