package tig.timelog.dao;


import java.sql.Date;
import java.util.List;

import tig.timelog.model.TTimeLogEntry;

public interface IDataMgmtDao {

	public List<TTimeLogEntry> getTimeLogEntriesByDateAndUserID(final Date date, final String userID);
	
	public int deleteTimeLogEntiesByDateAndUserID(final Date date, final String userID);
	
	public int deleteTimeLogEntiesByID(final int id);
	
	public void saveTimeLogEnties(final TTimeLogEntry entry);
	
	public void updateTimeLogEnties(final TTimeLogEntry entry);

}
