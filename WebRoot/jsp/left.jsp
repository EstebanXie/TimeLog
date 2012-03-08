<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Mockup</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<base href="<%=basePath%>"/>
<link href="<%=path%>/style/common.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/js/Main.js" type="text/javascript"></script>
</head>

<body>
  <div class="LeftMenu">
  </div>

	<div class="LeftNav">
		 <s:iterator value="#session.__CURRENT_LOGINED_USER_MENU_CONTEXT" var="menu" status="status">
      <h1 id="LeftMain<s:property value="#status.count"/>"
        onclick="Tab('LeftMain', <s:property value="#status.count"/>, <s:property value="#session.__CURRENT_LOGINED_USER_MENU_CONTEXT.size()"/>)"
        <s:if test="#status.count == 1">
          class="current"
        </s:if>
        >
			 	<span class="LeftMain<s:property value='#menu.resourceSeq'/>">
			 		<s:property value="#menu.resourceName"/>
			 	</span>
        </h1>
          <div class="LeftMain" id="LeftMain_cont<s:property value="#status.count"/>"
            <s:if test="#status.count != 1">
              style="display:none"
            </s:if>
          >
            <ul>
            <s:iterator value="#menu.childMenuList" var="childMenu">
              <li>
                <a href="<s:property value="#childMenu.resourceUrl"/>" target="right">
                  <s:property value="#childMenu.resourceName"/>
                </a>
              </li>
            </s:iterator>
            </ul>
          </div>
		 </s:iterator>
	</div>
</body>
</html>
