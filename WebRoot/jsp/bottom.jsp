<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>head</title>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="<%=basePath%>style/common.css"/>

</head>

<body class="BottomBG">
<div class="Warp">
<div class="Bottom"></div>
</div></body>
</html>
