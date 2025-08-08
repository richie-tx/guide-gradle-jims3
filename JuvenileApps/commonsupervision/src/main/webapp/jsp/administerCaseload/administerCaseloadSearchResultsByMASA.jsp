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
	<title>CommonSupervision/administerCaseload/administerCaseloadSearchResultsByMASA.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript">
		function validateCaseSelection() {
			var selected = false;
			var elements = document.getElementsByName("casesSelectedForReassignment");
			if (elements.length > 0) {
				for (i = 0; i < elements.length; i++) {
					var element = document.getElementsByName("casesSelectedForReassignment")[i];
					if (element.checked) {
						selected = true;
						break;
					}
				}
			}
			if (!selected) {
				alert ("Please select a case.");
			}
			return selected;
		}
	</script>	
</head>

<body topmargin=0 leftmargin="0">
	<html:form action="/handleSuperviseeSearchByMASAAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|2">
			<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top>
						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
									<!--tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5><a href="caseloadList.htm">Office MGR</a> | <a href="caseloadListCLO.htm">CLO</a> | <a href="caseloadListCSO.htm">Officer</a> | <a href="caseloadListMASA.htm">MA/SA</a> | <a href="caseloadListAsstSup.htm">Asst Supervisor</a></td>
							</tr>
						</table>
						<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;
												<bean:message key="prompt.caseload"/>&nbsp;<bean:message key="prompt.searchResults"/> - MA / SA</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->

									<!-- example of single result from SPN Search-->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class="detailHead">
											<td>Caseload Supervisee(s)</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td width="1%"></td>
														<td>Supervisee Name</td>
														<td>SPN</td>
														<td title="Level Of Supervision">LOS</td>
														<td title="Next Court Date">NxtCtDate</td>
														<td title="Last Office Visit">Last OV</td>
														<td>Days Left</td>
													</tr>
													<tr>
														<td width=1%>
															<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name="caseAssignmentForm" property="defendantId"/>">
																<img src="/<msp:webapp/>images/page_go.png" alt="View Casenotes" width="16" height="16" border="0" />
															</a>																	
														</td>																										
														<td width=1%>
															<a href="javascript:showHide('img<bean:write name="caseAssignmentForm" property="defendantId" />', 
																						 '', '/<msp:webapp/>');" title="View Cases">
																<img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="caseAssignmentForm" property="defendantId" />" border="0" />
															</a>
														</td>
														<td>
															<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name="caseAssignmentForm" property="defendantId"/>">
																<msp:formatter name="caseAssignmentForm" property="superviseeName" format="L,F"/>
															</a>
														</td>												
														<td><bean:write name="caseAssignmentForm" property="defendantId"/></td>
														<td><bean:write name="caseAssignmentForm" property="levelOfSupervision"/></td>
														<td><bean:write name="caseAssignmentForm" property="jailIndicator"/></td>
														<td><bean:write name="caseAssignmentForm" property="warrantIndicator"/></td>
														<td><bean:write name="caseAssignmentForm" property="nextOfficeVisitDate"/></td>
														<td><bean:write name="caseAssignmentForm" property="lastFaceToFaceDate"/></td>
														<logic:lessEqual name="caseAssignmentForm" property="daysLeft" value="90">
															<logic:greaterEqual name="caseAssignmentForm" property="daysLeft" value="0">
																<td><bean:write name="caseAssignmentForm" property="daysLeft"/></td>
															</logic:greaterEqual>
															<logic:lessThan name="caseAssignmentForm" property="daysLeft" value="0">
																<td class=changedRed><bean:write name="caseAssignmentForm" property="daysLeft"/></td>
															</logic:lessThan>
														</logic:lessEqual>														
														<logic:greaterThan name="caseAssignmentForm" property="daysLeft" value="90">
															<td></td>
														</logic:greaterThan>
													</tr>
													<!-- <tr id="sup99Cases" class="hidden">  -->
													<tr>
														<td></td>
														<td colspan=9>
															<span id="img<bean:write name="caseAssignmentForm" property="defendantId" />Span" class="hidden">
																<table width="100%" cellpadding="2" cellspacing="1">
																	<tr class=formDeLabel>
																		<td width="1%"></td>
																		<td width=5% title="Degree of Offense">DEG</td>
																		<td width=50% title="Case No">Case</td>
																		<td width=1% title="Court">CRT</td>
																		<td width=15% title="Start Date of Community Supervision">DOCS</td>
																		<td width=15% title="End Date of Community Supervision">End Date</td>
																		<td width=15% title="Days Left for Probation" >Days Left</td>
																		<td width=1% title="Case Status">C</td>
																		<td width=1% title="Defendant Status">D</td>
																	</tr>
																	<% int RecordCounter=0;
																	   String bgcolor="";
																	%>																	
																	<logic:iterate id="activeCase" name="caseAssignmentForm" property="activeCases">
																		<tr class= <% RecordCounter++; 
																		              bgcolor = "alternateRow";                      
																		              if (RecordCounter % 2 == 1)
																		                  bgcolor = "normalRow";
																	                  out.print(bgcolor); %> >
																	        <td>
																	        	<html:multibox property="casesSelectedForReassignment">
																	        		<bean:write name="activeCase" property="caseAssignmentId"/>
																	        	</html:multibox>
																	        </td>
																			<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																			<td>
																				<a href="/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="caseAssignmentId"/>">
																					<bean:write name="activeCase" property="caseAssignmentId"/>
																				</a>
																			</td>																			
																			<td><bean:write name="activeCase" property="courtId"/></td>
																			<td><bean:write name="activeCase" property="probationStartDate"/></td>
																			<td><bean:write name="activeCase" property="probationEndDate"/></td>
																			<logic:equal name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																				<td/>
																			</logic:equal>
																			<logic:notEqual name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																				<td><bean:write name="activeCase" property="daysLeft"/></td>
																			</logic:notEqual>
																			<td><bean:write name="activeCase" property="caseStatus"/></td>
																			<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
																		</tr>
																	</logic:iterate>
																</table>
															</span>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!-- Single result from SPN Search end-->
									<!-- END DETAIL TABLE -->
									
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateCaseSelection();">
													<bean:message key="button.reassign" />
												</html:submit> 
												<html:submit property="submitAction" disabled="true">
													<bean:message key="button.reports" />
												</html:submit> 
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->									
								</td>
							</tr>
						</table>
						<!-- END  TABLE -->
					</td>
				</tr>
			</table>
		</div>
		<br>			
	</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
