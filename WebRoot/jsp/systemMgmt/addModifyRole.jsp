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
  <script src="js/jquery.js" type="text/javascript"></script>
  <script src="js/common.js" type="text/javascript"></script>
  <script src="js/preventDoubleClick.js" type="text/javascript"></script>
    <script>
  	function checkOnSubmit() {
			if(document.roleForm["role.roleName"].value == '') {
				alert('Please input Role name£¡');
				return false;
			}
			
			return true;
  	}
  	</script>
  <link rel="stylesheet" href="style/common.css"/>
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
    <s:form action="addModifyRole"
    				name="roleForm"
            namespace="/sysMgmt" theme="simple" onSubmit="disableButton();">
     <s:hidden name="role.roleId"/>
     <table cellpadding="0" cellspacing="0" border="0">
       <tr>
        <th width="30%">Role Name<font color="red">*</font></th>
        <td>
         <s:textfield name="role.roleName" maxlength="20"/>
        </td>
       </tr>
       
      <tr>
       <th>Remarks</th>
       <td>
        <s:textfield name="role.remark" size="30"/>
       </td>
      </tr>
      
      <tr>
       <th>Authorization/Resource</th>
       <td>
          <s:iterator value="resources" var="resource" status="status">
              <span>
                <strong><s:property value="#resource.resourceName"/></strong>
              </span>
                <div class="LeftMain" id="LeftMain_cont<s:property value="#status.count"/>">
                  <s:iterator value="#resource.childMenuList" var="childResource" status="index">
                    <s:if test="hisResources.contains(#childResource.resourceId)">
                      <s:checkbox name="resourceIdArray"
                         fieldValue="%{#childResource.resourceId}"
                         value="true"
                         cssClass="checkBox"></s:checkbox><s:property value="#childResource.resourceName"/>
                    </s:if>
                    <s:else>
                      <s:checkbox name="resourceIdArray"
                         fieldValue="%{#childResource.resourceId}"
                         cssClass="checkBox"></s:checkbox><s:property value="#childResource.resourceName"/>
                    </s:else>
                    
                     <s:if test="#index.count != 0 && #index.count % 4 == 0"><br/></s:if>
                  </s:iterator>
                </div>
           </s:iterator>
       </td>
      </tr>
       
      <tr align="center">
       <td colspan="2">
        <center>
         <s:if test="role.roleId != null && role.roleId != ''">
          <s:submit value="Modify" cssClass="TableBtn"  onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:if>
         <s:else>
          <s:submit value="Add" cssClass="TableBtn" onclick="setCurrentElement(this);return checkOnSubmit();"/>
         </s:else>
         &nbsp;&nbsp;&nbsp;
         <input type="button" class="TableBtn" value="Reset"  onclick="return clearForm();">
         &nbsp;&nbsp;&nbsp;
         <input type="button" value="Back" class="TableBtn" onclick="window.history.go(-1)"/>
         <!--<s:submit value="·µ»Ø" cssClass="TableBtn"
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