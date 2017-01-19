package model.data.SokoElements;

import java.util.HashMap;
/**
 *<h1>Element Movable Factory</h1> 
 *create movable elements according to the char type
 */
public class ElementMovableFactory {

	private interface Creator{
		public movable create(Position pos);
	}
	
	private class PlayerCreator implements Creator{
		@Override
		public movable create(Position pos) {
			return new Player(pos);
		}
	}
	
	private class BoxCreator implements Creator{
		@Override
		public movable create(Position pos) {
			return new Box(pos);
		}
	}
	
	HashMap<Character, Creator> movableCreators;
	
	public ElementMovableFactory() {
		this.movableCreators=new HashMap<Character, ElementMovableFactory.Creator>();
		this.movableCreators.put('A', new PlayerCreator());
		this.movableCreators.put('@', new BoxCreator());
	}
	/**
	 * create movable by the type char
	 * @param type This is the key in the HashMap who map between the char(key)and the suitable creator(value)
	 * @param pos Initialize the created movable by the accepted position
	 * @return New movable
	 */
	public movable createMovable(char type, Position pos){
		Creator c=movableCreators.get(type);
		if(c!=null) return c.create(pos); 
		return null;
	}

}
