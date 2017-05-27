package commons.searcable;

import java.util.HashMap;

import commons.Level;
import model.data.SokoElements.Position;
import search.Action;
import search.Searchable;
import search.State;

public class SearchableLevel implements Searchable<SokobanState>{
	
	Level theLevel;
	char[][]board;
	Position currentPos, newPos;
	
	public SearchableLevel(Level theLevel,Position fromPos, Position toPos) {
		this.theLevel = theLevel;
		board=theLevel.getLevelBored();
		this.currentPos = fromPos; 
		this.newPos = toPos;
	}
	
	public void setPositions(Position fromPos , Position toPos){
		this.currentPos = fromPos; 
		this.newPos = toPos;
		
	}

	@Override
	public State<SokobanState> getInitialState() {
		SokobanState s=new SokobanState(board);
		State<SokobanState>state= new State<SokobanState>(s,0);
		state.setCameFrom(null);
		return state;
	}

	@Override
	public State<SokobanState> getGoalState() {
		char[][]newBoard=copyBoard(this.board);
		
		newBoard[newPos.getX()][ newPos.getY()] = newBoard[currentPos.getX()][ currentPos.getY()];
		newBoard[currentPos.getX()][ currentPos.getY()]= ' '; 
		
		SokobanState goalState = new SokobanState(newBoard);
		return new State<SokobanState>(goalState, 0);
	}

	@Override
	public HashMap<Action, State<SokobanState>> getAllPossibleStates(State<SokobanState> state) {
		return null;
	}
	
	private char[][] copyBoard(char[][] board) {

		char[][] newBoard = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
}
