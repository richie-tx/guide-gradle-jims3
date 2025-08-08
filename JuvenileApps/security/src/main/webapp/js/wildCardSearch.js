<!-- wildCardSearch.js --> 
// verifies field is at least 2 characters and that neither of the characters is an asterisks.
// fldValue should be Trimmed before calling this function
function validateWildCardSearchField(fldName, fldValue)
{
	var errorMsg = ""
	if (fldValue > "")
	{
		if (fldValue.length < 2 || (fldValue.charAt(0) == '*' || fldValue.charAt(1) == '*'))
		{
			errorMsg = fldName + " must be at least 2 valid characters or 2 valid characters before the *."	
		}
	}
	return errorMsg;
}
// original code, no longer used
//function validateNameField(fldName, fldValue)
//{
//	var errorMsg = ""
//	if (fldValue > "")
//	{
//		if (fldValue.length < 2)
//		{
//			errorMsg = fldName + " must be at least 2 characters."
//		} 
//		else
//		{
//			if (fldValue.charAt(0) == '*' || fldValue.charAt(1) == '*')
//			{
//				errorMsg = fldName + " must start with 2 valid characters."
//			}
//		}	
//	}
//	return errorMsg;
//}