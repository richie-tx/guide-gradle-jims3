<!-- roleCreate2.js -->
function validateSearchFields(theForm){
    var msg = "";
    theForm.agencyName.value = Trim(theForm.agencyName.value);
    theForm.agencyCode.value = Trim(theForm.agencyCode.value);
    if (theForm.agencyName.value == "" &&
    	theForm.agencyCode.value == "" &&
        theForm.agencyTypeId.selectedIndex == 0 &&
        theForm.jmcRepId.selectedIndex == 0){
           	alert("At least 1 input value required for Agency search.");
           	theForm.agencyName.focus();
			return false;
    }
 
    msg = validateWildCardSearchField("Agency Name", theForm.agencyName.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyName.focus();   
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
function validateAgencySelect(theForm){
     for (var i = 0; i <theForm.length; i++){
 		if(theForm.elements[i].type == "checkbox"){
			if(theForm.elements[i].name == "selectedAgencies"){
				if (theForm.elements[i].checked == true){ 
					return true;
				}
			}
		}     
    }
	alert("AT least 1 Agency must be selected for found list.");
	theForm.agencyName.focus();
	return false;
}

function checkSelectedList(theForm){
	var agency = document.getElementsByName("agencySelected");
    if (agency.length == 0){
		alert("AT least 1 Agency must be added to selected list.");
		theForm.agencyName.focus();
		return false;
	}
	return true;	
}
