function checkInput(theForm)
{
	var employeeIds = document.getElementsByName("searchEmployeeId");
	if (employeeIds.length == 0){
		var badgeNumbers = document.getElementsByName("searchBadgeNumber");	
		var otherIdNumbers = document.getElementsByName("searchOtherIdNumber");		
	} else {
		var employeeId = Trim(employeeIds[0].value);
		if (employeeId == ""){
			alert("Employee ID is required for search.");
			theForm.searchEmployeeId.focus();
			return false;
		} 
		return true;
	}
	var badgeNumber = Trim(badgeNumbers[0].value);
	var otherIdNumber = Trim(otherIdNumbers[0].value);
	if (badgeNumber == "" && otherIdNumber == ""){
	
		alert("Badge Number or Other ID Number is required for search.");
		theForm.searchBadgeNumber.focus();
		return false;
	}
	return true;
}

function setCursor()
{
	for(var i=0; i< document.forms[0].length; i++)
	{
		if(document.forms[0].elements[i].type == 'text')
		{
			document.forms[0].elements[i].focus();
			break;
		}
	}
}