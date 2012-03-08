<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html  xmlns="http://www.w3.org/1999/xhtml">
  <head>
   
    
    <title>My JSP 'index.jsp' starting page</title>
     <base href="<%=basePath%>"/>
	<link rel="stylesheet" href="style/common.css"/>
  </head>

  <body class="RightBG">
  <div class="RightMain">
  	<dl class="ErrArea">
  	<dt><img src="images/err.jpg" /></dt>
  	<br/>
 		<p><b>抱歉！系统出错了。请联系系统管理员，谢谢。</b></p>
 	</dl>
  </div>
  </body>
</html>
