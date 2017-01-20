package model.policy;

import commons.Level;
import model.data.SokoElements.Box;
import model.data.SokoElements.Floor;
import model.data.SokoElements.GeneralElement;
import model.data.SokoElements.Player;
import model.data.SokoElements.Position;
import model.data.SokoElements.unmovable;
import model.policy.moveType.IMoveType;

/**
 * <h1>My Sokoban Policy</h1>
 * defines the policy:
 * 1. actor can't walk thought walls
 * 2. actor can't push box on another box/wall
 * 3. actor can't pull boxes
 */

public class MySokobanPolicy extends GeneralSokobanPolicy {

	public MySokobanPolicy(Level level) {
		super(level);
	}
	/**
	 * the method check if the actor can move according to the moveType
	 */
	@Override
	public void checkPolicy(Player player, IMoveType moveType) {
		
		//get the next Position step
		Position nextPosition= moveType.getNextPosition(player.getPosition());
		GeneralElement elementInNextPosition=getLevel().getElementInPosition(nextPosition);
		
		if(elementInNextPosition instanceof unmovable){
			if(((unmovable) elementInNextPosition).isStepable())
				getLevel().upDateLevelPlayerMoves(player, nextPosition);
		}
		
		else if (elementInNextPosition instanceof Box)
		{
			Position boxNextPosition= moveType.getNextPosition(elementInNextPosition.getPosition());
			GeneralElement elementInBoxNextPosition=getLevel().getElementInPosition(boxNextPosition);
			
			if(elementInBoxNextPosition instanceof Floor){
				getLevel().upDateLevelPlayerBoxMoves(player,(Box)elementInNextPosition, boxNextPosition);
			}
		}
	/*
	
	
		public void checkPolicy(GeneralElement element, IMoveType moveType) {
			
			//get the next Position step
			Position2D nextPosition=(Position2D) moveType.getNextPosition(element.getPosition());
			GeneralElement elementInNextPosition=getLevel().getElementInPosition(nextPosition);
			
			if(isPosibleToMoveThrough(element, elementInNextPosition))
				{
					checkPolicy(elementInNextPosition, moveType);
				}
			}
			
		}
		
		boolean isPosibleToMoveThrough(GeneralElement element1, GeneralElement element2){
			if(element1 instanceof Player){
				if(element2 instanceof Floor)
					return true;
				if(element2 instanceof Wall)
					return false;
				if(element2 instanceof Box)
					return true;		
				if(element2 instanceof Player)
					return false;
		      }
			if(element1 instanceof Box){
				if(element2 instanceof Floor)
					return true;
				if(element2 instanceof Wall)
					return false;
				if(element2 instanceof Box)
					return false;
				if(element2 instanceof Player)
					return false;
    	      }
			return false;*/
		}

}
