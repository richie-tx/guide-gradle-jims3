function validateSearchFields(thisForm)
{
	thisForm.departmentNamePrompt.value = Trim(thisForm.departmentNamePrompt.value);
	thisForm.departmentIdPrompt.value = Trim(thisForm.departmentIdPrompt.value);
	if(thisForm.departmentNamePrompt.value == "" && thisForm.departmentIdPrompt.value=="")
	{
		alert("At least 1 input value required for Department search.");
		thisForm.departmentNamePrompt.focus();		    
	    return false;
	}
	var msg = validateWildCardSearchField("Department Name", thisForm.departmentNamePrompt.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.departmentNamePrompt.focus();		    
		return false;
	}	
	var msg = validateWildCardSearchField("Department Code", thisForm.departmentIdPrompt.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.departmentIdPrompt.focus();		    
		return false;
	}	
	return true;	
}
function validateRadioFields(thisForm) {	
    var myOption = 0;    
	
	for(var i=0; i<thisForm.length;i++)
    {    
     	if(thisForm.elements[i].type == 'radio')
     	{
     	  if(thisForm.elements[i].checked)
	      {
	      	myOption=1;
	      }   
     	}
     }
     
	if (myOption == 0) {
		alert("You must select a department to proceed.");
		return false;
	}
	
	return true;

}