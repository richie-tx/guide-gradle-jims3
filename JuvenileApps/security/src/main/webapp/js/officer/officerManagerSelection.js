function validateSearchFields(thisForm)
{
	thisForm.managerId.value = Trim(thisForm.managerId.value);
	thisForm.lastNamePrompt.value = Trim(thisForm.lastNamePrompt.value);
	thisForm.firstNamePrompt.value = Trim(thisForm.firstNamePrompt.value);	
	if(thisForm.managerId.value == "" && thisForm.lastNamePrompt.value == "" && thisForm.firstNamePrompt.value == "")
	{
		alert("At least 1 input value required for Manager search.");
		thisForm.managerId.focus();		    
	    return false;
	}
   	if (thisForm.lastNamePrompt.value == ""){
    	if (thisForm.firstNamePrompt.value > ""){
        	alert("Last Name must be provided to use First Name search field.");
         	thisForm.lastNamePrompt.focus();
         	return false;      
      	}	
   	} 	
	var msg = validateWildCardSearchField("User ID", thisForm.managerId.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.managerId.focus();		    
		return false;
	}	
	var msg = validateWildCardSearchField("Last Name", thisForm.lastNamePrompt.value);
	if (msg != ""){
    	alert(msg);	
		thisForm.lastNamePrompt.focus();		    
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

function showSelectButton(){
	document.getElementById("selectManagerButton").className='visible';
}
