<!DOCTYPE HTML>
<!-- 06/02/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/10/2007  C Shimek      - #38306 add multiple submit functionality  -->
<!-- 02/04/2009  C Shimek      - #56860 add Back to Top  -->
<!-- 10/19/2015  RYoung        - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleAssignUserSummary.jsp</title>
<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>
<%-- This script should be removed on security inquiry is ready --%>
<script type="text/javascript">
function nogo(){
  alert("Role Name details display is a future feature.");
  return false;
}
</script>
</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->
<html:form action="/submitAssignUserRoles" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|24">	
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" valign="top"><bean:message key="title.assignUserRoles" />&nbsp;<bean:message key="title.summary"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>The following roles will be assigned when you select the finish button. </li>
        <li>Click the hyperlinked role to view details.  </li>
	  </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.user" /> <bean:message key="prompt.info"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.user" /> <bean:message key="prompt.name"/></td>
								<td class="formDe"><bean:write property="userLastName" name="assignRolesForm"/> <logic:notEqual name="assignRolesForm" property="userFirstName" value=""> , </logic:notEqual> <bean:write property="userFirstName" name="assignRolesForm"/> <bean:write property="userMiddleName" name="assignRolesForm"/></td>								
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.userId"/></td>
								<td class="formDe"><bean:write property="userId" name="assignRolesForm"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="title.jims2Roles"/></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">

						<bean:size id="rolesSize" name="assignRolesForm" property="currentRoles" />
						<script>
						renderScrollingArea(<bean:write name="rolesSize" />); 
						</script>
						<!--checkboxes start-->
						<table width="100%" cellspacing="1" cellpadding="2">
						<thead>
							<tr class="formDeLabel">
								<td class="boldText"><bean:message key="title.jims2Roles"/></td>
							    <td class="boldText"><bean:message key="prompt.roleDescription"/></td>
								</tr>
								</thead>
								<tbody>
							<logic:iterate id="currentRolesIndex" name="assignRolesForm" property="currentRoles" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td>
								<%-- <a href="/<msp:webapp/>handleRoleSelection.do?action=view&roleId=<bean:write name="currentRolesIndex" property="roleId"/>"><bean:write name="currentRolesIndex" property="roleName"/></a> --%>
									<a href="#" onclick="return nogo()" title="View <bean:write name="currentRolesIndex" property='roleName'/> details">
									<bean:write name="currentRolesIndex" property="roleName"/></a>
								</td>
								<td><bean:write name="currentRolesIndex" property="roleDescription"/>
							</tr>
							</logic:iterate>
							</tbody>
						</table>						
						</div>
						<br>

					</td>
				</tr>
			</table>
			<!--assign SA end-->
			<br>
			<!-- BEGIN BUTTON TABLE -->
			<table align="center">
				<tr>
					<td>
						<input type="button" value="Back" onclick="history.go(-1)">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
					</td>
				</tr>
			</table>
		<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
</html:form>	
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>