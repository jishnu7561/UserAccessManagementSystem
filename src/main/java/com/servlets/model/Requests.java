package com.servlets.model;

public class Requests {

	private int id;             
    private int userId;         
    private int softwareId;     
    private String accessType;  
    private String reason;      
    private RequestStatus status;
    
    
	
	public Requests() {
		super();
	}
	public Requests(int userId, int softwareId, String accessType, String reason, RequestStatus status) {
		super();
		this.userId = userId;
		this.softwareId = softwareId;
		this.accessType = accessType;
		this.reason = reason;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(int softwareId) {
		this.softwareId = softwareId;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status.toString();
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
    
    
}
