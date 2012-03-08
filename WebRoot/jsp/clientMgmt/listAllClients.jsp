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
  <title>Client Management</title>
  <script src="js/Main.js" type="text/javascript"></script>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/dataInit.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
  
  <script type="text/javascript">

  function query(pageNo,action) { //¸ÄformÃû³Æ
		document.clientListForm["paging.pageNum"].value = pageNo;
	  var orgaction = document.clientListForm.action;
	  document.clientListForm.action = action;
		document.clientListForm.submit();
		document.clientListForm.action = orgaction;
	}
  function resetPagnum(){
	  document.clientListForm["paging.pageNum"].value=1;
  }
  </script>
  
  <link rel="stylesheet" href="style/common.css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Client Management"/>
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
    <s:form action="clientMgmt/toAddClient"
            name="clientListForm" theme="simple" onSubmit="disableButton();">
            
       <s:hidden name="paging.pageNum"/>
            
            
         <s:if test="clients != null && !clients.isEmpty()"> 
	         	<h1>
	         		<div style="width:100%">
	         			Total <s:property value="paging.numOfRecords"/> Results &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         			<s:property value="paging.pageNum"/>Page/<s:property value="paging.numOfPages"/>Pages &nbsp;
	         		</div>
	         		
	         		<s:if test="paging.numOfPages > 1">
	                <strong><a href="javascript:query(1,'clientMgmt/listAllClients')">First page</a></strong>
	                <s:if test="paging.pageNum > 1">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum - 1"/>,'clientMgmt/listAllClients')">Preview</a></strong>
	                </s:if>
	                <s:if test="paging.pageNum < paging.numOfPages">
	                    |&nbsp;<strong><a href="javascript:query(<s:property value="paging.pageNum + 1"/>,'clientMgmt/listAllClients')">Next</a></strong>
	                </s:if>
	                	|&nbsp;
	                <strong><a href="javascript:query(<s:property value="paging.numOfPages"/>,'clientMgmt/listAllClients')">Last page</a></strong>
	            </s:if>
	         	</h1>
	     	</s:if>
	     	   
     <table cellpadding="0" cellspacing="0">
      <tr>
       <th width="5%">&nbsp;</th>
       <th width="20%">Client Name</th>
       <th width="15%">clientCode</th>
       <th width="15%">Status</th>
       <th width="15%">TransferDate</th>
       <th width="20%">Notes</th>
      </tr>
       
      <s:iterator value="clients" var="client">
       <tr>
        <td>
         <s:checkbox name="clientIdArray"
                     fieldValue="%{#client.clientId}"
                     cssClass="checkBox"></s:checkbox>
        </td>
        <td>
         <s:property value="#client.clientName"/>
        </td>
        <td>
         <s:property value="#client.clientCode"/>
        </td>
        <td>
         <s:property value="#client.status"/>
        </td>
        <td>
         <s:property value="#client.transferDate"/>
        </td>
        <td>
         <s:property value="#client.notes"/>
        </td>                
       </tr>
      </s:iterator>
       
      <tr align="center">
       <td colspan="6">
        <center>
        <s:hidden name="operation"/>
         <s:submit value="Add Client" cssClass="TableBtn" onclick="javascript:setCurrentElement(this); clientListForm.operation.value='add';resetPagnum();"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Modify Client" cssClass="TableBtn"
                   action="toModifyClient"
                   onclick="setCurrentElement(this);resetPagnum();javascript: clientListForm.operation.value='edit'; return checkModify(clientListForm.clientIdArray);"/>
         &nbsp;&nbsp;&nbsp;
         <s:submit value="Delete Client" cssClass="TableBtn"
                   action="deleteClient"
                   onclick="setCurrentElement(this);resetPagnum();javascript: return checkDelete(clientListForm.clientIdArray);"/>
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