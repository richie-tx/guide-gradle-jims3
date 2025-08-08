<!-- Javascript for emulated validation for Juvenile warrant JOT Search -->
function SearchValidator(thisForm)
{
 	var daLogNum = Trim(thisForm.daLogNum.value);
    if (daLogNum == "")
    {
        thisForm.daLogNum.focus();
		alert("DA Log Number is a required field.\n");
    	return false;
    }
    if(validateNumeric(daLogNum) == false)
	{
        thisForm.daLogNum.focus();
		alert("DA Log Number should be an integer.\n");
    	return false; 	 		
 	 }
     return true;
}

function validateNumeric(number)
{  
	var regex = /^[0-9]+$/;
	
	return regex.test(number);
}
