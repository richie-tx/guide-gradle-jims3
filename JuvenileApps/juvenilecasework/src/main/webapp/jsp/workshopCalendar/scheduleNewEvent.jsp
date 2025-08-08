<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 06/07/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.PDCalendarConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<!-- <script>
	$(document).ready(function(){
		$("#curSerServiceTypeCode").on('change',function(){	
		spinner();
		window.localStorage.clear();
		$('form').attr('action',"/JuvenileCasework/handleJuvenileServiceEvent.do?submitAction=Service Location Change");
		$('form').submit();	
	});
	})
	</script> -->

<script type="text/javascript">

$(function() {
	
	if(typeof $("#entryDate")!= "undefined"){			
		datePickerSingle( $("#entryDate"),"Entry Date", false);		 
	}
	if(typeof $("#endDate")!= "undefined"){			
		datePickerSingle( $("#endDate"),"End Date", false);		 
	}
	
});

</script>
<script type="text/javascript">
function showCalendar(theForm)
{
	if(theForm["currentService.locationId"].value == "")
	{
		$('#calendar').hide();
		$('#schedGrp').hide();
		$('schedOne').hide();
		$( '#recurringTable').hide() ;
		$( '#addButtonsTable').hide();
		
		theForm["currentService.serviceTypeId"].value = "";
	}
	else
	{
		var forwardURL = "/<msp:webapp/>handleJuvenileServiceEvent.do?submitAction=Service Location Change";
		theForm.action = forwardURL;
		theForm.submit();
	} 	
	
}

/* */
function showCreateEventForm(theForm)
{
	if(theForm["currentService.serviceTypeId"].value == "")
	{
		showHide('schedGrp', 0);
		showHide('schedOne', 0);
		$('#recurringTable').hide() ;
		$( '#addButtonsTable').hide();
	}
	else
	{
		if(theForm["currentService.locationId"].value == "")
		{
			alert("Please select an option from the *Service Location* dropdown list.");
			theForm["currentService.serviceTypeId"].value = "";
			return false;
		}
		else
		{			
			var forwardURL = "/<msp:webapp/>handleJuvenileServiceEvent.do?submitAction=Service Type Change";			
			theForm.action = forwardURL;
			theForm.submit();
		}
	} 
	 
}

/**
* Normally, onchange would work fine on radio button. 
  However, it didn't work properly in I.E 6.0.29, 
  while it worked fine in FireFox 1.5.0.4
  
  observation: FF is not the defined browser standard.
*/
function showInstructor(theForm)
{
	var services = document.getElementsByName("currentService.serviceId"); //theForm["currentService.serviceId"];
	//console.log('services: ', services );
	
	var selectedServiceId;
	
	if(services.length > 0)
	{
		for( i = 0; i < services.length; i++)
		{
			if(services[ i ].checked)
			{
				selectedServiceId = services[ i ].value;
			}
		}
	}
	else
	{
		selectedServiceId = services.value;
	}	
	
	console.log("selected service Id: ", selectedServiceId);
	
	processShowInstructor(theForm, services, selectedServiceId);
	
}
	
function processShowInstructor(theForm, services, selectedServiceId){
		
	//==== this block of code is not currently being used but might be needed in the future ===
		var serviceProviderId;	
		var spId;
		var rowNum;
		var count;

		for(i = 0; i < services.length; i++){
			if(services[i].checked){
				spId = services[i].parentNode.parentNode.id;
				
				if(spId){
					var charPosition = spId.indexOf("-");
					serviceProviderId = spId.substring(0, charPosition);				
					console.log('serviceProviderId: ', serviceProviderId);
				}			
			}
		}		
	//========================
		
		
	changeService(theForm, selectedServiceId);	
		
}

function changeService(theForm){
		
			var forwardURL = "/<msp:webapp/>handleJuvenileServiceEvent.do?submitAction=Service Change&expandProviders=true";
			theForm.action = forwardURL;
			theForm.submit();
			spinner();								
}

/* */		
function reloadPage(theForm)
{	
	//show appropriate instruction depending on user's action
	if(theForm["currentService.locationId"].value == "")
	{
		$('#serviceInstruct').show() ;
		$('#calendar').hide();
	}
	else
	{
		$('#calendar').show();
		
		if(theForm["currentService.serviceTypeId"].value == "")
		{
			$( '#serviceTypeInstruct' ).show() ;
		}
		else
		{
			$('#schedGrp').show() ;
			$('#schedOne').show() ;
			$('#recurringTable').show() ;
			$( '#addButtonsTable').show();
			$( '#multipleInstruct').show();
			
			toggleRecurrenceTable( );
		}
	}
}

/* */
function serviceTypeCallback()
{	
	var i = document.forms[0].currentService.serviceTypeChoice.selectedIndex ;
	var serviceSelected =  document.forms[0].currentService.serviceTypeChoice.options[ i ].text ;
	
	i = document.forms[0].currentService.locationId.selectedIndex ;
	var locationSelected  =  document.forms[0].currentService.locationId.options[ i ].text ;
	
	if( locationSelected == "Please Select" )
	{
		alert( "Please select an option from the *Service Location* dropdown list" ) ;
		document.forms[0].serviceTypeChoice.selectedIndex = 0 ;
		document.forms[0].currentService.locationId.focus();
		return ;
	}				

	switch( serviceSelected ) 
	{
		case "Please Select" :
			show( 'recurringTable', 0, 'row' ) ;
			break ;

		default:
			// the event-type is 'pre-defined', so show that section
			showHide( 'schedGrp', 1 );
			showHide( 'schedOne', 1 );
			break ;
	} 
}

/* */
function toggleRecurrenceTable( )
{
	show( 'recurrenceTable', document.forms[0]["currentService.currentEvent.recurringEvent"][0].checked, 'row' ) ;
	recurCallback( ) ;
}

