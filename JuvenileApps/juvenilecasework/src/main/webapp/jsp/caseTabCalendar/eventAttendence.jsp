<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- feb 1 2007 - mjt - create jsp --%>
<%-- oct 28 2008 - cws - defect#55174 added max. length edit of 3000 to progress notes --%>
<%-- jul 17 2009 - cws   #61004 added timeout.js  --%>
<%-- 10/24/2012 DGibler		73746 MJCW: Add Street Number suffix field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants"%>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.UIConstants" %>



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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - eventAttendence.jsp</title>
<html:javascript formName="eventAttendanceForm"/>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" >
<html:form action="/submitDocumentAttendance" target="content">
<html:hidden name ="calendarEventListForm" property="action" styleId = "calEventListAction"/>
<logic:notEqual name="calendarEventListForm" property="action" value="attendanceSummary">
  <logic:notEqual name="calendarEventListForm" property="action" value="attendanceConfirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|143">
  </logic:notEqual>
</logic:notEqual>

<logic:equal name="calendarEventListForm" property="action" value="attendanceSummary">
  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|145">
</logic:equal>

<logic:equal name="calendarEventListForm" property="action" value="attendanceConfirm">
  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|144"> 
</logic:equal> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">

			<logic:notEqual name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
				<bean:message key="title.juvenileCasework" /> - Calendar Event - 
				<bean:message key="button.documentAttendance" /><br />

				 <logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate">Add</logic:equal>

				 <logic:equal name ="calendarEventListForm" property="action" value="attendancePresent">View</logic:equal>
			 		Attendance
				 <logic:equal name="calendarEventListForm" property="action" value="attendanceSummary"><bean:message key="prompt.summary" /></logic:equal>

				 <logic:equal name="calendarEventListForm" property="action" value="attendanceConfirm"><bean:message key="prompt.confirmation" /></logic:equal> 
				 
				 <logic:equal name ="calendarEventListForm" property="action" value="attendancePresent">Details</logic:equal>
			</logic:notEqual>
		</td>
  </tr>

	<logic:equal name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
		<tr>
			 <td align='center' class="header">
				<bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" />
	    	- <bean:message key="button.documentAttendance" /><br />

				 <logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate">Add</logic:equal>

				 <logic:equal name ="calendarEventListForm" property="action" value="viewAttendance">View</logic:equal>
			 		Attendance
				 <logic:equal name="calendarEventListForm" property="action" value="attendanceSummary"><bean:message key="prompt.summary" /></logic:equal>

				 <logic:equal name="calendarEventListForm" property="action" value="attendanceConfirm"><bean:message key="prompt.confirmation" /></logic:equal> 
				 
				 <logic:equal name ="calendarEventListForm" property="action" value="viewAttendance">Details</logic:equal>
			</td>
		</tr>
	</logic:equal>

  <logic:equal name="calendarEventListForm" property="action" value="attendanceConfirm">
    <tr> 
    	<td align='center' class='confirm'><bean:message key="prompt.attendance" />&nbsp;documented.</td>
    </tr>
	</logic:equal> 
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
	      <logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate">
	          <li>Select Attended, Absent, or Excused for Attendance Status, then select <b>Next</b> button to view summary.</li>
		      <li>Select the Spell Check icon for the Progress Notes field to check spelling.</li>
	    	  <li><b>Note:</b> New progress notes will be appended to the current progress notes.</li>
	    	  <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	      </logic:equal>
	
	      <logic:equal name ="calendarEventListForm" property="action" value="viewAttendance">
	          <li>Select the Back button to return to the previous screen.</li>
	          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	      </logic:equal>
	      <logic:equal name ="calendarEventListForm" property="action" value="attendancePresent">
	          <li>Select the Back button to return to the previous screen.</li>
	          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	      </logic:equal>
	
	      <logic:equal name ="calendarEventListForm" property="action" value="attendanceSummary">
	          <li>Review information, then select the <b>Finish</b> button to save information.</li>
	          <li>Select <b>Back</b> button to return to previous page.</li>
	          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	      </logic:equal>
	
	      <logic:equal name ="calendarEventListForm" property="action" value="attendanceConfirm">
	          <logic:notEqual name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
		          <li>Select the <b>Return to Calendar</b> button to return to the calendar.</li>
		          <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	          </logic:notEqual>
	          <logic:equal name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
	           <li>Select the <b>Document Attendance</b> button to view the Document Attendance screen.</li>
	           <li><b>Note:</b> Juveniles scheduled may exceed the Total Scheduled when some are marked excused or cancelled.</li>
	          </logic:equal>
	      </logic:equal>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN CASEFILE HEADER TABLE --%>
