<!-- officerSearch.js -->
function validateSearchFields(theForm){
	var inputFound = false;
    var offType = false;
    var lName = Trim(theForm.lastNamePrompt.value);
    var fName = Trim(theForm.firstNamePrompt.value);
    var userId = Trim(theForm.userIdPrompt.value);
    var deptCode = Trim(theForm.departmentIdPrompt.value);       
    var deptName = Trim(theForm.departmentNamePrompt.value);
    var badgeNum = Trim(theForm.badgeNumPrompt.value);       
    var otherIdNum = Trim(theForm.otherIdNumPrompt.value); 
    var statusId = theForm.statusId.value; 
    var managerId = Trim(theForm.managerId.value);
    theForm.firstNamePrompt.value = fName;   
    theForm.lastNamePrompt.value = lName;   
    theForm.userIdPrompt.value = userId;   
    theForm.departmentIdPrompt.value = deptCode;   
    theForm.departmentNamePrompt.value = deptName;   
    theForm.badgeNumPrompt.value = badgeNum;   
    theForm.otherIdNumPrompt.value = otherIdNum;   
    theForm.managerId.value = managerId;
	
    var input = lName + fName + userId + deptCode + deptName + badgeNum + otherIdNum +statusId+managerId;
 	if (input > ""){  
		inputFound = true;
	}	
    if(theForm.officerTypeId.selectedIndex > 0){
        offType = true;
	}   
    if (fName > "" && lName == "") {
		alert("Last Name must be provided to use First Name search field.");
        theForm.lastNamePrompt.focus();
        return false;       	
    }
    
   	if (!inputFound){
   		if (offType = true){
   		 alert("At least one other search criteria is required with Officer Type.");
   		} else {
	    alert("At least 1 input value required for search.");
	    }
	    theForm.lastNamePrompt.focus();
	    return false;
    }
	msg = validateWildCardSearchField("Last Name", theForm.lastNamePrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.lastNamePrompt.focus();		    
		return false;
    }  
	msg = validateWildCardSearchField("User ID", theForm.userIdPrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.userIdPrompt.focus();		    
		return false;
    }
	msg = validateWildCardSearchField("Department Code", theForm.departmentIdPrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.departmentIdPrompt.focus();		    
		return false;
    }
	msg = validateWildCardSearchField("Department Name", theForm.departmentNamePrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.departmentNamePrompt.focus();		    
		return false;
    }                  
	msg = validateNumberOnlySearchField("Badge Number", theForm.badgeNumPrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.badgeNumPrompt.focus();		    
		return false;
    }
	msg = validateNumberOnlySearchField("Other Id Number", theForm.otherIdNumPrompt.value);
	if (msg != ""){
    	alert(msg);	
		theForm.otherIdNumPrompt.focus();		    
		return false;
    }
	msg = validateNumberOnlySearchField("Manager User ID", theForm.managerId.value);
	if (msg != ""){
    	alert(msg);	
		theForm.managerId.focus();		    
		return false;
    }        
	
   return true;
}

function validateNumberOnlySearchField(fldName, fldValue)
{
	var errorMsg = ""
	if (fldValue > "")
	{
		if (fldValue.charAt(0) == '*' )
		{
			errorMsg = fldName + " must have at least 1 valid character before the *."	
		}
	}
	return errorMsg;
}