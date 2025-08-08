function validateInputs(theButton, theForm)
{
	var msg = "";
	var result = "";
	var numericRegExp = /^[0-9]*$/;
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\x27\x3B\x26\x2f\x2c\-]*$/;

	if (typeof(theForm.testDateStr) == "undefined")	{
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

	if (theButton.value == "Next") {
		if (theForm.totalSpecimensAnalyzed.value > ""){
			if (numericRegExp.test(theForm.totalSpecimensAnalyzed.value,numericRegExp) == false) {
				msg = "Total Specimens Analyzed must be a number.\n";
				theForm.totalSpecimensAnalyzed.focus();
			}
		}	
	}
// trap no data entry 	
	if (theForm.testDateStr.value == "" && 
		theForm.substance.value == "" &&
		theButton.value != "Next"){
			alert("No data entered to create new Positive Urinalysis.");
			theForm.testDateStr.focus();
			return false;
	}
	if (theForm.testDateStr.value > ""){ 
		result = validateDate(theForm.testDateStr.value, "Test Date", true);
		if (result != "") {
			theForm.testDateStr.focus();
			msg += result;
		}
	}	
	
	if (theForm.substance.value > ""){
		if (alphaNumWithSymbolsRegExp.test(theForm.substance.value,alphaNumWithSymbolsRegExp) == false){
			if (msg == ""){
				theForm.substance.focus();
		    }
			msg += "Substance value contains an invalid character.\n";
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