package tig.timelog.action;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tig.timelog.constant.ContextConstants;
import tig.timelog.model.Code;
import tig.timelog.model.TTimeLogEntry;
import tig.timelog.model.TUser;
import tig.timelog.service.ICodeTableService;
import tig.timelog.service.IDataMgmtService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DataMgmtAction extends ActionSupport {
	ActionContext context = ActionContext.getContext();
	
	HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);

	private IDataMgmtService dataMgmtService;
	
	private ICodeTableService codeTableService;
	
	private Date enterDateSelected;

	private List<TTimeLogEntry> timeLogEntries;

	private List<Code> listProjectType;
	
	private List<Code> listWorkType;
	
	private List<Code> listTaskType;
	
	private List<Code> listSiteType;
	
	private List<Code> listisOT;
	
	private List<List> listProject;
	
	private List<Code> listTaskTypeED;
	private List<Code> listTaskTypeEMCM;
	
	

	public List<List> getListProject() {
		return listProject;
	}

	public void setListProject(List<List> listProject) {
		this.listProject = listProject;
	}

	public List<TTimeLogEntry> getTimeLogEntries() {
		return timeLogEntries;
	}

	public void setTimeLogEntries(List<TTimeLogEntry> timeLogEntries) {
		this.timeLogEntries = timeLogEntries;
	}

	public Date getEnterDateSelected() {
		return enterDateSelected;
	}

	public void setEnterDateSelected(Date enterDateSelected) {
		this.enterDateSelected = enterDateSelected;
	}

	public IDataMgmtService getDataMgmtService() {
		return dataMgmtService;
	}

	public void setDataMgmtService(IDataMgmtService dataMgmtService) {
		this.dataMgmtService = dataMgmtService;
	}

	

	@SuppressWarnings("unused")
	private HttpServletResponse response;

	public String initTimeLogDate() throws Exception {
	
		return SUCCESS;
	}
	
	
	
	public String initTimeLogEdit() throws Exception {
		initTimeEntryList();
		initList();
		
		return SUCCESS;
	}
	
	
	public void validateSaveTimeLogEdit(){
//		float nonOTTime = 0;
//		float otTime = 0;
//		boolean isOTTime = false;
//		for(TTimeLogEntry retry : timeLogEntries){
//			if(retry.getProjectId()!= null && !"".equals(retry.getProjectId())){
//				if(!retry.isOt()){
//					nonOTTime += retry.getWorkHour();
//				} else if (retry.isOt()) {
//					otTime += retry.getWorkHour();
//					isOTTime = true;
//				}
//			}
//		}
//		// when work at Saturday and Sunday,Only check if OT work hour is greater
//		// than zero
//		if (enterDateSelected != null) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(enterDateSelected);
//			if (calendar.get(Calendar.DAY_OF_WEEK) == 1
//					|| calendar.get(Calendar.DAY_OF_WEEK) == 7) {
//				if (otTime > 0) {
//					return;
//				} else if (otTime <= 0 && isOTTime) {
//					initList();
//					super.addActionError(super
//							.getText("validation.checkOT.greaterThanZeroMsg"));
//					return;
//				}
//			}
//		}
//		if (nonOTTime < 8){
//			initList();
//			super.addActionError(super.getText("validation.checkOT.msg"));
//		}
		boolean isTicketFill =true;
		boolean isWorkTypeFill = true;
		for(TTimeLogEntry retry : timeLogEntries){
			if(retry.getProjectId()!= null && !"".equals(retry.getProjectId())){
				if(retry.getProjectTypeId().equals("EM")||retry.getProjectTypeId().equals("CM"))
					if(retry.getTicketNumber()==null||"".equals(retry.getTicketNumber())){
						isTicketFill = false;
						break;
					}
					
			}
		}
		for(TTimeLogEntry retry : timeLogEntries){
			if(retry.getProjectId()!= null && !"".equals(retry.getProjectId())&&retry.getProjectTypeId()!=null&&!"".equals(retry.getProjectTypeId())){
				if(retry.getWorkTypeId()==null||"".equals(retry.getWorkTypeId())){
					isWorkTypeFill = false;
					break;
				}
					
			}
		}
		System.out.print("Ticket:"+isTicketFill);
		if(!isTicketFill){
			initList();
			super.addActionError(super.getText("validation.checkTicket.msg"));
		}
		if(!isWorkTypeFill){
			initList();
			super.addActionError(super.getText("validation.checkWorkType.msg"));
		}
		
	}

	public String saveTimeLogEdit(){

		dataMgmtService.saveTimeLogEnties(timeLogEntries);
		
		initTimeEntryList();
		initList();
	    this.addActionMessage(super.getText("dateMgmtUpdate.msg"));
		return SUCCESS;
	}
	
	
	public String deleteTimeLogEntry(){
		dataMgmtService.deleteTimeLogEnties(timeLogEntries);
		
		initTimeEntryList();
		initList();
		this.addActionMessage(super.getText("dataMgmtDelete.msg"));
		return SUCCESS;
	}
	
	private void initList(){

		listProjectType = codeTableService.findProjectTypeList(ContextConstants.CODE_PROJECT);
		listProject = new ArrayList();
		listProject.add(new ArrayList());
		for(Code c : listProjectType){
			listProject.add(codeTableService.findProjectByProjectType(c.getCodeName(), enterDateSelected));
		}
		//listProject = codeTableService.getProjects();
		listWorkType = codeTableService.findWorkList(ContextConstants.CODE_WORK);
		listTaskType = codeTableService.findWorkList(ContextConstants.CODE_TASK);
		listTaskTypeED = new ArrayList();
		listTaskTypeEMCM = new ArrayList();
		for(Code c:listTaskType){
			if(c.getCodeName().equals("A")||c.getCodeName().equals("CG")||c.getCodeName().equals("DE"))
				listTaskTypeEMCM.add(c);
			else if(c.getCodeName().equals("C")||c.getCodeName().equals("D")||c.getCodeName().equals("R")){
				listTaskTypeED.add(c);
			}
		}
//		for(Code c:listProjectType){
//			if(c.getCodeName().equals("ED")||c.getCodeName().equals("EM")||c.getCodeName().equals("CM"))
//				listTaskType = codeTableService.findWorkList(ContextConstants.CODE_TASK,c.getCodeName());
//		}
		
		//listSiteType = codeTableService.findWorkList(ContextConstants.CODE_SITE);
		
		listisOT = codeTableService.findWorkList(ContextConstants.CODE_OT);
	}
	
	private void initTimeEntryList(){
		TUser u = (TUser) request.getSession().getAttribute(ContextConstants.CURRENT_LOGINED_USER_CONTEXT);
		timeLogEntries = dataMgmtService.getTimeLogEntiesByDate(enterDateSelected, u.getUserId());
		
		for(int i = timeLogEntries.size(); i < ContextConstants.TIME_ENTRY_NUMBER; i++){
		    TTimeLogEntry entry = new TTimeLogEntry(u.getUserId(), enterDateSelected);
			timeLogEntries.add(entry);
		}
	}
	
	public void updateTaskType(){
		List<Code> EDTask= new ArrayList<Code>();
		List<Code> EM_CMTask = new ArrayList<Code>(); 
		initList();
		String projType = request.getParameter("projType");
		for(Code c : listTaskType){
			if(projType.equals("ED")){
				if(c.getCodeName().equals("C")||c.getCodeName().equals("D")||c.getCodeName().equals("R"))
					EDTask.add(c);
			}
			else if(projType.equals("EM")||projType.equals("CM")){
				if(c.getCodeName().equals("A")||c.getCodeName().equals("CG")||c.getCodeName().equals("DE"))
					EM_CMTask.add(c);
			}
		}
		if(EDTask.size()!=0)
			listTaskType = EDTask;
		else if(EM_CMTask.size()!=0)
			listTaskType = EM_CMTask;
		
	}
	public List getListWorkType() {
		return listWorkType;
	}

	public void setListWorkType(List listWorkType) {
		this.listWorkType = listWorkType;
	}

	public List getListTaskType() {
		return listTaskType;
	}

	public void setListTaskType(List listTaskType) {
		this.listTaskType = listTaskType;
	}

	public List getListSiteType() {
		return listSiteType;
	}

	public void setListSiteType(List listSiteType) {
		this.listSiteType = listSiteType;
	}

	public List getListisOT() {
		return listisOT;
	}

	public void setListisOT(List listisOT) {
		this.listisOT = listisOT;
	}


	public ICodeTableService getCodeTableService() {
		return codeTableService;
	}

	public void setCodeTableService(ICodeTableService codeTableService) {
		this.codeTableService = codeTableService;
	}

	public List<Code> getListTaskTypeED() {
		return listTaskTypeED;
	}

	public void setListTaskTypeED(List<Code> listTaskTypeED) {
		this.listTaskTypeED = listTaskTypeED;
	}

	public List<Code> getListTaskTypeEMCM() {
		return listTaskTypeEMCM;
	}

	public void setListTaskTypeEMCM(List<Code> listTaskTypeEMCM) {
		this.listTaskTypeEMCM = listTaskTypeEMCM;
	}

	public List<Code> getListProjectType() {
		return listProjectType;
	}

	public void setListProjectType(List<Code> listProjectType) {
		this.listProjectType = listProjectType;
	}

	

}
