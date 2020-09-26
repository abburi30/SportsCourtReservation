package com.project.pojo;

import com.project.service.user.CheckStatus;

public class BookingStatus {

	public String newStaus;
	private String query;
	private String updateQuery;
	private String status;
	private String updaterow;

	public String getNewStaus() {
		return newStaus;
	}

	public void setNewStaus(String newStaus) {
		this.newStaus = newStaus;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdaterow() {
		return updaterow;
	}

	public void setUpdaterow(String updaterow) {
		this.updaterow = updaterow;
	}

}
