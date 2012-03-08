package tig.timelog.service;


import java.util.List;

import tig.timelog.model.TRole;
import tig.timelog.model.TUser;


public interface ISystemMgmtService {

  public List<TRole> listAllRoles();

  public String addModifyRole(TRole tRole);

  public TRole getRole(String roleId);

  public void deleteRole(String[] roleIds);

  public void removeResourcesByRole(String roleId);

  public void addResourcesToRole(String roleId, String[] resourceIdArray);

  public List<String> getResourcesByRole(String roleId);

  public boolean checkUserExistByRole(String[] roleIdArray);

  public void updateLoginPassword(TUser user);

}


