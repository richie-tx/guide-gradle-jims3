<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- 06/05/2008	 Leslie Deen	#52283 Add Help File --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ page import="naming.Features" %>
<%@page import="messaging.administercaseload.reply.CaseAssignmentResponseEvent"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<head>
	<!-- This is the SPN Search for Office Mgr -->
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
	<title>Common Supervision - csCalendar/fieldVisit/caseloadSearchResults.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<body topmargin="0" leftmargin="0">
	<html:form action="/handleCaseloadSelection" target="content">
		
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
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--tabs end-->
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
								<td valign=top align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.caseload" />&nbsp;<bean:message key="prompt.searchResults" /> -
												
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="addAttendees">
													Add Attendees
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|50">
												</logic:equal>
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
														Office Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|45">
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
														Field Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|51">
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
														Group Office Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|46">
													</logic:equal>	
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
									
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>
														<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="addAttendees">
															Select Supervisee Case(s) and click Add Selected
														</logic:equal>
														<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">
															<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
																Select a Supervisee Case and click Create Office Visit
															</logic:equal>
															<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
																Select a Supervisee Case and click Create Field Visit
															</logic:equal>
															<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
																Select Supervisee Case(s) and click Create Group Office Visit
															</logic:equal>
														</logic:equal>
														
														
														
													</li>
												</ul>
											</td>
										</tr>
									</table>

									<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
										<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
											<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
											<tiles:put name="displayEvents" value="true"/>
										</tiles:insert>
										<div class="spacer4px"></div>
									</logic:equal>
									
									<!-- END ERROR TABLE -->									
									<logic:notPresent name="org.apache.struts.action.ERROR"> 
										<!-- example of single result from SPN Search-->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Caseload Supervisee(s)</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr class="formDeLabel">
															
															<jims2:if name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV" op="equal">
																<jims2:or name="csCalendarDisplayForm" property="activityFlow" value="addAttendees" op="equal">
																	<td width="1%"></td>
																</jims2:or>	
															</jims2:if>
															<td>Supervisee Name</td>
															<td>SPN</td>
															<td title="Level Of Supervision">LOS</td>
															<td title="Jail Indicator">J</td>
															<td title="Warrant Indicator">W</td>
															<td title="Next Office Visit Date">Next OV</td>
															<td title="Last Face to Face">Last F2F</td>
															<td>Days Left</td>
														</tr>
														<logic:notEmpty name="csCalendarDisplayForm" property="searchBySPNResult">
														
														<input type="hidden" name="superviseeId" value="<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId' />">
														<tr>
															<td width="1%" nowrap="nowrap"><input type="radio" name="selectedValue" value="" onclick=""/>
																<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId'/>">
																	<img src="/<msp:webapp/>images/page_go.png" alt="View Casenotes" width="16" height="16" border="0" />
																</a>																	
																<a href="javascript:showHide('img<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId' />', 
																							 '', '/<msp:webapp/>');" title="View Cases">
																	<img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId' />" border="0" />
																</a>
															</td>
															<td>
																<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId'/>">
																	<bean:write name="csCalendarDisplayForm" property="searchBySPNResult.defendantFullName"/>
																</a>
															</td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.defendantId"/></td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.levelOfSupervision"/></td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.jailIndicator"/></td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.warrantIndicator"/></td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.lastFaceToFaceDate" formatKey="date.format.mmddyyyy"/></td>
															<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.nextOfficeVisitDate" formatKey="date.format.mmddyyyy"/></td>
															<logic:lessEqual name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft" value="90">
																<logic:greaterEqual name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft" value="31">
																	<td><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft"/></td>
																</logic:greaterEqual>
																<logic:lessEqual name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft" value="30">
																	<td class="caseloadDaysLeft"><bean:write name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft"/></td>
																</logic:lessEqual>
															</logic:lessEqual>														
															<logic:greaterThan name="csCalendarDisplayForm" property="searchBySPNResult.daysLeft" value="90">
																<td></td>
															</logic:greaterThan>
														</tr>
														<!-- <tr id="sup99Cases" class="hidden">  -->
														<tr>
															<td colspan="9">
																<span id="img<bean:write name='csCalendarDisplayForm' property='searchBySPNResult.defendantId' />Span" class="hidden">
																	<table width="100%" cellpadding="2" cellspacing="1">
																		<tr class="formDeLabel">
																			<td width="5%" title="Degree of Offense">DEG</td>
																			<td width="50%" title="Case No">Case</td>
																			<td width="1%" title="Court">CRT</td>
																			<td width="15%" title="Start Date of Community Supervision">DOCS</td>
																			<td width="15%" title="End Date of Community Supervision">End Date</td>
																			<td width="15%">Days Left</td>
																			<td width="1%" title="Case Status">C</td>
																			<td width="1%" title="Defendant Status">D</td>
																		</tr>
																		<% int RecordCounter=0;
																		   String bgcolor="";
																		%>																	
																		<logic:iterate id="activeCase" name="csCalendarDisplayForm" property="searchBySPNResult.caseAssignments">
																			<tr class= <% RecordCounter++; 
																			              bgcolor = "alternateRow";                      
																			              if (RecordCounter % 2 == 1)
																			                  bgcolor = "normalRow";
																		                  out.print(bgcolor); %> >
																		        	
																				<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																				<td>																				
																					<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">
																						<bean:write name="activeCase" property="criminalCaseId"/>
																					</a>
																					<%-- if the case is OOC show ICAOS link --%>
																					<elogic:if name="activeCase" property="courtId" op="equal" value="INF">
																					<elogic:or name="activeCase" property="courtId" op="equal" value="INM" />
																					<elogic:or name="activeCase" property="courtId" op="equal" value="OTF" />
																					<elogic:or name="activeCase" property="courtId" op="equal" value="OTM" />																								
																					<elogic:then>
																					<span style="padding-left:7px"><a href="http://www.interstatecompact.org/" class=editLink target="_new">ICAOS</a></span>
																					</elogic:then>
																					</elogic:if>
																				</td>																																																									
																				<td><bean:write name="activeCase" property="courtId"/></td>
																				<td><bean:write name="activeCase" property="probationStartDate"/></td>
																				<td><bean:write name="activeCase" property="probationEndDate"/></td>
																				<logic:equal name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																				<td></td>
																				</logic:equal>
																				<logic:notEqual name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																				<logic:greaterEqual name="activeCase" property="daysLeft" value="31">
																					<td><bean:write name="activeCase" property="daysLeft"/></td>
																				</logic:greaterEqual>
																				<logic:lessEqual name="activeCase" property="daysLeft" value="30">
																					<td class="caseloadDaysLeft"><bean:write name="activeCase" property="daysLeft"/></td>
																				</logic:lessEqual>
																				</logic:notEqual>
																				<td><bean:write name="activeCase" property="caseStatus"/></td>
																				<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
																			</tr>
																		</logic:iterate>
																	</table>
																</span>
															</td>
														</tr>
														</logic:notEmpty>
														<!-- END SPN DISPLAY -->
														<!-- BEGIN SUPERVISE DISPLAY -->
														<logic:notEmpty name="csCalendarDisplayForm" property="defendantsSupervised">
															<logic:iterate id="supervisee" name="csCalendarDisplayForm" property="defendantsSupervised" indexId="indexSPN">
														<%--		<pg:item>   --%>
																<tr class="<%out.print((indexSPN.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																<td>
																	<table width="100%" border="0">
																	<tr>
																		<td>
																		     <logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">																	
																				<html:checkbox styleId="caseSuperviseeIds" name="csCalendarDisplayForm" property="caseSuperviseeIds" value="<%=((CaseAssignmentResponseEvent)supervisee).getDefendantId()%>" />
																			 </logic:equal>
																			 <logic:notEqual name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
																				<html:radio styleId="caseSuperviseeId" name="csCalendarDisplayForm" property="caseSuperviseeId" value="<%=((CaseAssignmentResponseEvent)supervisee).getDefendantId()%>" />
																			 </logic:notEqual>
																		</td>
																		<td width="1%">
																				<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name="supervisee" property="defendantId"/>">
																					<img src="/<msp:webapp/>images/page_go.png" alt="View Casenotes" width="16" height="16" border="0" />
																					</a>																	
																		</td>
																		<td width="1%">
																			<a href="javascript:showHide('img<bean:write name="supervisee" property="defendantId" />', 
																								 '', '/<msp:webapp/>');" title="View Cases">
																				<img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="supervisee" property="defendantId" />" border="0" />
																			</a>
																		</td>
																	</tr>
																	</table>
																</td>	
																	<td>
																		<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="supervisee" property="defendantId"/>">
																			<bean:write name="supervisee" property="defendantFullName"/>
																		</a>
																	</td>
																	<td><bean:write name="supervisee" property="defendantId"/></td>
																	<td><bean:write name="supervisee" property="levelOfSupervision"/></td>
																	<td><bean:write name="supervisee" property="jailIndicator"/></td>
																	<td><bean:write name="supervisee" property="warrantIndicator"/></td>
																	<td><bean:write name="supervisee" property="formattedNextOfficeVisitDate"/></td>
																	<td><bean:write name="supervisee" property="formattedLastFaceToFaceDate"/></td>
																	<logic:greaterEqual name="supervisee" property="daysLeft" value="0">
																		<td><bean:write name="supervisee" property="daysLeft"/></td>
																	</logic:greaterEqual>
																	<logic:lessThan name="supervisee" property="daysLeft" value="30">
																		<td class="caseloadDaysLeft" colspan="2"><bean:write name="supervisee" property="daysLeft"/></td>
																	</logic:lessThan>											
																	<logic:greaterThan name="supervisee" property="daysLeft" value="90">
																		<td colspan="2"></td>
																	</logic:greaterThan>
																</tr> 
																<tr>
																	<td></td>
																	<td colspan="10">
																		<span id="img<bean:write name="supervisee" property="defendantId" />Span" class="hidden">
																			<table width="98%" align="center" cellpadding="2" cellspacing="1" >
																				<tr class="formDeLabel">
																					<td width="1%"/>
																					<td width="5%" title="Degree of Offense">DEG</td>
																					<td width="35%" title="Case No">Case</td>
																					<td width="1%" title="Court">CRT</td>
																					<td width="15%" title="Start Date of Community Supervision">DOCS</td>
																					<td width="15%" title="End Date of Community Supervision">End Date</td>
																					<td width="15%">Days Left</td>
																					<td width="1%" title="Case Status">C</td>
																					<td width="1%" title="Defendant Status">D</td>
																				</tr>
																				<logic:iterate id="activeCase" name="supervisee" property="caseAssignments" indexId="index">
																					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																				        <td>
																				        </td>
																						<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																						<td>		
																							<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">																																						
																								<bean:write name="activeCase" property="criminalCaseId"/>
																							</a>
																							<!-- if the case is OOC show ICAOS link -->
																							<elogic:if name="activeCase" property="courtId" op="equal" value="INF">
																							<elogic:or name="activeCase" property="courtId" op="equal" value="INM" />
																							<elogic:or name="activeCase" property="courtId" op="equal" value="OTF" />
																							<elogic:or name="activeCase" property="courtId" op="equal" value="OTM" />																								
																							<elogic:then>
																							<span style="padding-left:7px"><a href="http://www.interstatecompact.org/" class="editLink" target="_new">ICAOS</a></span>
																							</elogic:then>
																							</elogic:if>
																						</td>																																																									
																						<td><bean:write name="activeCase" property="courtId"/></td>
																						<td><bean:write name="activeCase" property="probationStartDate"/></td>
																						<td><bean:write name="activeCase" property="probationEndDate"/></td>
																						<logic:equal name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																							<td></td>
																						</logic:equal>
																						<logic:notEqual name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																						<logic:greaterEqual name="activeCase" property="daysLeft" value="31">
																							<td><bean:write name="activeCase" property="daysLeft"/></td>
																						</logic:greaterEqual>
																						<logic:lessEqual name="activeCase" property="daysLeft" value="30">
																							<td class="caseloadDaysLeft"><bean:write name="activeCase" property="daysLeft"/></td>
																						</logic:lessEqual>
																						</logic:notEqual>
																						<td><bean:write name="activeCase" property="caseStatus"/></td>
																						<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
																					</tr>
																				</logic:iterate>
																			</table>
																		</span>
																	</td>
																</tr>			
														<%-- 	</pg:item>	 --%>												
															</logic:iterate> 
															<!-- END SUPERVISEE DISPLAY -->
														</logic:notEmpty>  
													</table>
												</td>
											</tr>
										</table>
										
										<%-- 
										<table width="98%" cellpadding="0" cellspacing="0" border=0>
											<tr>
												<td class=legendSmallText>
													<img src="/<msp:webapp/>images/flag_blue.gif" title="Open Program Referrals" border=0 align=left> Open Program Referrals
												</td>
											</tr>
											<tr>
												<td class=legendSmallText>
													<img src="/<msp:webapp/>images/flag_red.gif" title="Pending New Case Assignment" border=0 align=left> Pending New Case Assignment
												</td>
											</tr>
											<tr>
												<td class=legendSmallText>
													<img src="/<msp:webapp/>images/flag_yellow.gif" title="Pending Case Reassignment" border=0 align=left> Pending Reassignment
												</td>
											</tr>
										</table>
										--%>
										<!-- example of single result from SPN Search end-->
										<!-- END DETAIL TABLE -->
										
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
												
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="addAttendees">
													<input type="submit" value="<bean:message key='button.addSelected' />" name="submitAction" 
														onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSGVSummary.do?submitAction=Next',false);">
												</logic:equal>
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_CREATE%>'>
														<html:submit property="submitAction">
															<bean:message key="button.createFieldVisit" />
														</html:submit> 
													</jims2:isAllowed>	
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_CREATE%>'>
														<input type="submit" value="<bean:message key='button.createOfficeVisit' />" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSOVCreateUpdate.do',false);">
														</jims2:isAllowed>	
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
														<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="addAttendees">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALGOV_CREATE%>'>
															<input type="submit" value="<bean:message key='button.createGroupOfficeVisit' />" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSGVCreateUpdate.do',false);">
														</jims2:isAllowed>
														</logic:notEqual>
														
													</logic:equal>
												</logic:equal>	
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
									</logic:notPresent>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction">
													<bean:message key="button.back" />
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
						<!-- END  TABLE -->
					</td>
				</tr>
			</table>
		</div>
		<br>			
	</html:form>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
