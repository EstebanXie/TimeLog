package tig.timelog.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import tig.timelog.dao.IProjectMgmtDao;
import tig.timelog.model.TProject;
import tig.timelog.service.IProjectMgmtService;

public class ProjectMgmtServiceImpl implements IProjectMgmtService {
	Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	private IProjectMgmtDao projectMgmtDao;
	

	public IProjectMgmtDao getProjectMgmtDao() {
		return projectMgmtDao;
	}


	public void setProjectMgmtDao(IProjectMgmtDao projectMgmtDao) {
		this.projectMgmtDao = projectMgmtDao;
	}


	@Override
	public List<TProject> listAllProjects(TProject project) {
		return projectMgmtDao.listAllProjects(project);
	}


	@Override
	public void addProject(TProject project) {
		projectMgmtDao.addProject(project);
	}


	@Override
	public void modifyProject(TProject project) {
		projectMgmtDao.modifyProject(project);
	}

}
