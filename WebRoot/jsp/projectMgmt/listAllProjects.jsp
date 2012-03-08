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
  <title>Project Management</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/dataInit.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  <script src="js/Calendar.js" charset="utf-8"></script>
  <script type="text/javascript">

  function query(pageNo,action) { //改form名称
		document.projectListForm["paging.pageNum"].value = pageNo;
	  var orgaction = document.projectListForm.action;
	  document.projectListForm.action = action;
		document.projectListForm.submit();
		document.projectListForm.action = orgaction;
	}
  function resetPagnum(){
	  document.projectListForm["paging.pageNum"].value=1;
  }
  function toAddPrj(){
	  document.projectListForm.action = "projectMgmt/toAddProjects";
	  //document.projectListForm.submit();
	}

  </script>
  
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Project Management"/>
   </h1>
    
   <center>
     <h3>
    <font color="green">
      <s:actionmessage value="actionMessages"/>
    </font>
     </h3>
   </center>
    
   <div class="ResultTable">
    <s:form action="projectMgmt/listAllProjects"
            name="projectListForm" theme="simple" onSubmit="disableButton();">
            <h1>Search Criteria </h1>
	
      <table cellpadding="0" cellspacing="0">
      	<tr>
      		<td>Project Name</td>
      		<td><s:textfield name="prjCriteria.projectName"/> </td>
      		<td>Project Type</td>
      		<td><s:select name="prjCriteria.projectTypeID" list="projectTypes" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      	</tr>
      	
   		<tr>
		   	<td colspan="4">
		       <center>
		        <s:submit value="Search " onclick="query(1, 'projectMgmt/listAllProjects')" cssClass="TableBtn" />&nbsp;&nbsp;&nbsp;
				<input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">&nbsp;&nbsp;&nbsp;
		       </center>
       		</td>
       </tr>
   	</table>
            <br>
           <div class="ResultTable" >		 
       <s:hidden name="paging.pageNum"/>
            
            
         <s:if test="projects != null && !projects.isEmpty()"> 
	         	<h1  >
	         		<div style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         			<s:property value="paging.pageNum"/>Page/<s:property value="paging.numOfPages"/>Pages &nbsp;
	         		</div>
	         		<!-- 
	         		<div class="ControlBar" style="width:<s:property value='#pageWidth'/>px">第<s:property value="paging.pageNum"/>页/共<s:property value="paging.numOfPages"/>页 &nbsp;</div>
	         		 -->
	         		 
	         		<s:if test="paging.numOfPages > 1">
	                <strong><a href="javascript:query(1,'projectMgmt/listAllProjects')">First page</a></strong>
	                <s:if test="paging.pageNum > 1">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum - 1"/>,'projectMgmt/listAllProjects')">Preview</a></strong>
	                </s:if>
	                <s:if test="paging.pageNum < paging.numOfPages">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum + 1"/>,'projectMgmt/listAllProjects')">Next</a></strong>
	                </s:if>
	                	|&nbsp;
	                <strong><a href="javascript:query(<s:property value="paging.numOfPages"/>,'projectMgmt/listAllProjects')">Last page</a></strong>
	            </s:if>
	         	</h1>
	     	</s:if>
	     	   
     <table cellpadding="0" cellspacing="0">
      <tr align="left">
       <th width="5%">&nbsp;</th>
       <th width="30%">Project Name</th>
       <th width="25%">Project Type</th>
       <th width="20%">Start Date</th>
       <th width="20%">End Date</th>
      </tr>
       
      <s:iterator value="projects" var="project">
       <tr>
        <td>
         <s:checkbox name="projectIdArray"
                     fieldValue="%{#project.projectID}"
                     cssClass="checkBox"></s:checkbox>
        </td>
        <td>
         <s:property value="#project.projectName"/>
        </td>
        <td>
         <s:property value="#project.projectTypeName"/>
        </td>
        <td>
         <s:date name="#project.startDate" format="yyyy-MM-dd"/>
        </td>
        <td>
         <s:date name="#project.enddate" format="yyyy-MM-dd"/>
        </td>
       </tr>
      </s:iterator>
       
      <tr align="center">
       <td colspan="5">
        <center>
        <s:hidden name="operation"/>
         <s:submit value="Add Project" cssClass="TableBtn" 
         			onclick="javascript:setCurrentElement(this); projectListForm.operation.value='add';resetPagnum();toAddPrj();"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Modify Project" cssClass="TableBtn"       
         		   action="toModifyProject"        
                   onclick="setCurrentElement(this);resetPagnum();javascript: projectListForm.operation.value='edit'; return checkModify(projectListForm.projectIdArray);"/>
         &nbsp;&nbsp;&nbsp;
         <%-- 
         <s:submit value="Delate Project" cssClass="TableBtn"
                   action="deleteRole"
                   onclick="setCurrentElement(this);resetPagnum();javascript: return checkDelete(projectListForm.projectIdArray);"/>
        --%>
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