/* */
function recurCallback()
{
	var i = document.forms[0]["currentService.currentEvent.recurrencePattern"].selectedIndex ;
	var valSelected =  document.forms[0]["currentService.currentEvent.recurrencePattern"].options[ i ].text ;

	clearRecurRows() ;
	show( 'rangeRow1', 1, 'row' ) ;
	show( 'rangeRow3', 1, 'row' ) ;
	
	switch( valSelected ) 
	{
		case "Daily" :
			show( 'dailyRow1', 1, 'row' ) ;
			show( 'dailyRow2', 1, 'row' ) ;
			break ;
		case "Weekly" :
			show( 'weeklyRow1', 1, 'row' ) ;
			show( 'weeklyRow2', 1, 'row' ) ;
			break ;
		case "Monthly" :
			show( 'monthlyRow1', 1, 'row' ) ;
			show( 'monthlyRow2', 1, 'row' ) ;
			break ;
		case "Yearly" :
			show( 'yearlyRow1', 1, 'row' ) ;
			show( 'yearlyRow2', 1, 'row' ) ;
			break ;
		default:
			clearRecurRows() ;
			break ;									
	}
}

/* */
function clearRecurRows()
{
	show( 'dailyRow1', 0, 'row' ) ;
	show( 'dailyRow2', 0, 'row' ) ;
	show( 'weeklyRow1', 0, 'row' ) ;
	show( 'weeklyRow2', 0, 'row' ) ;
	show( 'monthlyRow1', 0, 'row' ) ;
	show( 'monthlyRow2', 0, 'row' ) ;
	show( 'yearlyRow1', 0, 'row' ) ;
	show( 'yearlyRow2', 0, 'row' ) ;
	show( 'rangeRow1', 0, 'row' ) ;
	show( 'rangeRow3', 0, 'row' ) ;
}

/* */
function validateRecurringEvent(theForm)
{	
	clearAllValArrays();
	var recEvent = document.forms[0]["currentService.currentEvent.recurringEvent"][0].checked;
	
	if (recEvent)
	{				
    var offset = document.forms[0]["currentService.currentEvent.recurrencePattern"].selectedIndex ;			
    var val =  document.forms[0]["currentService.currentEvent.recurrencePattern"].options[ offset ].text ;			

    if( offset == 0 )
		{
      alert("Please select a recurrence pattern");
      return false;
    }					
			
    if (document.forms[0]["currentService.currentEvent.frequencyRadio"][0].checked)
    {	
    	clearAllValArrays();
    	customValRequired("currentService.currentEvent.recurrenceFrequency", "End after ocurrences is required");			
			addNumericValidation("currentService.currentEvent.recurrenceFrequency","End after ocurrences must be numeric");
			if( !validateCustomStrutsBasedJS(theForm) )
			{
				return false;
			}

			var myNumOfOccurrences=document.forms[0]["currentService.currentEvent.recurrenceFrequency"].value;
			if( myNumOfOccurrences > 60 )
			{
				alert("End after ocurrences cannot be greater than 60.");
				document.forms[0]["currentService.currentEvent.recurrenceFrequency"].focus();
				return false;
			}
    }
			
		if( document.forms[0]["currentService.currentEvent.frequencyRadio"][1].checked )
		{
			clearAllValArrays();
  		customValRequired("currentService.currentEvent.recurrenceEndDate", "End by is required");			
			addMMDDYYYYDateValidation("currentService.currentEvent.recurrenceEndDate","End by must be in date format ex: 04/04/2000")		

			if( ! validateCustomStrutsBasedJS(theForm) )
			{
				return false;
			}	

			var startDate = new Date(document.forms[0]["currentService.currentEvent.eventDateStr"].value);
			var endDate = new Date(document.forms[0]["currentService.currentEvent.recurrenceEndDate"].value);
		
			if( endDate <= startDate )
			{
				alert("End date cannot occur before start date");
				document.forms[0]["currentService.currentEvent.recurrenceEndDate"].focus();
				return false;
			}		

			var eventDate = document.forms[0]["currentService.currentEvent.eventDateStr"];
			var oneYearFromStartDate = new Date(eventDate.value);

			oneYearFromStartDate.setFullYear(oneYearFromStartDate.getFullYear() +1 );
			if( endDate > oneYearFromStartDate )
			{
				alert("End date cannot be more than one year from start date.");
				document.forms[0]["currentService.currentEvent.recurrenceEndDate"].focus();
				return false;
			}		
		}				
			
		switch( val ) 
		{
  		case "Daily" :
  			return validateDailyRecurrence(theForm);					
  		case "Weekly" :
  			return validateWeeklyRecurrence(theForm);					
  		case "Monthly" :
  			return validateMonthlyRecurrence(theForm);					
  		case "Yearly" :
  			return validateYearlyRecurrence(theForm);					
		}
	}

	return true;
}

/* */
function validateDailyRecurrence(theForm)
{
	if( document.forms[0]["currentService.currentEvent.dailyRadio"][0].checked )
	{
		clearAllValArrays();
		customValRequired("currentService.currentEvent.dailyRecurrenceInterval", "Recurrence Interval is required");			
		addNumericValidation("currentService.currentEvent.dailyRecurrenceInterval","Recurrence Interval must be numeric");

		return validateCustomStrutsBasedJS(theForm);
	}
 
	return true;
}

/* */
function validateWeeklyRecurrence(theForm)
{
  clearAllValArrays();
	customValRequired("currentService.currentEvent.weeklyRecurrenceInterval", "Recurrence Interval is required");			
	addNumericValidation("currentService.currentEvent.weeklyRecurrenceInterval","Recurrence Interval must be numeric");

	if( ! validateCustomStrutsBasedJS(theForm) )
	{
		return false;
	}
 
  var dayList = document.forms[0]["currentService.currentEvent.weeklyDay"];
	if(dayList)
	{
		for(i = 0; i < dayList.length; i++)
		{
			if( dayList[ i ].checked )
			{
				return true;
			}
		}
	}
	alert("Please select a week day in order to proceed");

	return false;
}

