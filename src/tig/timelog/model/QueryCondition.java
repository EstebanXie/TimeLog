package tig.timelog.model;

import java.io.Serializable;
import java.util.Date;

/*
 * Classname : QueryCondition.java
 *
 * Version information
 *
 * Date : Jul 26, 2010
 *
 * Copyright notice
 * @author : Herry.Long
 */
@SuppressWarnings("serial")
public class QueryCondition implements Serializable {
	private long agencyId;
	
	// 兼业代理机构组织机构代码
	private String agencyOrgCode;
	private String agencyOrgName;
	
	// 兼业代理机构组织机构类型
	private String agencyOrgType;
	
	// 兼业代理业务许可证编码
	private String permitNo;
	// 金融业务许可证编码
	private String financePermitNo;
	// 兼业代理机构名称
	private String agencyName;

	// 保险公司名称
	private String insurerName;
	
	private String orgId;
	
	private String orgCode;
	
	//保险公司类型
	private String insurerType;
	
	//操作类型
	private int procType;
	//记录的状态
	private long stateId;
	
	//是否有效
	private long isValid;
	
	private String relationCreateApplyType;
	
	private Date fromDate;
	
	private Date endDate;

	public String getAgencyOrgCode() {
		return agencyOrgCode;
	}

	public void setAgencyOrgCode(String agencyOrgCode) {
		this.agencyOrgCode = agencyOrgCode;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public void setFinancePermitNo(String financePermitNo) {
		this.financePermitNo = financePermitNo;
	}

	public String getFinancePermitNo() {
		return financePermitNo;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public int getProcType() {
		return procType;
	}

	public void setProcType(int procType) {
		this.procType = procType;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getIsValid() {
		return isValid;
	}

	public void setIsValid(long isValid) {
		this.isValid = isValid;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	public String getInsurerType() {
		return insurerType;
	}

	public void setInsurerType(String insurerType) {
		this.insurerType = insurerType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAgencyOrgType() {
		return agencyOrgType;
	}

	public void setAgencyOrgType(String agencyOrgType) {
		this.agencyOrgType = agencyOrgType;
	}

	public String getRelationCreateApplyType() {
		return relationCreateApplyType;
	}

	public void setRelationCreateApplyType(String relationCreateApplyType) {
		this.relationCreateApplyType = relationCreateApplyType;
	}

	public String getAgencyOrgName() {
		return agencyOrgName;
	}

	public void setAgencyOrgName(String agencyOrgName) {
		this.agencyOrgName = agencyOrgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
