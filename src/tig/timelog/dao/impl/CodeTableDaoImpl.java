package tig.timelog.dao.impl;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import tig.timelog.dao.ICodeTableDao;
import tig.timelog.model.Code;
import tig.timelog.model.TProject;


public class CodeTableDaoImpl extends JdbcDaoSupport implements ICodeTableDao {
	/**
	 * codeTable : T_CODE
	 */
	@SuppressWarnings("unchecked")
	public List<Code> getCodeList(final String codeType){
		String SQL = " select code_name, code_desc, code_type from t_code where code_type = '"+codeType+"'";
		final List codeList = this
				.getJdbcTemplate().query(SQL, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Code code = new Code();
						if (rs == null) {
							return null;
						} else {
							code.setCodeName(rs.getString("code_name"));
							code.setCodeDesc(rs.getString("code_desc"));
							code.setCodeType(rs.getString("code_type"));
						}
						return code;
					}
				});

		return codeList;
	}

	@Override
	public List<TProject> getProjectByType(String projectType, Date date) {
		String SQL = "select project_id, project_name, project_type_id, site_type_id, start_date, " +
				" end_date, c1.code_desc project_type_name, c2.code_desc site_type_name from" +
				" (select project_id, project_name, project_type_id, site_type_id, start_date, " +
				"  end_date from t_project " +
				" where project_type_id =? and start_date <=? and end_date>=? ) p"+
				" join t_code c1 on c1.code_name = p.project_type_id left join t_code c2 on c2.code_type = p.site_type_id ";
		Object[] params = new Object[] { projectType, date, date };
		List projectList = this.getJdbcTemplate().query(SQL, params, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TProject p = new TProject();
						if (rs == null) {
							return null;
						} else {
							p.setProjectID(rs.getInt("project_id"));
							p.setProjectName(rs.getString("project_name"));
							p.setProjectTypeID(rs.getString("project_type_id"));
							p.setSiteTypeID(rs.getString("site_type_id"));
							p.setStartDate(rs.getDate("start_date"));
							p.setEnddate(rs.getDate("end_date"));
							
							p.setProjectTypeName(rs.getString("project_type_name"));
							p.setSiteTypeName(rs.getString("site_type_name"));
						}
						return p;
					}
				});
		if(projectList == null){
			projectList = new ArrayList();
		}
		return projectList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TProject> getProjects() {
		String SQL = "select project_id, project_name, project_type_id, site_type_id, start_date,  end_date from t_project ";
		
		final List<TProject> projectList = this
				.getJdbcTemplate().query(SQL, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TProject project = new TProject();
						if (rs == null) {
							return null;
						} else {
							project.setProjectID(rs.getInt("project_id"));
							project.setProjectName(rs.getString("project_name"));
							project.setProjectTypeID(rs.getString("project_type_id"));
							project.setSiteTypeID(rs.getString("site_type_id"));
							project.setStartDate(rs.getDate("start_date"));
							project.setEnddate(rs.getDate("end_date"));
						}
						return project;
					}
				});

		return projectList;
	}
}