/* */
function validateMonthlyRecurrence(theForm)
{
	if( document.forms[0]["currentService.currentEvent.monthlyRadio"][0].checked )
	{
		clearAllValArrays();
		customValRequired("currentService.currentEvent.monthlyDay", "Day of month is required");			
		addNumericValidation("currentService.currentEvent.monthlyDay","Day of month must be numeric");

		if( ! validateCustomStrutsBasedJS(theForm) )
		{
			return false;
		}

	  clearAllValArrays();
		customValRequired("currentService.currentEvent.monthlyRecurrenceInterval1", "Interval is required");			
		addNumericValidation("currentService.currentEvent.monthlyRecurrenceInterval1","Interval must be numeric");
		
		if( ! validateCustomStrutsBasedJS(theForm) )
		{
			return false;
		}
	} 
	else if(document.forms[0]["currentService.currentEvent.monthlyRadio"][1].checked)
	{
		clearAllValArrays();
		customValRequired("currentService.currentEvent.monthlyRecurrenceInterval2", "Interval is required");			
		addNumericValidation("currentService.currentEvent.monthlyRecurrenceInterval2","Interval must be numeric");

		return validateCustomStrutsBasedJS(theForm);
	}

	return true;
}

/* */
function validateYearlyRecurrence(theForm)
{
	if (document.forms[0]["currentService.currentEvent.yearlyRadio"][0].checked)
	{
		var day = document.forms[0]["currentService.currentEvent.yearlyDay"].value;
		var offset = document.forms[0]["currentService.currentEvent.yearlyMonthNumber"].selectedIndex ;						
		var month = document.forms[0]["currentService.currentEvent.yearlyMonthNumber"].options[offset].value;

		clearAllValArrays();
		customValRequired("currentService.currentEvent.yearlyDay", "Day is required");			
		addNumericValidation("currentService.currentEvent.yearlyDay","Day must be numeric");
		
		if( ! validateCustomStrutsBasedJS(theForm) )
		{
			return false;
		}

		if( day < 1 || day > 31 )
		{
			alert("Day must be between 1 and 31.");
			document.forms[0]["currentService.currentEvent.yearlyDay"].focus();
			return false;
		}

		if( (month == 3 || month == 5 || month == 8 || month == 10) && day == 31) 
		{
			alert("Month does not have 31 days!")
			document.forms[0]["currentService.currentEvent.yearlyDay"].focus();
			return false
		}

		if( month == 1 && day > 28 )
		{ 
			alert("February doesn't have " + day + " days!");
			document.forms[0]["currentService.currentEvent.yearlyDay"].focus();
			return false;
		}		
	}	
	
	return true;
}  

/* */
function customValidation(theForm)
{ 
	if( validateAddToWorkshopCalendar(theForm) &&
			validateTime(theForm) && 
			validateServiceProvider(theForm) && 
			validateInstructorName(theForm) && 
			isPastEvent(theForm)&& 
		  validateRecurringEvent(theForm))
	{
    return true;
  }
  else
	{
    return false;
  }
}
function validateAddToWorkshopCalendar(theForm)
{
	var evtDate = theForm["currentService.currentEvent.eventDateStr"].value;
	var evtTime = theForm["currentService.currentEvent.eventTime"].value;
	var sessionlengthVal = theForm["currentService.currentEvent.sessionLength"].value;
	var maxVal = theForm["currentService.currentEvent.maxAttendance"].value;
	var message="";
	var check=false;
	if(evtDate == ""){
		message = message+"Event Date is required"+"\n";
		//alert("Event Date is required");
		check = true;
	}
	if(evtTime == "" ){
		message = message+"Event Time is required"+"\n";
		check = true;
	} 
	if(sessionlengthVal == "" ){
		//alert("Session Length is required");
		//return false;
		message = message+"Session Length is required"+"\n";
		check = true;
	} 
	if(maxVal == "" ){
		//alert("Max Attendence is required");
		//return false;
		message = message+"Max Attendance is required"+"\n";
		check = true;
	}
	if(check){
		alert(message);
		return false;
	} else{
		return true;
	}

}
/* */
function validateTime(theForm)
{
	var eventTime = theForm["currentService.currentEvent.eventTime"].value;
	var sessionLength = theForm["currentService.currentEvent.sessionLength"].value;
	var separator = ':';
	
	if(sessionLength == "")
	{
		return true;
	}		

	var eventTimes = eventTime.split(separator);
	var sessionLengths = sessionLength.split(separator);
	
	var eventHr = 0, eventMin = 0;
	
	if(eventTime == "12:00 PM") 
	{
		eventHr = 12;
	} 
	else if(eventTime == "12:30 PM") 
	{
		eventHr = 12;
		eventMin = 30;
	}
	else 
	{
		var amPm = eventTime.substring(6).toUpperCase();
		eventHr = parseInt(eventTimes[0]);	
		if(amPm == "PM") 
		{
			eventHr = eventHr + 12;
		}		
		eventMin = parseInt(eventTimes[1]);
	}
	
	/* The sessionLength code table values are 5,10,15,20,25,30,35,40,45,50...80,
		which stands for session length 0.5 hr, 1 hr, 1.5 hrs....8hrs.
		Parse out the hour by dividing the value by 10. Parse out minutes 
		by doing a mod on 10..minute value is always 0 or 30.
	*/ 
	var sessLengthInt = parseInt(sessionLength);
	var sessLengthHr = parseInt(sessLengthInt / 10);	
	var sessLengthMin = 0;
	
	if( sessLengthInt % 10 != 0 )
	{
		sessLengthMin = 30;
	}
			
	var totalHour = eventHr + sessLengthHr;
	if(eventMin + sessLengthMin > 59)
	{
		totalHour++;
	}
	
	if(totalHour > 23)
	{
		alert("End date cannot go to the next day.");
		return false;
	}
	else 
	{
		return true;
	}
}

