package model.data.SokoElements;

public class Target extends Floor implements unmovable {

	@Override
	public char getType() {
		return 'o';
	}

	public Target() {
		super.setRepresntive('o');
	}
	
	public Target(Position pos){
		super.setRepresntive('o');
		super.setPosition(pos);
	}
}
