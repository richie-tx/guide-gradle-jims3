
function showCalendar(theForm)
{
	if(theForm["currentService.locationId"].value == "")
	{
		show('calendar', 0, 'table');
		showHide('schedGrp', 0);
		showHide('schedOne', 0);
		show( 'recurringTable', 0, 'row' ) ;
		show( 'addButtonsTable', 0, 'table');
		
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
		show( 'recurringTable', 0, 'row' ) ;
		show( 'addButtonsTable', 0, 'table');
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
	var services = theForm["currentService.serviceId"];
	var selectedServiceId;

	if(services.length > 0)
	{
		for( i = 0; i < services.length; i++)
		{
			if(services[ i ].checked)
				selectedServiceId = services[ i ].value;
		}
	}
	else
	{
		selectedServiceId = services.value;
	}	

	if('<bean:write name="scheduleNewEventForm" property="currentService.serviceId"/>' != selectedServiceId)
	{
		var forwardURL = "/<msp:webapp/>handleJuvenileServiceEvent.do?submitAction=Service Change";
		
		theForm.action = forwardURL;
		theForm.submit();
	}
}

/* */		
function reloadPage(theForm)
{	
	//show appropriate instruction depending on user's action
	if(theForm["currentService.locationId"].value == "")
	{
		showHide( 'serviceInstruct', 1 ) ;
	}
	else
	{
		showHide('calendar', 1);
		
		if(theForm["currentService.serviceTypeId"].value == "")
		{
			showHide( 'serviceTypeInstruct', 1 ) ;
		}
		else
		{
			showHide('schedGrp', 1);
			showHide('schedOne', 1);
			show( 'recurringTable', 1, 'row' );
			show( 'addButtonsTable', 1, 'table');
			
			showHide( 'multipleInstruct', 1 ) ;
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
	if( validateTime(theForm) && 
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

	if( scheduledDate < today )
	{
		alert("Start Date cannot be in the past. (Now: " + today + " Event:" + scheduledDate +")" );
		eventDate.focus();
		return false;
	}
	
	scheduledDate.setHours(eventTimes[0]);
	scheduledDate.setMinutes(eventTimes[1]);
	today = new Date();
	
	if( scheduledDate < today )
	{
	   alert("Event Time cannot be in the past. (Now: " + today + " Event:" + scheduledDate +")" );
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
