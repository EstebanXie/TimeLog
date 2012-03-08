package tig.timelog.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import tig.timelog.dao.IProjectMgmtDao;
import tig.timelog.model.TProject;


public class ProjectMgmtDaoImpl extends BaseDao implements IProjectMgmtDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<TProject> listAllProjects(TProject project) {
		String prjSql = "SELECT PROJECT_ID, PROJECT_NAME, PROJECT_TYPE_ID, " +
				"SITE_TYPE_ID, START_DATE, END_DATE, code_desc " +
				"FROM t_project tp, t_code tc where tp.project_type_id = tc.code_name " +
				"and tc.code_type = 'Project'";
		if (project != null)
		{
			if (project.getProjectName()!=null && !"".equals(project.getProjectName()))
			{
				prjSql += " and tp.project_name like '%"+project.getProjectName().trim()+"%' ";
			}
			if (project.getProjectTypeID()!=null && !"".equals(project.getProjectTypeID()))
			{
				prjSql += " and tp.project_type_id ='"+project.getProjectTypeID()+"' ";
			}
			if (project.getProjectID()!=null)
			{
				prjSql += " and tp.project_id = "+project.getProjectID();
			}
			
		}
		List<TProject> prjList = getJdbcTemplate().query(prjSql, new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				TProject project = new TProject();
				if (rs == null)
				{
					return null;
				}
				else {
					project.setProjectID(rs.getInt("PROJECT_ID"));
					project.setProjectName(rs.getString("PROJECT_NAME"));
					project.setProjectTypeID(rs.getString("PROJECT_TYPE_ID"));
					project.setProjectTypeName(rs.getString("code_desc"));
					project.setSiteTypeID(rs.getString("SITE_TYPE_ID"));
					project.setStartDate(rs.getDate("START_DATE"));
					project.setEnddate(rs.getDate("END_DATE"));
				}
				return project;
			}
		});
		return prjList;
	}

	@Override
	public void addProject(final TProject project) {
		String insertSql = "INSERT INTO T_PROJECT (project_name, project_type_id,"
				+ "site_type_id, start_date, end_date) VALUES(?, ?, ?, ?, ?)";
		getJdbcTemplate().update(insertSql, new PreparedStatementSetter() {;
			public void setValues(PreparedStatement ps) throws SQLException {
				initProject(project);
				
				ps.setString(1, project.getProjectName());
				ps.setString(2, project.getProjectTypeID());
				ps.setString(3, project.getSiteTypeID());
				ps.setDate(4, project.getStartDate());
				ps.setDate(5, project.getEnddate());
			}
		});
	}

	@Override
	public void modifyProject(final TProject project) {
		String updateSql = "UPDATE t_project SET project_name = ?, project_type_id = ?, site_type_id = ?, "
				+ "start_date = ?, end_date = ? WHERE project_id = ?";
		getJdbcTemplate().update(updateSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				initProject(project);
				
				ps.setString(1, project.getProjectName());
				ps.setString(2, project.getProjectTypeID());
				ps.setString(3, project.getSiteTypeID());
				ps.setDate(4, project.getStartDate());
				ps.setDate(5, project.getEnddate());
				ps.setInt(6, project.getProjectID());
			}
		});
	}
	
	private void initProject(TProject project)
	{
		if(project.getProjectTypeID()==null)
		{
			project.setProjectTypeID("");
		}
		if (project.getSiteTypeID()==null)
		{
			project.setSiteTypeID("");
		}
		Date currentDate = new Date(System.currentTimeMillis());
		if (project.getStartDate()==null)
		{
			project.setStartDate(currentDate);
		}
		if (project.getEnddate()==null)
		{
			project.setEnddate(currentDate);
		}
		//end date should not before start date
		if (project.getEnddate().before(project.getStartDate()))
		{
			project.setEnddate(project.getStartDate());
		}
	}
	
	
}
