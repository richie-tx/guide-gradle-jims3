<!DOCTYPE HTML>
<%-- 06/19/2007   CShimek   #42954 revise app.js for new build.xml --%>
<%-- 08/09/2007   CShimek	#44352 add JIMS2 Heading to page --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>


<!--HEADER TAG START-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base />

<!-- Javascripts -->
<script type="text/JavaScript" src="/appshell/js/app.js"></script>
<script type="text/JavaScript" src="/appshell/js/loginGenericOfficer.js"></script>

<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>

<title><bean:message key="title.heading" /> - loginGenericOfficer.jsp</title>
</head>
<!--END HEADER TAG-->
<!--BEGIN BODY TAG-->
<body topmargin=0 leftmargin="0" onKeyDown="checkEnterKey(event,true)">

<html:form action="/handleValidateOfficerAction" focus="badgeNumber">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|88">
<!-- BEGIN JIMS2 HEADING TABLES -->
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
<!-- END JIMS2 HEADING TABLES -->
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<td align="center" class="header"><bean:message key="title.officerValidationScreen"/></td>
		</tr>
		<logic:notEqual name="loginForm" property="errorMessage" value=" ">
			<tr>
				<td align="center" class="errorAlert"><bean:write  name="loginForm" property="errorMessage"/></td>
			</tr>
		</logic:notEqual>
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr> 
	</TBODY>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Please enter either Badge Number or Other ID Number.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
<table width=98% border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td valign="top" align="center">
			<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
					<td class="formDe"><bean:write name="loginForm" property="logonId"/></td>
				</tr>
			    <tr>
					<td class="formDeLabel" width="4%" nowrap><bean:message key="prompt.departmentName"/></td>
					<td class="formDe"><bean:write name="loginForm" property="departmentName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.badgeNumber"/></td>
					<td class="formDe"><html:text name="loginForm" property="badgeNumber" size="10" maxlength="10"/></td>
				</tr>
				<tr>
					<td class="formDeLabel">OR</td>		
					<td class="formDe"></td>	
				</tr>
				<tr>
					<td class="formDeLabel" width="4%" nowrap ><bean:message key="prompt.other"/> <bean:message key="prompt.id"/> <bean:message key="prompt.number"/></td>
					<td class="formDe"><html:text name="loginForm" property="otherIdNumber" size="10" maxlength="10" /></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:submit  onclick="return validateFields(this.form)" property="submitAction"><bean:message key="button.submit"></bean:message></html:submit>
			<logic:equal name="loginForm" property="action" value="createOfficerProfile">
				<html:submit property="submitAction" value="Create Officer Profile"></html:submit>
			</logic:equal>
			<html:submit property="submitAction"><bean:message key="button.reset"></bean:message></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
</body>
</html:html>