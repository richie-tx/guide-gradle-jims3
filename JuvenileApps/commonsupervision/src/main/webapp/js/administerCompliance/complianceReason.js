function checkReasonSelect()
{
	var compReasons = document.getElementsByName("complianceReasonId");
	for (x = 0; x < compReasons.length; x++)
	{
		if (compReasons[x].selectedIndex == 0)
		{
			alert("Compliance Reason must be selected.");
			compReasons[x].focus();
			return false;
		}
	}
	return true;
}