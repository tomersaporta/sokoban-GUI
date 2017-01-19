package model.data.SokoElements;

public class Floor extends CommonGeneralElement implements unmovable {

	@Override
	public char getType() {
		return ' ';
	}
	
	public Floor() {
		super.setRepresntive(' ');
	}
	
	public Floor(Position pos){
		super.setRepresntive(' ');
		super.setPosition(pos);

	}

	@Override
	public boolean isStepable() {
		return true;
	}

}
