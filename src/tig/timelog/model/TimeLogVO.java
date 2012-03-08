package tig.timelog.model;

import java.sql.Date;

public class TimeLogVO {
	private String userName;
	private float workHour;
	private Date workTime;
	private float otHour;
	private float nonOTHour;
	public float getOtHour() {
		return otHour;
	}
	public void setOtHour(float otHour) {
		this.otHour = otHour;
	}
	public float getNonOTHour() {
		return nonOTHour;
	}
	public void setNonOTHour(float nonOTHour) {
		this.nonOTHour = nonOTHour;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getWorkHour() {
		return workHour;
	}
	public void setWorkHour(float workHour) {
		this.workHour = workHour;
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	
	
}
