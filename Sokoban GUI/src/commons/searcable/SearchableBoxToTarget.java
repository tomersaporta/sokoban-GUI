package commons.searcable;

import java.util.HashMap;
import model.data.SokoElements.Position;
import search.Action;
import search.BFS;
import search.Searcher;
import search.Solution;
import search.State;

public class SearchableBoxToTarget extends CommonSearchable {

	private Searcher<Position> search;
	private SearchablePlayerToBox searchablePlayer;
	private Position playerInitPos;

	public SearchableBoxToTarget(Position fromPosition, Position toPosition, char[][] board, Searcher<Position> search,
			SearchablePlayerToBox searchablePlayer) {
		super(fromPosition, toPosition, board);
		this.search = search;
		this.searchablePlayer = searchablePlayer;
		this.playerInitPos=deletePlayerFromBoard();

	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleStates(State<Position> state) {

		HashMap<Action, State<Position>> states = new HashMap<>();

		// get the position from the state
		Position currPos = state.getState();

		Position playerTarget;

		Solution playerSolution;
		Position playerPos = getPlayerPos(state.getAction(), currPos);
		if (playerPos == null)
			return null;

		// move up and down
		if (isValidPos(currPos.getX() - 1, currPos.getY()) && isValidPos(currPos.getX() + 1, currPos.getY())) {

			if (isPossibleToMove(currPos.getX() - 1, currPos.getY())&& isPossibleToMove(currPos.getX() + 1, currPos.getY())){
				
				// move up
				// checking if the player could reach to the opposite position
				playerTarget = new Position(currPos.getX() + 1, currPos.getY());
				playerSolution = isPlayerCanMoveToBox(currPos, playerPos, playerTarget);
				
				if (playerSolution != null || playerTarget.equals(playerPos)) {
					Position newPos = new Position(currPos.getX() - 1, currPos.getY());
					State<Position> newState = new State<Position>(newPos, state.getCost() + 1);
					newState.setPreActions(playerSolution.getTheSolution());
					states.put(new Action("up"), newState);
				}

				// move down
				playerTarget = new Position(currPos.getX() - 1, currPos.getY());
				playerSolution = isPlayerCanMoveToBox(currPos, playerPos, playerTarget);
				if (playerSolution != null || playerTarget.equals(playerPos)) {
					Position newPos = new Position(currPos.getX() + 1, currPos.getY());
					State<Position> newState = new State<Position>(newPos, state.getCost() + 1);
					newState.setPreActions(playerSolution.getTheSolution());
					states.put(new Action("down"), newState);
				}
			}
		}
		if(isValidPos(currPos.getX(), currPos.getY() - 1)&&isValidPos(currPos.getX(), currPos.getY() + 1)){
			if(isPossibleToMove(currPos.getX(),currPos.getY() - 1)&& isPossibleToMove(currPos.getX(), currPos.getY() + 1)){
				//move left
				playerTarget=new Position(currPos.getX(),currPos.getY()+1);
				playerSolution=isPlayerCanMoveToBox(currPos, playerPos, playerTarget);
				if(playerSolution!=null || playerTarget.equals(playerPos)){
					Position newPos=new Position(currPos.getX(), currPos.getY()-1);
					State<Position> newState = new State<Position>(newPos, state.getCost() + 1);
					newState.setPreActions(playerSolution.getTheSolution());
					states.put(new Action("left"), newState);
				}
				
				//move right
				playerTarget=new Position(currPos.getX(), currPos.getY()-1);
				playerSolution=isPlayerCanMoveToBox(currPos, playerPos, playerTarget);
				if(playerSolution!=null || playerTarget.equals(playerPos)){
					Position newPos=new Position(currPos.getX(),currPos.getY()+1);
					State<Position> newState = new State<Position>(newPos, state.getCost() + 1);
					newState.setPreActions(playerSolution.getTheSolution());
					states.put(new Action("right"), newState);
				}
				
				
			}
		
		
		}

		return states;
	}

	private boolean isPossibleToMove(int x, int y) {
		return this.board[x][y]==' '||this.board[x][y]=='o';
	}

	private Position getPlayerPos(Action action, Position currPos) {

		if(action!=null){
		String act=action.getAction();
			if(act.equals("up"))
				return new Position(currPos.getX() + 1, currPos.getY());
			if(act.equals("down"))
				return new Position(currPos.getX() - 1, currPos.getY());
			if(act.equals("left"))
				return new Position(currPos.getX(), currPos.getY() + 1);
			if(act.equals("right"))
				return new Position(currPos.getX(), currPos.getY() - 1);
		}
		
		return this.playerInitPos;

	}

	private Solution isPlayerCanMoveToBox(Position currPos, Position playerPos, Position playerTarget) {
		char[][] stateBoard;
		stateBoard = copyBoard(this.board);
		stateBoard[currPos.getX()][currPos.getY()] = '@';
		stateBoard[playerPos.getX()][playerPos.getY()] = 'A';
		
//		for (int i = 0; i < this.board.length; i++) {
//			for (int j = 0; j < this.board[0].length; j++) {
//				System.out.print(stateBoard[i][j]);
//			}
//			System.out.println();
//		} 
		
		this.searchablePlayer.initParams(playerPos, playerTarget, stateBoard);
		//SearchablePlayerToBox s=new SearchablePlayerToBox(playerPos, playerTarget, stateBoard);
		//return this.search.search(this.searchablePlayer);
		
		//this.search=new BFS<Position>();
		((BFS<Position>) this.search).initBFS();
		
		return this.search.search(this.searchablePlayer);
	}

	private Position deletePlayerFromBoard() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'A') {
					this.board[i][j] = ' ';
					return new Position(i, j);
				}
			}
		}
		return null;

	}
}
