<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TimeLog</title>
<base href="<%=basePath%>"/>
<link href="style/common.css" rel="stylesheet" type="text/css" />
</head>
<frameset rows="149 , * , 87" frameborder="0" framespacing="0" >
	<frame src="jsp/head.jsp" noresize="noresize" scrolling="no"/>
	<frame name="body" src="jsp/body.jsp" noresize="noresize" scrolling="no"/>
	<frame src="jsp/bottom.jsp" noresize="noresize" scrolling="no"/>
</frameset>
<noframes></noframes>
</html>
