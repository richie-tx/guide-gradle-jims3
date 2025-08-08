<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- 5/11/2012	RYoung		ER #73310 Display casefile Info used to create event --%>
<%-- 6/28/2012	C SHIMEK	ER #73798 Added in-house guardian check to ID Info button --%>
<%-- 07/11/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to hide Document Attendance button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.Features" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.UIConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarEventDetails.jsp</title>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleCalendarEventDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|137"> 

<input type="hidden" name="guardianInHouseFld" value="<bean:write name="calendarEventListForm" property="guardianInHouse"/>" Id="gihId" />
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;- <bean:message key="title.calendarEvent" />&nbsp;<bean:message key="prompt.details" /></td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<logic:notEqual name ="calendarEventListForm" property="calendarType" value="PRE">
					<li>Select <b>Back</b> button to return to previous page.</li>
					<li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
				</logic:notEqual>
					
				<logic:equal name ="calendarEventListForm" property="calendarType" value="PRE">
					<li>Select <b>Close Window</b> button to to close this window.</li>
					<li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
				</logic:equal>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:notEqual name ="calendarEventListForm" property="calendarType" value="PRE">
<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
</logic:notEqual>
<%-- END CASEFILE HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>

    		<logic:notEqual name ="calendarEventListForm" property="calendarType" value="PRE">
        <%--tabs start--%> 
        		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
          			<tiles:put name="tabid" value="calendartab" />
          			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
        		</tiles:insert> 
        <%--tabs end--%>
    	 	</logic:notEqual> 

			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
					<%-- BEGIN DETAIL TABLE --%>
						<div class='spacer'></div>
						<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.event" />&nbsp;<bean:message key="prompt.details" />&nbsp;(<bean:write name="calendarEventListForm" property='currentEvent.eventId'/>)</td>
								<td class="detailHead"style="text-align:right">Supervision #: <bean:write name="calendarEventListForm" property="currentEvent.casefileId"/></td>
							</tr>
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">	
								<tr>
									<td colspan="2">
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.serviceProvider" /></td>
												<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceProviderName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.eventDate" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel"><bean:message key="prompt.eventTime" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength" /></td>
												<td class="formDe">        					
													<bean:define id="eventSessionLength" name="calendarEventListForm" property="currentEvent.eventSessionLength" type="java.lang.String"/>
													<%=UIUtil.getTimeInHours(eventSessionLength)%>
												</td>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.minAttendance" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.minAttendance"/></td>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.maxAttendance" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.maxAttendance"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.totalScheduled" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.currentEnrollment"/></td>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.instructorName" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.instructorName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.attendance" /> <bean:message key="prompt.status" /></td>
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="attendanceStatus"/></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>                  	
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="addlAttendees" /></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendeeNames" /></td>
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="selectedAttendeeNamesAsString" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'>
													<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
														<bean:write name="calendarEventListForm" property="progressNotes"/>
													</logic:notEmpty>
													<logic:empty name="calendarEventListForm" property="progressNotes"> 
	            										No Progress Notes.
            										</logic:empty>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:equal>
								
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">	
								<tr>
									<td colspan="2">
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
												<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
												<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personsInterviewed" /></td>
												<td class='formDe' colspan='3'>
													<logic:notEmpty name="calendarEventListForm" property="currentEvent.interviewPersons">
														<logic:iterate id="personsIter" name="calendarEventListForm" property="currentEvent.interviewPersons">
															<bean:write name="personsIter" property="formattedName"/><br>
														</logic:iterate>
													</logic:notEmpty>												
												</td> 
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.interviewType" /></td>
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.interviewType"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>
												<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.attendance" /> <bean:message key="prompt.status" /></td>
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="attendanceStatus"/></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>                  	
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="addlAttendees" /></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendeeNames" /></td>
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="selectedAttendeeNamesAsString" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.summary" /> <bean:message key="prompt.notes" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="summaryNotes"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'>
													<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
														<bean:write name="calendarEventListForm" property="progressNotes"/>
													</logic:notEmpty>
													<logic:empty name="calendarEventListForm" property="progressNotes"> 
														No Progress Notes.
													</logic:empty>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:equal>

							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>">	
								<tr>
									<td colspan="2">
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
												<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
											</tr>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.dateOfContact" /></td>
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
												</tr>
											</logic:equal>
											<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
												</tr>
											</logic:notEqual>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'>
														<bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/>
														<logic:notEmpty name="calendarEventListForm" property="currentEvent.familyLocationValidation">
															<logic:equal name="calendarEventListForm" property="currentEvent.familyLocationValidation" value="Y">
																<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
															</logic:equal>
															<logic:equal name="calendarEventListForm" property="currentEvent.familyLocationValidation" value="N">
																<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
															</logic:equal>
														</logic:notEmpty>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.location"/></td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'>
														<bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/>
														<logic:notEmpty name="calendarEventListForm" property="currentEvent.familyLocationValidation">
															<logic:equal name="calendarEventListForm" property="currentEvent.familyLocationValidation" value="Y">
																<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
															</logic:equal>
															<logic:equal name="calendarEventListForm" property="currentEvent.familyLocationValidation" value="N">
																<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
															</logic:equal>
														</logic:notEmpty>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'>
														<bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'>
														<bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
													<td class="formDe" colspan='3'>
														<bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolDistrictName"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.school" /></td>
													<logic:empty name="calendarEventListForm" property="currentEvent.instructionType">
														<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/></td>
													</logic:empty>
													<logic:notEmpty name="calendarEventListForm" property="currentEvent.instructionType">
														<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/>:&nbsp;&nbsp;<bean:write name="calendarEventListForm" property="currentEvent.instructionType"/></td>
													</logic:notEmpty>
												</tr>
											</logic:equal>
														
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolDistrictName"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.school" /></td>
													<logic:empty name="calendarEventListForm" property="currentEvent.instructionType">
														<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/></td>
													</logic:empty>
													<logic:notEmpty name="calendarEventListForm" property="currentEvent.instructionType">
														<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/>:&nbsp;&nbsp;<bean:write name="calendarEventListForm" property="currentEvent.instructionType"/></td>
													</logic:notEmpty>
												</tr>
											</logic:equal>
	
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>		
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
												</tr>
											</logic:equal>
											
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>		
													<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
												</tr>
											</logic:equal>
														
											<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">	
											<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">			
												<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
													<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
														<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
															<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">	
																<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">	
																	<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">						
																		<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
																			<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>		
																					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>			
																				</tr>
																			</logic:notEqual>
																		</logic:notEqual>
																	</logic:notEqual>
																</logic:notEqual>
															</logic:notEqual>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
											</logic:notEqual>
					
											<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">		
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
													<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
												</tr>
											</logic:notEqual>
											<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.contactTime" /></td>
													<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personContacted" /></td>
													<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.contactFirstName"/> <bean:write name="calendarEventListForm" property="currentEvent.contactLastName"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sexOffender"/> <bean:message key="prompt.registrant"/></td>
													<td class="formDe" colspan="3">
														<logic:equal name="calendarEventListForm" property="currentEvent.sexOffenderRegistrantStr" value="Y">
															<bean:message key="prompt.yes"/>
														</logic:equal>
														<logic:notEqual name="calendarEventListForm" property="currentEvent.sexOffenderRegistrantStr" value="Y">
															<bean:message key="prompt.no"/>
														</logic:notEqual>
													</td>
												</tr>
												<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.restrictionsOther"/></td>		
	    											<td class="formDe" colspan="3" nowrap='nowrap'><bean:write name="calendarEventListForm" property="currentEvent.restrictionsOther"/></td>														
	    										</tr>
	    										<tr>
	    											<td class="formDeLabel" valign="top"><bean:message key="prompt.offenses" /></td>
	    											<td class="formDe" colspan="3">
														<table width="100%" cellspacing="0" cellpadding="2">
															<tr class="formDeLabel">
																<td>(Type) <bean:message key="prompt.description"/></td>
																<td><bean:message key="prompt.weaponInvolved"/></td>
																<td><bean:message key="prompt.typeOfWeapon"/></td>
															</tr>
				    										<logic:iterate id="offs" name="calendarEventListForm" property="currentEvent.offenseInvolvedWeaponList" indexId="index">
		    													<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
		    														<td>
		    															(<bean:write name="offs" property="referralNumber"/> - <bean:write name="offs" property="offenseCategoryDescription"/>) <bean:write name="offs" property="offenseDescription" />
		    														</td>
					    											<td>
		    															<logic:equal name="offs" property="weaponInvolvedStr" value="Y">
		    																<bean:message key="prompt.yes"/>
		    															</logic:equal>
		    															<logic:equal name="offs" property="weaponInvolvedStr" value="N">
		    																<bean:message key="prompt.no"/>
		    															</logic:equal>
		    														</td>														
		    														<td nowrap='nowrap'><bean:write name="offs" property="typeOfWeaponDescription"/></td>														
		    													</tr>
															</logic:iterate>	
	    												</table>
	    											</td>	
	    										</tr>
											</logic:equal>
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.attendance" /> <bean:message key="prompt.status" /></td>
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="attendanceStatus"/></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>                  	
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="addlAttendees" /></td>
											</tr>
											<tr>
												<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendeeNames" /></td>
												<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="selectedAttendeeNamesAsString" /></td>
											</tr>	  				
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan='4'>
													<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
														<bean:write name="calendarEventListForm" property="progressNotes"/>
													</logic:notEmpty>
													<logic:empty name="calendarEventListForm" property="progressNotes"> 
														No Progress Notes.
													</logic:empty>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual name ="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%>">
								<bean:define id="currentJuv" name ="calendarEventListForm" property="currentJuvenileId"/>
								<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">	
									<table width="100%">  
										<logic:equal name="calendarEventListForm" property="juvenileNum" value="<%=(String)currentJuv%>">	
										  <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALENDAR_BTNS%>'>
											<tr>
												<td align="center">
													<input type="submit" name="submitAction" value="<bean:message key='button.requestAttorneyAppt'/>" id="cedIO1" data-href="/<msp:webapp/>handleAttorneyOptions.do">
													<html:submit property="submitAction" styleId="evDetGuardianCheck"><bean:message key="button.idInfo"/></html:submit>
													<html:submit property="submitAction"><bean:message key="button.documentAttendance"/></html:submit>
													<html:submit property="submitAction"><bean:message key="button.writingSample"/></html:submit>											  
													<html:submit property="submitAction"><bean:message key="button.appointmentLetter"/></html:submit>
													<input type="submit" name="submitAction" value="<bean:message key='button.viewSocialHistoryData'/>" id="cedIO2" data-href="/<msp:webapp/>handleSocialHistoryData.do">			
												</td>
											</tr>
										   </jims2:isAllowed>
										</logic:equal>
										<tr>
											<td align="center">
											    <logic:notEmpty name="calendarEventListForm" property="programReferralList">
							                      <bean:size id="juvList" name="calendarEventListForm" property="programReferralList"/>
							                      <logic:greaterThan name="juvList" value="0">
							                        <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALATTENDPRT%>'>
							                        <html:submit property="submitAction"><bean:message key="button.printAttendanceList"/></html:submit> 
							                        </jims2:isAllowed> 
							                      </logic:greaterThan>
                                                </logic:notEmpty>
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</table>	
								</logic:equal>

								<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">	
									<table width="100%">  
										<tr>
											<td align="center">
											    <logic:notEmpty name="calendarEventListForm" property="programReferralList">
							                      <bean:size id="juvList" name="calendarEventListForm" property="programReferralList"/>
							                      <logic:greaterThan name="juvList" value="0">
							                        <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALATTENDPRT%>'>
							                         <html:submit property="submitAction"><bean:message key="button.printAttendanceList"/></html:submit>							                         
							                        </jims2:isAllowed> 
							                      </logic:greaterThan>
                                                </logic:notEmpty>
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
												<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
													<logic:equal name="calendarEventListForm" property="juvenileNum" value="<%=(String)currentJuv%>">
														<logic:equal name="calendarEventListForm" property="isAttendedExcusedAbsent" value="false">
															<html:submit property="submitAction"><bean:message key="button.documentAttendance"/></html:submit>	
														</logic:equal>	
													</logic:equal>
												</logic:equal>	
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</table><%-- END BUTTON TABLE --%>
								</logic:notEqual>
							</logic:notEqual>
							<logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%>">
							    <logic:notEmpty name="calendarEventListForm" property="programReferralList">
							      <bean:size id="juvList" name="calendarEventListForm" property="programReferralList"/>
							      <logic:greaterThan name="juvList" value="0">
							       <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALATTENDPRT%>'>
							        <table width="100%">  
										<tr>
											<td align="center">
											  <html:submit property="submitAction"><bean:message key="button.printAttendanceList"/></html:submit>
											</td>
										</tr>
									</table>
								   </jims2:isAllowed> 
							      </logic:greaterThan>
                                </logic:notEmpty>
							</logic:equal>
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>

<%-- Defect 49803 - wrap button with logic tags so it only displays  when in "window pop-up"mode
	"Defect 4692: Calendar Event Details pop-up -- add Close Window button"
		 added the button without enclosing it in "logic" tags
--%>
	<logic:equal name ="calendarEventListForm" property="calendarType" value="PRE">
		<tr><td></td></tr>
		<tr>
			<td colspan='2' align='center'>
				<input type='button' value='Close Window'  onclick="javascript:window.close();">
			</td>
		</tr> 
	</logic:equal>
</table>
</html:form>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>