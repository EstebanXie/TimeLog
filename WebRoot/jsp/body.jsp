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
</head>

<frameset cols="*, 180,800, *" frameborder="0" framespacing="0">
	<frame src="jsp/space.jsp" scrolling="no"  noresize="noresize"/>
	<frame name="left" src="jsp/left.jsp" scrolling="no"  noresize="noresize"/>
    <frame name="right" src="jsp/right.jsp" noresize="noresize"/>
    <frame src="jsp/space.jsp" scrolling="no"  noresize="noresize"/>
</frameset>
<noframes></noframes>
</html>
