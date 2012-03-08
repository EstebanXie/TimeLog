package tig.timelog.dao.impl;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.IDataMgmtDao;
import tig.timelog.model.TTimeLogEntry;


public class DataMgmtDaoImpl extends BaseDao implements IDataMgmtDao {

	
	@Override
	public int deleteTimeLogEntiesByDateAndUserID(final Date date, final String userID) {
		StringBuffer strSql = new StringBuffer("delete from t_timelog_entry where user_id = ? and work_time = ? ");
		
	    int result = getJdbcTemplate().update(strSql.toString(), new PreparedStatementSetter() {
	    	public void setValues(PreparedStatement ps) throws SQLException {
	    		ps.setString(1, userID);
	    		ps.setDate(2, date);
	    	}
	    });
		
		return result;
		
	}

	@Override
	public List<TTimeLogEntry> getTimeLogEntriesByDateAndUserID(final Date date, final String userID) {
	    String findSql =
	        "SELECT TIMELOG_ID, USER_ID, PROJECT_ID, PROJECT_TYPE_ID, WORK_TYPE_ID" +
	        ", TASK_ID, TICKET_NO, WORK_HOUR, IS_OT, NOTES, WORK_TIME  from " +
	        " t_timelog_entry where USER_ID = ? and WORK_TIME = ? ";
	      Object[] params = new Object[] { userID, date };
	    
	      List timeLogEntries = this.getJdbcTemplate().query(findSql, params, new RowMapper() {
	          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	  List<TTimeLogEntry> timeLogEntries = null;
	        	  TTimeLogEntry entry = null;
	            if (rs == null){
	            	 timeLogEntries = new ArrayList<TTimeLogEntry>();
	            	 return timeLogEntries;
	            }else{
	            	entry = new TTimeLogEntry();
	            	
	            	entry.setTimeLogID(rs.getString("TIMELOG_ID"));
	            	entry.setUserId(rs.getString("USER_ID"));
	            	entry.setProjectId(rs.getString("PROJECT_ID"));
	            	
	            	entry.setProjectTypeId(rs.getString("PROJECT_TYPE_ID"));
	            	entry.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
	            	entry.setTaskId(rs.getString("TASK_ID"));
	            	
	            	entry.setTicketNumber(rs.getString("TICKET_NO"));
	            	entry.setWorkHour(rs.getFloat("WORK_HOUR"));
	            	entry.setOverTime(rs.getString("IS_OT"));
	            	
	            	entry.setNotes(rs.getString("NOTES"));
	            	entry.setWorkTime(rs.getDate("WORK_TIME"));
	            }
	            return entry;
	          }
	         
	});
	      
	      return timeLogEntries;
	}

	@Override
	public void saveTimeLogEnties(final TTimeLogEntry entry) {
	    String insertOrgUserRelation = "insert into t_timelog_entry (TIMELOG_ID, USER_ID, PROJECT_ID, PROJECT_TYPE_ID, WORK_TYPE_ID" +
	        ", TASK_ID, TICKET_NO, WORK_HOUR, IS_OT, NOTES, WORK_TIME) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? )";
	   
	    	  getJdbcTemplate().update(insertOrgUserRelation, new PreparedStatementSetter() {
		  	        public void setValues(PreparedStatement ps) throws SQLException {
		  	          ps.setString(1, null);
		  	          ps.setString(2, entry.getUserId());
		  	          ps.setString(3, entry.getProjectId());
		  	          
		  	          ps.setString(4, entry.getProjectTypeId());
		  	          ps.setString(5, entry.getWorkTypeId());
		  	          ps.setString(6, entry.getTaskId());
		  	          
		  	          ps.setString(7, entry.getTicketNumber());
		  	          ps.setFloat(8, entry.getWorkHour());
		  	          ps.setString(9, entry.getOverTime());
		  	          
		  	          ps.setString(10, entry.getNotes());
		  	          ps.setDate(11, entry.getWorkTime());
		  	        }
	    	  });
	}

	@Override
	public int deleteTimeLogEntiesByID(final int id) {
		StringBuffer strSql = new StringBuffer("delete from t_timelog_entry where timelog_id = ? ");
		
	    int result = getJdbcTemplate().update(strSql.toString(), new PreparedStatementSetter() {
	    	public void setValues(PreparedStatement ps) throws SQLException {
	    		ps.setInt(1, id);
	    	}
	    });
		
		return result;
	}

	@Override
	public void updateTimeLogEnties(final TTimeLogEntry entry) {
	    String insertOrgUserRelation = "UPDATE t_timelog_entry SET PROJECT_ID = ?," +
	    		" PROJECT_TYPE_ID = ?, WORK_TYPE_ID = ?, TASK_ID = ?, TICKET_NO = ?, WORK_HOUR = ?, " +
	    		" IS_OT = ?, NOTES = ?, WORK_TIME = ? where TIMELOG_ID = ?";
   
    	  getJdbcTemplate().update(insertOrgUserRelation, new PreparedStatementSetter() {
	  	        public void setValues(PreparedStatement ps) throws SQLException {
	  	        
	  	          ps.setString(1, entry.getProjectId());
	  	          
	  	          ps.setString(2, entry.getProjectTypeId());
	  	          ps.setString(3, entry.getWorkTypeId());
	  	          ps.setString(4, entry.getTaskId());
	  	          
	  	          ps.setString(5, entry.getTicketNumber());
	  	          ps.setFloat(6, entry.getWorkHour());
	  	          ps.setString(7, entry.getOverTime());
	  	          
	  	          ps.setString(8, entry.getNotes());
	  	          ps.setDate(9, entry.getWorkTime());
	  	          
	  	          ps.setString(10, entry.getTimeLogID());
	  	        }
    	  });

		
	}

}
