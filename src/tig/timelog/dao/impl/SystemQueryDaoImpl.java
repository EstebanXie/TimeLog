package tig.timelog.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.ISystemQueryDao;
import tig.timelog.model.TTimeLogEntry;
import tig.timelog.model.TimeLogVO;

public class SystemQueryDaoImpl extends BaseDao implements ISystemQueryDao {
	@Override
	@SuppressWarnings("unchecked")
	public List<TTimeLogEntry> findTimeLogEntry(final String userId, final String projectTypeCode, final String workCode, final String taskCode, final String ticketNumber
			, final String isOT, final Date beginDate, final Date endDate, final String projectName, final String siteType){
		
		StringBuffer sql = new StringBuffer("select s.USER_NAME, s.project_name project,c0.CODE_DESC projectType, c1.CODE_DESC siteType, c2.CODE_DESC workType")
		.append(", c3.CODE_DESC taskType, s.TICKET_NO, s.WORK_HOUR, s.WORK_TIME, c4.CODE_DESC isOT, s.NOTES ")
		.append(" from (select u.USER_NAME, p.project_name, p.SITE_TYPE_ID, p.PROJECT_TYPE_ID, t.WORK_TYPE_ID, t.TASK_ID,t.TICKET_NO, t.WORK_HOUR, t.WORK_TIME")
		.append(", t.IS_OT, t.NOTES from t_user u INNER join t_timelog_entry t on u.USER_ID = t.USER_ID ")
		.append(" inner join t_project p on p.PROJECT_ID = t.PROJECT_ID where 1=1 ");
		if(userId != null && !userId.equals("")){
			sql.append(" and t.USER_ID = '").append(userId).append("'");
		}
		if(projectName != null && !projectName.equals("")){
			sql.append(" and p.PROJECT_NAME like '%").append(projectName.trim()).append("%'");
		}
		if(siteType != null && !siteType.equals("")){
			sql.append(" and p.SITE_TYPE_ID = '").append(siteType).append("'");
		}
		if(projectTypeCode != null && !projectTypeCode.equals("")){
			sql.append(" and p.PROJECT_TYPE_ID = '").append(projectTypeCode).append("'");
		}
		if(workCode != null && !workCode.equals("")){
			sql.append(" and t.WORK_TYPE_ID =  '").append(workCode).append("'");
		}
		if(taskCode != null && !taskCode.equals("")){
			sql.append(" and t.TASK_ID = '").append(taskCode).append("'");
		}
		if(ticketNumber != null && !ticketNumber.equals("")){
			sql.append(" and t.TICKET_NO like '%").append(ticketNumber.trim()).append("%'");
		}
		if(isOT != null && !isOT.equals("")){
			sql.append(" and t.IS_OT = '").append(isOT).append("'");
		}
		if(beginDate != null ){
			sql.append(" and t.WORK_TIME >= date('").append(beginDate).append("')");
		}
		if(endDate != null ){
			sql.append(" and t.WORK_TIME <= date('").append(endDate).append("')");
		}
		sql.append(")s left join t_code c0 on s.PROJECT_TYPE_ID = c0.CODE_NAME and c0.CODE_TYPE = 'Project'")
		.append(" left join t_code c1 on s.SITE_TYPE_ID = c1.CODE_NAME and c1.CODE_TYPE = 'Site'")
	    .append(" left join t_code c2 on s.WORK_TYPE_ID = c2.CODE_NAME and c2.CODE_TYPE = 'Work'")
		.append(" left join t_code c3 on s.TASK_ID = c3.CODE_NAME and c3.CODE_TYPE = 'Task'")
		.append(" left join t_code c4 on s.IS_OT = c4.CODE_NAME and c4.CODE_TYPE = 'OT'")
		.append(" order by WORK_TIME desc, USER_NAME, project");
				
	    List<TTimeLogEntry> timeEntryList = this.getJdbcTemplate().query(sql.toString(), new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	TTimeLogEntry timeEntry = new TTimeLogEntry();
	          if (rs == null) {
	            return null;
	          } else {
	        	  timeEntry.setUserNameString(rs.getString("USER_NAME"));
	        	  timeEntry.setIsOTString(rs.getString("isOT"));
	        	  timeEntry.setProjectString(rs.getString("project"));
	        	  timeEntry.setProjectTypeString(rs.getString("projectType"));
	        	  timeEntry.setSiteString(rs.getString("siteType"));
	        	  timeEntry.setWorkString(rs.getString("workType"));
	        	  timeEntry.setTaskString(rs.getString("taskType"));
	        	  timeEntry.setTicketNumber(rs.getString("TICKET_NO"));
	        	  timeEntry.setWorkHour(rs.getFloat("WORK_HOUR"));
	        	  timeEntry.setWorkTime(rs.getDate("WORK_TIME"));
	        	  timeEntry.setNotes(rs.getString("NOTES"));
	          }
	          
	          return timeEntry;
	        }
	      });

	    return timeEntryList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TimeLogVO> findValidate(final String userId, final Date beginDate, final Date endDate){
		StringBuffer sql = new StringBuffer("select u.USER_NAME,t.WORK_TIME,t.IS_OT,(case when t.IS_OT ='T' then round(sum(work_hour),2) else 0 end) OT_HOUR,(case when is_ot ='F' then round(sum(work_hour),2) else 0 end) NON_OT_HOUR from t_user u ")
		.append("INNER join t_timelog_entry t on u.USER_ID = t.USER_ID where 1=1 ");
		if(userId != null && !userId.equals("")){
			sql.append(" and t.USER_ID = '").append(userId).append("'");
		}
		if(beginDate != null ){
			sql.append(" and t.WORK_TIME >= date('").append(beginDate).append("')");
		}
		if(endDate != null ){
			sql.append(" and t.WORK_TIME <= date('").append(endDate).append("')");
		}
		sql.append(" group by u.USER_NAME, t.WORK_TIME,t.IS_OT order by t.WORK_TIME, u.USER_NAME");
				
	    List<TimeLogVO> returnList = this.getJdbcTemplate().query(sql.toString(), new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	TimeLogVO timeLogVO = new TimeLogVO();
	          if (rs == null) {
	            return null;
	          } else {
	        	  timeLogVO.setUserName(rs.getString("USER_NAME"));
	        	  timeLogVO.setWorkTime(rs.getDate("WORK_TIME"));
	        	  timeLogVO.setNonOTHour(rs.getFloat("NON_OT_HOUR"));
	        	  timeLogVO.setOtHour(rs.getFloat("OT_HOUR"));
	          }
	          
	          return timeLogVO;
	        }
	      });

	    return returnList;
	}
}
