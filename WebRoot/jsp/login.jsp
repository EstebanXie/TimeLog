<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TimeLog</title>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="style/common.css"/>
<script language="javascript" type="text/javascript">
	if (top.location != self.location){
		top.location=self.location;     
	} 
	
</script>
</head>

<body class="LoginBG">
<form action="user/login" method="post" name="loginForm" id="loginForm">
<div class="LoginWarp">
  <div class="LoginBox">
  	<h1>
      <font color="red">
      	<s:fielderror fieldName="loginError" theme="simple"/>
      </font>

  	</h1>
    <dl>
      <dt>UserName:</dt>
      <dd>
        <input type="text" name = "user.userName" size="20" />
      </dd>
      <dt>Password:</dt>
      <dd>
        <input type="password" name = "user.password" size="20" />
      </dd>
      <dd class="LoginBtn"><input type="image" src="<%=basePath%>images/LogBtn.gif" onclick="javascript: document.loginForm.submit();"/></dd>
   	</dl>
  </div>
</div>
</form>
</body>
</html>
