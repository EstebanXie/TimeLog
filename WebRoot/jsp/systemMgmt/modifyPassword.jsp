<%@ page language="java" contentType="text/html; charset=GB2312"
         pageEncoding="GB2312"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
  <base href="<%=basePath%>"></base>
  <title>Modify Password</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  <script>
  function modifyPwd()
  {
	  var oldPwd=document.pwdForm["oldPwd"].value;
	  var newPwd1=document.pwdForm["newPwd1"].value;
	  var newPwd2=document.pwdForm["newPwd2"].value;
	  if(oldPwd=='')
	  {
		  alert("Please input old password£¡");
		  document.pwdForm["oldPwd"].focus();
		  return false;
	  }
	  else if(newPwd1=='')
	  {
		  alert("Please input new password");
		  document.pwdForm["newPwd1"].focus();
		  return false;
	  }
	  else if(newPwd2=='')
	  {
		  alert("repeat new password");
		  document.pwdForm["newPwd2"].focus();
		  return false;
	  }
	  else if(newPwd1!=newPwd2)
	  {
		  alert("new passwords don't match£¡");
		  document.pwdForm["newPwd2"].focus();
		  return false;
	  }
	  return true;
  }
  </script>
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Modify Password"/>
   </h1>
    
  <center><font color="green"><s:actionmessage value="actionMessages"/></font></center>
	 <font color="red">
	 	<center>
			<s:fielderror fieldName="errorMsg" theme="simple"/>
		</center>
	</font>
   <div class="AddTable">
    <s:form action="modifyLoginPwd" namespace="/sysMgmt" 
            name="pwdForm" theme="simple" onSubmit="disableButton();">
    
     <table cellpadding="0" cellspacing="0" border="0">
       <tr>
        <th width="200">Old Password<font color="red">*</font></th>
        <td>
         <s:password name="oldPwd" maxlength="30"  theme="simple"/>
        </td>
       </tr>
       
       <tr>
        <th>New Password<font color="red">*</font></th>
        <td>
        <s:password name="newPwd1" maxlength="30"  theme="simple"/>
        </td>
       </tr>
       
       <tr>
        <th>Repeat new password<font color="red">*</font></th>
        <td>
        <s:password name="newPwd2" maxlength="30"  theme="simple"/>
        </td>
       </tr>
       
       
      <tr align="center">
       <td colspan="5">
        <center>
         <s:submit value="Modify" cssClass="TableBtn" onclick="setCurrentElement(this);return modifyPwd();" action="modifyLoginPwd"/>
         <input type="button" value="Reset" class="TableBtn" onclick="return clearForm();" />
        </center>
       </td>
      </tr>
     </table>
    </s:form>
   </div>
  </div>
  <!-- pageName : modifyPassword.jsp -->
  <script type="text/javascript">
	
</script>
 </body>
</html>