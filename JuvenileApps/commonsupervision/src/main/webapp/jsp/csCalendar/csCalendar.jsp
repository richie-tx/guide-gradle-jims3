<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja	Create JSP -->
<!-- 06/05/2008	 Leslie Deen	#52283 Add Help File -->
<!-- 10/08/2008	 Richard Young	#53169 Add feature around add event button -->
<!-- 02/24/2010	 C Shimek       #64116 Replaced sortResult in officerRow and SupervisorRow (3/29/10) with sort in action -->
<!-- 06/04/2010	 C Shimek       #65679 Revised disableEmptyList() and added <div> around calendar display to handle user without staff position -->
<!-- 01/25/2010  C Shimek       #67874 Revised Add New Event button to use submitAction -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.Features"%>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/blueCalendarSkin.css" />
<html:base />
<title>Common Supervision - csCalendar/csCalendar.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
	
<script type='text/javascript'>
function changeSupervisor(theForm){
	theForm.officer.selectedIndex=0;
	show("officerRow", 0);
}
	
function disableEmptyList() {
	if (typeof(document.forms[0].selectedDivisionId) != "undefined") {
		var divisions = document.forms[0].selectedDivisionId;
		if(divisions.options.length < 2) {
			divisions.disabled=true;
			document.getElementById("GetSupervisorsButton").disabled=true;
		}	
	}
	if (typeof(document.forms[0].selectedSupervisorId) != "undefined") {
		var supervisors = document.forms[0].selectedSupervisorId;
		var officers = document.forms[0].selectedOfficerId;
		if(supervisors.options.length < 2) {
			supervisors.disabled=true;
			document.getElementById("GetOfficersButton").disabled=true;
			document.getElementById("theCalendar").className='hidden';
		}
				
		if(officers.options.length < 2) {
			officers.disabled=true;
		}
	} else {
		document.getElementById("theCalendar").className='hidden';
	}	
}
</script>
</head>
	<body topmargin="0" leftmargin="0" onload="disableEmptyList()">
		<html:form action="/displayCSCalendar" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|1">
		<input type="hidden" name="context" value="P">
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
											<tiles:put name="tab" value="calendarTab"/>
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
										<!--header area start-->
								
										<!-- BEGIN HEADING TABLE -->										
										<div class="header"><bean:message key="title.CSCD"/> - <bean:message key="prompt.calendar"/></div>
										
										<table width="98%" border="0"> 
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTRUCTION TABLE -->
										
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Click Events link to view all the events for that day. Click Add New Event to add an event.</li>
													</ul>
												</td>
											</tr>
										</table>
										<!-- END INSTRUCTION TABLE -->
										<!-- BEGIN DETAIL TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td>Search Calendars</td>
												</tr>
												<tr>
													<td>
														<table width="100%" border="0" cellpadding="2" cellspacing="1">
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_VIEW%>'>
															<tr id=divisionRow>
																<jims2:sortResults hideMe="true" beanName="csCalendarDisplayForm" results="divisionList" 
																	primaryPropSort="positionName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="16" />
																<td class="formDeLabel" width="1%"><bean:message key="prompt.division" /></td>
																<td class="formDe" colspan="3">
																	<html:select size="1" property="selectedDivisionId">
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																		<html:optionsCollection property="divisionList" value="organizationId" label="assignedNameQualifiedByPositionName" />
																	</html:select>
																	<html:submit styleId="GetSupervisorsButton" property="submitAction"> <bean:message key="button.getSupervisors" /></html:submit>
																	</td>
															</tr>		
															</jims2:isAllowed>		
																	
															<tr id="supervisorRow">
																<td class="formDeLabel" width="1%"><bean:message key="prompt.supervisor" /></td>
																<td class=formDe colspan=3>
																	<html:select size="1" property="selectedSupervisorId">
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																		<html:optionsCollection property="supervisorList" value="staffPositionId" label="assignedNameQualifiedByPositionName" />
																	</html:select>
																	<html:submit styleId="GetOfficersButton" property="submitAction"> <bean:message key="button.getOfficers" /></html:submit>
																</td>
															</tr>			
															<tr id="officerRow">
																<td class='formDeLabel' width='1%'><bean:message key="prompt.officer" /></td>
																<td class="formDe" colspan="3">
																	<html:select size="1" property="selectedOfficerId">
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																		<html:optionsCollection property="officerList" value="staffPositionId" label="assignedNameQualifiedByPositionName" />
																	</html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"></td>
																<td class="formDe">
																	<html:submit property="submitAction"> <bean:message key="button.viewCalendar" /></html:submit>
																	<input type="button" value=Refresh onclick=reloadPage()>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<div class=spacer4px></div>
											
											<bean:define id="officerName" name="csCalendarDisplayForm" property="selectedOfficerName" type="String"/>
											<bean:define id="positionId"><bean:write name="csCalendarDisplayForm" property="positionId"/></bean:define>
											<bean:define id="eventLinkURL">/<msp:webapp/>displayCSEventList.do?submitAction=Link&context=P&positionId=<bean:write name="csCalendarDisplayForm" property="positionId"/></bean:define>
											<div id="theCalendar" class="visible">
												<%
													Boolean need = (Boolean)request.getAttribute("calendarNeedsRefresh");
													boolean needsRefresh = true;
													if (need!=null){
														needsRefresh = need.booleanValue();
													}
												%>
												
												<jims2:cscalendar
													calendarStyleSheet="blueCalendarSkin.css"
													serviceEvent="messaging.cscdcalendar.GetMonthlyCSCalendarEvent"
													eventTimeFormat="h:mma"
													weekDayViewType="FULLTEXT"
													eventLink="<%=eventLinkURL%>"
													dayDisplayClass="ui.taglib.ConsolidatedCSEventDayPresentation"
													title="<%=officerName%>"
													currentContext="P"
													needsRefresh="<%=needsRefresh%>"
													positionId="<%=positionId%>">
													
												</jims2:cscalendar>
												
												<logic:messagesNotPresent> 
	
													<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OTR_CREATE%>'>
														<tr>
															<td align="center">
																<html:submit property="submitAction"><bean:message key="button.addNewEvent" /></html:submit>
															</td>
														</tr>
													</jims2:isAllowed>
													</table>
													<!-- END BUTTON TABLE -->
												</logic:messagesNotPresent>
											</div>
											<!-- END DETAIL TABLE -->
										</td>
									</tr>
								</table><br>
							</td>
						</tr>
					</table><br>
					<!--casefile tabs end-->
				</td>
			</tr>
		</table>
		<!-- END  TABLE -->
	</div>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>