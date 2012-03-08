package tig.timelog.model;

import java.io.Serializable;
import java.sql.Date;

import tig.timelog.constant.ContextConstants;

public class TTimeLogEntry implements Serializable{

	public boolean isDeleteF() {
		return deleteF;
	}

	public void setDeleteF(boolean deleteF) {
		this.deleteF = deleteF;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465056843500629004L;
	
	private String timeLogID;
	
	
	public TTimeLogEntry(String userID, Date workTime){
		timeLogID="";
		userId = userID;
		projectId= "";
		projectTypeId="";
		workTypeId="";
	    taskId="";
		ticketNumber="";
		workHour= 0f;
		overTime=ContextConstants.FALSE;
		notes="";
		this.workTime = workTime ;
		
		deleteF = false;
	}
	
	public TTimeLogEntry(){
		
	}
	
	public String getTimeLogID() {
		return timeLogID;
	}

	public void setTimeLogID(String timeLogID) {
		this.timeLogID = timeLogID;
	}

	private boolean deleteF;
	
	private String userId;
	
	private String projectId;
	
	private String projectTypeId;
	
	private String workTypeId;
	
	private String taskId;
	
	private String ticketNumber;
	
	private Float workHour;
	
	private String overTime;
	
	private String projectName;
	
	private String siteName;
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private boolean ot;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Float getWorkHour() {
		if (workHour == null){
			return 0f;
		}
		return workHour;
	}

	public void setWorkHour(Float workHour) {
		this.workHour = workHour;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
		if(ContextConstants.TRUE.equals(overTime)){
			this.ot = true;
		}else{
			this.ot = false;
		}
	}

	public boolean isOt() {
		return ot;
	}

	public void setOt(boolean ot) {
		this.ot = ot;
		if(ot){
			this.overTime = ContextConstants.TRUE;
		}else{
			this.overTime = ContextConstants.FALSE;
		}
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	private String notes;
	
	private Date workTime;
	
	private String userNameString;
	private String projectString;
	private String projectTypeString;
	private String workString;
	private String taskString;
	private String isOTString;
	private String siteString;


	public String getSiteString() {
		return siteString;
	}

	public void setSiteString(String siteString) {
		this.siteString = siteString;
	}

	public String getUserNameString() {
		return userNameString;
	}

	public void setUserNameString(String userNameString) {
		this.userNameString = userNameString;
	}

	public String getProjectString() {
		return projectString;
	}

	public void setProjectString(String projectString) {
		this.projectString = projectString;
	}

	public String getProjectTypeString() {
		return projectTypeString;
	}

	public void setProjectTypeString(String projectTypeString) {
		this.projectTypeString = projectTypeString;
	}

	public String getWorkString() {
		return workString;
	}

	public void setWorkString(String workString) {
		this.workString = workString;
	}

	public String getTaskString() {
		return taskString;
	}

	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}

	public String getIsOTString() {
		return isOTString;
	}

	public void setIsOTString(String isOTString) {
		this.isOTString = isOTString;
	}
	
	
}
