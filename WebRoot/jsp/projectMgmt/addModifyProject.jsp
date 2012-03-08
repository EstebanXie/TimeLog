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
  <title>Add/Modify Role</title>
  	<script src="js/Main.js" type="text/javascript"></script>
  	<script src="js/common.js" type="text/javascript"></script>
  	<script src="js/preventDoubleClick.js" type="text/javascript"></script>
	<script src="js/Calendar.js" charset="utf-8"></script>
  	<script src="js/jquery-1.6.2.min.js"></script>
  	<script src="js/jquery-ui-1.8.17.custom.min.js"></script>
    <script>
	function checkData()
	{
		if($("#project_Name").val()=="") {
			alert('Please input Project name！');
			$("#project_Name").focus();
			return false;
		}
		if($("#project_type").val()=="") {
			alert('Please Choose Project Type！');
			$("#project_type").focus();
			return false;
		}
		if($("#site_type").val()=="") {
			alert('Please Choose Site Type！');
			$("#site_type").focus();
			return false;
		}
		
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (startDate!='' && endDate!='')
		{
			if (new Date(Date.parse(startDate)) > new Date(Date.parse(endDate)))
			{
				alert('EndDate must after StartDate');
				return false;
			}
		}

		return true;
	}
    
  	function checkOnSubmit() {
  			$("#project_form").attr("action", "projectMgmt/addProject");
			return checkData();
  	}
	function checkOnModify() {
		
		$("#project_form").attr("action", "projectMgmt/modifyProject");
		return checkData();
		}
  	
  	$(function() {
		  $("#startDate" ).datepicker({ 
		   dateFormat: 'yy/mm/dd',
		   autoSize: true,
		   altField:'#actualDate'
		    });
	 });

  	$(function() {
		  $("#endDate" ).datepicker({ 
		   dateFormat: 'yy/mm/dd',
		   autoSize: true,
		   altField:'#actualDate'
		    });
	 });


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
    <s:text name="Add/Modify Role"/>
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
    <s:form name="projectForm" id="project_form"
            namespace="/projectMgmt" theme="simple" onSubmit="disableButton();">
     
     <table cellpadding="0" cellspacing="0" border="0">
       <tr>
        <th width="30%">Project Name<font color="red">*</font></th>
        <td>
         <s:textfield name="prjCriteria.projectName" id="project_Name" style="width:200px;" maxlength="150"/>
        </td>
       </tr>
       
      <tr>
       <th>Project Type<font color="red">*</font></th>
       <td>
        <s:select id="project_type" name="prjCriteria.projectTypeID" style="width:200px;"  list="projectTypes" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/>
       </td>
      </tr>
      
      <tr>
       <th>Site Type<font color="red">*</font></th>
       <td>
          <s:select id="site_type" name="prjCriteria.siteTypeID" style="width:200px;"  list="siteTypes" listKey="codeName" listValue="codeDesc" emptyOption="true" multiple="false"/>
       </td>
      </tr>
      
      <tr>
       <th>Start Date</th>
       <td>
          <s:textfield name="prjCriteria.startDate" id="startDate" readonly="true" style="width:200px;"/>
       </td>
      </tr>
      
      <tr>
       <th>End Date</th>
       <td>
          <s:textfield name="prjCriteria.enddate" id="endDate" readonly="true" style="width:200px;"/>
       </td>
      </tr>
       
      <tr align="center">
       <td colspan="2">
        <center>
         <s:if test="prjCriteria.projectID != null && prjCriteria.projectID != ''">
          <s:hidden name="prjCriteria.projectID"/>	
          <s:submit value="Modify" cssClass="TableBtn"  onclick="setCurrentElement(this);return checkOnModify();"/>
         </s:if>
         <s:else>
          <s:submit value="Add" cssClass="TableBtn" onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:else>
         &nbsp;&nbsp;&nbsp;
         <input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">
         &nbsp;&nbsp;&nbsp;
         <input type="button" value="Back" class="TableBtn" onclick="window.history.go(-1)"/>
         <!--<s:submit value="返回" cssClass="TableBtn"
                   action="listAllRoles" onclick="setCurrentElement(this);"/>-->
        </center>
       </td>
      </tr>
     </table>
    </s:form>
   </div>
  </div>
  <!-- pageName : addModifyRole.jsp -->
  <script type="text/javascript">
	
</script>
  
 </body>
</html>