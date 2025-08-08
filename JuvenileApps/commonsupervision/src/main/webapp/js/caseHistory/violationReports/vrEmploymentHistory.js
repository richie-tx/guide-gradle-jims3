function validateInputs(theButton, theForm)
{
	var msg = "";
	var result = "";
	var str = "";
	var flds = "";
	var alphaNumSpRegExp = /^[a-zA-Z0-9 ]*$/;
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x3B\x26\x2f\x2c\-]*$/;

	if (typeof(theForm.employerName) == "undefined")
	{
		if (theButton.value != "Next"){
			return true;
		}

		result = validateComments(theForm.create1Comments.value, "Comments");
		if (result != "") {
			if (msg == ""){
				theForm.create1Comments.focus();
	    	}
			msg += result;
		}
		if (msg != ""){
			alert(msg);
			return false;
		}
		return true;
	} 

	theForm.employerName.value = trimAll(theForm.employerName.value);
	theForm.jobTitle.value = trimAll(theForm.jobTitle.value); 
	
	if (theForm.employerName.value == "" &&
		theForm.jobTitle.value == "" &&
		theForm.jobStatusId.selectedIndex == 0 &&
		theButton.value != "Next"){
			alert("No data entered to create new Employment History.");
			theForm.employerName.focus();
			return false;
	}	
		
	if (theForm.employerName.value > ""){
		if (alphaNumWithSymbolsRegExp.test(theForm.employerName.value,alphaNumWithSymbolsRegExp) == false) {
			msg = "Employer Name contains invalid character.\n";
			theForm.employerName.focus();
		}
	}
	
	if (theForm.jobTitle.value > ""){
		if (alphaNumSpRegExp.test(theForm.jobTitle.value,alphaNumSpRegExp) == false) {
			if (msg == ""){
				theForm.jobTitle.focus();
			}
			msg += "Job Title contains invalid character.\n";
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
		if (typeof(document.forms[0].employerName) != "undefined") {
		    document.forms[0].employerName.focus();
		}
				
	cp[0].value = "";
}