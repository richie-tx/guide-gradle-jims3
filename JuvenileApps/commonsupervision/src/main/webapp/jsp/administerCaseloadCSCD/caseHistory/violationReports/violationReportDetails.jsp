<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  this page used to display VR details for tasks -->
<!--MODIFICATIONS -->
<!-- 02/12/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 01/26/2009 C Shimek          - #52815 revised dateformat in status block -->
<!-- 05/10/2010 C Shimek          - 65373 revised action used in print call -->
<!-- 02/24/2011 R Young           - 69264 Fix Self-Approve button displays for CSO -->
<!-- 11/08/2011 C Shimek          - #71259 change prompt.filedDate to prompt.presentDate -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/violationReportDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function printReport(){
	openWindow('handleViolationReportUpdates.do?submitAction=Print');
} 
function printCaseNote(){
	caseNoteId = document.getElementById("caseNoteId").value;
	var webApp = "/<msp:webapp/>";
	var url = webApp + 'handleViolationReportDetailsSelection.do?submitAction=Link&rrand=<%out.print((Math.random()*246));%>';
	var newWin = window.open(url, "pictureWindow", "height=500,width=650,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleViolationReportDetailsSelection" target="content">
<logic:equal name="violationReportsForm" property="statusId" value="PM">
		<!--  Approval -->
</logic:equal>
<logic:equal name="violationReportsForm" property="statusId" value="MA">
	 <!-- Submission Review -->
</logic:equal>	
<logic:equal name="violationReportsForm" property="statusId" value="PS">
	 <!-- Submission Approval -->
</logic:equal>	
<logic:equal name="violationReportsForm" property="statusId" value="SA">
	 <!-- Court Submission -->
</logic:equal>
<logic:equal name="violationReportsForm" property="statusId" value="FL">
	 <!--  Filed -->
</logic:equal>

<div align="center">
<!-- BEGIN PAGE TABLE -->
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
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
<!-- BEGIN SUPERVISEE INFORMATION TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE INFORMATION TABLE -->
<!-- BEGIN GREEN TABS TABLE -->		
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->							
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.violationReport"/>
												<bean:message key="title.details"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|2">
<%-- 												<logic:equal name="violationReportsForm" property="taskType" value="Approval"> --%>
												<logic:equal name="violationReportsForm" property="statusId" value="PM">
													- for Approval
												</logic:equal>
<%-- 												<logic:equal name="violationReportsForm" property="taskType" value="SubmissionReview"> --%>
												<logic:equal name="violationReportsForm" property="statusId" value="MA">
													- for Submission Review
												</logic:equal>	
<%-- 												<logic:equal name="violationReportsForm" property="taskType" value="SubmissionApproval"> --%>
												<logic:equal name="violationReportsForm" property="statusId" value="PS">
													- for Submission Approval
												</logic:equal>	
<%-- 												<logic:equal name="violationReportsForm" property="taskType" value="CourtSubmission"> --%>
												<logic:equal name="violationReportsForm" property="statusId" value="SA">
													- for Court Submission
												</logic:equal>
<%-- 												<logic:equal name="violationReportsForm" property="taskType" value="Filed"> --%>
												<logic:equal name="violationReportsForm" property="statusId" value="FL">
													- Presented
												</logic:equal>
											</td>
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
<!-- BEGIN CONFIRMATION TABLE -->
									<table width="98%" align="center">
										<tr>
											<td align="center" class="confirm"><bean:write name="violationReportsForm" property="confirmationMessage" /></td>
										</tr>
									</table>								
<!-- END CONFIRMATION TABLE -->
<!-- BEGIN STATUS TABLE -->
									<table border="0" width="99%" cellpadding="2" cellspacing="1" class="borderTable">
										<tr class="formDeLabel">
											<td><bean:message key="prompt.status" /></td>
											<td><bean:message key="prompt.status" /> <bean:message key="prompt.changed" /> <bean:message key="prompt.date" /></td>
											<td><bean:message key="prompt.createdBy" /></td>
											<td><bean:message key="prompt.createDate" /></td>
										</tr>
										<tr>
											<td><bean:write name="violationReportsForm" property="statusDesc"/></td>
											<td><bean:write name="violationReportsForm" property="statusChangedDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
											<td><bean:write name="violationReportsForm" property="createdByName"/></td>
											<td><bean:write name="violationReportsForm" property="createDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
										</tr>
									</table>
<!-- END STATUS TABLE -->	
									<br>
<!-- BEGIN FILING INFORMATION TABLE -->	
<%--									<logic:equal name="violationReportsForm" property="taskType" value="Filed">	--%>
									<logic:equal name="violationReportsForm" property="statusId" value="FL">							
										<table width="99%" border="0" cellspacing="1" cellpadding="2" class="borderTable">
											<tr class="detailHead">
												<td class="detailHead" colspan="2"><bean:message key="prompt.filingInformation" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.presentDate" /></td>
												<td class="formDe">
													<bean:write name="violationReportsForm" property="fileDateStr" formatKey="date.format.mmddyyyy"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.presentedBy" /></td>
												<td class="formDe"><bean:write name="violationReportsForm" property="presentedByName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.whoSigned?" /></td>
												<td class="formDe"><bean:write name="violationReportsForm" property="whoSignedName"/></td>
											</tr>
										</table>
									<br>
									</logic:equal>
<!-- END FILING INFORMATION TABLE -->												
<!-- BEGIN DETAILS TABLE -->
									<tiles:insert page="violationReportElementsTile.jsp" flush="true">
									</tiles:insert> 
<!-- END DETAILS TABLE -->
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table width="98%">
<%-- 										<logic:notEqual name="violationReportsForm" property="taskType" value="Filed"> --%>
										<logic:notEqual name="violationReportsForm" property="statusId" value="FL">
											<tr>
												<td align="center">
<%-- 													<logic:equal name="violationReportsForm" property="taskType" value="Approval"> --%>
													<logic:equal name="violationReportsForm" property="statusId" value="PM">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.approve" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.changeRequest" /></html:submit>
													</logic:equal>
<%-- 													<logic:equal name="violationReportsForm" property="taskType" value="SubmissionReview"> --%>
													<logic:equal name="violationReportsForm" property="statusId" value="MA">
														<logic:equal name="violationReportsForm" property="cloPosition" value="true">
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.selfApprove" /></html:submit>												
														</logic:equal>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.changeRequest" /></html:submit>
													</logic:equal>	
<%-- 													<logic:equal name="violationReportsForm" property="taskType" value="SubmissionApproval">  --%>
													<logic:equal name="violationReportsForm" property="statusId" value="PS">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.approve" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.changeRequest" /></html:submit>
													</logic:equal>	
<%-- 													<logic:equal name="violationReportsForm" property="taskType" value="CourtSubmission">	 --%>										
													<logic:equal name="violationReportsForm" property="statusId" value="SA">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.present" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.returnForResponse" /></html:submit>
													</logic:equal>
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<logic:equal name="violationReportsForm" property="allowMaintain" value="adminCrtProceedings">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.maintain" /></html:submit>
													</logic:equal>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:notEqual>
<%--										<logic:equal name="violationReportsForm" property="taskType" value="Filed">	--%>
										<logic:equal name="violationReportsForm" property="statusId" value="FL">																							
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.tasks" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.caseHistory" /></html:submit>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<logic:notEmpty name="violationReportsForm" property="caseNoteId">
														<input type="button" name="submitAction" value="<bean:message key="button.printCasenote" />" onclick="printCaseNote()" >
														<input type="hidden" name="caseNoteId" value='<bean:write name="violationReportsForm" property = "caseNoteId"/>' id="caseNoteId">
													</logic:notEmpty>
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>		
										</logic:equal>
									</table>	
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->								
						<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->					
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->		
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>