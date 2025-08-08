// javaScript for caseAssignmentDataControl.jsp
function validateInputs(theForm){
	var msg = validateDate(theForm.pgmUnitAssignmentDateStr.value , "Program Unit Assignment Date", true);
	if (msg != ""){
		theForm.pgmUnitAssignmentDateStr.focus(); 
	}
	var rbs = document.getElementsByName("divisionPgmUnitId");
	var selMade = false;
	if (rbs.length != 0){
		for (x = 0; x < rbs.length; x++){
			if (rbs[x].checked == true){
				selMade = true;
				break;
			}
		}	
	}
	if (selMade == false){
		msg += "Division/Program Unit selection required.";
	}		
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;	
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