package tig.timelog.model;

import java.io.Serializable;

/***********************************************************************
 * Module: TUser.java
 * Author: Cecilia.Chen
 * Purpose: Defines the Class TUser
 ***********************************************************************/

public class TUser implements Serializable {

  private String userId;
  private java.lang.String userName;
  private java.lang.String password;
  private java.lang.String realName;
  private java.lang.String phoneNumber;
  private int state;
  private java.util.Date insertDate;
  private java.util.Date updateDate;
  
  private TRole role;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public java.lang.String getUserName() {
    return userName;
  }

  public void setUserName(java.lang.String userName) {
    this.userName = userName;
  }

  public java.lang.String getPassword() {
    return password;
  }

  public void setPassword(java.lang.String password) {
    this.password = password;
  }

  public java.lang.String getRealName() {
    return realName;
  }

  public void setRealName(java.lang.String realName) {
    this.realName = realName;
  }

  public java.lang.String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(java.lang.String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public java.util.Date getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(java.util.Date insertDate) {
    this.insertDate = insertDate;
  }

  public java.util.Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(java.util.Date updateDate) {
    this.updateDate = updateDate;
  }

  public void setRole(TRole role) {
    this.role = role;
  }

  public TRole getRole() {
    return role;
  }
}
