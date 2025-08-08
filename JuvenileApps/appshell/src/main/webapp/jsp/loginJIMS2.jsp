<!DOCTYPE HTML>
<%-- modifications --%>
<%-- 02/05/2007   CShimek	#39146 Added Re-enter JIMS2 User Id field --%>   
<%-- 02/09/2007   LDeen 	#39339 Added min & maxLength for JIMS2 UserID field --%> 
<%-- 02/05/2007   CShimek	#39849 change No Help to function call instead on inline alert --%>   
<%-- 04/17/2007   CShimek   #41195 revised maxlength on passwords from 8 to 32 --%>
<%-- 06/19/2007   CShimek   #42954 revise app.js include  --%>
<%-- 08/10/2007   CShimek   #44352 added JIMS2 Heading --%>

<!-- associated object List-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<html:html  locale="true">

<!--HEADER TAG START-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />

<logic:notEqual name="loginForm" property="userType" value="nonGenericUser">
<html:javascript formName="loginGenericForm"/>
</logic:notEqual>

<logic:equal name="loginForm" property="userType" value="nonGenericUser">
<html:javascript formName="loginJIMS2Form"/>
</logic:equal>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/appshell.css" />
<html:base />

<title><bean:message key="title.heading" /> - loginJIMS2.jsp</title>

<script type="text/JavaScript" src="/appshell/js/loginJIMS2.js"></script>
<script type="text/JavaScript" src="/appshell/js/app.js"></script>
<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>
</head>
<!--END HEADER TAG-->
<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKey(event,true)">

<html:form action="/displayCreateJIMS2AccountSummaryAction" focus="jims2UserId">
<logic:equal name="loginForm" property="userType" value="genericUser">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|282">
</logic:equal>
<logic:equal name="loginForm" property="userType" value="genericSP">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|286">
</logic:equal>	
<logic:equal name="loginForm" property="userType" value="nonGenericUser">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|289">
</logic:equal>	
<html:errors/>
<!-- BEGIN JIMS2 HEADING TABLES -->
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
<!-- END JIMS2 HEADING TABLES -->
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<td align="center" class="header"><bean:message key="title.createJIMS2UserProfileAccount"/></td>
		</tr>
		<logic:notEqual name="loginForm" property="errorMessage" value=" ">
			<tr>
				<td align="center" class="errorAlert"><bean:write  name="loginForm" property="errorMessage"/></td>
			</tr>
		</logic:notEqual>
	</TBODY>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<td>
				<ul>
					<logic:notEqual name="loginForm" property="userType" value="nonGenericUser">
						<li>Please enter an email address in JIMS2 User ID field</li>
						<li>Please enter 6 or more characters in JIMS2 Password field.</li>
					</logic:notEqual>
					<logic:equal name="loginForm" property="userType" value="nonGenericUser">
						<li>Create a JIMS2 User Id that is formatted like an email address.</li>
						<li>Note that your password will be the same as your JIMS password.</li>	
					</logic:equal>	
					<li>Please enter required fields.</li>					
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required">
				<img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.requiredFieldsInstruction"/>
			</td>
		</tr>
	</TBODY>
</table>
<!-- END INSTRUCTION TABLE -->	
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<TBODY>
		<tr>
        	<td colspan="2"><input type="hidden" name="userType" value="<bean:write name="loginForm" property="userType"/>"/></td>
     	</tr>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.userId"/></td>
			<td class="formDe"><bean:write name="loginForm" property="logonId"/></td>		
		</tr>
		<logic:notEqual name="loginForm" property="userType" value="genericSP">
			<tr>
				<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.departmentName"/></td>
				<td class="formDe"><bean:write name="loginForm" property="departmentName"/></td>
			</tr>
		</logic:notEqual>
		<logic:equal name="loginForm" property="userType" value="genericUser">
			<tr>
				<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.badgeNumber"/></td>
				<td class="formDe"><bean:write name="loginForm" property="badgeNumber"/></td>
			</tr>
		</logic:equal>
		<logic:equal name="loginForm" property="userType" value="genericSP">
			<tr>
				<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.serviceProviderName"/></td>
				<td class="formDe"><bean:write name="loginForm" property="serviceProviderName"/></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.employeeId"/></td>
				<td class="formDe"><bean:write name="loginForm" property="employeeId"/></td>
			</tr>
		</logic:equal>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.userName"/></td>
			<td class="formDe"><bean:write name="loginForm" property="userName"/></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.workPhone"/></td>
			<td class="formDe">
				<bean:write name="loginForm" property="userWorkPhoneNumber.areaCode"/> - 
				<bean:write name="loginForm" property="userWorkPhoneNumber.prefix"/> - 
				<bean:write name="loginForm" property="userWorkPhoneNumber.last4Digit"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.email"/></td>
			<td class="formDe"><bean:write  name="loginForm" property="userEmail"/></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.1.diamond"/><bean:message key="prompt.JIMS2UserId"/> (email)</td>
			<td class="formDe"><html:text name="loginForm" property="jims2UserId" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.1.diamond"/><bean:message key="prompt.reEnterJIMS2UserId"/></td>
			<td class="formDe"><html:text name="loginForm" property="confirmJIMS2UserId" size="50" maxlength="50" /></td>
		</tr>
		<logic:notEqual name="loginForm" property="userType" value="nonGenericUser">
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.JIMS2Password"/></td>	
				<td class="formDe"><html:password name="loginForm" property="jims2Password" size="8" value="" maxlength="32"/></td>	
			</tr>
			<tr>
				<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.1.diamond"/><bean:message key="prompt.confirmJIMS2Password"/></td>	
				<td class="formDe"><html:password name="loginForm" property="confirmJIMS2Password" size="8" value="" maxlength="32"/></td>	
			</tr>
			<tr>
				<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.1.diamond"/><bean:message key="prompt.forgottenPasswdPhrase"/></td>
				<td class="formDe">
					<html:select name="loginForm" property="forgottenPasswdPhraseId">
						<html:option value="">Select one</html:option>
						<html:optionsCollection name="loginForm" property="forgottenPasswdPhraseList" value="code" label="description"/>
					</html:select>
				</td>	
			</tr>
			<tr>
				<td class="formDeLabel" width=1%><bean:message key="prompt.1.diamond"/><bean:message key="prompt.answer"/></td>
				<td class="formDe"><html:text name="loginForm" property="answer" size="30" maxlength="30"/></td>	
			</tr>
		</logic:notEqual>
	</TBODY>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table  width="100%" border="0">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
			<html:submit property="submitAction" onclick="return validateFields(this.form);"><bean:message key="button.submit"></bean:message></html:submit>
			<html:reset><bean:message key="button.reset"></bean:message></html:reset>
			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
</body>
</html:html>