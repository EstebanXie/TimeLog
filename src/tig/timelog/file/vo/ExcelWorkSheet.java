package tig.timelog.file.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

@SuppressWarnings("hiding")
public class ExcelWorkSheet<T> {
	private String sheetName; //sheet name
	private List<T> data = new ArrayList<T>(); // data rows
	private List<String> columns; // column names

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
}
