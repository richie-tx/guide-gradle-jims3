$(document).ready(function () {

	//Reg-ex
	var validSpChars = /[^@,$,!,#,%,&,+,*]/g;
	var allNums = /[0-9]/g;  
	var validDigits = /[^0-9]/g;
	
	//Must contain 6 to 15 alphanumeric characters. Must Contain at least 1 digit [0-9] , one capital letter and one of the the following special characters [+*@#%&$].\n
	var validGenericPasswd = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&*+]).*$/g
	 

	$("#submitBtn").on("click",function(){
		var errorMsg = "";
		if($("#currPassword").val()=="")
		{
			alert("Current Password must be entered.\n");
			$("#currPassword").focus();
			return false;
		}
		if($("#newPassword").val()=="")
		{
			alert("New Password must be entered.\n");
			$("#newPassword").focus();
			return false;
		}
		if($("#confirmPassword").val()=="")
		{
			alert("Confirm Password must be entered.\n");
			$("#confirmPassword").focus();
			return false;
		}
	//	if($("#userType").val()=="GENERICSP"){
			 if($("#newPassword").val().length<6)
			 {
				if (errorMsg == "")
			 	{
					$("#newPassword").focus();
			  	}
				errorMsg += "New Password must have at least 6 characters.\n";
			 }
			 if ($("#newPassword").val().length> 15)
			 {
				 if (errorMsg == "")
				 {
					$("#newPassword").focus();
				 }		
			     errorMsg += "New Password cannot exceed 15 characters.\n";
			 }
			//all numbers validations 
			if($("#newPassword").val().length > "")
			{
				var	alphaString = $("#newPassword").val().replace(allNums, "");
				if(alphaString == "")
				{
					if (errorMsg == "")
					{
						$("#newPassword").focus();
			  		}	
			  		alert("New Password cannot be all numbers.\n");
			  		return false;
				}
			}
			
			//validation: Must contain 6 to 15 alphanumeric characters. Must Contain at least 1 digit [0-9] , one capital letter and one of the the following special characters [+*@#%&$].\n 
			if (validGenericPasswd.test($("#newPassword").val(),validGenericPasswd) == false)
			{
					if (errorMsg == "")
		 			{
						$("#newPassword").focus();
			  		}	
	   			 	errorMsg += "New Password is invalid.\n must contain 6 to 15 alphanumeric characters.\n must contain at least 1 digit [0-9].\n must contain one capital letter.\n must contain one of the following special characters \n [+*@#%&$]";				
			}	
			
		/*	var specialChar =$("#newPassword").val().replace(validSpChars , "");
			var digitString = $("#newPassword").val().replace(validDigits , "");	
			
			//digit and special character validation.
			if(digitString < 1 || specialChar < 1)
			{
				if (errorMsg == "")
				{
					$("#newPassword").focus();
		  		}	
		  		errorMsg += "New Password should contain at least 1 digit[0-9] or 1 special character [+*@#%&$].\n";
			}
			*/
			//new password and confirm password match
			if ($("#confirmPassword").val() > "")
			{
				if ($("#newPassword").val() != $("#confirmPassword").val())
				{
	 				if (errorMsg == "")
	 				{
	 					$("#confirmPassword").focus();
		  			}	   	
		     		errorMsg += "New Password mismatch. Please Retype Password.\n";
				}
			}	
					//}
		if (errorMsg == "")
		{
	  		return true;
		}
		alert(errorMsg);
		return false;
	});
});


