package model.data.SokoElements;

public class Player extends CommonGeneralElement implements movable{
	
	@Override
	public char getType() {
		return 'A';
	}

	public Player() {
		super.setRepresntive('A');
	}

	public Player(Position pos){
		super.setRepresntive('A');
		super.setPosition(pos);

	}
	

}
