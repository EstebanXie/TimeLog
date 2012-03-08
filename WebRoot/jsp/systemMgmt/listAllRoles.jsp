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
  <title>Role Management</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/dataInit.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  
  <script type="text/javascript">

  function query(pageNo,action) { //改form名称
		document.roleListForm["paging.pageNum"].value = pageNo;
	  var orgaction = document.roleListForm.action;
	  document.roleListForm.action = action;
		document.roleListForm.submit();
		document.roleListForm.action = orgaction;
	}
  function resetPagnum(){
	  document.roleListForm["paging.pageNum"].value=1;
  }
  </script>
  
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Role Management"/>
   </h1>
    
   <center>
     <h3>
    <font color="green">
      <s:actionmessage value="actionMessages"/>
    </font>
     </h3>
   </center>
    
   <br></br>
   <div class="ResultTable">
    <s:form action="sysMgmt/toAddModifyRole"
            name="roleListForm" theme="simple" onSubmit="disableButton();">
            
       <s:hidden name="paging.pageNum"/>
            
            
         <s:if test="roles != null && !roles.isEmpty()"> 
	         	<h1  >
	         		<div style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         			<s:property value="paging.pageNum"/>Page/<s:property value="paging.numOfPages"/>Pages &nbsp;
	         		</div>
	         		<!-- 
	         		<div class="ControlBar" style="width:<s:property value='#pageWidth'/>px">第<s:property value="paging.pageNum"/>页/共<s:property value="paging.numOfPages"/>页 &nbsp;</div>
	         		 -->
	         		 
	         		<s:if test="paging.numOfPages > 1">
	                <strong><a href="javascript:query(1,'sysMgmt/listAllRoles')">First page</a></strong>
	                <s:if test="paging.pageNum > 1">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum - 1"/>,'sysMgmt/listAllRoles')">Preview</a></strong>
	                </s:if>
	                <s:if test="paging.pageNum < paging.numOfPages">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum + 1"/>,'sysMgmt/listAllRoles')">Next</a></strong>
	                </s:if>
	                	|&nbsp;
	                <strong><a href="javascript:query(<s:property value="paging.numOfPages"/>,'sysMgmt/listAllRoles')">Last page</a></strong>
	            </s:if>
	         	</h1>
	     	</s:if>
	     	   
     <table cellpadding="0" cellspacing="0">
      <tr>
       <th width="5%">&nbsp;</th>
       <th width="20%">Role Name</th>
       <th width="75%">Remark</th>
      </tr>
       
      <s:iterator value="roles" var="role">
       <tr>
        <td>
         <s:checkbox name="roleIdArray"
                     fieldValue="%{#role.roleId}"
                     cssClass="checkBox"></s:checkbox>
        </td>
        <td>
         <s:property value="#role.roleName"/>
        </td>
        <td>
         <s:property value="#role.remark"/>
        </td>
       </tr>
      </s:iterator>
       
      <tr align="center">
       <td colspan="5">
        <center>
        <s:hidden name="operation"/>
         <s:submit value="Add Role" cssClass="TableBtn" onclick="javascript:setCurrentElement(this); roleListForm.operation.value='add';resetPagnum();"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Modify Role" cssClass="TableBtn"
                   action="toAddModifyRole"
                   onclick="setCurrentElement(this);resetPagnum();javascript: roleListForm.operation.value='edit'; return checkModify(roleListForm.roleIdArray);"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Delate Role" cssClass="TableBtn"
                   action="deleteRole"
                   onclick="setCurrentElement(this);resetPagnum();javascript: return checkDelete(roleListForm.roleIdArray);"/>
        </center>
       </td>
      </tr>
     </table>
     <br></br>
    </s:form>
   </div>
  </div>
  <!-- pageName : listAllRoles.jsp -->
  <script type="text/javascript">
	
</script>
 </body>
</html>