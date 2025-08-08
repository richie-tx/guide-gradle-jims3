function validateOfficerFields(frm) {
	var errorMsg = null;
	var errorField = null;
	var ValidateDigits = /[^0-9]/g;   
	var ValidateSpChar = /[^@,$]/g;  
	trim(frm.badgeNumber);
	trim(frm.otherIdNumber);
	trim(document.getElementById("userName.lastName"));
	trim(document.getElementById("userName.firstName"));
	trim(frm.jims2UserId);
	trim(frm.jims2Password);
	trim(frm.confirmJIMS2Password);
	trim(frm.answer);
	var passwordAnswer = frm.answer.value.toUpperCase();
	frm.answer.value = passwordAnswer;

	if (frm.badgeNumber.value == "" && frm.otherIdNumber.value == "") {
		alert("Badge number or other ID number must be entered to create officer profile.");
		frm.badgeNumber.focus();
		return false;
	}
	if (document.getElementById("userCellPhoneNumber.areaCode").value > "" && document.getElementById("userCellPhoneNumber.prefix").value == "") {
		alert("Cell Phone Prefix must be entered if Cell Phone area code is entered.");
		document.getElementById("userCellPhoneNumber.prefix").focus();
		return false;
	}
	if (document.getElementById("userCellPhoneNumber.prefix").value > "" && document.getElementById("userCellPhoneNumber.last4Digit").value == "") {
		alert("Cell Phone last 4 digit number must be entered if Cell Phone prefix is entered.");
		document.getElementById("userCellPhoneNumber.last4Digit").focus();
		return false;
	}
	if (document.getElementById("userCellPhoneNumber.last4Digit").value > "" && document.getElementById("userCellPhoneNumber.prefix").value == "") {
		alert("Cell Phone Prefix must be entered if Cell Phone last4Digit number is entered.");
		document.getElementById("userCellPhoneNumber.prefix").focus();
		return false;
	}
	if (document.getElementById("userCellPhoneNumber.last4Digit").value > "" && document.getElementById("userCellPhoneNumber.areaCode").value == "") {
		alert("Cell Phone Area Code must be entered if Cell Phone last4Digit number is entered.");
		document.getElementById("userCellPhoneNumber.areaCode").focus();
		return false;
	}
	if (document.getElementById("userPagerNumber.areaCode").value > "" && document.getElementById("userPagerNumber.prefix").value == "") {
		alert("Pager Prefix must be entered if Pager area code is entered.");
		document.getElementById("userPagerNumber.prefix").focus();
		return false;
	}
	if (document.getElementById("userPagerNumber.prefix").value > "" && document.getElementById("userPagerNumber.last4Digit").value == "") {
		alert("Pager last4Digit number must be entered if Pager prefix is entered.");
		document.getElementById("userPagerNumber.last4Digit").focus();
		return false;
	}
	if (document.getElementById("userPagerNumber.last4Digit").value > "" && document.getElementById("userPagerNumber.prefix").value == "") {
		alert("Pager Prefix must be entered if Pager last4Digit number is entered.");
		document.getElementById("userPagerNumber.prefix").focus();
		return false;
	}
	if (document.getElementById("userPagerNumber.last4Digit").value > "" && document.getElementById("userPagerNumber.areaCode").value == "") {
		alert("Pager Area Code must be entered if Pager last4Digit number is entered.");
		document.getElementById("userPagerNumber.areaCode").focus();
		return false;
	} 
 
	if(validateLoginOfficerCreateForm(frm)){  
		if (frm.jims2Password.value > ""){
			if(frm.jims2Password.value.length < 6 || frm.jims2Password.value.length > 32){
				alert("Password must be from 6 to 32 characters.");
				frm.jims2Password.focus();
				return false;			
			}	
		}
		var digitString = frm.jims2Password.value.replace(ValidateDigits , "");	
		var specialChar = frm.jims2Password.value.replace(ValidateSpChar , "");	  
		if(digitString < 1 && specialChar < 1){
			alert("Password should contain at least 1 digit or 1 special character [@ , $].");
			frm.jims2Password.focus();
			return false;
		}	    

		if (frm.confirmJIMS2Password.value > ""){
			if(frm.confirmJIMS2Password.value.length < 6 || frm.confirmJIMS2Password.value.length > 32){
				alert("Confirm password must be from 6 to 32 characters.");
				frm.confirmJIMS2Password.focus();
				return false;			
			}	
		}	  
		if (frm.confirmJIMS2Password.value != frm.jims2Password.value) {
			alert("Password Mismatch. Please Retype Password");
			frm.jims2Password.focus();
			return false;           
		}
	} 
}

function trim(textbox) {
	if (textbox) {
		while (textbox.value.substring(0,1) == " ") {
			textbox.value = textbox.value.substring(1);
		}
	}
}