<!-- 06/13/2005  validates fields for searches and required fields -->

function validateAgencySearchFields(theForm){
	var msg = "";
   	var agencyName = document.getElementsByName("searchAgencyName");
   	var agencyCode = document.getElementsByName("agencyCode");  

	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
		agencyCode[0].value = Trim(agencyCode[0].value);
    	if(agencyName[0].value == "" && agencyCode[0].value == "" && theForm.agencyTypeId.selectedIndex == 0){
	    	alert("At least 1 input value required for Agency search.");
	    	theForm.searchAgencyName.focus();
	   		return false;
    	}				
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
   
function validateSAUserSearchFields(theForm){
	theForm.lastName.value = Trim(theForm.lastName.value);
	theForm.firstName.value = Trim(theForm.firstName.value);
	theForm.middleName.value = Trim(theForm.middleName.value);	
	theForm.userId.value = Trim(theForm.userId.value);
    var inputFlds = theForm.lastName.value + theForm.firstName.value + theForm.userId.value;

    if (inputFlds == "" && theForm.departmentId.selectedIndex == 0){
    	 alert("At least 1 input value required for User search.");
	     theForm.lastName.focus();
	     return false;
	}  
	if (theForm.lastName.value == "")
	{ 
		if (theForm.firstName.value > ""){
    	    alert("Last name required if First Name entered for search.");
            theForm.lastName.focus();
	        return false;
      	}   
		if (theForm.middleName.value > ""){
    	    alert("Last name required if Middle Name entered for search.");
            theForm.lastName.focus();
	        return false;
      	}   
    }    
   	msg = validateWildCardSearchField("Last Name", theForm.lastName.value);
	if (msg != ""){
       alert(msg);	
       theForm.lastName.focus();   
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

function validateMAUserSearchFields(theForm){
	theForm.lastName.value = Trim(theForm.lastName.value);
	theForm.firstName.value = Trim(theForm.firstName.value);
	theForm.middleName.value = Trim(theForm.middleName.value);	
	theForm.userId.value = Trim(theForm.userId.value);
	theForm.userAgencyName.value = Trim(theForm.userAgencyName.value);
	theForm.department.value = Trim(theForm.department.value);
    var inputFlds = theForm.lastName.value + theForm.firstName.value + theForm.middleName.value + theForm.userId.value + theForm.userAgencyName.value + theForm.department.value;

    if (inputFlds == ""){
    	 alert("At least 1 input value required for User search.");
	     theForm.lastName.focus();
	     return false;
	} 
	if (theForm.lastName.value == "")
	{ 
		if (theForm.firstName.value > ""){
    	    alert("Last name required if First Name entered for search.");
            theForm.lastName.focus();
	        return false;
      	}   
		if (theForm.middleName.value > ""){
    	    alert("Last name required if Middle Name entered for search.");
            theForm.lastName.focus();
	        return false;
      	}   
    }    
   	msg = validateWildCardSearchField("Last Name", theForm.lastName.value);
	if (msg != ""){
       alert(msg);	
       theForm.lastName.focus();   
       return false;
    }  
   	msg = validateWildCardSearchField("User ID", theForm.userId.value);
	if (msg != ""){
       alert(msg);	
       theForm.userId.focus();   
       return false;
    }   
   	msg = validateWildCardSearchField("Agency Name", theForm.userAgencyName.value);
	if (msg != ""){
       alert(msg);	
       theForm.userAgencyName.focus();   
       return false;
    }  
   	msg = validateWildCardSearchField("Department Name", theForm.department.value);
	if (msg != ""){
       alert(msg);	
       theForm.department.focus();   
       return false;
    }           
    return true;
}

function validateRequiredFields(theForm){
	var msg = "";
	var regexp = /^[a-zA-Z0-9 '-./\\();\x26]*$/;
	var result = false;
	var tfld = Trim(theForm.userGroupName.value);
	theForm.userGroupName.value = tfld;
	tfld = Trim(theForm.userGroupDescription.value); 
	theForm.userGroupDescription.value = tfld;
	if (theForm.userGroupName.value == ""){
      msg = "User Group Name is required.\n";
      theForm.userGroupName.focus();
	}else {
	   result = regexp.test(theForm.userGroupName.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.userGroupName.focus();
           }
	       msg += "User Group Name contains one or more invalid characters.\n";	   
	   }    
   }
   if (theForm.userGroupDescription.value == ""){
	  if (msg == ""){
	       theForm.userGroupDescription.focus();
	  }   
      msg += "User Group Description is required.\n";
   }else {
	   result = regexp.test(theForm.userGroupDescription.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.userGroupDescription.focus();
           }
	       msg += "User Group Description contains one or more invalid characters.\n";	   
	   }
	}	   
	if (msg != ""){
		alert(msg);
		return false;
	}
	return true;
}

function allUsersSelect(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;
 

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = true;
	}else {
///			uncheckSelectAll(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

function uncheckSelectAll(el, checkAllName)
{	
	var theForm = el.form;
	if (!el.checked){
		theForm.selectAllUsers.checked = false;
	}
}
function resetFields(theForm)
{
/*	Clear Agency Selection */
	var rbutton = document.getElementsByName("SelectedAgencies");
	for (x = 0; x <rbutton.length; x++)
	{
		rbutton[x].checked = false;
	} 
/*	Clear Users Selection */
	var cbox = document.getElementsByName("selectedUsers");
	for (x = 0; x <cbox.length; x++)
	{
		cbox[x].checked = false;
	}
	if (cbox.length > 0)
	{
		theForm.selectAllUsers.checked = false;
	}
	var setf = document.getElementsByName("searchAgencyName");
	if (setf.length > 0){ 
		theForm.searchAgencyName.focus();
	} else {
		theForm.lastName.focus();
	}
	return false;
}
function setPageType(fldName){
	document.forms[0].pageType.value = fldName;
	return true;
}
