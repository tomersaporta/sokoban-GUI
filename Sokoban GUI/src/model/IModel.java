package model;

import java.util.List;

import commons.Level;
import commons.Record;

public interface IModel {
	
	public Level getCurrentLevel();
	public void LoadLevel(String filepath);
	public void SaveLevel(String filepath);
	public void move(String moveInput);
	public int getSteps();
	public List<Record> getRecordsList();
	public void dbQuery(String params);

}
