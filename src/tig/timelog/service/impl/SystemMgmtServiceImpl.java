package tig.timelog.service.impl;



import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import tig.timelog.dao.ISystemMgmtDao;
import tig.timelog.model.QueryCondition;
import tig.timelog.model.TRole;
import tig.timelog.model.TUser;
import tig.timelog.service.ISystemMgmtService;

public class SystemMgmtServiceImpl implements ISystemMgmtService {
  Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
  private ISystemMgmtDao sysMgmtDao;

  public ISystemMgmtDao getSysMgmtDao() {
    return sysMgmtDao;
  }

  public void setSysMgmtDao(ISystemMgmtDao sysMgmtDao) {
    this.sysMgmtDao = sysMgmtDao;
  }

  public List<TRole> listAllRoles() {
    return this.sysMgmtDao.listAllRoles();
  }

  public String addModifyRole(TRole tRole) {
//    String roleId = "";
    if (StringUtils.isNotEmpty(String.valueOf(tRole.getRoleId()))) {
      this.sysMgmtDao.updateRole(tRole);
    } else {
//      roleId = this.sysMgmtDao.getNextRoleId();
//      tRole.setRoleId(roleId);
      this.sysMgmtDao.addRole(tRole);
    }
//    roleId = tRole.getRoleId();
    return tRole.getRoleId();
  }

  public TRole getRole(String roleId) {
    return this.sysMgmtDao.getRole(roleId);
  }

  public void deleteRole(String[] roleIds) {
    for (String roleId : roleIds) {
      this.sysMgmtDao.deleteRole(roleId);
    }
  }

  public void removeResourcesByRole(String roleId) {
    this.sysMgmtDao.removeResourcesByRole(roleId);
  }

  public void addResourcesToRole(String roleId, String[] resourceIdArray) {
    String[] pResourceId = this.sysMgmtDao.getPResourceIds(resourceIdArray);

    for (String pRID : pResourceId) {
      this.sysMgmtDao.addResourceRoleMapping(pRID, roleId);
    }

    for (String rID : resourceIdArray) {
      this.sysMgmtDao.addResourceRoleMapping(rID, roleId);
    }
  }

  public List<String> getResourcesByRole(String roleId) {
    return this.sysMgmtDao.getResourcesByRole(roleId);
  }

	@Override
	public boolean checkUserExistByRole(String[] roleIdArray) {
		boolean result = false;
		for(String roleId : roleIdArray) {
			if(this.sysMgmtDao.checkUserExistByRole(roleId) > 0) {
				result = true;
				break;
			}
		}
		
		return result;
	}

	@Override
	public void updateLoginPassword(TUser user) {
		this.sysMgmtDao.updateLoginPassword(user);
	}
}


