<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 06/07/2006		AWidjaja Create JSP--%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.Features" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;Workshop Calendar - interviewDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitJuvenileServiceEvent" target="content">


<logic:equal name="pageType" value="summary">
	<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|266">
	</logic:notEmpty>
	<logic:empty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|267">
	</logic:empty>
</logic:equal>
<logic:equal name="pageType" value="confirmation">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|268">
</logic:equal>             

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<logic:equal name="pageType" value="summary">
		<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
			<tr><td align="center" class="header">Juvenile Casework - Workshop Calendar - Details and Conflicts</td></tr>
		</logic:notEmpty>
		<logic:empty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
			<tr><td align="center" class="header">Juvenile Casework - Workshop Calendar - Details Summary</td></tr>
		</logic:empty>
	</logic:equal>
	<logic:equal name="pageType" value="confirmation">
		<tr><td align="center" class="header">Juvenile Casework - Workshop Calendar - Confirmation</td></tr>
		<tr><td align="center" class="confirm">Service Event(s) have been saved to the Workshop Calendar</td></tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>	
			<logic:equal name="pageType" value="summary">
				<ul>
					<li>Select hyperlinked Event ID to view conflicting event.</li>
					<li>Select <b>Add conflicting event</b> checkbox, then <b>Go Ahead &amp; Schedule</b> button to add event regardless of conflict.</li>
					<li>Select <b>Back</b> button to reschedule selected event.</li>
				</ul>
			</logic:equal>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
	<table width='98%' align="center">							
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>		
	</table>
<%-- END ERROR TABLE --%>

<%-- BEGIN summary CONFLICTING EVENTS TABLE --%>
	<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
		<logic:equal name="pageType" value="summary">
		<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
			<tr>
				<td class="detailHead">Existing Events</td>
			</tr>
			<tr>
				<td valign="top" align="center">
					<table width='100%' cellpadding="4" cellspacing="1">			
						<tr bgcolor='#cccccc'>
							<td class="subHead">Event ID</td>

							<td class="subHead">Event Date/Time</td>
							<td class="subHead">End Time</td>
							<td class="subHead">Location Unit</td>
							<td class="subHead">Instructor</td>
							<td class="subHead">Service Provider</td>
						</tr>

						<logic:iterate id="eventIter" indexId="iterIndex" name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
    					<tr class="<%out.print( (iterIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
								<td align="left">
									<bean:define id="currentServiceProviderId" name="scheduleNewEventForm" property="currentService.serviceProviderId" type="java.lang.String"/>
										<logic:equal name="eventIter" property="serviceProviderId" value="<%=currentServiceProviderId%>">
											<a href="javascript:openWindow('/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIter" property="eventId"/>&windowType=popup');">
											<bean:write name="eventIter" property="eventId"/></a>
										</logic:equal>
										<logic:notEqual name="eventIter" property="serviceProviderId" value="<%=currentServiceProviderId%>">
											<bean:write name="eventIter" property="eventId"/>
										</logic:notEqual>
								</td>
								<td align="left"><bean:write name="eventIter" property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<bean:write name="eventIter" property="eventDate" formatKey="time.format.hhmma" /></td>
								<td align="left"><bean:write name="eventIter" property="endDatetime" formatKey="time.format.hhmma" /></td>
								<td align="left"><bean:write name="eventIter" property="serviceLocationName"/></td>
								<td align="left"><bean:write name="eventIter" property="instructorName"/></td>
								<td align="left"><bean:write name="eventIter" property="serviceProviderName"/></td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</table>
		</logic:equal>
	</logic:notEmpty>	
</div>
<%-- END CONFLICTING EVENTS TABLE --%>	

