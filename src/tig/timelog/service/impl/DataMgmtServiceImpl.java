package tig.timelog.service.impl;


import java.sql.Date;
import java.util.List;

import tig.timelog.dao.IDataMgmtDao;
import tig.timelog.model.TTimeLogEntry;
import tig.timelog.service.IDataMgmtService;

public class DataMgmtServiceImpl implements IDataMgmtService {
	private IDataMgmtDao dataMgmtDao;

	public IDataMgmtDao getDataMgmtDao() {
		return dataMgmtDao;
	}

	public void setDataMgmtDao(IDataMgmtDao dataMgmtDao) {
		this.dataMgmtDao = dataMgmtDao;
	}

	@Override
	public List<TTimeLogEntry> getTimeLogEntiesByDate(Date dateSelected, String userID) {
		return dataMgmtDao.getTimeLogEntriesByDateAndUserID(dateSelected, userID);
	}

	@Override
	public void saveTimeLogEnties(List<TTimeLogEntry> listLogEntries) {
		for(TTimeLogEntry item : listLogEntries){
			if(item.getTimeLogID() != null && !"".equals(item.getTimeLogID())){
				dataMgmtDao.updateTimeLogEnties(item);
			}else if(item.getProjectId() != null && !"".equals(item.getProjectId())){
				dataMgmtDao.saveTimeLogEnties(item);
			}
		}
	}

	@Override
	public void deleteTimeLogEnties(List<TTimeLogEntry> listLogEntries) {
		for(TTimeLogEntry entry : listLogEntries){
			if(entry.isDeleteF() && !"".equals(entry.getTimeLogID().trim())){
				dataMgmtDao.deleteTimeLogEntiesByID(Integer.parseInt(entry.getTimeLogID().trim()));
			}
		}
	}

	
	

}
