<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 06/07/2006		AWidjaja Create JSP--%>
<%-- 10/27/2011		CShimek	 Added onclick script to Generate Document button to prevent duplicate selection --%>
<%-- 02/22/2016		RCapestani Bug 33590 MJCW: School Adjudication Notification (Calendar) Report - Remove "BFO" From Generate Document Button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>

<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/> - scheduleNewEventSummary.jsp</title>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/submitScheduleEvent" target="content"> 

<logic:equal name="pageType" value="summary">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|140">
</logic:equal>
<logic:equal name="pageType" value="confirmation">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|274">
</logic:equal>  
<logic:equal name="pageType" value="maxYouthLimit">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|274">
</logic:equal>        
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<logic:equal name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
		<logic:equal name="pageType" value="summary">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for<br>Past Pre-Scheduled Event(s) Summary</td></tr>
		</logic:equal>
		<logic:equal name="pageType" value="confirmation">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for<br>Past Pre-Scheduled Event(s) Confirmation</td></tr>
		</logic:equal>
	</logic:equal>

	<logic:notEqual name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
		<logic:equal name="pageType" value="summary">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Calendar Events Summary</td></tr>
		</logic:equal>
	
		<logic:equal name="pageType" value="confirmation">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Calendar Events Confirmation</td></tr>
		</logic:equal>
	</logic:notEqual>

	 <logic:equal name="pageType" value="confirmation">
		<tr><td align="center" class="confirm">The following event(s) has been scheduled</td></tr>
		<logic:equal name="scheduleNewEventForm" property="programReferralNew" value="true">
			<tr><td align='center' class="confirm">The following Program Referral has been created.</td></tr>   
		</logic:equal>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width='98%' align='center'>							
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>		
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<logic:equal name="pageType" value="summary">
	<table width="98%">
		<tr>
			<td>	
				<ul>
				    <li>Select a hyperlinked date to view the Event details.</li>
					<li>Select hyperlinked Event ID to view conflicting event.</li>
						<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
							<logic:notEmpty name="scheduleNewEventForm" property="programReferral"> 
								<li>Select <b>Add conflicting event</b> checkbox, if applicable, to add event regardless of conflict</li>
								<li>Select the <b>Finish</b> button to view the confirmation.</li>
							</logic:notEmpty>						
							<logic:empty name="scheduleNewEventForm" property="programReferral"> 
								<li>Select the <b>Create New Referral</b> button to create a new Referral.</li>
							</logic:empty>						
						</logic:equal>
					<li>Select <b>Back</b> button to reschedule selected event.</li>
				</ul>
			</td>
		</tr>
	</table>
</logic:equal>

<logic:equal name="pageType" value="confirmation">
	<table width="98%">
		<tr>
			<td>	
				<ul>
			    <li>Select a hyperlinked date to view the Event details.</li>
				</ul>
			</td>
		</tr>
	</table>
</logic:equal>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END Casefile Header TABLE --%>
<div class='spacer'></div>
<%--begin of blue tabs--%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>
			<%--begin of blue tabs--%> 
	  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="calendartab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert> 
	      <%--end of blue tabs--%> 
