package model.db;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Records")
public class Record {

	
	@Column(name="RecordID")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recordId;
	
	@Column(name="LevelID")
	private String levelId;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="Steps")
	private int steps;
	
	@Column(name="LevelTime")
	private Time time;
	
	public Record() {
	}

	public Record(String levelId, String userName, int steps, Time time) {
		this.levelId = levelId;
		this.userName = userName;
		this.steps = steps;
		this.time = time;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", levelId=" + levelId + ", userName=" + userName + ", steps=" + steps
				+ ", time=" + time + "]";
	}
	
	
}
