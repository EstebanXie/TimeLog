package tig.timelog.dao;

import java.util.List;

import tig.timelog.model.TResource;
import tig.timelog.model.TUser;


public interface IUserDao extends IBaseDao{
  public void addUser(TUser user);
  public TUser findUser(String userName, String password);
  public List<TResource> getUserMenu(TUser user);

  public List<TResource> getAllResources();

  public List<TUser> getAllUsers();

  public TUser getUser(String userId);

  public String getNextUserId();

  public void removeUserRoleRelation(String string);

  public void updateUser(TUser tUser);

  public void addUserRoleRelation(TUser tUser);

  public void deleteUser(String userId);
  
  public TUser getuserByName(String userName);
}
