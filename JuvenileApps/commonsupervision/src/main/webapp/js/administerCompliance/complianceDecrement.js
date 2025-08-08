function validateSelectEvent(theForm)
{
	var flds = document.getElementsByName("ncEventId");
	if (flds.length == 0)
	{
		alert("No event(s) available for selection to continue.");
		return false;
	}
	for (x = 0; x < flds.length; x++)
	{
		if (flds[x].checked == true)
		{
			return true;
		}	 
	}
	alert("Event selection required to continue.");
	return false;	
}
