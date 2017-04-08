package model.db;

public class QueryParams {
	
	private String levelId;
	private String userName;
	private String orderBy;
	
	public QueryParams() {
		this.levelId = null;
		this.userName = null;
		this.orderBy = null;
	}

	public QueryParams(String levelId, String userName, String orderBy) {
		this.levelId = levelId;
		this.userName = userName;
		this.orderBy = orderBy;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
	
	
	
}
