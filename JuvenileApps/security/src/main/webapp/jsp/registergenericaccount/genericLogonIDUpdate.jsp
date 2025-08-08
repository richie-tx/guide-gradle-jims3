<!DOCTYPE HTML>
<!-- 09/22/2006	 CShimek   - Create JSP -->
<!-- 01/11/2007  CShimek   - #38306 add multiple submit functionality  -->
<!-- 02/20/2007  CShimek   - #39739 change generic Logon Id values to register generic account	-->
<!-- 05/14/2007  CShimek   - #41983 added hidden field for currentPassword for validation -->
<!-- 02/05/2009  CShimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />

<html:base />
<title><bean:message key="title.heading" /> - genericLogonIDUpdate.jsp</title>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/registerGenericAccount/genericLogonIDUpdate.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/displayGenericLogonIDSummary" target="content" focus="newPassword">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|219">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.update"/> <bean:message key="prompt.genericAccount"/></td>
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
				<li>Enter required fields and select Next button to view summary.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields"/></td>
	</tr>	
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
      <td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
			<tr class="detailHead">
				<td class="detailHead"><bean:message key="prompt.genericAccount"/> <bean:message key="prompt.information"/></td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
							<td class="formDe"><html:text property="logonId" size="8" maxlength="5"/></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.current"/> <bean:message key="prompt.password"/></td>
							<td class="formDe">
								<bean:write name="genericLogonIdForm" property="currentPassword" />
								<input type="hidden" name="currentPassword" value="<bean:write name="genericLogonIdForm" property="currentPassword" />" >
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.newPassword"/></td>
							<td class="formDe"><html:text property="newPassword" size="10" maxlength="8"/></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.reEnterNewPassword"/></td>
							<td class="formDe"><html:text property="reenterPassword" size="10" maxlength="8"/></td>
						</tr>
					</table>
				</td>
			</tr>
        </table>
        <br>
<!--BEGIN BUTTON TABLE--> 
        <table> 
			<tr> 
				<td>
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>&nbsp;		
					<html:submit onclick="return validateFields() && disableSubmit(this, this.form);" property="submitAction"><bean:message key="button.next"/></html:submit>
					<html:reset value="Reset" onclick="javascript:this.form.newPassword.focus();"></html:reset>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
				</td> 
			</tr> 
        </table>
<!--END BUTTON TABLE-->
        </td>
    </tr> 
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
