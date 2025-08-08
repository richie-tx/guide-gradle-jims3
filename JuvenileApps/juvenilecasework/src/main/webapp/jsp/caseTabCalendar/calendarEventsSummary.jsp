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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarEventsSummary.jsp</title>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/HandleScheduleEventAction" target="content">
<logic:equal name="calendarEventDetailsForm" property="action" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|">
</logic:equal>
<logic:equal name="calendarEventDetailsForm" property="action" value="confirmation">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|">         
</logic:equal>

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.heading" />&nbsp;<bean:message key="title.juvenileCasework" />&nbsp;<bean:message key="title.calendarEvent" />
      <logic:equal name="calendarEventDetailsForm" property="sourcePage" value="referral"> /Referral</logic:equal>
      <logic:equal name="calendarEventDetailsForm" property="action" value="summary"> Summary</logic:equal>
      <logic:equal name="calendarEventDetailsForm" property="action" value="confirmation"> Confirmation</logic:equal>
    </td>
  </tr>
  <logic:equal name="ScheduleCalendarNewEventForm" property="action" value="confirmation">
     <tr><td>&nbsp;</td></tr>
     <tr>
         <td align="center" class="confirm">The following event(s) has been scheduled.</td>
     </tr>      
     <logic:equal name="ScheduleCalendarNewEventForm" property="programReferralNew" value="true">
        <tr>
           <td align="center" class="confirm">The following Program Referral has been created.</td>
        </tr>   
     </logic:equal>
  </logic:equal>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END Heading TABLE --%>


<%-- BEGIN Instruction TABLE --%>
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
				<li>
        
		<logic:equal name="ScheduleCalendarNewEventForm" property="action" value="summary">
          Review information, then select Finish button to save the events.
          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
        </logic:equal> 

        <logic:equal name="ScheduleCalendarNewEventForm" property="action" value="confirmation">
           Select Return to Calendar button to return to Calendar.
           <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
        </logic:equal>  
				
				</li> 
      </ul>
    </td>
  </tr>
</table>
<%-- END Instruction TABLE --%>

<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END Casefile Header TABLE --%>


