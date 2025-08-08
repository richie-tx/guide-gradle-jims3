<!DOCTYPE HTML>
<!------------------MODIFICATIONS ---------------------------->
<!-- March 2007 - daw - create jsp -->
<!-- jul 20 2009 - cws  #61004 added timeout.js  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCalendarConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarEvents.jsp</title>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/handleViewCalendar" target="content">


<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Calendar Events</td>
  </tr>
</table>
<!-- END HEADING TABLE -->


<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0" align="center">
  <tr>
    <td>
      <ul>
        <li>Select hyperlinked <b>Events</b> for a day to view the list of events.</li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign="top" align="center">

      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td align="center">   

				<bean:define id="eventLinkURL">/<msp:webapp/>handleViewCalendar.do?submitAction=Display View Calendar Event List</bean:define>
				<logic:equal name="scheduleNewEventForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
					<bean:define id="calendarTitle">Service Provider Calendar - <bean:write name="scheduleNewEventForm" property="providerName"/></bean:define>					
					<bean:define id="serviceProId" name="scheduleNewEventForm" property="serviceProviderId" type="String"/>
					<jims2:appcalendar  
						calendarStyleSheet="blueCalendarSkin.css"
						serviceEvent="messaging.calendar.GetCalendarServiceEventsEvent"
						responseType="short"
						eventTimeFormat="h:mma"
						weekDayViewType="FULLTEXT"
						sessionAttributeName="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>"
						eventLink="<%=eventLinkURL%>"
						dayDisplayClass="ui.taglib.calendar.ConsolidatedEventDayPresentation"
						title="<%=calendarTitle%>"
						serviceProviderId="<%=serviceProId%>"
						needsRefresh="true">
					</jims2:appcalendar>
				</logic:equal>
				
				<logic:equal name="scheduleNewEventForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_JPO%>">
					<bean:define id="calendarTitle">JPO Calendar -  <bean:write name="scheduleNewEventForm" property="officerName"/></bean:define>
					<bean:define id="offId" name="scheduleNewEventForm" property="officerId" type="String"/>
					<jims2:appcalendar  
						calendarStyleSheet="blueCalendarSkin.css"
						serviceEvent="messaging.calendar.GetCalendarServiceEventsEvent"
						responseType="short"
						eventTimeFormat="h:mma"
						weekDayViewType="FULLTEXT"
						sessionAttributeName="<%=PDCalendarConstants.CALENDAR_TYPE_JPO%>"
						eventLink="<%=eventLinkURL%>"
						dayDisplayClass="ui.taglib.calendar.ConsolidatedEventDayPresentation"
						title="<%=calendarTitle%>"
						officerId="<%=offId%>"
						needsRefresh="true">
					</jims2:appcalendar>
				</logic:equal>
				
				<logic:equal name="scheduleNewEventForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_CLM%>">
					<bean:define id="calendarTitle">Caseload Manager Calendar -  <bean:write name="scheduleNewEventForm" property="officerName"/></bean:define>
					<bean:define id="offId" name="scheduleNewEventForm" property="officerId" type="String"/>
					<jims2:appcalendar  
						calendarStyleSheet="blueCalendarSkin.css"
						serviceEvent="messaging.calendar.GetCalendarServiceEventsEvent"
						responseType="short"
						eventTimeFormat="h:mma"
						weekDayViewType="FULLTEXT"
						sessionAttributeName="<%=PDCalendarConstants.CALENDAR_TYPE_CLM%>"
						eventLink="<%=eventLinkURL%>"
						dayDisplayClass="ui.taglib.calendar.ConsolidatedEventDayPresentation"
						title="<%=calendarTitle%>"
						officerId="<%=offId%>"
						needsRefresh="true">
					</jims2:appcalendar>
				</logic:equal>
				
			
          </td>
        </tr>
      </table>

    </td>
  </tr>
</table>


<!-- BEGIN BUTTON TABLE -->
<br>
<table border="0" width="100%" align="center">
  <tr>
	<td align="center">
		<html:submit property="submitAction"><bean:message key="button.searchCalendarOptions"/></html:submit>     
	</td>
  </tr>
</table>
<!-- END BUTTON TABLE -->


</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>