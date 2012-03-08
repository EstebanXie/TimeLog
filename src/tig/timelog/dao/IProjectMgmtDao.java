package tig.timelog.dao;


import java.util.List;

import tig.timelog.model.TProject;

public interface IProjectMgmtDao extends IBaseDao {

  public List<TProject> listAllProjects(TProject project);
  
  public void addProject(TProject project);

  public void modifyProject(TProject project);

}


