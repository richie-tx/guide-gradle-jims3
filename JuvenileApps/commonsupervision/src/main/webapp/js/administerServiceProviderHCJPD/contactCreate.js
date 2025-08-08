
function validateFields()
{
	var thisForm = document.forms[0];
	var nameRegExp = /^[a-zA-Z0-9/ \\.()\-\x26]*$/;	

	var lName = document.getElementsByName("currentContact.contactName.lastName");		
	if(lName.length > 0)
	{
		trim(thisForm["currentContact.contactName.lastName"]);
		trim(thisForm["currentContact.contactName.firstName"]);
		trim(thisForm["currentContact.contactName.middleName"]);
		trim(thisForm["currentContact.contactName.suffix"]);			
/* validate last name if present */
		if(thisForm["currentContact.contactName.lastName"].value == "")
		{
			alert("Last name is required to create contact.");
	      	thisForm["currentContact.contactName.lastName"].focus();
	      	return false;
		}
		if(nameRegExp.test(thisForm["currentContact.contactName.lastName"].value) == false)
		{
			alert("Last name must be alphanumeric.");
	      	thisForm["currentContact.contactName.lastName"].focus();
	      	return false;			
		}
		if(thisForm["currentContact.contactName.lastName"].value.length > 75) 
		{
			alert("Last name  can not be greater than 75 characters.");
	      	thisForm["currentContact.contactName.lastName"].focus();
	      	return false;			
		}
/* validate first name if present */
		if(thisForm["currentContact.contactName.firstName"].value > "")
		{
			if(nameRegExp.test(thisForm["currentContact.contactName.firstName"].value) == false)
			{
				alert("First name must be alphanumeric.");
	      		thisForm["currentContact.contactName.firstName"].focus();
	      		return false;			
			}
			if(thisForm["currentContact.contactName.firstName"].value.length > 50) 
			{
				alert("Last name  can not be greater than 50 characters.");
	      		thisForm["currentContact.contactName.firstName"].focus();
	      		return false;			
			}
		}	
/* validate middle name if present */
		if(thisForm["currentContact.contactName.middleName"].value > "")
		{
			if(nameRegExp.test(thisForm["currentContact.contactName.middleName"].value) == false)
			{
				alert("Middle name must be alphanumeric.");
	      		thisForm["currentContact.contactName.middleName"].focus();
	      		return false;			
			}
			if(thisForm["currentContact.contactName.middleName"].value.length > 50) 
			{
				alert("Middle name can not be greater than 50 characters.");
	      		thisForm["currentContact.contactName.middleName"].focus();
	      		return false;			
			}
		}	
/* validate suffix if present */
		if(thisForm["currentContact.contactName.suffix"].value > "")
		{
			if(nameRegExp.test(thisForm["currentContact.contactName.suffix"].value) == false)
			{
				alert("Suffix must be alphanumeric.");
	      		thisForm["currentContact.contactName.suffix"].focus();
	      		return false;			
			}
			if(thisForm["currentContact.contactName.suffix"].value.length > 10) 
			{
				alert("Suffix can not be greater than 10 characters.");
	      		thisForm["currentContact.contactName.middleName"].focus();
	      		return false;			
			}
		}					
	}
	
	if(thisForm["inHouse"].value == "Yes")
	{
		trim(thisForm["currentContact.logonId"]);
		if(document.getElementById('idButton').value =="Find")
		{
			trim(thisForm["currentContact.contactName.lastName"]);
		}
	}
	else
	{
		if(thisForm["currentContact.jobTitle"] != null)
		{
			trim(thisForm["currentContact.jobTitle"]);
			var alphaNumRegex = /^[a-zA-Z0-9 ]*$/;	
			if(alphaNumRegex.test(thisForm["currentContact.jobTitle"].value) == false)
			{
				alert("Job Title must be alphanumeric value.");
				thisForm["currentContact.jobTitle"].focus();		
				return false;
			}								
		}		
	}
	
	trim(thisForm["currentContact.email"]);	
	var adminContact=0;
	if(thisForm["currentContact.isAdminContact"] != null)
	{
		for(var i=0; i<thisForm["currentContact.isAdminContact"].length;i++)
	    {  
	       	if( thisForm["currentContact.isAdminContact"][i].checked)
	    		adminContact=1;
	    }
	    if(adminContact == 0)
	    {
	    	alert("Administrative contact indicator required to create contact.");
	       thisForm["currentContact.isAdminContact"][0].focus();
	       return false;
	    }
	  }
	return validateContactForm(thisForm);

}
function checkLogonId()
{
	var thisForm = document.forms[0];
	trim(thisForm["currentContact.logonId"]);
	if(thisForm["currentContact.logonId"].value =="")
	{
		alert("User ID has to be provided to find Profile.");
      	thisForm["currentContact.logonId"].focus();
      	return false;
	}

}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