<logic:notEqual name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	  <tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
</logic:notEqual>
<%-- END CASEFILE HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0">
  <tr>
    <td>

			<logic:notEqual name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
	      <%--tabs start--%> 
	      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	        <tiles:put name="tabid" value="calendartab" />
	        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	      </tiles:insert> 
	      <%--tabs end--%>
	
	      <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	        <tr>
	          <td>
	            <%-- BEGIN DETAIL TABLE --%>
       </logic:notEqual>
						<div class='spacer'></div>
            <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
                <td class="detailHead"><bean:message key="prompt.event" /> <bean:message key="prompt.details" /></td>
            	</tr>
			
			
<%-- this read-only Event Details section is always displayed, whether we're in 
		 EDIT or SUMMARY / CONFIRMATION "mode".  there are three different 
		 types of events, so we have three different logic:equal sections--%> 			
	<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">	
	           	<tr>
	           		<td>
            		  <table width='100%' cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.program" /> <bean:message key="prompt.serviceProvider" /></td>
				            	<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceProviderName"/></td>
				            </tr>
				            <tr>
											<td class="formDeLabel"><bean:message key="prompt.eventDate" /></td>
				            	<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel"><bean:message key="prompt.eventTime" /></td>
					            <td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
	           				</tr>
	           				<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
	            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
	            			</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength" /></td>
	            				<td class="formDe">        					
												<bean:define id="eventSessionLength" name="calendarEventListForm" property="currentEvent.eventSessionLength" type="java.lang.String"/>
												<%=UIUtil.getTimeInHours(eventSessionLength)%>
											</td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
	            			</tr>
				
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.minAttendance" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.minAttendance"/></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.maxAttendance" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.maxAttendance"/></td>
	            			</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.totalScheduled" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.currentEnrollment"/></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.instructorName" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.instructorName"/></td>
	            			</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments" /></td>
	            			</tr>
	            			<tr>
	            				<td class="formDe" colspan='4'><bean:write name="calendarEventListForm" property="currentEvent.eventComments"/></td>
	            			</tr>
	           			</table>
	           		</td>
	           	</tr>
</logic:equal>
								
<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">	
            	<tr>
            		<td>
            			<table width='100%' cellpadding="2" cellspacing="1">
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
	            				<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
	            			</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
	            				<td class="formDe"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
	            			</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personsInterviewed" /></td>
											<td class='formDe' colspan='3'>
												<logic:notEmpty name="calendarEventListForm" property="currentEvent.interviewPersons">
													<logic:iterate id="personsIter" name="calendarEventListForm" property="currentEvent.interviewPersons">
														<bean:write name="personsIter" property="formattedName"/><br>
													</logic:iterate>
												</logic:notEmpty>												
											</td> 
										</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.interviewType" /></td>
											<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.interviewType"/></td>
	            			</tr>
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.interviewLocation" /></td>
	            				<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>
	            			</tr>
									</table>
            		</td>
            	</tr>
</logic:equal>

<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>">	
            	<tr>
            		<td>
            			<table width='100%' cellpadding="2" cellspacing="1">
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
	            				<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventType"/></td>
	            			</tr>
	            			<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventDate" /></td>
	            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
	            			</tr>
	            			</logic:notEqual>
	            			<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.dateOfContact" /></td>
	            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
	            			</tr>
	            			</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">		
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.location"/></td>
	            				</tr>
										</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">		
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/></td>
	            				</tr>
										</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">		
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/></td>
	            				</tr>
										</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">		
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.location" /> for <bean:message key="prompt.memberName" /></td>
	            					<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.familyLocation"/></td>
	            				</tr>
										</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
		            			<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>
		            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolDistrictName"/></td>
		            			</tr>
		            			<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.school" /></td>
		            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/></td>
		            			</tr>
										</logic:equal>
										<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
		            			<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.schoolDistrict" /></td>
		            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolDistrictName"/></td>
		            			</tr>
		            			<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.school" /></td>
		            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.schoolName"/></td>
		            			</tr>
		            			<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.personContacted" /></td>
		            				<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.contactFirstName"/> <bean:write name="calendarEventListForm" property="currentEvent.contactLastName"/></td>
		            			</tr>
										</logic:equal>
									<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.eventLocation" /></td>		
											<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
										</tr>
									</logic:equal>		
									<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.eventLocation" /></td>		
											<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.facilityCd"/></td>			
										</tr>
									</logic:equal>								
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">										
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">										
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">										
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
										<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.eventLocation" /></td>		
												<td class="formDe" colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.serviceLocationName"/></td>			
											</tr>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
										</logic:notEqual>
							<logic:notEqual name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">							
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventTime" /></td>
											<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
	            			</tr>
	            			</logic:notEqual>
	            			<logic:equal name="calendarEventListForm" property="currentEvent.eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">							
	            			<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.contactTime" /></td>
											<td class="formDe" colspan="3"><bean:write name="calendarEventListForm" property="currentEvent.eventDate" formatKey="time.format.hhmma"/></td>
	            			</tr>
	            			</logic:equal>
	            			
            			</table>
            		</td>
            	</tr>
