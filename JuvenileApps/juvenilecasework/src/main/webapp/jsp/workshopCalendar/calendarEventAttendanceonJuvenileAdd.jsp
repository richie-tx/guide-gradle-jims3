<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson		Create JSP --%>
<%-- 03/10/2011	Clarence Shimek			defect 69467 revised View to Update href under Progress Notes --%>
<%-- 03/24/2011 Clarence Shimek         reworked Add Attendee href after QA UI review --%>
<%-- 03/31/2011 Clarence Shimek         #69792 added coding to disable attendance fields for outsource users after 7 days --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCalendarConstants"%>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">



<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<!--JQUERY FRAMEWORK--> <%-- added for #36737--%>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/calendarEvent.js"></script> <%-- added for #36737--%>

<title><bean:message key="title.heading" /> - calendarEventAttendanceonJuvenileAdd.jsp</title>

<script type='text/javascript'>
radioChecked = false;
	
function closeProgressNotesTable()
{	 
  showHide( "progressTable", 0 ) ;
}

function closeMonthlySummaryTable()
{	 
  showHide( "monthlySummaryTable", 0 ) ;
}

function validateFields(theForm)
{
  if (radioChecked == false)
  {	 
  	alert("Please mark attendance for at least one juvenile");  	
  	return false;
  }	  

  return validateAttendee(theForm);
}

function validateAttendee(theForm)
{
	<bean:size id="numEvents" name="calendarEventDetailsForm" property="newAttendanceEvents"/>
  	var attendee;
  	var eventSize = <%=numEvents.intValue()%>;
  	for (i = 0; i < eventSize; i++)
	{ 
		attendee = theForm["newAttendanceEvents[" + i + "].addlAttendees"];		
		clearAllValArrays();		
    	if(attendee.value != "")
    	{
			addNumericValidation("newAttendanceEvents[" + i + "].addlAttendees","Additional Attendees must be numeric");			
			if( !validateCustomStrutsBasedJS(theForm) )
			{
				return false;
			}
			var attendeeVal = parseInt(attendee.value);
		
			if(attendeeVal > 19)
			{
				alert("Additional Attendees cannot be more than 19");
				attendee.focus();
				return false;
			}
   		}
	}

  return true;
}

function setClicked(element, juvenileId)
{			
	radioChecked = true;  
	var theForm = document.forms[0];
	<bean:size id="numEvents" name="calendarEventDetailsForm" property="newAttendanceEvents"/>	
	var eventSize = <%=numEvents.intValue()%>;
	for (i = 0; i < eventSize; i++)
	{	
		if(theForm["newAttendanceEvents[" + i + "].juvenileId"].value == juvenileId)
		{
		  if(element.value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>" || element.value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>")
		  { 				
			theForm["newAttendanceEvents[" + i + "].addlAttendees"].value = "" ;
			theForm["newAttendanceEvents[" + i + "].addlAttendees"].disabled = "disabled" ;					
		  } 
		  else
		  {
			  theForm["newAttendanceEvents[" + i + "].addlAttendees"].disabled = "" ;
		  }
		  break;
		}
	}
}

function showProgressNotes(buttonName,juvenileId,hrefName)
{
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId + "&flowInd=" + hrefName;
//  alert(document.forms[0].action); 			
	document.forms[0].submit();
	return false;
}

function showMonthlySummary(buttonName,juvenileId)
{
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId;			
	document.forms[0].submit();
	return false;
}

