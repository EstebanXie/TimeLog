package tig.timelog.file.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExportFileAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String format = "xls";
	private HttpServletResponse response;
	ActionContext context = ActionContext.getContext();
	// HttpServletRequest request = (HttpServletRequest) context
	// .get(ServletActionContext.HTTP_REQUEST);
	private String fileName;

	public String execute() {
		response = ServletActionContext.getResponse();
		setResponseHeader();
		try {
			exportExcel(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setResponseHeader() {
		try {
			// response.setContentType("application/msexcel;charset=UTF-8");
			// //两种方法都可以
			response.setContentType("application/octet-stream;charset=iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ java.net.URLEncoder.encode(this.fileName, "UTF-8"));
			// 客户端不缓存
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 导出数据 */
	private void exportExcel(OutputStream os) throws IOException {
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("Export Infos");
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("No.");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Last Name");
		row.createCell(3).setCellValue("Address");
		row.createCell(4).setCellValue("Remark");
		CellStyle sty = book.createCellStyle();
		List<Userinfo> list = this.getUserInfos();
		for (int i = 0; i < list.size(); i++) {
			Userinfo user = list.get(i);
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getLastname());
			row.createCell(3).setCellValue(user.getAddres());
			row.createCell(4).setCellValue(user.getRemark());
		}
		try {
			book.write(os);
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		this.fileName = "export-data.xls";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// /** 记住一定有该属性的set方法 */
	// public void setServletResponse(HttpServletResponse response) {
	// this.response = response;
	// }
}
