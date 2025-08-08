<!-- JavaScript for agencyUpdate.jsp page only -->
function validateAgencyUpdateForm(theForm){
	var agNameExp = /^[a-zA-Z0-9/ \\.,()\-\x26\x27]*$/;
    theForm.agencyNamePrompt.value = Trim(theForm.agencyNamePrompt.value);	
    var msg = "";
	if(theForm.agencyNamePrompt.value == ""){
		msg += "Agency Name required.\n";
	    theForm.agencyNamePrompt.focus();
	} else {
		var result = agNameExp.test(theForm.agencyNamePrompt.value,agNameExp);
	   if (result == false){
           if (msg == ""){
              theForm.agencyNamePrompt.focus();
           }
	       msg += "Agency Name value entered contains invalid characters.\n";	   
	   } 
	}
	if(theForm.agencyNamePrompt.value  > "" && theForm.agencyNamePrompt.value  < 5){
		if (msg == ""){
			theForm.agencyNamePrompt.focus();
		}
		msg += "Agency Name must be 5 or more characters long.\n";
	} 
	
// agencyIdPrompt is a entry only when page is used to create agency 	
	var txtField = document.getElementsByName("agencyIdPrompt");
	if (txtField.length > 0){
	    var agencyCode = Trim(txtField[0].value);
	    if (agencyCode == ""){
	    	if (msg == ""){
				txtField[0].focus();
			}	
			msg += "Agency Code is required.\n";
		} 
		if (agencyCode > ""){
		    if (agencyCode.length < 3){
		    	if (msg == ""){
					txtField[0].focus();
				}	
				msg += "Agency Code must be 3 characters long.\n";
			}  
			if (agencyCode.indexOf(" ") > -1){
	    		if (msg == ""){
					txtField[0].focus();
				}
				msg += "Agency Code can not contain spaces or blanks."		
			}
		}	
	}

	if (msg == ""){
		return true;
	}	
	alert(msg);
	return false;
}


 	


