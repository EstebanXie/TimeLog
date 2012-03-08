package tig.timelog.dao.impl;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.IUserDao;
import tig.timelog.model.TResource;
import tig.timelog.model.TRole;
import tig.timelog.model.TUser;

public class UserDaoImpl extends BaseDao implements IUserDao {
  Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

  public void addUser(final TUser user) {
    String sql = "INSERT INTO t_user (USER_ID,USER_NAME, PASSWORD, REAL_NAME, STATE, INSERT_DATE, UPDATE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
    getJdbcTemplate().update(sql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
        	long currentTime = System.currentTimeMillis();
          ps.setString(1, null);
          ps.setString(2, user.getUserName());
          ps.setString(3, user.getPassword());
          ps.setString(4, user.getRealName());
          ps.setString(5, "1");
          ps.setDate(6, new Date(currentTime));
          ps.setDate(7, new Date(currentTime));
        }
      });
  }

  @SuppressWarnings("unchecked")
  public TUser findUser(String userName, String password) {
    String findUserSql =
      "SELECT USER_ID, USER_NAME, PASSWORD, REAL_NAME, PHONE_NUMBER, STATE from t_user where user_Name = ? and password = ? and state = 1";
    Object[] params = new Object[] { userName, password };
    TUser resultUser = null;
    List userList = this.getJdbcTemplate().query(findUserSql, params, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          TUser u = new TUser();
          if (rs == null)
            return null;
          u.setUserId(rs.getString("USER_ID"));
          u.setUserName(rs.getString("USER_NAME"));
          u.setRealName(rs.getString("REAL_NAME"));
          u.setPhoneNumber(rs.getString("PHONE_NUMBER"));
          u.setPassword(rs.getString("PASSWORD"));
          
          return u;
        }
      });
    if (userList != null && userList.size() > 0)
      resultUser = (TUser)userList.get(0);
    return resultUser;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<TResource> getUserMenu(TUser user) {
    String findMenuSql = "select tr.RESOURCE_ID, tr.RESOURCE_P_ID, tr.RESOURCE_NAME, tr.RESOURCE_URL, tr.RESOURCE_SEQ\n" +
      "  from t_user tu\n" +
      "  left join t_user_role_relation turr\n" +
      "    on tu.user_id = turr.user_id\n" +
      "  left join t_role_resource_relation trrr\n" +
      "    on turr.role_id = trrr.role_id\n" +
      "  left join t_resource tr\n" +
      "    on trrr.resource_id = tr.resource_id\n" +
      " where tu.user_id = ?\n" +
      "   and tr.resource_p_id = 0\n" +
      " order by tr.resource_seq";
    Object[] params = new Object[] { user.getUserId() };
    List<TResource> mainMenuList = this.getJdbcTemplate().query(findMenuSql, params, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          TResource menu = new TResource();
          if (rs == null)
            return null;
          else {
            menu.setResourceId(rs.getString("RESOURCE_ID"));
            menu.setResourcePId(rs.getString("RESOURCE_P_ID"));
            menu.setResourceName(rs.getString("RESOURCE_NAME"));
            menu.setResourceUrl(rs.getString("RESOURCE_URL"));
            menu.setResourceSeq(rs.getString("RESOURCE_SEQ"));
          }
          return menu;
        }
      });

    String findChildMenuSql = "select RESOURCE_ID, RESOURCE_P_ID, RESOURCE_NAME, RESOURCE_URL, RESOURCE_SEQ\n" +
      "  from (select tr.RESOURCE_ID,\n" +
      "               tr.RESOURCE_P_ID,\n" +
      "               tr.RESOURCE_NAME,\n" +
      "               tr.RESOURCE_URL,\n" +
      "               tr.RESOURCE_SEQ\n" +
      "          from t_user tu\n" +
      "          left join t_user_role_relation turr\n" +
      "            on tu.user_id = turr.user_id\n" +
      "          left join t_role_resource_relation trrr\n" +
      "            on turr.role_id = trrr.role_id\n" +
      "          left join t_resource tr\n" +
      "            on trrr.resource_id = tr.resource_id\n" +
      "         where tu.user_id = ?\n" +
      "         order by trrr.resource_id) t\n" +
      " where t.resource_p_id = ? order by resource_seq";
    List<TResource> childMenuList = null;
    if (mainMenuList != null && mainMenuList.size() > 0) {
      for (int index = 0; index < mainMenuList.size(); index++) {
        TResource mainMenu = mainMenuList.get(index);
        String mainMenuId = mainMenu.getResourceId();
        childMenuList =
            this.getJdbcTemplate().query(findChildMenuSql, new Object[] { user.getUserId(), mainMenuId }, new RowMapper() {
              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TResource menu = new TResource();
                if (rs == null)
                  return null;
                else {
                  menu.setResourceId(rs.getString("RESOURCE_ID"));
                  menu.setResourcePId(rs.getString("RESOURCE_P_ID"));
                  menu.setResourceName(rs.getString("RESOURCE_NAME"));
                  menu.setResourceUrl(rs.getString("RESOURCE_URL"));
                  menu.setResourceSeq(rs.getString("RESOURCE_SEQ"));
                }
                return menu;
              }
            });
        mainMenu.setChildMenuList(childMenuList);
      }
    }

    return mainMenuList;
  }

  @SuppressWarnings("unchecked")
