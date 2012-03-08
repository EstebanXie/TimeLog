package tig.timelog.action;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import tig.timelog.constant.ContextConstants;
import tig.timelog.model.Paging;
import tig.timelog.model.QueryCondition;

import tig.timelog.model.TResource;
import tig.timelog.model.TRole;
import tig.timelog.model.TUser;
import tig.timelog.service.ICodeTableService;
import tig.timelog.service.IDataMgmtService;
import tig.timelog.service.ISystemMgmtService;
import tig.timelog.service.IUserService;
import tig.timelog.util.DataStoreLocal;
import tig.timelog.util.ExcelHandle;
import tig.timelog.util.PagingUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SystemMgmtAction extends ActionSupport  implements SessionAware, ServletRequestAware,
ServletResponseAware {
  protected final Logger log = Logger.getLogger(SystemMgmtAction.class);

  private IUserService userService;
  private ISystemMgmtService sysMgmtSerivce;
  private IDataMgmtService dataMgmtService;
  private ICodeTableService codeTableService;

  private Map<String, Object> session;


  private List<TRole> roles;
  private String[] roleIdArray;
  private TRole role;
  private List<TResource> resources;
  private String operation;
  private String[] resourceIdArray;
  private List<String> hisResources;

  private List<TUser> users;
  private String[] userIdArray;
  private TUser user;
  private TUser editUser;
  private String oldPwd;
  private String newPwd1;
  private String newPwd2;

  private String[] agtReltAmtIdArray;

  private QueryCondition queryCondition;
  private String[] pluralisticAgencyIdArray;
  private String[] palnAgtInsurCates;

  private String agtRltnTime;
  
  private List<String> yearList;
  private List<String> monthList;
  private String condRptYear;
  private String condRptMonth;
  private String insurerOrgId;
  
  private String agencyId;
  private String insuranceCate;
  private String allRelationInsurCateCodesString = "";
  private String addInsuranceCateAndRelationString;
  
  Paging paging  = new Paging();

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  
  /*Start of user part*/

  public String listAllUsers() {
    this.users = this.userService.getAllUsers();
    
    if(users!=null&&users.size()>0){
    PagingUtil.getPaging(this.paging, this.users.size()); //鏀筶ist鍚嶇О 鍜�绫诲悕
    int iStartLimit = PagingUtil.getStartLimit(this.paging);
    int end = PagingUtil.getLastLimit(iStartLimit, this.paging, this.users.size());
    List<TUser> pagedList = new ArrayList<TUser>();
    for (int i = iStartLimit; i < end; i++) {
      pagedList.add(this.users.get(i));
    }
   
    this.users = pagedList;
    
    }
    return SUCCESS;
  }

  public String initUser() {
    if ("edit".equalsIgnoreCase(this.operation)) {
      this.editUser = this.userService.getUser(this.userIdArray[0]);
    }
    this.roles = this.sysMgmtSerivce.listAllRoles();
    return SUCCESS;
  }

  public String addModifyUser() {
	  if(StringUtils.isEmpty(editUser.getUserId()) && userService.getuserByName(editUser.getUserName()) != null){
		  this.addActionMessage(super.getText("useralreadyexisted.msg"));
		  return SUCCESS;
	  }
    this.userService.addModifyUser(this.editUser);
    this.addActionMessage(super.getText("userOperationSuccess.msg"));
    return SUCCESS;
  }

  public String deleteUser() {
    if (this.userIdArray != null && this.userIdArray.length > 0) {
      this.userService.deleteUser(this.userIdArray);
    }

    this.addActionMessage(super.getText("userDeleteSuccess.msg"));
    return SUCCESS;
  }
  /*End of user part  */


  /*Start of role part*/

  public String listAllRoles() {
    this.roles = this.sysMgmtSerivce.listAllRoles();
    
    if(roles!=null&&roles.size()>0){
    PagingUtil.getPaging(this.paging, this.roles.size()); 
    int iStartLimit = PagingUtil.getStartLimit(this.paging);
    int end = PagingUtil.getLastLimit(iStartLimit, this.paging, this.roles.size());
    List<TRole> pagedList = new ArrayList<TRole>();
    for (int i = iStartLimit; i < end; i++) {
      pagedList.add(this.roles.get(i));
    }

    this.roles = pagedList;
    
    }
    return "success";
  }

  public String initRole() {
    if ("edit".equalsIgnoreCase(this.operation)) {
      // Edit
      this.role = this.sysMgmtSerivce.getRole(this.roleIdArray[0]);
      this.hisResources = this.sysMgmtSerivce.getResourcesByRole(this.roleIdArray[0]);
    }

    this.resources = userService.getAllResources();
    return "success";
  }

  public String addModifyRole() {
    String roleId = this.sysMgmtSerivce.addModifyRole(this.role);
    // 1: remove all his resources
    this.sysMgmtSerivce.removeResourcesByRole(roleId);
    // 2: add new resources
    if (this.resourceIdArray != null && this.resourceIdArray.length > 0) {
      this.sysMgmtSerivce.addResourcesToRole(roleId, this.resourceIdArray);
    }

    this.addActionMessage(super.getText("roleOperationSuccess.msg"));

    return "success";
  }

  public String deleteRole() {
	if(this.sysMgmtSerivce.checkUserExistByRole(this.roleIdArray)) {
		this.addActionMessage(super.getText("roleDeleteFailed.msg"));
	} else {
	    this.sysMgmtSerivce.deleteRole(this.roleIdArray);
	    this.addActionMessage(super.getText("roleDeleteSuccess.msg"));
	}
	this.roles = this.sysMgmtSerivce.listAllRoles();
	 if(roles!=null&&roles.size()>0){
		    PagingUtil.getPaging(this.paging, this.roles.size()); 
		    int iStartLimit = PagingUtil.getStartLimit(this.paging);
		    int end = PagingUtil.getLastLimit(iStartLimit, this.paging, this.roles.size());
		    List<TRole> pagedList = new ArrayList<TRole>();
		    for (int i = iStartLimit; i < end; i++) {
		      pagedList.add(this.roles.get(i));
		    }

		    this.roles = pagedList;
		    
		    }
    return "success";
  }
  /*End of role part  */

  @SuppressWarnings("unchecked")
  public String login() throws Exception {
    TUser resultUser = userService.checkLogin(user);
    if (resultUser != null) {
      if (session.get(ContextConstants.CURRENT_LOGINED_USER_CONTEXT) == null ||
        session.get(ContextConstants.CURRENT_LOGINED_USER_MENU_CONTEXT) == null) {
        this.user = this.userService.getUser(resultUser.getUserId());
        session.put(ContextConstants.CURRENT_LOGINED_USER_CONTEXT, user);
        List<TResource> menuList = userService.getUserMenu(user);
        if (menuList == null || menuList.isEmpty()) {
          session.clear();
          this.addFieldError("loginError", super.getText("loginFail.nopriviliage.msg"));
          return "loginFail";
        }

        session.put(ContextConstants.CURRENT_LOGINED_USER_MENU_CONTEXT, menuList);

        Map map = new HashMap();
        map.put(ContextConstants.CURRENT_LOGINED_USER_CONTEXT, this.user);
        DataStoreLocal.addData(map);
      }

      return "success";
    } else {
      this.addFieldError("loginError", super.getText("loginError.msg"));
      return "loginFail";
    }
  }

  public String logout() throws Exception {
    session.remove(ContextConstants.CURRENT_LOGINED_USER_CONTEXT);
    session.remove(ContextConstants.CURRENT_LOGINED_USER_MENU_CONTEXT);
    session.clear();
    return "success";
  }

 
  public String initModifyPwd() throws Exception{
	  
	  return SUCCESS;
  }
  
  public String modifyLoginPwd() throws Exception{
	  this.user= (TUser) session.get(ContextConstants.CURRENT_LOGINED_USER_CONTEXT);
	  if(!this.oldPwd.equalsIgnoreCase(user.getPassword())){
		  this.addFieldError("errorMsg", this.getText("passwordWrong.msg"));
		  return SUCCESS;
	  }else if(this.oldPwd.equalsIgnoreCase(newPwd1)){
		  this.addFieldError("errorMsg", this.getText("passwordDuplicate.msg"));
		  return SUCCESS;
	  }
	  TUser newUser=new TUser();
	  newUser.setUserId(this.user.getUserId());
	  newUser.setPassword(newPwd1);
	  this.sysMgmtSerivce.updateLoginPassword(newUser);
	  this.user.setPassword(newPwd1);
	  session.put(ContextConstants.CURRENT_LOGINED_USER_CONTEXT, this.user);
	  this.addActionMessage(this.getText("modifyPasswordSuccess.msg"));
	  return SUCCESS;
  }

  public IUserService getUserService() {
    return userService;
  }

  public void setUserService(IUserService userService) {
    this.userService = userService;
  }

  public TUser getUser() {
    return user;
  }

  public void setUser(TUser user) {
    this.user = user;
  }

  public ISystemMgmtService getSysMgmtSerivce() {
    return sysMgmtSerivce;
  }

  public void setSysMgmtSerivce(ISystemMgmtService sysMgmtSerivce) {
    this.sysMgmtSerivce = sysMgmtSerivce;
  }

  public void setRoles(List<TRole> roles) {
    this.roles = roles;
  }

  public List<TRole> getRoles() {
    return roles;
  }

  public void setRoleIdArray(String[] roleIdArray) {
    this.roleIdArray = roleIdArray;
  }

  public String[] getRoleIdArray() {
    return roleIdArray;
  }

  public void setRole(TRole role) {
    this.role = role;
  }

  public TRole getRole() {
    return role;
  }

  public void setResources(List<TResource> resources) {
    this.resources = resources;
  }

  public List<TResource> getResources() {
    return resources;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return operation;
  }

  public void setResourceIdArray(String[] resourceIdArray) {
    this.resourceIdArray = resourceIdArray;
  }

  public String[] getResourceIdArray() {
    return resourceIdArray;
  }

  public void setHisResources(List<String> hisResources) {
    this.hisResources = hisResources;
  }

  public List<String> getHisResources() {
    return hisResources;
  }

  public void setUsers(List<TUser> users) {
    this.users = users;
  }

  public List<TUser> getUsers() {
    return users;
  }

  public void setUserIdArray(String[] userIdArray) {
    this.userIdArray = userIdArray;
  }

  public String[] getUserIdArray() {
    return userIdArray;
  }

  public void setEditUser(TUser editUser) {
    this.editUser = editUser;
  }

  public TUser getEditUser() {
    return editUser;
  }

  
  public void setQueryCondition(QueryCondition queryCondition) {
    this.queryCondition = queryCondition;
  }

  public QueryCondition getQueryCondition() {
    return queryCondition;
  }

  
  
  public ICodeTableService getCodeTableService() {
	return codeTableService;
  }

  public void setCodeTableService(ICodeTableService codeTableService) {
	this.codeTableService = codeTableService;
  }

  public IDataMgmtService getDataMgmtService() {
	return dataMgmtService;
  }

  public void setDataMgmtService(IDataMgmtService dataMgmtService) {
	this.dataMgmtService = dataMgmtService;
  }

  public List<String> getYearList() {
	return yearList;
  }

  public void setYearList(List<String> yearList) {
	this.yearList = yearList;
  }

  public List<String> getMonthList() {
	return monthList;
  }

  public void setMonthList(List<String> monthList) {
	this.monthList = monthList;
  }

/**
   * Testing code. Writing excel file to client.
   */
  @SuppressWarnings("unused")
  private HttpServletRequest request;
  private HttpServletResponse response;

  public void downLoadExcel() throws Exception {
    response.reset();
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + new String("Excel.xls".getBytes(), "iso-8859-1"));
    OutputStream os = response.getOutputStream();
    ExcelHandle.writeExcel(os);
    os.flush();
    os.close();
  }

	public Paging getPaging() {
		return paging;
	}
	
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd1() {
		return newPwd1;
	}

	public void setNewPwd1(String newPwd1) {
		this.newPwd1 = newPwd1;
	}

	public String getNewPwd2() {
		return newPwd2;
	}

	public void setNewPwd2(String newPwd2) {
		this.newPwd2 = newPwd2;
	}
	 @Override
	  public void setServletRequest(HttpServletRequest arg0) {
	    this.request = arg0;
	  }
	
	  @Override
	  public void setServletResponse(HttpServletResponse arg0) {
	    this.response = arg0;
	  }
}
