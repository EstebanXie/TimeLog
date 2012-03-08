package tig.timelog.service;



import java.sql.Date;
import java.util.List;

import tig.timelog.model.TProject;

public interface ICodeTableService {
	
	public List findSiteList(String siteType);
	
	public List findProjectTypeList(String projType);
	
	public List findTaskList(String taskType);
	
	public List findWorkList(String siteType);
	
	public List findIsOTList(String ot);
	
	public List findProjectByProjectType(String projectType, Date selectedDate);
	
	public List<TProject> getProjects();
}
