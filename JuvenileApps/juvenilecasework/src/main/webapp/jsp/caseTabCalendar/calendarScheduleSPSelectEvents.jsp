<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 06/20/2011	CShimek 	Create jsp for Activity 70628 --%>
<%-- 10/18/2011	CShimek 	Added edit to disable checkbox for event status code = SC (scheduled) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="ui.common.UIUtil" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarScheduleSPSelectEvents.jsp</title>

<html:base />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>


</head>
<%--BEGIN BODY TAG--%>
<body   topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayCalendarSPSelectEventsSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|"> 

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework" /> - Select Schedule Events for Calendar
		</td>
  	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Select event date(s) then click Next.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END Casefile Header TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAILS TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>
	  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="calendartab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert> 
			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>		  
						<div class='spacer'></div>		  
						<table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">      
							<tr> 
								<td class="detailHead" colspan='2'><bean:message key="prompt.serviceProvider" /></td>
							</tr>
							<tr>
								<td>
									<table align="center" width='100%' border='0' cellpadding="1" cellspacing="2" class='alternateRow'>
										<tr class='formDeLabel'>
											<td width='1%'colspan="2""><bean:write name="scheduleNewEventForm" property="serviceProviderName"/></td>
										</tr>
										<tr>
											<td class="formDe"><strong><bean:message key="prompt.program" />:</strong></td>
											<td><bean:write name="scheduleNewEventForm" property="programName"/>
												<logic:equal name='scheduleNewEventForm' property='hasProgramReferral' value='no'>
													<img src="/<msp:webapp/>images/r_no.gif" alt="calendarImage" title="no program referral" border='0'>											
												</logic:equal>
												<logic:equal name='scheduleNewEventForm' property='hasProgramReferral' value='yes'>
													<img src="/<msp:webapp/>images/r_yes.gif" alt="calendarImage" title="has program referral" border='0'>											
												</logic:equal>
												<logic:equal name='scheduleNewEventForm' property='hasProgramReferral' value='true'>
													<img src="/<msp:webapp/>images/r_otherCasefile.gif" alt="calendarImage" title="has program referral on other casefile" border='0'>											
												</logic:equal>
											</td>
										</tr>
										<tr>
											<td class="formDe" width="1%" nowrap="nowrap"><strong><bean:message key="prompt.serviceName" />:</strong></td>
											<td><bean:write name="scheduleNewEventForm" property="serviceName"/></td>
										</tr>
									</table>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr class='detailHead'>
											<td width='1%'><input type='checkbox' name="selectAll" id="selectAllEvents"></td>
											<td><bean:message key="prompt.eventDate" />
											 	<jims:sortResults beanName="scheduleNewEventForm" results="eventList" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>	 		
											<td><bean:message key="prompt.eventTime" /></td>											
											<td nowrap='nowrap'><bean:message key="prompt.eventType" /></td>											
											<td><bean:message key="prompt.locationUnit" /><jims:sortResults beanName="scheduleNewEventForm" results="eventList" primaryPropSort="serviceLocationName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" /></td>											
											<td nowrap='nowrap'><bean:message key="prompt.instructor" /><jims:sortResults beanName="scheduleNewEventForm" results="eventList" primaryPropSort="instructorName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="3" /></td>											
											<td><bean:message key="prompt.eventMax" /></td>
											<td nowrap='nowrap'><bean:message key="prompt.sched" /></td>
										</tr>
										<logic:iterate id="eventIndex" name="scheduleNewEventForm" property="eventList"  indexId="index">
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td>
													<logic:notEqual name="eventIndex" property="eventStatusCode" value="SC">
														<input type="checkbox" name="selectedEventIds" value="<bean:write name='eventIndex' property='eventId'/>">
													</logic:notEqual>
													<logic:equal name="eventIndex" property="eventStatusCode" value="SC">
														<input type="checkbox" name="noSelect" disabled="disabled" >
													</logic:equal>
													
												</td>
												<td>
										<%-- 		<a href="javascript:openWindow('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.conflictingEvents"/>&eventId=<bean:write name="eventIter" property="eventId"/>&eventType=JUV')">  --%>
												<a href="javascript:openWindow('/<msp:webapp/>displayCalendarSPSelectEventsSummary.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventIndex" property='eventId'/>&currentJuvenileId=<bean:write name="scheduleNewEventForm" property='juvenileNum'/>')"><bean:write name="eventIndex" property="eventDate"  formatKey="date.format.mmddyyyy"/></a></td>
												<td><bean:write name="eventIndex" property="eventDate"  formatKey="time.format.hhmma"/></td>
												<td><bean:write name="eventIndex" property="eventType" /></td>
												<td><bean:write name="eventIndex" property="serviceLocationName" /></td>
												<td><bean:write name="eventIndex" property="instructorName" /></td>
												<td><bean:write name="eventIndex" property="maxAttendance" /></td>
												<td><bean:write name="eventIndex" property="currentEnrollment" /></td>
											</tr>
										</logic:iterate>
									</table>
								</td>
							</tr>			
						</table>
						<div class='spacer'></div>	
					</td>
				</tr>
			</table>
			<div class='spacer'></div>	
<%-- BEGIN BUTTON TABLE --%>			
			<table width="100%">
				<tr>
					<td align="center">
						<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
						<logic:equal name='scheduleNewEventForm' property='hasProgramReferral' value='true'>
							<html:submit property="submitAction" disabled="true"><bean:message key="button.next" /></html:submit>
						</logic:equal>
						<logic:notEqual name='scheduleNewEventForm' property='hasProgramReferral' value='true'>
							<html:submit property="submitAction" styleId="validationSPSelect"><bean:message key="button.next" /></html:submit>
						</logic:notEqual>
						<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					</td>
				</tr>
			</table>	
<%-- END BUTTON TABLE --%>				
		</td>
	</tr>			
</table>	
<%-- END DETAILS TABLE --%>
<logic:iterate id="element" name="scheduleNewEventForm" property="selectedEventIds" indexId="index">
   <input type="hidden" name="uniqueIds" value=<bean:write name="element" /> >
</logic:iterate>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>