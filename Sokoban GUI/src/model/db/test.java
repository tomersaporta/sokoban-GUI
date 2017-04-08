package model.db;

import java.sql.Time;

import commons.Level;


public class test {

	public static void main(String[] args) {
		
		Manager m=Manager.getInstance();
		QueryParams params=new QueryParams("level4", null, "time");
		m.recordsQuery(params);
		//m.getAllRecords();
		//m.getAllRecordsByLevel();
		
		/*User u=new User("yarden4");
		m.add(u);
		
		Level l=new Level();
		l.setLevelID("level4");
		m.add(l);
		Record r=new Record(l.getLevelID(), u.getName(), 60, new Time (System.currentTimeMillis()));
		//Record rec = new Record(l.getLevelID(),u.getId(),50, new Time(0) );
		m.add(r);
		
		
		
		Record r=new Record("level4", "yarden4", 40, new Time(System.currentTimeMillis()));
		m.add(r);
		r=new Record("level4", "yarden4", 50, new Time(System.currentTimeMillis()));
		m.add(r);*/
	}

}