/* */
function isPastEvent(theForm)
{
	var eventTime = theForm["currentService.currentEvent.eventTime"];
	var eventDate = theForm["currentService.currentEvent.eventDateStr"];
	var separator = ':';

	clearAllValArrays();
	customValRequired("currentService.currentEvent.eventDateStr", "Scheduled Date is required.");
	addMMDDYYYYDateValidation("currentService.currentEvent.eventDateStr","Scheduled Date must be a date ex: 04/04/2000");
	customValMaxLength('currentService.currentEvent.comments','Comments cannot be longer than 255 characters',255);
	addDB2FreeTextValidation('currentService.currentEvent.comments',"<bean:message key='errors.comments' arg0='Comments'/>",'');

	if( ! validateCustomStrutsBasedJS(theForm) )
	{
		return false;
	}
	
	var scheduledDate = new Date(eventDate.value);
	var eventTimes = eventTime.value.split(separator);
	var today = new Date();
	
	today.setHours(0);
	today.setMinutes(0);
	today.setSeconds(0);
	today.setMilliseconds(0);

	// <kkoyyada>JIMS200056909 : Allow the users to schedule past dates/times but no more than 7 days. 
	today.setDate(today.getDate()- 7);
	
	if( scheduledDate < today)
	{
		alert("An event cannot be scheduled for a date/time older than 7 days.(Allowed Date: " + (today) + " Event:" + scheduledDate +")" );
		//alert("Start Date cannot be in the past. (Now: " + today + " Event:" + scheduledDate +")" );
		eventDate.focus();
		return false;
	}

	var scheduledDate = new Date(eventDate.value);
	var eventTimes = eventTime.value.split(':');
	var eventHrStr = eventTimes[0];
	var eventMinStr = eventTimes[1].split(' ')[0];
	var eventAMPM = eventTimes[1].split(' ')[1];

	var eventHr;
	var eventMin;	

	if(eventHrStr.charAt(0) == '0') 
	{
		eventHr = parseInt(eventHrStr.charAt(1));
	}
	else
	{
		eventHr = parseInt(eventHrStr);
	}
		
	if(eventMinStr.charAt(0) == '0')
	{
		eventMin = parseInt(eventMinStr.charAt(1));
	}
	else
	{
		eventMin = parseInt(eventMinStr);
	}
	
	if(eventAMPM.toUpperCase() == "AM") 
	{
		if(eventHr == 12) 
		{
			eventHr = 0; 
		}
	} 
	else 
	{ //PM here
		if(eventHr != 12) 
		{
			eventHr += 12;
		}
	}
	
	scheduledDate.setHours(eventHr);
	scheduledDate.setMinutes(eventMin);
	today = new Date();

	// <kkoyyada>JIMS200056909 : Allow the users to schedule past dates/times but no more than 7 days.
	today.setDate(today.getDate()- 7);
	
	if( scheduledDate < today )
	{
	   alert("Event Time cannot be in the past. (Allowed Timeline: " + today + " Event:" + scheduledDate +")" );
	   eventTime.focus();
	   return false;
	}	
	
	return true;
}

/* */
function validateServiceProvider(theForm)
{	
	var svcRadio = theForm["currentService.serviceId"];
	
	if(svcRadio)
	{
		if(svcRadio.length) //more than 1 radio button
		{
			for(i = 0; i < svcRadio.length; i++)
			{
				if(svcRadio[i].checked == true)
				{
					return true;
				}
			}
			
			alert("Please select a service in order to proceed.");
			return false;
		}
		else
		{
			if(svcRadio.checked == true)
			{
				return true;
			}
			else
			{
				alert("Please select a service in order to proceed.");
				return false;
			}
		}
	}
	
	return false;
}

/* */
function validateInstructorName(theForm)
{
	var instructorList = theForm["currentService.currentEvent.instructorId"];
	
	if(instructorList)
	{
		for(i = 0; i < instructorList.length; i++)
		{
			if(instructorList[i].selected == true && instructorList[i].value != "")
			{
				return true;
			}
		}
	}

	alert("Please select an instructor in order to proceed");
	
	return false;
}
</script>
<%-- <html:javascript formName="addToWorkshopCalendarForm"/>  --%>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;Workshop Calendar - scheduleNewEvent.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>


<html:form action="/handleJuvenileServiceEvent" target="content">
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0' onload="reloadPage(document.forms[0])">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|263"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Workshop Calendar - Schedule Service</td>
  </tr>
