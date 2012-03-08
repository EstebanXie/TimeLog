package tig.timelog.model;

import java.io.Serializable;

/***********************************************************************
 * Module: TRole.java 
 * Author: Cecilia.Chen 
 * Purpose: Defines the Class TRole
 ***********************************************************************/

public class TRole implements Serializable {

	private String roleId;

	private java.lang.String roleName;
  
  private String remark;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getRoleName() {
		return roleName;
	}

	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getRemark() {
    return remark;
  }
}
