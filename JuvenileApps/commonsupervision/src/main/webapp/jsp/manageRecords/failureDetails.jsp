<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--11/05/2008	C Shimek   - created JSP  --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageRecords/failureDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body>
<!-- BEGIN FORM -->
<html:form action="/submitAssociateSummary" target="content">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN HEADING TABLE -->
			<table width="100%">
				<tr>
					<td align="center" class="header">
						<logic:notEmpty name="spnSplitForm" property="currentAssessments">
							<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.assessment"/>&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="spnSplitForm" property="currentAssociates">
							<bean:message key="prompt.associate" />
						</logic:notEmpty>
						<logic:notEmpty name="spnSplitForm" property="currentSupervisionPlans">
							<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.supervisionPlan"/>
						</logic:notEmpty>
						<bean:message key="prompt.details" />
					</td>		
				</tr>
			</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
			<table width="98%" align="center">							
				<tr>
					<td align="center" class="errorAlert"><html:errors/></td>
				</tr>		
			</table>
<!-- END ERROR TABLE -->
<!-- END BUTTON TABLE -->	
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td align="center">
						<input type="button" name="close" value="Close Window" onclick="window.close()") >
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