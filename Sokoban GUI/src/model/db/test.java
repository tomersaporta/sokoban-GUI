package model.db;

import java.sql.Time;

import commons.Level;


public class test {

	public static void main(String[] args) {
		
		Manager m=Manager.getInstance();
		
		m.getAllRecords();
		/*User u=new User("123456", "Tomer");
		m.add(u);
		
		Level l=new Level();
		l.setLevelID("level1");
		m.add(l);
		Record r=new Record(l.getLevelID(), u.getId(), 60, new Time(18, 00, 00));
		//Record rec = new Record(l.getLevelID(),u.getId(),50, new Time(0) );
		m.add(r);
		
		/*Record r=new Record("level1", "yardi", 5, new Time(0));
		RecordDBManager rm=new RecordDBManager();
		rm.add(r);*/
	}

}