<%-- BEGIN EVENT DETAIL TABLE --%>
<br>
<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
	<tr>
		<td class="detailHead">Proposed Event Details</td>
	</tr>
	<tr>
		<td valign="top" align="center">
			<table width='100%' cellpadding="2" cellspacing="1">
				
				<tr>
					<td colspan='4'>
						<table align="center" width="100%" cellpadding="3" cellspacing="1" class="borderTableGrey">

      			<tr>
      				<td class="formDeLabel" nowrap="nowrap">Service Provider</td>
      				<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.serviceProvider"/></td>
      				<td class="formDeLabel" nowrap="nowrap">Service Name</td>
      				<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.service"/></td>
      			</tr>
						
      			<tr>
      				<td class="formDeLabel" nowrap="nowrap">Event Type</td>
      				<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.serviceType"/></td>
      				<td class="formDeLabel" nowrap="nowrap">Location Unit</td>
      				<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.location"/></td>
      			</tr>

      			<tr>
      				<td class="formDeLabel" nowrap="nowrap">Session Length</td>
      				<td class="formDe">
    						<bean:define id="eventSessionLength" name="scheduleNewEventForm" property="currentService.currentEvent.sessionLength" type="java.lang.String"/>
    						<%=UIUtil.formatCodeTableSessionLength(eventSessionLength)%>
    					</td>
      				<td class="formDeLabel" nowrap="nowrap">Max Attendance</td>
      				<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.maxAttendance"/></td>
      			</tr>

      			<tr>
      				<td class="formDeLabel"nowrap="nowrap">Instructor</td>
      				<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.instructorName"/></td>
      				<td class="formDeLabel" nowrap="nowrap">Recurring Event</td>
      				<td class="formDe">No</td>
      			</tr>
            <tr>
    					<td colspan="4">
    						<table align="center" width="100%" cellpadding="0" cellspacing="0">
    							<tr>
    								<td class="formDeLabel">Comments</td>
    							</tr>
    							<tr>
    								<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.comments"/></td>
    							</tr>
    						</table>
    					</td>
            </tr>
				 </table>
				 
				 <div class='spacer'></div>
				<table align="center" width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
	    		<tr class="alternateRow">
  					<td class="formDeLabel" width="1%" nowrap="nowrap">
  						<logic:equal name="pageType" value="summary">
  							<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.conflictingEvents">
  								Add conflicting event
  							</logic:notEmpty>
  						</logic:equal>
  					</td>					
      			<td class="formDeLabel" nowrap="nowrap">Event Date</td>
      			<td class="formDeLabel">Event Time</td>
      			<td class="formDeLabel">End Time</td>
      		</tr>
				
  				<nested:iterate id="event" name="scheduleNewEventForm" property="allEvents">
  					<tr>
  						<logic:notEqual name="pageType" value="summary">
                 <td></td>
  						</logic:notEqual>
  						<logic:equal name="pageType" value="summary">
      					<nested:equal property="conflictingEvent" value="true">
                  <td align="center" bgcolor='#ff0000'><nested:checkbox property="addConflictingEvent" value="true"/></td>
   							</nested:equal>			
   							<nested:equal property="conflictingEvent" value="false">
                  <td align="center"></td>
   							</nested:equal>			
 							</logic:equal>

  						<td align="left"
							  <logic:equal name="pageType" value="summary">
      					  <nested:equal property="conflictingEvent" value="true">class='errorAlert'</nested:equal>
      					</logic:equal>
    					>							
								<nested:write property="startDatetime" formatKey="date.format.mmddyyyy"/>	
						  </td>
	      			<td align="left"><nested:write property="startDatetime" formatKey="time.format.hhmma" /></td>					
	      			<td align="left"><nested:write property="endDatetime" formatKey="time.format.hhmma" /></td>					
  					</tr>		
				  </nested:iterate>
				</logic:equal>
			</table>
		</td>
	</tr>				
</table>
		</td>
	</tr>				
</table>

      <%-- BEGIN BUTTON TABLE --%>
      <div class='spacer'></div>
      <table width="100%">
        <tr>
          <td align="center">
      		<logic:equal name="pageType" value="summary">
      			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      			<html:submit property="submitAction"><bean:message key="button.goAhead&Schedule"/></html:submit> 
      			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      		</logic:equal>
      		<logic:equal name="pageType" value="confirmation">
      			<html:submit property="submitAction"><bean:message key="button.returnToWorkshopCalendar"/></html:submit>
      			 <jims2:isAllowed requiredFeatures='<%=Features.JCW_WC_JUV_ADD%>'>
      				<html:submit property="submitAction"><bean:message key="button.addYouthToSession"/></html:submit> <!-- add feature -->
      			</jims2:isAllowed>    			
      		</logic:equal>
          </td>
        </tr>
      </table>
      <%-- END BUTTON TABLE --%>

		</td>
	</tr>				
</table>
<%-- END EVENT DETAIL TABLE --%>


</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>