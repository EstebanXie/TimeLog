package tig.timelog.dao;

import java.util.List;

import tig.timelog.file.vo.TaskVO;

public interface ITaskImportDao {

	public void addTask(final TaskVO task);

	public int[] saveTaskVOListToImportTaskTable(List<TaskVO> taskVO);

	public int[] updateTaskVOListToImportTaskTable(final List<TaskVO> taskVO);

	public int[] saveTaskVOListToImportTaskLogTable(List<TaskVO> taskVO);

	public List<TaskVO> getTasksByTicketNumber(final String ticketNumber, final String clientType);

    public String getCycleTimeByTicketNumber(String ticketNumber);

}
