package tig.timelog.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tig.timelog.model.Code;
import tig.timelog.model.Paging;
import tig.timelog.model.TProject;
import tig.timelog.service.ICodeTableService;
import tig.timelog.service.IProjectMgmtService;
import tig.timelog.util.PagingUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ProjectMgmtAction extends ActionSupport {
	protected final Logger log = Logger.getLogger(ProjectMgmtAction.class);

	private IProjectMgmtService projectMgmtService;
	private ICodeTableService codeTableService;

	private List<TProject> projects;
	
	private List<Code> projectTypes;
	
	private List<Code> siteTypes;
	
	private TProject prjCriteria;
	
	private String[] projectIdArray;

	Paging paging = new Paging();
	
	public String[] getProjectIdArray() {
		return projectIdArray;
	}

	public void setProjectIdArray(String[] projectIdArray) {
		this.projectIdArray = projectIdArray;
	}

	public List<Code> getSiteTypes() {
		return siteTypes;
	}

	public void setSiteTypes(List<Code> siteTypes) {
		this.siteTypes = siteTypes;
	}

	public IProjectMgmtService getProjectMgmtService() {
		return projectMgmtService;
	}

	public void setProjectMgmtService(IProjectMgmtService projectMgmtService) {
		this.projectMgmtService = projectMgmtService;
	}

	public List<TProject> getProjects() {
		return projects;
	}

	public void setProjects(List<TProject> projects) {
		this.projects = projects;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public TProject getPrjCriteria() {
		return prjCriteria;
	}

	public void setPrjCriteria(TProject prjCriteria) {
		this.prjCriteria = prjCriteria;
	}

	public List<Code> getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(List<Code> projectTypes) {
		this.projectTypes = projectTypes;
	}

	public ICodeTableService getCodeTableService() {
		return codeTableService;
	}

	public void setCodeTableService(ICodeTableService codeTableService) {
		this.codeTableService = codeTableService;
	}

	public String queryProjects() {
		projects = projectMgmtService.listAllProjects(prjCriteria);
		initPrjType();

		if (projects != null && projects.size() > 0) {
			PagingUtil.getPaging(this.paging, this.projects.size());
			int iStartLimit = PagingUtil.getStartLimit(this.paging);
			int end = PagingUtil.getLastLimit(iStartLimit, this.paging,
					this.projects.size());
			List<TProject> pagedList = new ArrayList<TProject>();
			for (int i = iStartLimit; i < end; i++) {
				pagedList.add(this.projects.get(i));
			}

			this.projects = pagedList;

		}
		return SUCCESS;
	}
	
	
	public String toAddProjects(){
		initPrjType();
		initSiteType();
		return SUCCESS;
	}
	
	public String addProject(){
		projectMgmtService.addProject(prjCriteria);
		this.addActionMessage(super.getText("projectOperationSuccess.msg"));
		return SUCCESS;
	}
	
	public String toModifyProject()
	{
		initPrjType();
		initSiteType();
		
		TProject project = new TProject();
		project.setProjectID(Integer.valueOf(projectIdArray[0]));
		prjCriteria = projectMgmtService.listAllProjects(project).get(0);
		return SUCCESS;
	}
	
	public String modifyProject(){
		projectMgmtService.modifyProject(prjCriteria);
		this.addActionMessage(super.getText("projectOperationSuccess.msg"));
		return SUCCESS;
	}


	private void initPrjType()
	{
		projectTypes = codeTableService.findProjectTypeList("Project");
	}
	
	private void initSiteType()
	{
		siteTypes = codeTableService.findSiteList("Site");
	}
}
