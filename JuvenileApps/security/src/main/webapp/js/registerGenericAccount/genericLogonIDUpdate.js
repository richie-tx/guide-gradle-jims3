function validateFields(){
	var msg = "";
	var theForm =  document.forms[0];
	var logonId = Trim(theForm.logonId.value).toUpperCase();
	var newPassword = Trim(theForm.newPassword.value).toUpperCase();
	var reenterPassword = Trim(theForm.reenterPassword.value).toUpperCase();	
	
	theForm.logonId.value = logonId;		
	theForm.newPassword.value = newPassword;	
	theForm.reenterPassword.value = reenterPassword;		

	if (logonId == ""){
		msg = "JIMS User ID is required.\n";
		theForm.logonId.focus();
	}
	if (logonId > ""){
		if (logonId.length < 5){
			msg += "User ID must be 5 characters.\n";
			theForm.logonId.focus();
		}	
	}	
	
	var result = validatePassword(theForm.newPassword, "Password",theForm);
	if (result != ""){
		if (msg == ""){
			theForm.newPassword.focus();
		}
		msg += result;
	}
	result = validatePassword(theForm.reenterPassword, "Re-enter Password");
	if (result != ""){
		if (msg == ""){
			theForm.reenterPassword.focus();
		}
		msg += result;
	}		
	if (msg == ""){
		if (newPassword.toString() != reenterPassword.toString()){
			theForm.reenterPassword.focus();
			msg = "Password Mismatch. Please Retype Password.\n";		
		}	
	}	
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;
}

function validatePassword(pword, fldName,theForm){
	theForm =  document.forms[0];
	var userIdFmtRegex = /^[a-zA-Z0-9#!*@$]+$/;
	
	var validDigits = /[^0-9]/g;  
	var validSpChar = /[^@,*,$,#,!]/g;
	
	var str = "";
	if (Trim(pword.value) == "")
	{
		str = fldName + " is required.\n";
	}
	if (Trim(pword.value).length <6)
	{
	 	if (str == "")
 		{
	 		pword.focus();
  		}
	 	str += fldName +" must have at least 6 characters.\n";
	} 
	
	if (Trim(pword.value).length > 32)
	{
	 	if (str == "")
 		{
	 		pword.focus();
  		}		
	 	str += fldName +" cannot exceed 32 characters.\n";
	} 
	
	var digitString = Trim(pword.value).replace(validDigits , "");	
	var specialChar = Trim(pword.value).replace(validSpChar , "");	 
	if(digitString < 1 && specialChar < 1)
	{
 		if (str == "")
 		{
 			pword.focus();
		}	
 		str += fldName +" should contain at least 1 digit or 1 special character [!, @, #, *, $].\n";
	}
	
	if (userIdFmtRegex.test(Trim(pword.value)) == false)
	{
		if (str == "")
		{
			pword.focus();
  		}	
		str += fldName +" value is invalid. Must contain alphanumeric values with at least 1 digit or 1 special character [!, @, #, *, $].\n";				
	}			
	return str;	
}
