package tig.timelog.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.IEZClientDao;
import tig.timelog.model.TEZClient;
import tig.timelog.model.TRole;
import tig.timelog.model.TUser;

public class EZClientDaoImpl extends BaseDao implements IEZClientDao{

	@Override
	public void addEZClient(final TEZClient client) {
	    String addSql = "INSERT INTO t_ez_client (CLIENT_NAME, CLIENT_CODE, STATUS, TRANSFER_DATE, NOTES) VALUES (?, ?, ?, ?, ?)";
	    
	    getJdbcTemplate().update(addSql, new PreparedStatementSetter() {
	        public void setValues(PreparedStatement ps) throws SQLException {
	          ps.setString(1, client.getClientName());
	          ps.setString(2, client.getClientCode());
	          ps.setString(3, client.getStatus());
	          ps.setDate(4, client.getTransferDate());
	          ps.setString(5, client.getNotes());
	        }
	      });
	  }
		

	@Override
	public void deleteEZClient(final Integer id) {
	    String updateSql = "update t_ez_client set del_state = 1 where client_id = ?";
	    
	    int result = getJdbcTemplate().update(updateSql, new PreparedStatementSetter() {
	    	public void setValues(PreparedStatement ps) throws SQLException {
	    		ps.setInt(1, id);
	    	}
	    });
	}

	@Override
	public List<TEZClient> getAllTEZClient(Integer clientId) {
	    String findSql = "select client_id,client_name,client_code,status,transfer_date,notes" +
	      " from t_ez_client where del_state = 0 ";
	    if(clientId != 0)
	    	findSql += " and client_id = " + clientId;
	    findSql += " order by client_name";
	    List<TEZClient> clientList = this.getJdbcTemplate().query(findSql, new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	          if (rs == null) {
	            return null;
	          }
	          TEZClient client = new TEZClient();
	          client.setClientId(rs.getInt("CLIENT_ID"));
	          client.setClientName(rs.getString("CLIENT_NAME"));
	          client.setClientCode(rs.getString("CLIENT_CODE"));
	          client.setStatus(rs.getString("STATUS"));
	          client.setTransferDate(rs.getDate("TRANSFER_DATE"));
	          client.setNotes(rs.getString("NOTES"));
	          return client;
	        }
	      });

	    return clientList;
	}

	@Override
	public void updateEZClient(final TEZClient client) {
	    String updateSql = "update t_ez_client set client_name = ?, client_code = ?, status = ?, transfer_date = ?, notes = ?";
	    
	    updateSql += " where client_id = ?";
	    
		int result = getJdbcTemplate().update(updateSql, new PreparedStatementSetter() {
	        public void setValues(PreparedStatement ps) throws SQLException {
	          ps.setString(1,client.getClientName());
	          ps.setString(2, client.getClientCode());
	          ps.setString(3, client.getStatus());
	          ps.setDate(4, client.getTransferDate());
	          ps.setString(5, client.getNotes());
	          ps.setInt(6, client.getClientId());
	        }
	      });
	}


	@Override
	public TEZClient getClientByName(String clientName) {
	      String findUserSql = "select CLIENT_ID,CLIENT_NAME, CLIENT_CODE, STATUS, TRANSFER_DATE, NOTES from T_EZ_CLIENT where del_state = 0 and CLIENT_NAME = '"+clientName+"'";
	      List<TEZClient> clientList = this.getJdbcTemplate().query(findUserSql, new RowMapper() {
	          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	            if (rs == null) {
	              return null;
	            }
		          TEZClient client = new TEZClient();
		          client.setClientId(rs.getInt("CLIENT_ID"));
		          client.setClientName(rs.getString("CLIENT_NAME"));
		          client.setClientCode(rs.getString("CLIENT_CODE"));
		          client.setStatus(rs.getString("STATUS"));
		          client.setTransferDate(rs.getDate("TRANSFER_DATE"));
		          client.setNotes(rs.getString("NOTES"));
		          return client;
	          }
	        });
	      if (clientList != null && !clientList.isEmpty()) {
	        return clientList.get(0);
	      }
	      return null;
	}
}
