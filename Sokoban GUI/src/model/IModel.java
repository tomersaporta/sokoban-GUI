package model;

import commons.Level;

public interface IModel {
	
	public Level getCurrentLevel();
	public void LoadLevel(String filepath);
	public void SaveLevel(String filepath);
	public void move(String moveInput);
}
