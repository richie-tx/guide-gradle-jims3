// these functions are case history - violation report and case summary pages
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

function validateComments(fldValue, fldName, maxLen)
{
	var msg = "";
	var maxlen = maxLen;
//	var commentRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\x7C\-]*$/;

	// set default length to 3500	
	if (maxlen == null || maxlen == ""){
		maxlen = 3500;
	}

	if (fldValue.length > maxlen){
		msg = fldName + " can not be greater than " + maxlen + " characters.\n";
//	} else if (fldValue.length > 0) {
//		if (commentRegExp.test(fldValue,commentRegExp) == false) {
//			msg = fldName + " contains invalid character or begins with space.\n";
//		}
	}
	return msg;
}	

function validateCheckBoxSelect(theForm)
{
	for (x =0; x<theForm.length; x++){
		if (theForm.elements[x].type == "checkbox"){
			if (theForm.elements[x].checked == true) {
				return true;
			}
		}	
	}
	alert("No items selected to Remove.");
	return false;
}
function textLimit(field, maxlen) {
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
		alert("Your input has been truncated to " + maxlen +" characters!");	
	}
}
function loadPrevComments()
{
     var fldin = document.getElementsByName("prevComments");
     var fldout = document.getElementsByName("create1Comments");
     if (fldin[0].value != null && fldin[0].value > "")
     {
           fldout[0].value = fldin[0].value;
           textLimit(fldout[0], 3500);
     } else {        
           alert("No previous comments found to insert.");
     }    
}

/**
 * pass in field to copy from, and field to copy to
 * copy from field normally previous field that is hidden. Copy to field
 * is normally a text area that you want to pull prev information to for user.
 */
function loadMentalHealthComments(fromFieldName, toFieldName)
{
     //var fldin = document.getElementsByName("prevComments");
	 //var fldout = document.getElementsByName("create1Comments");
	 var fldin = document.getElementsByName(fromFieldName);
	 var fldout = document.getElementsByName(toFieldName);    
     if (fldin[0].value != null && fldin[0].value > "")
     {
           fldout[0].value = fldin[0].value;
           textLimit(fldout[0], 3500);
     } else {        
           alert("No previous comments found to insert.");
     }    
}