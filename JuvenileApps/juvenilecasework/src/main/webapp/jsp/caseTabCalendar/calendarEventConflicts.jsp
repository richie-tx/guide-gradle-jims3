<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
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

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;-calendarEventConflicts.jsp</title>


<%-- this function was for prototyping only ... this function was called when
 the "Go Ahead and Schedule" button was selected ... if the "Simulate No Referral" 
 checkbox was checked, the user would be routed to the Create Referral flow --%>
<script type="text/javascript">
function checkReferral()
{
  if( document[0].referralCheckbox.checked )
  {
    goNav('../caseTabProgramReferrals/programReferralCreate.htm?create&action=create&sourcePage=calendar');
  }
  else
  {
    goNav('calendarEventsSummary.htm?sum&action=sum&sourcePage=conflict') ;
  }
}
</script>
<%-- this is for prototyping  --%>

</head>


<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/HandleScheduleEventAction" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|141"> 


<%-- BEGIN Heading TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.heading" />&nbsp;<bean:message key="title.juvenileCasework" />&nbsp;<bean:message key="title.calendarEvents" />&nbsp;<bean:message key="title.summary" /></td>
  </tr>
</table>

<table width="100%">
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<%-- END Heading TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Select hyperlinked Event ID to view conflicting event.</li>
        <li>Select <b>Select to add this event</b> checkbox, then <b>Go Ahead &amp; Schedule</b> button to add event regardless of conflict.</li>
        <li>To simulate that this Program event has no referral, check the <b>Simulate no Referral</b> checkbox (default is there is an associated Referral).</li>
        <li>Select <b>Back</b> button to reschedule selected event.</li>
        <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
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
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td>
      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="calendartab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
      <%--tabs end--%>
				
      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td>
      
            <%-- BEGIN Juvenile CONFLICTING EVENTS TABLE 
								 this table shows events on the Juvenile's calendar
								 that are already scheduled ... these are not events
								 that the user wants to schedule at this time 
						--%>
            <div class='spacer'></div>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr>
            		<td class="detailHead"><bean:message key="prompt.confictingJuvenileEvents" /></td>
            	</tr>
            	<tr>
            		<td valign="top" align="center">
            			<table width='100%' cellpadding="4" cellspacing="1">
                    <%-- column heading --%>			
            				<tr bgcolor='#cccccc'>
            					<td class="subHead"><bean:message key="prompt.event" />&nbsp;<bean:message key="prompt.dateTime" /></td>
            					<td class="subHead"><bean:message key="prompt.provider" /></td>
            					<td class="subHead"><bean:message key="prompt.location" /></td>
            					<td class="subHead"><bean:message key="prompt.type" /></td>
            				</tr>
										
                    <%-- column data --%>			
        						<logic:iterate indexId="juvIndex" id="eventIter" name="ScheduleCalendarNewEventForm" property="currentService.currentEvent.conflictingJuvenileEvents">
        							<tr class=<%String rowColor = "alternateRow";
														if (juvIndex.intValue() % 2 == 1)
														rowColor = "normalRow";
														out.print(rowColor);%>
												>
        								<td>
    											<a href="/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventIter" property='eventId'/>">
                            <bean:write name="eventIter" property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<bean:write name="eventIter" property="eventDate" formatKey="time.format.HHmm"/>
													</a>
                        </td>
        								<td><bean:write name="eventIter" property="serviceProviderName"/></td>
        								<td><bean:write name="eventIter" property="serviceLocationName"/></td>
        								<td><bean:write name="eventIter" property="eventType"/></td>
        							</tr>
        						</logic:iterate>
										
            			</table>
            		</td>
            	</tr>
            </table>
            <%-- END Juvenile CONFLICTING EVENTS TABLE --%>					

            <%-- BEGIN JPO CONFLICTING EVENTS TABLE 
								 this table shows events on the JPO's calendar
								 that are already scheduled ... these are not events
								 that the user wants to schedule at this time 
						--%>
            <br>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr>
            		<td class="detailHead"><bean:message key="prompt.confictingJPOEvents" /></td>
            	</tr>
            	<tr>
            		<td valign="top" align="center">
            			<table width='100%' cellpadding="4" cellspacing="1">
                    <%-- column heading --%>			
            				<tr bgcolor='#cccccc'>
            					<td class="subHead"><bean:message key="prompt.event" />&nbsp;<bean:message key="prompt.dateTime" /></td>
            					<td class="subHead"><bean:message key="prompt.provider" /></td>
            					<td class="subHead"><bean:message key="prompt.location" /></td>
            					<td class="subHead"><bean:message key="prompt.type" /></td>
            				</tr>
										
                    <%-- column data --%>			
        						<logic:iterate indexId="juvIndex" id="eventIter" name="ScheduleCalendarNewEventForm" property="currentService.currentEvent.conflictingJPOEvents">
        							<tr class=<%String rowColor = "alternateRow";
														if (juvIndex.intValue() % 2 == 1)
														rowColor = "normalRow";
														out.print(rowColor);%>
												>
        								<td>
    											<a href="/<msp:webapp/>displayServiceEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventIter" property='eventId'/>">
                            <bean:write name="eventIter" property="eventDate" formatKey="date.format.mmddyyyy"/>&nbsp;<bean:write name="eventIter" property="eventDate" formatKey="time.format.HHmm"/>
													</a>
                        </td>
        								<td><bean:write name="eventIter" property="serviceProviderName"/></td>
        								<td><bean:write name="eventIter" property="serviceLocationName"/></td>
        								<td><bean:write name="eventIter" property="eventType"/></td>
        							</tr>
        						</logic:iterate>
										
            			</table>
            		</td>
            	</tr>
            </table>
            <%-- END JPO CONFLICTING EVENTS TABLE --%>
            
            <%-- BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
            <div class='spacer'></div>
