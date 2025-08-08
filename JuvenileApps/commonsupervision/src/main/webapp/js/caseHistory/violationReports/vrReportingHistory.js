function validateInputs(theButton, theForm)
{
	var msg = "";
	var dateResult = "";
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\(),\x27\x3B\x26\x2f\x2c\-]*$/;

	if (typeof(theForm.occurrenceDateStr) == "undefined") {
		if (theButton.value != "Next"){
			return true;
		}
		result2 = validateComments(theForm.create1Comments.value, "Comments");
		if (result2 != ""){
			theForm.create1Comments.focus();
			alert(result2);
			return false;
		}
		return true;
	} 
// trap no data entry 	
	if (theForm.occurrenceDateStr.value == "" && 
		theForm.selectedEventTypeIds.selectedIndex < 0 &&
		theForm.reportingHistoryDetails.value == ""	&& 
		theButton.value != "Next"){
			alert("No data entered to create new Reporting History.");
			theForm.occurrenceDateStr.focus();
			return false;
	}	

	if (theForm.occurrenceDateStr.value > ""){
		dateResult = validateDate(theForm.occurrenceDateStr.value, "Occurrence Date", false);
		if (dateResult != "") {
			theForm.occurrenceDateStr.focus();
			msg += dateResult;
		} else {
			dt = theForm.occurrenceDateStr.value + " 00:00";
			occurDateTime = new Date(dt);
			curDateTime = new Date();
			if (occurDateTime > curDateTime){
				if (msg == ""){
					theForm.occurrenceDateStr.focus();
		    	}
				msg += "Occurrence Date can not be future value.\n";
			}								
		}
	}

	if (theForm.reportingHistoryDetails.value > ""){
		if (alphaNumWithSymbolsRegExp.test(theForm.reportingHistoryDetails.value,alphaNumWithSymbolsRegExp) == false){
			if (msg == ""){
				theForm.reportingHistoryDetails.focus();
		    }
			msg += "Details contains an invalid character.\n";
		}
		if (theForm.reportingHistoryDetails.value.length > 50){
			if (msg == ""){
				theForm.reportingHistoryDetails.focus();
		    }
			msg += "Details contains more than 50 characters.\n";
		}
		
	}
	if (theButton.value == "Next"){
		result = validateComments(theForm.create1Comments.value, "Comments");
		if (result != "") {
			if (msg == ""){
				theForm.create1Comments.focus();
	    	}
			msg += result;
		}	
	}
	if (msg == ""){
		return true;
	}	
	alert(msg);
	return false;		
}

function setAddCursorPos()
{
	var cp = document.getElementsByName("cursorPos");
		if (typeof(document.forms[0].occurrenceDateStr) != "undefined") {
		    document.forms[0].occurrenceDateStr.focus();
		}
				
	cp[0].value = "";
}