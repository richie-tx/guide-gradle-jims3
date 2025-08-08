<!-- userGroupSearch.js -->

function validateSearchFields(theForm){
	var inputFound = false;
	for(i=0; i<theForm.length; i++) {
		if(theForm.elements[i].type == "text"){
			theForm.elements[i].value = Trim(theForm.elements[i].value);			
			if (theForm.elements[i].value > ""){
				inputFound = true;
//				break;
			}
		}
		if(theForm.elements[i].type == "select-one"){
			if (theForm.elements[i].selectedIndex > 0){
				inputFound = true;
//				break;
			}
		}			
	}		
	if (!inputFound){
	    alert("At least 1 input value required for User Group search.");
	    theForm.userGroupName.focus();
	    return false;
    } 
    
   	var msg = validateWildCardSearchField("User Group Name", theForm.userGroupName.value);
	if (msg != ""){
       alert(msg);	
       theForm.userGroupName.focus();   
       return false;
    }  
    msg = validateWildCardSearchField("User Group Description", theForm.userGroupDescription.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.userGroupDescription.focus();   
       	return false;
    }   
    var agencyName = document.getElementsByName("agencyName");
	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
	   	msg = validateWildCardSearchField("Agency Name", agencyName[0].value);
		if (msg != ""){
       		alert(msg);	
			agencyName[0].focus();   
			return false;
		}	
    }    
   return true;
}