public List<TResource> getAllResources() {
    String findMenuSql =
      "SELECT RESOURCE_ID, RESOURCE_P_ID, RESOURCE_NAME, RESOURCE_URL, RESOURCE_SEQ FROM T_RESOURCE WHERE RESOURCE_P_ID = 0 ORDER BY resource_seq";
    List<TResource> mainMenuList = this.getJdbcTemplate().query(findMenuSql, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          TResource menu = new TResource();
          if (rs == null)
            return null;
          else {
            menu.setResourceId(rs.getString("RESOURCE_ID"));
            menu.setResourcePId(rs.getString("RESOURCE_P_ID"));
            menu.setResourceName(rs.getString("RESOURCE_NAME"));
            menu.setResourceUrl(rs.getString("RESOURCE_URL"));
            menu.setResourceSeq(rs.getString("RESOURCE_SEQ"));
          }
          return menu;
        }
      });
    String findChildMenuSql =
      "SELECT RESOURCE_ID, RESOURCE_P_ID, RESOURCE_NAME, RESOURCE_URL, RESOURCE_SEQ FROM T_RESOURCE WHERE RESOURCE_P_ID = ? ORDER BY resource_seq";
    List<TResource> childMenuList = null;
    if (mainMenuList != null && mainMenuList.size() > 0) {
      for (int index = 0; index < mainMenuList.size(); index++) {
        TResource mainMenu = mainMenuList.get(index);
        String mainMenuId = mainMenu.getResourceId();
        childMenuList = this.getJdbcTemplate().query(findChildMenuSql, new Object[] { mainMenuId }, new RowMapper() {
              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TResource menu = new TResource();
                if (rs == null)
                  return null;
                else {
                  menu.setResourceId(rs.getString("RESOURCE_ID"));
                  menu.setResourcePId(rs.getString("RESOURCE_P_ID"));
                  menu.setResourceName(rs.getString("RESOURCE_NAME"));
                  menu.setResourceUrl(rs.getString("RESOURCE_URL"));
                  menu.setResourceSeq(rs.getString("RESOURCE_SEQ"));
                }
                return menu;
              }
            });
        mainMenu.setChildMenuList(childMenuList);
      }
    }

    return mainMenuList;
  }

  @SuppressWarnings("unchecked")
public List<TUser> getAllUsers() {
    String findUserSql = "select tu.USER_ID,\n" +
      "       tu.USER_NAME,\n" +
      "       tu.PASSWORD,\n" +
      "       tu.REAL_NAME,\n" +
      "       tu.PHONE_NUMBER,\n" +
      "       tu.STATE,\n" +
      "       tr.role_id,\n" +
      "       tr.role_name,\n" +
      "       tr.remark\n" +
      "  from t_user tu\n" +
      "  left join t_user_role_relation turl\n" +
      "    on tu.user_id = turl.user_id\n" +
      "  left join t_role tr\n" +
      "    on turl.role_id = tr.role_id\n" +
      " where tu.state = 1 order by tu.USER_ID";
    List<TUser> userList = this.getJdbcTemplate().query(findUserSql, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          if (rs == null) {
            return null;
          }

          TUser u = new TUser();
          TRole role = new TRole();
          u.setUserId(rs.getString("USER_ID"));
          u.setUserName(rs.getString("USER_NAME"));
          u.setRealName(rs.getString("REAL_NAME"));
          u.setPhoneNumber(rs.getString("PHONE_NUMBER"));
          u.setPassword(rs.getString("PASSWORD"));

          role.setRoleId(rs.getString("role_id"));
          role.setRoleName(rs.getString("role_name"));
          role.setRemark(rs.getString("remark"));
          u.setRole(role);

          return u;
        }
      });

    return userList;
  }

  @SuppressWarnings("unchecked")
