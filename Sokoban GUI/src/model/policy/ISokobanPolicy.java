package model.policy;

import model.data.SokoElements.Player;
import model.policy.moveType.IMoveType;
/**
 * <h1>Sokoban Policy interface</h1>
 * defines the behavior that all the Sokoban's policies needs to implement
 *
 */
public interface ISokobanPolicy {
	
	public void checkPolicy(Player p, IMoveType moveType);
}
