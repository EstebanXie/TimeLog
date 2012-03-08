package tig.timelog.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import tig.timelog.dao.ISystemQueryDao;
import tig.timelog.model.TTimeLogEntry;
import tig.timelog.model.TimeLogVO;
import tig.timelog.service.ISystemQueryService;
import tig.timelog.util.DateUtil;

public class SystemQueryServiceImpl implements ISystemQueryService{
	private ISystemQueryDao systemQueryDao; 
	
	public void setSystemQueryDao(ISystemQueryDao systemQueryDao) {
		this.systemQueryDao = systemQueryDao;
	}

	public List<TTimeLogEntry> findTimeLogEntry(String userId, String projectTypeCode, String workCode, String taskCode, String ticketNumber
				, String isOT, Date beginDate, Date endDate, String projectName, String siteType){
		
		return systemQueryDao.findTimeLogEntry(userId, projectTypeCode, workCode, taskCode, ticketNumber, isOT, beginDate, endDate,projectName,siteType);
	}

	public List findValidate(final String userId, final Date beginDate, final Date endDate){
		List<TimeLogVO> list = systemQueryDao.findValidate(userId, beginDate, endDate);
		List<Object> titleList = new ArrayList<Object>();
		titleList.add("Resource Name");

		for( Date begin = beginDate; begin.before(endDate)|| begin.equals(endDate); ){
			titleList.add(begin);
			begin = new java.sql.Date(begin.getTime()+86400000);
		}

		Set<String> set = new HashSet<String>();

		if(list != null && !list.isEmpty()){
			for(int i=0; i<list.size(); i++){
				if(!titleList.contains(list.get(i).getWorkTime()))
					titleList.add(list.get(i).getWorkTime());

				set.add(list.get(i).getUserName());
			}
		}
		Object[][] array = new Object[set.size()+1][titleList.size()];
		
		for(int i=0; i<titleList.size();i++){
			array[0][i] = titleList.get(i);
		}
		Iterator it = set.iterator(); 
		int m=1;
		while(it.hasNext()){
			array[m++][0] = it.next();
		}
		
		for (int j = 0; j < list.size(); j++) {
			for(int i=1;i<titleList.size();i++){
				if(DateUtil.date2String(list.get(j).getWorkTime(), "yy/mm/dd").equals(DateUtil.date2String((Date)array[0][i], "yy/mm/dd")) ){
					for(int k=0;k<set.size();k++){	
						if(list.get(j).getUserName().equals(array[k+1][0])){
							if (array[k + 1][i] == null) {
								if (list.get(j).getNonOTHour() != 0) {
									array[k + 1][i] = list.get(j)
											.getNonOTHour();
								} else if(list.get(j).getOtHour() !=0){
									array[k + 1][i] = "/OT-"
											+ list.get(j).getOtHour();
								}
							} else {
								if (array[k + 1][i].toString().contains("/OT")) {
									array[k + 1][i] = list.get(j)
											.getNonOTHour()
											+ String.valueOf(array[k + 1][i]);

								}else if (list.get(j).getOtHour() != 0) {
									array[k + 1][i] = String
											.valueOf(array[k + 1][i])
											+ "/OT-"
											+ list.get(j).getOtHour();
								}
							}
						}
					}
				}
			}
		} 
		List<Object> returnList = new ArrayList<Object>();
		
		for(int i=0; i<set.size()+1;i++){
			List<Object> itemList = new ArrayList<Object>();
			for(int j=0; j<titleList.size();j++){
				itemList.add(j, array[i][j]);
			}
			returnList.add(itemList);
		}
		return returnList;
	}
}