</table>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" align="center">
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
      <div id='serviceInstruct' class='hidden'>
        <ul>
          <li>Select a <b>Service Location</b> from the dropdown selection list.</li>
        </ul>
      </div>

      <div id='serviceTypeInstruct' class='hidden'>
        <ul>
          <li>Select a <b>Service Type</b> from the dropdown selection list.</li>
          <li>Select a hyperlinked day on the calendar to view the scheduled services in a new window.</li>
        </ul>
      </div>

      <div id='multipleInstruct' class='hidden'>
        <ul>
          <li>Expand a Service Provider, then select a Service</li>
          <li>Provide the information required in the <b>Service Event Scheduling</b> section.</li>
          <li>If event recurs, select Yes to <b>Recurring Event?</b> and define the Pattern and Range, if applicable.</li>
          <li>Select <b>Add to Workshop Calendar</b> button to add to calendar.</li>
        </ul>
		    <br>
        <span class="required" align="left"><bean:message key="prompt.dateFieldsInstruction" /></span>
      </div>

      <div id='singleInstruct' class='hidden'>
        <ul>
          <li>Enter the single event information.</li>
          <li>Provide the information required in the <b>Service Event Scheduling</b> section.</li>
          <li>Select <b>Add to Workshop Calendar</b> button to add to calendar.</li>
          <li>If event recurs, select Yes to <b>Recurring Event?</b> and define the Pattern and Range, if applicable.</li>
        </ul>
			  <br>
        <span class="required"><bean:message key="prompt.dateFieldsInstruction" /></span>
      </div>
    
      <div id='summaryInstruct' class='hidden'>
        <ul>
          <li>Select hyperlinked Event ID to view conflicting event.</li>
          <li>Select <b>Add conflicting event</b> checkbox, then <b>Go Ahead &amp; Schedule</b> button to add event regardless of conflict.</li>
          <li>Select <b>Back</b> button to reschedule selected event.</li>
        </ul>
      </div>

      <div id='confInstruct' class='hidden'>
        <ul>
          <li>Select the <b>Return to Schedule Workshop Calendar</b> button to continue.</li>
        </ul>
      </div>
	  </td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- Service Location Unit and Service Type dropdown list table --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">Service Type</td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">			
				<tr>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" />Service Location Unit</td>
					<td class="formDe" colspan="3">
						<html:select name="scheduleNewEventForm" property="currentService.locationId" onchange="showCalendar(this.form);">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection name="scheduleNewEventForm" property="currentService.locationsList" value="juvLocationUnitId" label="locationUnitName" />
						</html:select>
					</td>								
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" />Service Type</td>
					 <td class="formDe" colspan="3">
						<html:select name="scheduleNewEventForm" property="currentService.serviceTypeId"
						onchange="showCreateEventForm(this.form);">
						<html:option value="">
							<bean:message key="select.generic" />
						</html:option>
						<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeId"
							label="serviceTypeName" />
					</html:select>
					</td> 
					<%-- <td class="formDe" colspan="3">
						<html:select  name="scheduleNewEventForm" property="currentService.serviceTypeCode" onchange="showCreateEventForm(this.form);">
							<html:option value=""><bean:message key="select.generic" /></html:option>
							<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeCode" label="description" />					
						</html:select>
					</td> --%>
				</tr>
      </table>
    </td>
  </tr>
</table>
<%-- End Service Location Unit and Service Type dropdown list table --%>


<%-- Service Provider event list table --%>
<div id='schedGrp' class='hidden'>
<div class='spacer'></div>
<table align='center' width='98%' cellpadding='0' cellspacing='0'>  
	<tr>
		<td colspan='4'>
			<table align='center' width='100%' border='0' cellpadding='0' cellspacing='0' class='borderTableBlue'>
				<tr>
					<td>          
						<table width='100%' border='0' cellpadding='2' cellspacing='1'>
							<tr>
								<td class='detailHead' width='30%' align="left"><bean:message key="prompt.2.diamond" />&nbsp;Service Provider</td>
								<td class='detailHead' align="left">Service Name</td>
							</tr>
						
							<logic:notEmpty name="scheduleNewEventForm" property="currentService.serviceProviders">
								<logic:iterate id="serviceProvidersIter" name="scheduleNewEventForm" property="currentService.serviceProviders">
									<tr class='selectedRow'>
										<td colspan='2' align="left">
											<bean:size id="serviceCount" name="serviceProvidersIter" property="serviceResponseEvents" />
											<logic:equal name="scheduleNewEventForm" property="expandProviders" value="false">																					
												<a href="javascript:showHideMulti('list1ParentExpand', 
													'<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-', <%=serviceCount%>, '/<msp:webapp/>');">
													<img src="/<msp:webapp/>images/expand.gif" name="list1ParentExpand" border='0'>
												</a>												
											</logic:equal>
											<logic:equal name="scheduleNewEventForm" property="expandProviders" value="true">												
												<a href="javascript:showHideMulti('list1ParentContract', 
													'<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-', <%=serviceCount%>, '/<msp:webapp/>');">
													<img src="/<msp:webapp/>images/contract.gif" name="list1ParentContract" border='0'>
												</a>
											</logic:equal>
											&nbsp;<bean:write name="serviceProvidersIter" property="juvServProviderName"/>
										</td>
									</tr>

									<logic:empty name="serviceProvidersIter" property="serviceResponseEvents">
										<tr>
											<td colspan='2' class='alternateRow' align="left">No Available Services</td>
										</tr>
									</logic:empty>
									
									<logic:notEmpty name="serviceProvidersIter" property="serviceResponseEvents">
										<logic:iterate indexId="index" id="serviceResponseEventsIter" name="serviceProvidersIter" property="serviceResponseEvents">											
																								
											<logic:equal name="scheduleNewEventForm" property="expandProviders" value="false">																															
												<tr  class="hidden" id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>'>
								                	<td colspan='2' align="left">
									                  <table width="100%" border='0' cellpadding="2" cellspacing="1">
					                  					<html:hidden  name="serviceProvidersIter" property="juvServProviderId" styleId="juvServProviderId"/>
															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>'>
																<td width="30%" align="left" nowrap>
																	<bean:define id="svcId" name="serviceResponseEventsIter" property="serviceId" type="java.lang.String"/>	
																	<%-- <bean:define id="pgmId" name="serviceResponseEventsIter" property="programId" type="java.lang.String"/>	 --%>											
																	<html:radio name="scheduleNewEventForm" property="currentService.serviceId" value="<%=svcId%>" onclick='showInstructor(this.form);'/>
																	<bean:write name='serviceResponseEventsIter' property='serviceId'/>
																	<%-- <bean:write name='serviceResponseEventsIter' property='programId'/> --%>
																</td>
																<td align="left" nowrap><bean:write name="serviceResponseEventsIter" property="serviceName"/></td>	
																<%-- <html:hidden  name="serviceResponseEventsIter" property="programId" styleId="programId"/> --%>															
															</tr>	
														</table>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="scheduleNewEventForm" property="expandProviders" value="true">
												<bean:define id="spId" name="scheduleNewEventForm" property="selectedServiceProviderId" type="java.lang.String"/>
													
												<logic:equal name="serviceProvidersIter" property="juvServProviderId" value="<%=spId%>">	
												<tr  id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>'>
													
									                	<td colspan='2'>
											                	<table width="100%" border='0' cellpadding="2" cellspacing="1">
																	<tr class="<%out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>' >
																		<td width='30%' align="left" nowrap>
																			<bean:define id="svcId" name="serviceResponseEventsIter" property="serviceId" type="java.lang.String"/>													
																			<html:radio name="scheduleNewEventForm" property="currentService.serviceId" value="<%=svcId%>" onclick="showInstructor(this.form);"/>
																			<bean:write name='serviceResponseEventsIter' property='serviceId'/>
																		</td>
																		<td align="left" nowrap><bean:write name="serviceResponseEventsIter" property="serviceName"/></td>
																		
																	</tr>	
																</table>
														</td>

												</tr>
												</logic:equal>
												<logic:notEqual name="serviceProvidersIter" property="juvServProviderId" value="<%=spId%>">
													<tr class="hidden" id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>'>
													
									                	<td colspan='2'>
											                	<table width="100%" border='0' cellpadding="2" cellspacing="1">
																	<tr class="<%out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" id='<bean:write name="serviceProvidersIter" property="juvServProviderId"/>-<%=index.intValue()%>' >
																		<td width='30%' align="left" nowrap>
																			<bean:define id="svcId" name="serviceResponseEventsIter" property="serviceId" type="java.lang.String"/>													
																			<html:radio name="scheduleNewEventForm" property="currentService.serviceId" value="<%=svcId%>" onclick="showInstructor(this.form);"/>
																			<bean:write name='serviceResponseEventsIter' property='serviceId'/>
																		</td>
																		<td align="left" nowrap><bean:write name="serviceResponseEventsIter" property="serviceName"/></td>
																		
																	</tr>	
																</table>
														</td>

												</tr>
												</logic:notEqual>
												
											</logic:equal>
										</logic:iterate>
									</logic:notEmpty>
									
								</logic:iterate>
							</logic:notEmpty>	

						</table>
          </td>
        </tr>
      </table>			
		</td>
  </tr>
