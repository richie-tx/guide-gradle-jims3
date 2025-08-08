<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 06/17/2011	CShimek 	Create jsp from copy of calendarScheduleNewEvent.jsp --%>
<%-- 5/11/2012	RYoung		ER #73310 Display casefile Info used to create event --%>

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
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
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

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarScheduleSPEvents.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<html:base />

</head>

<%--BEGIN BODY TAG--%>
<body topmargin='0' leftmargin='0' onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayCalendarSPSelectEvents" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|"> 
<input type="hidden" name="uniqueId" id="uniqueId" value="<bean:write name="scheduleNewEventForm" property="selectedServiceId"/>" />

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework" /> - Select Service Provider for Event
		</td>
  	</tr>
</table>

<table width="100%">
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
				<li>Select a <b>Service Provider</b> from the dropdown list, associated Program/Service Name(s) will display.</li>
				<li>Select a <b>Program/Service Name</b> from the associated display.</li>
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
<%-- BEGIN DETAILS TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>
	  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="calendartab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert> 
			<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
				<tr>
					<td>		  
						<div class='spacer'></div>		  
						<table align="center" width='100%' cellpadding="2" cellspacing="1" >      
							<tr> 
								<td class="detailHead" colspan='2'><bean:message key="prompt.serviceProvider" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap="nowrap">
									<img src="/<msp:webapp/>images/required.gif" title="Required" border='0' width='10' height='9'><bean:message key="prompt.serviceProvider" />
								</td>
								<td class="formDe">
									<html:select  name="scheduleNewEventForm" property="serviceProviderId" styleId="serviceProviderId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="scheduleNewEventForm" property="serviceProviderList" value="juvServProviderId" label="juvServProviderName" />					
									</html:select>
								</td>               
							</tr>
						</table>
						<table align="center" width='100%' cellpadding="2" cellspacing="1">
							<tr> 
								<td class="detailHead" colspan='2'><bean:message key="prompt.program" />/<bean:message key="prompt.serviceProvider" /></td>
							</tr>
							<logic:empty name="scheduleNewEventForm" property="programList">
								<tr> 
									<td colspan='2' align="left">No Program/Service Name found for Selected Service Provider</td>
								</tr>
							</logic:empty>
							<logic:iterate id="pgmIndex" name="scheduleNewEventForm" property="programList" indexId="index">								
								<tr>
									<td colspan="2">
										<table align="center" width='100%' border='0' cellpadding="2" cellspacing="1">
											<tr>
												<td class='formDeLabel' colspan="2">
													<a href="javascript:showHideExpand('sp<% out.print(index);%>', 'row', /<msp:webapp/>)" border=0><img src="/<msp:webapp/>images/expand.gif" border="0" name="sp<% out.print(index);%>"></a>&nbsp;<bean:message key="prompt.program" />:&nbsp<bean:write name="pgmIndex" property="programName" />
													<input type="hidden" name="spanName" value="sp<% out.print(index);%>" >
												</td>
											</tr>
											<tr id="sp<% out.print(index);%>Span" class="hidden">
												<td>
													<table width='100%' border='0' cellpadding="2" cellspacing="1">
														<tr class='detailHead'>
															<td></td>
															<td align="left"><img src="/<msp:webapp/>images/required.gif" title="Required" border='0' width='10' height='9'>Service Name</td>
														</tr>
											 			<logic:iterate id="servIndex"  name="pgmIndex" property="services" >
															<tr>
																<td width='1%'><input type='radio' name="selectedServiceId" value=<bean:write name="servIndex" property="serviceId" />></td>
																<td align="left"><bean:write name="servIndex" property="serviceName" /></td>
															</tr>
														</logic:iterate>	
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>					
							</logic:iterate>						
							
						</table>
						<table align="center" width='100%' cellpadding="2" cellspacing="1" >
							<tr> 
								<td class="detailHead" colspan='4'><bean:message key="prompt.date" /> or <bean:message key="prompt.dateRange" /></td>
							</tr>
							<tr> 
								<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.from" /></td>
								<td class="formDe">
									<html:text name="scheduleNewEventForm" property="eventFromDate" styleId="eventFromDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
								</td>
								<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.to" /></td>
								<td class="formDe">	
									<html:text name="scheduleNewEventForm" property="eventToDate" styleId="eventToDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
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
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_SP_EVENTS_MORETHAN7DAYSOLD%>'>	
							 	<html:submit property="submitAction" styleId="nextNoCheck"><bean:message key="button.next" /></html:submit>
							</jims2:isAllowed>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_SP_EVENTS_MORETHAN7DAYSOLD%>' value='false'>	
								<html:submit property="submitAction" styleId="nextCheck"><bean:message key="button.next" /></html:submit>
							</jims2:isAllowed>
						<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					</td>
				</tr>
			</table>	
<%-- END BUTTON TABLE --%>				
		</td>
	</tr>			
</table>	
<%-- END DETAILS TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>