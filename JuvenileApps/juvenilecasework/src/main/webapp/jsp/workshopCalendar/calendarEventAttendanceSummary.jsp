 <!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson		Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 03/18/2011 C Shimek    #69407 added View href un Additional Attendees to match prototype  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>




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
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - calendarEventAttendanceSummary.jsp</title>

<script type='text/javascript'>
function closeProgressNotesTable()
{ 
	showHide( "progressTable", 0 ) ;
}

function closeMonthlySummaryTable()
{ 
	showHide( "monthlySummaryTable", 0 ) ;
}

function closeAttendeesViewTable()
{ 
	showHide( "attendeesViewTable", 0 ) ;
}

function showTestResults(juvenileId)
{
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" 
				+ "Link" + "&juvenileId=" + juvenileId;					
	document.forms[0].submit();
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/submitWorkshopAttendance" target="content">
<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceSummary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|282">
</logic:equal>
<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">
	<logic:notEqual name="calendarEventDetailsForm" property="secondaryAction" value="addTestResults" >
	   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|283">
	</logic:notEqual>
</logic:equal>	        

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" /></td>
	</tr>
	<tr>  
		<td align="center" class="header">
			<bean:message key="title.calendarEventAttendeeList" />  
			<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceSummary">	
				<bean:message key="title.summary" /> 
			</logic:equal> 
			<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">
				<logic:notEqual name="calendarEventDetailsForm" property="secondaryAction" value="addTestResults" >
					<bean:message key="title.confirmation" /> 
				</logic:notEqual>
			</logic:equal>	
		</td>		
	</tr>
  	<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">
  		<logic:notEqual name="calendarEventDetailsForm" property="secondaryAction" value="addTestResults" >
	  		<tr>
	    		<td align='center' class='confirm'>Attendance documented, Non-Attendance Notifications sent.</td>
	  		</tr> 
	 	</logic:notEqual>
  	</logic:equal>	
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
				<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceSummary">	
					<li>Select <b>Finish</b> to save attendance.</li>
					<li>Select <b>Back</b> button to return to previous page.</li>
					<li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
				</logic:equal> 
				
				<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">
					<li>Select the <b>Return to Calendar</b> button to return to the calendar.</li>
					<li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
				</logic:equal>	
			</ul>	  
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.event" /> <bean:message key="prompt.details" /></td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.program" /> <bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>
					<td class="formDe" colspan="3"><bean:write name="calendarEventDetailsForm" property="serviceProviderName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="date.format.mmddyyyy"/></td>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="serviceLocationName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.event" /> <bean:message key="prompt.time" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="time.format.hhmma"/></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.session" /> <bean:message key="prompt.length" /></td>
					<td class="formDe">
						<bean:define id="eventSessionLength" name="calendarEventDetailsForm" property="eventSessionLength" type="java.lang.String"/>
						<%=UIUtil.getTimeInHours(eventSessionLength)%>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventType"/></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventStatus" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventStatus"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.minimumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMinimum"/></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.maximumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMaximum"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.totalScheduled" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="totalScheduled"/></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.instructorName" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="instructorName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.eventComments" /></td>
				</tr>
				<tr>
					<td class="formDe" colspan='4'><bean:write name="calendarEventDetailsForm" property="eventComments"/></td>
				</tr>	
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
<%-- BEGIN ATTENDANCE TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue"> 
	<tr>
		<td class="detailHead"><bean:message key="title.juvenileAttendanceList" /></td>   
	</tr>
	<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="false" >	
		<tr>
			<td ><bean:message key="title.noJuvenileForEvent"/></td>   
		</tr>  
	</logic:equal>

	<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="true" >
		<tr>
			<td>
				<table width="'100%" cellpadding="2" cellspacing="1">
					<tr class='subHead'>
						<td class="formDeLabel"><bean:message key="prompt.attended"/></td>
						<td class="formDeLabel"><bean:message key="prompt.absent"/></td>
						<td class="formDeLabel"><bean:message key="prompt.excused"/></td>
						<td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>
						<td class="formDeLabel"><bean:message key="prompt.juvenile#"/></td>	
						<td class="formDeLabel" width="1%"><bean:message key="prompt.additionalAttendees"/></td>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_TEST_U%>'>					
								<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">							
									<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.MENTAL_HEALTH_EVALUATION%>">
										<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.test"/> <bean:message key="prompt.results"/></td>
									</logic:equal>							
								</logic:equal>
							</jims2:isAllowed>
						<td class="formDeLabel"><bean:message key="prompt.progressNotes"/></td>
						<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
							<td class="formDeLabel"><bean:message key="prompt.monthlySummary"/></td>
						</logic:equal>
						<td class="formDeLabel" width="1%">Added <bean:message key="prompt.attendee"/>s</td>
					</tr>

					<logic:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="existingAttendanceEvents">
						<tr class=<% out.print( (recordCounter.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>>
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>						
							</td>	
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
							</td>		
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
							</td>	
							<td nowrap='nowrap'><bean:write name="personsIter" property="juvenileName"/></td>	
							<td ><bean:write name="personsIter" property="juvenileId"/></td>
							<td ><bean:write name="personsIter" property="addlAttendees"/></td>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_TEST_U%>'>
								<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">								
									<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.MENTAL_HEALTH_EVALUATION%>">
										<td width="1%" nowrap='nowrap'>
											<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">			
												<a href="/<msp:webapp/>displayWorkshopAttendance.do?submitAction=Link&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">										
													<bean:message key="prompt.add"/> <bean:message key="prompt.test"/> <bean:message key="prompt.results"/></a>
											</logic:equal>
									</td>	
									</logic:equal>
								</logic:equal>						
							</jims2:isAllowed>
							<td>	
								<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewProgressNotes'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">
									<bean:message key="prompt.view"/></a>
							</td>
							<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
								<td>	
									<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewMonthlySummary'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">
										<bean:message key="prompt.view"/></a>
								</td>
							</logic:equal>
							<td>
								<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewAttendees'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>&listInd=Existing">
									<bean:message key="prompt.view"/></a>
							</td>
						</tr>	
					</logic:iterate>	
	
					<nested:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="newAttendanceEvents">
						<tr class="<%out.print( (recordCounter.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>						
							</td>	
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
							</td>		
							<td align="center">			
								<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
							</td>	
							<td nowrap='nowrap'><bean:write name="personsIter" property="juvenileName"/></td>	
							<td ><bean:write name="personsIter" property="juvenileId"/></td>
							<td >
								<bean:write name="personsIter" property="addlAttendees"/>
							</td>	

							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_TEST_U%>'>						
								<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">								
									<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.MENTAL_HEALTH_EVALUATION%>">										
										<td width="1%" nowrap='nowrap'>
												<logic:equal name="personsIter" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">				
													<a href="/<msp:webapp/>displayWorkshopAttendance.do?submitAction=Link&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">											
													<bean:message key="prompt.add"/> <bean:message key="prompt.test"/> <bean:message key="prompt.results"/></a>
												</logic:equal>
										</td>																				
									</logic:equal>								
								</logic:equal>	
							</jims2:isAllowed>
							<td>	
								<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewProgressNotes'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">
									<bean:message key="prompt.view"/></a>
							</td>
							<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
								<td>	
									<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewMonthlySummary'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>">
										<bean:message key="prompt.view"/></a>
								</td>
							</logic:equal>
							<td>
								<a href="/<msp:webapp/>submitWorkshopAttendance.do?submitAction=<bean:message key='button.viewAttendees'/>&juvenileId=<bean:write name='personsIter' property='juvenileId'/>&listInd=New">
									<bean:message key="prompt.view"/></a>
							</td>
						</tr>	
					</nested:iterate>	
				</table>
			</td>
		</tr>
  	</logic:equal>
</table>
<%-- END ATTENDANCE TABLE --%>
<div class='spacer4px'></div>
<%-- begin progress notes table --%>
<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewProgressNotes" >
	<div class='visible' id='progressTable'>
		<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
			<tr>
				<td class='detailHead'><bean:message key="title.juvenileAttendanceList"/>-<bean:message key="prompt.progressNotes"/></td>
			</tr>
			<tr>
				<td valign='top' align='center'>
					<nested:nest property="currentAttendanceEvent">
						<table width='100%' cellpadding="2" cellspacing="1">		
							<tr>
								<td class='formDeLabel'><bean:message key="prompt.juvenile#"/></td>
								<td class='formDe'><nested:write property="juvenileId"/></td>
								<td class='formDeLabel'><bean:message key="prompt.juvenileName"/></td>
								<td class='formDe'><nested:write property="juvenileName"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.progressNotes"/></td>
							</tr>
							<tr>
								<td class='formDe' colspan='4'><nested:write property="existingProgressNotes"/></td>
							</tr>
							<tr>
								<td colspan='4' align='center'><br><input type='button' value='Close Progress Notes' onClick="javascript:closeProgressNotesTable();"></td>
							</tr>
						</table>
					</nested:nest>
				</td>
			</tr>
		</table>
	</div>
</logic:equal>
<%-- end progress notes table --%>

<%-- begin monthly summary table --%>
<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewMonthlySummary" >
	<div class='visible' id='monthlySummaryTable'>
		<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
			<tr>
		  		<td class='detailHead'><bean:message key="title.juvenileAttendanceList"/>-<bean:message key="prompt.monthlySummary"/></td>
		  	</tr>
		  	<tr>
		  		<td valign='top' align='center'>
					<nested:nest property="currentAttendanceEvent">
			  			<table width='100%' cellpadding="2" cellspacing="1">		
			  				<tr>
			  					<td class='formDeLabel'><bean:message key="prompt.juvenile#"/></td>
			  					<td class='formDe'><nested:write property="juvenileId"/></td>
			  					<td class='formDeLabel'><bean:message key="prompt.juvenileName"/></td>
			  					<td class='formDe'><nested:write property="juvenileName"/></td>
			  				</tr>
			  				<tr>
			  					<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.monthlySummary"/></td>
			  				</tr>
							
							<tr>
								<td class='formDe' colspan='4'><nested:write property="monthlySummary"/></td>
							</tr>
							<tr>
								<td colspan='4' align='center'><br><input type='button' value='Close Monthly Summary' onClick="javascript:closeMonthlySummaryTable();"></td>
							</tr>
			  			</table>
					</nested:nest>
		  		</td>
		  	</tr>
		</table>
	</div>
</logic:equal>
<%-- end monthly summary table --%>
<%-- BEGIN VIEW/ADD ATTENDEES --%>	
<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewAttendees" >
	<div class='visible' id='attendeesViewTable'>
	   	<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
          	<tr> 
          		<td class='detailHead'><bean:message key="title.juvenileAttendanceList" /> - <bean:message key="prompt.attendee" />&nbsp;<bean:message key="prompt.info" /></td>
          	</tr>
          	<tr>
          		<td valign='top' align='center'>
          			<table width='100%' cellpadding="2" cellspacing="1">
						<tr> 
							<td class='formDeLabel' valign='top' width='1%' nowrap='nowrap'>											
								<bean:message key="prompt.additionalAttendeeNames"/>
							</td> 
							<td class='formDe'>									
								<logic:equal name="calendarEventDetailsForm" property="attendeeNamesStr" value="">
									Name list is empty.
								</logic:equal>
								<logic:notEqual name="calendarEventDetailsForm" property="attendeeNamesStr" value="">
									<bean:write name="calendarEventDetailsForm" property="attendeeNamesStr" />
								</logic:notEqual>
							</td>	
						</tr> 
					</table>
	            </td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" value="Close Attendee List" onClick="javascript:closeAttendeesViewTable();">
				</td>
			</tr>
		</table>
	</div>
</logic:equal>   
<%-- END ADD ATTENDEES --%>	
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table width="100%">  
	<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceConfirm">
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.returnToCalendar"/></html:submit>	
				<input type="button" value="Return to Query" onclick="window.location.href='/JuvenileCasework/displayServiceEventList.do?submitAction=Display Service Event List&amp;locationId=<bean:write name="calendarEventDetailsForm" property="currentEvent.locationUnitId"/>&amp;sessionevents=PROVIDERCALENDAR'"/>
		</td>
		</tr>
	</logic:equal>                  
			
	<logic:equal name ="calendarEventDetailsForm" property="action" value="attendanceSummary">
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
				<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			 </td>
		</tr>
	</logic:equal>                  
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>