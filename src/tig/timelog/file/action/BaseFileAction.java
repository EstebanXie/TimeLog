package tig.timelog.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import tig.timelog.file.service.ExcelFileService;
import tig.timelog.file.service.Userinfo;
import tig.timelog.file.vo.ExcelWorkSheet;
import tig.timelog.file.vo.TaskVO;
import tig.timelog.service.ITaskImportService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseFileAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	private ITaskImportService taskImportService;

	// variable for import file
	private File excelFile;
	private String excelFileFileName;
	private List<ExcelWorkSheet<TaskVO>> excelWorkSheets;
//	private boolean saveFileFlag;
//	private String directory = "/upload";
	// variable for export file
	private String format = "xls";
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String contentType;
	private String contentDisposition;

	public void exportFile() {
		setResponseHeader();
		try {
			ExcelFileService.exportExcel(response.getOutputStream(), format,
					this.getUserInfos());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String importFile() {
//		if (saveFileFlag) {
//			saveFileToServer();
//		}
		try {
			excelWorkSheets = ExcelFileService.importExcel(new FileInputStream(
					excelFile), excelFileFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (taskImportService.saveImportData(excelWorkSheets) > 0) {
			request.setAttribute("importStatus", "Successfully!");
		} else {
			request.setAttribute("importStatus", "Failure!");
		}
		return SUCCESS;
	}

//	private void saveFileToServer() {
//		String targetDirectory = ServletActionContext.getServletContext()
//				.getRealPath(directory);
//		File target = new File(targetDirectory, excelFileFileName);
//		if (target.exists()) {
//			target.delete();
//		}
//		try {
//			FileUtils.copyFile(excelFile, target);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	protected void setResponseHeader() {
		try {
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", contentDisposition);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private List<Userinfo> getUserInfos() {
		List<Userinfo> list = new ArrayList<Userinfo>();
		for (int i = 0; i < 5; i++) {
			Userinfo userInfo = new Userinfo();
			userInfo.setId(i + 1);
			userInfo.setName("John");
			userInfo.setLastname("Zhang");
			userInfo.setAddres("suzhou");
			userInfo.setRemark("commentspppds");
			list.add(userInfo);
		}
		return list;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> paramMap) {

	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}

	public List<ExcelWorkSheet<TaskVO>> getExcelWorkSheets() {
		return excelWorkSheets;
	}

	public void setExcelWorkSheets(List<ExcelWorkSheet<TaskVO>> excelWorkSheets) {
		this.excelWorkSheets = excelWorkSheets;
	}

//	public void setSaveFileFlag(boolean saveFileFlag) {
//		this.saveFileFlag = saveFileFlag;
//	}

//	public void setDirectory(String directory) {
//		this.directory = directory;
//	}

	public void setTaskImportService(ITaskImportService taskImportService) {
		this.taskImportService = taskImportService;
	}

}
