<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>Time Log Edit</title>
<base href="<%=basePath%>"/>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/preventDoubleClick.js" type="text/javascript"></script>
<script src="js/DataMgmt.js" type="text/javascript"></script>



<s:if test="timeLogEntries != null && !timeLogEntries.isEmpty()">
	<s:iterator value="timeLogEntries" status="stat">
	<script type="text/javascript">
	       $(document).ready(function(){
	           
	            var elemts = document.getElementsByName("timeLogEntries[<s:property value="#stat.index"/>].projectId");
	       		var elemtsTask = document.getElementsByName("timeLogEntries[<s:property value="#stat.index"/>].taskId");
	       		var elemtsWork =  document.getElementsByName("timeLogEntries[<s:property value="#stat.index"/>].workTypeId");
	       			
	           $("#entryProjectType<s:property value="#stat.index"/>").change(function(){
	               $("#entryProjectType<s:property value="#stat.index"/> option").each(function(i,o){
	                  
	                   if($(this).attr("selected"))
	                   {
	                       $(elemts).hide();
	                       $(elemts).attr("disabled","true");
	                       $(elemts).eq(i).show();
	                       enable_td_by_project(elemts[i],<s:property value="#stat.index"/>);
	                       $(elemts).eq(i).attr("disabled","");
	                      if($(this).val()=='EM'||$(this).val()=='CM'){
	                       		$(elemtsTask).eq(1).attr("disabled","");
	                       		$(elemtsTask).eq(0).attr("disabled","true");
	                       		$(elemtsTask).eq(0).hide();
	                       		$(elemtsTask).eq(1).show();
	                      }else{
	                       		$(elemtsTask).eq(0).attr("disabled","");
	                       		$(elemtsTask).eq(0).show();
	                       		$(elemtsTask).eq(1).hide();
	                       		$(elemtsTask).eq(1).attr("disabled","true");
	                      }
	                      if(!($(elemtsWork).val()=='D')){
	                       		$(elemtsTask).eq(0).attr("disabled","true");
	                       		$(elemtsTask).eq(1).attr("disabled","true");
	                       }
	                      
	                   }
	               });
	           });
	           
	           
	           $("#entryProjectType<s:property value="#stat.index"/>").change();
	       });
	       
	       $(document).ready(function(){
	       		var elemts = document.getElementsByName("timeLogEntries[<s:property value="#stat.index"/>].taskId");
	       		 $("#entryProjectType<s:property value="#stat.index"/>").change(function(){
	       		 	$("#entryProjectType<s:property value="#stat.index"/> option").each(function(i,o){
	                
	                   if($(this).attr("selected"))
	                   {
	                   	 if($(this).val()=='EM'||$(this).val()=='CM'){
	                   	 		$(elemts).eq(0).css("display","none");
		                      	$(elemts).eq(1).css("display","block");
		                       	$(elemts).eq(1).attr("disabled","");
	                       }else{
	                       		$(elemts).eq(1).css("display","none");
		                      	$(elemts).eq(0).css("display","block");
		                       	$(elemts).eq(0).attr("disabled","");
	                       }
	                      
	                      
	                   }
	               });
	               });
	       });
	      
	</script>
    </s:iterator>
</s:if>



<script type="text/javascript">

function enable_td_by_project(obj,index){
	var pro_value = obj.value;

	if(pro_value != ""){
		var tr_ele = obj.parentElement.parentElement;
		
		enable_all_td(tr_ele,index);
	}else{
		var tr_ele = obj.parentElement.parentElement;
		disable_all_td(tr_ele);
	}
}


function enable_all_td(obj_tr,index){
	$(obj_tr.cells[3].firstChild).attr("disabled","");
	
	
	check_task_type(obj_tr.cells[3].firstChild,index);
	$(obj_tr.cells[5].firstChild).attr("disabled","");
    $(obj_tr.cells[6].firstChild).attr("disabled","");
	$(obj_tr.cells[7].firstChild).attr("disabled","");
	$(obj_tr.cells[8].firstChild).attr("disabled","");
	
}

function disable_all_td(obj_tr){
	$(obj_tr.cells[3].firstChild).attr("disabled","true");
	$(obj_tr.cells[4].firstChild).attr("disabled","true");
	$(obj_tr.cells[5].firstChild).attr("disabled","true");
	$(obj_tr.cells[6].firstChild).attr("disabled","true");
	$(obj_tr.cells[7].firstChild).attr("disabled","true");
	$(obj_tr.cells[8].firstChild).attr("disabled","true");
}


function check_task_type(obj,index){
	var elementName = "timeLogEntries["+index+"].projectTypeId";
	var projectTypeVal = document.getElementsByName(elementName)[0].value;
	var elemtTask = document.getElementsByName("timeLogEntries["+index+"].taskId");
	var pro_value = obj.value;
	
	if(pro_value == "D"){
		if(projectTypeVal=='EM'||projectTypeVal=='CM'){
			$(elemtTask).eq(1).attr("disabled","");
		}else{
			$(elemtTask).eq(0).attr("disabled","");
			}
	}else{
		$(elemtTask).eq(0).attr("disabled","true");
		$(elemtTask).eq(1).attr("disabled","true");
	}
}

</script>
<link rel="stylesheet" href="style/common.css"/>
</head>
<body class="RightBG">
<div class="RightMain">
	<h1><s:text name="Time Log Edit"/></h1>
	<center><font color="green"><h3><s:actionmessage value="actionMessages"/></h3></font></center>
	<center><font color="error"><h3><s:actionerror value="actionErrors"/></h3></font></center>
	<br>
	<div class="AddTable">
	
	    <s:property value="enterDateSelected"/>
		<s:form action="" namespace="/dataMgmt" name="personAgtDataInputForm" method="post" theme="simple" onSubmit="disableButton();">
          <s:hidden name="enterDateSelected"/>
          <table cellpadding="0" cellspacing="0" id="timeLogItems">
             <tr>
              	<th></th>
              	<th><center>project type</center></th>
                <th><center>project</center></th>
               
                <th><center>work type</center></th>
                <th><center >task type</center></th>
                <th><center>ticket number</center></th>
                <th><center>work hour</center></th>
                <th><center>OT</center></th>
                <th><center>notes</center></th>
             </tr>
              <s:if test="timeLogEntries != null && !timeLogEntries.isEmpty()">
              	<s:iterator value="timeLogEntries" status="stat" var="record">
	              	<tr>
	              		<td><s:checkbox name="%{'timeLogEntries['+#stat.index+'].deleteF'}"/></td>
	              		
	              		<td><s:select list="listProjectType" listKey="codeName" listValue="codeDesc" 
	              			name="%{'timeLogEntries['+#stat.index+'].projectTypeId'}" id="entryProjectType%{#stat.index}" emptyOption="true"/></td>
	              		
	              		<td>
	              			<s:if test="listProject != null && !listProject.isEmpty()">
	              			<s:iterator value="listProject" var="precord">
	              				<s:select list="precord" listKey="projectID" listValue="projectName" 
	              					name="%{'timeLogEntries['+#stat.index+'].projectId'}" 
	              			  		  onchange="enable_td_by_project(this,%{#stat.index})"/>
	              			</s:iterator>
	              			</s:if>
	              		</td>
	              			
	              		<td><s:select list="listWorkType" listKey="codeName" listValue="codeDesc"
	              			name="%{'timeLogEntries['+#stat.index+'].workTypeId'}" emptyOption="true"  onchange="check_task_type(this,%{#stat.index})"/></td>
	              			
	              		<!--  <td><s:select list="listTaskType" listKey="codeName" listValue="codeDesc"
	              			name="%{'timeLogEntries['+#stat.index+'].taskId'}" emptyOption="true" /></td>-->
	              		<td >	              		
	              			<s:select list="listTaskTypeED" listKey="codeName" listValue="codeDesc"
	              			name="%{'timeLogEntries['+#stat.index+'].taskId'}" emptyOption="true"/>
	              			<s:select list="listTaskTypeEMCM" listKey="codeName" listValue="codeDesc"
	              			name="%{'timeLogEntries['+#stat.index+'].taskId'}" emptyOption="true"/>
	              		</td>
	              		
	              		<td><s:textfield name="%{'timeLogEntries['+#stat.index+'].ticketNumber'}"   /></td>
	              		<td><s:textfield name="%{'timeLogEntries['+#stat.index+'].workHour'}"  /></td>
	              		
	              		<td><s:checkbox name="%{'timeLogEntries['+#stat.index+'].ot'}" /></td>
	              		
	              		<td><s:textfield name="%{'timeLogEntries['+#stat.index+'].notes'}"  /></td>
	              		
	              		
	              		<s:hidden name="%{'timeLogEntries['+#stat.index+'].userId'}"/>
	              		<s:hidden name="%{'timeLogEntries['+#stat.index+'].workTime'}"/>
	              		<s:hidden name="%{'timeLogEntries['+#stat.index+'].timeLogID'}"/>
	              		
	              	</tr>
              	</s:iterator>
              	
              </s:if>
          </table>
          <table>
		      <tr align="center">
		            <td>
		            	<center>
			            <s:submit value="save" cssClass="TableBtn" action="savePersonTimeLogData" theme="simple"/>&nbsp;&nbsp;&nbsp;
						<s:submit value="delete" cssClass="TableBtn" action="deletePersonTimeLogData" theme="simple"/>&nbsp;&nbsp;&nbsp;
						<input type="button" class="TableBtn" value="reset"  onclick="return clearForm();">&nbsp;&nbsp;&nbsp;
						<input type="button" value="back" class="TableBtn" onclick="window.history.go(-1)"/>
						<!--<s:submit value="back" cssClass="TableBtn" action="searchProfAgtReportData" onclick="setCurrentElement(this);return checkItemsOnBack();" theme="simple"/>-->
			            </center>
		            </td>
		      </tr>
          </table>
	  </s:form>
  	</div>
</div>
<!-- pageName : profAgtDataReportInput.jsp -->
<script type="text/javascript">
	
</script>
</body>
</html>
