<html:html>
<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login --/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
</head>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/common/navmenu.css" />
<html:base />
<title><bean:message key="title.heading"/> - Navigation Menu</title>

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" rightmargin="0">
<form name="navigation">
<!-- Use case table -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr>
       <td colspan="2" class="header" height="20" valign="bottom" nowrap>&nbsp;Manage User Profile</td>
    </tr>
    <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
      <!--a href="userProfileCreate.jsp" target="content">&nbsp;&nbsp;- Create User Profile</a-->
      <a href="/<msp:webapp/>displayUserProfileCreateForm.do" target="content" class="hyperlink">
		&nbsp;&nbsp;- Create User Profile</a>
      </td>
    
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>
      <!--a href="userProfileSearch.jsp" target="content">&nbsp;&nbsp;- View/Modify User Profile</a-->
      <a href="/<msp:webapp/>displayUserSearchForm.do" target="content" class="hyperlink">
      	&nbsp;&nbsp;- View/Modify User Profile</a>
      </td>
   </tr>
</table>
</html:form>
<!-- END FORM -->
<html:errors></html:errors>

</body>
</html:html>