</table>			
</div>
<%-- End Service Provider event list table --%>


<%-- service event scheduling table --%>
<div id='schedOne' class='hidden'>
	<br>
	<table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">  
		<tr>
			<td class="detailHead" colspan='4'>Service Event Scheduling</td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.eventDate"/></td>
			<td class="formDe">
				<html:text styleId="entryDate" name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr" size="10" maxlength="10"/>
			</td> 
			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.eventTime"/></td>
      <td class="formDe">
        <html:select property="currentService.currentEvent.eventTime">
					<html:option key="select.generic" value="" />
					<html:optionsCollection name="scheduleNewEventForm" property="currentService.currentEvent.workDays" value="description" label="description" />
			  </html:select>
		  </td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.sessionLength"/></td>
			<td class="formDe">
			  <html:select property="currentService.currentEvent.sessionLength">
					<html:option key="select.generic" value="" />
					<html:optionsCollection name="scheduleNewEventForm" property="currentService.sessionLengthIntList" value="code" label="description" />
			  </html:select>					
			</td>
			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.maxAttendance"/></td>
			<td class="formDe"><html:text name="scheduleNewEventForm" property="currentService.currentEvent.maxAttendance" size="4" maxlength="4"/></td>
		</tr>

		<logic:empty name="scheduleNewEventForm" property="currentService.instructorList">
			<% String noInstruct = (String)request.getAttribute("showNoInstructor");
			   String rowClass = "hidden" ;
				if( noInstruct != null )
				{
					rowClass = noInstruct ;
				}					
			%>
			<tr class="<%=rowClass%>" id='instructorOne'>
				<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.instructorName"/></td>
				<td class="errorAlert" colspan='3'>No Instructor(s) found.</td> 
			</tr>
		</logic:empty>
		
		<logic:notEmpty name="scheduleNewEventForm" property="currentService.instructorList">
			<tr id='instructorOne'>
				<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.instructorName"/></td>
				<td class="formDe" colspan='3'> 
					<html:select name="scheduleNewEventForm" property="currentService.currentEvent.instructorId">
						<html:option value=""><bean:message key="select.generic" /></html:option>
						<html:optionsCollection property="currentService.instructorList" value="id" label="contactName.formattedName" />
					</html:select> 
				</td> 
			</tr>
		</logic:notEmpty>

	  <%-- comments field --%>		
		<tr>
	    <td colspan='4'>
				<table align="center" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="formDeLabel">Comments&nbsp;
    					<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
    						<tiles:put name="tTextField" value="currentService.currentEvent.comments"/>
     						<tiles:put name="tSpellCount" value="spellBtn1" />
  						</tiles:insert>
  						(Max. characters allowed: 255)  
  					</td>
					</tr>
	        <tr>
						<td class="formDe">
						  <%-- 02mar2009 - mjt - column length for this field is 250 in the database,
								  but the counter was set to 255 ... need to account for date/time/user stamp --%>
							<html:textarea name="scheduleNewEventForm" property="currentService.currentEvent.comments" style="width:100%" rows="3" onmouseout="textCounter(this,255)" onkeydown="textCounter(this,255)"/>
						</td>
					</tr>
				</table>
	    </td>
	  </tr>
	  <%-- end comments field --%>
	</table>			
</div>
<%-- end service event scheduling table --%>


