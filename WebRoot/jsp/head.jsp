<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<title>head</title>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="<%=basePath%>style/common.css"/>
</head>

<body>
<div class="Warp">
	<div class="Head">  </div>
    <div class="HeadNav">
    	<div class="UserInfo">
    		<s:property value="#session.__CURRENT_LOGINED_USER_CONTEXT.realName"/>£¬Welcome! 
    		&nbsp;&nbsp;today is: <s:set var="today" value="new java.util.Date()"/>
    		<s:date name="#today" format="yyyy-MM-dd E "/>
    		<a href="user/logout" target="_top" class="Logout">Logout</a>
    		
   		</div>
      <div class="HeadMenu"></div></div></div>
</body>
</html>
