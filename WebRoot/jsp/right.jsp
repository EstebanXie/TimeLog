<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/> 
<title>Mockup</title>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="<%=basePath%>style/common.css"/>
<script src="js/date.format.js" type="text/javascript"></script>
<script type="text/javascript">
function entryTimeDataInputPage() {
    document.rightForm["enterDateSelected"].value = new Date().format("yyyy/mm/dd");
	document.rightForm.action="dataMgmt/addTimeLogEnter";
	document.rightForm.submit();
}
</script>
</head>

<body class="RightBG" onload="entryTimeDataInputPage()">
<s:form method="POST"  name="rightForm">
   <s:hidden name="enterDateSelected"  />
</s:form>
</body>
</html>
