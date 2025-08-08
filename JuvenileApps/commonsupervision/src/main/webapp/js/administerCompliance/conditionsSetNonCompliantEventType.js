function pageLoad() {
	var fldName = "";
	var selectFldName = "";
	var showFldName = "";
	for (var x = 0; x <document.forms[0].length; x++)
	{
		
		if(document.forms[0].elements[x].type == "select-multiple"){
			showFldName = "newEventType";
			fldName = document.forms[0].elements[x].name;
			selectFldName = document.getElementsByName(fldName);
			if (document.forms[0].elements[x].name.indexOf("LikeConditions") > 0)
			{
				showFldName = "newLikeEventType";
			}
			eventTypeCheck(selectFldName[0], showFldName);	
		}
	}
}

function eventTypeCheck(theSelect, showFieldName){
	var indx = theSelect.name.substring(theSelect.name.indexOf('[') + 1, theSelect.name.indexOf('[') + 2);
	var showWhat = showFieldName + indx;
	for (var i = 0; i < theSelect.options.length; i++) {
		if (theSelect.options[ i ].selected) {
			if(theSelect.options[ i ].value.toUpperCase() == "NEW"){
				show(showWhat, 1 ,"row");
			}else {
				show(showWhat, 0);
			} 
		}
	}
}


function checkRequired(theForm)
{
	var msg = "";
	var curDateTime = new Date();
	var occurDate = "";
	var occurTime = "";
	var occurDateTime = "";
	var indx = 0;
	var checkDate = false;	
	var sprBegDate = theForm.sprvnBeginDate.value;
	var sprBegDateTime = curDateTime;
	var positiveUA = false;
	if (sprBegDate > "")
	{
		sprBegDateTime = new Date(sprBegDate);		
	}
	var mm = sprBegDateTime.getMonth() + 1;
	if (mm < 10) {
		mm = "0" + mm;
	}
	var dd = sprBegDateTime.getDate();
	if (dd < 10){
		dd = "0" + dd;
	}
	var hr = sprBegDateTime.getHours();
	var med = "AM";
	if (hr > 12){
		hr = hr - 12;
		med = "PM";
	}
	if (hr < 10){
		hr = "0" + hr;
	}
	var min = sprBegDateTime.getMinutes();
	if (min < 10){
		min = "0" + min;
	}	  
	var sprBegDateFMT = mm + "/" + dd + "/" + sprBegDateTime.getFullYear();
	var sprBegDateCompare = sprBegDateTime.getFullYear() + "" + mm + "" + dd;
	
	for (var x = 0; x <theForm.length; x++)
	{
		checkDate = false;
		if(theForm.elements[x].type == "text")
		{
			if (theForm.elements[x].name.indexOf("occurrenceDate") > 0)
			{
				msg = validateDate(theForm.elements[x].value);
				if (msg != "")
				{
					alert(msg);
					theForm.elements[x].focus();
					return false;
				}
				indx = x;
				occurDate = theForm.elements[x].value;
				
			}
			if (theForm.elements[x].name.indexOf("occurrenceTime") > 0)
			{
				
				if (theForm.elements[x].value > "")
				{					
					msg = validateTime(theForm.elements[x].value, theForm.elements[x + 1].selectedIndex);
					if (msg != "")
					{
						alert(msg);
						theForm.elements[x].focus();
						return false;
					}
					occurTime = theForm.elements[x].value;

					if (theForm.elements[x + 1].selectedIndex > 0){
						var theTime = occurTime.split(':');
						var hr = Number(theTime[0]);
						if (hr < 12){
							hr = hr + 12;
						}	
						occurTime = hr + ":" + theTime[1];
					}
					
				} else {
					occurTime = "00:00";
				}
				checkDate = true;		
					
			}

// check entered date and time against supervision begin and ending date and time
			if (checkDate)
			{				
				dt = occurDate + " " + occurTime;				
				occurDateTime = new Date(dt);
				
				var mm = occurDateTime.getMonth() + 1;
				if (mm < 10) {
					mm = "0" + mm;
				}
				
				var dd = occurDateTime.getDate();
				if (dd < 10){
					dd = "0" + dd;
				}
				
				var yyyy = occurDateTime.getFullYear();
				
				var compareOccurDate = yyyy + "" + mm + "" + dd;				
				if (compareOccurDate < sprBegDateCompare)
				{
					alert("Occurrence Date can not be previous to Supervision begin date " + sprBegDateFMT + ".");
					theForm.elements[indx].focus();
					return false;
				}	
				if (occurDateTime > curDateTime)
				{
					if (occurTime == "00:00"){
						alert("Occurrence Date can not be future value.");						
					} else {
						alert("Occurrence Date and Time combination can not be future value.");
					}
					theForm.elements[indx].focus();
					return false;
				}								
			}			
			if (theForm.elements[x].name.indexOf("details") > 0)				
			{
				if (positiveUA)
				{	
					if (theForm.elements[x].value == "")
				    {
						msg = "Details required when Event Type equals 'Positive UA'.";
					    alert(msg);
	     			    theForm.elements[x].focus();
	     			    positiveUA = false;
					    return false;
				    }
				}
				if (theForm.elements[x].value > "") {
				    msg = validateDetails(trimAll(theForm.elements[x].value));
				    if (msg != "")
				    {
					    alert(msg);
					    theForm.elements[x].focus();
					    return false;
				    }
				}
			}			
		}
		if(theForm.elements[x].type == "select-multiple")			
		{
			positiveUA = false;
			if(theForm.elements[x].selectedIndex < 0)
			{
				alert("Event Type selection is required.");
				theForm.elements[x].focus();
				return false;		 
			}
			if(theForm.elements[x].options[theForm.elements[x].selectedIndex].value.toUpperCase() == "NEW")
			{
				msg = validateNewEventType(trimAll(theForm.elements[x + 1].value));
				if (msg != "")
				{
					alert(msg);
					theForm.elements[x + 1].focus();
					return false
				}
					
			} 
			for (y=0; y< theForm.elements[x].options.length; y++)
			{	
				if (theForm.elements[x].options[y].selected)
				{
					if (theForm.elements[x].options[y].value == "CSC65" || theForm.elements[x].options[y].value == "CSC66")
					{			 
						positiveUA = true;
						break;
					}
				}
			}
						
		}		
	}
	return true;
}
function validateDate(fldValue)
{
	var msg = "";
	if (fldValue == "")
	{
		
		return "Occurrence Date is required.";
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		return "Occurrence Date must be in the MM/DD/YYYY format.";
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (month.length != 2 || day.length != 2 || year.length != 4)
	{
		return "Occurrence Date must be in the MM/DD/YYYY format.";	
	} 

    if (month < 1 || month > 12)
    {
		return "Occurrence Date is not valid.";	
    }
    if (day < 1 || day > 31) {
		return "Occurrence Date is not valid.";	
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		return "Occurrence Date is not valid.";	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day>29 || (day == 29 && !leap)) {
			return "Occurrence Date is not valid.";	
        }
    }    
 	return msg;
}
function validateTime(fldValue, med)
{
	var msg = "";
// used split, could not be regexp to 24 hour time in customStrutsBasedValidation to work	
		var theTime = fldValue.split(':');
		var hr = theTime[0];
		var min = theTime[1];
		var definedNumbers = /^[0-9]*$/;
		if (fldValue.length < 5 || definedNumbers.test(hr) == false ||  definedNumbers.test(min) == false|| hr.length > 2 || hr > 12 || min > 59)
		{
			msg = "Occurrence Time is not in proper 12 hour format.";
		}	
	return msg;
}
function validateNewEventType(fldValue)
{
	var msg = "";
	if (fldValue == "")
	{
		msg = "New Event Type required when Event Type equals 'NEW EVENT TYPE'.";
	} else {	
		var definedAlphaNumericnSpaceMaskWSymbols=/^[a-zA-Z0-9 ,.#-]*$/;
		if (definedAlphaNumericnSpaceMaskWSymbols.test(fldValue, definedAlphaNumericnSpaceMaskWSymbols) == false)
		{
			msg = "New Event Type contains invalid character.";
		}
	}		
	return msg;
}
function validateDetails(fldValue)
{
	var msg = "";
		
	    var definedAlphaNumericnSpaceMaskWSymbols=/^[a-zA-Z0-9 ,\'./\\()\x3B\x26\-]*$/;
	    if (definedAlphaNumericnSpaceMaskWSymbols.test(fldValue, definedAlphaNumericnSpaceMaskWSymbols) == false)
	    {
		    msg = "Details contains invalid character.";
	    }
	    
	return msg;
}



