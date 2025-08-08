<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/12/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 01/26/2009 C Shimek          - #52815 revised dateformat in status block -->
<!-- 07/28/2009 C Shimek          - #61165 added Court Actions display block for Filed report status  -->
<!-- 05/24/2010 C Shimek          - #65388 added secondaryAction = blank check to button table -->
<!-- 11/08/2011 C Shimek          - #71259 change prompt.filedDate to prompt.presentDate -->
<!-- 10/03/2013 RCarter           - #76175 Add court actions update link for PRESENTED reports -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%-- @ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" --%>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/violationReportCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function printReport(){
	openWindow('handleViolationReportUpdates.do?submitAction=Print');
}
//***Begin SHOW HIDE FOR SINGLE ROW Script 
function showHideComments(what,hide)
{
	if( ! hide )
	{
		document.getElementById( what ).className = 'hidden';
	}
  else 
  {
	 	document.getElementById( what ).className = 'visible';
	 	document.getElementById( what ).focus();
	}
}
//***End SHOW HIDE FOR SINGLE ROW Script
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleViolationReportUpdates" target="content">  <!-- target="content" -->

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
                                              <logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.CREATE%>">
                                                  <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.create"/>
                                                  <bean:message key="title.violationReport"/>
                                                  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|3">
                                              </logic:equal>
                                              <logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
                                                  <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.update"/>
                                                  <bean:message key="title.violationReport"/>
                                                  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|32">
                                              </logic:equal>
                                              <logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.MAINTAIN%>">
                                                  <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.maintain"/>
                                                  <bean:message key="title.violationReport"/>
                                                  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|33">
                                              </logic:equal>
                                              <logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.DELETE%>">
                                                  <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.delete"/>
                                                  <bean:message key="title.violationReport"/>&nbsp;<bean:message key="title.summary"/>
                                                  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|5">
                                              </logic:equal>
                                              <logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.VIEW%>">
                                               <bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.violationReport"/>
											<bean:message key="title.details"/>
											<logic:equal name="violationReportsForm" property="statusId" value="FL">
												- Presented
											</logic:equal>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|2"> 
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
<!-- BEGIN INSTRUCTION TABLE -->
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.CREATE%>">
											<table width="98%" cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td>
														<ul>
															<li>To edit section - click Update</li>
														</ul>
													</td>
												</tr>
											</table>
										</logic:equal>	
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN STATUS TABLE -->
										<logic:notEqual name="violationReportsForm" property="statusId" value="">
											<table border="0" width="99%" cellpadding="2" cellspacing="1" class="borderTable">
												<tr class="formDeLabel">
													<td><bean:message key="prompt.status" /></td>
													<td><bean:message key="prompt.status" /> 
													<bean:message key="prompt.changed" /> 
													<bean:message key="prompt.date" />
													</td>
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
											<br>
											<bean:define id="hideBack" value="Y" />
										</logic:notEqual>	
<!-- END STATUS TABLE -->	
										<logic:equal name="violationReportsForm" property="statusId" value="FL">
<!-- BEGIN FILING INFORMATION TABLE -->									
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
<!-- END FILING INFORMATION TABLE -->												
										</logic:equal>
<!-- BEGIN DETAILS TABLE -->
										<tiles:insert page="violationReportElementsTile.jsp" flush="true">
										</tiles:insert> 
<!-- END DETAILS TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->										
<!-- BEGIN BUTTON TABLE -->
									<table width="98%" align="center" >
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.VIEW%>">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<logic:equal name="violationReportsForm" property="allowMaintain" value="<%=UIConstants.YES%>">  
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.maintain" /></html:submit>
													</logic:equal>
													<logic:notEqual name="violationReportsForm" property="statusId" value="FL">
														<logic:equal name="violationReportsForm" property="taskflowInd" value="true"> 
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.update" /></html:submit>
														</logic:equal>
													</logic:notEqual> 		
													<logic:equal name="violationReportsForm" property="statusId" value="DR">
														<logic:notEqual name="violationReportsForm" property="taskflowInd" value="true">
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.update" /></html:submit>
														</logic:notEqual>
													</logic:equal>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:equal>	
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.DELETE%>">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.delete" /></html:submit>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:equal>	
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.CREATE%>">
											<logic:notEqual name="violationReportsForm" property="statusId" value="">
												<tr>
													<td align="center">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.violationReportsList" /></html:submit>
													</td>
												</tr>
											</logic:notEqual>
											<tr>
												<td align="center">
													<logic:present name="hideBack">
														<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													</logic:present>
													<logic:notPresent name="hideBack">
														<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
														<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
														<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
													</logic:notPresent>
												</td>
											</tr>
										</logic:equal>	
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.violationReportsList" /></html:submit>
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:equal>	
										<logic:equal name="violationReportsForm" property="secondaryAction" value="<%=UIConstants.MAINTAIN%>">
											<logic:notEqual name="violationReportsForm" property="statusId" value="DR" >
												<logic:notEqual name="violationReportsForm" property="statusId" value="FL" >
													<tr>
														<td align="center">
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.file/CourtActions" /></html:submit>
														</td>
													</tr>
												</logic:notEqual>
											</logic:notEqual>
											<logic:equal name="violationReportsForm" property="statusId" value="DR" > 
												<tr>
													<td align="center">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.submitForApproval" /></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.violationReportsList" /></html:submit>
													</td>
												</tr>
											</logic:equal>
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
													<input type="button" name="submitAction" value="<bean:message key="button.print" />" onclick="printReport()" >
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:equal>
										<logic:equal name="violationReportsForm" property="secondaryAction" value="">
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:equal>
									</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->					
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->		
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>