function showAttendance(buttonName, juvenileId, statusType)
{ 
	var theForm = document.forms[0];
	if(validateAttendee(theForm))
	{
		var attendanceCd = "";
		if (statusType == "NEW")
		{	
			<bean:size id="numEvents" name="calendarEventDetailsForm" property="newAttendanceEvents"/>	
			var eventSize = <%=numEvents.intValue()%>;

			for (i = 0; i < eventSize; i++)
			{		
				if(document.forms[0]["newAttendanceEvents[" + i + "].juvenileId"].value == juvenileId)
				{
//					addlAttendees = document.forms[0]["newAttendanceEvents[" + i + "].addlAttendees"].value;
					rad = document.forms[0]["newAttendanceEvents[" + i + "].attendanceStatusCd"];
	
					for ( j = 0; j < rad.length; j++)
			  		{
						if (rad[j].value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>")
						{	
				  			if (rad[j].checked == true) 
				  			{	
			  				attendanceCd = rad[j].value;
				  			}
				  			j = rad.length; 
						}	
			  		}
			  		break;		
				}
			}
		} else {
			<bean:size id="numEvents" name="calendarEventDetailsForm" property="existingAttendanceEvents"/>	
			var eventSize = <%=numEvents.intValue()%>;

			for (i = 0; i < eventSize; i++)
			{		
				if(document.forms[0]["existingAttendanceEvents[" + i + "].juvenileId"].value == juvenileId)
				{
					rad = document.forms[0]["existingAttendanceEvents[" + i + "].attendanceStatusCd"];
	
					for ( j = 0; j < rad.length; j++)
			  		{
						if (rad[j].value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>")
						{
				  			if (rad[j].checked == true)
				  			{	
				  				attendanceCd = rad[j].value;
			  				}
			  				j = rad.length;
						}	
			  		}
			  		break;			
				}
			}
		}					
	}
	else
	{
		return false;
	}
	if (attendanceCd == "")
	{
		alert("Attendance status of 'Attended' is required to Add Attendees.");
		return false;
  	}	
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId;
// 	document.forms[0].action = "/<msp:webapp/>handleCalendarEventDetails.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId +"&secondaryAction=workshopCalendar" + "&addlAttendees="+addlAttendees+"&attendanceCd="+attendanceCd; 	
  	document.forms[0].submit();

	return false;
}

function viewAttendance(buttonName, juvenileId, listInd)
{
// 	document.forms[0].action = "/<msp:webapp/>handleCalendarEventDetails.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId;
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" + buttonName + "&juvenileId=" + juvenileId + '&listInd=' + listInd; 			
  	document.forms[0].submit();

	return false;
}

function showTestResults(juvenileId)
{
	document.forms[0].action = "/<msp:webapp/>displayWorkshopAttendance.do?submitAction=" + "Link" +	"&juvenileId=" + juvenileId;				
	document.forms[0].submit();

	return false;
}

function checkRadioStatus(theForm)
{
	<logic:notEmpty name="calendarEventDetailsForm" property="newAttendanceEvents">
		<bean:size id="numEvents" name="calendarEventDetailsForm" property="newAttendanceEvents"/>
		var eventSize = <%=numEvents.intValue()%>; 
		for (i = 0; i < eventSize; i++)
		{
			element = "newAttendanceEvents[" + i + "].attendanceStatusCd";	
			rad = theForm[element];	
 		
			for ( j = 0; j < rad.length; j++)
			{			
				if (rad[j].checked == true)
				{					
					radioChecked = true;  
					if(rad[j].value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>" || rad[j].value == "<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>")
					{ 				
						theForm["newAttendanceEvents[" + i + "].addlAttendees"].value = "" ;
						theForm["newAttendanceEvents[" + i + "].addlAttendees"].disabled = "disabled" ;					
					} 
					else
					{
						theForm["newAttendanceEvents[" + i + "].addlAttendees"].disabled = "" ;
					}
					break;
				}
			}
		}
//		alert(theForm.outSourceVendor.value);
//		if (theForm.outSourceVendor.value == "true")
//		{
//			var curDate = new Date();
//			var evDate = new Date(theForm.caldEventDate.value);
//			evDate.setDate(evDate.getDate() + 7);
//			if(evDate < curDate) 
//			{
 // 				alert("event date to old");
 // 				theForm.moreThan7Days.value = "Y";
//			}	
//		}		
		return true;
	</logic:notEmpty>
}

function notesTextCounter( field, maxlimit )
{

  var existingNotes = document.getElementsByName("currentAttendanceEvent.existingProgressNotes")[0].value;
  existingNotesLength = existingNotes.length;
  totalLength = existingNotesLength + field.value.length;
  if (field.value.length > (maxlimit + 1) - existingNotesLength)
	{
	alert("Your input has been truncated to "  +maxlimit +" characters!");
	}
  if( totalLength > maxlimit )
  {
    field.value = field.value.substring( 0, (maxlimit - existingNotesLength) );
    return false;
  }
}

function validateProgressNotes(theForm)
{
	clearAllValArrays();
	customValMaxLength('currentAttendanceEvent.progressNotes','Progess Notes cannot be longer than 3000 characters',3000);
	addDB2FreeTextValidation('currentAttendanceEvent.progressNotes',"<bean:message key='errors.comments' arg0='Progress Notes'/>",'');
	customValRequired('currentAttendanceEvent.progressNotes', 'No Progess Notes entry made to save.');
	return validateCustomStrutsBasedJS(theForm);
}

function validateMonthlySummary(theForm)
{
	clearAllValArrays();
	customValMaxLength('currentAttendanceEvent.monthlySummary','Monthly Summary cannot be longer than 12000 characters',11850);
	addDB2FreeTextValidation('currentAttendanceEvent.monthlySummary',"<bean:message key='errors.comments' arg0='Monthly Summary'/>",'');
	customValRequired('currentAttendanceEvent.monthlySummary', 'No Monthly Summary entry made to save.');
	return validateCustomStrutsBasedJS(theForm);
}
function validateAttendeeSelect(el){
	// start loop at 1 to bypass Please Select option	
	for (x=1; x<el.options.length; x++){
		if (el.options[x].selected){
			return true;
		}
	}	
	alert("At least 1 Addition Attendee Name selection is required.");				 
	return false;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0" onload="checkRadioStatus(document.forms[0]);">
<html:form method="post" action="/displayWorkshopAttendance" target="content">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|281">

<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.workshopCalendar" /></td>
		</tr>
		<tr>
			<td align="center" class="header"><bean:message key="title.calendarEventAttendeeList" /></td>
		</tr>
		
		<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="saveProgressNotes" >
		  <tr>
    		<td align='center' class='confirm'>Progress Notes Updated Successfully.</td>
		  </tr> 
 		</logic:equal>
 		
 		<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="saveMonthlySummary" >
		  <tr>
    		<td align='center' class='confirm'>Monthly Summary Updated Successfully.</td>
		  </tr> 
 		</logic:equal>
 		
 		<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="saveAttendeeList" >
		  <tr>
    		<td align='center' class='confirm'>Additional Attendees Updated Successfully.</td>
		  </tr> 
 		</logic:equal>

	</table>
	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
	<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>	
	<table width="98%">
		<tr>
			<td>
			<ul>
				<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="true">
					<li>Select Attended, Absent, or Excused event radio button for each juvenile, then select <b>Next</b> to view summary.</li>
					<li>Select hyperlinked <b>Add</b> to add Progress Notes for that juvenile.</li>
					<li>Select hyperlinked <b>Add</b> to add Additional Attendees.</li>
					<li>Select hyperlinked <b>View</b> to view that Juvenile's Progress Notes or Attendees.</li>
				</logic:equal>
				<li>Select <b>Back</b> button to return to previous page.</li>
				<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="true">
					<li>Grayed-out radio buttons signify attendance previously recorded.</li>
					<li>Note: New progress notes will be appended to the current progress notes.</li>
				</logic:equal>
				<jims2:if name="calendarEventDetailsForm" property="secondaryAction" value="addProgressNotes" op="equal">
					<jims2:then>
						<li>Select the Spell Check icon for the progress Notes field to check spelling.</li>
						<li>Select the <b>Save Progress Notes</b> button to retain progress notes.</li>
						<li>Select <b>Cancel Progress Notes</b> button to clear progress notes.</li>
					</jims2:then>
				</jims2:if>
				<jims2:if name="calendarEventDetailsForm" property="secondaryAction" value="addMonthlySummary" op="equal">
					<jims2:then>
						<li>Select the Spell Check icon for the monthly summary field to check spelling.</li>
						<li>Select the <b>Save Monthly Summary</b> button to retain the monthly summary.</li>
						<li>Select <b>Cancel Monthly Summary</b> button to clear the monthly summary.</li>
					</jims2:then>
				</jims2:if>
			</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
	<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>	
	<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead"><bean:message key="prompt.event" /> <bean:message key="prompt.details" />&nbsp (<bean:write name="calendarEventDetailsForm" property="serviceEventId" />)</td>
		</tr>
		<tr>
			<td>
			<table width='100%' cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.program" /> <bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>
					<td class="formDe" colspan="3"><bean:write name="calendarEventDetailsForm" property="serviceProviderName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventDate" /></td>
					<td class="formDe">
						<bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="date.format.mmddyyyy" />
					</td>
					<td class="formDeLabel" width="1%" nowrap='nowrap' valign='top'><bean:message key="prompt.event" /> <bean:message key="prompt.location" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="serviceLocationName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.event" /> <bean:message key="prompt.time" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventDate" formatKey="time.format.hhmma" /></td>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.session" /> <bean:message key="prompt.length" /></td>
					<td class="formDe"><bean:define id="eventSessionLength" name="calendarEventDetailsForm" property="eventSessionLength" type="java.lang.String" /> <%=UIUtil.getTimeInHours(eventSessionLength)%></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventType" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventType" /></td>
					<td class="formDeLabel" nowrap='nowrap' valign='top'><bean:message key="prompt.eventStatus" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventStatus" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.minimumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMinimum" /></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.maximumAttendance" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="eventMaximum" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.totalScheduled" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="totalScheduled" /></td>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.instructorName" /></td>
					<td class="formDe"><bean:write name="calendarEventDetailsForm" property="instructorName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.comments" /></td>
					<td class="formDe" colspan='3'><bean:write name="calendarEventDetailsForm" property="eventComments" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
<%-- END DETAIL TABLE --%>
	<div class='spacer'></div>
<%-- BEGIN ATTENDANCE TABLE --%>	
	<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead" colspan="6"><bean:message key="title.juvenileAttendanceList" /></td>
		</tr>

		<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="false">
			<tr>
				<td colspan="6"><bean:message key="title.noJuvenileForEvent" /></td>
			</tr>
		</logic:equal>

		<logic:equal name="calendarEventDetailsForm" property="attendanceEventsPresent" value="true">
			<tr>
				<td>
				<table width='100%' cellpadding="2" cellspacing="1">
					<tr class='subHead'>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.attended" /></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.absent" /></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.excused" /></td>
						<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.juvenileName" /></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.juvenile#" /></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.additionalAttendees" /></td>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.progressNotes" /></td>
						<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
							<td class="formDeLabel" width="1%"><bean:message key="prompt.monthlySummary" /></td>
						</logic:equal>
						<td class="formDeLabel" width="1%"><bean:message key="button.addAttendees" />' <bean:message key="prompt.name" /></td>
					</tr>

					<logic:empty name='calendarEventDetailsForm' property='existingAttendanceEvents'>
						<logic:empty name='calendarEventDetailsForm' property='newAttendanceEvents'>
							<tr>
								<td colspan='6'>No Juvenile Attendance records</td>
							</tr>
						</logic:empty>
					</logic:empty>

					<logic:notEmpty name='calendarEventDetailsForm' property='existingAttendanceEvents'>
						<nested:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="existingAttendanceEvents">
							<tr class="<%out.print((recordCounter.intValue() % 2 == 1) ? "alternateRow" : "normalRow");%>">
								<td align="center"><nested:radio property="attendanceStatusCd" disabled="true" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>" /></td>
								<td align="center"><nested:radio property="attendanceStatusCd" disabled="true" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>" /></td>
								<td align="center"><nested:radio property="attendanceStatusCd" disabled="true" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>" /></td>
								<td width="1%" nowrap='nowrap'><nested:write name="personsIter" property="juvenileName" /></td>
								<nested:hidden property="juvenileId"></nested:hidden>
								<td width="1%"><nested:write name="personsIter" property="juvenileId" /></td>
								<td width="1%"><nested:text name="personsIter" property="addlAttendees" size="2" maxlength="2" disabled="true" /></td>
								<td width="1%" nowrap='nowrap'><a href="#" onclick="return showProgressNotes('<bean:message key='button.viewProgressNotes'/>','<nested:write property='juvenileId'/>','<bean:message key="prompt.update" />')"> Review</a></td>
								<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
									<td width="1%" nowrap='nowrap'><a href="#" onclick="return showMonthlySummary('<bean:message key='button.viewMonthlySummary'/>','<nested:write property='juvenileId'/>')"> <bean:message key="prompt.view" /></a></td>
								</logic:equal>	
								<td width="1%" nowrap='nowrap'>
									<logic:equal name="calendarEventDetailsForm" property="outSourceVendorOver7Days" value="false">
										<nested:empty property="addlAttendeeNames">
											<a href="#" onclick="return showAttendance('<bean:message key='button.addAttendees'/>','<nested:write property='juvenileId'/>', 'EXISTING')"> <bean:message key="prompt.add" /></a>
										</nested:empty>
										<nested:notEmpty property="addlAttendeeNames">
											<a href="#" onclick="return viewAttendance('<bean:message key='button.viewAttendees'/>','<nested:write property='juvenileId'/>', 'EXISTING')"> <bean:message key="prompt.view" /></a>
										</nested:notEmpty>
									</logic:equal>
									<logic:equal name="calendarEventDetailsForm" property="outSourceVendorOver7Days" value="true">
										<a href="#" onclick="return viewAttendance('<bean:message key='button.viewAttendees'/>','<nested:write property='juvenileId'/>', 'EXISTING')"> <bean:message key="prompt.view" /></a>
									</logic:equal>
								</td>
							</tr>
						</nested:iterate>
					</logic:notEmpty>

					<logic:notEmpty name='calendarEventDetailsForm' property='newAttendanceEvents'>
						<bean:define id="evDate" name="calendarEventDetailsForm" property="eventDate" />
						<%  boolean disabledExcused = false; 	
							boolean isDisabled = false;
								
							Date dat = new Date();
							Date ev = (Date) evDate;
							if(ev.compareTo(dat) >= 0) 
							{
								isDisabled = true;
							}
							
						%>
						<logic:equal name="calendarEventDetailsForm" property="outSourceVendorOver7Days" value="true">
							<% isDisabled = true;
							disabledExcused = true;
							%>
						</logic:equal>
						<nested:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="newAttendanceEvents">
							<html:hidden name="calendarEventDetailsForm" property="tentativeRefPrgmRef" styleId="tentativeRefFlag"/> <%-- added for #36737--%>
							<html:hidden name="personsIter" property="serviceEventAttendanceId" styleId="attendance_id"/> <%-- added for #36737--%> 
							<tr class="<%out.print((recordCounter.intValue() % 2 == 1) ? "alternateRow" : "normalRow");%>"  id="<bean:write  name="personsIter" property="serviceEventAttendanceId"/>"> <%-- added for #36737--%>
								<bean:define name="personsIter" property="juvenileId" id="juvId"/>
								<bean:define id="jsfunct">setClicked(this, '<nested:write name='juvId'/>' );</bean:define>
								<td align="center"><nested:radio property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>" disabled="<%=isDisabled%>" onclick="<%=jsfunct%>" styleId="attended"/></td> <%-- added for #36737--%>
								<td align="center"><nested:radio property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>" disabled="<%=isDisabled%>" onclick="<%=jsfunct%>" styleId="absent"/></td>  <%-- added for #36737--%>
								<td align="center"><nested:radio property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>"  disabled="<%=disabledExcused%>" onclick="<%=jsfunct%>" styleId="excused"/></td> <%-- added for #36737--%>
								<td width="1%" nowrap='nowrap'><nested:write property="juvenileName" /></td>
								<nested:hidden property="juvenileId"></nested:hidden>
								<td width="1%"><nested:write property="juvenileId" /></td>
								<td width="1%">
							<%-- 	<nested:text disabled="<%=isDisabled%>" property="addlAttendees" size="2" maxlength="2" value="" /> --%>
									<nested:text disabled="true" property="addlAttendees" size="2" maxlength="2" styleId="addlAttendees"/> <%-- <bean:write name="personsIter" property="addlAttendees" />" --%>
								</td>							
	                     		<td width="1%" nowrap='nowrap' id="progressNotes"><a href="#" onclick="return showProgressNotes('<bean:message key='button.addProgressNotes'/>','<nested:write property='juvenileId'/>','<bean:message key="prompt.add" />')"> <bean:message key="prompt.add" /></a></td>																						
								<logic:equal name="calendarEventDetailsForm" property="eventTypeCode" value="<%=UIConstants.REPORTING%>">
									<nested:empty  property="monthlySummary">
										<td width="1%" nowrap='nowrap'><a href="#" onclick="return showMonthlySummary('<bean:message key='button.addMonthlySummary'/>','<nested:write property='juvenileId'/>')"> <bean:message key="prompt.add" /></a></td>
									</nested:empty>
									<nested:notEmpty  property="monthlySummary">
										<td width="1%" nowrap='nowrap'><a href="#" onclick="return showMonthlySummary('<bean:message key='button.viewMonthlySummary'/>','<nested:write property='juvenileId'/>')"> <bean:message key="prompt.view" /></a></td>
									</nested:notEmpty>
								</logic:equal>	
								<td width="1%" nowrap='nowrap' id="addAttendees">
									<logic:equal name="calendarEventDetailsForm" property="outSourceVendorOver7Days" value="false">
								 		<nested:empty property="addlAttendeeNames">
											<a href="#" onclick="return showAttendance('<bean:message key='button.addAttendees'/>','<nested:write property='juvenileId'/>', 'NEW')"> <bean:message key="prompt.add" /></a>
										</nested:empty>
										<nested:notEmpty property="addlAttendeeNames">
											<a href="#" onclick="return viewAttendance('<bean:message key='button.viewAttendees'/>','<nested:write property='juvenileId'/>', 'NEW')"> <bean:message key="prompt.view" /></a>
										</nested:notEmpty>
									</logic:equal> 
									<logic:equal name="calendarEventDetailsForm" property="outSourceVendorOver7Days" value="true">
										<a href="#" onclick="return viewAttendance('<bean:message key='button.viewAttendees'/>','<nested:write property='juvenileId'/>', 'NEW')"> <bean:message key="prompt.view" /></a>
									</logic:equal>
								</td>
							
							</tr>
							
						</nested:iterate>
					</logic:notEmpty>
					<%-- added for #36737--%>
					<nested:iterate indexId="recordCounter" id="attendanceIter" name="calendarEventDetailsForm" property="tentativeReferredPrgmReferrals">
						<html:hidden styleId="referralLstAttendanceId" name="attendanceIter" property="serviceEventAttendanceId"/>
					</nested:iterate>
					<%-- added for #36737--%>
				</table>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- END ATTENDANCE TABLE --%>

<%-- begin progress notes table --%>
	<jims2:if name="calendarEventDetailsForm" property="secondaryAction" value="viewProgressNotes" op="equal">
		<jims2:or name="calendarEventDetailsForm" property="secondaryAction" value="addProgressNotes" op="equal" />
		<jims2:then>

			<div class='visibleTR' id='progressTable'>
			<br>
			<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class='detailHead'><bean:message key="title.juvenileAttendanceList" />&nbsp;-&nbsp;<bean:message key="prompt.progressNotes" /></td>
				</tr>
				<tr>
					<td valign='top' align='center'>
 						<nested:nest property="currentAttendanceEvent"> 
							<table width='100%' cellpadding="2" cellspacing="1">
								<tr>
									<td class='formDeLabel' width="8%" nowrap="nowrap"><bean:message key="prompt.juvenile#" /></td>
									<td class='formDe'><nested:write property="juvenileId" /></td>
									<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.juvenileName" /></td>
									<td class='formDe'><nested:write property="juvenileName" /></td>
								</tr>
								<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addProgressNotes">
									<tr>
										<td class='formDeLabel' colspan="4"><bean:message key="prompt.currentProgressNotes" /></td>
									</tr>
									<tr>	
										<td class='formDe' colspan="4"><nested:write property="existingProgressNotes" /></td>
									</tr>
									<nested:hidden property="existingProgressNotes" />
									<tr>
										<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.progressNotes" /> &nbsp; 
											<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addProgressNotes">
												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
													<tiles:put name="tTextField" value="currentAttendanceEvent.progressNotes" />
													<tiles:put name="tSpellCount" value="spellBtn1" />
												</tiles:insert>
												(Max. characters allowed including Current Progress Notes: 3000)
											</nested:equal>
										</td>
									</tr>
									<tr>
										<td class='formDe' colspan='4'><nested:textarea style="width:100%" rows="3" property="progressNotes" onkeyup="notesTextCounter(this,3000);" /></td>
									</tr>
									<tr>
										<td colspan='4' align='center'><br>
											<html:submit property="submitAction" onclick="return validateProgressNotes(this.form)"><bean:message key="button.saveProgressNotes" /></html:submit> 
											<html:submit property="submitAction"><bean:message key="button.cancelProgressNotes" /></html:submit>
										</td>
									</tr>
								</nested:equal>

								<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewProgressNotes">
									<tr>
										<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.progressNotes" /> &nbsp; 
											<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addProgressNotes">
												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
													<tiles:put name="tTextField" value="currentAttendanceEvent.progressNotes" />
													<tiles:put name="tSpellCount" value="spellBtn1" />
												</tiles:insert>
												(Max. characters allowed: 3000)
											</nested:equal>
										</td>
									</tr>
									<tr>
										<td class='formDe' colspan='4'>
											<nested:empty  property="progressNotes">
	                     					 	No Progress Notes.
											</nested:empty>
	
											<nested:notEmpty  property="progressNotes">
												<nested:write property="progressNotes" />
											</nested:notEmpty>
										</td>
									</tr>
									<tr>
										<td colspan='4' align='center'><br>
										<html:submit property="submitAction" onclick="closeProgressNotesTable();">
											<bean:message key="button.closeProgressNotes" />
										</html:submit></td>
									</tr>
								</nested:equal>
							</table>
						</nested:nest>
					</td>
				</tr>
			</table>
			</div>

		</jims2:then>
		</jims2:if>
		
		<jims2:if name="calendarEventDetailsForm" property="secondaryAction" value="viewMonthlySummary" op="equal">
		<jims2:or name="calendarEventDetailsForm" property="secondaryAction" value="addMonthlySummary" op="equal" />
		<jims2:then>
			<div class='visibleTR' id='monthlySummaryTable'><br>
			<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class='detailHead'><bean:message key="title.juvenileAttendanceList" />&nbsp;-&nbsp;<bean:message key="prompt.monthlySummary" /></td>
				</tr>
				<tr>
					<td valign='top' align='center'>
						<nested:nest property="currentAttendanceEvent">
							<table width='100%' cellpadding="2" cellspacing="1">
								<tr>
									<td class='formDeLabel' width="8%" nowrap="norwap"><bean:message key="prompt.juvenile#" /></td>
									<td class='formDe'><nested:write property="juvenileId" /></td>
									<td class='formDeLabel' width="12%" nowrap="norwap"><bean:message key="prompt.juvenileName" /></td>
									<td class='formDe'><nested:write property="juvenileName" /></td>
								</tr>
								<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addMonthlySummary">
									<tr>
										<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.monthlySummary" /> &nbsp; 
											<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addMonthlySummary">
												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
													<tiles:put name="tTextField" value="currentAttendanceEvent.monthlySummary" />
													<tiles:put name="tSpellCount" value="spellBtn1" />
												</tiles:insert>
												(Max. characters allowed: 12000)
											</nested:equal>
										</td>
									</tr>
									<tr>
										<td class='formDe' colspan='4'><nested:textarea style="width:100%" rows="3" property="monthlySummary" onmouseout="textCounter(this, 11850);" onkeypress="textCounter(this,11850)" /></td>
									</tr>
									<tr>
										<td colspan='4' align='center'><br>
											<html:submit property="submitAction" onclick="return validateMonthlySummary(this.form)"><bean:message key="button.saveMonthlySummary" /></html:submit> 
											<html:submit property="submitAction"><bean:message key="button.cancelMonthlySummary" /></html:submit>
										</td>
									</tr>
								</nested:equal>

								<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewMonthlySummary">
								<tr>
									<td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.monthlySummary" /> &nbsp; 
										<nested:equal name="calendarEventDetailsForm" property="secondaryAction" value="addMonthlySummary">
											<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
												<tiles:put name="tTextField" value="currentAttendanceEvent.monthlySummary" />
												<tiles:put name="tSpellCount" value="spellBtn1" />
											</tiles:insert>
											(Max. characters allowed: 12000)
										</nested:equal>
									</td>
								</tr>
									<tr>
										<td class='formDe' colspan='4'>
											<nested:empty  property="monthlySummary">
	                     						 No Monthly Summary.
											</nested:empty>
	
											<nested:notEmpty  property="monthlySummary">
												<nested:write property="monthlySummary" />
											</nested:notEmpty>
										</td>
									</tr>
									<tr>
										<td colspan='4' align='center'><br>
										<html:submit property="submitAction" onclick="closeMonthlySummaryTable();">
											<bean:message key="button.closeMonthlySummary" />
										</html:submit></td>
									</tr>
								</nested:equal>
							</table>
						</nested:nest>
					</td>
				</tr>
			</table>
			</div>
		</jims2:then>
	</jims2:if>

<%-- BEGIN VIEW/ADD ATTENDEES --%>	
	<jims2:if name="calendarEventDetailsForm" property="secondaryAction" value="addAttendess" op="equal">
	<jims2:or name="calendarEventDetailsForm" property="secondaryAction" value="viewAttendees" op="equal" />
		<jims2:then>
			<br>
	    	<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
            	<tr> 
            		<td class='detailHead'><bean:message key="title.juvenileAttendanceList" /> - <bean:message key="prompt.attendee" />&nbsp;<bean:message key="prompt.info" /></td>
            	</tr>
            	<tr>
            		<td>
	           			<nested:nest property="currentAttendanceEvent"> 
							<table width='100%' cellpadding="2" cellspacing="1">
								<tr>
									<td class='formDeLabel' width="8%" nowrap="nowrap"><bean:message key="prompt.juvenile#" /></td>
									<td class='formDe'><nested:write property="juvenileId" /></td>
									<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.juvenileName" /></td>
									<td class='formDe'><nested:write property="juvenileName" /></td>
								</tr>
								<tr> 
								<td class='formDeLabel' valign='top' width='1%' nowrap='nowrap' >											
									<bean:message key="prompt.additionalAttendeeNames"/>
								</td> 
								<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="addAttendess">
									<td class='formDe' colspan="3">									
										<logic:empty name="calendarEventDetailsForm" property="contactNames">
											Name list is empty.
										</logic:empty>
										<logic:notEmpty name="calendarEventDetailsForm" property="contactNames">
											<html:select name="calendarEventDetailsForm" property="selectedAttendeeNames" size="5" multiple="true">
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<logic:iterate id="contactsIter" name="calendarEventDetailsForm" property="contactNames">
													<option value="<bean:write name='contactsIter' property='formattedName'/>">
														<bean:write name="contactsIter" property="formattedName"/>															
													</option> 
												</logic:iterate>
											</html:select>
										</logic:notEmpty>					
									</td> 
								</logic:equal>
								<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewAttendees">
									<td class='formDe' colspan="3">									
										<logic:equal name="calendarEventDetailsForm" property="attendeeNamesStr" value="">
											Name list is empty.
										</logic:equal>
										<logic:notEqual name="calendarEventDetailsForm" property="attendeeNamesStr" value="">
											<bean:write name="calendarEventDetailsForm" property="attendeeNamesStr" />
										</logic:notEqual>
									</td>	
								</logic:equal>
							</tr> 
						</table>
						</nested:nest>
		            </td>
				</tr>
				<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="addAttendess">
					<logic:notEmpty name="calendarEventDetailsForm" property="contactNames">
						<tr>
							<td align="center">
								<html:submit property="submitAction" onclick="return validateAttendeeSelect(this.form.selectedAttendeeNames)"><bean:message key="button.saveAttendeeList" /></html:submit>
								<html:submit property="submitAction"><bean:message key="button.cancelAttendeeList" /></html:submit>
							 </td>
						</tr>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="calendarEventDetailsForm" property="secondaryAction" value="viewAttendees">
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.closeAttendeeList" /></html:submit>
						</td>
					</tr>
				</logic:equal>			
			</table>
    	</jims2:then>
    </jims2:if>   
<%-- END ADD ATTENDEES --%>	
	<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>	
	<table width="100%">
		<logic:equal name="calendarEventDetailsForm" property="action" value="attendanceUpdate">
			<%-- summary buttons --%>
			<tr>
				<td align="center">
					<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction" onclick="return validateFields(this.form)"><bean:message key="button.next" /></html:submit>
					<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
				</td>
			</tr>
		</logic:equal>

		<logic:equal name="calendarEventDetailsForm" property="action" value="attendancePresent">
			<%-- edit buttons --%>
			<tr>
				<td align="center">
					<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- END BUTTON TABLE --%>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>