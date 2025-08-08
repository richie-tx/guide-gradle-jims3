<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson		Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - calendarEventDetails.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitWorkshopCancellation" target="content">

<logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationSummary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|284">
</logic:equal>
<logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationConfirm">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|285">
</logic:equal>     

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">
		<bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" /> 
		<bean:message key="title.eventCancellation" />&nbsp;  
		<logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationSummary">	
			<bean:message key="title.summary" /> 
		</logic:equal> 
		<logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationConfirm">
			<bean:message key="title.confirmation" /> 
		</logic:equal>	
	 </td>	
  </tr>
   <logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationConfirm">
	  <tr>
	    <td align="center" class="confirm">The event has been cancelled and notifications have been sent.</td>
	  </tr> 
  </logic:equal>
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
		 <logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationSummary">			 
          <li>Select the <b>Finish</b> button to confirm the cancellation and generate the Cancellation List. </li>
          <li>Select <b>Back</b> button to return to previous page.</li>
          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
		 </logic:equal> 
		 <logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationConfirm">
			<li>Select the <b>Return to Calendar</b> button to return to the calendar.</li>
			<li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
		 </logic:equal>	
      </ul>	  
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.event" /> <bean:message key="prompt.details" /></td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.program" /> <bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>
					<td class="formDe" colspan="3"><bean:write name="calendarEventDetailsForm" property="serviceProviderName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventDate" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="date.format.mmddyyyy"/></td>
					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="serviceLocationName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.event" /> <bean:message key="prompt.time" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="time.format.hmma"/></td>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.session" /> <bean:message key="prompt.length" /></td>
					<td class="formDe">
						<bean:define id="eventSessionLength" name="calendarEventDetailsForm" property="eventSessionLength" type="java.lang.String"/>
						<%=UIUtil.getTimeInHours(eventSessionLength)%>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventType" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventType"/></td>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventStatus" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventStatus"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.minimumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMinimum"/></td>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.maximumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMaximum"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.totalScheduled" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="totalScheduled"/></td>
					<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.instructorName" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="instructorName"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap="nowrap" colspan="4"><bean:message key="prompt.eventComments" /></td>
				</tr>
				<tr>
					<td class="formDe" colspan="4"><bean:write name="calendarEventDetailsForm" property="eventComments"/></td>
				</tr>	
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN ATTENDANCE TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue"> 
  <tr>
    <td class="detailHead" colspan="6"><bean:message key="title.cancellationCallList"/></td>   
  </tr>
  
  <logic:empty name="calendarEventDetailsForm" property="cancellationList">	
		<tr>
			<td colspan="6"><bean:message key="title.noJuvenileForEvent"/></td>   
		</tr>  
  </logic:empty>

  <logic:notEmpty name="calendarEventDetailsForm" property="cancellationList">
		<tr>
		  <td>
		    <table width='100%' cellpadding="2" cellspacing="1">
					<tr class="subHead">
						<td class="formDeLabel" width="1%"><bean:message key="prompt.juvenile#"/></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.juvenileName"/></td>					
					</tr>
					<logic:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="cancellationList">
						<tr class="<%out.print( (recordCounter.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
							<td width="1%">	
								<a href="javascript:openWindow('/<msp:webapp/>submitWorkshopCancellation.do?submitAction=<bean:message key="button.viewGuardianInfo"/>&juvenileId=<bean:write name="personsIter" property="juvenileId"/>')">				
								<bean:write name="personsIter" property="juvenileId"/></a>	
							</td>		
							<td width="1%" nowrap="nowrap"><bean:write name="personsIter" property="juvenileName"/></td>	
						</tr>	
					</logic:iterate>	
				</table>
			</td>
		</tr>
  </logic:notEmpty>
</table>
<%-- END ATTENDANCE TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class='spacer'></div>
<table width="100%">                  
  <logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationConfirm"> <%-- confirmation buttons --%>
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.returnToEventList"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.generateCancellationList"/></html:submit>
			</td>
		</tr>
  </logic:equal>                  
	  
  <logic:equal name ="calendarEventDetailsForm" property="action" value="cancellationSummary"> <%-- edit buttons --%>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
