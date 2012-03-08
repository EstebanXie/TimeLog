package tig.timelog.service;


import java.util.List;

import tig.timelog.model.TProject;


public interface IProjectMgmtService {

  public List<TProject> listAllProjects(TProject project);
  
  public void addProject(TProject project);
  
  public void modifyProject(TProject project);

}


