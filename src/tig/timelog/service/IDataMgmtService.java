package tig.timelog.service;


import java.sql.Date;
import java.util.List;

import tig.timelog.model.TTimeLogEntry;


public interface IDataMgmtService {

	List<TTimeLogEntry> getTimeLogEntiesByDate(Date dateSelected, String userID);
	
	void saveTimeLogEnties(List<TTimeLogEntry> listLogEntries);
	
	void deleteTimeLogEnties(List<TTimeLogEntry> listLogEntries);
	
}
