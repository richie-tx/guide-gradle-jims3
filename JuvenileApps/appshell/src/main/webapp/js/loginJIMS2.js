function validateFields(frm) {
	var frm = document.forms[0];
	var ValidateDigits = /[^0-9]/g;   
	var ValidateSpChar = /[^@,$]/g;  

	trim(frm.jims2UserId);
	trim(frm.confirmJIMS2UserId);    
	trim(frm.jims2Password);
	trim(frm.confirmJIMS2Password);
	trim(frm.answer);
  
	if(frm.jims2UserId.value == "")
	{
		frm.jims2UserId.focus();
	    alert("JIMS2 User ID is required.");
	    return false;
	}
	if(frm.confirmJIMS2UserId.value == "")
	{
		frm.confirmJIMS2UserId.focus();
	    alert("Re-Enter JIMS2 User ID is required.");
	    return false;
	}
	var jim2UserId = frm.jims2UserId.value.toUpperCase();
	var confirmJIMS2UserId = frm.confirmJIMS2UserId.value.toUpperCase();
	if (jim2UserId != confirmJIMS2UserId)
	{
		frm.confirmJIMS2UserId.focus();
	    alert("Re-Enter JIMS2 User ID does not match JIMS2 User ID entry.");
	    return false;
	}	
	if(frm.userType.value!="nonGenericUser")
	{
		if (frm.jims2Password.value == "")
		{
		   frm.jims2Password.focus();
		   alert("JIMS2 Password required.");
		   return false;
		}
		var pwordRegex = /^[a-zA-Z0-9@$]+$/;
		if(pwordRegex.test(frm.jims2Password.value) == false)
		{	
		  	alert("Password value is invalid.");
		  	frm.jims2Password.focus();
		  	return false;			
		}			  
		var digitString = frm.jims2Password.value.replace(ValidateDigits , "");	
		var specialChar = frm.jims2Password.value.replace(ValidateSpChar , "");	  
		if(digitString < 1 && specialChar < 1)
		{
		 	alert("Password should contain at least 1 digit or 1 special character [@ , $].");
		  	frm.jims2Password.focus();
		  	return false;
		}	  
		if (frm.confirmJIMS2Password.value == "")
		{
		  	  alert("Please confirm the Password you entered.");
		      frm.confirmJIMS2Password.focus();
		      return false;           
	    } 
		if(pwordRegex.test(frm.jims2Password.value) == false)
		{	
		  	alert("Confirm Password value is invalid.");
		  	frm.confirmJIMS2Password.focus();
		  	return false;			
		}	
		var digitString = frm.confirmJIMS2Password.value.replace(ValidateDigits , "");	
		var specialChar = frm.confirmJIMS2Password.value.replace(ValidateSpChar , "");	  
		if(digitString < 1 && specialChar < 1)
		{
		 	alert("Confrim Password should contain at least 1 digit or 1 special character [@ , $].");
		  	frm.confirmJIMS2Password.focus();
		  	return false;
		}			    
		if (frm.confirmJIMS2Password.value != frm.jims2Password.value)
		{
		  	  alert("Password Mismatch. Please Retype Password.");
		  	  frm.jims2Password.focus();
		      return false;           
		} 
		if (frm["forgottenPasswdPhraseId"].selectedIndex == 0)
		{
		  	 alert("Please select a Forgotten Password Phrase for your password.");
		     frm["forgottenPasswdPhraseId"].focus();
		     return false;                
		} 
		if (frm.answer.value == "")
		{
		 	  alert("Please provide an answer for your Forgotten Password Phrase.");
		      frm.answer.focus();
		      return false;
		}      
		if (frm.answer.value.length < 2)
		{
     		  alert("Password Answer must be at least 2 characters.");
			  frm.answer.focus();
		      return false;
		} else {
			var passwordAnswer = frm.answer.value.toUpperCase();
			frm.answer.value = passwordAnswer;	
		}
	 
	}

	if(frm.userType.value=="nonGenericUser")
	{
		return validateLoginJIMS2Form(frm);
	} 
	return validateLoginGenericForm(frm);
}

function trim(textbox) {
	if (textbox) {
		while (textbox.value.substring(0,1) == " ") {
			textbox.value = textbox.value.substring(1);
		}
	}
}