<%-- 10apr08 - mjt - why is this commented out ?!?!?!??!

          <logic:notEmpty name="programReferralForm" property="serviceEvents">
 			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
 				<tr>
 					<td class=detailHead><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:message key="prompt.program" />:&nbsp;<html:text size="30" maxlength="75" property="programName" /> Avoiding Alcohol</td>
 				</tr>

 				<tr id='referralDetailsViewOnly' class='hidden'>
  				    <td valign="top" align="center">
  					    <table cellpadding=2 cellspacing=1 width='100%'>
                            <tr>
								<td class="formDeLabel"><bean:message key="prompt.beginDate" /></td>
								<td class="formDe"><bean:write name="scheduleNewEventForm" property="beginDate"/></td>
								<td class="formDeLabel"><bean:message key="prompt.assignedHours" /></td>
								<td class="formDe"><bean:write name="scheduleNewEventForm" property="assignedHours"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.courtOrdered" /></td>
								<td class="formDe"><bean:write name="scheduleNewEventForm" property="isCourtOrdered"/></td>
								<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
								<td class="formDe"><bean:write name="scheduleNewEventForm" property="referralStatus"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="4"><bean:message key="prompt.2.comments" /></td>
                            </tr>
                            <tr>
								<td class="formDe" colspan="4">
									<div  class='scrollingDiv100'>
										<table>
											<tr><td><bean:write name="scheduleNewEventForm" property="comments"/></td></tr>
										</table>
									</div>											
								</td>
                            </tr>
                       </table>
                   </td>
               </tr>
            </table>
          </logic:notEmpty>