</logic:equal>
<%-- end read-only Event Details section  --%> 			
				</table>	
          
					<%-- this is where attendance is recorded; i.e., we're in edit mode --%>
					<logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate">
					<html:hidden name="calendarEventListForm" property="tentativeRefPrgmRef" styleId="tentativeRefPrgm"/> <%-- added for #36737--%>
  					<div class='spacer'></div>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr> <%-- table titlebar --%>
            		<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.attendance" /></td>
            	</tr>
            	<tr>
            		<td valign='top' align='center'>
            			<table width='100%' cellpadding="2" cellspacing="1">
                  	<tr>
                  		<td class='formDeLabel' width='1%' nowrap='nowrap'>
												<img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border='0' width='10' height='9'>
												<bean:message key="prompt.attendance" />&nbsp;<bean:message key="prompt.status" />
											</td>
											<td class='formDe'>
            						<html:select property="attendanceStatusCd" styleId='attendanceDropdown'>
            							<html:option value=""><bean:message key="select.generic" /></html:option>
            							<html:optionsCollection property="attendanceStatusList" value="code" label="description" /> 
            						</html:select>
											</td>
											<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>
					            <td class="formDe"><html:text name="calendarEventListForm" property="addlAttendees" styleId="addlAttendees" size="2" maxlength="2" /></td>
      							</tr>

										<logic:equal name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
	      							<tr>
	      								<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.juvenileName" /></td>
	      								<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.juvenileFullName"  /></td>
	      							</tr>
	      						</logic:equal>

      							<tr> 
											<td class='formDeLabel' valign='top' width='1%' nowrap='nowrap'>											
												<bean:message key="prompt.additionalAttendeeNames"/>
											</td> 
											<td class='formDe' colspan="3">									
												<logic:empty name="calendarEventListForm" property="contactNames">Name list is empty.</logic:empty>
		
												<logic:notEmpty name="calendarEventListForm" property="contactNames">
													<html:select name="calendarEventListForm" property="selectedAttendeeNames" size="5" multiple="true">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<logic:iterate id="contactsIter" name="calendarEventListForm" property="contactNames">
															<option value="<bean:write name='contactsIter' property='formattedName'/>">
																<bean:write name="contactsIter" property="formattedName"/>															
															</option>
														</logic:iterate>
													</html:select>
												</logic:notEmpty>					
											</td> 
										</tr> 
								<tr>
										<td class='formDeLabel' colspan="4"><bean:message key="prompt.currentProgressNotes" /></td>
										</tr>
										<tr>
										<td class='formDe' colspan="4"><nested:write property="existingProgressNotes" /></td>
								</tr>
										<%-- notes (comments) field --%>  
        						<tr> 
        							<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.progress" />&nbsp;<bean:message key="prompt.notes" />&nbsp;

						            <logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate">
	              					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
	              						<tiles:put name="tTextField" value="progressNotes"/>
	              						<tiles:put name="tSpellCount" value="spellBtn1" />
	            						</tiles:insert>
	            						(Max. characters allowed: 3000)
	            					</logic:equal>  

           						</td>
        						</tr>
        						<tr>
        							<td class='formDe' colspan='4'><html:textarea name="calendarEventListForm" property="progressNotes" styleId="progressNotes" style="width:100%" rows="3"/></td>
        						</tr>

            			</table>
            		</td>
            	</tr>
            </table>
            </logic:equal>                  
            <%-- end edit attendance record --%>

            
            <%-- begin attendance summary - this section is shown in either SUMMARY or CONFIRMATION modes --%>
            <logic:notEqual name ="calendarEventListForm" property="action" value="attendanceUpdate">
            <div class='spacer'></div>
            <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr> <%-- table titlebar --%>
            		<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.attendance" /></td>
            	</tr>
            	<tr>
            		<td valign='top' align='center'>
            			<table width='100%' cellpadding="2" cellspacing="1">		
                  	<tr>
                  		<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.attendance" />&nbsp;<bean:message key="prompt.status" /></td>
                  		<td class='formDe' nowrap='nowrap'><bean:write name="calendarEventListForm" property="attendanceStatus" /></td>
                  		<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendees" /></td>                  	
                  		<td class='formDe' nowrap='nowrap'><bean:write name="calendarEventListForm" property="addlAttendees" /></td>
      							</tr>

										<logic:equal name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
	      							<tr>
	      								<td class='formDeLabel'><bean:message key="prompt.juvenileName" /></td>
	      								<td class='formDe' colspan='3'><bean:write name="calendarEventListForm" property="currentEvent.juvenileFullName"  /></td>
	      							</tr>
	      						</logic:equal>
	      						
		      					<tr>	<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.additionalAttendeeNames" /></td>
		      						<td class='formDe' colspan='4' nowrap='nowrap'>
			      						<logic:notEmpty name="calendarEventListForm" property="selectedNamesList">
													<logic:iterate id="attendeeIter" name="calendarEventListForm" property="selectedNamesList">
														<bean:write name="attendeeIter" property="formattedName"/><br>
													</logic:iterate>
												</logic:notEmpty>
											</td>
		      					</tr>
			
										<%-- notes (comments) field --%>
								<tr>
										<td class='formDeLabel' colspan="4"><bean:message key="prompt.currentProgressNotes" /></td>
								</tr>
								<tr>
										<td class='formDe' colspan="4"><nested:write property="existingProgressNotes" /></td>
								</tr>
        						<tr>
        							<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.progress" />&nbsp;<bean:message key="prompt.notes" /></td>
        						</tr>
	                   <tr>
                      <td class='formDe' colspan='4'>
                        <logic:empty  name="calendarEventListForm" property="progressNotes">
                          No Progress Notes.
                        </logic:empty>

                        <logic:notEmpty  name="calendarEventListForm" property="progressNotes">
		                      <bean:write name="calendarEventListForm" property="progressNotes" />
	                      </logic:notEmpty>
                      </td>
                    </tr>
                  </table>
            		</td>
            	</tr>
            </table>
            </logic:notEqual>                  
            <%-- end attendance summary --%>
			
			
			            <%-- BEGIN BUTTON TABLE --%>
			            <table width="100%">  
			              <logic:equal name ="calendarEventListForm" property="action" value="attendanceUpdate"> <%-- summary buttons --%>
			                <tr>
			                  <td align="center">
			                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
			                    <html:submit property="submitAction" styleId="validateAttendaceNext"><bean:message key="button.next"/></html:submit>
			                    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			  								</td>
			  							</tr>
			              </logic:equal>                  
			
			              <logic:equal name ="calendarEventListForm" property="action" value="attendanceConfirm"> <%-- confirmation buttons --%>
			              		  <logic:equal name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
			              		  	<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.documentAttendance"/></html:submit>
										</td>
									</tr>
			              		  </logic:equal>
			              		   <logic:notEqual name ="calendarEventListForm" property="secondaryAction" value="workshopCalendar">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.returnToCalendar"/></html:submit>
											</td>
										</tr>
									</logic:notEqual>
              </logic:equal>                  
                  
              <logic:equal name ="calendarEventListForm" property="action" value="attendanceSummary"> <%-- edit buttons --%>
                <tr>
                  <td align="center">
                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                    <html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
                    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                  </td>
                </tr>
              </logic:equal>                  
						  <logic:equal name ="calendarEventListForm" property="action" value="attendancePresent"> <%-- edit buttons --%>
                <tr>
                  <td align="center">
                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                  </td>
                </tr>
              </logic:equal>
               <logic:equal name ="calendarEventListForm" property="action" value="viewAttendance"> <%-- edit buttons --%>
                <tr>
                  <td align="center">
                      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>                   
                  </td>
                </tr>
              </logic:equal>
            </table>
            <%-- END BUTTON TABLE --%>

          </td>
        </tr>
      </table>
			<div class='spacer'></div>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>


<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>
</html:html>