<%-- BEGIN BLUE BORDER TABS TABLE --%>
			<table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
						<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.juvenileConflictingEvents">
							<logic:equal name="pageType" value="summary">
  							<%-- BEGIN summary CONFLICTING JUVENILE EVENTS TABLE --%>
	  							<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
	  								<tr>
	  									<td class="detailHead">Existing Juvenile Events</td>
	  								</tr>
	  								<tr>
	  									<td valign='top' align='center'>
	  										<table width='100%' cellpadding="4" cellspacing="1">			
	  											<tr bgcolor='#cccccc'>										
	  												<td class="subHead"><bean:message key="prompt.eventDate"/>/<bean:message key="prompt.time"/></td>
	  												<td class="subHead">End <bean:message key="prompt.time"/></td>
	  												<td class="subHead"><bean:message key="prompt.serviceProvider"/></td>
	  												<td class="subHead"><bean:message key="prompt.location"/> <bean:message key="prompt.unit"/></td>
	  												<td class="subHead"><bean:message key="prompt.eventType"/></td>		
	  											</tr>
	  											
	  											<logic:iterate indexId="juvIndex" id="eventIter" name="scheduleNewEventForm" property="currentService.currentEvent.juvenileConflictingEvents">
	  												<tr class=<% out.print( (juvIndex.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %> height="100%"> 
	  													<td>
	  														<a href="javascript:openWindow('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.conflictingEvents"/>&eventId=<bean:write name="eventIter" property="eventId"/>&eventType=JUV')">																								
	  														<bean:write name="eventIter" property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<bean:write name="eventIter" property="eventDate" formatKey="time.format.hhmma" /></a>				
	  													</td> 
	
	                          							<td><bean:write name="eventIter" property="endDatetime" formatKey="time.format.hhmma" /></td>
	  													<td><bean:write name="eventIter" property="serviceProviderName"/></td>												
	  											
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">		
	  														<td><bean:write name="eventIter" property="location"/></td>			
	  													</logic:equal>
	  													
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">		
	  														<td><bean:write name="eventIter" property="familyLocation"/></td>			
	  													</logic:equal>
	  													
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">		
	  														<td><bean:write name="eventIter" property="familyLocation"/></td>			
	  													</logic:equal>
	
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">		
	  														<td><bean:write name="eventIter" property="familyLocation"/></td>			
	  													</logic:equal>
	  													
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">		
	  														<td><bean:write name="eventIter" property="familyLocation"/></td>			
	  													</logic:equal>
	  													
														<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">		
	  														<td><bean:write name="eventIter" property="familyLocation"/></td>			
	  													</logic:equal>
	  													
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
	  														<td><bean:write name="eventIter" property="schoolDistrictName"/>, <bean:write name="eventIter" property="schoolName"/></td>			
	  													</logic:equal>
	
														<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
	  														<td><bean:write name="eventIter" property="schoolDistrictName"/>, <bean:write name="eventIter" property="schoolName"/></td>	
	  													</logic:equal>
	
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
	  														<td><bean:write name="eventIter" property="facilityCd"/></td>			
	  													</logic:equal>
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
	  														<td><bean:write name="eventIter" property="facilityCd"/></td>			
	  													</logic:equal>
	
	  													<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
	  														<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
	  															<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
	  																<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
	    																<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
	   																		<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">
	   																			<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">
	   																				<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">
	   																					<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
	   																						<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">																
															    									<td><bean:write name="eventIter" property="serviceLocationName"/></td>	
															    							</logic:notEqual>																											
	   																					</logic:notEqual>
	   																				</logic:notEqual>
	   																			</logic:notEqual>
	   																		</logic:notEqual>
																		</logic:notEqual>					
			  														</logic:notEqual>
			  													</logic:notEqual>
			  												</logic:notEqual>
			  											</logic:notEqual>
	  													<td><bean:write name="eventIter" property="eventType"/></td>
	  												</tr>
	  											</logic:iterate>
	  										</table>
	  									</td>
	  								</tr>
	  							</table>
							</logic:equal>  <%-- end pageType = summary --%>
						</logic:notEmpty>	
  					<%-- END CONFLICTING JUVENILE  EVENTS TABLE --%>	
  					</td>
  				</tr>
		      	<tr>
      				<td>
      			<%-- BEGIN summary CONFLICTING JPO  EVENTS TABLE --%>
	    				<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.jpoConflictingEvents">
	    					<logic:equal name="pageType" value="summary">
		      					<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
		      						<tr>
		      							<td class="detailHead">Existing JPO Events</td>
		      						</tr>
		      						<tr>
		      							<td valign='top' align='center'>
		      								<table width='100%' cellpadding="4" cellspacing="1">			
		      									<tr bgcolor='#cccccc'>
		      										<td class="subHead"><bean:message key="prompt.eventDate"/>/<bean:message key="prompt.time"/></td>
	  												<td class="subHead">End <bean:message key="prompt.time"/></td>
		      										<td class="subHead"><bean:message key="prompt.serviceProvider"/></td>
		      										<td class="subHead"><bean:message key="prompt.location"/> <bean:message key="prompt.unit"/></td>
		      										<td class="subHead"><bean:message key="prompt.eventType"/></td>										
		      									</tr>
		      									<logic:iterate indexId="jpoIndex" id="eventIter" name="scheduleNewEventForm" property="currentService.currentEvent.jpoConflictingEvents">
		      										<tr class=<%out.print( (jpoIndex.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%> height="100%">
	    	  											<td>
	      													<a href="javascript:openWindow('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.conflictingEvents"/>&eventId=<bean:write name="eventIter" property="eventId"/>&eventType=JPO')">												
	      														<bean:write name="eventIter" property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<bean:write name="eventIter" property="eventDate" formatKey="time.format.hhmma"/></a>				
	      												</td>
							                            <td><bean:write name="eventIter" property="endDatetime" formatKey="time.format.hhmma" /></td>
	      												<td><bean:write name="eventIter" property="serviceProviderName"/></td>											
		      											<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">		
	      													<td><bean:write name="eventIter" property="location"/></td>			
	    	  											</logic:equal>
	      											
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">		
	      													<td><bean:write name="eventIter" property="familyLocation"/></td>			
	      												</logic:equal>
	
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">		
	      													<td><bean:write name="eventIter" property="familyLocation"/></td>			
	      												</logic:equal>
	
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">		
	      													<td><bean:write name="eventIter" property="familyLocation"/></td>			
	      												</logic:equal>      											
	
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">		
	      													<td><bean:write name="eventIter" property="familyLocation"/></td>			
	      												</logic:equal>      											
	
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">		
	      													<td><bean:write name="eventIter" property="familyLocation"/></td>			
	      												</logic:equal>      											
	      											
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
	      													<td><bean:write name="eventIter" property="schoolDistrictName"/>, <bean:write name="eventIter" property="schoolName"/></td>			
	      												</logic:equal>
	      											
	      												<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
	      													<td><bean:write name="eventIter" property="schoolDistrictName"/>, <bean:write name="eventIter" property="schoolName"/></td>			
	      												</logic:equal>
	      											
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
	  														<td><bean:write name="eventIter" property="facilityCd"/></td>			
	  													</logic:equal>
	  													
	  													<logic:equal name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
	  														<td><bean:write name="eventIter" property="facilityCd"/></td>			
	  													</logic:equal>
	
		  												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
		  													<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
			      												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
				      												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
					      												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
						       												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">															
							       												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">
								       												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">
									       												<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">
										       													<logic:notEqual name="eventIter" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
										       														<td><bean:write name="eventIter" property="serviceLocationName"/></td>	
										       													</logic:notEqual>
										       												</logic:notEqual>
										   											</logic:notEqual>
									   											</logic:notEqual>
								   											</logic:notEqual>
																		</logic:notEqual>					
					      											</logic:notEqual>
																</logic:notEqual>
															</logic:notEqual>
														</logic:notEqual>
		      											<td><bean:write name="eventIter" property="eventType"/></td>											
	    	  										</tr>
	      										</logic:iterate>
	      									</table>
	      								</td>
	      							</tr>
	      						</table>
	    					</logic:equal>  <!-- end pageType = summary -->
  					  	</logic:notEmpty>	
<%-- END CONFLICTING JPO EVENTS TABLE --%>	
    		  		</td>
    	  		</tr>
<%-- BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
				<tr>
					<td>
						<logic:notEmpty name="scheduleNewEventForm" property="programReferral">
         					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
         						<tr>
         							<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /></td> 
         						</tr>
         						<tr>
      				    			<td valign='top' align='center'>
      					    			<table cellpadding='2' cellspacing='1' width='100%'>
                      						<tr>
		        								<td class="formDeLabel"><bean:message key="prompt.beginDate" /></td>
		        								<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.beginDateStr"/></td>
		        								<td class="formDeLabel"><bean:message key="prompt.assignedHours" /></td>
		        								<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.assignedHours"/></td>
		        							</tr>
		        							<tr>
		        								<td class="formDeLabel"><bean:message key="prompt.courtOrdered" /></td>
		         								<bean:define id="progRef" name="scheduleNewEventForm" property="programReferral"/>		
		        								<td class="formDe"><jims2:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>								
		        								<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>
		        								<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.referralState.description"/></td>
		        							</tr>
		        							<tr>
		        								<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" /></td>
											</tr>
											<tr>
		        								<td class="formDe" colspan="4">
        											<div  class='scrollingDiv100'>
		        										<table>
		        											<logic:notEmpty name="scheduleNewEventForm" property="programReferral.referralComments">																							
		        												<logic:iterate  id="refComment" name="scheduleNewEventForm" property="programReferral.referralComments">
		        													<tr style="height:100%">
		    														  <td><bean:write name="refComment" property="commentText"/> [<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/> ]</td>
		    														</tr>
		        												</logic:iterate>
		        											</logic:notEmpty>
		        											<logic:notEmpty name="scheduleNewEventForm" property="programReferral.comments">
		        												<tr style="height:100%">
		    														  <td><bean:write name="scheduleNewEventForm" property="programReferral.comments"/> [<bean:write name="scheduleNewEventForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="scheduleNewEventForm" property="programReferral.currentUserName"/> ]</td>
		    														</tr>
		        											</logic:notEmpty>
		        										</table>
		        									</div>											
		        								</td>
        					              	</tr>
										</table>
									</td>
								</tr>
							</table>
						</logic:notEmpty>
					</td>  
				</tr>           
<%-- END PROGRAM REFERRAL DETAILS TABLE --%>
		      	<tr>
      				<td>
<%-- BEGIN EVENT DETAIL TABLE --%>
      					<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
		      				<tr>
		      					<td class="detailHead">Proposed <bean:message key="prompt.eventDetails" /></td>
		      				</tr>
		      				<tr>
		      					<td>
<%-- BEGIN GREY BORDER TABLE --%>
		      						<table width='100%' cellpadding="2" cellspacing="1" class="borderTableGrey">							
		      							<tr>
			  						      	<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
			  						      	<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.serviceType"/>
												<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
			  						      	 		<bean:write name="scheduleNewEventForm" property="currentService.letterType"/>
			  						      		</logic:equal></td>
										</tr>	
		      							<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">																	
	    									<tr>
	    										<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.serviceProvider"/></td>		
	    										<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.serviceProvider"/></td>			
	    									</tr>
	    
	    									<tr>
	    										<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.program"/></td>		
	    										<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="programName"/></td>			
	    									</tr>													
		      							</logic:equal>				
  				
		      							<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>">								
    										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.JOB_VISIT%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>
    									
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>
	    					
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_VISIT%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>
	
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>

	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>
	
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">		
	    										<tr>
	    											<td class="formDeLabel" width="1%" colspan='2'><bean:message key="prompt.locationMemberName"/></td>		
	    										</tr>
	    										<tr>
	    											<td class="formDe" nowrap='nowrap' colspan='2'><bean:write name="scheduleNewEventForm" property="currentService.memberLocation"/></td>			
	    										</tr>
	    									</logic:equal>
	    									
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
	    										<tr>
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.schoolDistrictName"/></td>			
	    										</tr>	
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolName" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.schoolName"/></td>														
	    										</tr>
	    									</logic:equal>
    									
	    									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
	    										<tr>
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.schoolDistrictName"/></td>			
	    										</tr>	
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolName" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.schoolName"/></td>														
	    										</tr>
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personContacted" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.contactFirstName"/>	<bean:write name="scheduleNewEventForm" property="currentService.contactLastName"/></td>														
	    										</tr>
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sexOffender"/> <bean:message key="prompt.registrant"/></td>		
	    											<td class="formDe">
	    												<logic:equal name="scheduleNewEventForm" property="currentService.sexOffenderRegistrantStr" value="Y">
	    													<bean:message key="prompt.yes"/>
	    												</logic:equal>
	    												<logic:equal name="scheduleNewEventForm" property="currentService.sexOffenderRegistrantStr" value="N">
	    													<bean:message key="prompt.no"/>
	    												</logic:equal>
	    											</td>														
	    										</tr>
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.restrictionsOther"/></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.restrictionsOther"/></td>														
	    										</tr>
	    										<tr>
	    											<td class="detailHead" colspan="2"><bean:message key="prompt.offenses" /></td>
	    										</tr>
	    										<logic:iterate id="offs" name="scheduleNewEventForm" property="offenseInvolvedWeaponList" >
		    										<tr>
		    											<td class="formDeLabel" colspan="2">
		    												(<bean:write name="offs" property="referralNumber"/> - <bean:write name="offs" property="offenseCategoryDescription"/>) <bean:write name="offs" property="offenseDescription" />
		    											</td>
		    										</tr>
		    										<tr>
		    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.weaponInvolved"/></td>		
		    											<td class="formDe">
		    												<logic:equal name="offs" property="weaponInvolvedStr" value="Y">
		    													<bean:message key="prompt.yes"/>
		    												</logic:equal>
		    												<logic:equal name="offs" property="weaponInvolvedStr" value="N">
		    													<bean:message key="prompt.no"/>
		    												</logic:equal>
		    											</td>														
		    										</tr>
		    										<tr>	
		    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.typeOfWeapon"/></td>		
		    											<td class="formDe" nowrap='nowrap'><bean:write name="offs" property="typeOfWeaponDescription"/></td>														
		    										</tr>
												</logic:iterate>	
	    									</logic:equal>
    										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">	
    										
    											<tr>
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.controllingReferral" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.controllingReferral"/></td>			
	    										</tr>	
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'>My Office hours are</td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="officerHours"/></td>														
	    										</tr>
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.phone" /></td>	
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="officerPhone"/> Ext <bean:write name="scheduleNewEventForm" property="officerPhoneExtn"/></td>		
	    																									
	    										</tr>
	    										<tr>	
	    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.fax" /></td>		
	    											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="officerFax"/></td>														
	    										</tr>
    											
    										</logic:equal>
	      									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
	      										<tr>	
	      											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventLocation" /></td>		
	      											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.facility"/></td>			
	      										</tr>											
	      									</logic:equal>
	      									<!-- Added for 12533 user story -->
	      									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
	      										<tr>	
	      											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventLocation" /></td>		
	      											<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.facility"/></td>			
	      										</tr>											
	      									</logic:equal>
    									
		    								<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
		    									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
			    									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
				    									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
					    									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
					      										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">
						      										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">
							      										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">
								      										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
									      										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
									      											<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
									      											<tr>	
									      												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventLocation" /></td>		
									      												<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.location"/></td>			
									      											</tr>
									      											</logic:notEqual>	
									      										</logic:notEqual>			
									      									</logic:notEqual>
								      									</logic:notEqual>
							      									</logic:notEqual>
						      									</logic:notEqual>
														    </logic:notEqual>
				    									</logic:notEqual>					
			    									</logic:notEqual>
		    									</logic:notEqual>
	    									</logic:notEqual>
											<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
												<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">				
					              				<tr>
					              					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength"/></td>
							                        <td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.sessionLengthDesc"/></td>	
					              				</tr>
					              				</logic:notEqual>
				              				</logic:notEqual>
										</logic:equal>
  				
      									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">													  
        							<%-- interview type table ... shown when an interview type is selected above --%>
    										<tr>
    											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personsInterviewed" /></td> 
    											<td class="formDe">
	    											<logic:notEmpty name="scheduleNewEventForm" property="currentService.selectedIntervieweeList">
	    												<logic:iterate id="personsIter" name="scheduleNewEventForm" property="currentService.selectedIntervieweeList">
	    													<bean:write name="personsIter" property="formattedName"/><br>
	    												</logic:iterate>
	    											</logic:notEmpty>												
	    										</td> 
	    									</tr>		
	    									<tr>	
	    										<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.interviewType" /></td> 			
	    										<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.interviewType"/></td>			
	    									</tr>	
	    
	    									<tr>
	    										<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.interview" />&nbsp;<bean:message key="prompt.location" /></td>		
	    										<td class="formDe" nowrap='nowrap'><bean:write name="scheduleNewEventForm" property="currentService.location"/></td>		
	    									</tr>	
				              				<tr>
              									<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength"/></td>
                        						<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.sessionLengthDesc"/></td>	
              								</tr>
    									
	    									<logic:equal name="scheduleNewEventForm" property="currentService.locationId" value="<%=UIConstants.USER_ENTERED_CUSTOM_ADDRESS%>">
		      									<tr>
		      										<td colspan="2">
		      											<table width="100%" cellpadding="2" cellspacing="1">
		      												<tr>
		      													<td class="detailHead" ><bean:message key="prompt.new" />&nbsp;<bean:message key="prompt.interview" />&nbsp;<bean:message key="prompt.address" /></td>											
		      													<td class="detailHead"><bean:message key="prompt.address" />&nbsp;<bean:message key="prompt.status" /></td>
		      													<td class="detailHead" colspan="2">
		      													   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="">UNPROCESSED</logic:equal>       	    
		      													   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="U">UNPROCESSED</logic:equal>
		      													   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="Y">VALID</logic:equal>
		      													   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="N">INVALID</logic:equal>
		      													</td>
		      												</tr>
     												
		      												<tr id='saddr0'>
		      													<td class="formDeLabel" ><bean:message key="prompt.streetNumber" /></td>
		      													<td class="formDe" ><bean:write name="scheduleNewEventForm" property="currentService.newAddress.streetNum"/></td>
		      													<td class="formDeLabel" ><bean:message key="prompt.streetNumber" />&nbsp;<bean:message key="prompt.suffix" /></td>
		      													<td class="formDe" ><bean:write name="scheduleNewEventForm" property="currentService.streetNumSuffixName"/></td>
		      												</tr>
		      												<tr>	
		      													<td class="formDeLabel"><bean:message key="prompt.streetName" /></td>
		      													<td class="formDe" colspan="3"><bean:write name="scheduleNewEventForm" property="currentService.newAddress.streetName"/></td>
		      												</tr>
		      
		      												<tr id='saddr1'>
		      													<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>														
		      													<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.streetTypeName"/></td>
		      													<td class="formDeLabel"><bean:message key="prompt.apartmentNumber" /></td>
		      													<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.newAddress.aptNum"/></td>
		      												</tr>
															<tr id='saddr2'>
																<td class="formDeLabel"><bean:message key="prompt.city" /></td>
																<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.newAddress.city"/></td>
																<td class="formDeLabel"><bean:message key="prompt.state" /></td>
																<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.stateName"/></td>  
															</tr>
															<tr id='saddr3'>
																<td class="formDeLabel"><bean:message key="prompt.addressType" /></td>
																<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.addressTypeName"/></td>
																<td class="formDeLabel"><bean:message key="prompt.county" /></td>
																<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.countyName"/></td>
															</tr>
															<tr id='saddr4'>
																<td class="formDeLabel"><bean:message key="prompt.zipCode" /></td>
																<td class="formDe" colspan='3'><bean:write name="scheduleNewEventForm" property="currentService.newAddress.zipCode"/>-<bean:write name="scheduleNewEventForm" property="currentService.newAddress.additionalZipCode"/></td>
															</tr>
														</table>
													</td>
												</tr>
											</logic:equal>	<!--  end custom address -->									
										</logic:equal>
												
 										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">	
 											<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">																												
											<tr>
												<td class="detailHead" colspan="2" nowrap='nowrap' ><bean:message key="prompt.comments" /></td>						
											</tr>
											<tr>
												<td class="formDe" colspan="2"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.comments"/>&nbsp;</td>						
											</tr>
											</logic:notEqual>
										</logic:notEqual>
		      						</table>
<%-- END GREY BORDER TABLE --%>
									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
<%-- BEGIN NON-SHCOOL ADJUCICATION TABLE --%>
      									<table width='100%' cellpadding="2" cellspacing="1">
          									<tr class='alternateRow'>
          										<td colspan='2'>
          											<table width='100%' cellpadding="2" cellspacing="1">							
          												<tr>
          													<logic:equal name="pageType" value="summary">
          														<td class="formDeLabel" width="1%" nowrap='nowrap'>									
          															<%String tableHeader="";%>											
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.jpoConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.juvenileConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>																					
          															<%=tableHeader%>
          														</td>
          													</logic:equal>	
				          									<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
				          									<td class="formDeLabel"><bean:message key="prompt.eventTime" /></td>
				          									<td class="formDeLabel">End <bean:message key="prompt.time" /></td>
			         										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
	        				  									<td class="formDeLabel"><bean:message key="prompt.service" /></td>
	          												</logic:equal>
          												</tr>					
          
				          								<nested:iterate indexId="eventIndex" id="event" name="scheduleNewEventForm" property="allEvents" indexId="indexer">
				            								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				            									<logic:equal name="pageType" value="summary">	
				            										<td align="center">
				            											<nested:equal property="conflictingEvent" value="true"><img alt='exclaim' src="/<msp:webapp/>images/exclamation-triangle.gif" /><nested:checkbox property="addConflictingEvent" value="true" styleId="setConflictingSelection"/></nested:equal>
				            										</td>
				            									</logic:equal>
				            									<td>	
				            										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
				            											<a href="javascript:openWindow('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key='button.preScheduledEvents' />&eventId=<nested:write property="eventId" />')">																									
				            											<nested:write property="startDatetime" formatKey="date.format.mmddyyyy" /></a>
				            											<input type="hidden" name="saveEventDate" value="<nested:write property='startDatetime' formatKey="date.format.mmddyyyy" />"  id="eventDate">
				            										</logic:equal>	
				            										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">																						
				           												<nested:write property="startDatetime" formatKey="date.format.mmddyyyy" />	
				            										</logic:notEqual>
				            									</td>
				            									<td><nested:write property="startDatetime" formatKey="time.format.hhmma"/></td>					
				            									<td><nested:write property="endDatetime" formatKey="time.format.hhmma"/></td>	
				            									<input type="hidden" name="saveEventDate" value="<nested:write property='startDatetime' formatKey="date.format.mmddyyyy" />"  id="eventDate">	
				            									<input type="hidden" name="saveEventDate" value="<nested:write property='startDatetime' formatKey="time.format.hhmma"/>" id="eventTime">
				            										
					         									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
						            								<td><bean:write name="scheduleNewEventForm" property="currentService.service"/></td>					
						            							</logic:equal>
				            								</tr>		
																<nested:define id="eventId" property="addConflictingEvent"/>
																<input type="hidden" name="eventSelection" value="<%=eventId%>" id="eventSelectionId"/>
				         										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
						            								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
						            									<td nowrap='nowrap' align='right' style="font-weight: bold;">Comments</td>
						            									<td colspan='4'><nested:write property="eventComments" /></td>
						            								</tr>
				         										</logic:equal>
				          								</nested:iterate>					
				          							</table>
          										</td>	
          									</tr>						
				        				</table>
<%-- END NON-SHCOOL ADJUCICATION TABLE --%>	
										</logic:notEqual>			        				
        							</logic:notEqual>
									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
<%-- BEGIN SHCOOL ADJUCICATION TABLE --%>
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr class='alternateRow'>
												<td colspan='2'>
													<table width='100%' cellpadding="2" cellspacing="1">							
														<tr>
          													<logic:equal name="pageType" value="summary">
          														<td class="formDeLabel" width="1%" nowrap='nowrap'>									
          															<%String tableHeader="";%>											
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.jpoConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.juvenileConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>																					
          															<%=tableHeader%>
          														</td>
          													</logic:equal>	
          													<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.dateOfContact" /></td>
          													<td class="formDeLabel"><bean:message key="prompt.contactTime" /></td>
          												</tr>					
				          								<nested:iterate indexId="eventIndex" id="event" name="scheduleNewEventForm" property="allEvents" indexId="indexer">
				            								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				            									<logic:equal name="pageType" value="summary">	
				            										<td align="center">
				            											<nested:equal property="conflictingEvent" value="true"><img alt='exclaim' src="/<msp:webapp/>images/exclamation-triangle.gif" /><nested:checkbox property="addConflictingEvent" value="true" /></nested:equal>													
				            										</td>
				            									</logic:equal>
				            									<td>	
				            										<nested:write property="startDatetime" formatKey="date.format.mmddyyyy" />	
				            									</td>
				            									<td><nested:write property="startDatetime" formatKey="time.format.hhmma"/></td>					
				            								</tr>		
				          								</nested:iterate>					
				          							</table>
          										</td>	
          									</tr>						
        								</table>
<%-- END SHCOOL ADJUCICATION TABLE --%>        								
									</logic:equal>
									
										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
<%-- BEGIN APPOINTMENT LETTER TABLE --%>
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr class='alternateRow'>
												<td colspan='2'>
													<table width='100%' cellpadding="2" cellspacing="1">							
														<tr>
          													<logic:equal name="pageType" value="summary">
          														<td class="formDeLabel" width="1%" nowrap='nowrap'>									
          															<%String tableHeader="";%>											
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.jpoConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>
          															<logic:notEmpty name="scheduleNewEventForm" property="currentService.currentEvent.juvenileConflictingEvents">
          																<%tableHeader="Add Conflicting Event";%>
          															</logic:notEmpty>																					
          															<%=tableHeader%>
          														</td>
          													</logic:equal>	
          													<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
          													<td class="formDeLabel"><bean:message key="prompt.eventTime" /></td>
          												</tr>					
				          								<nested:iterate indexId="eventIndex" id="event" name="scheduleNewEventForm" property="allEvents" indexId="indexer">
				            								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				            									<logic:equal name="pageType" value="summary">	
				            										<td align="center">
				            											<nested:equal property="conflictingEvent" value="true"><img alt='exclaim' src="/<msp:webapp/>images/exclamation-triangle.gif" /><nested:checkbox property="addConflictingEvent" value="true" /></nested:equal>													
				            										</td>
				            									</logic:equal>
				            									<td>	
				            										<nested:write property="startDatetime" formatKey="date.format.mmddyyyy" />	
				            									</td>
				            									<td><nested:write property="startDatetime" formatKey="time.format.hhmma"/></td>					
				            								</tr>		
				          								</nested:iterate>					
				          							</table>
          										</td>	
          									</tr>						
        								</table>
<%-- END APPOINTMENT LETTER TABLE --%>        								
									</logic:equal>
								</td>
							</tr>				
						</table>
<%-- END EVENT DETAIL TABLE --%>
   			    	</td>
     			</tr>	
		    	<tr>
    				<td>
<%-- BEGIN BUTTON TABLE --%>
						<div class='spacer'></div>
		    			<table width="100%">
   							<tr>
   			    				<td align="center">
			        				<logic:equal name="pageType" value="summary">
       									<input type="submit" name="submitAction" id="resetActionSummary" data-href='Back' value="<bean:message key='button.back'/>">
   										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
     										<html:submit property="submitAction" styleId="check4DocGenerated"><bean:message key="button.generateDocument"/></html:submit>
     									</logic:equal>
     									<!-- Added for 11109 user story -->
     									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
     										<html:submit property="submitAction" styleId="checkApptLetter"><bean:message key="button.generateAppointmentLetter"/></html:submit>
     									</logic:equal>
     									<!-- Added for 12533 user story -->
     									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">	
     										<input type="submit" name="submitAction" value="<bean:message key='button.generateSpanishLetter'/>" id="checkEventSelected" data-href="ES"> 
     										<input type="submit" name="submitAction" value="<bean:message key='button.generateEnglishLetter'/>" id="checkEventSelected2" data-href="EN">
     									</logic:equal>
     									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CLOSING_LETTER%>">	
     										<input type="submit" name="submitAction" value="<bean:message key='button.closingLetterEnglish'/>" id="checkEventSelected" data-href="closingLetterEnglish"> 
     										<input type="submit" name="submitAction" value="<bean:message key='button.closingLetterSpanish'/>" id="checkEventSelected2" data-href="closingLetterSpanish">
     									</logic:equal>     									
     									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CLOSING_PACKET%>">
     										<input type="submit" name="submitAction" value="<bean:message key='button.closingPacketEnglish'/>" id="checkEventSelected" data-href="closingPacketEnglish">
     										<input type="submit" name="submitAction" value="<bean:message key='button.closingPacketSpanish'/>" id="checkEventSelected2" data-href="closingPacketSpanish">
     									</logic:equal>
     									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
     										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
        										<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
        										<logic:notEmpty name="scheduleNewEventForm" property="programReferral"> 
        											<%--Bug fix #28488  starts--%>
        											<html:submit property="submitAction" styleId="disableSummaryFinish"><bean:message key="button.finish"/></html:submit>
        											<%--Bug fix #28488  ends--%>  			
        										</logic:notEmpty>
	        									<logic:empty name="scheduleNewEventForm" property="programReferral"> 
	        										<html:submit property="submitAction"><bean:message key="button.createNewReferral" /></html:submit>									 
	        									</logic:empty>						
		        							</logic:equal>
			        						<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
			        							<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CLOSING_LETTER%>">
			        								<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CLOSING_PACKET%>">
			        									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
			        									<%--Bug fix #28488  starts--%>	
		        										<html:submit property="submitAction" styleId="disableSummaryFinish2"><bean:message key="button.finish" /></html:submit>
			        									<%--Bug fix #28488  ends--%>	
			        									</logic:notEqual>
			        								</logic:notEqual>
			        							</logic:notEqual>	
			        						</logic:notEqual> 
		        					</logic:notEqual>
		        					</logic:notEqual>
       								<input type="submit" name="submitAction" id="resetActionSummary2" data-href='Cancel' value="<bean:message key='button.cancel'/>">
       							</logic:equal>
       							
			      				<logic:equal name="pageType" value="confirmation">
			      					<div id="docPastEvents">
			      						<input type="button" value="<bean:message key="button.documentAttendance"/>" onClick="goNav('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.documentAttendance"/>&currentJuvenileId=<bean:write name="scheduleNewEventForm" property="juvenileNum"/>&eventId=<bean:write name="scheduleNewEventForm" property="currentService.currentEvent.proposedEventId"/>&preScheEvt=true');"  />
			      					</div>
			      					<div class='spacer'></div>
			      					<html:submit property="submitAction"><bean:message key="button.returnToCalendar"/></html:submit> 
								</logic:equal>
								<logic:equal name="pageType" value="maxYouthLimit">
			      					<%-- <input type="submit" name="submitAction" id="resetActionSummary" data-href='Back' value="<bean:message key='button.back'/>">
			      					<input type="submit" name="submitAction" id="resetActionSummary2" data-href='Cancel' value="<bean:message key='button.cancel'/>"> --%>
			      					<div class='spacer'></div>
			      					<html:submit property="submitAction"><bean:message key="button.returnToCalendar"/></html:submit> 
								</logic:equal>
   			  				</tr>
   						</table>
<%-- END BUTTON TABLE --%>
			    	</td>
    			</tr>
			</table>
<%-- BEGIN BLUE BORDER TABS TABLE --%>			
		</td>
	</tr>
</table>
<input type="hidden" name="docGenInd" id="DocGenerated" value="" >
</html:form>

<%-- <logic:equal name="pageType" value="confirmation">
<html:form action="/handleCalendarEventDetails?eventId=<bean:write name="scheduleNewEventForm.currentService.currentEvent" property="currentService.currentEvent.eventId"/>" target="content">
	<html:submit property="submitAction"><bean:message key="button.documentAttendance"/></html:submit>
</html:form>			
</logic:equal> --%>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>