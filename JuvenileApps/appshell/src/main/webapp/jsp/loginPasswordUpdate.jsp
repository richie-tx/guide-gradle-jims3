<!DOCTYPE HTML>
<!-- 05/16/2007	C Shimek	revised to not display Help href when page displays withing frame, found while testing defects -->
<%-- 08/10/2007 C Shimek   	#44352 added JIMS2 Heading --%>
<!-- associated object List-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%-- 
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<html:html  locale="true">
--%>


<!--HEADER TAG START-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base />

<html:javascript formName="updatePasswordForm"/>

<title><bean:message key="title.heading" /> - loginPasswordUpdate.jsp</title>


<!-- JQUERY FW -->
<!-- UI smoothness Theme -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/jquery-ui.min.css" />


<script type="text/javascript" src="/<msp:webapp/>js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.min.js"></script> 

<!-- JQUERY FW -->

<script type="text/javascript" src="/<msp:webapp/>js/loginPasswordUpdate.js"></script>
<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>




</head>
<!--END HEADER TAG-->
<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" onLoad="document.forms[0].currentPassword.focus();">

<html:form action="/submitUpdatePasswordAction" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|228">
<!-- BEGIN JIMS2 HEADING TABLES -->
<logic:notEqual name="loginForm" property="fromPage" value="mainMenu">
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
</logic:notEqual>	
<!-- END JIMS2 HEADING TABLES -->
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0" align="center">
	<TBODY>
		<tr>
			<td align="center" class="header"><bean:message key="title.updatePassword"/></td>
		</tr>
		<logic:notEqual name="loginForm" property="confirmMessage" value="">
			<tr>
				<td align="center" class="confirm"><bean:write name="loginForm" property="confirmMessage"/></td>
			</tr>
		</logic:notEqual>
	</TBODY>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0" align="center">
	<TBODY>
		<tr>
    		<td colspan="2">
	  			<ul>
		        	<li>Please enter required fields.</li>
					<logic:equal name="loginForm" property="fromPage" value="mainMenu">
						<li>You will be automatically logged out once your password is updated.</li>
					</logic:equal>		        	
      			</ul>
			</td>
		</tr>	
		<tr>
			<td class="required" align="center"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>
	</TBODY>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue" align="center">
	<TBODY>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.userId"/></td>
			<td class="formDe">
				<logic:notEqual name="loginForm" property="userType" value="nonGenericUser"> 
					<bean:write name="loginForm" property="jims2UserId"/>
				</logic:notEqual>
				<logic:equal name="loginForm"  property="userType" value="nonGenericUser"> 
				 	<bean:write name="loginForm" property="jimsUserId"/>
				</logic:equal>
			</td>	
		</tr>
		 
		<tr bgcolor="#F0F0F0">
			<bean:define id="passwd" name="loginForm" property="password"/>
			<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.password"/></td>
			<td class="formDe"><html:password name="loginForm" property="currentPassword" size="16" maxlength="17" styleId="currPassword"/></td>		
		</tr>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.newPassword"/></td>
			<td class="formDe"><html:password name="loginForm" property="newPassword" size="16" maxlength="17" styleId="newPassword"/></td>		
		</tr>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/>Confirm <bean:message key="prompt.password"/></td>
			<td class="formDe"><html:password name="loginForm" property="confirmPassword" size="16" maxlength="17" styleId="confirmPassword"/></td>		
		</tr>
		
		<%-- <jims:if name="loginForm" property="userType" value="nonGenericUser" op="notEqual">
		<jims:and name="loginForm" property="userType" value="N" op="notEqual" /> 
		<jims:then>
			<tr> 
				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.forgottenPasswdPhrase"/></td>
				<td class="formDe">
					<html:select name="loginForm" property="forgottenPasswdPhraseId">
					<html:option value="">Select one</html:option>
					<html:optionsCollection name="loginForm" property="forgottenPasswdPhraseList" value="code" label="description"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.answer"/></td>
				<td class="formDe"><html:text name="loginForm" property="answer" size="30" value="" maxlength="30"/></td>	
			</tr>
		</jims:then>
		</jims:and> 
		</jims:if>  --%>

	</TBODY>
</table>
<!-- END DETAIL TABLE -->
<table>
	<tr>
		<td><input type="hidden" name="type" value="<bean:write name="loginForm" property="userType"/>" id="userType"/></td>
		<td><html:hidden name="loginForm" property="action" value="updatePassword"/></td>
	</tr>
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%" border="0">
	<tr>
		<td align="center">
			<logic:notEqual name="loginForm" property="userType" value="nonGenericUser"> 
				<html:submit property="submitAction" styleId="submitBtn"><bean:message key="button.submit"></bean:message></html:submit>
			</logic:notEqual>

			<logic:equal name="loginForm" property="userType" value="nonGenericUser"> 
				<html:submit property="submitAction" styleId="submitBtn" disabled="true"><bean:message key="button.submit"></bean:message></html:submit>
			</logic:equal>
						
			<logic:notEqual name="loginForm" property="fromPage" value="mainMenu">
				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
			</logic:notEqual>	
			<logic:equal name="loginForm" property="fromPage" value="mainMenu">
				<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			</logic:equal>	
			<html:reset><bean:message key="button.reset"></bean:message></html:reset>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
</body>
</html:html>