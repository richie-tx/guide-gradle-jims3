function submitPage(theForm)
{
	var logonID = Trim(theForm.searchLogonId.value);
	if (logonID == "")
	{
		alert("Full or partial User ID required for search.");
		theForm.searchLogonId.focus();
		return false;
	}
	if (logonID.length < 2)
	{
		alert("JIMS User ID must be at least 2 characters.");
		theForm.searchLogonId.focus();
		return false;
	} 
	if (logonID.charAt(0) == '*' || logonID.charAt(1) == '*')
	{
		alert("JIMS User ID must start with 2 valid characters.");
		theForm.searchLogonId.focus();
		return false;				
	}
	return true;
}
