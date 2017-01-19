package model.data.SokoElements;

public class Box extends CommonGeneralElement implements movable {
	
	private Boolean isOnTarget;
	
	
	public Box() {
		super.setRepresntive('@');
		this.isOnTarget = false;
	}
	
	public Box(Position pos){
		super.setRepresntive('@');
		this.isOnTarget = false;
		super.setPosition(pos);
	}
	

	@Override
	public char getType() {
		return '@';
	}
	
	//getters-setters
	public Boolean getIsOnTarget() {
		return isOnTarget;
	}

	public void setIsOnTarget(Boolean isOnTarget) {
		this.isOnTarget = isOnTarget;
	}
	
}
