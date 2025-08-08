<!DOCTYPE>
<%-- 06/19/2007   CShimek   #42954 revise app.js for new build.xml --%>
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
<html:javascript formName="loginOfficerCreateForm"/>
<!-- Javascripts -->
<script type="text/JavaScript" src="/appshell/js/loginOfficerCreate.js"></script>
<script type="text/JavaScript" src="/appshell/js/app.js"></script>

<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base />

<title><bean:message key="title.heading" /> - loginOfficerCreate.jsp</title>
</head>
<!--END HEADER TAG-->
<!--BEGIN BODY TAG-->
<body topmargin=0 leftmargin="0" onKeyDown="checkEnterKey(event,true)">

<html:form action="/displayCreateOfficerProfileSummaryAction" focus="badgeNumber">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|130">
<html:errors/>
<!-- BEGIN JIMS2 HEADING TABLES -->
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
<!-- END JIMS2 HEADING TABLES -->
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<td align="center" class="header"><bean:message key="title.createOfficerProfile"/></td>
		</tr>
		<logic:notEqual name="loginForm" property="errorMessage" value=" ">
			<tr>
				<td align="center" class="errorAlert"><bean:write name="loginForm" property="errorMessage"/></td>
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
			<td colspan="2">
				<ul>
					<li>Enter at least the required officer information and select Next button. </li>								
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.1.diamond"/>
				<bean:message key="prompt.1.diamond"/>At least one of these fields required.
			</td>
		</tr>
	</TBODY>
</table>
<!-- END INSTRUCTION TABLE  -->
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<TBODY>
	<tr bgcolor="#F0F0F0">
		<td class="formDeLabel">
		<bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>
			<bean:message key="prompt.badgeNumber"/></td>
		<td class="formDe"><html:text name="loginForm" property="badgeNumber" size="10" maxlength="10"/></td>		
	</tr>
	<tr>
		<td class="formDeLabel">
		<bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>
			<bean:message key="prompt.other"/> <bean:message key="prompt.id"/> <bean:message key="prompt.number"/></td>
		<td class="formDe"><html:text name="loginForm" property="otherIdNumber" size="10" maxlength="10"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.lawEnforcementAgency"/></td>
		<td class="formDe"><bean:write name="loginForm" property="agencyName"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.departmentName"/></td>
		<td class="formDe"><bean:write name="loginForm" property="departmentName"/></td>
	</tr>
	<tr>
		<td class="formDeLabel">
		<bean:message key="prompt.1.diamond"/>
			<bean:message key="prompt.lastName"/></td>
		<td class="formDe"><html:text name="loginForm" property="userName.lastName" size="30" maxlength="75" /></td>
	</tr>
	<tr>
		<td class="formDeLabel">
		<bean:message key="prompt.1.diamond"/>
			<bean:message key="prompt.firstName"/></td>
		<td class="formDe"><html:text name="loginForm" property="userName.firstName" size="25" maxlength="50" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.middleName"/></td>
		<td class="formDe"><html:text name="loginForm" property="userName.middleName" size="25" maxlength="50"/></td>
	</tr>
	<tr>
		<td class="formDeLabel">
		<bean:message key="prompt.1.diamond"/>
			<bean:message key="prompt.workPhone"/></td>
		<td class="formDe">
			<html:text name="loginForm" property="userWorkPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
			<html:text name="loginForm" property="userWorkPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"  /> - 
			<html:text name="loginForm"	property="userWorkPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> 
			Ext. <html:text name="loginForm" property="userWorkPhoneNumber.ext" size="6" maxlength="6" onkeyup="return autoTab(this, 4);" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
		<td class="formDe">
			<html:text name="loginForm"	property="userCellPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
			<html:text name="loginForm" property="userCellPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
			<html:text name="loginForm" property="userCellPhoneNumber.last4Digit" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.pager"/></td>
		<td class="formDe">
			<html:text name="loginForm" property="userPagerNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
			<html:text name="loginForm" property="userPagerNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
			<html:text name="loginForm" property="userPagerNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.email"/></td>
		<td class="formDe"><html:text name="loginForm" property="userEmail" size="25" maxlength="100"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.JIMS2UserId"/></td>
		<td class="formDe"><html:text name="loginForm" property="jims2UserId" size="30"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.JIMS2Password"/></td>	
		<td class="formDe"><html:password name="loginForm" property="jims2Password" size="8" maxlength="32"/></td>	
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.confirmJIMS2Password"/></td>	
		<td class="formDe"><html:password name="loginForm" property="confirmJIMS2Password" size="8" maxlength="32"/></td>	
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.forgottenPasswdPhrase"/></td>
		<td class="formDe">
			<html:select name="loginForm" property="forgottenPasswdPhraseId">
				<html:option value="" >Select one</html:option>
				<html:optionsCollection name="loginForm" property="forgottenPasswdPhraseList" value="code" label="description"/>
			</html:select>
		</td>	
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.answer"/></td>
		<td class="formDe"><html:text name="loginForm" property="answer" size="30" maxlength="30"/></td>	
	</tr>
	</TBODY>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
			<html:submit property="submitAction"  onclick="return validateLoginOfficerCreateForm(this.form) && validateOfficerFields(this.form);"><bean:message key="button.next"></bean:message></html:submit>
			<html:reset><bean:message key="button.reset"></bean:message></html:reset>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
</body>
</html:html>