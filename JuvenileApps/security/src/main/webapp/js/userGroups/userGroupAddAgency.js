<!-- roleCreate2.js -->
function validateSearchFields(theForm){
    theForm.searchAgencyName.value = Trim(theForm.searchAgencyName.value);
    theForm.agencyCode.value = Trim(theForm.agencyCode.value);
    if (theForm.searchAgencyName.value == "" && theForm.agencyCode.value == "" &&
        theForm.agencyTypeId.selectedIndex == 0){
           alert("At least 1 input value required for Agency search.");
           theForm.searchAgencyName.focus();	
           return false;
    }
   	var msg = validateWildCardSearchField("Agency Name", theForm.searchAgencyName.value);
	if (msg != ""){
       alert(msg);	
       theForm.searchAgencyName.focus();   
       return false;
    }  
   	msg = validateWildCardSearchField("Agency Code", theForm.agencyCode.value);
	if (msg != ""){
       alert(msg);	
       theForm.agencyCode.focus();   
       return false;
    }     
    return true;
}
