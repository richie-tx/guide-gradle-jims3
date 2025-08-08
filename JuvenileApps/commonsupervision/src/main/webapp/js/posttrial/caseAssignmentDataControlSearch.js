// javaScript for caseAssignmentDataControlSearch.jsp
function validateInputs(theForm){
	var msg = "";
	var alphaNumRegExp = /^[a-zA-Z0-9]*$/;
	if (theForm.caseNum.value == ""){
		msg = "Case # is required.\n";
		theForm.caseNum.focus();
	} else if (alphaNumRegExp.test(theForm.caseNum.value,alphaNumRegExp) == false) {
			msg = "Case # contains invalid character or begins with space.\n";
			theForm.caseNum.focus();
	}		
	if (theForm.cdi.value == ""){
		if (msg == ""){
			theForm.cdi.focus();
		}	
		msg += "CDI is required.";
	}
	if (msg == ""){
		return true;
	}		
	alert(msg);
	return false;
}
function resetInputs(theForm){
	theForm.caseNum.value = "";
	theForm.cdi.value = "";
	theForm.caseNum.focus();
	return false;
}