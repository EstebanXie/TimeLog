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
  <title>User Management</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/dataInit.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  
  <script type="text/javascript">
 	 function query(pageNo,action) { 
		document.userListForm["paging.pageNum"].value = pageNo;
	  var orgaction = document.userListForm.action;
	  document.userListForm.action = action;
		document.userListForm.submit();
		document.userListForm.action = orgaction;
	}
 	 function resetPagnum(){
 		  document.userListForm["paging.pageNum"].value=1;
 	  }
  
  </script>
  
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="User Management"/>
   </h1>
    
   <center>
    <font color="green">
     <h3>
      <s:actionmessage value="actionMessages"/>
     </h3>
    </font>
   </center>
    
   <br></br>
   <div class="ResultTable">
    <s:form action="user/toAddModifyUser"
            name="userListForm" theme="simple" onSubmit="disableButton();">
     <s:hidden name="paging.pageNum"/>       
            
        <s:if test="users != null && !users.isEmpty()"> 
	         	<h1  >
	         		<div class="Tit" style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         			<s:property value="paging.pageNum"/>page/Total <s:property value="paging.numOfPages"/> Pages &nbsp;
	         		</div>
	         		<!-- 
	         		<div class="ControlBar" style="width:<s:property value='#pageWidth'/>px">第<s:property value="paging.pageNum"/>页/共<s:property value="paging.numOfPages"/>页 &nbsp;</div>
	         		 -->
	         		 
	         		<s:if test="paging.numOfPages > 1">
	                <strong><a href="javascript:query(1,'user/listAllUsers')">First pgae</a></strong>
	                <s:if test="paging.pageNum > 1">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum - 1"/>,'user/listAllUsers')">preview</a></strong>
	                </s:if>
	                <s:if test="paging.pageNum < paging.numOfPages">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum + 1"/>,'user/listAllUsers')">next</a></strong>
	                </s:if>
	                	|&nbsp;
	                <strong><a href="javascript:query(<s:property value="paging.numOfPages"/>,'user/listAllUsers')">Last page</a></strong>
	            </s:if>
	         	</h1>
	     	</s:if>    
            
     <table cellpadding="0" cellspacing="0">
      <tr>
       <th width="5%">&nbsp;</th>
       <th width="20%">User Name（Logon name）</th>
       <th width="20%">User Name</th>
       <th width="20%">Role</th>
      </tr>
       
      <s:iterator value="users" var="user">
       <tr>
        <td>
         <s:checkbox name="userIdArray"
                     fieldValue="%{#user.userId}"
                     cssClass="checkBox"></s:checkbox>
        </td>
        <td>
         <s:property value="#user.userName"/>
        </td>
        <td>
         <s:property value="#user.realName"/>
        </td>
        <td>
         <s:property value="#user.role.roleName"/>
        </td>
       </tr>
      </s:iterator>
       
      <tr align="center">
       <td colspan="5">
        <center>
        <s:hidden name="operation"/>
         <s:submit value="Add User" cssClass="TableBtn" onclick="setCurrentElement(this);resetPagnum();javascript: userListForm.operation.value='add';"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Modify User" cssClass="TableBtn"
                   action="toAddModifyUser"
                   onclick="javascript:setCurrentElement(this); userListForm.operation.value='edit';resetPagnum(); return checkModify(userListForm.userIdArray)"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Delete Name" cssClass="TableBtn"
                   action="deleteUser"
                   onclick="setCurrentElement(this);resetPagnum();javascript: return checkDelete(userListForm.userIdArray)"/>
        </center>
       </td>
      </tr>
     </table>
     <br></br>
    </s:form>
   </div>
  </div>
  <!-- pageName : listAllUsers.jsp -->
  <script type="text/javascript">
	
</script>
 </body>
</html>