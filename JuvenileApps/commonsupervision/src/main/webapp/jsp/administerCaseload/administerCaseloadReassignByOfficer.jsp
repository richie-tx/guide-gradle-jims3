<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--02/28/2008	LDeen	Activity #49819 integrate help    --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

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
	<title>CommonSupervision/administerCaseload/administerCaseloadReassignByOfficer.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<body topmargin=0 leftmargin="0">
	<html:form action="/reassignSuperviseeByOfficerAction" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|32">
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;
									Reassignment Request</td>
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
								<td><ul><li>Review the selected cases and enter required fields. Click Next.</li></ul></td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<!--header start-->
						<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
						</tiles:insert>
						<!--header end--> 
						<div class=spacer4px></div>
						<!-- BEGIN DETAIL TABLE -->
						<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr class=detailHead>
								<td>
									Selected Cases
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr class=formDeLabel>
											<td width=5% title="Degree of Offense">DEG</td>
											<td width=50% title="Case No">Case</td>
											<td width=1% title="Court">CRT</td>
											<td width=15% title="Start Date of Community Supervision">DOCS</td>
											<td width=15% title="End Date of Community Supervision">End Date</td>
											<td width=1% title="Case Status">C</td>
											<td width=1% title="Defendant Status">D</td>
										</tr>
										<% int RecordCounter=0;
										   String bgcolor="";
										%>																	
										<logic:iterate id="activeCase" name="caseAssignmentForm" property="activeCasesSelectedForReassignment">
											<tr class= <% RecordCounter++; 
											              bgcolor = "alternateRow";                      
											              if (RecordCounter % 2 == 1)
											                  bgcolor = "normalRow";
										                  out.print(bgcolor); %> >
												<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
												<td><bean:write name="activeCase" property="criminalCaseId"/></td>
												<td><bean:write name="activeCase" property="courtId"/></td>
												<td><bean:write name="activeCase" property="probationStartDate"/></td>
												<td><bean:write name="activeCase" property="probationEndDate"/></td>
												<td><bean:write name="activeCase" property="caseStatus"/></td>
												<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
											</tr>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</table>
						<div class=spacer4px></div>
						<!-- END DETAIL TABLE -->
						<table width="98%" cellpadding="1" cellspacing="1">
							<tr>
								<td class=required colspan=2><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Field</td>
							</tr>
						</table>
						<!-- BEGIN TASK INFORMATION TABLE -->			
						<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr class="detailHead">
								<td>Task Information</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class=formDeLabel><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>To</td>
											<td class=formDe>
												<html:select property="supervisorPositionId">
													<html:optionsCollection name="caseAssignmentForm" property="supervisionStaff" value="staffPositionId" label="assignedName.formattedName"/> 
													<%-- <html:optionsCollection name="caseAssignmentForm" property="supervisionStaff" value="staffPositionId" label="staffPositionId"/> --%>
												</html:select>																										
											</td>
										</tr><tr>
											<td class=formDeLabel width=1% nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Subject</td>
											<td class=formDe>
												<html:text size="50" maxlength="75" property="requestReassignmentTaskSubject" ></html:text>
											</td>
										</tr>
										<%--<tr>
											<td class=formDeLabel width=1% nowrap>Next Action</td>
											<td class=formDe>												
												<html:text readonly="true" size="40" property="requestReassignmentTaskNextAction" value="Case Reassignment Approval Request"></html:text>
											</td>
										</tr>--%>
										<tr>
											<td class=formDeLabel colspan=2><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Task Text</td>
										</tr>
										<tr>
											<td class=formDe colspan=2>
												<html:textarea property="requestReassignmentTaskNote" rows="3" style="width:100%;"></html:textarea>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- END TASK INFORMATION TABLE -->		
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction">
										<bean:message key="button.back" />
									</html:submit> 
									<html:submit property="submitAction">
										<bean:message key="button.next" />
									</html:submit> 
									<html:submit property="submitAction">
										<bean:message key="button.cancel" />
									</html:submit> 
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
		
