package tig.timelog.dao;

import java.sql.Date;
import java.util.List;

import tig.timelog.model.TTimeLogEntry;
import tig.timelog.model.TimeLogVO;

public interface ISystemQueryDao {
	public List<TTimeLogEntry> findTimeLogEntry(final String userId, final String projectTypeCode, final String workCode, final String taskCode, final String ticketNumber
			, final String isOT, final Date beginDate, final Date endDate, final String projectName, final String siteType);
	
	public List<TimeLogVO> findValidate(final String userId, final Date beginDate, final Date endDate);
}
