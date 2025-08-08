<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 03/12/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/closeCase/closeCaseSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitCloseCase" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/HC_Case/Close_Supervision_HC_Case.htm#|3">
<div align="center">
<!-- BEGIN MAIN TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<!-- BEGIN BLUE TABS TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
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
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<!-- END BLUE TABS TABLE -->	
		<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.closeCase"/>&nbsp;<bean:message key="title.summary"/></td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						
				<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
				<!-- END ERROR TABLE -->
				
						<!-- BEGIN INSTRUCTION TABLE -->
						<div class="instructions" align="left">
							<ul>
								<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
							</ul>
						</div>
						<div class="spacer"></div>
						<!-- END INSTRUCTION TABLE -->
						
				<!-- BEGIN SUPERVISEE INFO TABLE -->
						<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
						</tiles:insert>
				<!-- END SUPERVISEE INFO TABLE -->	
						<br>
						
						<!-- BEGIN DETAIL TABLE -->
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<bean:message key="prompt.closeCase"/>&nbsp;<bean:message key="prompt.info"/>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<% int recCounter=0;
									   String bgcolor=""; %>
										<tr class="formDeLabel">
											<td><bean:message key="prompt.case"/></td>
											<td><bean:message key="prompt.court"/></td>
											<td><bean:message key="prompt.inactivationDate"/></td>
										</tr>
									 <logic:iterate id="caseIndex" name="caseAssignmentForm" property="closeCasesList">
										<% recCounter++;
										   bgcolor = "alternateRow";                      
										   if (recCounter % 2 == 1) {
											  bgcolor = "normalRow";
										   } %>
										<tr class=<%= bgcolor %>>
											<td><bean:write name="caseIndex" property="criminalCaseId"/></td>
											<td><bean:write name="caseIndex" property="courtId"/></td>
											<td><bean:write name="caseIndex" property="terminationDateStr"/></td>
										</tr>
                                      </logic:iterate> 
									</table>
								</td>
							</tr>
						</table>
						<!-- END DETAIL TABLE -->
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.finish" /></html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<!-- END BLUE TABS TABLE -->	
			<br>
		</td>
	</tr>
</table>
<!-- END MAIN TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
