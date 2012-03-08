package tig.timelog.service.impl;



import java.sql.Date;
import java.util.List;

import tig.timelog.dao.ICodeTableDao;
import tig.timelog.model.TProject;
import tig.timelog.service.ICodeTableService;

public class CodeTableServiceImpl implements ICodeTableService {
	private ICodeTableDao codeTableDao;
	
	@Override
	public List findSiteList(String siteType) {
		return codeTableDao.getCodeList(siteType);
	}
	
	@Override
	public List findProjectTypeList(String projType) {
		return codeTableDao.getCodeList(projType);
	}
	
	@Override
	public List findTaskList(String taskType) {
		return codeTableDao.getCodeList(taskType);
	}
	
	@Override
	public List findWorkList(String siteType) {
		return codeTableDao.getCodeList(siteType);
	}
	
	@Override
	public List findIsOTList(String ot) {
		return codeTableDao.getCodeList(ot);
	}

	public void setCodeTableDao(ICodeTableDao codeTableDao) {
		this.codeTableDao = codeTableDao;
	}


	@Override
	public List<TProject> getProjects(){
		return codeTableDao.getProjects();
	}

	@Override
	public List findProjectByProjectType(String projectType, Date selectedDate) {
		return codeTableDao.getProjectByType(projectType, selectedDate);
	}
}