--%>     
            <%-- END PROGRAM REFERRAL DETAILS TABLE --%>					

            <%-- BEGIN EVENT DETAIL TABLE --%>
            <div class='spacer'></div>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr>
            		<td class="detailHead"><bean:message key="prompt.event" />&nbsp;<bean:message key="prompt.details" /></td>
            	</tr>
            	<tr>
            		<td valign="top" align="center">
            			<table width='100%' cellpadding="4" cellspacing="1">
            				
            				<tr>
            					<td colspan='4'>
            						<table align="center" width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
            
                    			<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.serviceProvider" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="serviceProvider"/></td>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.serviceName" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="serviceName"/></td>
                    			</tr>
              						
                    			<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.eventType" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="eventType"/></td>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.sessionLength" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="eventHours"/> hour(s)</td>
                    			</tr>
                    			<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
                  					<td class="formDe" colspan='3'><bean:write name="ScheduleCalendarNewEventForm" property="eventLocation"/></td>
                    			</tr>

                  				<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.minAttendance" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="minAttendance"/></td>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.maxAttendance" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="maxAttendance"/></td>
                  				</tr>
                  				<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.total" /><bean:message key="prompt.#Scheduled" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="scheduled"/></td>
                    				<td class="formDeLabel" nowrap><bean:message key="prompt.recurringEvent" /></td>
                            <td class="formDe"><jims2:displayBoolean name="ScheduleCalendarNewEventForm" property="isRecurringEvent" trueValue="Yes" falseValue="No"/></td>
                  				</tr>
                  				<tr>
                            <td class="formDeLabel" nowrap><bean:message key="prompt.instructorName" /></td>
                  					<td class="formDe" colspan='3'><bean:write name="ScheduleCalendarNewEventForm" property="instructorFullName"/></td>
                  				</tr>
                  				<tr>
                            <td class="formDeLabel" nowrap colspan='4'><bean:message key="prompt.comments" /></td>
                  				</tr>
                  				<tr>
                  					<td class="formDe" colspan='4'><bean:write name="ScheduleCalendarNewEventForm" property="comments"/></td>
                  				</tr>
              
                  				<tr><td class="selectedRow" colspan='4'>&nbsp;</td></tr>
              
                          <%-- BEGIN Conflicting Events section 
             								 this section shows events that the user has just
														 scheduled and conflict with existing events on
														 either/or the Juvenile's or JPO's calendar shown
														 in the previous two tables up top
              						--%>

													<%-- column headings --%>													
                          <tr class="alternateRow">
                            <td class="formDeLabel" width="1%" nowrap>
                              <logic:equal name="pageType" value="summary">
                                <logic:notEmpty name="ScheduleCalendarNewEventForm" property="currentService.currentEvent.conflictingEvents">
                                  <bean:message key="prompt.addConfictingEvents" />
                                </logic:notEmpty>
                              </logic:equal>
                            </td>					

                            <td class="formDeLabel" nowrap><bean:message key="prompt.eventDate" /></td>
                            <td class="formDeLabel" colspan='2'><bean:message key="prompt.eventTime" /></td>
                          </tr>
                            
													<%-- column data --%>													
                          <nested:iterate id="event" name="ScheduleCalendarNewEventForm" property="allEvents">
                          <tr>
                            <td>
                              <logic:equal name="pageType" value="summary">
                                <nested:equal property="conflictingEvent" value="true">
                                  <nested:checkbox property="addConflictingEvent" value="true"/>																
                                </nested:equal>			
                              </logic:equal>
                            </td>
                            
                            <td 
                              <logic:equal name="pageType" value="summary">
                                <nested:equal property="conflictingEvent" value="true"> class='errorAlert'</nested:equal>
                              </logic:equal>
                            > <%-- closing td begin --%>							
                              <nested:write property="startDatetime" formatKey="date.format.mmddyyyy"/>	
                            </td>
                            
                            <td colspan="2"><nested:write property="startDatetime" formatKey="time.format.HHmm"/></td>					
                          </tr>		
                          </nested:iterate>
                          </logic:equal>													
            
            						</table>
            					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            </table>
            <%-- END EVENT DETAIL TABLE --%>

            <%-- BEGIN BUTTON TABLE --%>
            <div class='spacer'></div>
            <table width="100%">
              <tr>
                <td align="center">
                  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                  <logic:notEmpty name="ScheduleCalendarNewEventForm" property="programReferral"> 
          					 <html:submit property="submitAction"><bean:message key="button.createNewReferral" /></html:submit>
          				  </logic:notEmpty>
          				  <logic:empty name="ScheduleCalendarNewEventForm" property="programReferral"> 
        					 <html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>  					 
          				  <logic:empty/>
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


</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
