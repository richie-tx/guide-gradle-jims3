<!-- csProgramReferrals.js -->



function spCheckAll(el)
{
	var elements = document.getElementsByName("selectedSPIds");
	var spLen = elements.length;
	
	if(el.checked)
	{
		for(var index=0; index < spLen; index++)
		{
			var filteredSP = elements[index];
			
			if(!filteredSP.disabled)
			{
				filteredSP.checked = true;
			}
		}

		var userEnteredSP = document.getElementsByName("userEnteredServiceProvider")[0];
		if(!userEnteredSP.disabled)
		{
			var userEnteredSPName = document.getElementsByName("userEnteredServiceProviderName")[0];
			if (userEnteredSPName.value != null && userEnteredSPName.value != "")
			{	
				userEnteredSP.checked = true;
			}	
		}
	}
	else
	{
		for(index=0; index < spLen; index++)
		{
			var filteredSP = elements[index];
			
			if(!filteredSP.disabled)
			{
				filteredSP.checked = false;
			}
		}

		var userEnteredSP = document.getElementsByName("userEnteredServiceProvider")[0];
		if(!userEnteredSP.disabled)
		{
			userEnteredSP.checked = false;
		}
	}
}



function validatePhone(areaCodeElemName, prefixElemName, lastFourDigitElemName, phoneErrorMsgName, theForm){
	if (theForm[areaCodeElemName].value > "" && theForm[prefixElemName].value == "") {
      alert(phoneErrorMsgName + " Prefix must be entered if Area Code is entered.");
      theForm[prefixElemName].focus();
      return false;
   }
   if (theForm[prefixElemName].value > "" && theForm[lastFourDigitElemName].value == "") {
      alert(phoneErrorMsgName + " Last Four Digits must be entered if Prefix is entered.");
      theForm[lastFourDigitElemName].focus();
      return false;
   }
   if (theForm[prefixElemName].value == "" && theForm[lastFourDigitElemName].value > "") {
      alert(phoneErrorMsgName + " Prefix must be entered if last four digits are entered.");
      theForm[prefixElemName].focus();
      return false;
     }
    if (theForm[areaCodeElemName].value == "" && theForm[lastFourDigitElemName].value > "") {
      alert(phoneErrorMsgName + " Area Code must be entered if last four digits are entered.");
      theForm[areaCodeElemName].focus();
      return false;
     } 
    return true;
}













