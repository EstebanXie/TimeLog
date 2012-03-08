package tig.timelog.service.impl;



import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;


import org.apache.commons.lang.xwork.StringUtils;


import tig.timelog.dao.IUserDao;
import tig.timelog.model.TResource;
import tig.timelog.model.TUser;
import tig.timelog.service.IUserService;

public class UserServiceImpl implements IUserService {
  Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
  private IUserDao userDao;

  public void registerUser(TUser user) {
    userDao.addUser(user);
  }

  public IUserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(IUserDao userDao) {
    this.userDao = userDao;
  }

  public TUser checkLogin(TUser user) {
    return userDao.findUser(user.getUserName(), user.getPassword());
  }

  @Override
  public List<TResource> getUserMenu(TUser user) {
    return userDao.getUserMenu(user);
  }

  public List<TResource> getAllResources() {
    return this.userDao.getAllResources();
  }

  public List<TUser> getAllUsers() {
    return this.userDao.getAllUsers();
  }

  public TUser getUser(String userId) {
    return this.userDao.getUser(userId);
  }

  public void addModifyUser(TUser tUser) {
	  TUser tUser1;
    if (StringUtils.isNotEmpty(tUser.getUserId())) {
      // Edit
      // 1. Remove t_org_user_relation
//      this.userDao.removeOrgUserRelation(tUser.getUserId());
      // 2. Remove t_user_role_relation
      this.userDao.removeUserRoleRelation(tUser.getUserId());
      // 3. Update t_user
      this.userDao.updateUser(tUser);
    } else {
      // Add
      // 1. Get next user id
     // tUser.setUserId(this.userDao.getNextUserId());
      // 2. Insert t_user
      this.userDao.addUser(tUser);
      tUser1 = this.userDao.findUser(tUser.getUserName(), tUser.getPassword());
      tUser.setUserId(tUser1.getUserId());
    }

    // 4. Insert t_org_user_relation
//    this.userDao.addOrgUserRelation(tUser);
    // 5. Insert t_user_role_relation
   
    this.userDao.addUserRoleRelation(tUser);
  }

  public void deleteUser(String[] userIdArray) {
    for (String userId : userIdArray) {
      this.userDao.deleteUser(userId);
    }
  }
  public TUser getuserByName(String userName){
	  return this.userDao.getuserByName(userName); 
  }
}
