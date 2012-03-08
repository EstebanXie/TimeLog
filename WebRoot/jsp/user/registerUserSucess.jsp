<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
 		Added user successful
 		<br>
 		User Name£º<s:property value="user.userName"/> 
 		<br>
 		Address£º<s:property value="user.address"/>  
 		
 		 <br>
 		 <s:debug></s:debug>
 		 <br>
 		  <a href="<%=basePath %>/index.jsp">Back</a>
 		  <!-- pageName : registeruserSuccess.jsp -->
  </body>
</html>
