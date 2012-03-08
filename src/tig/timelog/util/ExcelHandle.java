package tig.timelog.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Boolean;
import jxl.write.Border;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tig.timelog.model.TTimeLogEntry;

/**
 * Created by IntelliJ IDEA. User: xl Date: 2005-7-17 Time: 9:33:22 To change
 * this template use File | Settings | File Templates.
 */
public class ExcelHandle {
  public ExcelHandle() {
  }

  //file name : fileName.xls

  public static void responseInit(HttpServletResponse response, String fileName) throws Exception {
    response.reset();
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859_1"));
  }
  

  private static String[] allInfoTitle = {"Work Time","Resource Name","Project","Project Type","Site Type",
		  "Work Type","Task","Ticket Number","Work Hours","IsOT","Note"}; 

  /**
   * 读取Excel
   *
   * @param filePath
   */
  public static void readExcel(String filePath) {
    try {
      InputStream is = new FileInputStream(filePath);
      Workbook rwb = Workbook.getWorkbook(is);
      // Sheet st = rwb.getSheet("0")这里有两种方法获取sheet表,1为名字，而为下标，从0开始
      Sheet st = rwb.getSheet("original");
      Cell c00 = st.getCell(0, 0);
      // 通用的获取cell值的方式,返回字符串
      String strc00 = c00.getContents();
      // 获得cell具体类型值的方式
      if (c00.getType() == CellType.LABEL) {
        LabelCell labelc00 = (LabelCell)c00;
        strc00 = labelc00.getString();
      }
      // 输出
      System.out.println(strc00);
      // 关闭
      rwb.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
//
  public static void writeExcel(OutputStream os) {
	    try {
	      /**
				 * 只能通过API提供的工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，
				 * 因为类WritableWorkbook的构造函数为protected类型
				 * method(1)直接从目标文件中读取WritableWorkbook wwb =
				 * Workbook.createWorkbook(new File(targetfile)); method(2)如下实例所示
				 * 将WritableWorkbook直接写入到输出流
				 */
	      WritableWorkbook wwb = Workbook.createWorkbook(os);
	      // 创建Excel工作表 指定名称和位置
	      WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);

	      // **************往工作表中添加数据*****************

	      // 1.添加Label对象
	      Label label = new Label(0, 0, "this is a label test");
	      ws.addCell(label);

	      // 添加带有字型Formatting对象
	      WritableFont wf = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
	      WritableCellFormat wcf = new WritableCellFormat(wf);
	      Label labelcf = new Label(1, 0, "this is a label test", wcf);
	      ws.addCell(labelcf);

	      // 添加带有字体颜色的Formatting对象
	      WritableFont wfc =
	        new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
	          jxl.format.Colour.RED);
	      WritableCellFormat wcfFC = new WritableCellFormat(wfc);
	      Label labelCF = new Label(2, 0, "This is a Label Cell", wcfFC);
	      ws.addCell(labelCF);

	      // 2.添加Number对象
	      Number labelN = new Number(3, 1, 3.1415926);
	      ws.addCell(labelN);

	      // 添加带有formatting的Number对象
	      NumberFormat nf = new NumberFormat("#.##");
	      WritableCellFormat wcfN = new WritableCellFormat(nf);
	      Number labelNF = new jxl.write.Number(1, 1, 1111113.1415926, wcfN);
	      ws.addCell(labelNF);

	      // 3.添加Boolean对象
	      Boolean labelB = new jxl.write.Boolean(0, 2, false);
	      ws.addCell(labelB);

	      // 4.添加DateTime对象
	      jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3, new java.util.Date());
	      ws.addCell(labelDT);

	      // 添加带有formatting的DateFormat对象
	      DateFormat df = new DateFormat("yyyy/MM/dd");
	      WritableCellFormat wcfDF = new WritableCellFormat(df);
	      DateTime labelDTF = new DateTime(1, 3, new java.util.Date(), wcfDF);
	      ws.addCell(labelDTF);

	      // 添加图片对象,jxl只支持png格式图片
	      // File image = new File("f:\\2.png");
	      // WritableImage wimage = new WritableImage(10, 10, 20, 2, image);//
	      // 0,1分别代表x,y.2,2代表宽和高占的单元格数
	      // ws.addImage(wimage);
	      // 写入工作表
	      wwb.write();
	      wwb.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
  
  
  /**
   * 拷贝后,进行修改,其中file1为被copy对象，file2为修改后创建的对象
   * 尽单元格原有的格式化修饰是不能去掉的，我们还是可以将新的单元格修饰加上去， 以使单元格的内容以不同的形式表现
   *
   * @param file1
   * @param file2
   */
  public static void modifyExcel(File file1, File file2) {
    try {
      Workbook rwb = Workbook.getWorkbook(file1);
      WritableWorkbook wwb = Workbook.createWorkbook(file2, rwb); // copy
      WritableSheet ws = wwb.getSheet(0);
      WritableCell wc = ws.getWritableCell(0, 0);
      // 判断单元格的类型,做出相应的转换
      if (wc.getType().equals(CellType.LABEL)) {
        Label label = (Label)wc;
        label.setString("The value has been modified");
      }
      wwb.write();
      wwb.close();
      rwb.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  public static void writeDataSearchExcel(OutputStream os, List<TTimeLogEntry> timeLogList){
		 WritableWorkbook wwb;
		 
		 try{
			  wwb = Workbook.createWorkbook(os);
		      WritableSheet ws = wwb.createSheet("Sheet", 0);

		      WritableCellFormat format = new WritableCellFormat();
		      format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		      WritableFont wf = new WritableFont(WritableFont.ARIAL, 11,WritableFont.BOLD, 
		    		  false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); 
		      
		      WritableCellFormat headerformat = new WritableCellFormat(wf);
		      headerformat.setAlignment(Alignment.CENTRE);
		      headerformat.setVerticalAlignment(VerticalAlignment.CENTRE);
		      headerformat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		      headerformat.setFont(new WritableFont(WritableFont.ARIAL ,16 , WritableFont.BOLD ));
		      
		      WritableCellFormat titleformat = new WritableCellFormat(wf);
		      titleformat.setAlignment(Alignment.CENTRE);
		      titleformat.setVerticalAlignment(VerticalAlignment.CENTRE);
		      titleformat.setBackground(Colour.GRAY_25);
		      titleformat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		      titleformat.setWrap(false);
		      
		      WritableCellFormat centerformat = new WritableCellFormat(wf);
		      centerformat.setAlignment(Alignment.CENTRE);
//		      centerformat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		      
		      WritableCellFormat bodyformat = new WritableCellFormat(wf);
		      bodyformat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		      
		      WritableCellFormat calformat = new WritableCellFormat(wf);
		      calformat.setAlignment(Alignment.CENTRE);
		      calformat.setBackground(Colour.GRAY_25);
		      
		      NumberFormat nf = new NumberFormat("0.00");
		      WritableCellFormat wcfN = new WritableCellFormat(nf);
		      wcfN.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		      wcfN.setAlignment(Alignment.RIGHT);
		      
		      int row = 0;
		     
		      //写清单的表头
		      Label xlsHead = new Label(0, row, "TimeLog", headerformat);
		      ws.mergeCells(0, row, 10, 0);
		      ws.addCell(xlsHead);
		      row++;
		      
		      ws.setColumnView(0,15);
		      ws.setColumnView(1,18);
		      ws.setColumnView(2,25);
		      ws.setColumnView(3,15);
		      ws.setColumnView(4,12);
		      ws.setColumnView(5,15);
		      ws.setColumnView(6,18);
		      ws.setColumnView(7,15);
		      ws.setColumnView(8,13);
		      ws.setColumnView(9,8);
		      ws.setColumnView(10,30);
		      
	    	  Label columnTitle = new Label(0, row, "");
	    	 
	    	  
	    	  for(int i = 0; i < allInfoTitle.length; i++){
	    		  columnTitle = new Label(i, row, allInfoTitle[i], titleformat);
	 		     // ws.mergeCells(0, row, 0, row+1);
//	 		      ws.setColumnView(0,11);
	 		      ws.addCell(columnTitle);
	    	  }
		     
		      row++;
			  Label filedValue = new Label(0, row, "");
			
			  for(TTimeLogEntry entry: timeLogList){
			  
				  filedValue = new Label(0,row ,DateUtil.date2String(entry.getWorkTime()),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(1,row,entry.getUserNameString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(2,row,entry.getProjectString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(3,row,entry.getProjectTypeString(),bodyformat);
				  ws.addCell(filedValue);

				  filedValue=new Label(4,row,entry.getSiteString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(5,row,entry.getWorkString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(6,row,entry.getTaskString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(7,row,entry.getTicketNumber(),bodyformat);
				  ws.addCell(filedValue);
				  
				  Number labelNF = new jxl.write.Number(8, row, entry.getWorkHour(), wcfN);
				  ws.addCell(labelNF);
				  
				  filedValue=new Label(9,row,entry.getIsOTString(),bodyformat);
				  ws.addCell(filedValue);
				  
				  filedValue=new Label(10,row,entry.getNotes(),bodyformat);
				  ws.addCell(filedValue);
			
				  row++;
			  }
	      wwb.write();
	      wwb.close();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
  }
  
  
  

 
  // 测试
  public static void main(String[] args) {
    try {
      // 读Excel
      ExcelHandle.readExcel("d://数据报表.xls");
      // 输出Excel
      File fileWrite = new File("testWrite.xls");
      fileWrite.createNewFile();
      OutputStream os = new FileOutputStream(fileWrite);
      ExcelHandle.writeExcel(os);
      // 修改Excel
      // ExcelHandle.modifyExcel(new File(""), new File(""));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
}