<%-- recurring event table --%>
<table align="center" width='98%' cellpadding="0" cellspacing="0">
	<tr id='recurringTable' class='hidden'>
		<td>
			<br>
			<table align="center" width='100%' cellpadding="2" cellspacing="1" class="borderTableGrey">        
				<tr>
					<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.2.diamond" />Recurring Event?</td>
					<td class="formDe" nowrap='nowrap' onclick="toggleRecurrenceTable( );">
						<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="true">Yes</html:radio>
						<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="false">No</html:radio>
					</td>					
				</tr>				
   		</table>
		</td>
	</tr>

	<tr id='recurrenceTable' class='hidden'>
		<td colspan='2'>
       <%-- Recurrence Pattern inner table --%>
			<table align="center" width='100%' cellpadding="2" cellspacing="1" class="borderTableGrey">
				<tr>
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond" />Recurrence Pattern</td>
				</tr>
				<tr>
					<td nowrap='nowrap' class='formDe' align="left">
						<html:select name="scheduleNewEventForm" property="currentService.currentEvent.recurrencePattern" onchange='javascript:recurCallback();'>
							<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:option value="D">
								<bean:message key="prompt.daily" />
							</html:option>
							<html:option value="W">
								<bean:message key="prompt.weekly" />
							</html:option>
							<html:option value="M">
								<bean:message key="prompt.monthly" />
							</html:option>
							<html:option value="Y">
								<bean:message key="prompt.yearly" />
							</html:option>									
						</html:select>
			    </td>
				</tr>

				<tr id='dailyRow1' class='hidden'>
					<td nowrap='nowrap' align="left">							
						<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.dailyRadio" value="true" />
						Every&nbsp;<html:text name="scheduleNewEventForm" property="currentService.currentEvent.dailyRecurrenceInterval" size="3" maxlength="3"/>
						&nbsp;day(s).
					</td>
				</tr>

				<tr id='dailyRow2' class='hidden' align="left">
					<td class='formDe'><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.dailyRadio" value="false"/>Every weekday</td>
				</tr>

				<tr id='weeklyRow1' class='hidden' align="left">
					<td nowrap='nowrap'>
						Recur every <html:text name="scheduleNewEventForm" property="currentService.currentEvent.weeklyRecurrenceInterval" size="3" maxlength="3"/>
						week(s) on:
					</td>
				</tr>

				<tr id='weeklyRow2' class='hidden'>
					<td nowrap='nowrap' class='formDe' align="left">
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="0">
							<bean:message key="prompt.sun" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="1">
							<bean:message key="prompt.mon" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="2">
							<bean:message key="prompt.tue" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="3">
							<bean:message key="prompt.wed" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="4">
							<bean:message key="prompt.thu" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="5">
							<bean:message key="prompt.fri" />
						</html:checkbox>
						<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="6">
							<bean:message key="prompt.sat" />
						</html:checkbox>
					</td>
				</tr>

				<tr id='monthlyRow1' class='hidden'>
					<td nowrap='nowrap' align="left"><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRadio" value="true"/>
						Day&nbsp;
						<html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyDay" size="2" maxlength="2"/>
						&nbsp;of every&nbsp;
						<html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRecurrenceInterval1" size="2" maxlength="2"/>
						&nbsp;month(s).
					</td>
				</tr>

				<tr id='monthlyRow2' class='hidden'>
					<td nowrap='nowrap' class='formDe' align="left"><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRadio" value="false" />
						The&nbsp;
						<html:select name="scheduleNewEventForm" property="currentService.currentEvent.monthlyWeekNumber" >
						<html:option value="1">
						<bean:message key="prompt.first" />
						</html:option>					
						<html:option value="2">
						<bean:message key="prompt.second" />
						</html:option>
						<html:option value="3">
						<bean:message key="prompt.third" />
						</html:option>
						<html:option value="4">
						<bean:message key="prompt.fourth" />
						</html:option>
						<html:option value="5">
						<bean:message key="prompt.last" />
						</html:option>
						</html:select>						
             <html:select name="scheduleNewEventForm" property="currentService.currentEvent.monthlyWeekDay" >
							<html:option value="0">
							<bean:message key="prompt.sunday" />
							</html:option>					
							<html:option value="1">
							<bean:message key="prompt.monday" />
							</html:option>
							<html:option value="2">
							<bean:message key="prompt.tuesday" />
							</html:option>
							<html:option value="3">
							<bean:message key="prompt.wednesday" />
							</html:option>
							<html:option value="4">
							<bean:message key="prompt.thursday" />
							</html:option>
							<html:option value="5">
							<bean:message key="prompt.friday" />
							</html:option>						
							<html:option value="6">
							<bean:message key="prompt.saturday" />
							</html:option>									
							</html:select>						
              of every <html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRecurrenceInterval2" size="2" maxlength="2"/>&nbsp;month(s).
 						</td>
 					</tr>
 
 	        <tr id='yearlyRow1' class='hidden'>
   					<td nowrap='nowrap' align="left"><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.yearlyRadio" value="true"/>Every&nbsp;
							<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyMonthNumber" >
								<html:option value="0">
									<bean:message key="prompt.january" />
								</html:option>					
								<html:option value="1">
									<bean:message key="prompt.february" />
								</html:option>
								<html:option value="2">
									<bean:message key="prompt.march" />
								</html:option>
								<html:option value="3">
									<bean:message key="prompt.april" />
								</html:option>
								<html:option value="4">
									<bean:message key="prompt.may" />
								</html:option>						
								<html:option value="5">
									<bean:message key="prompt.june" />
								</html:option>									
								<html:option value="6">
									<bean:message key="prompt.july" />
								</html:option>
								<html:option value="7">
									<bean:message key="prompt.august" />
								</html:option>
								<html:option value="8">
									<bean:message key="prompt.september" />
								</html:option>
								<html:option value="9">
									<bean:message key="prompt.october" />
								</html:option>
								<html:option value="10">
									<bean:message key="prompt.november" />
								</html:option>
								<html:option value="11">
									<bean:message key="prompt.december" />
								</html:option>						
							</html:select>
							<html:text name="scheduleNewEventForm" property="currentService.currentEvent.yearlyDay" size="2" maxlength="2"/>
 						</td>
				  </tr>

 					<tr id='yearlyRow2' class='hidden'>
 						<td nowrap='nowrap' class='formDe' align="left">
							<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.yearlyRadio" value="false"/>
								Every&nbsp;The&nbsp;
							
								<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekNumber" >
									<html:option value="1">
										<bean:message key="prompt.first" />
									</html:option>					
									<html:option value="2">
										<bean:message key="prompt.second" />
									</html:option>
									<html:option value="3">
										<bean:message key="prompt.third" />
									</html:option>
									<html:option value="4">
										<bean:message key="prompt.fourth" />
									</html:option>
									<html:option value="5">
										<bean:message key="prompt.last" />
									</html:option>
								</html:select>	

               <html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekDay" >
								<html:option value="0">
									<bean:message key="prompt.sunday" />
								</html:option>					
								<html:option value="1">
									<bean:message key="prompt.monday" />
								</html:option>
								<html:option value="2">
									<bean:message key="prompt.tuesday" />
								</html:option>
								<html:option value="3">
									<bean:message key="prompt.wednesday" />
								</html:option>
								<html:option value="4">
									<bean:message key="prompt.thursday" />
								</html:option>
								<html:option value="5">
									<bean:message key="prompt.friday" />
								</html:option>						
								<html:option value="6">
									<bean:message key="prompt.saturday" />
								</html:option>									
							</html:select>	&nbsp;	of  &nbsp;
					
							<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyMonthNumber1" >
								<html:option value="0">
									<bean:message key="prompt.january" />
								</html:option>					
								<html:option value="1">
									<bean:message key="prompt.february" />
								</html:option>
								<html:option value="2">
									<bean:message key="prompt.march" />
								</html:option>
								<html:option value="3">
									<bean:message key="prompt.april" />
								</html:option>
								<html:option value="4">
									<bean:message key="prompt.may" />
								</html:option>						
								<html:option value="5">
									<bean:message key="prompt.june" />
								</html:option>									
								<html:option value="6">
									<bean:message key="prompt.july" />
								</html:option>
								<html:option value="7">
									<bean:message key="prompt.august" />
								</html:option>
								<html:option value="8">
									<bean:message key="prompt.september" />
								</html:option>
								<html:option value="9">
									<bean:message key="prompt.october" />
								</html:option>
								<html:option value="10">
									<bean:message key="prompt.november" />
								</html:option>
								<html:option value="11">
									<bean:message key="prompt.december" />
								</html:option>						
							</html:select>					
 						</td>
 					</tr>
      					
 					<tr id='rangeRow1' class='hidden'>
 						<td class='formDeLabel' align="left"><bean:message key="prompt.2.diamond" />Range of Recurrence</td>
 					</tr>
					<tr id='rangeRow3' class='hidden'>
     				<td class='formDe' align="left">
						  <html:radio name="scheduleNewEventForm" property="currentService.currentEvent.frequencyRadio" value="true"/>
						  End after&nbsp;
						  <html:text name="scheduleNewEventForm" property="currentService.currentEvent.recurrenceFrequency" size="2" maxlength="2"/>
						  &nbsp;occurrences&nbsp;&nbsp;
						  
     						 <html:radio name="scheduleNewEventForm" property="currentService.currentEvent.frequencyRadio" value="false"/>
							End by&nbsp;
							<html:text styleId="endDate" name="scheduleNewEventForm" property="currentService.currentEvent.recurrenceEndDate" size="10" maxlength="10"/>
     				  </td>
      			 </tr>					
        
            </table> <%-- Recurrence Pattern inner table --%>
          </td>
        </tr>
