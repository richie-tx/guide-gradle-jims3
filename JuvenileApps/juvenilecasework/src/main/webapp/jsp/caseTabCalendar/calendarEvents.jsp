<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 06/23/2011 C Shimek    #70628 added Schedule Service Provider Events button  --%>
<%-- 08/29/2011 C Shimek    #67476 (not part of original ER) Revised href for Past Pre-scheduled to button and revised schedule button names  --%>
<%-- 04/20/2012 C Shimek    #73232 added allowUpdate edit to Schedule Juvenile Events button for closed casefile status  --%>
<%-- 05/08/2012 C Shimek    #73232 added allowPrePetitionUpdates edit to SP Event Types and Events by SP buttons for closed casefile status with pre-petition supervision type --%>
<%-- 07/10/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to hide schedule buttons --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="ui.common.UIUtil" %>
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
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarEvents.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>


</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayCalendar" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|135"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.calendarEvent" />s</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
	
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select a hyperlinked <b>Event</b> for a day to view the list of events.</li>
				<li>Select <b>Schedule New Event</b> button to schedule a new event.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END CASEFILE HEADER TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>
			<%--tabs start--%> 
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="calendartab" />
				<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
			</tiles:insert> 
			<%--tabs end--%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
						<div class='spacer'></div>
<%-- BEGIN MONTH TABLE --%>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign='top' align='center'>
									<bean:define id="eventJuvLinkURL">/<msp:webapp/>displayCalendarEventList.do?submitAction=Display Calendar Event List&juvenileNum=<bean:write name="scheduleNewEventForm" property="juvenileNum"/></bean:define>                  	
									<bean:define id="eventJpoLinkURL">/<msp:webapp/>displayCalendarEventList.do?submitAction=Display Calendar Event List&officerId=<bean:write name="scheduleNewEventForm" property="officerId"/></bean:define>                  										
									<bean:define id="juvenileNum"><bean:write name="scheduleNewEventForm" property="juvenileNum"/></bean:define>
									<bean:define id="officerId"><bean:write name="scheduleNewEventForm" property="officerId"/></bean:define>
									<bean:define id="caseFileId"><bean:write name="scheduleNewEventForm" property="caseFileId"/></bean:define>
        
				           			<% Boolean need = (Boolean)request.getAttribute("calendarNeedsRefresh");
				           				boolean needsRefresh = true;
				           				if( need != null)
													{
				           					needsRefresh = need.booleanValue();
				           				}
				           			%>
<%-- Begin Juvenile's Calendar --%>
					              	<bean:define id="juvenileCalendarTitle"><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.calendar" />&nbsp;-&nbsp;<bean:write name="scheduleNewEventForm" property="juvenileName"/></bean:define>
	            				  	<jims2:appcalendar  
			           					calendarStyleSheet="blueCalendarSkin.css"
			           					serviceEvent="messaging.calendar.GetCalendarServiceEventsEvent"
			           					eventTimeFormat="h:mma"
			           					weekDayViewType="ABBREV"
			           					sessionAttributeName="<%=PDCalendarConstants.CALENDAR_TYPE_JUVENILE%>"
			           					eventLink="<%=eventJuvLinkURL%>"
			           					dayDisplayClass="ui.taglib.calendar.ConsolidatedEventDayPresentation" docketEventsNeeded="true"
			           					title="<%=juvenileCalendarTitle%>" 
			      							juvenileNum="<%=juvenileNum%>"
			      							responseType="short" 
			           					needsRefresh="<%=needsRefresh%>">									
									</jims2:appcalendar>
<%-- End Juvenile's Calendar --%>
								</td>
							</tr>
						</table>
<%-- END MONTH TABLE --%>
						<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
						<table border="0" width="100%">
							<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
								<tr>
									<td align="center">
										<logic:equal name="scheduleNewEventForm" property="allowUpdates" value="true"> 
										    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALENDAR_BTNS%>'>
											    <html:submit onclick="spinner()" property="submitAction"><bean:message key="button.scheduleJuvenileEvents"/></html:submit>
											</jims2:isAllowed>
										</logic:equal> 
										<logic:equal name="scheduleNewEventForm" property="allowPrePetitionUpdates" value="true"> 
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_JUV_SERV_PROVIDER_EVENTS_ONLY%>'>  
												<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.scheduleEventsBySPEventTypes"/></html:submit> 
											</jims2:isAllowed>
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALENDAR_BTNS%>'>
											    <html:submit onclick="spinner()" property="submitAction"><bean:message key="button.scheduleEventsByServiceProvider"/></html:submit>
											</jims2:isAllowed>
										</logic:equal>	 
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td align="center">
									<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
									<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
								</td>
							</tr>
						</table>
<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>