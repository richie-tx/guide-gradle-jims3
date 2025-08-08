<!-- JavaScript to ensure that the signature option is selected and the reason not signed field is 
<!--  entered if signature option is unsend or return -->

<script language=javascript>

function stringTrim(s)
{
    return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

function fieldCheck()
{	
   	var selectedIndex = document.forms[0].signatureCommandId.selectedIndex;
    if (document.forms[0].signatureCommandId.selectedIndex == 0)
	{
		alert("Signature option must be selected.");
		document.forms[0].signatureCommandId.focus();
		return false;
	}
	else
	{
		var selectedOptionId = document.forms[0].signatureCommandId.options[selectedIndex].value;
		var reasonField = document.forms[0].unsendNotSignedReason;
		var reason = stringTrim(reasonField.value);
		
		var maxReasonLength = 255;
		
/**		alert(selectedOptionId);
		alert(reason.length); */
	   	  
		if (selectedOptionId == "US")
		{
	         if (reason == "")
			 {
	           alert("Reason Not Signed entry required when the signature option is UNSEND.");
		       reasonField.focus();
		       return false;
			 }
			 else if(reason.length > maxReasonLength)
			 {
			 	alert("The Reason Not Signed field may only contain "+maxReasonLength+" characters.");
		       	reasonField.focus();
		       	return false;
			}
		}
		else if (selectedOptionId == "R")
		{
	        if (reason == "")
			{
	        	alert("Reason Not Signed entry required when the signature option is RETURN.");
		    	reasonField.focus();
		    	return false;
			}
			else if(reason.length > maxReasonLength)
			{
			 	alert("The Reason Not Signed field may only contain "+maxReasonLength+" characters.");
		       	reasonField.focus();
		       	return false;
			}
		}
		else if (selectedOptionId == "S")
		{
			if (reason > "")
			{
	        	alert("Reason Not Signed should not be entered when the signature option is SIGN.");
		    	reasonField.focus();
		    	return false;
			}
		}
	}
	 
	return true;
}
</script>