/*function validateInputs()
{
	var errorMsg = "";
	var frm = document.forms[0];
	var ValidateDigits = /[^0-9]/g;  
	var ValidateSpChar = /[^@.%?_|!#:=*\-\&\$]/g; //added for #39614
	//var ValidateSpChar = /^[a-zA-Z0-9@.%?_|!#:=*\-\&\$]+$/g; // added for 36781
	var ValidateNonGenericPasswd = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@.%?_|!#:=*\-\&\$]).*$/g; // added for 36781,//#39614
	var ValidateSpCharGenericUser = /[^@,$]/g; // added for 36781
	var allNums = /[0-9]/g;  
	var validNonGenericPasswrod = /^[a-zA-Z0-9]+$/; //old rule not in use.
	var validGenericPasswrod = /^[a-zA-Z0-9@$]+$/;
	var alphaString = "";
	var curPwrd = Trim(frm.currentPassword.value);
	var newPwrd = Trim(frm.newPassword.value);
	var confirmPwrd = Trim(frm.confirmPassword.value);
	var userType = frm.type.value.toUpperCase();

	frm.currentPassword.value = curPwrd;
	frm.newPassword.value = newPwrd; 
	frm.confirmPassword.value = confirmPwrd;
  
  	if (frm.currentPassword.value == "")
  	{
  		errorMsg = "Current Password must be entered.\n";
	    frm.currentPassword.focus();
	}  
  	if (frm.newPassword.value == "")
  	{
  		if (errorMsg == "")
  		{
  			frm.newPassword.focus();
  		}
     	errorMsg += "New Password must be entered.\n";
	} 
	if (frm.newPassword.value > "")
	{
		var	alphaString = frm.newPassword.value.replace(allNums, "");
		if(alphaString == "")
		{
	 		if (errorMsg == "")
	 		{
				frm.newPassword.focus();
  			}	
  			errorMsg += "New Password cannot be all numbers.\n";
		}
		 password for generic user is case sensative at this time. may change 	
		if(userType == "GENERICUSER" || userType == "L" || userType == "S")
		{
			if (frm.newPassword.value.length <6)
			{
			 	if (errorMsg == "")
		 		{
  					frm.newPassword.focus();
		  		}
				errorMsg += "New Password must have at least 6 characters.\n";
			} 
			if (frm.newPassword.value.length > 32)
			{
			 	if (errorMsg == "")
		 		{
  					frm.newPassword.focus();
		  		}		
		     	errorMsg += "New Password cannot exceed 32 characters.\n";
			} 
			var digitString = frm.newPassword.value.replace(ValidateDigits , "");	
			var specialChar = frm.newPassword.value.replace(ValidateSpCharGenericUser , "");	  // added for 36781
			if(digitString < 1 && specialChar < 1)
			{
		 		if (errorMsg == "")
		 		{
  					frm.newPassword.focus();
	  			}	
	  			errorMsg += "New Password should contain at least 1 digit or 1 special character [@ , $].\n";
			}
			if (validGenericPasswrod.test(frm.newPassword.value) == false)
				{
					if (errorMsg == "")
	 				{
						frm.newPassword.focus();
			  		}	
   				 	errorMsg += "New Password value is invalid.  Must contain alphanumeric values with at least 1 digit or 1 special character [@ , $].\n";				
				}			
			if (frm.confirmPassword.value > "")
			{
				if (frm.newPassword.value != frm.confirmPassword.value)
				{
 					if (errorMsg == "")
 					{
						 frm.confirmPassword.focus();
	  				}	   	
	    	 		errorMsg += "New Password mismatch. Please Retype Password.\n";
				}
			}	
		}
 password for non-generic user is NOT case sensative at this time. 		
		if(userType == "NONGENERICUSER" || userType == "N")
		{
			newPwrd = frm.newPassword.value.toUpperCase();
			curPwrd = frm.currentPassword.value.toUpperCase();
			confirmPwrd = frm.confirmPassword.value.toUpperCase();
			if (newPwrd > "")
			{
				if (curPwrd == newPwrd)
				{
		 			if (errorMsg == "")
		 			{
  						frm.newPassword.focus();
		  			}	  
		     		errorMsg += "New password cannot be the same as current password.\n";
				}  
			   	if (frm.newPassword.value.length <8)
			   	{
	 				if (errorMsg == "")
	 				{
	  					 frm.newPassword.focus();
			  		}	   	
		    	 	errorMsg += "New Password must have at least 8 characters.\n";
				} 
			  	if (frm.newPassword.value.length > 8)
		   		{
	 				if (errorMsg == "")
		 			{
  						 frm.newPassword.focus();
		  			}	   	
		     		errorMsg += "New Password cannot exceed 8 characters.\n";
				}
				if (validNonGenericPasswrod.test(newPwrd) == false) // added for 36781 old rule
				{
					if (errorMsg == "")
	 				{
						frm.newPassword.focus();
			  		}	
   				 	errorMsg += "New Password value is invalid.  Must contain alphanumeric values only.\n";				
				}
			  	// 	added for 36781

				var digitString = frm.newPassword.value.replace(ValidateDigits , "");	
				var specialChar = frm.newPassword.value.replace(ValidateSpChar , "");	
				if(digitString < 1 && specialChar < 1)
				{
			 		if (errorMsg == "")
			 		{
	  					frm.newPassword.focus();
		  			}	
		  			errorMsg += "New Password should contain at least 1 digit , 1 special character[.@%?_|#!-=:&*$].\n";
				}else{ //added for #39614
					if (ValidateNonGenericPasswd.test(newPwrd) == false) // added for 36781
					{
						if (errorMsg == "")
			 			{
							frm.newPassword.focus();
				  		}	
		   			 	errorMsg += "New Password value is invalid. Must contain alphanumeric values with at least 1 digit,1 special character[.@%?_|#!-=:&*$].\n";				
					}
				}
				
				// added for 36781
				if (confirmPwrd > "")
				{
					if (newPwrd != confirmPwrd) {
 						if (errorMsg == "")
		 				{
							frm.confirmPassword.focus();
				  		}	   	
    				 	errorMsg += "New Password mismatch. Please Retype Password.\n";
					}
				}	
			}
		}
	}		
	
   	if (frm.confirmPassword.value == "")
   	{
  		if (errorMsg == "")
  		{
  			frm.confirmPassword.focus();
  		}
     	errorMsg += "Re-Enter New Password must be entered.\n";
	}
 password for generic user is case sensative at this time. may change 	
	if(userType == "GENERICUSER" || userType == "L" || userType == "S")
	{
		if (frm["forgottenPasswdPhraseId"].selectedIndex == 0)
		{
		 	if (errorMsg == "")
		 	{
  				frm["forgottenPasswdPhraseId"].focus();
	  		}		
  			errorMsg += "Please select a Forgotten Password Phrase for your password.\n";
	 	} 
		var answer = Trim(frm.answer.value);
		frm.answer.value = answer;  
		if (frm.answer.value == "")
		{
		 	if (errorMsg == "")
		 	{
  				frm.answer.focus();
	  		}
			errorMsg += "Please provide an answer for your Forgotten Password Phrase.\n";
		}
	} 
  
	if (errorMsg == "")
	{
  		return true;
	}
	alert(errorMsg);
	return false;
}*/

function Trim(s)
{
	// Remove leading spaces and carriage returns
	while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
	{ 
		s = s.substring(1,s.length); 
	}
	
	// Remove trailing spaces and carriage returns
	while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
	{ 
		s = s.substring(0,s.length-1); 
	}
	return s;
}