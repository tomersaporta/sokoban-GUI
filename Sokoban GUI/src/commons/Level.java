package commons;

import java.util.ArrayList;

import model.data.SokoElements.Box;
import model.data.SokoElements.Floor;
import model.data.SokoElements.GeneralElement;
import model.data.SokoElements.Player;
import model.data.SokoElements.Position;
import model.data.SokoElements.Target;
import model.data.SokoElements.movable;
import model.data.SokoElements.unmovable;
/**
 * <h1>Level</h1>
 *manage the whole data of the level
 */
public class Level{
	
	private int steps;
	private long time;
    private ArrayList<Box> listBox;
	private ArrayList<Player> listPlayer;
	private ArrayList<Target> listTarget;
	private int row;
	private int col;
	private movable [][] movearr;//array of dynamic sokobanEntity
	private unmovable [][] background;//array of static sokobanEntity
	
	public Level() {
		this.steps=0;
		this.time = System.currentTimeMillis();
		this.listBox=new ArrayList<Box>();
		this.listPlayer=new ArrayList<Player>();
		this.listTarget=new ArrayList<Target>();
		this.row=0;
		this.col=0;
		this.movearr=new movable[this.row][this.col];
		this.background=new unmovable[this.row][this.col];
	}
	
	public Level(int row, int col){
		this.steps=0;
		this.time = System.currentTimeMillis();
		this.listBox=new ArrayList<Box>();
		this.listPlayer=new ArrayList<Player>();
		this.listTarget=new ArrayList<Target>();
		this.row=row;
		this.col=col;
		this.movearr=new movable[this.row][this.col];
		this.background=new unmovable[this.row][this.col];
		
		//initialize the background array to Floor
		for(int i=0;i<this.row;i++)
			for(int j=0;j<this.col;j++)
			{
				this.background[i][j]=new Floor();
			}
		
	}

	//getters-setters
	public ArrayList<Box> getListBox() {
		return listBox;
	}

	public void setListBox(ArrayList<Box> listBox) {
		this.listBox = listBox;
	}

	public void addToListBox(movable mv){
		this.listBox.add((Box) mv);
	}
	
	public ArrayList<Player> getListPlayer() {
		return listPlayer;
	}

	public void setListPlayer(ArrayList<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}
	
	public void addToListPlayer(movable mv){
		this.listPlayer.add((Player) mv);
	}

	public ArrayList<Target> getListTarget() {
		return listTarget;
	}

	public void setListTarget(ArrayList<Target> listTarget) {
		this.listTarget = listTarget;
	}

	public void addToListTarget(unmovable mv){
		this.listTarget.add((Target) mv);
	}
	
	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public movable[][] getMovearr() {
		return movearr;
	}

	public void setMovearr(movable[][] movearr) {
		this.movearr = movearr;
	}
	
	/**
	 * set movable entity in the movearr array
	 * @param moveobj Set this movable element to the movearr
	 */
	public void setMovearrInIndex(movable moveobj) {
		Position pos=moveobj.getPosition();
		this.movearr[pos.getX()][pos.getY()] = moveobj;
	}

	public unmovable[][] getBackground() {
		return background;
	}

	public void setBackground(unmovable[][] background) {
		this.background = background;
	}
	/**
	 * set unmovable entity in the background array
	 * @param unmoveobj Set this movable element to the background array
	 */
	public void setBackgroundInIndex(unmovable unmoveobj) {
		Position pos=unmoveobj.getPosition();
		this.background[pos.getX()][pos.getY()] = unmoveobj;
	}
	
	public void setBackgroundInIndex(unmovable unmov, int i,int j) {
		this.background[i][j] = unmov;
	}

	/**
	 * check if the level solvable
	 * @return true if the level can be solved
	 */
	public Boolean isSolved() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return the number of boxes
	 * @return int 
	 */
	public int numOfBoxes() {
		return this.listBox.size();
	}

	/**
	 * return the number of targets
	 * @return int 
	 */
	public int numOfTargets() {
		return this.listTarget.size();
	}
	
	/**
	 * return the number of players
	 * @return int 
	 */
	public int numOfPlayers() {
		return this.listPlayer.size();
	}



	/**
	 * return the number of boxes on target
	 * @return int
	 */
	public int numOfBoxesOnTargets() {
		int count=0;
		for (int i = 0 ; i<numOfBoxes(); i++)
			if (getListBox().get(i).getIsOnTarget())
				count++;
		return count;
	}
	
	/**
	 * check the environment of a movable element 
	 * @param element The element we would like to check
	 * @param pos The position of the element
	 * @return Return true if the character can move
	 */
	//
	public boolean isPossibleToMove(movable element, Position pos){
		if((this.background[pos.getX()][pos.getY()]) instanceof Floor) 
			return true;
		return false;
	}

	/**
	 * check if the level is ended
	 * @return true if all the boxes on targets
	 */
	public Boolean isEndOfLevel() {
		return numOfBoxesOnTargets()==numOfTargets();
	}

	/**
	 * return the element in the given position
	 * @param myPos The position we would like to the element in it
	 * @return
	 */
	public GeneralElement getElementInPosition(Position myPos) {
		if(movearr[myPos.getX()][myPos.getY()]!=null)
			return movearr[myPos.getX()][myPos.getY()];
		else
			return background[myPos.getX()][myPos.getY()];
		
	}

	/**
	 * update level data by move command
	 * @param player The player we want to update
	 * @param newPosition The new position of the player
	 */
	public void upDateLevelPlayerMoves(Player player, Position newPosition) {
			
		Position OldPosition=player.getPosition();
		
		movearr[newPosition.getX()][newPosition.getY()]=player;
		movearr[OldPosition.getX()][OldPosition.getY()]=null;
		
		player.setPosition(newPosition);
		
		//update the steps
		setSteps(getSteps()+1);
		
	}
	/**
	 * update level data by move command
	 * @param player The player we want to update
	 * @param box The box we want to update
	 * @param newPositionBox The new position of the box
	 */
	public void upDateLevelPlayerBoxMoves(Player player, Box box,Position newPositionBox) {
		
		Position newPositionPlayer=box.getPosition();
		Position OldPositionPlayer=player.getPosition();
		
		
		movearr[newPositionBox.getX()][newPositionBox.getY()]=box;
		movearr[newPositionPlayer.getX()][newPositionPlayer.getY()]=player;
		movearr[OldPositionPlayer.getX()][OldPositionPlayer.getY()]=null;
		
		player.setPosition(newPositionPlayer);
		box.setPosition(newPositionBox);
		
		//update the steps
		setSteps(getSteps()+1);
		
		if(this.background[newPositionBox.getX()][newPositionBox.getY()]instanceof Target)
			box.setIsOnTarget(true);
		else 
			box.setIsOnTarget(false);
		
	}
	
	
	public char[][] getLevelBored(){
		
		char[][]result=new char[getRow()][getCol()];
		for(int i=0;i<getRow();i++)
			for(int j=0;j<getCol();j++)
				result[i][j]=getElementInPosition(new Position(i, j)).getType();
		
		return result;
				
	}
	
}
