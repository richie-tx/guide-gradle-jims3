function validateSearchFields(thisForm)
{
	thisForm.searchDepartmentName.value = Trim(thisForm.searchDepartmentName.value);
	thisForm.searchDepartmentId.value = Trim(thisForm.searchDepartmentId.value);
	if(thisForm.searchDepartmentName.value == "" && thisForm.searchDepartmentId.value=="")
	{
		alert("At least 1 input value required for Department search.");
		thisForm.searchDepartmentName.focus();		    
	    return false;
	}
	var msg = validateWildCardSearchField("Department Name", thisForm.searchDepartmentName.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.searchDepartmentName.focus();		    
		return false;
	}	
	var msg = validateWildCardSearchField("Department Code", thisForm.searchDepartmentId.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.searchDepartmentId.focus();		    
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
