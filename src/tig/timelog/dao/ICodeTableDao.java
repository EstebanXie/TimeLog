package tig.timelog.dao;

import java.sql.Date;
import java.util.List;

import tig.timelog.model.Code;
import tig.timelog.model.TProject;

public interface ICodeTableDao {
	//codetable : 
	public List<Code> getCodeList(final String codeType);
	
	public List<TProject> getProjectByType(final String projectType, final Date date);
	
	public List<TProject> getProjects();
}
