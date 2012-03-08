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
  <title>Add/Modify Client</title>
	<script src="js/Main.js" type="text/javascript"></script>
  	<script src="js/jquery.js" type="text/javascript"></script>
  	<script src="js/common.js" type="text/javascript"></script>
  	<script src="js/preventDoubleClick.js" type="text/javascript"></script>
	<script src="js/Calendar.js" charset="utf-8"></script>
	<script src="js/jquery.js" type="text/javascript"></script>
  	<script src="js/jquery-1.6.2.min.js"></script>
  	<script src="js/jquery-ui-1.8.17.custom.min.js"></script>
    <script>
  	function checkOnSubmit() {
			if(document.clientForm["editClient.clientName"].value == '') {
				alert('Please input Client name£¡');
				return false;
			}
			
			return true;
  	}


  	</script>
  	<script type="text/javascript">
	  $(function() {
		  $("#transferDate" ).datepicker({ 
		   dateFormat: 'yy/mm/dd',
		   autoSize: true,
		   altField:'#actualDate'
		    });
	 });
	 </script>
  <link rel="stylesheet" href="style/common.css"/>
  <link href="css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
 </head>
 <body class="RightBG">
  <div class="RightMain">
   <h1>
    <s:text name="Add/Modify Client"/>
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
    <s:form action="addClient"
    				name="clientForm"
            namespace="/clientMgmt" theme="simple" onSubmit="disableButton();">
     <s:hidden name="editClient.clientId"/>
     <table cellpadding="0" cellspacing="0" border="0">
       <tr>
        <th width="30%">Client Name<font color="red">*</font></th>
        <td>
         <s:textfield name="editClient.clientName" maxlength="20"/>
        </td>
       </tr>
       
      <tr>
       <th>Client Code</th>
       <td>
        <s:textfield name="editClient.clientCode" size="20"/>
       </td>
      </tr>
      
       <tr>
       <th>Client Status</th>
       <td>
       	<s:select name="editClient.status" list="{'Valid','Cancelled'}" required="true"/>         
       </td>
      </tr>   

      <tr>
       <th>Client TransferDate</th>
       <td>
        <s:textfield name="editClient.transferDate" id="transferDate" readonly="true"/>
       </td>
      </tr>
      
      <tr>
       <th>Client Notes</th>
       <td>
       <s:textarea name="editClient.notes" rows="2" cols="25"></s:textarea>
       </td>
      </tr>
                      
      <tr align="center">
       <td colspan="2">
        <center>
         <s:if test="editClient.clientId != null && editClient.clientId != ''">
          <s:submit value="Modify"  action="modifyClient" cssClass="TableBtn"  onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:if>
         <s:else>
          <s:submit value="Add" cssClass="TableBtn" onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:else>
         &nbsp;&nbsp;&nbsp;
         <input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">
         &nbsp;&nbsp;&nbsp;
         <input type="button" value="Back" class="TableBtn" onclick="window.history.go(-1)"/>
        </center>
       </td>
      </tr>
     </table>
    </s:form>
   </div>
  </div>
  <script type="text/javascript">
	
</script>
  
 </body>
</html>