package tig.timelog.model;

import java.io.Serializable;

/***********************************************************************
 * Module: TRoleResourceRelation.java 
 * Author: Cecilia.Chen 
 * Purpose: Defines the Class TRoleResourceRelation
 ***********************************************************************/

public class TRoleResourceRelation implements Serializable {
	private long relationId;
	private long roleId;
	private long resourceId;

	public long getRelationId() {
		return relationId;
	}

	public void setRelationId(long relationId) {
		this.relationId = relationId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

}
