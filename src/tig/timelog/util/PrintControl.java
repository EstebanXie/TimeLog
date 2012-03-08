package tig.timelog.util;

import java.io.IOException;

public class PrintControl {
	public String print(int width, int fontSize, String value, double x, double y, String font) throws IOException{
		return "<div class=\"moveme\" style=\"color:#000000; FONT-FAMILY:楷体_GB2312;position: absolute; text-align:left; width: "+width+"px; " +
				"height: 15px; z-index: 3; left: "+x+"px; top: "+y+"px\" id=\"layer\"><p align=\"left\"><font style=\"COLOR:#000000;FONT-SIZE:"
				+fontSize+"pt;FONT-FAMILY:楷体_GB2312;FONT-WEIGHT:BOLD;\">"+value+"</font></div>";
	}
	public String printDate(int width, int fontSize, String value, double x, double y,double letterSpace) throws IOException{
		return "<div class=\"moveme\" style=\"color:#000000; FONT-FAMILY:楷体_GB2312;position: absolute; text-align:left; width: "+width+"px; " +
				"height: 15px; z-index: 3; left: "+x+"px; top: "+y+"px\" id=\"layer\"><p align=\"left\"><font style=\"COLOR:#000000;FONT-SIZE:"
				+fontSize+"pt;FONT-FAMILY:楷体_GB2312;letter-spacing: "+letterSpace+"px;FONT-WEIGHT:BOLD;\">"+value+"</font></div>";
	}
	public String printTitle(int width, int fontSize, String value, double x, double y, double letterSpace){
		return "<div class=\"moveme\" style=\"color:#000000; FONT-FAMILY:宋体_GB2312;float:left; position: absolute;float:left; text-align:left; " +
				"width: "+width+"px; height: 15px; z-index: 3; left: "+x+"px; top: "+y+"px;\" id=\"layer\"><p align=\"left\">" +
						"<font style=\"COLOR:#000000;FONT-SIZE:"+fontSize+"pt;FONT-FAMILY:宋体_GB2312;letter-spacing: "+letterSpace+"px;FONT-WEIGHT:900; \">"
						+value+"</font></div>";
	}
}
