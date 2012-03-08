<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="GB2312"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>Insert title here</title>
	<base href="<%=basePath%>">
	<title>All Info Enquiry</title>
	<script src="js/Main.js" type="text/javascript"></script>
  	<script src="js/jquery.js" type="text/javascript"></script>
  	<script src="js/common.js" type="text/javascript"></script>
  	<script src="js/preventDoubleClick.js" type="text/javascript"></script>
	<script src="js/Calendar.js" charset="utf-8"></script>
	<script src="js/jquery.js" type="text/javascript"></script>
  	<script src="js/jquery-1.6.2.min.js"></script>
  	<script src="js/jquery-ui-1.8.17.custom.min.js"></script>
  	<script type="text/javascript">
	  $(function() {
		  $("#beginDate" ).datepicker({ 
		   dateFormat: 'yy/mm/dd',
		   autoSize: true,
		   altField:'#actualDate'
		    });
	 });
	 </script>
	 <script type="text/javascript">
	  $(function() {
		  $("#endDate" ).datepicker({ 
		   dateFormat: 'yy/mm/dd',
		   autoSize: true,
		   altField:'#actualDate'
		    });
	 });
 	</script>
	<script language="javascript" type="text/javascript">

		function checkForm(){

		}
		function query(pageNo) {
	    	document.allQueryInfo["paging.pageNum"].value = pageNo;
	    	document.allQueryInfo.submit();
	    }
  	</script>
	<style type="text/css">
	 	.ui-datepicker { width: 17em; padding: .2em .2em 0; font-size: 15px;}  /*覆盖了jquery-ui-1.8.13.custom.css中的.ui-datepicker，用于调整大小*/
	</style>
  	<link rel="stylesheet" href="style/common.css"/>
  	<link href="css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="All Info Enquiry"/>
   </h1>
    
   <center>
    <font color="green">
      <s:actionmessage value="actionMessages"/>
    </font>
   </center>
    
   <div class="ResultTable">
    <s:form action="allInfoQuery" name="allQueryInfo" theme="simple" namespace="/systemQuery" onSubmit="disableButton();">
       <s:hidden name="paging.pageNum"/>	 
       <h1>Search Criteria </h1>
	
      <table cellpadding="0" cellspacing="0">
      	<tr>
      		<td>Resource Name</td>
      		<td><s:select name="userId" list="userList" listKey="userId" listValue="userName" emptyOption="true" multiple="false"/></td>
      		<td>Project Type</td>
      		<td><s:select name="projectCode" list="projectTypeList" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      	</tr>
      	<tr>
      		<td>Project Name</td>
      		<td><s:textfield name="projectName" label="Project Name"/></td>
      		<td>Site Type</td>
      		<td><s:select name="siteType" list="siteList" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      	</tr>
      	<tr>
      		<td>Work</td>
      		<td><s:select name="workCode" list="workList" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      		<td>Tasks</td>
      		<td><s:select name="taskCode" list="taskList" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      	</tr>
      	<tr>
      		<td>Ticket Number</td>
      		<td><s:textfield name="ticketNumber" label="Ticket Number"/></td>
      		<td>isOT</td>
      		<td><s:select name="isOT" list="isOTList" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/></td>
      	</tr>
      	<tr>
      		<td>Begin Date</td>
      		<td><s:textfield name="beginDate" id="beginDate" readonly="true"/></td>
      		<td>End Date</td>
      		<td><s:textfield name="endDate" id="endDate" readonly="true"/></td>
      	</tr>
   		<tr>
		   	<td colspan="4">
		       <center>
		        <s:submit value="Search " onclick="checkForm()" cssClass="TableBtn" />&nbsp;&nbsp;&nbsp;
				<input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">&nbsp;&nbsp;&nbsp;
		       </center>
       		</td>
       </tr>
   	</table>
   	<div class="ResultTable" >		
		<div  style="overflow-x:auto;overflow-y:auto;" >
		 <s:if test="timeEntryList != null && !timeEntryList.isEmpty()">
         	<h1>
         		<div style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         			<s:property value="paging.pageNum"/>Page/Total <s:property value="paging.numOfPages"/> Pages &nbsp;
	         		</div>
         		<s:if test="paging.numOfPages > 1">
                <strong><a href="javascript:query(1)">First page</a></strong>
                <s:if test="paging.pageNum > 1">
                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum - 1"/>)">Preview</a></strong>
                </s:if>
                <s:if test="paging.pageNum < paging.numOfPages">
                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum + 1"/>)">Next</a></strong>
                </s:if>
                	|&nbsp;
                <strong><a href="javascript:query(<s:property value="paging.numOfPages"/>)">Last page</a></strong>
            </s:if>
         	</h1>
     	
        <table cellpadding="0" cellspacing="0" >
              <tr>
                	<th width="100px">Work Time</th>
                	<th width="100px">Resource Name</th>
                	<th width="100px">Project</th>
                	<th width="100px">Project Type</th>
                	<th width="100px">Site Type</th>
                	<th width="100px">Work Type</th>
                	<th width="100px">Task</th>
                	<th width="100px">Ticket Number</th>
                	<th width="100px">Work Hours</th>
                	<th width="100px">IsOT</th>
                	<th width="120px">Note</th>
              </tr>
              
              <s:iterator value="timeEntryList" var="record" >
              	<tr>
              		<td><s:property value="#record.workTime"/></td>
              		<td><s:property value="#record.userNameString"/></td>
              		<td><s:property value="#record.projectString"/></td>
              		<td><s:property value="#record.projectTypeString"/></td>
              		<td><s:property value="#record.siteString"/></td>
              		<td><s:property value="#record.workString"/></td>
              		<td><s:property value="#record.taskString"/></td>
              		<td><s:property value="#record.ticketNumber"/></td>
              		<td><s:property value="#record.workHour"/></td>
              		<td><s:property value="#record.isOTString"/></td>
              		<td><s:property value="#record.notes"/></td>
           		</tr>
              </s:iterator>
          </table>
          		
          <table>
          	<tr>
          		<td> <center>
          			<s:submit value="Export" cssClass="TableBtn" action="exportAllInfoResult" onclick="clearCurrentElement(this);"/> 
          		</center></td>
          	</tr>
          </table>
     	</s:if>
       </div>	
          
  	</div>
    </s:form>
   </div>
  </div>
  
  
  <!-- pageName : allInfoQuery.jsp -->

 </body>
</html>