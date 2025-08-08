<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 05/24/2013 C Shimek    #75575 added Instructor column --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
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
<title><bean:message key="title.heading" /> - calendarEventList.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">

<html:form action="/handleJuvenileServiceEvent" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|25"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" /> - <bean:message key="prompt.event" /> <bean:message key="prompt.list" /></td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">

<br>    
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Select hyperlink to view program details.</li>
				<li>Select <strong>Back</strong> button to return to previous page.</li>
				<li>Place cursor over <strong>Status</strong> code and click to see description.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<div class='spacer' />
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.event" /> <bean:message key="prompt.details" /> <bean:message key="prompt.list" /> for&nbsp;(<bean:write name="calendarEventListForm" property="eventDate" formatKey="date.format.mmddyyyy"/>)</td>
	</tr>
	<tr>
		<td>
<%-- BEGIN DETAILS TABLE --%>		
			<table width='100%' cellpadding="2" cellspacing="1">
				<logic:empty name="calendarEventListForm" property="dayEvents">
					<tr bgcolor='#cccccc'> 
						<td colspan="6" class="subHead">No Events Available </td> 
					</tr> 
				</logic:empty>
				<logic:notEmpty name="calendarEventListForm" property="dayEvents">
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.time" /></td>
						<td class="formDeLabel"><bean:message key="prompt.locationUnit" /></td>
						<td class="formDeLabel"><bean:message key="prompt.type" /></td>
						<td class="formDeLabel"><bean:message key="prompt.service" /> <bean:message key="prompt.name" /></td>
						<td class="formDeLabel"><bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>				  
						<td class="formDeLabel"><bean:message key="prompt.instructor" /></td>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.status" /></td>
					</tr> 
					<logic:iterate indexId="indexer" id="eventsForDay" name="calendarEventListForm" property="dayEvents" indexId="indexer">
<%-- Begin Pagination item wrap --%>
						<pg:item>		  
							<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
								<td>
									<bean:define id="currentServiceProviderId" name="scheduleNewEventForm" property="currentService.serviceProviderId" type="java.lang.String"/>
									<logic:equal name="scheduleNewEventForm" property="currentService.inHouse" value="true">
										<logic:equal name="eventsForDay" property="serviceProvideInhouse" value="true">
											<a href="/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>">
												<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/>
											</a>
										</logic:equal>
										<logic:equal name="eventsForDay" property="serviceProvideInhouse" value="false">
											<logic:equal name="eventsForDay" property="serviceProviderId" value="<%=currentServiceProviderId%>">
												<a href="/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>">
													<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/>
												</a>
											</logic:equal>
											<logic:notEqual name="eventsForDay" property="serviceProviderId" value="<%=currentServiceProviderId%>">
												<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/>						
											</logic:notEqual>
										</logic:equal>
									</logic:equal>
										
									<logic:equal name="scheduleNewEventForm" property="currentService.inHouse" value="false">
										<logic:equal name="eventsForDay" property="serviceProviderId" value="<%=currentServiceProviderId%>">
											<a href="/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>">
												<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/>
											</a>
										</logic:equal>
										<logic:notEqual name="eventsForDay" property="serviceProviderId" value="<%=currentServiceProviderId%>">
											<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/>						
										</logic:notEqual>
									</logic:equal>
			  				 	</td>
								<td><bean:write name="scheduleNewEventForm" property="currentService.location"/></td>
								<td><bean:write name="eventsForDay" property="eventType"/></td>
								<td><bean:write name="eventsForDay" property="serviceName"/></td>
								<td><bean:write name="eventsForDay" property="serviceProviderName"/></td>	
								<td><bean:write name="eventsForDay" property="instructorName"/></td>				  
								<td title='<bean:write name="eventsForDay" property="eventStatusCode"/>=<bean:write name="eventsForDay" property="eventStatus"/>'><bean:write name="eventsForDay" property="eventStatusCode"/></td>
							</tr>
						</pg:item>
<%-- End Pagination item wrap --%>		
					</logic:iterate>
				</logic:notEmpty>
			</table>
<%-- END DETAILS TABLE --%>	
<%-- Begin Pagination navigation TABLE--%>
			<table align="center">
				<tr>
					<td>
						<pg:index>
							<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
								<tiles:put name="pagerUniqueName" value="pagerSearch"/>
								<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
							</tiles:insert>
						</pg:index>
					</td>
				</tr>
			</table>
<%-- End Pagination navigation Row--%>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table width="100%" border="0">  
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>