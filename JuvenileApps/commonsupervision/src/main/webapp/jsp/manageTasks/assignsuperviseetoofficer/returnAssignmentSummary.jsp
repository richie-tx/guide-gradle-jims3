<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<!-- 03/17/2010  C Shimek    #64493 removed unnecessay onload from body tag that was causing js error. Also cleaned-up coding to UI standards -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<head>
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/returnAssignmentSummary" target="content">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
					<!--blue tabs start--> 
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> 
					<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"/></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt="" /></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header">CSCD - Return Assignment Reason Summary</td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> 
					<!-- BEGIN ERROR TABLE -->
					<table width="98%" align="center">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERROR TABLE -->																																
					<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Verify that information is correct and select Finish button. 
									If any changes are needed, select Back button.
								</li>
							</ul>
							</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE -->					
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center">
							<!--header start--> 
								<tiles:insert page="../../common/assignmentHeader.jsp" flush="true"></tiles:insert> 
							<!--header end-->
							</td>
						</tr>
					</table>
					<br>
					<!-- BEGIN DETAIL TABLE -->
					<table width="98%" border="0" cellpadding="4" cellspacing="1" class="borderTableBlue">
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programUnit"/></td>
							<td class="formDe"><bean:write name="caseAssignmentForm" property="programUnitName" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.workgroup"/></td>
							<td class="formDe"><bean:write name="caseAssignmentForm" property="workGroupName" /></td>
						</tr>
					</table>
					<br>
					<table width="98%" cellpadding="2" cellspacing="1" border="0" class="borderTableBlue">
						<tr class="formDeLabel">
							<td>Return Reason</td>
						</tr>
						<tr>
							<td class="formDe"><bean:write name="caseAssignmentForm" property="assignmentReturnReason" /></td>
						</tr>
					</table>
					<br>
					<!-- END DETAIL TABLE --> 
					<!-- BEGIN BUTTON TABLE -->
					<table border="0">
						<tr align="center">
							<td>
								<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
							</td>
							<td>
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.finish" />
								</html:submit>
							</td>
							<td>
								<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>