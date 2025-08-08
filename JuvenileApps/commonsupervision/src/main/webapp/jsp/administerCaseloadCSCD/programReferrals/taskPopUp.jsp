<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/04/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/taskPopUp.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>

	function validateFields(theForm)
	{
		clearAllValArrays();

		addDefinedDB2Mask("programIssueTaskText","<bean:message key='errors.freeTextDB2' arg0='Program Issues'/>");
		customValMaxLength('programIssueTaskText','Program Issues cannot be more than 1000 characters.',1000);
		
		return validateCustomStrutsBasedJS(theForm);	
	}
	
</script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/handleTask" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top" align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.issues"/>
								</td>
							</tr>
							<tr>
								<td><html:textarea name="cscProgRefForm" property="programIssueTaskText" style="width:100%" rows="10"></html:textarea>
								</td>
							</tr>
						</table>
					 </td>
				</tr>
			</table>
			<div class="spacer4px"></div>
			<!-- begin BUTTON TABLE -->
			<table width="98%" cellpadding="2" cellspacing="1">
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="validateFields(this.form) && window.close()" ><bean:message key="button.submit" /></html:submit>
						<input type="button" value="Cancel" onclick="window.close()">
					</td>
				</tr>
			</table>
		</td>
	 </tr>
</table>      
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
