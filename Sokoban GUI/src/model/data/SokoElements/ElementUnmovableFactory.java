package model.data.SokoElements;

import java.util.HashMap;
/**
 *<h1>Element Unmovable Factory</h1> 
 *create unmovable elements according to the char type
 */
public class ElementUnmovableFactory {

	private interface Creator{
		public unmovable create(Position pos);
	}
	
	private class WallCreator implements Creator{
		@Override
		public unmovable create(Position pos) {
			return new Wall(pos);
		}
	}
	
	private class TargetCreator implements Creator{
		@Override
		public unmovable create(Position pos) {
			return new Target(pos);
		}
	}
	
	private class FlooerCreator implements Creator{
		@Override
		public unmovable create(Position pos) {
			return new Floor(pos);
		}
	}
	
	HashMap<Character, Creator> unmovableCreators;
	
	public ElementUnmovableFactory() {
		this.unmovableCreators=new HashMap<Character, ElementUnmovableFactory.Creator>();
		this.unmovableCreators.put('o', new TargetCreator());
		this.unmovableCreators.put(' ', new FlooerCreator());
		this.unmovableCreators.put('#', new WallCreator());
	}
	/**
	 * create unmovable by the type char
	 * @param type This is the key in the HashMap who map between the char(key)and the suitable creator(value)
	 * @param pos Initialize the created unmovable by the accepted position
	 * @return New unmovable
	 */
	public unmovable createUnmovable(char type,Position pos){
		Creator c=unmovableCreators.get(type);
		if(c!=null) return c.create(pos); 
		return null;
	}

}
