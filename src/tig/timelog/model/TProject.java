package tig.timelog.model;

import java.sql.Date;

public class TProject {
	private Integer projectID;
	
	private String projectName;
	
	private String projectTypeID;
	
	private String projectTypeName;
	
	private String siteTypeID;
	
	private String siteTypeName;
	
	private Date startDate;
	
	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectTypeID() {
		return projectTypeID;
	}

	public void setProjectTypeID(String projectTypeID) {
		this.projectTypeID = projectTypeID;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public String getSiteTypeName() {
		return siteTypeName;
	}

	public void setSiteTypeName(String siteTypeName) {
		this.siteTypeName = siteTypeName;
	}

	public String getSiteTypeID() {
		return siteTypeID;
	}

	public void setSiteTypeID(String siteTypeID) {
		this.siteTypeID = siteTypeID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	private Date enddate;
}
