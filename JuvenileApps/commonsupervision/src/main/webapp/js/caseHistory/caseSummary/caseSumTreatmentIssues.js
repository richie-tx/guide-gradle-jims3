function validateInputs(theButton, theForm)
{
	var msg = "";
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x3B\x26\x2f\x2c\-]*$/;	
	
	if (typeof(theForm.referralBeginDateStr) == "undefined") {
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
	theForm.serviceProviderName.value = trimAll(theForm.serviceProviderName.value); 
	theForm.referralBeginDateStr.value = trimAll(theForm.referralBeginDateStr.value);
	theForm.referralExitDateStr.value = trimAll(theForm.referralExitDateStr.value);
	
	if (theForm.programGroupId.selectedIndex == 0 &&
		theForm.programTypeId.selectedIndex == 0 &&	
	    theForm.serviceProviderName.value == "" && 
		theForm.referralBeginDateStr.value == "" &&
		theForm.referralExitDateStr.value == "" && 
		theForm.selectedDischargeReasonId.selectedIndex == 0)
		{
		if (theButton.value != "Next"){
			alert("No data entered to create new Treatment Issue.");
			theForm.referralTypeDesc.focus();
			return false;
		} else {
			return true;
		}	
	}
	
	if (theForm.programGroupId.selectedIndex == 0)
	{
		msg = "Program Group selection required.\n";
		theForm.programGroupId.focus()
	} else {
		if (theForm.programTypeId.selectedIndex == 0)
		{
			msg = "Program Type selection required.\n";
			theForm.programTypeId.focus()			
		}	
	}
	
	if (theForm.programGroupId.selectedIndex != 0 &&
		theForm.programTypeId.selectedIndex != 0 &&	
	    theForm.serviceProviderName.value == ""){
		if (msg == ""){
			theForm.serviceProviderName.focus();
		}
		msg += "Service Provider Name is required.\n";
	} else {
		if (alphaNumWithSymbolsRegExp.test(theForm.serviceProviderName.value,alphaNumWithSymbolsRegExp) == false) {
			if (msg == ""){
				theForm.serviceProviderName.focus();
			}
			msg += "Service Provider Name contains invalid character.\n";
		}
	}

	var beginDateResult = "x";
	var exitDateResult = "x";

	if (theForm.referralBeginDateStr.value > ""){
		beginDateResult = validateDate(theForm.referralBeginDateStr.value, "Begin Date", true);
		if (beginDateResult != "") {
			if (msg == ""){
				theForm.referralBeginDateStr.focus();
			}
			msg += beginDateResult;
		}
	}
	if (theForm.referralExitDateStr.value > ""){
		exitDateResult = validateDate(theForm.referralExitDateStr.value, "Exit Date", true);
		if (exitDateResult != "") {
			if (msg == ""){
				theForm.referralExitDateStr.focus();
			}
			msg += exitDateResult;
		} 
	}
	if (beginDateResult == "" && exitDateResult == "")
	{
		var dt = theForm.referralBeginDateStr.value + " 00:00";
		var begDateTime = new Date(dt);
		dt = theForm.referralExitDateStr.value + " 00:00";
		var exitDateTime = new Date(dt);
		if (begDateTime > exitDateTime){
			if (msg == ""){
				theForm.referralExitDateStr.focus();
			}
			msg += "Exit Date can not be previous to Begin Date.\n";		
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
	
	if (msg != ""){
		alert(msg);
		return false;
	}
	return true;
}

function setAddCursorPos()
{
	var cp = document.getElementsByName("cursorPos");
		if (typeof(document.forms[0].referralTypeDesc) != "undefined") {
		    document.forms[0].referralTypeDesc.focus();
		}
				
	cp[0].value = "";
}