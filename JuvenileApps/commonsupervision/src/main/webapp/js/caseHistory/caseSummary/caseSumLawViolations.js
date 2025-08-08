function validateInputs(theButton, theForm){
	var msg = "";
	var result = "";
	var result2 = "";
	var alphaNumRegExp = /^[a-zA-Z0-9]*$/;
	var offLitRegExp = /^[a-zA-Z0-9\.\\\\';,()\x26\x2f\x3c\x3e\x24\x3d\-][a-zA-Z0-9 \.\\\\';,()\x26\x2f\x3c\x3e\x24\x3d\-]*$/;

	if (typeof(theForm.courtNum) == "undefined") {
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
	
	theForm.lvCaseNum.value = trimAll(theForm.lvCaseNum.value);
	theForm.courtNum.value = trimAll(theForm.courtNum.value);
	theForm.offenseDateStr.value = trimAll(theForm.offenseDateStr.value);
	theForm.offenseLiteral.value = trimAll(theForm.offenseLiteral.value);				

	if (theForm.lvCaseNum.value == "" &&
	    theForm.courtNum.value == "" &&
		theForm.offenseDateStr.value == "" &&
		theForm.offenseLiteral.value == "" && 
		theForm.offenseLevelId.selectedIndex == 0 &&
		theButton.value != "Next"){
			alert("No data entered to create new Law Violation.");
			theForm.lvCaseNum.focus();
			return false;
	}	

	if (theForm.lvCaseNum.value > ""){
		if (alphaNumRegExp.test(theForm.lvCaseNum.value,alphaNumRegExp) == false) {
			msg = "Case# contains invalid character.\n";
			theForm.lvCaseNum.focus();
		}
	}
	
	if (theForm.courtNum.value > ""){
		if (alphaNumRegExp.test(theForm.courtNum.value,alphaNumRegExp) == false) {
			if (msg == ""){
				theForm.courtNum.focus();
			}
			msg += "CRT contains invalid character.\n";
		}
	}

	if (theForm.offenseDateStr.value > ""){
		result = validateDate(theForm.offenseDateStr.value, "Offense Date", true);
		if (result != "") {
			if (msg == ""){
				theForm.offenseDateStr.focus();
		    }
			msg += result;
		} 
	}
	
	if (theForm.offenseLiteral.value > ""){
		if (offLitRegExp.test(theForm.offenseLiteral.value,offLitRegExp) == false) {
			if (msg == ""){
				theForm.offenseLiteral.focus();
			}
			msg += "Offense Literal contains invalid character.\n";
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