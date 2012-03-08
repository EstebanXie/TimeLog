package tig.timelog.service;



import java.util.List;

import tig.timelog.model.TResource;
import tig.timelog.model.TUser;


public interface IUserService {
	public void registerUser(TUser user);
	public TUser checkLogin(TUser user);
	public List<TResource> getUserMenu(TUser user);

  public List<TResource> getAllResources();

  public List<TUser> getAllUsers();

  public TUser getUser(String userId);

  public void addModifyUser(TUser tUser);

  public void deleteUser(String[] userIdArray);

  public TUser getuserByName(String userName);
}
