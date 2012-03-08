<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>Insert title here</title>
<base href="<%=basePath%>" />
<link rel="stylesheet" href="style/common.css"/>
<script language="JavaScript">
	function ImportFile() {
		var s = document.getElementById("excelFile");
		//s.click();
		var str = s.value;
		if(str==null || str==""){
			return false;
		}
		//获取欲上传的文件路径  
		var filepath = str;
		//为了避免转义反斜杠出问题，这里将对其进行转换  
		var re = /(\\+)/g;
		var filename = filepath.replace(re, "#");
		//对路径字符串进行剪切截取  
		var one = filename.split("#");
		//获取数组中最后一个，即文件名  
		var two = one[one.length - 1];
		//再对文件名进行截取，以取得后缀名  
		var three = two.split(".");
		//获取截取的最后一个字符串，即为后缀名  
		var last = three[three.length - 1];
		//添加需要判断的后缀名类型  
		var tp = "xls,xlsx";
		//var tp ="jpg,gif,bmp,JPG,GIF,BMP";   

		//返回符合条件的后缀名在字符串中的位置  
		var rs = tp.indexOf(last);
		//如果返回的结果大于或等于0，说明包含允许上传的文件类型  
		if (rs < 0) {
			alert("Please choose the Excel file!");
			return false;
		}
		//var importForm = document.getElementById("importForm");
		//document.forms[0].method = "POST";
		//document.forms[0].action="../dataMgmt/importFile?excelFileName=" + str;
		//document.forms[0].submit();
		//window.location.href = "../dataMgmt/importFile?excelFileName=" + str;
	}
</script>
</head>
<body>

	<s:form id="importForm" name="importForm" method="post"
		enctype="multipart/form-data" action="dataMgmt/importFile"
		onsubmit="return ImportFile();">
		<s:file name="excelFile" id="excelFile" cssClass="TableBtn"/>
		<s:submit value="Import" cssClass="TableBtn"/>
	</s:form>
	<s:form name="form1" namespace="/dataMgmt" action="exportFile"
		method="post">
		<input type="hidden" name="format" value="xls" />
		<s:submit name="sub" value="Export" cssClass="TableBtn"></s:submit>
	</s:form>
	<p>
	<div id="importMsg">${importStatus}</div>
	<h1>
		<s:iterator var="excelWorkSheet" value="excelWorkSheets">
			<s:property value="#excelWorkSheet.sheetName" />
		</s:iterator>
	</h1>
	<s:iterator var="excelWorkSheet" value="excelWorkSheets">
		<s:iterator value="#excelWorkSheet.columns">
			<s:property />  ||    
        </s:iterator>
	</s:iterator>
	<p>
		<s:iterator var="excelWorkSheet" value="excelWorkSheets">
			<s:iterator var="taskVO" value="#excelWorkSheet.data">
				<p>
					<s:property value="#taskVO.ticketNumber" />
					<s:property value="#taskVO.clientName" />
					<s:property value="#taskVO.subject" />
					<s:property value="#taskVO.state" />
					<s:property value="#taskVO.priority" />
					<s:property value="#taskVO.dueDate" />
				</p>
			</s:iterator>
		</s:iterator>
</body>
</html>