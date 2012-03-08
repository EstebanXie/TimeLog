package tig.timelog.service;

import java.util.List;

import tig.timelog.file.vo.ExcelWorkSheet;
import tig.timelog.file.vo.TaskVO;

public interface ITaskImportService {
	public void addTask(TaskVO task);

	public int[] saveTaskVOListToImportTaskTable(List<TaskVO> taskVO);

	public int[] saveTaskVOListToImportTaskTable2(
			List<ExcelWorkSheet<TaskVO>> excelWorkSheets);

	public int[] saveTaskVOListToImportTaskLogTable(List<TaskVO> taskVOs);

	public int[] saveTaskVOListToImportTaskLogTable2(
			List<ExcelWorkSheet<TaskVO>> excelWorkSheets);

    public int saveImportData(List<ExcelWorkSheet<TaskVO>> excelWorkSheets);
}
