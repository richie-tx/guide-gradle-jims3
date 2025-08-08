<!DOCTYPE HTML>

<!-- Modifications -->
<!-- CShimek 05/25/2007 Created JSP -->
<!-- CShimek 06/28/2007 revised page to work as password update confirmation -->
<%-- CShimek 08/10/2007 #44352 added JIMS2 Heading --%>
<%-- CShimek 08/10/2007 #42837 added Click OK to alert message --%>
<!-- ************************************** NOTE ****************************************************** 
* This page is only used if user has successfully updated their password after having successfully  
* logged into JIMS2.  It's main purpose is to display something other than a blank page under the 
* confirmation alert.  In this case the something is a copy of the update password page
****************************************************************************************************** -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<html:html>
<HEAD>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- LINK href="../theme/Master.css" rel="stylesheet" type="text/css"> --%>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<title><bean:message key="title.heading" /> - loginPasswordUpdateConfirm.jsp</title>
<script type="text/javascript">
function logout(){
	var location = "/appshell/displayLogout.do";	
	alert('Your Password has been successfully updated.\nClick OK and log in again.');
	window.location.href=location;
}
</script>
</HEAD>

<BODY onload="logout();">
<html:form action="/submitUpdatePasswordAction">
<!-- BEGIN JIMS2 HEADING TABLES -->
<logic:notEqual name="loginForm" property="fromPage" value="mainMenu">
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
</logic:notEqual>	
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0">
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
<table width="98%" border="0">
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
<!-- images require full level qualification to display for some reason  -->		
		<tr>
			<td class="required"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>
	</TBODY>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<TBODY>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.userId"/></td>
			<td class="formDe"><bean:write name="loginForm" property="jims2UserId"/></td>			
		</tr>
		<tr bgcolor="#F0F0F0">
			<bean:define id="passwd" name="loginForm" property="password"/>
			<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><bean:message key="prompt.password"/></td>
			<td class="formDe"><html:password name="loginForm" property="currentPassword" size="16" maxlength="17"/></td>		
		</tr>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><bean:message key="prompt.newPassword"/></td>
			<td class="formDe"><html:password name="loginForm" property="newPassword" size="16" maxlength="17"/></td>		
		</tr>
		<tr bgcolor="#F0F0F0">
			<td class="formDeLabel" nowrap width=1%><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>Confirm <bean:message key="prompt.newPassword"/></td>
			<td class="formDe"><html:password name="loginForm" property="confirmPassword" size="16" maxlength="17"/></td>		
		</tr>
		<%-- <jims:if name="loginForm" property="userType" value="nonGenericUser" op="notEqual">
		<jims:and name="loginForm" property="userType" value="N" op="notEqual" /> 
		<jims:then>
			<tr> 
				<td class="formDeLabel" nowrap width=1%><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><bean:message key="prompt.forgottenPasswdPhrase"/></td>
				<td class="formDe">
					<html:select name="loginForm" property="forgottenPasswdPhraseId">
					<html:option value="">Select one</html:option>
					<html:optionsCollection name="loginForm" property="forgottenPasswdPhraseList" value="code" label="description"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><bean:message key="prompt.answer"/></td>
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
		<td><input type="hidden" name="type" value="<bean:write name="loginForm" property="userType"/>"/></td>
		<td><html:hidden name="loginForm" property="action" value="updatePassword"/></td>
	</tr>
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.submit"></bean:message></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			<html:reset><bean:message key="button.reset"></bean:message></html:reset>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
</BODY>
</html:html>