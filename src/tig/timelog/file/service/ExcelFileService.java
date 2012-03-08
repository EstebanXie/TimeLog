package tig.timelog.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tig.timelog.file.vo.ExcelWorkSheet;
import tig.timelog.file.vo.TaskVO;

public class ExcelFileService {

	public static void exportExcel(OutputStream os, String format,
			List<Userinfo> list) throws IOException {
		Workbook book = null;
		if ("xls".equalsIgnoreCase(format)) {
			book = new HSSFWorkbook();
		}
		if ("xlsx".equalsIgnoreCase(format)) {
			book = new XSSFWorkbook();
		}
		Sheet sheet = book.createSheet("Export Infos");
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("No.");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Last Name");
		row.createCell(3).setCellValue("Address");
		row.createCell(4).setCellValue("Remark");
		for (int i = 0; i < list.size(); i++) {
			Userinfo user = list.get(i);
			row = sheet.createRow(i + 1);
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

	public static List<ExcelWorkSheet<TaskVO>> importExcel(InputStream is,
			String excelFileFileName) {
		List<ExcelWorkSheet<TaskVO>> excelWorkSheets = new ArrayList<ExcelWorkSheet<TaskVO>>();
		Date importDate = new Date();
		try {
			Workbook book = createWorkBook(is, excelFileFileName);
			// get the total number of sheets in Excel,support multi-sheets
			for (int j = 0; j < book.getNumberOfSheets(); j++) {
				ExcelWorkSheet<TaskVO> excelWorkSheet = new ExcelWorkSheet<TaskVO>();
				Sheet sheet = book.getSheetAt(j);
				excelWorkSheet.setSheetName(sheet.getSheetName());
				Row firstRow = sheet.getRow(0);
				Iterator<Cell> iterator = firstRow.iterator();

				List<String> cellNames = new ArrayList<String>();
				while (iterator.hasNext()) {
					cellNames.add(iterator.next().getStringCellValue());
				}
				excelWorkSheet.setColumns(cellNames);
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row ros = sheet.getRow(i);
					if (!"".equals(ros.getCell(0).getStringCellValue())
							&& ros.getCell(0).getStringCellValue() != null) {
						TaskVO taskVO = new TaskVO();
						taskVO.setTicketNumber(ros.getCell(0)
								.getStringCellValue());
						taskVO.setClientName(ros.getCell(1)
								.getStringCellValue());
						taskVO.setSubject(ros.getCell(2).getStringCellValue());
						taskVO.setState(ros.getCell(3).getStringCellValue());
						taskVO.setPriority(ros.getCell(4).getStringCellValue());
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						Date dueDate = null;
						try {
							dueDate = sdf.parse(ros.getCell(5)
									.getStringCellValue());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						taskVO.setDueDate(dueDate);
						taskVO.setImportTime(importDate);
						excelWorkSheet.getData().add(taskVO);
					}
				}
				excelWorkSheets.add(excelWorkSheet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelWorkSheets;
	}

	private static Workbook createWorkBook(InputStream is,
			String excelFileFileName) throws IOException {
		if (excelFileFileName.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(is);
		}
		if (excelFileFileName.toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(is);
		}
		return null;
	}

}
