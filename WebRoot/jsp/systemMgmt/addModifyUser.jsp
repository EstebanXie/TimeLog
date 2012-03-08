<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
  <base href="<%=basePath%>"></base>
  <title>Add/Modify User</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
    <script>
  	function checkOnSubmit() {
			if(document.userForm["editUser.userName"].value == '') {
				alert('Please input User name(logon name)！');
				return false;
			}

			<s:if test="editUser.userId != null && editUser.userId != ''">
			
			</s:if>
			<s:else>
			if(document.userForm["editUser.password"].value == '') {
				alert('Please input password！');
				return false;
			}
			</s:else>
			
			return true;
  	}
  	</script>
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Add/Modify User"/>
   </h1>
    
   <center>
    <font color="error">
     <h3>
      <s:actionmessage value="actionMessages"/>
     </h3>
    </font>
   </center>
   <br/>
   <div class="AddTable">
    <s:form action="addModifyUser"
    				name="userForm"
            namespace="/user" theme="simple" onSubmit="disableButton();">
     <s:hidden name="editUser.userId"/>
     <table cellpadding="0" cellspacing="0" border="0">
       <tr>
        <th width="200">User Name(Logon name)<font color="red">*</font></th>
        <td>
         <s:textfield name="editUser.userName" maxlength="25"/>
        </td>
       </tr>
       
       <tr>
        <th>
        	Password<s:if test="editUser.userId != null && editUser.userId != ''"></s:if><s:else><font color="red">*</font></s:else>
        </th>
        <td>
         <s:password name="editUser.password" maxlength="30"/>
        </td>
       </tr>
       
       <tr>
        <th>User Name</th>
        <td>
         <s:textfield name="editUser.realName"/>
        </td>
       </tr>
    
       
       <tr>
        <th>Role<font color="red">*</font></th>
        <td>
         <s:select name="editUser.role.roleId" list="roles" listKey="roleId" 
              listValue="roleName" required="true"/> 
        </td>
       </tr>
       
      <tr align="center">
       <td colspan="5">
        <center>
         <s:if test="editUser.userId != null && editUser.userId != ''">
          <s:submit value="Modify" cssClass="TableBtn" onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:if>
         <s:else>
          <s:submit value="Add" cssClass="TableBtn" onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:else>
         &nbsp;&nbsp;&nbsp;
         <input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">
         &nbsp;&nbsp;&nbsp;
         <input type="button" value="Back" class="TableBtn" onclick="window.history.go(-1)"/>
         <!--<s:submit value="返回" cssClass="TableBtn"
                   action="listAllUsers" onclick="setCurrentElement(this);"/>-->
        </center>
       </td>
      </tr>
     </table>
    </s:form>
   </div>
  </div>
  <!-- pageName : addModifyUser.jsp -->
  <script type="text/javascript">
	
</script>
 </body>
</html>