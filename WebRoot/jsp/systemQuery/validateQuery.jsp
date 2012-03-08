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
	<title>Validation Search</title>
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
    <s:text name="Validation Search"/>
   </h1>
    
   <center>
    <font color="green">
      <s:actionmessage value="actionMessages"/>
    </font>
   </center>
    
   <div class="ResultTable">
    <s:form action="validateQuery" name="validateQuery" theme="simple" namespace="/systemQuery" onSubmit="disableButton();">
       		 
       <h1>Search Criteria </h1>
	
      <table cellpadding="0" cellspacing="0">
      	<tr>
      		<td>Resource Name</td>
      		<td><s:select name="userId" list="userList" listKey="userId" listValue="userName" emptyOption="true" multiple="false"/></td>
      		<td></td>
      		<td></td>
      	</tr>
      	
      	<tr>
      		<td>Begin Date<font color="red">*</font></td>
      		<td><s:textfield name="beginDate" id="beginDate" readonly="true" /></td>
      		<td>End Date<font color="red">*</font></td>
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
		 <s:if test="validateList != null && !validateList.isEmpty()">
         	<h1>
         		<div style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
              <s:iterator value="validateList" var="row" status="i">
              	<tr>
              		<s:iterator value="row" var="record" status="c">
              			<s:if test="#record != null">
              				<s:if test="#i.index==0">
              					<th><s:property value="#record"/></th>
              				</s:if>
              				<s:else>
              					<td align="center"><s:property value="#record"/></td>
              				</s:else>
             			</s:if>
              			<s:else>
              				<td style="background-color:activeborder;"></td>
              			</s:else>
              			
              		</s:iterator>
           		</tr>
              </s:iterator>
          </table>
     	</s:if>
       </div>	       
  	</div>
    </s:form>
   </div>
  </div>
  
  
  <!-- pageName : validateQuery.jsp -->

 </body>
</html>