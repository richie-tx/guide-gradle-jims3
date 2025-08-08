function checkInput(theForm)
{
	logonId = Trim(theForm.searchLogonId.value).toUpperCase();	
	theForm.searchLogonId.value = logonId;
	if (logonId == ""){
		alert("JIMS2 User ID is required.");
		theForm.searchLogonId.focus();	
		return false;
	}
	if (logonId.length < 5){
		alert("JIMS2 User ID must be at least 5 characters.");
		theForm.searchLogonId.focus();	
		return false;
	}
	return true;
}
