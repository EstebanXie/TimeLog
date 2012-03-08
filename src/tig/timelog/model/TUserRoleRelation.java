package tig.timelog.model;

import java.io.Serializable;

/***********************************************************************
 * Module: TUserRoleRelation.java 
 * Author: Cecilia.Chen 
 * Purpose: Defines the Class TUserRoleRelation
 ***********************************************************************/

public class TUserRoleRelation implements Serializable {

	private long relationId;

	private long userId;

	private long roleId;

	public long getRelationId() {
		return relationId;
	}

	public void setRelationId(long relationId) {
		this.relationId = relationId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
