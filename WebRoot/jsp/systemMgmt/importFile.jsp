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
		//��ȡ���ϴ����ļ�·��  
		var filepath = str;
		//Ϊ�˱���ת�巴б�ܳ����⣬���ｫ�������ת��  
		var re = /(\\+)/g;
		var filename = filepath.replace(re, "#");
		//��·���ַ������м��н�ȡ  
		var one = filename.split("#");
		//��ȡ���������һ�������ļ���  
		var two = one[one.length - 1];
		//�ٶ��ļ������н�ȡ����ȡ�ú�׺��  
		var three = two.split(".");
		//��ȡ��ȡ�����һ���ַ�������Ϊ��׺��  
		var last = three[three.length - 1];
		//�����Ҫ�жϵĺ�׺������  
		var tp = "xls,xlsx";
		//var tp ="jpg,gif,bmp,JPG,GIF,BMP";   

		//���ط��������ĺ�׺�����ַ����е�λ��  
		var rs = tp.indexOf(last);
		//������صĽ�����ڻ����0��˵�����������ϴ����ļ�����  
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