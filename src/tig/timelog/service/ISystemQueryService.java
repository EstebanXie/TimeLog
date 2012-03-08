package tig.timelog.service;

import java.sql.Date;
import java.util.List;

import tig.timelog.model.TTimeLogEntry;

public interface ISystemQueryService {
	public List<TTimeLogEntry> findTimeLogEntry(String userId, String projectTypeCode, String workCode, String taskCode, String ticketNumber
			, String isOT, Date beginDate, Date endDate, String projectName, String siteType);
	
	public List findValidate(final String userId, final Date beginDate, final Date endDate);
}
