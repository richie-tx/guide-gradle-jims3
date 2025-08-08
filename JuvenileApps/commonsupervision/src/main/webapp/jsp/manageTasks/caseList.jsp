<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>

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
	<title><bean:message key="title.heading" /> - assignSupervisee/caseList.jsp</title>

	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
	
<body topmargin=0 leftmargin="0">
	<html:form action="/handleCaseAssignment" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Caseload/CSCD_Caseload.htm#|13">
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top">
						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--blue tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>		
									<!--blue tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
						</table>
						<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign="top" align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header" >Review Active Cases</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Click Next.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
											
									<br>
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class=detailHead>
											<td>
												Active Cases
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td>Case</td>
														<td>Court</td>
														<td>Program Unit</td>
														<td>Officer</td>
													</tr>
													<% int RecordCounter=0;
													   String bgcolor="";
													%>
								                    <logic:iterate id="caseIndex" name="caseAssignmentForm" property="activeCases">
								  						<tr class= <% RecordCounter++; 
											              bgcolor = "alternateRow";                      
											              if (RecordCounter % 2 == 1)
											                  bgcolor = "normalRow";
										                   out.print(bgcolor); %>>
															<td><bean:write name="caseIndex" property="criminalCaseId"/></td>
															<td><bean:write name="caseIndex" property="courtId"/></td>
															<td><bean:write name="caseIndex" property="programUnitName" /></td>
															<td><bean:write name="caseIndex" property="officerName"/></td>	
														</tr>
													</logic:iterate>
												</table>
											</td>
										</tr>
									</table>
									<br>
									<logic:notEqual name="caseAssignmentForm" property="<%=CaseloadConstants.ACTIVITY_IND%>" value="<%=CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SELECT_PU%>">
									<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class=detailHead>
											<td>
												You are about to acknowledge possession of the following case
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td>Case</td>
														<td>Court</td>
														<td>Program Unit</td>
														<td>Officer</td>
													</tr>
													<tr>
														<td><bean:write name="caseAssignmentForm" property="caseToAcknowledge.criminalCaseId"/></td>
														<td><bean:write name="caseAssignmentForm" property="caseToAcknowledge.courtId"/></td>
														<td><bean:write name="caseAssignmentForm" property="caseToAcknowledge.programUnitName"/></td>
														<logic:equal name="caseAssignmentForm" property="<%=CaseloadConstants.ACTIVITY_IND%>" value="<%=CaseloadConstants.WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR_SELECT_SUP%>">
														<td><bean:write name="caseAssignmentForm" property="caseToAcknowledge.officerName"/></td>
														</logic:equal>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									</logic:notEqual>
									<!-- END DETAIL TABLE -->
									
									<br>
									
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
											<html:submit property="submitAction">
												<bean:message key="button.back" />
											</html:submit> 
											<logic:equal name="caseAssignmentForm" property="<%=CaseloadConstants.ACTIVITY_IND%>"
												value="<%=CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SELECT_PU%>">
												<html:submit property="submitAction">
													<bean:message key="button.next" />
												</html:submit>
											</logic:equal>
											<logic:notEqual name="caseAssignmentForm" property="<%=CaseloadConstants.ACTIVITY_IND%>" 
												value="<%=CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_SELECT_PU%>">
												<html:submit property="submitAction">
													<bean:message key="button.paperFileReceived" />
												</html:submit>
											</logic:notEqual>
											 
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
						<br>
					</td>
				</tr>
			</table>
			</div>
		</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
	</body>
</html:html>
