package tig.timelog.service.impl;

import java.util.*;

import tig.timelog.dao.IEZClientDao;
import tig.timelog.dao.ITaskImportDao;
import tig.timelog.file.vo.ExcelWorkSheet;
import tig.timelog.file.vo.TaskVO;
import tig.timelog.model.NClientType;
import tig.timelog.model.NTimeLogState;
import tig.timelog.service.ITaskImportService;

public class TaskImportServiceImpl implements ITaskImportService {
	private ITaskImportDao taskImportDao;
    private IEZClientDao ezClientDao;
    private static final String MAP_INSERT = "insert";
    private static final String MAP_UPDATE = "update";
    private static final String MAP_INSERT_LOG = "insertLog";

	public void setTaskImportDao(ITaskImportDao taskImportDao) {
		this.taskImportDao = taskImportDao;
	}

	public void addTask(TaskVO task) {
		taskImportDao.addTask(task);
	}

    public void setEzClientDao(IEZClientDao ezClientDao) {
        this.ezClientDao = ezClientDao;
    }

    @Override
	public int[] saveTaskVOListToImportTaskTable(List<TaskVO> taskVOList) {
		return taskImportDao.saveTaskVOListToImportTaskTable(taskVOList);
	}

	@Override
	public int[] saveTaskVOListToImportTaskTable2(
			List<ExcelWorkSheet<TaskVO>> excelWorkSheets) {
		List<TaskVO> taskVOList = this.generateTaskVOList(excelWorkSheets);

//		return taskImportDao.saveTaskVOListToImportTaskTable(rebuildTaskVOList(taskVOList));
		return null;
	}

	private List<TaskVO> generateTaskVOList(
			List<ExcelWorkSheet<TaskVO>> excelWorkSheets) {
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		for (ExcelWorkSheet<TaskVO> excelWorkSheet : excelWorkSheets) {
			taskVOList.addAll(excelWorkSheet.getData());
		}
		return taskVOList;
	}

	@Override
	public int[] saveTaskVOListToImportTaskLogTable(List<TaskVO> taskVOs) {
		return taskImportDao.saveTaskVOListToImportTaskLogTable(taskVOs);
	}

	@Override
	public int[] saveTaskVOListToImportTaskLogTable2(
			List<ExcelWorkSheet<TaskVO>> excelWorkSheets) {
		List<TaskVO> taskVOList = this.generateTaskVOList(excelWorkSheets);
//		return taskImportDao.saveTaskVOListToImportTaskLogTable(rebuildTaskVOList(taskVOList));
		return null;
	}

    @Override
    public int saveImportData(List<ExcelWorkSheet<TaskVO>> excelWorkSheets) {
        int count = 0;
        List<TaskVO> taskVOList = this.generateTaskVOList(excelWorkSheets);
        //get new task VO which include new values
        Map<String,List<TaskVO>> taskVOMap = rebuildTaskVOList(taskVOList);
        List<TaskVO> insertTaskVOList = taskVOMap.get(MAP_INSERT);
        List<TaskVO> updateTaskVOList = taskVOMap.get(MAP_UPDATE);
        List<TaskVO> insertLogTaskVOList = taskVOMap.get(MAP_INSERT_LOG);
        if(insertTaskVOList!=null&&insertTaskVOList.size()!=0)
        {
            count = (taskImportDao.saveTaskVOListToImportTaskTable(insertTaskVOList)).length;
        }
        if(updateTaskVOList!=null&&updateTaskVOList.size()!=0)
        {
            taskImportDao.updateTaskVOListToImportTaskTable(updateTaskVOList);
        }
        if(insertLogTaskVOList!=null&&insertLogTaskVOList.size()!=0)
        {
            taskImportDao.saveTaskVOListToImportTaskLogTable(insertLogTaskVOList);
        }

        return count;
    }

    private Map<String,List<TaskVO>> rebuildTaskVOList(List<TaskVO> taskVOList){
        Map<String,List<TaskVO>> taskVOMap = new HashMap<String, List<TaskVO>>();
        List<TaskVO> insertTaskVOList = new ArrayList<TaskVO>();
        List<TaskVO> updateTaskVOList = new ArrayList<TaskVO>();
        List<TaskVO> insertLogTaskVOList = new ArrayList<TaskVO>();
        if(taskVOList!=null)
        {
            for(TaskVO taskVO : taskVOList)
            {
                TaskVO newTaskVO = new TaskVO();
                //get the cycle times
                String cycleTime = this.getCycleTimeByTicketNumber(taskVO.getTicketNumber());
                Boolean isExistTicketNumber =  isExistTicketNumber(cycleTime);

                //The values are from the import excel
                newTaskVO = setOriginalImportValue(newTaskVO,taskVO);

                //start to set new value base on business rule
                newTaskVO.setClientType(getClientType(taskVO.getClientName())); //set client type
                // convert import TIG state to time log status
                newTaskVO.setStatus(NTimeLogState.resolve(taskVO.getState(),isExistTicketNumber));
                //set cycle times
                newTaskVO.setCycleTime(cycleTime);

                if(isExistTicketNumber)
                {
                    updateTaskVOList.add(newTaskVO);        
                }else{
                    insertTaskVOList.add(newTaskVO);    
                }

                insertLogTaskVOList.add(newTaskVO);
            }
            taskVOMap.put(MAP_INSERT,insertTaskVOList);
            taskVOMap.put(MAP_UPDATE,updateTaskVOList);
            taskVOMap.put(MAP_INSERT_LOG,insertLogTaskVOList);
            return taskVOMap;
        }
        return null;
    }

    private TaskVO setOriginalImportValue(TaskVO destinationTaskVO,TaskVO originalTaskVO){
        destinationTaskVO.setClientName(originalTaskVO.getClientName());
        destinationTaskVO.setTicketNumber(originalTaskVO.getTicketNumber());
        destinationTaskVO.setDueDate(originalTaskVO.getDueDate());
        destinationTaskVO.setPriority(originalTaskVO.getPriority());
        destinationTaskVO.setState(originalTaskVO.getState());
        destinationTaskVO.setImportTime(new Date());
        destinationTaskVO.setSubject(originalTaskVO.getSubject());
        return destinationTaskVO;
    }
    
    private String getClientType(String clientName){
        if(ezClientDao.getClientByName(clientName)!=null)
        {
            return   NClientType.EZ_PUBLISH.getType();
        }
        return NClientType.CODE_FUSION.getType();
    }
    
    private String getCycleTimeByTicketNumber(String ticketNumber){
        return taskImportDao.getCycleTimeByTicketNumber(ticketNumber);
    }
    
    private Boolean isExistTicketNumber(String cycleTime){
        if(cycleTime.equals("0")){
            return Boolean.FALSE;         
        }
        return Boolean.TRUE;
    }
}
