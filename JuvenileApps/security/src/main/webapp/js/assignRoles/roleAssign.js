<!-- JavaScript for this page only -->
<!-- 06/06/2005  awidjaja - this validates the fields with condition in addition to Struts validation -->

function validateUserGroupSearchFields(theForm){
	var input = "";
	var msg = "";
	theForm.userGroupName.value = Trim(theForm.userGroupName.value);
	theForm.userGroupDescription.value = Trim(theForm.userGroupDescription.value);	
	input = theForm.userGroupName.value + theForm.userGroupDescription.value;
	var agencyName = document.getElementsByName("userGroupAgencyName");
	var agencyCode = document.getElementsByName("userGroupAgencyCode");	
	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
		agencyCode[0].value = Trim(agencyCode[0].value);
		input += agencyName[0].value + agencyCode[0].value;
	}
	if (input == ""){
	    alert("At least 1 input value required for User Group search.");
	    theForm.userGroupName.focus();
	    return false;
    } 
    msg = validateWildCardSearchField("User Group Name", theForm.userGroupName.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.userGroupName.focus();   
       	return false;
    }    
    msg = validateWildCardSearchField("Description", theForm.userGroupDescription.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.userGroupDescription.focus();   
       	return false;
    }  
 	if (agencyName.length > 0){
	    msg = validateWildCardSearchField("Agency Name", agencyName[0].value);
		if (msg != ""){
       		alert(msg);	
	      	agencyName[0].focus();   
    	   	return false;
    	}  
	    msg = validateWildCardSearchField("Agency Code", agencyCode[0].value);
		if (msg != ""){
       		alert(msg);	
	      	agencyCode[0].focus();   
    	   	return false;
    	}     	 	
    } 	
  
   return true;
}

function validateUserSearchFields(theForm){
	var input = "";
	theForm.userLastName.value = Trim(theForm.userLastName.value);
	theForm.userFirstName.value = Trim(theForm.userFirstName.value);
	theForm.departmentName.value = Trim(theForm.departmentName.value);
	theForm.departmentId.value = Trim(theForm.departmentId.value);	
	theForm.userId.value = Trim(theForm.userId.value);				
	input = theForm.userLastName.value + theForm.userFirstName.value + 
			theForm.departmentName.value + theForm.departmentId.value + theForm.userId.value;
	var agencyName = document.getElementsByName("userAgencyNamePrompt");
	var agencyCode = document.getElementsByName("userAgencyCode");	
	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
		agencyCode[0].value = Trim(agencyCode[0].value);
		input += agencyName[0].value + agencyCode[0].value;
	}
	if (input == ""){
	    alert("At least 1 input value required for User search.");
	    theForm.userLastName.focus();
	    return false;
    } 
   	if (theForm.userLastName.value == "" && theForm.userFirstName.value > ""){
       	alert("Last Name must be provided to use First Name search field.");
       theForm.userLastName.focus(); 
       	return false;      
   	}
   	var msg = validateWildCardSearchField("Last Name", theForm.userLastName.value);
	if (msg != ""){
       alert(msg);	
       theForm.userLastName.focus();   
       return false;
    } 
 	if (agencyName.length > 0){
	    msg = validateWildCardSearchField("Agency Name", agencyName[0].value);
		if (msg != ""){
       		alert(msg);	
	      	agencyName[0].focus();   
    	   	return false;
    	}  
	    msg = validateWildCardSearchField("Agency Code", agencyCode[0].value);
		if (msg != ""){
       		alert(msg);	
	      	agencyCode[0].focus();   
    	   	return false;
    	}     	 	
    } 
   	msg = validateWildCardSearchField("Department", theForm.departmentName.value);
	if (msg != ""){
       alert(msg);	
       theForm.departmentName.focus();   
       return false;
    } 
   	msg = validateWildCardSearchField("Department Code", theForm.departmentId.value);
	if (msg != ""){
       alert(msg);	
       theForm.departmentId.focus();   
       return false;
    } 
   	msg = validateWildCardSearchField("User ID", theForm.userId.value);
	if (msg != ""){
       alert(msg);	
       theForm.userId.focus();   
       return false;
    }  
   return true;
}
