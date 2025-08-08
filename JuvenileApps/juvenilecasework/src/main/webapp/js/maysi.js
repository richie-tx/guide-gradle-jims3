function validateMHASelection(theForm) 
{	
	var msg = "";

	// 28 nov 2006 - mjt - following validation added
	if( theForm.administer[0].checked == false  &&  theForm.administer[1].checked == false )
	{
		msg = "Was the MAYSI administered? is required.\n";
		theForm.administer[1].focus();
	}

	if(theForm.referralNum.selectedIndex == 0)
	{
		msg = "Referral Number selection is required.\n";
		theForm.referralNum.focus();
	}

	if(theForm.locationId.selectedIndex == 0)
	{
		if (msg == "")
		{
			theForm.locationId.focus();
		}
		msg += "Location selection is required.\n";
	}

	if(theForm.lengthOfStayId.selectedIndex == 0)
	{
		if (msg == "")
		{
			theForm.lengthOfStayId.focus();
		}
		msg += "How Long has youth been here selection is required.\n";
	}
	
	// 28 nov 2006 - mjt - following validation added
	if( !theForm.hasPreviousMAYSI[0].checked  &&  !theForm.hasPreviousMAYSI[1].checked )
	{
		msg = "Has the youth taken a MAYSI before? is required.\n";
		theForm.hasPreviousMAYSI[1].focus();
	}
	
	if(theForm.facilityTypeId.selectedIndex == 0)
	{
		if( msg == "" )
		{
			theForm.facilityTypeId.focus();
		}
		msg += "Type of Facility selection is required.\n";
	}

	if( msg == "" ) 
	{
		return true;
	}
	
	alert(msg);
	return false;
}