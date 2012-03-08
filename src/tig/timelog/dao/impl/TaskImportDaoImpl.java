package tig.timelog.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.ITaskImportDao;
import tig.timelog.file.vo.TaskVO;

public class TaskImportDaoImpl extends BaseDao implements ITaskImportDao {

	public void addTask(final TaskVO task) {
		String sql = "INSERT INTO T_IMPORT_TASK (TICKET_NUMBER, CLIENT_NAME, SUBJECT, STATE, PRIORITY, DUE_DATE, IMPORT_TIME, STATUS, CLIENT_TYPE, DEVELOPER, ESTIMATE_HOURS, WORK_TYPE, QA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				long currentTime = System.currentTimeMillis();
				ps.setString(1, task.getTicketNumber());
				ps.setString(2, task.getClientName());
				ps.setString(3, task.getSubject());
				ps.setString(4, task.getState());
				ps.setString(5, task.getPriority());
				ps.setDate(6, new Date(task.getDueDate().getTime()));
				ps.setDate(7, new Date(currentTime));
				ps.setString(8, task.getStatus());
				ps.setString(9, task.getClientType());
				ps.setString(10, task.getDeveloper());
				ps.setBigDecimal(11, task.getEstimateHours());
				ps.setString(12, task.getWorkType());
				ps.setString(13, task.getQa());
			}
		});
	}

	@Override
	public int[] saveTaskVOListToImportTaskTable(final List<TaskVO> taskVOs) {
		String strSql = "INSERT INTO T_IMPORT_TASK (TICKET_NUMBER, CLIENT_NAME, SUBJECT, STATE, PRIORITY, DUE_DATE, IMPORT_TIME, STATUS, CLIENT_TYPE, DEVELOPER, ESTIMATE_HOURS, WORK_TYPE, QA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int[] updateCounts = getJdbcTemplate().batchUpdate(strSql,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, taskVOs.get(i).getTicketNumber());
						ps.setString(2, taskVOs.get(i).getClientName());
						ps.setString(3, taskVOs.get(i).getSubject());
						ps.setString(4, taskVOs.get(i).getState());
						ps.setString(5, taskVOs.get(i).getPriority());
						ps.setDate(6, new Date(taskVOs.get(i).getDueDate()
								.getTime()));
						ps.setDate(7, new Date(taskVOs.get(i).getImportTime()
								.getTime()));
						ps.setString(8, taskVOs.get(i).getStatus());
						ps.setString(9, taskVOs.get(i).getClientType());
						ps.setString(10, taskVOs.get(i).getDeveloper());
						ps.setBigDecimal(11, taskVOs.get(i).getEstimateHours());
						ps.setString(12, taskVOs.get(i).getWorkType());
						ps.setString(13, taskVOs.get(i).getQa());
					}

					public int getBatchSize() {
						return taskVOs.size();
					}
				});
		return updateCounts;
	}

    @Override
    public int[] updateTaskVOListToImportTaskTable(final List<TaskVO> taskVO) {
        String strSql = "UPDATE T_IMPORT_TASK SET SUBJECT=?, STATE=?, PRIORITY=?, DUE_DATE=?, IMPORT_TIME=?, STATUS=? WHERE TICKET_NUMBER=?";
        int[] updateCounts = getJdbcTemplate().batchUpdate(strSql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setString(1, taskVO.get(i).getSubject());
                        ps.setString(2, taskVO.get(i).getState());
                        ps.setString(3, taskVO.get(i).getPriority());
                        ps.setDate(4, new Date(taskVO.get(i).getDueDate()
                                .getTime()));
                        ps.setDate(5, new Date(taskVO.get(i).getImportTime()
                                .getTime()));
                        ps.setString(6, taskVO.get(i).getStatus());
                        ps.setString(7, taskVO.get(i).getTicketNumber());
                    }

                    public int getBatchSize() {
                        return taskVO.size();
                    }
                });
        return updateCounts;
    }

    @Override
	public int[] saveTaskVOListToImportTaskLogTable(final List<TaskVO> taskVOs) {
		String strSql = "INSERT INTO T_IMPORT_TASK_LOG (TICKET_NUMBER, CLIENT_NAME, SUBJECT, STATE, PRIORITY, DUE_DATE, IMPORT_TIME, STATUS, CYCLE_TIME, COMPLETE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int[] updateCounts = getJdbcTemplate().batchUpdate(strSql,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, taskVOs.get(i).getTicketNumber());
						ps.setString(2, taskVOs.get(i).getClientName());
						ps.setString(3, taskVOs.get(i).getSubject());
						ps.setString(4, taskVOs.get(i).getState());
						ps.setString(5, taskVOs.get(i).getPriority());
						ps.setDate(6, new Date(taskVOs.get(i).getDueDate()
								.getTime()));
						ps.setDate(7, new Date(taskVOs.get(i).getImportTime()
								.getTime()));
						ps.setString(8, taskVOs.get(i).getStatus());
						ps.setString(9, taskVOs.get(i).getCycleTime());
						ps.setString(10, null);
					}
					public int getBatchSize() {
						return taskVOs.size();
					}
				});
		return updateCounts;
	}

    @Override
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTasksByTicketNumber(final String ticketNumber, final String clientType) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer findSql = new StringBuffer(
				"select TASK_ID, TICKET_NUMBER, CLIENT_NAME, SUBJECT, STATE, PRIORITY, DUE_DATE, ");
		findSql.append("IMPORT_TIME, STATUS, CLIENT_TYPE, DEVELOPER, ESTIMATE_HOURS, WORK_TYPE, QA ");
		findSql.append("from T_IMPORT_TASK ");
		findSql.append("where 1 = 1 ");
		if (ticketNumber!=null && !"".equals(ticketNumber)) {
			findSql.append("and TICKET_NUMBER = ? ");
		    paramList.add(ticketNumber);
		}
		if (clientType!=null && !"".equals(clientType)) {
			findSql.append("and CLIENT_TYPE = ? ");
		    paramList.add(clientType);
		}
		findSql.append("order by DUE_DATE DESC ");
		Object[] params = paramList.toArray();
		List<TaskVO> taskList = this.getJdbcTemplate().query(
				findSql.toString(), params, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TaskVO task = new TaskVO();
						if (rs == null)
							return null;
						else {
							task.setTaskId(rs.getLong("TASK_ID"));
							task.setTicketNumber(rs.getString("TICKET_NUMBER"));
							task.setClientName(rs.getString("CLIENT_NAME"));
							task.setSubject(rs.getString("SUBJECT"));
							task.setState(rs.getString("STATE"));
							task.setPriority(rs.getString("PRIORITY"));
							task.setDueDate(rs.getDate("DUE_DATE"));
							task.setImportTime(rs.getDate("IMPORT_TIME"));
							task.setStatus(rs.getString("STATUS"));
							task.setClientType(rs.getString("CLIENT_TYPE"));
							task.setDeveloper(rs.getString("DEVELOPER"));
							task.setEstimateHours(rs.getBigDecimal("ESTIMATE_HOURS"));
							task.setWorkType(rs.getString("WORK_TYPE"));
							task.setQa(rs.getString("QA"));
						}
						return task;
					}
				});
		return taskList;
	}

    @Override
    @SuppressWarnings("unchecked")
    public String getCycleTimeByTicketNumber( String ticketNumber) {
        List<Object> paramList = new ArrayList<Object>();
        StringBuffer findSql = new StringBuffer("SELECT LOG_ID,TICKET_NUMBER FROM T_IMPORT_TASK_LOG WHERE 1=1 ");
        if (ticketNumber!=null && !"".equals(ticketNumber)) {
            findSql.append("and TICKET_NUMBER = ? ");
            paramList.add(ticketNumber);
        }

        Object[] params = paramList.toArray();
        List<TaskVO> taskList = this.getJdbcTemplate().query(
                findSql.toString(), params, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                TaskVO task = new TaskVO();
                if (rs == null)
                    return null;
                else {
                    task.setTaskId(rs.getLong("LOG_ID"));
                    task.setTicketNumber(rs.getString("TICKET_NUMBER"));
                }
                return task;
            }
        });

        if(taskList!=null)
        {
            return String.valueOf(taskList.size());
        }
        return String.valueOf(0);
    }
}
