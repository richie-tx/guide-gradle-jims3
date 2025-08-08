function checkInput(theForm){
	var msg = "";
	var password = "";
	var passwordAnswer = "";	
	var reenterPassword = "";
	var reenterUserID = "";
	var result1 = "";
	var result2 = "";
	var userID = "";	
	var userType = theForm.userType.value;

	userID = Trim(theForm.jims2LogonId.value).toUpperCase();	
	reenterUserID = Trim(theForm.reenterJIMS2LogonId.value).toUpperCase();	
	theForm.jims2LogonId.value = userID;		
	theForm.reenterJIMS2LogonId.value = reenterUserID;
	result1 = validateJIMS2UserID(userID, "User ID");
	if (result1 > ""){
		msg = result1;
		theForm.jims2LogonId.focus();	
	}
	result1 = validateJIMS2UserID(reenterUserID, "Re-enter JIMS2 User ID");
	if (result1 > ""){
		if (msg == ""){
			theForm.reenterJIMS2LogonId.focus();		
		}
		msg += result1;
	}	
	if (msg == ""){
		if (reenterUserID != userID){
			msg = "JIMS2 User IDs values do not match.\n";
			theForm.jims2LogonId.focus();			
		}
	}	

	if (userType == "S" || userType == "L"){
		password = Trim(theForm.password.value);
		reenterPassword = Trim(theForm.reenterPassword.value);			
		passwordAnswer = Trim(theForm.passwordAnswer.value).toUpperCase();	
		theForm.passwordAnswer.value = passwordAnswer;		
		theForm.password.value = password;	
		theForm.reenterPassword.value = reenterPassword;				
		result1 = validatePassword(password, "Password");
		if (result1 != ""){
			if (msg == ""){
				theForm.password.focus();
			}
			msg += result1;
		}
		result2 = validatePassword(reenterPassword, "Re-enter Password");
		if (result2 != ""){
			if (msg == ""){
				theForm.reenterPassword.focus();
			}
			msg += result2;
		}	
		if (result1 == "" && result2 == ""){
			if (password.toString() != reenterPassword.toString()){
				if (msg == ""){
					theForm.password.focus();
				}
				msg += "Password Mismatch. Please Retype Password.\n";		
			}	
		}	
		if (theForm.passwordQuestionId.selectedIndex == 0){
			if (msg == ""){
				theForm.passwordQuestionId.focus();
			}
			msg += "Password Question is required.\n";		
		} else {
		indx = theForm.passwordQuestionId.selectedIndex;
		theForm.passwordQuestion.value = theForm.passwordQuestionId.options[indx].text;
		}
				
// minimum length from email response		
		if (passwordAnswer == ""){
			if (msg == ""){
				theForm.passwordAnswer.focus();
			}
			msg += "Password Answer is required.\n";
		}else {
			if (passwordAnswer.length < 2){
				if (msg == ""){
					theForm.passwordAnswer.focus();
				}
				msg += "Password Answer must be at least 2 characters.\n";
			}
		}
	}

	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;
}

function validatePassword(pword, fldName){
	var str = "";
	if (pword == ""){
		str = fldName + " is required.\n";
		return str;
	}
	if (pword.length < 6 || pword.length > 32){
		str = fldName + " must be from 6 to 32 characters.\n";
		return str;
	}
	var pwordRegex = /^[a-zA-Z0-9@$]+$/;
	if(pwordRegex.test(pword) == false){
		str = fldName + " value is invalid.\n";
		return str;
	}
	var allNums = /[0-9]+$/;
	var numString = pword.replace(allNums, "");	
	if(numString == ""){
		str = fldName + " can not be all numbers.\n";
		return str;
	}	
	var ValidateDigits = /[^0-9]/g;   
	var ValidateSpChar = /[^@,$]/g;  
    var digitString = pword.replace(ValidateDigits , "");	
	var specialChar = pword.replace(ValidateSpChar , "");	
	if(digitString.length < 1 && specialChar.length < 1)
	{
	  	str = fldName + " should contain at least 1 digit or 1 special character [@ , $].\n";
	  	return str;
	}	
	return str;	
}
function validateJIMS2UserID(fldValue, fldName) {
	var errMsg = "";
	var userIDFmtRegex = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
//	var userIDFmtRegex = /^([A-Z]){1}([A-Z0-9_\.])*(@){1}([A-Z0-9])+(\.){1}((COM)|(ORG)|(EDU)|(BIZ)|(NET)|(GOV)){1}$/;		
	if (fldValue == ""){
		errMsg = fldName + " is required.\n";
		return errMsg;	
	}
	if (fldValue.length < 5){
		errMsg = fldName + " must be at least 5 characters.\n";
		return errMsg;
	}	
	if (userIDFmtRegex.test(fldValue) == false){
		errMsg = fldName + " should be in valid email format.\n";
		return errMsg;	
	}
	return errMsg;
}