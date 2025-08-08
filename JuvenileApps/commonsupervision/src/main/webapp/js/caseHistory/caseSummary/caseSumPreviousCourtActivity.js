function validateInputs(theButton, theForm)
{
	var msg = "";
	var result = "";

// Violation Report Edits	
	if (typeof(theForm.courtActivityVRDateStr) != "undefined") {
		if (theForm.courtActivityVRDateStr.value == "" &&
			theForm.create1Comments.value  == "" &&	
			theForm.courtActivityVRSummaryOfCourtActions.value == "" && 
			theButton.value == "Add Violation Report"){
				alert("No data entered to create new Law Violation.");
				theForm.courtActivityVRDateStr.focus();
				return false;
		}
		if (theButton.value == "Add Violation Report" || theButton.value == "Next") {
			if (theForm.courtActivityVRDateStr.value > ""){
				result = validateDate(theForm.courtActivityVRDateStr.value, "Violation Reports Date", true);
				if (result != "") {
					theForm.courtActivityVRDateStr.focus();
					msg += result;
				}
			}	
			if (theForm.create1Comments.value > "") {
				result = validateComments(theForm.create1Comments.value, "Type of Court Action", 50);
				if (result != ""){
					if (msg == ""){
						theForm.create1Comments.focus();
					}
					msg += result;
				}			
			}
			if (theForm.courtActivityVRSummaryOfCourtActions.value > "") {
				result = validateComments(theForm.courtActivityVRSummaryOfCourtActions.value, "Summary of Court Actions", 250);
				if (result != ""){
					if (msg == ""){
						theForm.courtActivityVRSummaryOfCourtActions.focus();
					}
					msg += result;
				}
			}
		}	
	}

// Motions edits
	if (typeof(theForm.motionDateStr) != "undefined"){
		if (theForm.motionDateStr.value == "" &&
			theForm.create2Comments.value  == "" &&	
			theForm.motionSummaryOfCourtActions.value == "" && 
			theForm.selectedMotionDispositionId.selectedIndex == 0 &&
			theButton.value == "Add Motion"){
				alert("No data entered to create new Motion.");
				theForm.motionDateStr.focus();
				return false;
		}
		if (theButton.value == "Add Motion" || theButton.value == "Next") {
			if (theForm.motionDateStr.value > ""){
				result = validateDate(theForm.motionDateStr.value, "Motions Date", true);
				if (result != "") {
					if (msg == ""){
						theForm.motionDateStr.focus();
					}	
					msg += result;
				}
			}	
			if (theForm.create2Comments.value > "") {
				result = validateComments(theForm.create2Comments.value, "Type of Court Action", 50);
				if (result != ""){
					if (msg == ""){
						theForm.create2Comments.focus();
					}
					msg += result;
				}			
			}		
			if (theForm.motionSummaryOfCourtActions.value > "") {
				result = validateComments(theForm.motionSummaryOfCourtActions.value, "Summary of Court Actions", 250);
				if (result != ""){
					if (msg == ""){
						theForm.motionSummaryOfCourtActions.focus();
					}
					msg += result;
				}	
			}
		}	
	}
	
// Others edits	
	if (typeof(theForm.otherDateStr) != "undefined"){
		if (theForm.otherDateStr.value == "" && 
			theForm.create3Comments.value  == "" &&	
			theForm.otherSummaryOfCourtActions.value == "" &&
			theButton.value == "Add Other"){
			 alert("No data entered to create new Other.");
			 theForm.otherDateStr.focus();
			 return false;
		}
		if (theButton.value == "Add Other" || theButton.value == "Next") {
			if (theForm.otherDateStr.value > ""){
				result = validateDate(theForm.otherDateStr.value, "Others Date", true);
				if (result != "") {
					if (msg == ""){
						theForm.otherDateStr.focus();
					}
					msg += result;
				}
			}
			if (theForm.create3Comments.value > "") {
				result = validateComments(theForm.create3Comments.value, "Type of Court Action", 50);
				if (result != ""){
					if (msg == ""){
						theForm.create3Comments.focus();
					}
					msg += result;
				}			
			}			
			if (theForm.otherSummaryOfCourtActions.value > "") {
				result = validateComments(theForm.otherSummaryOfCourtActions.value, "Summary of Court Actions", 250);
				if (result != ""){
					if (msg == ""){
						theForm.otherSummaryOfCourtActions.focus();
					}
					msg += result;
				}	
			}		
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
	if (cp[0].value == "VR"){
		if (typeof(document.forms[0].courtActivityVRDateStr) != "undefined") {
		    document.forms[0].courtActivityVRDateStr.focus();
		}
	}
	if (cp[0].value == "MOT"){
		if (typeof(document.forms[0].motionDateStr) != "undefined") {
		    document.forms[0].motionDateStr.focus();
		}    
	}
	if (cp[0].value == "OTH"){
		if (typeof(document.forms[0].otherDateStr) != "undefined") {
		    document.forms[0].otherDateStr.focus();
		}    
	}
	cp[0].value = "";
}