public TUser getUser(String userId) {
    String findUserSql = "select tu.USER_ID,\n" +
      "       tu.USER_NAME,\n" +
      "       tu.PASSWORD,\n" +
      "       tu.REAL_NAME,\n" +
      "       tu.PHONE_NUMBER,\n" +
      "       tu.STATE,\n" +
      "       tr.role_id,\n" +
      "       tr.role_name,\n" +
      "       tr.remark \n" +
      "  from t_user tu\n" +
      "  left join t_user_role_relation turl\n" +
      "    on tu.user_id = turl.user_id\n" +
      "  left join t_role tr\n" +
      "    on turl.role_id = tr.role_id\n" +
      " where tu.state = 1 and tu.user_id = ?";
    Object[] params = new Object[] { userId };
    List<TUser> userList = this.getJdbcTemplate().query(findUserSql, params, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          if (rs == null) {
            return null;
          }

          TUser u = new TUser();
          TRole role = new TRole();
          u.setUserId(rs.getString("USER_ID"));
          u.setUserName(rs.getString("USER_NAME"));
          u.setRealName(rs.getString("REAL_NAME"));
          u.setPhoneNumber(rs.getString("PHONE_NUMBER"));
          u.setPassword(rs.getString("PASSWORD"));

          role.setRoleId(rs.getString("role_id"));
          role.setRoleName(rs.getString("role_name"));
          role.setRemark(rs.getString("remark"));
          u.setRole(role);

          return u;
        }
      });
    if (userList != null && !userList.isEmpty()) {
      return userList.get(0);
    }

    return null;
  }

  @SuppressWarnings("unchecked")
public String getNextUserId() {
    String userId = "";
    String getNextRoleIdSql = "select USER_ID_SEQ.nextval userId from dual";
    Map result = super.getJdbcTemplate().queryForMap(getNextRoleIdSql);
    userId = String.valueOf(result.get("userId"));

    return userId;
  }

  public void removeOrgUserRelation(final String userId) {
    String deleteRoleSql = "DELETE FROM t_org_user_relation T WHERE T.USER_ID = ?";
    getJdbcTemplate().update(deleteRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, userId);
        }
      });
  }

  public void removeUserRoleRelation(final String userId) {
    String deleteRoleSql = "DELETE FROM t_user_role_relation WHERE USER_ID = ?";
    getJdbcTemplate().update(deleteRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, userId);
        }
      });
  }

  public void updateUser(final TUser tUser) {
    String updateUserSql = "update t_user set user_name = ?, real_name = ? ";
    if (StringUtils.isNotEmpty(tUser.getPassword())) {
      updateUserSql += ", password = ? ";
    }

    updateUserSql += "where user_id = ?";

    @SuppressWarnings("unused")
	int result = getJdbcTemplate().update(updateUserSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, tUser.getUserName());
          ps.setString(2, tUser.getRealName());
          if (StringUtils.isNotEmpty(tUser.getPassword())) {
            ps.setString(3, tUser.getPassword());
            ps.setString(4, tUser.getUserId());
          } else {
            ps.setString(3, tUser.getUserId());
          }
        }
      });
  }

  public void addUserRoleRelation(final TUser user) {
    String insertOrgUserRelation = "insert into t_user_role_relation (user_id, role_id) values(?, ?)";
    getJdbcTemplate().update(insertOrgUserRelation, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, user.getUserId());
          ps.setString(2, user.getRole().getRoleId());
        }
      });
  }

  public void deleteUser(final String userId) {
    String updateUserSql = "delete from t_user where user_id = ?";
    String updateUserRoleSql = "delete from t_user_role_relation where user_id = ?";
   

    @SuppressWarnings("unused")
    
    int result = getJdbcTemplate().update(updateUserRoleSql, new PreparedStatementSetter() {
    	public void setValues(PreparedStatement ps) throws SQLException {
    		ps.setString(1, userId);
    	}
    });
    
  
	result = getJdbcTemplate().update(updateUserSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, userId);
        }
      });
  }
  
  @SuppressWarnings("unchecked")
  public TUser getuserByName(String userName){
      String findUserSql = "select tu.USER_ID, tu.USER_NAME,  tu.PASSWORD, tu.REAL_NAME, tu.PHONE_NUMBER, tu.STATE from t_user tu where tu.user_name = '"+userName+"'";
      List<TUser> userList = this.getJdbcTemplate().query(findUserSql, new RowMapper() {
          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
              return null;
            }
            TUser u = new TUser();
            u.setUserId(rs.getString("USER_ID"));
            u.setUserName(rs.getString("USER_NAME"));
            u.setRealName(rs.getString("REAL_NAME"));
            u.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setState(rs.getInt("STATE"));

            return u;
          }
        });
      if (userList != null && !userList.isEmpty()) {
        return userList.get(0);
      }

      return null;
  }
}
