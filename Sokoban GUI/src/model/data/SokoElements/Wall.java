package model.data.SokoElements;

public class Wall extends CommonGeneralElement implements unmovable {

	public Wall() {
		super.setRepresntive('#');
	}
	
	public Wall(Position pos){
		super.setRepresntive('#');
		super.setPosition(pos);
	}
	
	@Override
	public char getType() {
		return '#';
	}

	@Override
	public boolean isStepable() {
		return false;
	}
	
	

}
