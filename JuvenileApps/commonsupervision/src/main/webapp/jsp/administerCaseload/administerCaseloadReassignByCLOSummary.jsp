<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--02/28/2008	LDeen	Activity #49819 integrate help    --%>
<%--08/27/2010	CShimek Activity #64291 revised bean write under selected cases to display seperated cdi and case number   --%>

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
	<title>CommonSupervision/administerCaseload/administerCaseloadReassignByCLOSummary.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<body topmargin=0 leftmargin="0">
	<html:form action="/reassignSuperviseeByCLOSummaryAction" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Caseload/CSCD_Caseload.htm#|11">
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
									Reassign Case(s) to CSO - Task Summary</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center" id="errorTable">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
						<!-- END ERROR TABLE -->												
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul><li>Review task information. Click Finish.</li></ul></td>
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
										<logic:iterate id="activeCase" name="caseAssignmentForm" property="activeCasesSelectedForReassignment" indexId="index">
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
												<td><bean:write name="activeCase" property="cdi"/> <bean:write name="activeCase" property="displayCaseNum"/></td>
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
						<div class=''spacer4px'></div>
						<!-- END DETAIL TABLE -->
						<!-- BEGIN TASK INFORMATION TABLE -->			
						<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr class="detailHead">
								<td>Task Information</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="4" cellspacing="1">
										<tr>
											<td class=formDeLabel>To</td>
											<td class=formDe>
												<bean:write name="caseAssignmentForm" property="workGroupName"/>												
											</td>
										</tr><tr>
											<td class=formDeLabel width=1% nowrap>Subject</td>
											<td class=formDe>
												<bean:write name="caseAssignmentForm" property="requestReassignmentTaskSubject"/>
											</td>
										</tr>
										<%--<tr>
											<td class=formDeLabel width=1% nowrap>Next Action</td>
											<td class=formDe>												
												<bean:write name="caseAssignmentForm" property="requestReassignmentTaskNextAction"/>
											</td>
										</tr>--%>
										<tr>
											<td class=formDeLabel colspan=2>Task Text</td>
										</tr>
										<tr>
											<td class=formDe colspan=2>
												<bean:write name="caseAssignmentForm" property="requestReassignmentTaskNote"/>
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
										<bean:message key="button.finish" />
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
			</table>	</td>
				</tr>
			</table>				
		</div>
	</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
		
