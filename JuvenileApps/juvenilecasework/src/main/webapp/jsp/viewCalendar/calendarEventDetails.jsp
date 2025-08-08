<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- jul 20 2009 - cws  #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarEventDetails.jsp</title>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<html:form action="/displayViewCalendarDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|351">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;- <bean:message key="title.calendarEvent" />&nbsp;<bean:message key="title.details" /></td>
  </tr>
</table>
<table width="100%">
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" align="center">
  <tr>
    <td>
      <ul>
        <li>Select the <b>Back</b> button to return to the previous page.</li>
        <logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
	        <li>The Juvenile Name in <b>bold text</b> was selected from the Search Results List.</li>
	        <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
        </logic:equal>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign='top' align='center'>

      <table align="center" width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      	<tr>
      		<td class="detailHead"><bean:message key="prompt.calendar" />&nbsp;<bean:message key="prompt.event" /></td>      		
      	</tr>

        <tr>
          <td>
            <!-- BEGIN DETAIL TABLE -->
						<div class='spacer'></div>
            <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
            		<td class="detailHead">
            			<table>
            				<tr>
            					<td class="detailHead" width="140px"><bean:message key="prompt.event" /></td>
            					<td class="detailHead">Event ID: <bean:write name="calendarEventListForm" property="currentEvent.eventId"/></td>
            				</tr>
            			</table>
            		</td>
            	</tr>
       		 <logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
              <tr> 
            		<td>
            			<table width='100%' cellpadding="2" cellspacing="1" align="center">
            			 
										<logic:empty name="calendarEventListForm" property="serviceProviderId">
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.serviceProvider" /></td>
	            					<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceProviderName"/></td>
	            				</tr>
	            			</logic:empty>
										<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.programName" /></td>
	            					<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.programName"/></td>
	            				</tr>
	            			</logic:notEmpty>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationUnit" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventStatus" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventStatus"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.sessionLength" /></td>
            					<td class="formDe"><bean:define id="eventSessionLength" name="calendarEventListForm" property="currentEvent.eventSessionLength" type="java.lang.String"/>
												<%=UIUtil.getTimeInHours(eventSessionLength)%></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.minimumAttendance" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.minAttendance"/></td>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.maximumAttendance" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.maxAttendance"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.totalScheduled" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.currentEnrollment"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.instructor" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.instructorName"/></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
            				</tr>
            				<tr>
	            				<td class="formDe" colspan='4'>
	           						<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
	            						<bean:write name="calendarEventListForm" property="progressNotes"/>
	           						</logic:notEmpty>
	           						<logic:empty name="calendarEventListForm" property="progressNotes"> 
	            						No Progress Notes.
	           						</logic:empty>
	           					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
        </logic:equal> 
				
				<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">
    				  <tr>				
            		<td>
            			<table width='100%' cellpadding="2" cellspacing="1" align="center">
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
            					<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personsInterviewed" /></td>
            					<td class="formDe" colspan='3'>

                       <logic:notEmpty name="calendarEventListForm" property="currentEvent.interviewPersons">
        									<logic:iterate id="personsIter" name="calendarEventListForm" property="currentEvent.interviewPersons">
        										<bean:write name="personsIter" property="formattedName"/><br>
        									</logic:iterate>
        							 </logic:notEmpty>

      							 </td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.interviewType" /></td>
            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.interviewType"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.interviewLocation" /></td>
            					<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.summary" /> <bean:message key="prompt.notes" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="summaryNotes"/></td>
            				</tr>
										
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'>
	           						<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
	            						<bean:write name="calendarEventListForm" property="progressNotes"/>
	           						</logic:notEmpty>
	           						<logic:empty name="calendarEventListForm" property="progressNotes"> 
	            						No Progress Notes.
	           						</logic:empty>
	           					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
        </logic:equal>

				<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>">				
            	<tr>
            		<td>
            			<table width='100%' cellpadding="2" cellspacing="1" align="center">
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
            					<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
            					<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
            					<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
            				</tr>

			                <logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolDistrictName"/></td>
	            				</tr>
	            				<tr>
	            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.schoolName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/></td>
	            				</tr>
			                </logic:equal>
	               
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>"> 
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationMemberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/></td>
	            				</tr>
		               		</logic:equal> 

							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>"> 
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationMemberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.location"/></td>
	            				</tr>
		               		</logic:equal> 
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>"> 
	            				<tr>
	            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationMemberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/></td>
	            				</tr>
		               		</logic:equal> 
	               
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
								<tr>
									<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>		
									<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
								</tr>
							</logic:equal>
							<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
								<tr>
									<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.locationUnit" /></td>		
									<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
								</tr>
							</logic:equal>

							<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">	
							<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">			               
	               			<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
	               			<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
	               			<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
	  						<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
			            		<tr>
			            			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationUnit" /></td>
			            			<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
			            		</tr>
		                    </logic:notEqual>
		                    </logic:notEqual>
		                    </logic:notEqual>
	    	           	    </logic:notEqual>        
            			    </logic:notEqual>   
            			    </logic:notEqual> 
                              
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.progressNotes" /></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'>
	           						<logic:notEmpty name="calendarEventListForm" property="progressNotes"> 
	            						<bean:write name="calendarEventListForm" property="progressNotes"/>
	           						</logic:notEmpty>
	           						<logic:empty name="calendarEventListForm" property="progressNotes"> 
	            						No Progress Notes.
	           						</logic:empty>
            					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            </logic:equal>
            </table>
            <!-- END DETAIL TABLE -->

      			<div class='spacer'></div>
      			<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
            		<td class="detailHead"><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.summary" />&nbsp;<bean:message key="prompt.list" /></td>
            	</tr>
            	<tr>
            		<td valign='top' align='center'>
            			<table width='100%' cellpadding="2" cellspacing="1" align="center">		
            				<tr bgcolor='#cccccc' class='subHead'>
            					<td width='1%' nowrap='nowrap'><bean:message key="prompt.juvenileName" /></td>
	                    <logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%>">
	                      <td width='1%' nowrap='nowrap'><bean:message key="prompt.acknowledgeDate" /></td>
	                      <td width='1%' nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>            					
	            					<td width='1%'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /></td>
	            				</logic:equal>
            					<td width="1%"><bean:message key="prompt.attendance" /> <bean:message key="prompt.status" /></td>
            				</tr>

										<%-- if this is a prescheduled event type --%>
                    <logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%>">
        							<logic:iterate id="referral" name="calendarEventListForm" property="programReferralList" indexId="index">
        								<tr class="<%out.print( ( index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">

			                    <logic:equal name="referral" property="outComeCd" value="VALUE_SELECTED">
	        									<td><b><bean:write name="referral" property="juvenileName"/></b></td>
	        								</logic:equal>
			                    <logic:notEqual name="referral" property="outComeCd" value="VALUE_SELECTED">
	        									<td><bean:write name="referral" property="juvenileName"/></td>
	        								</logic:notEqual>


        									<td><bean:write name="referral" property="acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
        									<td><bean:write name="referral" property="referralStatusDescription"/>&nbsp;<bean:write name="referral" property="referralSubStatusDescription"/></td>            														
        									<td><a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key="button.referral"/>&referralId=<bean:write name="referral" property="referralId" />',400,700)">View Details</a></td>
		            					<td><bean:write name="referral" property="extnNum"/></td>
        								</tr>	
        							</logic:iterate>
                    </logic:equal> 
                    
										<%-- ... and not a prescheduled event type --%> 							
                    <logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%>">
       								<tr class="alternateRow">	
       									<td><bean:write name="calendarEventListForm" property="searchEvent.juvenileFullname"/></td>
		            				<td><bean:write name="calendarEventListForm" property="attendanceStatus"/></td>
       								</tr>	
                    </logic:notEqual>
                    
            			</table>
            		</td>
            	</tr>
      			</table>
						
            <!-- BEGIN BUTTON TABLE -->
            <div class='spacer'></div>
            <table width="100%" align="center">  
              <tr>
                <td align="center">
                  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
    
                  <logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
                    <logic:notEmpty name="calendarEventListForm" property="programReferralList">
                      <bean:size id="juvList" name="calendarEventListForm" property="programReferralList"/>
                      <logic:greaterThan name="juvList" value="0">
                        <html:submit property="submitAction"><bean:message key="button.printAttendanceList"/></html:submit> 
                      </logic:greaterThan>
                    </logic:notEmpty>
                  </logic:equal>
                  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                </td>
              </tr>
            </table>
            <!-- END BUTTON TABLE -->

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
