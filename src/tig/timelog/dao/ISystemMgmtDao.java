package tig.timelog.dao;


import java.util.List;

import tig.timelog.model.TRole;
import tig.timelog.model.TUser;

public interface ISystemMgmtDao extends IBaseDao {

  /**
   * Get All Roles.
   *
   * @return All Roles.
   */
  public List<TRole> listAllRoles();

  public void updateRole(TRole tRole);

  public void addRole(TRole tRole);

  public TRole getRole(String roleId);

  public void deleteRole(String roleId);

  public String getNextRoleId();

  public void removeResourcesByRole(String roleId);

  public String[] getPResourceIds(String[] resourceIdArray);

  public void addResourceRoleMapping(String rID, String roleId);

  public List<String> getResourcesByRole(String roleId);

  public int checkUserExistByRole(String roleId);

  public void updateLoginPassword(TUser user);

}


