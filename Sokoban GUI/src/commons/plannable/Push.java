package commons.plannable;

import plannable.Action;
import plannable.Predicate;

public class Push extends Action {
	
	public Push() {
		this.name="Push";
		this.args=new String[]{"Box","fromPosition","toPosition"};
		
		
		this.preconditions.add(new Predicate("On","Player", "NextFromPosition"));
		
		this.addList.add(new Predicate("On","Box", "toPosition"));
	}
	
	

}
