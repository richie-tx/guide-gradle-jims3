function checkInput(theForm){
	var msg = "";
	var userId = "";
	var reenterUserId = "";
	var userIdErr = false;	
	var userType = theForm.userTypeInd.value.toUpperCase();
// start generic user edits part 1 of 2	
	if (userType != "N"){
		var lName = Trim(theForm.lastName.value).toUpperCase();
		var fName = Trim(theForm.firstName.value).toUpperCase();
		var mName = Trim(theForm.middleName.value).toUpperCase();	
		theForm.lastName.value = lName;
		theForm.firstName.value = fName;
		theForm.middleName.value = mName;		
		if (lName == ""){
			msg = "User Last Name is required.\n";
			theForm.lastName.focus();
		} else if (lName.length < 2){
			msg = "User Last Name must be a minimum of 2 characters.\n";
			theForm.lastName.focus();		
		}
		if (fName == ""){
			if (msg == ""){
				theForm.firstName.focus();
			}
			msg += "User First Name is required.\n";
		}		
	} 
// end generic user edits part 1 of 2	
// all users edits	
		userId = Trim(theForm.newJIMS2LogonId.value).toUpperCase();
		reenterUserId = Trim(theForm.reenterNewJIMS2LogonId.value).toUpperCase();
		curUserId = theForm.currentJIMS2LogonId.value.toUpperCase();
		theForm.newJIMS2LogonId.value = userId;			
		theForm.reenterNewJIMS2LogonId.value = reenterUserId;	
		result1 = validateJIMS2UserID(userId, "New User ID");
	    if (result1 > ""){
			if (msg == ""){
				theForm.newJIMS2LogonId.focus();
			}		    
			msg += result1;
			userIdErr = true;
		}
		result1 = validateJIMS2UserID(reenterUserId, "Re-enter New JIMS2 User ID");
		if (result1 > ""){
			if (msg == ""){
				theForm.reenterNewJIMS2LogonId.focus();		
			}
			msg += result1;
			userIdErr = true;
		}	
		if (userId == "" && reenterUserId > ""){
			if (msg == ""){
				theForm.newJIMS2LogonId.focus();
			}
			msg += "New JIMS2 User ID is required.\n";	
		}	
		if (userId > "" && reenterUserId == ""){
			if (msg == ""){
				theForm.reenterNewJIMS2LogonId.focus();
			}
			msg += "Re-enter New JIMS2 User ID is required.\n";	
		}	
		if (userId > "" && reenterUserId > "" && userIdErr == false){
			if (userId.toString() != reenterUserId.toString()){
				if (msg == ""){
					theForm.newJIMS2LogonId.focus();
				}
				msg += "JIMS2 User ID Mismatch. Please Retype JIMS2 User ID.\n";		
			}	
			if (userId.toString() == curUserId.toString()){
				if (msg == ""){
					theForm.newJIMS2LogonId.focus();
				}
				msg += "New JIMS2 User ID is same as current JIMS2 User ID.\n";			
			} 
		}
// start generic user edits part 2 of 2	
	if (userType != "N"){
		var newPassword = Trim(theForm.newPassword.value);
		var reenterNewPassword = Trim(theForm.reenterNewPassword.value);	
		var passwordAns = Trim(theForm.passwordAnswer.value).toUpperCase();	
		var result1 = "";
		var result2 = "";
		theForm.newPassword.value = newPassword;	
		theForm.reenterNewPassword.value = reenterNewPassword;
		theForm.passwordAnswer.value = passwordAns;	
		if (newPassword > ""){	
			result1 = validatePassword(newPassword, "Password");
			if (result1 != ""){
				if (msg == ""){
					theForm.newPassword.focus();
				}
				msg += result1;
			}
		}	
		if (reenterNewPassword > ""){	
			result2 = validatePassword(reenterNewPassword, "Re-enter Password");
			if (result2 != ""){
				if (msg == ""){
					theForm.reenterNewPassword.focus();
				}
				msg += result2;
			}	
		}
		if (newPassword == "" && reenterNewPassword > ""){
			if (msg == ""){
				theForm.newPassword.focus();
			}
			msg += "New Password is required.\n";	
		}	
		if (newPassword > "" && reenterNewPassword == ""){
			if (msg == ""){
				theForm.reenterNewPassword.focus();
			}
			msg += "Re-enter New Password is required.\n";	
		}	
		if (newPassword > "" && reenterNewPassword > "" && result1 == "" && result2 == ""){
			if (newPassword.toString() != reenterNewPassword.toString()){
				if (msg == ""){
					theForm.newPassword.focus();
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
		if (passwordAns == ""){
			if (msg == ""){
				theForm.passwordAnswer.focus();
			}
			msg += "Password Answer is required.\n";
		}else {
			if (passwordAns.length < 2){
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

function setCursor()
{
	for(var i=0; i< document.forms[0].length; i++)
	{
		if(document.forms[0].elements[i].type == 'text')
		{
			document.forms[0].elements[i].focus();
			break;
		}
	}
}
function validateJIMS2UserID(fldValue, fldName) {
	var errMsg = "";
	var userIDFmtRegex = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;	
/**	var userIDFmtRegex = /^([A-Z]){1}([A-Z0-9_\.])*(@){1}([A-Z0-9])+(\.){1}((COM)|(ORG)|(EDU)|(BIZ)|(NET)|(GOV)){1}$/;		*/
	if (fldValue == ""){
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
