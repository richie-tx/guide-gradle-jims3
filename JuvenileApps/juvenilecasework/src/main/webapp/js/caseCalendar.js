//JQuery code for all files in the caseTabCalendar folder

$(document).ready(function(){
	
	var tentativeRefPrgm =$("#tentativeRefPrgm").val();  //added for #36737
	//#33365 added for doc attendance.
	var currDate= new Date();
	var currTime = currDate.getHours() + ":" + currDate.getMinutes();
	var currTimeStr ="";
	var expandSPName=[];
	var programName=[];
	if(typeof currTime != "undefined" && currTime!=""){
	  currTimeStr =  new Date(0,0,0,currTime.split(":")[0],currTime.split(":")[1],0,0).getTime();
	}
	
	//Calendar 
	var eventDate = $("#eventDate").val();
	var eventTime = $("#eventTime").val();
	var evtTime24fmt = "";

	if(typeof eventDate != "undefined"){
		var evtDte = new Date(eventDate);
		if(typeof eventTime != "undefined" && eventTime!=""){
			var hour = eventTime.split(":")[0];
			var min = eventTime.split(":")[1].split(" ")[0];
			var day = eventTime.split(":")[1].split(" ")[1];
			if(day == "AM"){
				if(hour == 12){ // early morning
					hour = 24;
				}
				evtTime24fmt = hour+":"+min;
			}else{
				if(hour <=12){
					if(hour == 12){ // noon
						hour = 12;
					}else{
						hour = +12 + +hour;
					}
					evtTime24fmt = hour +":"+min;
				}
			}
			evtTime = new Date(0,0,0,evtTime24fmt.split(":")[0],evtTime24fmt.split(":")[1],0,0).getTime();
		}
		
		//if it is a current date and future time    
		if(evtDte.getDate() == currDate.getDate() && evtDte.getYear() == currDate.getYear() && evtDte.getMonth() == currDate.getMonth()  && evtTime > currTimeStr){
			$("#docPastEvents").hide();
		}
		
		//if it is a future event hide it
		if(evtDte > currDate){
			$("#docPastEvents").hide();
		}
	}	
	
	
//calendarEventAppointmentLetter.jsp
//--------------------------------------------------
	//function processOther(theForm)
	$("#curIntRecordsInventoryIds").on('change',function(){
		var recordsInventory = "";
		var checker = true;
		$("#curIntRecordsInventoryIds option:selected").each(function(index, value){
			recordsInventory = $(this).val();
			if(recordsInventory == "OCO" || recordsInventory == "ODS")
			{
				$("#otherRow").show();
				$("#otherRowTitle").show();
				checker = false;
				return false;
			}
		});
		if(checker){
			$("#otherRow").hide();
			$("#otherRowTitle").hide();
			$("[name='currentInterview.otherInventoryRecords']").val("");
		}
	});
	if(document.title == "calendarEventAppointmentLetter.jsp")
	{
		$("#curIntRecordsInventoryIds").trigger('change');
	}
	//function validateFields(theForm){
	$("#").on('click',function(){
		var recordsInventoryValues = theForm["currentInterview.recordsInventoryIds"].value;		

		if (recordsInventoryValues == "")
		{
			alert("Please select a document for appointment");
			return false;
		}		

		if (document.getElementById("otherRow").className != "hidden")
		{			
			if (theForm["currentInterview.otherInventoryRecords"].value == "")
			{
				alert("Pleast enter the other records");
				return false;
			}
		}		
		return true;
	});
	
	
//
//calendarEventDetails.jsp 
//--------------------------------------------------	
	if(document.title == "JIMS2 Juvenile Casework - calendarEventDetails.jsp" || document.title == "JIMS2 Juvenile Casework - calendarEventList.jsp")//on load
	{
		if(typeof showCaseToggle != 'undefined')
		{
			showCaseToggle = 1;
			showCaseInfo();
		}
	}
	$("#evDetGuardianCheck").on('click',function(){
		if ($("#gihId").val() == "N")
		{
			var sel = confirm("'In-home' guardian information needed for printing is not present.  Do you want to continue?");
			return sel;
		}
	});
	//also used by calendarEventSPDetails.jsp
	$("#cedIO1").on('click',function(){
		var webapp = $(this).data("href");
		return changeFormActionURL(webapp, false) && disableSubmitButtonCasework($(this));
	});
	$("#cedIO2").on('click',function(){
		var webapp = $(this).data('href');
		return changeFormActionURL(webapp, false) && disableSubmitButtonCasework($(this));
	});
	
//calendarEvent.jsp
//--------------------------------------------------
	$("#schEvent").on('click',function(){
		$('form:first').attr('action',forwardURL);
		alert($('form:first').attr('action').val());
		//$('form:first').submit();	
	});
	
	$("#calEventCancel").on('click',function(){
		var caseFileId = $(this).data('nav');
		goNav(caseFileId);
	});
	
//calendarScheduleNewEvent.jsp
//--------------------------------------------------
	if(document.title == "JIMS2 Juvenile Casework - calendarScheduleNewEvent.jsp")//on load
	{
		showCaseToggle = 1;
		showCaseInfo();
	}
	$("#curSerServiceTypeCode").on('change',function(){	
		
		window.localStorage.clear();
		/*var startDate = $("#eventFromDate").val();
		var endDate = $("#eventToDate").val();*/
		if(typeof $("#eventFromDate") != "undefined" && typeof $("#eventToDate") != "undefined")
		{
			var startDate = $("#eventFromDate").val();
			var endDate = $("#eventToDate").val();
			if(startDate==''||endDate=='')
			{
				alert('From and To date is required.')
				$("#curSerServiceTypeCode").val('');
			}
			else
			{	
			//pass the dates here if entered and filter events in below action for those dates ids eventFromDate eventToDate				
				spinner();
				$('form').attr('action',"/JuvenileCasework/handleScheduleEvent.do?submitAction=Service Type Change&startDate="+startDate+"&endDate="+endDate);
				$('form').submit();	
			}
		}
		else
		{
			spinner();
			$('form').attr('action',"/JuvenileCasework/handleScheduleEvent.do?submitAction=Service Type Change");
			$('form').submit();	
		}
	});
	
//calendarScheduleNewInterviewEvent.jsp
//--------------------------------------------------	

	if(typeof $("#entryDate") != "undefined")
	{		
		datePickerSingle( $("#entryDate"),"Interview Date",false);
	}
	if(typeof $("#courtDate") != "undefined")
	{		
		datePickerSingle( $("#courtDate"),"Court Date",false);
	}
	if(typeof $("#endDate") != "undefined")
	{		
		datePickerSingle( $("#endDate"),"End by",false);		
	}
	//function submitServiceTypeChange(theForm){
		//used one from calendarScheduleNewEvent.jsp
	//textLimit
	$("#curSerCurEvntComments").on('mouseout keyup',function(){
		textLimit($(this),350);
	});	
	
	//function toggleRecurrenceTable( ){
	$("[name='currentService.currentEvent.recurringEvent']").on('click',function(){
		if ($("[name='currentService.currentEvent.recurringEvent']") != null)
		{	
			if($("[name='currentService.currentEvent.recurringEvent']:checked").val() == "true")
			{
				$("#recurrenceTable").show();
			}
			else
			{
				$("#recurrenceTable").hide();
			}
			$("#recurrencePattern").trigger('change');
		}		
	});
	if(typeof $("#cntrlRef") != "undefined" && $("#cntrlRef").val() != null && $("#cntrlRef").val() != "")
	{			
		var selectedRef = $("#cntrlRef").val().split('-');		
		if(selectedRef[1].trim()=="F1" || selectedRef[1].trim()=='F2' || selectedRef[1].trim()=="F3" || selectedRef[1].trim()=="JF" || selectedRef[1].trim()=="CF")		
			$("#eventDetails").text("Event Details - Appointment Letter - Felony ");	
		else
			$("#eventDetails").text("Event Details - Appointment Letter - Misdemeanor ");
		
	}
	$("#cntrlRef").on('change',function(){
		
		var selectedRef = $("#cntrlRef").val().split('-');		
		if(selectedRef[1].trim()=="F1" || selectedRef[1].trim()=='F2' || selectedRef[1].trim()=="F3" || selectedRef[1].trim()=="JF" || selectedRef[1].trim()=="CF")
			$("#eventDetails").text("Event Details - Appointment Letter - Felony ");
		else
			$("#eventDetails").text("Event Details - Appointment Letter - Misdemeanor ");
	});
	//function recurCallback(){
	$("#recurrencePattern").on('change',function(){
		var val =  $("option:selected",this).text();

		clearRecurRows() ;
		$("#rangeRow1").show();
		$("#rangeRow3").show();
		
		switch( val ) 
		{
			case "Daily" :
				$("#dailyRow1").show();
				$("#dailyRow2").show();		
				break ;
			case "Weekly" :
				$("#weeklyRow1").show();
				$("#weeklyRow2").show();
				break ;
			case "Monthly" :
				$("#monthlyRow1").show();
				$("#monthlyRow2").show();
				break ;
			case "Yearly" :
				$("#yearlyRow1").show();
				$("#yearlyRow2").show();
				break ;
			default:
				clearRecurRows() ;
				break ;									
		}
	});
	function clearRecurRows()
	{
		$("#dailyRow1").hide();
		$("#dailyRow2").hide();
		$("#weeklyRow1").hide();
		$("#weeklyRow2").hide();
		$("#monthlyRow1").hide();
		$("#monthlyRow2").hide();
		$("#yearlyRow1").hide();
		$("#yearlyRow2").hide();
		$("#rangeRow1").hide();
		$("#rangeRow3").hide();
	}
	//function checkInterviewLocation(theForm){
	$("#serviceLocationId").on('change',function(){
		var interviewLocation = $("[name='currentService.locationId']");
		var tText =  $("option:selected",interviewLocation).val();
		if( tText == '-1' )
			$("#newAddressSection").show();
		else
		{
			$("#newAddressSection").hide();
			$("[name='currentService.newAddress.streetNum']").val("");
			$("[name='currentService.newAddress.streetNumSuffixCode']").val("");
			$("[name='currentService.newAddress.streetName']").val("");
			$("[name='currentService.newAddress.aptNum']").val("");
			$("[name='currentService.newAddress.city']").val("");
			$("[name='currentService.newAddress.zipCode']").val("");
			$("[name='currentService.newAddress.additionalZipCode']").val("");
			$("[name='currentService.newAddress.streetTypeCode']").val("");
			$("[name='currentService.newAddress.stateCode']").val("");
			$("[name='currentService.newAddress.addressTypeCode']").val("");
			$("[name='currentService.newAddress.countyCode']").val("");
		}
	});
	
	if(document.title=="JIMS2 Juvenile Casework - calendarScheduleNewInterviewEvent.jsp")
	{	
		$("[name='currentService.currentEvent.recurringEvent']:checked").trigger('click');
		if(serviceTypeCategory == "I")
		{
			$("serviceLocationId").trigger('change');
		}
	}
	
	//function customValidation(theForm){		
	$("#intValidateNext").on('click',function(){
		isValid = false;
		
		if(serviceTypeCategory == "P"){
			isValid=validatePreScheduled()
		}

		if(serviceTypeCategory == "N"){ 
			isValid = validateDateTime() 
			if(serviceTypeCode == "HDV"){				
				if (isValid)
				{
					isValid = validateHome();
				}
			}	

			if(serviceTypeCode == "CVS"){
				if (isValid)
				{
					isValid = validateSchool();
				}	
			}

			if(serviceTypeCode != "CVS" && serviceTypeCode != "HDV"){	
	  			if (isValid)
	  			{
	  				isValid = validateLocation();			
	  			}
			} 
			if (isValid)
			{
				isValid = validateRecurringEvent();
			}
		}

		if(serviceTypeCategory == "I"){
			isValid = validateDateTime();
			if (isValid)
			{
				isValid = validateInterview();
			}
			if (isValid )
			{
				isValid = validateRecurringEvent();
			}
		}		
		return isValid;			
	});
	function validatePreScheduled()
	{
		if ($("[name='uniqueId']") == "")
		{
			alert("Select at least one prescheduled event");		
			return false;
		}
		return true;	
	}
	function validateDateTime()
	{	
		var eventTime = $("[name='currentService.currentEvent.eventTime']");
		var eventDate = $("[name='currentService.currentEvent.eventDateStr']");
		var theForm = document.scheduleNewEventForm;

		if ($(eventDate).val() == "")
		{
			alert("Please enter Interview Date");
			$(eventDate).focus();
			return false;
		}
		else if ($(eventTime).val() == "")
		{
			alert("Please enter Interview Time");
			$(eventTime).focus();
			return false;
		}
		clearAllValArrays();
		customValRequired("currentService.currentEvent.eventDateStr", "Event Date is required.");
		addMMDDYYYYDateValidation("currentService.currentEvent.eventDateStr","Event Date must be a date ex: 04/04/2000");
		customValMaxLength('currentService.currentEvent.comments','Comments cannot be longer than 350 characters',350);
		addDB2FreeTextValidation('currentService.currentEvent.comments',"Comments must be alphanumeric with no leading spaces and the following symbols . '  & ; ,  ( ) / along with spaces are allowed.",'');
		if(! validateCustomStrutsBasedJS(theForm))
		{
			return false;
		}

		var scheduledDate = new Date(eventDate.value);
		var eventTimes = $(eventTime).val().split(':');
		var eventHrStr = eventTimes[0];
		var eventMinStr = eventTimes[1].split(' ')[0];
		var eventAMPM = eventTimes[1].split(' ')[1];

		var eventHr;
		var eventMin;	

		if(eventHrStr.charAt(0) == '0') {
			eventHr = parseInt(eventHrStr.charAt(1));
		}
		else
		{
			eventHr = parseInt(eventHrStr);
		}
			
		if(eventMinStr.charAt(0) == '0')
			eventMin = parseInt(eventMinStr.charAt(1));
		else
			eventMin = parseInt(eventMinStr);
		
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

		if( scheduledDate < today)
		{
	     alert("Event date/time cannot be in the past. (Now: " + today + " Event:" + scheduledDate +")" );
		   eventTime.focus();
		   return false;
		}	
		
		return true;
	} 
	function validateHome()
	{
		if ( $("[name='currentService.memberAddressId']").val() == "")
		{		
			alert("Please select a Member Location");
			$("[name='currentService.memberAddressId']").focus();
			return false;
		}
		return true;
	}
	function validateSchool()
	{
		if ( $("[name='currentService.schoolDistrictId']").val() == "")
		{		
			alert("Please select a School District");
			$("[name='currentService.schoolDistrictId']").focus();
			return false;
		}
	  else if ( $("[name=currentService.schoolId']").val() == "")
		{
			alert("Please select a School");
			$("[name=currentService.schoolId']").focus();
			return false;
		}	

		return true;
	}
	function validateLocation()
	{	
		if ( $("[name='currentService.locationId']").val() == "")
		{
			alert("Please select a Location Unit");
			$("[name='currentService.locationId']").focus();
			return false;
		}	
		return true;
	}
	function validateInterview()
	{	
		if ( $("[name='currentService.selectedIntervieweeArray']").val() <= "")
		{
			alert("Please select at least one Interviewee");
			$("[name='currentService.selectedIntervieweeArray']").focus();
			return false;
		}
		
		if ( $("[name='currentService.interviewTypeId']").val() == "")
		{
			alert("Please select Interview Type");
			$("[name='currentService.interviewTypeId']").focus();		
			return false;
		}		
		
		if ( $("[name='currentService.locationId']").val() == "-1")
		{
			return validateAddress('scheduleNewEventForm','', 'currentService.newAddress');
		}	
		return validateLocation();	
	}
	function validateRecurringEvent()
	{	
		var theForm = document.scheduleNewEventForm;
		clearAllValArrays();
		var recEvent = $("[name='currentService.currentEvent.recurringEvent']:checked").val();
		if (recEvent == "true")
		{			
			var val =  $("option:selected","[name='currentService.currentEvent.recurrencePattern']").text();			

			if ($("[name='currentService.currentEvent.recurrencePattern']").val() == "")
			{
				alert("Please select a recurrence pattern");
				return false;
			}					

		    if ($("[name='currentService.currentEvent.frequencyRadio']:checked").val() == "true")
		    {	
		    	clearAllValArrays();
		    	customValRequired("currentService.currentEvent.recurrenceFrequency", "End after ocurrences is required");			
				addNumericValidation("currentService.currentEvent.recurrenceFrequency","End after ocurrences must be numeric");
				if(!validateCustomStrutsBasedJS(theForm))
				{
					return false;
				}
				var myNumOfOccurrences=$("[name='currentService.currentEvent.recurrenceFrequency']").val();
				if(myNumOfOccurrences > 60)
				{
					alert("End after ocurrences cannot be greater than 60.");
					$("[name='currentService.currentEvent.recurrenceFrequency']").focus();
					return false;
				}
		    }
		    
			if ($("[name='currentService.currentEvent.frequencyRadio']:checked").val() == "false")
			{
				clearAllValArrays();
				customValRequired("currentService.currentEvent.recurrenceEndDate", "End by is required");			
				addMMDDYYYYDateValidation("currentService.currentEvent.recurrenceEndDate","End by must be in date format ex: 04/04/2000")		
				if(!validateCustomStrutsBasedJS(theForm))
				{
					return false;
				}	
				var startDate = new Date($("[name='currentService.currentEvent.eventDateStr']").val());
				var endDate = new Date($("[name='currentService.currentEvent.recurrenceEndDate']").val());
				
				if (endDate <= startDate) 
				{
					alert("End date cannot occur before start date");
					$("[name='currentService.currentEvent.recurrenceEndDate']").focus();
					return false;
				}	

				var eventDate =$("[name='currentService.currentEvent.eventDateStr']").val();
				var oneYearFromStartDate=new Date(eventDate);
				oneYearFromStartDate.setFullYear(oneYearFromStartDate.getFullYear() + 1);
				if(endDate>oneYearFromStartDate)
				{
					alert("End date cannot be more than one year from start date.");
					$("[name='currentService.currentEvent.recurrenceEndDate']").focus();
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
	function validateDailyRecurrence(theForm)
	{
		if ($("[name='currentService.currentEvent.dailyRadio']").first().prop("checked"))
		{
			clearAllValArrays();
		    customValRequired("currentService.currentEvent.dailyRecurrenceInterval", "Recurrence Interval is required");
		    return validateCustomStrutsBasedJS(theForm);
		}
	 
		return true;
	}
	function validateWeeklyRecurrence(theForm)
	{
		clearAllValArrays();
		customValRequired("currentService.currentEvent.weeklyRecurrenceInterval", "Recurrence Interval is required");			
		addNumericValidation("currentService.currentEvent.weeklyRecurrenceInterval","Recurrence Interval must be numeric");
		if(! validateCustomStrutsBasedJS(theForm))
		{
		 	return false;
		}
	 
		var dayList = $("[name='currentService.currentEvent.weeklyDay']:checked");
		if(dayList.length > 0)
		{
			return true;
		}
		alert("Please select a week day in order to proceed");

		return false;
	}
	function validateMonthlyRecurrence(theForm)
	{
		if ($("[name='currentService.currentEvent.monthlyRadio']:checked").val() == "true")
		{
			clearAllValArrays();
			customValRequired("currentService.currentEvent.monthlyDay", "Day of month is required");			
			addNumericValidation("currentService.currentEvent.monthlyDay","Day of month must be numeric");
			if(! validateCustomStrutsBasedJS(theForm))
			{
			 	return false;
			}

			clearAllValArrays();
			customValRequired("currentService.currentEvent.monthlyRecurrenceInterval1", "Interval is required");			
			addNumericValidation("currentService.currentEvent.monthlyRecurrenceInterval1","Interval must be numeric");
			 if(! validateCustomStrutsBasedJS(theForm))
			 {
			 	return false;
			 }
		 
		 } 
		 else if($("[name='currentService.currentEvent.monthlyRadio']:checked").val() == "false")
		 {
			 clearAllValArrays();
			customValRequired("currentService.currentEvent.monthlyRecurrenceInterval2", "Interval is required");			
			addNumericValidation("currentService.currentEvent.monthlyRecurrenceInterval2","Interval must be numeric");
			return validateCustomStrutsBasedJS(theForm);
		 }
	}
	function validateYearlyRecurrence(theForm)
	{
		if ($("[name='currentService.currentEvent.yearlyRadio']:checked").val() == "true")
		{
			var day = $("[name='currentService.currentEvent.yearlyDay']").val();
			var month = $("[name='currentService.currentEvent.yearlyMonthNumber']").val();
			
				
			clearAllValArrays();
			customValRequired("currentService.currentEvent.yearlyDay", "Day is required");			
			addNumericValidation("currentService.currentEvent.yearlyDay","Day must be numeric");
			if(! validateCustomStrutsBasedJS(theForm))
			{
				return false;
			}

			if (day < 1 || day > 31)
			{
				alert("Day must be between 1 and 31.");
				$("[name='currentService.currentEvent.yearlyDay']").focus();
				return false;
			}
			else if( (month == 3 || month == 5 || month == 8 || month == 10) && day == 31) 
			{
				alert("Month does not have 31 days!")
				$("[name='currentService.currentEvent.yearlyDay']").focus();
				return false
			}
			else if (month == 1 && day > 28) 
			{ 
				alert("February doesn't have " + day + " days!");
				$("[name='currentService.currentEvent.yearlyDay']").focus();
				return false;
			}
		}
		return true;
	}  


//calendarScheduleNewNonInterviewEvent.jsp
//--------------------------------------------------	
	//calendar
		//reused from page above
	//function submitServiceTypeChange(theForm)
		//used one from calendarScheduleNewEvent.jsp
	//textLimit
	$("#curSerCurEvntCommentsNONINT").on('mouseout keyup',function(){
		textLimit($(this),350);
	});
	if(document.title=="JIMS2 Juvenile Casework - calendarScheduleNewNonInterviewEvent.jsp")
	{	
		//alert("reloadPage....");
		$("[name='currentService.currentEvent.recurringEvent']:checked").trigger('click');
			
		if (serviceTypeCode == "SAN"){ 
			if ($("[name='currentService.sexOffenderRegistrant']:checked").val() == "true")
			{
				$("#restrictionRow").show();
			}
			wpnIdxStr = $("[name='weaponInvolvedStr']");
			var weaponInvolved = "";
			var typeOfWeaponId = "";
			totOffenses = wpnIdxStr.length;
			for (x =0; x<totOffenses; x++){
				weaponInvolved = "offs[" + x + "].weaponInvolved";	
				if (document.getElementsByName(weaponInvolved)[0].checked)
				{
					fld = "weaponRow" + x;
					show(fld, 1);
					typeOfWeaponId = "offs[" + x + "].typeOfWeaponId";
					fld = document.getElementsByName(typeOfWeaponId)[0];
					if (fld.options[fld.selectedIndex].value == "OTH"){
						fld = "weaponDescRow" + x;
						show(fld,1);
					}
				}
			}
		}
	}
	//function toggleRestrictionRow( )
	$("[name='currentService.sexOffenderRegistrant']").on('click',function(){
		$("#restrictionId").val("");
		if ($("[name='currentService.sexOffenderRegistrant']:checked").val() == "true")
		{
			$("#restrictionRow").show();
			$("#restrictionRow").focus();
		} else {
			$("#restrictionRow").hide();
		}
	});
	
	//function toggleWeaponRow(idx){
	$("[name*='weaponInvolved']:radio").on('click',function(){
		var idx = $(this).closest('td').data('idx');
		var typeOfWeaponId = "offs[" + idx + "].typeOfWeaponId";
		var weaponDescId = "weaponDescId" + idx;
		var weaponDescRow = "weaponDescRow" + idx;
		var weaponInvolved = "offs[" + idx + "].weaponInvolved";
		var weaponRow = "weaponRow" + idx;
		document.getElementsByName(typeOfWeaponId)[0].selectedIndex = 0;
		$("#"+weaponDescId).val("");
		$("#"+weaponDescRow).className = "hidden";
		if (document.getElementsByName(weaponInvolved)[0].checked)
		{
			$("#"+weaponRow).show();
			$("#"+weaponRow).focus();
		} else {
			$("#"+weaponRow).hide();
		}	
	});

	//function toggleWeaponDescRow(el,idx ){//el = this, idx = index
	$("[name*='typeOfWeaponId']").on('change',function(){
		var idx = $(this).closest('td').data('idx');
		var weaponDescId = "weaponDescId" + idx;
		var weaponDescRow = "weaponDescRow" + idx;
		$("#"+weaponDescId).val("");
		if ($(this).val() == "OTH")
		{
			$("#"+weaponDescRow).show();
			$("#"+weaponDescId).focus();
		} else {
			$("#"+weaponDescRow).hide();
		}
	});
	
	//function customValidation(theForm)
	$("#nonIntValidateNext").on('click',function(){
		
		var isValid = false;
		
		if(serviceTypeCategory == "P"){
			isValid = validatePreScheduled();
		}
		if(serviceTypeCategory == "N"){
			if(serviceTypeCode !=  "APL")
				isValid = validateDateTimeNonInt();
			else
				isValid=true;
			if(serviceTypeCode ==  "HDS"){				
				if (isValid)
				{
					isValid = validateHome();
				}
			}	
			if(serviceTypeCode ==  "JOB"){			
				if (isValid)
				{
					isValid = validateJobLocation();
				}
			}	
			if(serviceTypeCode ==  "HDV"){			
				if (isValid)
				{
					isValid = validateHome();
				}
			}	
			if(serviceTypeCode ==  "CVI"){			
				if (isValid)
				{
					isValid = validateHome();
				}
			}
			if(serviceTypeCode ==  "CVF"){			
				if (isValid)
				{
					isValid = validateHome();
				}
			}
			if(serviceTypeCode ==  "CFP"){			
				if (isValid)
				{
					isValid = validateHome();
				}
			}
			if(serviceTypeCode ==  "CVS"){	
				if (isValid)
				{
					isValid = validateSchoolNonInt();
				}	
			}
			if(serviceTypeCode ==  "SAN"){	
			if (isValid)
				{
					isValid = validateSchoolAdjudication();
				}	
			}
			if(serviceTypeCode ==  "APL"){					
				if (isValid)
					{
					isValid = validateApptLetter();
					}
			}
			if(serviceTypeCode ==  "PVT"){	
				if (isValid)
				{
					isValid = validateLocationForPlacement();
				}	
			}
			if(serviceTypeCode ==  "FPO"){	
				if (isValid)
				{
					isValid = validateLocationForPlacement();
				}
			}

	 		if(serviceTypeCode != "PVT" && serviceTypeCode != "JOB" && serviceTypeCode != "HDS" && serviceTypeCode != "HDV" && serviceTypeCode != "CVI"
	 			 && serviceTypeCode != "CVF" && serviceTypeCode != "CFP" && serviceTypeCode != "CVS" && serviceTypeCode != "SAN" && serviceTypeCode != "FPO"){
			    if(isValid)
	  			{
		 			isValid = validateLocation();
	  			}
			}
	 		if(serviceTypeCode != "SAN"){
	 			if (isValid)
				{
					isValid = validateRecurringEvent();
				}
	 		}	
		}

		if(serviceTypeCategory == "I"){
			isValid = validateDateTimeNonInt() 
			if (isValid)
			{
				isValid = validateInterview();
			}
			if (isValid )
		  {							
				isValid = validateRecurringEvent();
			}
		}			
		return isValid;	
	});
	function validateJobLocation()
	{	
		if ($("[name='currentService.location']").val() == "")
		{
			alert("Please select a Member Location.");
			$("[name='currentService.location']").focus();
			return false;
		}	

		return true;
	}
	function validateLocationForPlacement()
	{	
		if ($("[name='currentService.facilityId']").val() == "")
		{
			alert("Please select a Location Unit.");
			$("[name='currentService.facilityId']").focus();
			return false;
		}	

		return true;
	}
	function validateSchoolNonInt()
	{	
		if (!validateSchoolName()) 
		{
			return false;
		}

		return true;
	}
	function validateSchoolName()
	{	
		var stc = $("[name='currentService.serviceTypeCode']").val();
		var schoolIds = $("[name='currentService.schoolId']");
		var theIndex = 0;
		if (stc=="SAN"){
			theIndex = 1;
		}
		if (schoolIds[theIndex].selectedIndex == 0){
			alert("Please select a School Name.");
			schoolIds[theIndex].focus();
			return false;
		}		

		return true;
	}
	function validateApptLetter()
	{		
		var cntrlReferral = $("#cntrlRef").val();
		var courtDate = $("#courtDate").val();
		var etime = $("[name='currentService.currentEvent.eventTime']");
		var theForm = document.scheduleNewEventForm;
		//var edate = $("[name='currentService.currentEvent.eventDateStr']");
		var message="";
		if($("#cntrlRef").val()=="")
		{
			alert("Please select Controlling Referral.");	
			$("#cntrlRef").focus();
			return false;
		}
		if($("#courtDate").val() == "")
		{
			alert("Please select Court Date.");	
			$("#courtDate").focus();
			return false;
		}
		if($("#courtTime").val() == "")
		{
			alert("Please select Court Time.");	
			$("#courtTime").focus();
			return false;
		}
		if($("#officerHours").val() == "")
		{
			alert("Please enter officer work hours.");	
			$("#officerHours").focus();
			return false;
		}
		var officerPhoneAreaCode = $("#officerPhoneAreaCode").val();
	   	var officerPhonePrefix = $("#officerPhonePrefix").val();
	   	var officerPhone4Digit = $("#officerPhoneMain").val();
	   var officerPhoneExtn = $("#officerPhoneExtn").val();
		var faxAreaCode = $("#faxAreaCode").val();
	   	var faxPrefix = $("#faxPrefix").val();
	   	var faxMain = $("#faxMain").val();
		if(officerPhoneAreaCode=="" && officerPhonePrefix=="" && officerPhone4Digit=="")
		{
			alert("Officer Phone number is required.");
			$("#officerPhoneAreaCode").focus();
		    return false;
		}
		// future date/time check 		
		/*var hmp = $(etime).val().split(" ");
		var hrmin = hmp[0].split(":"); 
		var hrs = parseInt(hrmin[0]);
		if (hmp[1] == "PM"){
			if(hrs != 12) 
			{
				hrs = hrs + 12;
			}
		}	
		else if(hrs == 12) 
		{
			hrs = 0; 
		}

		hmp[0] = hrs + ":" + hrmin[1];
		var dt = courtDate + " " + hmp[0];
		var inputDateTime = new Date(dt);
		var curDateTime = new Date();
		if (inputDateTime > curDateTime){
			alert("Court Date and Time cannot be future value.");
			$(etime).focus();
			return false;			
		}*/
	
	 
	   
		if(!(validatePhone("officerPhoneAreaCode","officerPhonePrefix","officerPhoneMain","officerPhoneExtn","Officer Phone", document.forms[0]))){
			return false;
		}
		if(!(validatePhone("faxAreaCode","faxPrefix","faxMain","","Officer Fax", document.forms[0]))){
			return false;
		}
		
		if(!$.isNumeric(officerPhoneAreaCode))
		{
			alert("Phone number should be numeric.");
			$("#officerPhoneAreaCode").focus();
			return false;
		}
		else if(officerPhoneAreaCode.length <3)
		{
			alert("Area code cannot be less than 3 characters.");
			$("#officerPhoneAreaCode").focus();
			return false;
		}
		
		
		if(!$.isNumeric(officerPhonePrefix))
		{
			alert("Phone number should be numeric.");
			$("#officerPhonePrefix").focus();
			return false;
		}
		else if(officerPhonePrefix.length <3)
		{
			alert("Phone Prefix cannot be less than 3 characters.");
			$("#officerPhonePrefix").focus();
			return false;
		}
		
		if(!$.isNumeric(officerPhone4Digit))
		{
			alert("Phone number should be numeric.");
			$("#officerPhone4Digit").focus();
			return false;
		}
		else if(officerPhone4Digit.length <4)
		{
			alert("Phone main cannot be less than 4 characters.");
			$("#officerPhone4Digit").focus();
			return false;
		}
		
		if(officerPhoneExtn !="" && !$.isNumeric(officerPhoneExtn))
		{
			alert("Phone Extension should be numeric.");
			$("#officerPhoneExtn").focus();
			return false;
		}		
		if(faxAreaCode!="")
		{
			if(!$.isNumeric(faxAreaCode))
			{
				alert("Fax number should be numeric.");
				$("#faxAreaCode").focus();
				return false;
			}
			else if(faxAreaCode.length <3)
			{
				alert("Fax Area code cannot be less than 3 characters.");
				$("#faxAreaCode").focus();
				return false;
			}
		}
		if(faxPrefix!="")
		{
			if(!$.isNumeric(faxPrefix))
			{
				alert("Fax number should be numeric.");
				$("#faxPrefix").focus();
				return false;
			}
			else if(faxPrefix.length <3)
			{
				alert("Fax Prefix cannot be less than 3 characters.");
				$("#faxPrefix").focus();
				return false;
			}
		}
		if(faxMain!="")
		{
			if(!$.isNumeric(faxMain))
			{
				alert("Fax number should be numeric.");
				$("#faxMain").focus();
				return false;
			}
			else if(faxMain.length <4)
			{
				alert("Fax main cannot be less than 4 characters.");
				$("#faxMain").focus();
				return false;
			}
		}
		
		
		return true;
		
	}
	function validateSchoolAdjudication()
	{	
		var stc = $("[name='currentService.serviceTypeCode']").val();
		var schoolIds = $("[name='currentService.schoolId']");
		var theIndex = 0;
		if (stc!="SAN"){
			theIndex = 1;
		}
		if (schoolIds[theIndex].selectedIndex == 0){
			alert("Please select a School Name.");
			schoolIds[theIndex].focus();
			return false;
		}	

		if ( $("[name='currentService.contactLastName']").val() == "")
		{
			alert("Please enter a Contact Name");
			$("[name='currentService.contactLastName']").focus();
			return false;
		}
		var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
		var fld = "";
		$("#sexOffenderRegistrantStrId").val("N");
		if ($("[name='currentService.sexOffenderRegistrant']:checked").val() == "true")
		{
			fld = trim($("#restrictionId").val());
			$("#restrictionId").val(fld);
			if (fld == "")
			{
				alert("Restriction/Other is required.");
				$("#restrictionId").focus();
				return false;
			}
			if (alphaNumericRegExp.test(fld,alphaNumericRegExp) == false) {
				alert("Restriction/Other contains invalid character(s), only alphanumeric values allowed.");
				$("#restrictionId").focus();
				return false;
			}
			$("#sexOffenderRegistrantStrId").val("Y");
		}	
		// hidden fields
		wpnIdxStr = $("[name='weaponInvolvedStr']");
		toWpnId = $("[name='typeOfWeaponId']");
		typeWpnDesc =  $("[name='typeOfWeaponDescription']");
		wpnDesc = $("[name='weaponDescription']");
		var weaponInvolved = "";
		var typeOfWeaponId = "";
		var weaponDesc = "";
		var fld2 = "";
		
		totOffenses = wpnIdxStr.length;
		for (x =0; x<totOffenses; x++){
			wpnIdxStr[x].value = " ";
			toWpnId[x].value = " ";
			typeWpnDesc[x].value = " ";
			wpnDesc[x].value = " ";
			weaponInvolved = "offs[" + x + "].weaponInvolved";
			if (document.getElementsByName(weaponInvolved)[0].checked)
			{
				wpnIdxStr[x].value = "Y";
				typeOfWeaponId = "offs[" + x + "].typeOfWeaponId";
				fld = document.getElementsByName(typeOfWeaponId);
				if (fld[0].selectedIndex == 0)
				{	
					alert("Type of Weapon selection is required.");
					fld[0].focus();
					return false;
				}
				toWpnId[x].value = fld[0].options[fld[0].selectedIndex].value;
				weaponDesc = "offs[" + x + "].weaponDescription";
				if (fld[0].options[fld[0].selectedIndex].value == "OTH")
				{
					fld2 = trim(document.getElementsByName(weaponDesc)[0].value);
					document.getElementsByName(weaponDesc)[0].value = fld2;
					if (fld2 == "")
					{
						alert("Weapon Description is required.");
						document.getElementsByName(weaponDesc)[0].focus();
						return false;
					}
					if (alphaNumericRegExp.test(fld2,alphaNumericRegExp) == false) {
						alert("Weapon Description contains invalid character(s), only alphanumeric values allowed.");
						document.getElementsByName(weaponDesc)[0].focus();
						return false;
					}
					wpnDesc[x].value = document.getElementsByName(weaponDesc)[0].value;
					typeWpnDesc[x].value = wpnDesc[x].value;
				} else {
					wpnDesc[x].value = fld[0].options[fld[0].selectedIndex].text;
					typeWpnDesc[x].value = wpnDesc[x].value;
				}
			} else {
				wpnIdxStr[x].value = "N";
			}	
		}
		return true;
	}
	function validateDateTimeNonInt()
	{	
		var theIndex = 0;
		var theForm = document.scheduleNewEventForm;
		var stc = $("[name='currentService.serviceTypeCode']").val();
		var etime = $("[name='currentService.currentEvent.eventTime']");
		var edate = $("[name='currentService.currentEvent.eventDateStr']");

		if($(edate).val() == "")
		{
			alert("Please enter Date of Contact.");
			$(edate).focus();
			return false;
		}
		if($(etime).val() == "")
		{
			alert("Please select a Contact Time.");
			$(etime).focus();
			return false;
		}

		// future date check only for this service type
		if (stc == "SAN"){
			var hmp = $(etime).val().split(" ");
			var hrmin = hmp[0].split(":"); 
			var hrs = parseInt(hrmin[0]);
			if (hmp[1] == "PM"){
				if(hrs != 12) 
				{
					hrs = hrs + 12;
				}
			}	
			else if(hrs == 12) 
			{
				hrs = 0; 
			}

			hmp[0] = hrs + ":" + hrmin[1];
			var dt = $(edate).val() + " " + hmp[0];
			var inputDateTime = new Date(dt);
			var curDateTime = new Date();
			if (inputDateTime > curDateTime){
				alert("Date and Time of Contact can not be future value.");
				$(edate).focus();
				return false;			
			} 
		}
		
		clearAllValArrays();
//		customValRequired("currentService.currentEvent.eventDateStr", "Interview Date is required.");
//		addMMDDYYYYDateValidation("currentService.currentEvent.eventDateStr","Interview Date must be a date ex: 04/04/2000");
		customValMaxLength('currentService.currentEvent.comments','Comments cannot be longer than 350 characters',350);
		addDB2FreeTextValidation('currentService.currentEvent.comments',"Comments must be alphanumeric with no leading spaces and the following symbols . '  & ; ,  ( ) / along with spaces are allowed.",'');


		if(! validateCustomStrutsBasedJS(theForm))
		{
			return false;
		}

		var scheduledDate = new Date($(edate).val());
		var eventTimes = $(etime).val().split(':');
		
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

		/* **************
		   The follow javascript can be uncommented if it is deemed 
		   that back dates are now allowed for non-interviews
		   
		if( scheduledDate < today)
		{
	    alert("Event date/time cannot be in the past. (Now: " + today + " Event:" + scheduledDate +")" );
		  eventTime.focus();
		  return false;
		}
		**************  */	

		return true;
	}  

//calendarScheduleNewPreScheduledEvent.jsp
//--------------------------------------------------	
	//function submitServiceTypeChange(theForm){
		//used one from calendarScheduleNewEvent.jsp
	//function customValidation(theForm)
	$("#preSchedCustomValidation").on('click',function(){
		clearAllValArrays();
		var theForm = document.forms[0];
		var isValid = validateCustomStrutsBasedJS(theForm);
		
		if(isValid){
			spinner();
		}
		
		return isValid;	
	});
	
//calendarScheduleSPEvents.jsp
//--------------------------------------------------	
	if(typeof $("#eventFromDate") != "undefined" && typeof $("#eventToDate") != "undefined")
	{		
		datePickerRange( $("#eventFromDate"),$("#eventToDate"),"From Date","To Date");		
	}
	if(document.title == "JIMS2 Juvenile Casework - calendarScheduleSPEvents.jsp")//on load
	{
		showCaseToggle = 1;
		showCaseInfo();
		
		//function setSelect()
		var selectedId = $("#uniqueId").val();
		if (selectedId != null && selectedId > "")
		{
			var str = $("[name='spanName']").val();
			if($("[name='selectedServiceId'][value="+selectedId+"]"));
			{
				$("[name='selectedServiceId'][value="+selectedId+"]").prop('checked',true);
				showHideExpand(str, 'row', /JuvenileCasework/);
			}				
		}
	}
	//function getServiceProviderProgramInfo(theForm){	
	$("#serviceProviderId").on('change',function(){
		spinner();
		$('form').attr('action',"/JuvenileCasework/displayCalendarSPSelectEvents.do?submitAction=View Programs");
		$('form').submit();	
	});
	$("#nextNoCheck").on('click',function(){
		 return validationInput(this.form, 'noCheck') && disableSubmitButtonCasework($(this));
	});
	$("#nextCheck").on('click',function(){
		return validationInput(this.form, 'check') && disableSubmitButtonCasework($(this));
	});
	function validationInput(theForm, str)
	{
		var result;
		if ($("#serviceProviderId").val() == "")
		{
			alert("Service Provider selection is required.");
			$("#serviceProviderId").focus();
			return false;				
		}
		var rb = $("[name='selectedServiceId']");
		var rbChecked = false;
		if ($(rb).length == 0)
		{
			alert("The selected Service Provider currently has no assigned programs.");
			$("[name='selectedServiceId']").focus();
			return false;			
		}
		
		if($("[name='selectedServiceId']:checked").val())
		{
			rbChecked = true;
		}
		if (rbChecked == false)
		{
			alert("A Program service selection is required.");
			$("[name='selectedServiceId']").focus();
			return false;
		}
		if ($("#eventFromDate").val() > "")
		{
			result = validateDate($("#eventFromDate").val(), "From Date", true);
			if (result > "")
			{
				alert(result);
				theForm.eventFromDate.focus();
				return false;
			}
			var dt = $("#eventFromDate").val() + " 23:59";
			var fldDateTime = new Date(dt);
			var todaysDateTimeLess7 = new Date();
			todaysDateTimeLess7.setDate(todaysDateTimeLess7.getDate() - 7);
			if (str=="check" && todaysDateTimeLess7 > fldDateTime ){
				alert("From Date can not be more than 7 days previous.");
				$("#eventFromDate").focus();
				return false;			
			} 
					
		}
		if ($("#eventToDate").val() > "")
		{
			result = validateDate($("#eventToDate").val(), "From Date", false);
			if (result > "")
			{
				alert(result);
				$("#eventToDate").focus();
				return false;
			}		
		}	
	// From and To date validated at this point								
		if ($("#eventToDate").val() > "") 
		{
			if($("#eventFromDate").val() == "")
			{
			alert("From date required whenever 'To' date is present.");
			$("#eventFromDate").focus();
			return false;			
			}
			var fromdt = $("#eventFromDate").val() + " 23:59";
			var fromDateTime = new Date(fromdt);
			var todt = $("#eventToDate").val() + " 23:59";
			var toDateTime = new Date(todt);
			if (fromDateTime > toDateTime)
			{
				alert("To Date must same or greater than From Date value.");
				$("#eventToDate").focus();
				return false;
			}		
		}
		
		//everything checked out so start the spinner
		spinner();
		
		return true;
	}
	function validateDate(fldValue, fldName, futureDateEdit)
	{
		var msg = "";
		var numericRegExp = /^[0-9]*$/;
		if (fldValue == "")
		{
			msg = fldName + " is required.\n";
			return msg;
		}
		if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
		{
			msg = fldName + " must be in the MM/DD/YYYY format.\n";
			return msg;
		}
		var dValues = fldValue.split('/');
		var month = dValues[0];
		var day = dValues[1];
		var year = dValues[2];

		if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
			msg = fldName + " is not a valid date.\n";
			return msg;	
		} 

		if (month.length != 2 || day.length != 2 || year.length != 4) {
			msg = fldName + " must be in the MM/DD/YYYY format.\n";
			return msg;	
		} 

	    if (month < 1 || month > 12)
	    {
			msg = fldName + " is not valid.\n";
			return msg;		
	    }
	    if (day < 1 || day > 31) {
			msg = fldName + " is not valid.\n";
			return msg;		
	    }
	    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
	    {
			msg = fldName + " is not valid.\n";
			return msg;	
	    }
	    if (month == 2) {
	        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	        if (day > 29 || (day == 29 && !leap)) {
				msg = fldName + " is not valid.\n";
				return msg;	
	        }
	    }    
	    if (futureDateEdit && msg == ""){
			var dt = fldValue + " 00:00";
			var fldDateTime = new Date(dt);
			var curDateTime = new Date();
			if (fldDateTime > curDateTime){
				msg = fldName + " can not be future value.\n";
				return msg;				
			}    	
	    }
	 	return msg;
	}
	
//calendarScheduleSPSelectEvents.jsp
//--------------------------------------------------	
	if(document.title == "JIMS2 Juvenile Casework - calendarScheduleSPSelectEvents.jsp")
	{
		var selects = $("[name='selectedEventIds']");
		if (selects.length == 1)
		{
			$(selects).first().prop('checked',true);
		}
		if (selects.length > 1)
		{	
			var ids = $("[name='uniqueIds']");
			for (x=0; x<selects.length; x++)
			{
				for (y=0; y<ids.length; y++)
				{
					if (selects[x].value == ids[y].value)
					{
						selects[x].checked = true;
						break;
					}		
				}		
			}
		}
	}
	//function selectAllEvents(el){
	$("#selectAllEvents").on('click',function(){
		if($(this).prop('checked'))
		{
			$("[name='selectedEventIds']").prop('checked',true);
		}
		else
		{
			$("[name='selectedEventIds']").prop('checked',false);
		}
	});
	//function checkSelect(el){
	$("[name='selectedEventIds']").on('click',function(){
		if ($(this).prop('checked') == false)
		{	
			$("#selectAllEvents").prop('checked',false);
		}	
	});
	//function validationSelect(theForm){
	$("#validationSPSelect").on('click',function(){
		if($("[name='selectedEventIds']:checked").val())
		{
			return true;
		}
		alert("At least 1 event must be selected.");
		return false;
	});
	document.title == "JIMS2 Juvenile Casework - calendarEventDetails.jsp"
		var expandStat="";
	if(document.title == "JIMS2 Juvenile Casework - calendarScheduleNewPrescheduledEvent.jsp") //on load
	{		
		var retrievedData1 = localStorage.getItem("expandSPName");	
		if(retrievedData1!=null)
		{
			expandStat=JSON.parse(retrievedData1);
			for(var i=0; i<expandStat.length;i++)
			{				
				var vals = expandStat[i].split(":");
				showHideMulti(vals[0].trim(),vals[1].trim(),vals[2].trim(), '/JuvenileCasework/');			
			}
		}
		retrievedData1 = localStorage.getItem("programName");
		if(retrievedData1!=null)
		{
			expandStat=JSON.parse(retrievedData1);
			for(var i=0; i<expandStat.length;i++)
			{			
				var vals = expandStat[i].split(":");
				showHideMulti(vals[0].trim(),vals[1].trim(),vals[2].trim(), '/JuvenileCasework/');			
			}
		}
	}
	$("[id^=serviceProviderSelectedId]").on('click',function(){		
		
		var select= $(this).attr("href");		
		var providerLink="";
		var retrievedData2 = localStorage.getItem("expandSPName");		
		if(retrievedData2!=null)
			expandSPName=JSON.parse(retrievedData2);		
		if(select != "" ||select != "undefined")
			providerLink=select.substring(select.lastIndexOf("(")+1,select.lastIndexOf(")"));
		var vals=providerLink.split(",");
		vals[0]=vals[0].replace(/'/g,"");
		vals[1]=vals[1].replace(/'/g,"");
		vals[2]=vals[2].replace(/'/g,"");
		var linkStr=vals[0].trim()+":"+vals[1].trim()+":"+vals[2].trim();		
		expandSPName.push(linkStr);		
		if(select != null)		
			localStorage.setItem("expandSPName", JSON.stringify(expandSPName));	
	});
	$("[id^=programNameId]").on('click',function(){
		
		var select= $(this).attr("href");	
		var programLink="";
		var retrievedData2 = localStorage.getItem("programName");
		if(retrievedData2!=null)
			programName=JSON.parse(retrievedData2);
		if(select != "" ||select != "undefined")
			programLink=select.substring(select.lastIndexOf("(")+1,select.lastIndexOf(")"));
		var vals=programLink.split(",");
		vals[0]=vals[0].replace(/'/g,"");
		vals[1]=vals[1].replace(/'/g,"");
		vals[2]=vals[2].replace(/'/g,"");
		var linkStr=vals[0].trim()+":"+vals[1].trim()+":"+vals[2].trim();	
		programName.push(linkStr);
		if(select != null)
			localStorage.setItem("programName", JSON.stringify(programName));
				
	});
	
//calendarScheduleSPSelectEventsSummary.jsp
//--------------------------------------------------
	$("#spFinishButton").on('click',function(){
		disableSubmitButtonCasework($(this));
	});

//eventAttendence.jsp
//--------------------------------------------------
	$("#progressNotes").on('keyup mouseout',function(){
		textLimit($(this),3000);
	});
	var excused = false ;
	 //added for #36737
	if(tentativeRefPrgm=="true"){
		$("#attendanceDropdown option").each(function(){
			 if ( $(this).val() == 'AT' ) {
			        $(this).remove();
			    }
			 
			 if ( $(this).val() == 'AB' ) {
			        $(this).remove();
			    }
		});
	}
	 //added for #36737
	$("#attendanceDropdown").on('change',function(){
		var selectedText = $("option:selected",this).text();
		if( selectedText == "EXCUSED" || selectedText == "ABSENT")
		{
			excused = true ;
			$("[name='addlAttendees']").val("");
			$("[name='addlAttendees']").prop("disabled",true);
			$("[name='selectedAttendeeNames']").prop("disabled",true);
		}
		else
		{				
			excused = false ;
			$("[name='addlAttendees']").prop("disabled",false);
			$("[name='selectedAttendeeNames']").prop("disabled",false);
		}	
	});
	
	$("#validateAttendaceNext").on('click',function(){
		errStr = "";
		
		  if ($("[name='attendanceStatusCd']").val() == "")
		{ 		  
		  	errStr = "Please select an attendance status\n";
		  	alert(errStr);
		  	$("[name='attendanceStatusCd']").focus();
		  	return false;
		  }
		  
		  if($("[name='addlAttendees']").val() != "" && !excused )
		  {
			 // addNumericValidation("addlAttendees","Additional Attendees must be numeric");	
			  var theForm = document.form;
			  var numericRegExp = /^[0-9]*$/;
			  var fld = $("[name='addlAttendees']").val();
			  if(numericRegExp.test(fld, numericRegExp) == false)
			  {
				alert("Additional Attendees must be numeric.");
				$("[name='addlAttendees']").focus();
				return false;
			  }
				/*if( !validateEventAttendanceForm(theForm) )
				{
					return false;
				}*/
				
				var attendeeVal = parseInt($("[name='addlAttendees']").val());			
				if( attendeeVal > 19  )
				{
					alert("Additional Attendees cannot be more than 19");
					$("[name='addlAttendees']").focus();
					return false;
				}
			
		  }
		  // just in case textLimit fails somehow
		 
		  if ($("[name='progressNotes']").val().length > 3000)
			 {
				if (errStr == "")
				{
					$("[name='progressNotes']").focus();
				}		 
			  	errStr += "Progress Notes cannot exceed 3000 characters.";
			 }	
		  
			
		  if (errStr == "")
		  {  		  
		  	return true;
		  }
		  alert(errStr);

		  return false;
	});
	if(document.title == "JIMS2 Juvenile Casework - eventAttendence.jsp")
	{
		var action = $("#calEventListAction").val();
		if(action == "attendanceUpdate")
		{
			var selectedText = $("#attendanceDropdown").val();
			if( selectedText == "EX" || selectedText == "AB")
			{		
				$("[name='addlAttendees']").val("");
				$("[name='addlAttendees']").prop("disabled",true);
				$("[name='selectedAttendeeNames']").prop("disabled",true);
			}		
		}
	}

//programReferralCreate.jsp
//--------------------------------------------------	
	//function textLimit(field, maxlen) {
	$("#programReferalComment").on('keyup',function(){
		textLimit($(this),1000);
	});
	$("#programReferalSubmit").on('click',function(){
		return validateScheduleNewEventForm(this.form) && validateRadios(null, 'programReferral.courtOrdered','Court Ordered is required');
	});
//scheduleNewEventSummary.jsp
//--------------------------------------------------
	//function check4DocGenerated(){
	$("#check4DocGenerated").on('click',function(){
		var dg = $("#DocGenerated");
		if ($(dg).val() == "")
		{
			$(dg).val("Y");
			return true;	
		}
		return true;
	});
	//function checkEventSelected(el){
	$("#checkEventSelected,#checkEventSelected2").on('click',function(){
		var forwardURL;
		var el = $(this).data("href");
		if(el=='EN'||el=='ES'){
			forwardURL="/JuvenileCasework/submitScheduleEvent.do?action="+el;
		}else{
		 forwardURL="/JuvenileCasework/submitScheduleEvent.do?submitAction=Finish&action="+el;
		}
		
		document.forms[0].action=forwardURL;
		//document.forms[0].submit();
	});
	//function setSelection(){
	var selection=false; 
	$("#setConflictingSelection").on('click',function(){
		if(selection==false)
			selection=true;
		else
			selection=false;
	});
	//function resetAction(el){
	$("#resetActionSummary,#resetActionSummary2").on('click',function(){
		var el = $(this).data("href");
		var forwardURL="/JuvenileCasework/submitScheduleEvent.do?submitAction="+el;	
		$('form:first').attr('action',forwardURL);
		$('form:first').submit();
	});
	//disablesubmit
	$("#disableSummaryFinish,#disableSummaryFinish2").on('click',function(){
		disableSubmitButtonCasework($(this));
	});
});
