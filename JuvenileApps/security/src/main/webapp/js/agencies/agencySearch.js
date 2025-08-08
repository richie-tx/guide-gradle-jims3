function validateAgencySearchFields(theForm){
	theForm.agencyNamePrompt.value = Trim(theForm.agencyNamePrompt.value);
	theForm.agencyIdPrompt.value = Trim(theForm.agencyIdPrompt.value);

	if (theForm.agencyNamePrompt.value == "" && theForm.agencyIdPrompt.value == "" && theForm.agencyTypeId.selectedIndex == 0 && theForm.jmcRep.selectedIndex == 0) {
	    alert("At least 1 input value required for Agency search.");
	    theForm.agencyNamePrompt.focus();
	    return false;
    }   
 
    var msg = validateWildCardSearchField("Agency Name", theForm.agencyNamePrompt.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyNamePrompt.focus();  
       	return false;
    }  
    msg = validateWildCardSearchField("Agency Code", theForm.agencyIdPrompt.value);
    if (theForm.agencyIdPrompt.value > "" && theForm.agencyIdPrompt.value.charAt(0) == "*")
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyIdPrompt.focus();   
       	return false;
    }  
   return true;
}

function validateForm(el){	
	var input = el.agencyName.value + el.orgCode.value + el.originatingAgencyId.value;

	if (input == "" && el.agencyId.selectedIndex == 0 && el.agencyTypeId.selectedIndex == 0 && el.accessTypeId.selectedIndex == 0 && el.setcicAccessId.selectedIndex == 0 && el.agencyStatusId.selectedIndex == 0)
  {
    alert("Please enter a value for at least one of the fields.");
    el.agencyName.focus();
    return false;
  }
	return true;
}