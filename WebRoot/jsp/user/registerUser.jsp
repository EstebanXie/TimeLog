<%@ page language="java"  pageEncoding="gbk"%>
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
 		   Register User <br>
 		 <form method = "post" action = "user/addUser">
 		 	<table>
 		 		<tr>
 		 			<td>User Name:</td>
 		 			<td><input type = "text" name ="user.userName"></input></td>
 		 		</tr>
 		 		<tr>
 		 			<td>Password£º</td>
 		 			<td><input type = "password" name ="user.password"></input></td>
 		 		</tr>
 		 		<tr>
 		 			<td>Address£º</td>
 		 			<td><input type = "text" name ="user.address"></input></td>
 		 		</tr>
 		 		<tr>
 		 			<td colspan="2"><input type = "submit" value = "Submit"/></td>
 		 		</tr>
 		 	</table>
 		 	<br>
 		 </form>
 		 <a href="<%=basePath %>/index.jsp">Back</a>
 		 <!-- pageName : registerUser.jsp -->
  </body>
</html>