<%-- BEGIN Detail TABLE --%>
<div class="spacer"></div>
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
            <%-- BEGIN Event Detail TABLE --%>
            <div class="spacer"></div>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr>
            		<td class="detailHead"><bean:message key="prompt.scheduledEvents" /></td>
            	</tr>
            	<tr>
            		<td valign="top" align="center">
            			<table width='100%' cellpadding="4" cellspacing="1">
            				
            				<tr>
            					<td colspan='4'>
            						<table align="center" width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
            
                    			<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.program" /> <bean:message key="prompt.serviceProvider" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="serviceProvider"/></td>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.serviceName" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="serviceName"/></td>
                    			</tr>
              						
                    			<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventType" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="eventType"/></td>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.sessionLength" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="eventHours"/> hour(s)</td>
                    			</tr>
                    			<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
                  					<td class="formDe" colspan='3'><bean:write name="ScheduleCalendarNewEventForm" property="eventLocation"/></td>
                    			</tr>

                  				<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.minAttendance" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="minAttendance"/></td>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.maxAttendance" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="maxAttendance"/></td>
                  				</tr>
                  				<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.total" /><bean:message key="prompt.#Scheduled" /></td>
                  					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="scheduled"/></td>
                    				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.recurringEvent" /></td>
                            <td class="formDe"><jims2:displayBoolean name="ScheduleCalendarNewEventForm" property="isRecurringEvent" trueValue="Yes" falseValue="No"/></td>
                  				</tr>
                  				<tr>
                            <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.instructorName" /></td>
                  					<td class="formDe" colspan="3"><bean:write name="ScheduleCalendarNewEventForm" property="instructorFullName"/></td>
                  				</tr>

                  				<tr>
                            <td class="formDeLabel" nowrap="nowrap" colspan='4'><bean:message key="prompt.comments" /></td>
                  				</tr>
                  				<tr>
                  					<td class="formDe" colspan='4'><bean:write name="ScheduleCalendarNewEventForm" property="comments"/></td>
                  				</tr>

                  				<tr><td class="selectedRow" colspan="4">&nbsp;</td></tr>
              
													<%--  ******************* begin repeating data  ******************* --%>

                    			<logic:empty name="eventTimes">
                    				<tr bgcolor="#cccccc">
                    					<td colspan="4" class="subHead">No Events Available</td>
                    				</tr>
                    			</logic:empty>

                    			<%int RecordCounter = 0; String bgcolor = "";%>
                  
                    			<logic:notEmpty name="eventTimes">
														<%-- column headings --%>
                    				<tr class="alternateRow">
               								<td class="formDeLabel" width="1%" nowrap="nowrap">&nbsp;</td>					
                              <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventDate" /></td>
                              <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.eventTime" /></td>
                      			</tr>

														<%-- the data --%>
														
                    				<logic:iterate id="ruleIndex" name="rules">
                       				<%-- Begin Pagination item wrap --%>
                        		  <pg:item>
                       					<tr class='<%RecordCounter++;  
													 bgcolor = (RecordCounter % 2) == 1) ? "normalRow" : "alternateRow";
                       						out.print(bgcolor);%>'
																>
                      						<td width="1%" nowrap="nowrap">&nbsp;</td>
                      						<td><bean:write name="eventIndex" property="eventDate" formatKey="date.format.mmddyyyy" /></td>
                      						<td><bean:write name="eventIndex" property="eventTime" formatKey="date.format.HHmm" /></td>
																</tr>
                      			  </pg:item>
                              <%-- End Pagination item wrap --%>
                    				</logic:iterate>
                    			</logic:notEmpty>
              							
                          <%--  ******************* end repeating info ******************* --%>
            
            						</table>
            					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            </table>
            <%-- END Event Detail TABLE --%>


            <%-- BEGIN Program Referral Details TABLE --%>
   					<div class="spacer"></div>
 						<table width='98%' border="0" cellpadding="2" cellspacing="0" align='center' class="borderTableBlue">

 							<tr>
                <td width='1%' class="detailHead"><a href="javascript:showHideMulti('referral', 'pchar', 1);" border="0"><img border="0" src="../../common/images/expand.gif" name="referral"></a></td>
 								<td class="detailHead"><bean:message key="prompt.programReferral" /> <bean:message key="prompt.details" /></td>
 							</tr>

 							<tr id='pchar0' class='hidden'>
  							<td valign="top" align="center" colspan="2">
  								<table cellpadding="2" cellspacing="1" width='100%'>
                    <tr>
                      <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.beginDate" /></td>
            					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralBeginDate"/></td>
                      <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.endDate" /></td>
            					<td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralEndDate"/></td>
										</tr>

										<tr>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.courtOrdered" /></td>
                      <td class="formDe"><jims2:displayBoolean name="ScheduleCalendarNewEventForm" property="isReferralCourtOrdered" trueValue="Yes" falseValue="No"/></td>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.currentStatus" /></td>
                      <td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralCurrentStatus"/></td>
										</tr>

										<tr>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.assignedHours" /></td>
                      <td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralAssignedHours"/></td>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.dateSent" /></td>
                      <td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralDateSent"/></td>
										</tr>

										<tr>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.acknowledgeDate" /></td>
                      <td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralAcknowledgeDate"/></td>
              				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.program" /> <bean:message key="prompt.outcome" /></td>
                      <td class="formDe"><bean:write name="ScheduleCalendarNewEventForm" property="referralProgramOutcome"/></td>
										</tr>
										<tr>
                      <td class="formDeLabel" nowrap="nowrap" colspan='4'><bean:message key="prompt.comments" /></td>
                    </tr>
                    <tr>
											<td class="formDe" colspan="4">
												<div  class='scrollingDiv100'>
													<table>
														<tr>
															<td><bean:write name="ScheduleCalendarNewEventForm" property="referralComments"/></td>
														</tr>
													</table>
												</div>											
											</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <%-- END Program Referral Details TABLE --%>

						
            <%-- BEGIN Button TABLE --%>
            <div class="spacer"></div>
						<table width="100%">
              <tr>
                <td align="center">
            
                  <logic:equal name="ScheduleCalendarNewEventForm" property="action" value="summary">
                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
    								<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
                    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                  </logic:equal>
            
                  <logic:equal name="ScheduleCalendarNewEventForm" property="action" value="confirmation">
    								<html:submit property="submitAction"><bean:message key="button.returnToCalendar" /></html:submit>
                  </logic:equal>
                </td>
              </tr>
            </table>
            <%-- END Button TABLE --%>
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

