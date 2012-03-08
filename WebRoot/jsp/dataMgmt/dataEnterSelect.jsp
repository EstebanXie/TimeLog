<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="agencyMgmtSys"/></title>
<base href="<%=basePath%>"/>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  
  <script src="js/jquery-1.6.2.min.js"></script>
  <script src="js/jquery-ui-1.8.17.custom.min.js"></script>
  
  <link href="css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
  
  
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  
  
  <script type="text/javascript">
  $(function() {
  $( "#enterDateSelected" ).datepicker({ 
   dateFormat: 'yy/mm/dd',
   autoSize: true,
   altField:'#actualDate'
    });

 });
 </script>
 
 </script>
 <style type="text/css">

 .ui-datepicker { width: 17em; padding: .2em .2em 0; font-size: 15px;}  /*覆盖了jquery-ui-1.8.13.custom.css中的.ui-datepicker，用于调整大小*/

 </style>
 
 
 

 
 
  
  
<script>
function checkSearchCondition() {
	var date = document.dataReportSearchForm["enterDateSelected"];
	
	if(date.value==null || date.value==""){
		alert("请输入日期！");
		return false;
	}

	return true;
}


</script>
<link rel="stylesheet" href="style/common.css"/>
</head>

<body class="RightBG">
<div class="RightMain">
	<h1>select date</h1>
    <center>
	    <font color="green">
	    	<h3><s:actionmessage value="actionMessages"/></h3>
	    </font>
    </center>
    <br/>
    <div class="AddTable">
    <s:form action="" namespace="/dataMgmt" name="dataReportSearchForm" method="POST" enctype="multipart/form-data" onSubmit="disableButton();">
    <table cellpadding="0" cellspacing="0" border="0" >
		
		<s:textfield name="enterDateSelected" id="enterDateSelected" readonly="true" label="date" required="true"/>
		<tr>
			<td colspan="2"><center>
				<s:submit value="edit" cssClass="TableBtn" onclick="setCurrentElement(this);return checkSearchCondition()" action="addTimeLogEnter" theme="simple"/>&nbsp;&nbsp;&nbsp;
	
				<!--<s:reset value="rest" cssClass="TableBtn" theme="simple"/>-->
			</center></td>
		</tr>
	</table>
    </s:form>
    </div>
</div>
<!-- pageName : dataReportSearch.jsp -->
<script type="text/javascript">
	
</script>
</body>
</html>