</table>
<%-- end of *recurrence* functionality.  --%>


<%-- BEGIN BUTTON TABLE --%>
<span id='addButtonsTable' class='hidden'>
<div class='spacer'></div>
<table width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="return (customValidation(this.form))"><bean:message key="button.addToWorkshopCalendar"/></html:submit> 
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</span>


<%-- Calendar Table --%>
<div class='spacer'></div>
<table border="0" width="100%" cellspacing="0" cellpadding="0" id='calendar' class='hidden'>
	<tr>
		<td align="center">
			<logic:notEmpty name="scheduleNewEventForm" property="currentService.locationId">
				<bean:define id="calendarTitle">Workshop Calendar for location <bean:write name="scheduleNewEventForm" property="currentService.location"/></bean:define>
				<bean:define id="eventLinkURL">/<msp:webapp/>displayServiceEventList.do?submitAction=Display Service Event List&locationId=<bean:write name="scheduleNewEventForm" property="currentService.locationId"/></bean:define>
				<bean:define id="locationId"><bean:write name="scheduleNewEventForm" property="currentService.locationId"/></bean:define>
				
				<% Boolean need = (Boolean)request.getAttribute("calendarNeedsRefresh");
					boolean needsRefresh = true;
					if( need != null )
					{
						needsRefresh = need.booleanValue();
					}					
				%>
				
				<jims2:appcalendar  
					calendarStyleSheet="blueCalendarSkin.css"
					serviceEvent="messaging.calendar.GetCalendarServiceEventsEvent"
					eventTimeFormat="h:mma"
					weekDayViewType="FULLTEXT"
					sessionAttributeName="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>"
					eventLink="<%=eventLinkURL%>"
					dayDisplayClass="ui.taglib.calendar.ConsolidatedEventDayPresentation"
					title="<%=calendarTitle%>"
					locationId="<%=locationId%>"
					responseType="short" 
					needsRefresh="<%=needsRefresh%>">
				</jims2:appcalendar>
			</logic:notEmpty>
		</td>
	</tr>  
</table>

</body>
</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:html>
