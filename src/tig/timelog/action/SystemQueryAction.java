package tig.timelog.action;


import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import tig.timelog.model.Paging;
import tig.timelog.model.TTimeLogEntry;
import tig.timelog.service.ICodeTableService;
import tig.timelog.service.ISystemQueryService;
import tig.timelog.service.IUserService;
import tig.timelog.util.ExcelHandle;
import tig.timelog.util.PagingUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SystemQueryAction extends ActionSupport implements ServletResponseAware{
	Logger logger = Logger.getLogger(SystemQueryAction.class.getName());
	private ICodeTableService codeTableService;
	private IUserService userService;
	private ISystemQueryService systemQueryService;
	
	Paging paging  = new Paging();
	private List userList;
	private List projectList;
	private List projectTypeList;
	private List workList;
	private List taskList;
	private List siteList;
	private String ticketNumber;
	private List isOTList;
	private Date beginDate;
	private Date endDate;
	
	private String userId;
	private String projectCode;
	private String workCode;
	private String taskCode;
	private String isOT;
	private List<TTimeLogEntry> timeEntryList;
	private List validateList;
	private String projectName;
	private String siteType;
	
	
	public List getProjectList() {
		return projectList;
	}

	public void setProjectList(List projectList) {
		this.projectList = projectList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}


	private HttpServletResponse response;
	
	public List<TTimeLogEntry> getTimeEntryList() {
		return timeEntryList;
	}

	public void setTimeEntryList(List<TTimeLogEntry> timeEntryList) {
		this.timeEntryList = timeEntryList;
	}

	public List getValidateList() {
		return validateList;
	}

	public void setValidateList(List validateList) {
		this.validateList = validateList;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setCodeTableService(ICodeTableService codeTableService) {
		this.codeTableService = codeTableService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setSystemQueryService(ISystemQueryService systemQueryService) {
		this.systemQueryService = systemQueryService;
	}

	public List getTaskList() {
		return taskList;
	}

	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List getProjectTypeList() {
		return projectTypeList;
	}

	public void setProjectTypeList(List projectTypeList) {
		this.projectTypeList = projectTypeList;
	}

	public List getWorkList() {
		return workList;
	}

	public void setWorkList(List workList) {
		this.workList = workList;
	}

	public List getSiteList() {
		return siteList;
	}

	public void setSiteList(List siteList) {
		this.siteList = siteList;
	}

	public List getTeskList() {
		return taskList;
	}

	public void setTeskList(List teskList) {
		this.taskList = teskList;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public List getIsOTList() {
		return isOTList;
	}

	public void setIsOTList(List isOTList) {
		this.isOTList = isOTList;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SystemQueryAction(){
		
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getIsOT() {
		return isOT;
	}

	public void setIsOT(String isOT) {
		this.isOT = isOT;
	}
	
	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public String initAllInfoQuery(){
		//userList = 
		projectList = codeTableService.getProjects();
		projectTypeList = codeTableService.findProjectTypeList("Project");
		workList = codeTableService.findWorkList("Work");
		taskList = codeTableService.findTaskList("Task");
		siteList = codeTableService.findSiteList("Site");
		isOTList = codeTableService.findIsOTList("OT");
		userList= userService.getAllUsers();
		return SUCCESS;
	}
	
	public String allInfoQuery(){
		
		timeEntryList = systemQueryService.findTimeLogEntry(userId, projectCode, workCode, taskCode, ticketNumber, isOT, beginDate, endDate,projectName, siteType);
		 if(timeEntryList!=null&&timeEntryList.size()>0){
			    PagingUtil.getPaging(this.paging, this.timeEntryList.size()); 
			    int iStartLimit = PagingUtil.getStartLimit(this.paging);
			    int end = PagingUtil.getLastLimit(iStartLimit, this.paging, this.timeEntryList.size());
			    List<TTimeLogEntry> pagedList = new ArrayList<TTimeLogEntry>();
			    for (int i = iStartLimit; i < end; i++) {
			      pagedList.add(this.timeEntryList.get(i));
			    }
			   
			    this.timeEntryList = pagedList;
			    
		    }else{
		    	this.addActionMessage(super.getText("noRecord.msg"));
		    }
//		projectTypeList = codeTableService.findProjectTypeList("Project");
		projectList = codeTableService.getProjects();
		projectTypeList = codeTableService.findProjectTypeList("Project");
		workList = codeTableService.findWorkList("Work");
		taskList = codeTableService.findTaskList("Task");
		siteList = codeTableService.findSiteList("Site");
		isOTList = codeTableService.findIsOTList("OT");
		userList= userService.getAllUsers();
		return SUCCESS;
	}
	public String initvalidateQuery(){
		userList= userService.getAllUsers();
		return SUCCESS;
	}
	public String validateQuery(){	
		userList= userService.getAllUsers();
		if(beginDate == null || endDate == null){
			this.addActionMessage(super.getText("begingenddateisrequired.msg"));
			return SUCCESS; 
		}else if(beginDate.after(endDate)){
			this.addActionMessage(super.getText("begingdatelaterthanenddate.msg"));
			return SUCCESS; 
		}else if(beginDate.before(new java.sql.Date(endDate.getTime()-86400000*6))){
			this.addActionMessage(super.getText("validationover7days.msg"));
			return SUCCESS; 
		}
		validateList = systemQueryService.findValidate(userId, beginDate, endDate);
		 if(validateList!=null&&validateList.size()>0){
			    PagingUtil.getPaging(this.paging, this.validateList.size()); 
			    int iStartLimit = PagingUtil.getStartLimit(this.paging);
			    int end = PagingUtil.getLastLimit(iStartLimit, this.paging, this.validateList.size());
			    List pagedList = new ArrayList();
			    for (int i = iStartLimit; i < end; i++) {
			      pagedList.add(this.validateList.get(i));
			    }
			    this.validateList = pagedList;
		    }else{
		    	this.addActionMessage(super.getText("noRecord.msg"));
		    }
		return SUCCESS;
	}
	
	public void exportRptDimenExcel() throws Exception {
		
		timeEntryList = systemQueryService.findTimeLogEntry(userId, projectCode, workCode, taskCode, ticketNumber, isOT, beginDate, endDate,projectName, siteType);
/*		projectTypeList = codeTableService.findProjectTypeList("Project");
		workList = codeTableService.findWorkList("Work");
		taskList = codeTableService.findTaskList("Task");
		siteList = codeTableService.findSiteList("Site");
		isOTList = codeTableService.findIsOTList("OT");
		userList= userService.getAllUsers();*/
		
		ExcelHandle.responseInit(response, "Time Log");
		
		OutputStream os = response.getOutputStream();
		ExcelHandle.writeDataSearchExcel(os, timeEntryList);
		
		os.flush();
		os.close();
		
		
		
	/*	if ("A".equals(searchDimension)) {
			long insurOrgId = insurerOrgId!=null && !insurerOrgId.equals("") ? Long.valueOf(insurerOrgId) : 0;
			dataRptList = dataMgmtService.getDataRptDtlByYM(startYear, startMonth,endYear,endMonth, insurOrgId, 0, searchDimension);
		} else if ("B".equals(searchDimension)) {
			long agtId = agencyId!=null && !agencyId.equals("") ? Long.valueOf(agencyId) : 0;
			dataRptList = dataMgmtService.getDataRptDtlByYM(startYear, startMonth,endYear,endMonth, 0, agtId, searchDimension);
		}else if("C".equals(searchDimension)){
			if(orgCateType==null) orgCateType = "";
			dataRptList = dataMgmtService.getDataRptStatisticByYM(startYear, startMonth,endYear,endMonth,orgCateType,searchDimension);
		}
		yearList = new ArrayList<String>();
		monthList = new ArrayList<String>();
		for (int i=2010; i<=2050; i++) {
			yearList.add(String.valueOf(i));
		}
		for (int i=1; i<=12; i++) {
			monthList.add(String.format("%02d", i));
		}
		
		cate2List = dataInitService.findAllOrgType();
		TOrgCate2Code catAgency = new TOrgCate2Code();
		catAgency.setOrgCate2Code("2000");
		catAgency.setOrgCate2Name("专业代理");
		TOrgCate2Code catborker = new TOrgCate2Code();
		catborker.setOrgCate2Code("3000");
		catborker.setOrgCate2Name("经纪");
		cate2List.add(catAgency);
		cate2List.add(catborker);
		
		insurerList = dataInitService.findAllInsurerCode();
		insurCateList = dataMgmtService.getAllInsurCate();
		//insurCateSize = insurCateList!=null && insurCateList.size()>0 ? String.valueOf(insurCateList.size()) : "0";
		insurCateSize = "3";
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String("数据查询结果.xls".getBytes("UTF-8"), "ISO8859_1"));
		OutputStream os = response.getOutputStream();
		ExcelHandle.writeDataSearchExcel(os, this.searchDimension,
				this.dataRptList,this.insurCateList,this.insurerList,this.insurCateSize);
		os.flush();
		os.close();*/
		
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		 this.response = arg0;
		
	}
}


