package tig.timelog.dao.impl;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.ISystemMgmtDao;
import tig.timelog.model.QueryCondition;
import tig.timelog.model.TRole;
import tig.timelog.model.TUser;

public class SystemMgmtDaoImpl extends BaseDao implements ISystemMgmtDao {

  @SuppressWarnings("unchecked")
  public List<TRole> listAllRoles() {
    String findRoleSql = "SELECT ROLE_ID, ROLE_NAME, REMARK FROM T_ROLE ORDER BY ROLE_ID";
    List<TRole> roleList = this.getJdbcTemplate().query(findRoleSql, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          TRole role = new TRole();
          if (rs == null) {
            return null;
          } else {
            role.setRoleId(rs.getString("ROLE_ID"));
            role.setRoleName(rs.getString("ROLE_NAME"));
            role.setRemark(rs.getString("REMARK"));
          }

          return role;
        }
      });

    return roleList;
  }

  public void updateRole(final TRole tRole) {
    String updateRoleSql = "UPDATE T_ROLE SET ROLE_NAME = ?, REMARK = ? WHERE ROLE_ID = ?";
    getJdbcTemplate().update(updateRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, tRole.getRoleName());
          ps.setString(2, tRole.getRemark());
          ps.setString(3, tRole.getRoleId());
        }
      });

  }

  public void addRole(final TRole role) {
    String insertRoleSql = "INSERT INTO T_ROLE(ROLE_NAME, REMARK) VALUES(?, ?, ?)";
    getJdbcTemplate().update(insertRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, null);
          ps.setString(2, role.getRoleName());
          ps.setString(3, role.getRemark());
        }
      });
  }

  @SuppressWarnings("unchecked")
  public TRole getRole(String roleId) {
    String findRoleSql = "SELECT ROLE_ID, ROLE_NAME, REMARK FROM T_ROLE WHERE ROLE_ID = ?";
    Object[] params = new Object[] { roleId };
    TRole result = null;
    List<TRole> roleList = this.getJdbcTemplate().query(findRoleSql, params, new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
          TRole role = new TRole();
          if (rs == null) {
            return null;
          }

          role.setRoleId(rs.getString("ROLE_ID"));
          role.setRoleName(rs.getString("ROLE_NAME"));
          role.setRemark(rs.getString("REMARK"));
          return role;
        }
      });
    if (roleList != null && !roleList.isEmpty()) {
      result = roleList.get(0);
    }

    return result;
  }

  public void deleteRole(final String roleId) {
    String deleteRoleSql = "DELETE FROM T_ROLE T WHERE T.ROLE_ID = ?";
    String deleteRoleResourceSql = "DELETE FROM t_role_resource_relation T WHERE T.ROLE_ID = ?";
    getJdbcTemplate().update(deleteRoleResourceSql, new PreparedStatementSetter() {
    	public void setValues(PreparedStatement ps) throws SQLException {
    		ps.setString(1, roleId);
    	}
    });
    
    getJdbcTemplate().update(deleteRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, roleId);
        }
      });
  }

  @SuppressWarnings("unchecked")
  public String getNextRoleId() {
    String roleId = "";
    String getNextRoleIdSql = "select ROLE_ID_SEQ.nextval roleId from dual";
    Map result = super.getJdbcTemplate().queryForMap(getNextRoleIdSql);
    roleId = String.valueOf(result.get("roleId"));

    return roleId;
  }

  public void removeResourcesByRole(final String roleId) {
    String removeResourcesByRoleSql = "delete from t_role_resource_relation where role_id = ?";
    getJdbcTemplate().update(removeResourcesByRoleSql, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, roleId);
        }
      });
  }

  @SuppressWarnings("unchecked")
  public String[] getPResourceIds(String[] resourceIdArray) {
    StringBuffer getPResourceIdsSql =
      new StringBuffer("select distinct(resource_p_id) from t_resource where resource_id in (-9999");
    for (String resourceId : resourceIdArray) {
      getPResourceIdsSql.append(", ").append(resourceId);
    }

    getPResourceIdsSql.append(") order by resource_p_id");

    List result = super.getJdbcTemplate().queryForList(getPResourceIdsSql.toString(), String.class);

    String[] reV = new String[result.size()];
    for (int i = 0; i < result.size(); i++) {
      reV[i] = (String)result.get(i);
    }

    return reV;
  }

  public void addResourceRoleMapping(final String rID, final String roleId) {
    String addRoleResource = "insert into t_role_resource_relation(role_id, resource_id) values(?, ?)";
    getJdbcTemplate().update(addRoleResource, new PreparedStatementSetter() {
        public void setValues(PreparedStatement ps) throws SQLException {
          ps.setString(1, roleId);
          ps.setString(2, rID);
        }
      });
  }

  @SuppressWarnings("unchecked")
  public List<String> getResourcesByRole(String roleId) {
    String sql = "select distinct(resource_id) from t_role_resource_relation where role_id = " + roleId;
    List<String> returnV = new ArrayList<String>();
    List result = super.getJdbcTemplate().queryForList(sql, String.class);
    for (Object s : result) {
      returnV.add((String)s);
    }

    return returnV;
  }

  
  @SuppressWarnings("unchecked")
  @Override
  public int checkUserExistByRole(String roleId) {
    String sql = "select count(relation_id) amount\n" +
      "  from t_user_role_relation\n" +
      " where role_id = ?";
    Object[] params = new Object[] { roleId };
    Map result = super.getJdbcTemplate().queryForMap(sql, params);

    return ((BigDecimal)result.get("amount")).intValue();
  }

	@Override
	public void updateLoginPassword(final TUser user) {
		String sql="UPDATE t_user t set t.password=? WHERE t.user_id=? ";
		
	    getJdbcTemplate().update(sql, new PreparedStatementSetter() {
	        public void setValues(PreparedStatement ps) throws SQLException {
	          ps.setString(1, user.getPassword());
	          ps.setString(2, user.getUserId());
	        }
	      });
	}

}


