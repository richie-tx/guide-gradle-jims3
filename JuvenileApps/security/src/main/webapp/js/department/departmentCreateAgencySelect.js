<!-- departmentCreateAgencySelect.js -->
function validateAgencySearchFields(theForm)
{
	theForm.agencyName.value = Trim(theForm.agencyName.value);
	theForm.agencyId.value = Trim(theForm.agencyId.value);
   	if (theForm.agencyName.value == "" && theForm.agencyId.value == "")
   	{
	    alert("At least 1 input value required for Agency search.");
	    theForm.agencyName.focus();
	    return false;
    }  
   	var msg = validateWildCardSearchField("Agency Name", theForm.agencyName.value);
	if (msg != ""){
       alert(msg);	
       theForm.agencyName.focus();   
       return false;
    }  
    msg = validateWildCardSearchField("Agency Code", theForm.agencyId.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyId.focus();   
       	return false;
    }       
	return true;
}
function show(elementNameorId)
{
	var elementBtn=document.getElementById(elementNameorId);
	elementBtn.className='visible';
}
function hide(elementNameorId)
{
	var elementBtn=document.getElementById(elementNameorId);
	elementBtn.className='hidden';
}
