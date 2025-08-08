<!DOCTYPE HTML>

<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="java.util.Date" %>
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

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<title><bean:message key="title.heading" /> - calendarEventDetails.jsp</title>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayServiceEventDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|265"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" /> - <bean:message key="prompt.event" /> <bean:message key="title.details" /></td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <logic:equal name="calendarEventDetailsForm" property="windowType" value="popup">
          <li>Select <b>Close Window</b> button to close this window.</li>
        </logic:equal>

        <logic:notEqual name="calendarEventDetailsForm" property="windowType" value="popup">
          <li>Select <b>Cancel Event</b> button to cancel event.</li>
          <li>Select <b>Document Attendance</b> to view event attendee list.</li>
          <li>Select <b>Back</b> button to return to previous page.</li>
          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>  
		</logic:notEqual>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
  		<td class="detailHead">
			<table>
				<tr>
            		<td class="detailHead" width="160px"><bean:message key="prompt.event" /></td>
            		<td class="detailHead">Event ID: <bean:write name="calendarEventListForm" property="calendarEventId"/></td>
            	</tr>
          	</table>
    	</td>
    </tr>          		
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.program" /> <bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>
					<td class="formDe" colspan="3"><bean:write name="calendarEventDetailsForm" property="serviceProviderName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventDate" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="date.format.mmddyyyy"/></td>
					<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> <bean:message key="prompt.unit" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="serviceLocationName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.event" /> <bean:message key="prompt.time" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="time.format.hhmma"/></td>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.session" /> <bean:message key="prompt.length" /></td>
					<td class="formDe">
						<bean:define id="eventSessionLength" name="calendarEventDetailsForm" property="eventSessionLength" type="java.lang.String"/>
						<%=UIUtil.getTimeInHours(eventSessionLength)%>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventType" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventType"/></td>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventStatus" /></td>
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
					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td class="formDe" colspan='4'><bean:write name="calendarEventDetailsForm" property="eventComments"/></td>
				</tr>	
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class='spacer'></div>
<table width="100%">  
  <logic:equal name="calendarEventDetailsForm" property="windowType" value="popup">
	  <tr>
		  <td align='center'><input type='button' value='Close Window' onclick="javascript:window.close();" /></td>
		</tr>
  </logic:equal>
	
  <logic:notEqual name="calendarEventDetailsForm" property="windowType" value="popup">
    <tr>
      <td align="center">
        <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
  			
  			<logic:notEqual name="calendarEventDetailsForm" property="eventStatusCd" value="<%=PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED%>">
  				<bean:define id="evDate" name="calendarEventDetailsForm" property="eventDate"/>
  				<% Date date = (Date)evDate;
  					String cancelRights = "true";
  					if( date.compareTo(new Date()) < 0)
  					{
  						cancelRights = "false";
  					}
  					pageContext.setAttribute("cancelRights",cancelRights);
  				%>				
  				
  				<logic:equal name="cancelRights" scope="page" value="true">
  					<html:submit property="submitAction" onclick="return cancelConfirm('Are you sure you want to cancel this event?');">
  						<bean:message key="button.cancelEvent"></bean:message>
  					</html:submit>
  				</logic:equal>
  				<logic:notEqual name="calendarEventDetailsForm" property="eventStatusCd" value="<%=PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED%>">
  				    <html:submit property="submitAction"><bean:message key="button.documentAttendance"></bean:message></html:submit>
  				</logic:notEqual>    
  			</logic:notEqual>
  			<logic:equal name="calendarEventDetailsForm" property="eventStatusCd" value="<%=PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED%>">
  			    <html:submit property="submitAction"><bean:message key="button.viewDetails"></bean:message></html:submit>
  			</logic:equal>
            
  			<%-- <html:submit property="submitAction">
  				<bean:message key="button.returnToCalendar"></bean:message>    				
  			</html:submit> --%>
  			
  			<input id='returnToEventCalendar' type='button' value="Return To Calendar" onclick="history.go(-2)"/>

    		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_TEST_U%>'>
      		<logic:equal name="calendarEventDetailsForm" property="eventStatusCd" value="<%=PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED%>">
  					<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="MHE"> <%-- added for Defect 50801 --%>
        			<input type="submit" value="<bean:message key='button.addTestResults' />" onclick="return changeFormActionURL( this.form.name, '/JuvenileCasework/displayWorkshopAttendance.do?submitAction=Add Test Results', false )">
  					</logic:equal>
      		</logic:equal>
    		</jims2:isAllowed>
      </td>
    </tr>
  </logic:notEqual>
</table>
<%-- END BUTTON TABLE --%>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

