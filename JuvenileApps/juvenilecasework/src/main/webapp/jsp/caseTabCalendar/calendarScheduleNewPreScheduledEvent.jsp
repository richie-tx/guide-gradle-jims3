<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 12/2006	MTobler		Create jsp --%>
<%-- 7/17/2007	LDeen		Defect #43737 correcting validation messages --%>
<%-- 10/26/2007	CShimek		correct jsp name in title to reflect this page --%>

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

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> -  Workshop Calendar - calendarScheduleNewPrescheduledEvent.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<html:base />

<html:javascript formName="scheduleCalendarEvent"/>



</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>
<html:form action="/handleScheduleEvent" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|142"> 
<input type="hidden" name="uniqueId" value=""/>

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
  <tr>
		<logic:equal name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
	    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp; -&nbsp; Workshop Calendar - Schedule Juvenile for<br>Past Pre-Scheduled Event(s)</td>
    </logic:equal>

		<logic:notEqual name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
	    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Workshop Calendar -<bean:message key="title.scheduleNewCalendarEvent" /></td>
    </logic:notEqual>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END Heading TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Expand the desired Service Provider.</li>
        <li>Next, select an Event for the desired date/time/location.</li>
        <li>Select the Next button to view the Events Conflicts and Details page.</li>
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

<%-- BEGIN Detail TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>

  		<%--tabs start--%> 
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="calendartab" />
  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
  		</tiles:insert> 
  		<%--tabs end--%>

			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
				  <td>		  
						  <div class='spacer'></div>		  
					    <table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">      
								<%-- <tr> table titlebar
									<td class="detailHead" colspan='2'><bean:message key="prompt.eventType" /></td>
								</tr> --%>
								<tr>
									<td class="detailHead" colspan='4'><bean:message key="prompt.dateRange" /></td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.from" /></td>
									<td class="formDe">
										<html:text name="scheduleNewEventForm" property="eventFromDate" styleId="eventFromDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
									</td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.to" /></td>
									<td class="formDe">	
										<html:text name="scheduleNewEventForm" property="eventToDate" styleId="eventToDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
									</td>	
									<td class="detailHead" colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventType" /></td>	
									<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.select" /></td>
									<td class="formDe">
										<html:select  name="scheduleNewEventForm" property="currentService.serviceTypeCode" styleId="curSerServiceTypeCode">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeCode" label="description" />					
										</html:select>
									</td>               
								</tr>
						</table>
					</td>
				</tr>
         					
				<tr id='groupTable'>
					<td>
					  <div class='spacer'></div>	
							<logic:notEmpty name="scheduleNewEventForm" property="preScheduledEvents">	  
						<table align="center" width='98%' border='0' cellpadding="2" cellspacing="1" class='borderTableBlue'>

							<%-- begin repeating .... expandable titlebar --%>
								<tr>
									<td class="detailHead" nowrap='nowrap'><bean:message key="prompt.serviceProvider" />
									</td>
								</tr>
							
								<nested:iterate indexId="serviceProviderIndex" id="serviceProvider" name="scheduleNewEventForm" property="preScheduledEvents">
							
									<nested:notEmpty name="serviceProvider" property="preScheduledServices">
									<nested:size id="servicesCount" name="serviceProvider"  property="preScheduledServices"/>
										<tr class='selectedRow'>							
											<td class="formDeLabel" width='1%'>
												<a href="javascript:showHideMulti(
														'list1ParentExpandServ<nested:write name="serviceProvider" property="serviceProviderId"/>', 
														'Serv<nested:write name="serviceProvider" property="serviceProviderId"/>-', 1, '/<msp:webapp/>');"  id='serviceProviderSelectedId<nested:write name="serviceProvider" property="serviceProviderId"/>'>
  												<img src="/<msp:webapp/>images/expand.gif" 
  												   name="list1ParentExpandServ<nested:write name='serviceProvider' property='serviceProviderId'/>" border='0'></a>
														 &nbsp;<nested:write name="serviceProvider" property="serviceProviderName"/>
											</td>	
										</tr>	 
										<tr id='Serv<nested:write name="serviceProvider" property="serviceProviderId"/>-0' class='hidden'>														
											<td>														
												<table align="center" width='100%' border='0' cellpadding="0" cellspacing="0">
													
													<nested:iterate indexId="programIndex" id="preScheduledService" property="preScheduledServices">									
														<nested:size id="eventCount" name="preScheduledService" property="eventList"/>
														<%eventCount = new Integer(eventCount.intValue() +1);%>
														<tr class='selectedRow' align="left">
															<td nowrap='nowrap' width='1%' colspan="6">
																<a href="javascript:showHideMulti(
																		'list1ParentExpandProg<nested:write name="preScheduledService" property="programId"/>', 
																		'ProgHigh<nested:write name="preScheduledService" property="programId"/>-', 1, '/<msp:webapp/>');" id='programNameId<nested:write name="preScheduledService" property="programId"/>'>												
 																<img src="/<msp:webapp/>images/expand.gif" 
  																	name="list1ParentExpandProg<nested:write name='preScheduledService' property='programId'/>" id="img<nested:write name='preScheduledService' property='programId'/>" border='0'></a>
																		&nbsp;Program Name: <nested:write name="preScheduledService" property="programName"/> 
																<logic:equal name='preScheduledService' property='hasProgramReferral' value='no'>
																	<img src="/<msp:webapp/>images/r_no.gif" alt="calendarImage" title="no program referral" border='0'>											
														    </logic:equal>
																<logic:equal name='preScheduledService' property='hasProgramReferral' value='yes'>
																	<img src="/<msp:webapp/>images/r_yes.gif" alt="calendarImage" title="has program referral" border='0'>											
																</logic:equal>
																<logic:equal name='preScheduledService' property='hasProgramReferral' value='true'>
																	<img src="/<msp:webapp/>images/r_otherCasefile.gif" alt="calendarImage" title="has program referral on other casefile" border='0'>											
																</logic:equal>
															</td>	
														</tr>
														<%int serviceResponseEventsIndex = 0;%>															
														<tr id='ProgHigh<nested:write name="preScheduledService" property="programId"/>-<%=serviceResponseEventsIndex%>' class='hidden'>
															<td nowrap='nowrap' width='1%' colspan="6">
																<table width='100%' border='0' cellpadding="0" cellspacing="0">
																	<tr>																								
																		<td class="detailHead" nowrap='nowrap'></td>
																		<td class="detailHead" nowrap='nowrap'>Date/Time
																			 <jims:sortResults beanName="preScheduledService" results="eventList" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
																		<td class="detailHead" nowrap='nowrap'>Service Name
																			 <jims:sortResults beanName="preScheduledService" results="eventList" primaryPropSort="serviceName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" /></td>
																		<td class="detailHead">Location Unit
																			 <jims:sortResults beanName="preScheduledService" results="eventList" primaryPropSort="serviceLocationName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" /></td>
																		<td class="detailHead">Instructor
																			 <jims:sortResults beanName="preScheduledService" results="eventList" primaryPropSort="instructorName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" /></td>										
																		<td class="detailHead">Event Max</td>										
																		<td class="detailHead" nowrap='nowrap'>Sched</td>																				
																	</tr>

																	<nested:iterate property="eventList" indexId="index">
																		<nested:define id="currentEnroll" property="currentEnrollment"/>	
																		<nested:define id="maxAtt" property="maxAttendance"/>
																		
                                    <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																		  <% int temp = (index.intValue() -1);
  																			int temp1 = eventCount.intValue()-1;
  																			int max = Integer.parseInt( (String)maxAtt );
  																			int current = Integer.parseInt( (String)currentEnroll );
  																			boolean isEnabled = (current >= max) ? true : false;													
																		  %>								
																									
  																		<td nowrap='nowrap'><nested:checkbox property="preScheduledSelected" disabled="<%=isEnabled%>"/></td>
  																		<td><nested:write property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<nested:write property="eventDate" formatKey="time.format.hhmma"/></td>
  																		<td><nested:write property="serviceName"/></td>
  																		<td><nested:write property="serviceLocationName"/></td>	
  																		<td><nested:write property="instructorName"/></td>										
  																		<td nowrap='nowrap'><nested:write property="maxAttendance"/></td>										
  																		<td nowrap='nowrap'><nested:write property="currentEnrollment"/></td>										
  																  </tr>	
																	</nested:iterate>				
																</table>
															</td>
														</tr>
													</nested:iterate>	
												</table>	
											</td>						
										</tr>
									</nested:notEmpty>			
								</nested:iterate>
							

							<%-- end repeating data --%>
						</table>
						</logic:notEmpty>
						<%-- group table --%>
					</td>			
				</tr>

				<logic:empty name="scheduleNewEventForm" property="preScheduledEvents">
					<tr><td><br></td></tr>
					<tr>
						<td align="center" nowrap='nowrap'>&nbsp;&nbsp;There are no prescheduled events of this event type</td>
					</tr>
				</logic:empty>

				<tr>
					<td>
	          <%-- BEGIN BUTTON TABLE --%>
					  <div class='spacer'></div>
						<table width="100%"> 
						  <tr id='editButtons'>
	  						<td align="center">
	  						  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 

	  						  <logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">				
  	  							<logic:notEmpty name="scheduleNewEventForm" property="preScheduledEvents">
  	  								<html:submit property="submitAction" styleId="preSchedCustomValidation"><bean:message key="button.next" /></html:submit>
  	  							</logic:notEmpty>
	  						  </logic:equal>			

	  						  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
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
<br>

<%-- Begin Detail Table --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
