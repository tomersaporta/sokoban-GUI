package model.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import commons.Level;

@Entity(name="Users")
public class User {
	
	@Id
	@Column(name="UserID")
	private String id;
	
	@Column(name="UserName")
	private String name;
	
	@OneToMany
	@JoinColumn(name="UserID")
	private List<Level> levels = new ArrayList<Level>();

	public User() {
	}
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
