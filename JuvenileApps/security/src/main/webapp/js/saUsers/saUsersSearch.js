<!-- JavaScript for SA User Search page -->
<!-- 05/18/2005  sprakash - this validates the fields with condition in addition to Struts validation -->

function validateSearchFields(theForm)
{
	var testInput = "";
	var lastName = Trim(theForm.lastName.value);
    var firstName = Trim(theForm.firstName.value);
	var userId = Trim(theForm.logonId.value);
    var agencyName = Trim(theForm.agencyName.value);    
    
	theForm.lastName.value = lastName;
	theForm.firstName.value = firstName;
	theForm.logonId.value = userId;
	theForm.agencyName.value = agencyName;
	textInput = lastName + firstName + userId + agencyName; 

	if (textInput == "" && theForm.userTypeId.selectedIndex == 0){
	    alert("At least 1 input value required for User search.");
	    theForm.lastName.focus();
	    return false;
    } 
    
   	if (lastName == ""){
    	if (firstName > ""){
        	alert("Last Name must be provided to use First Name search field.");
         	theForm.lastName.focus();
         	return false;      
      	}	
   	} 
   	var msg = validateWildCardSearchField("Last Name", lastName);
	if (msg != ""){
       alert(msg);	
       theForm.lastName.focus();   
       return false;
    }  
    msg = validateWildCardSearchField("User ID", userId);
	if (msg != ""){
       alert(msg);	
       theForm.logonId.focus();   
       return false;
    }     
    msg = validateWildCardSearchField("Agency Name", agencyName);
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyName.focus();   
       	return false;
    }     
       
   	return true;
}

function nameSelectCheck(warFile)
{
	var rblogon = document.getElementsByName("selectedLogonId");
	var rbagency = document.getElementsByName("userAgencyId");	
	var rbagencyName = document.getElementsByName("userAgencyName");
	var baseURL = "/"+warFile+"displaySARoleCreate2.do?submitAction=Next"; 
	var newURL = "";
	for (x=0; x<rblogon.length; x++)
	{
		if (rblogon[x].checked)
		{
			if (rbagency[x].value == "")
			{
				newURL = "/"+warFile+"displaySARoleSearchResults.do?submitAction=Create New SA Role";
				break;
			} 
			newURL = baseURL + "&agencyId=" + rbagency[x].value + "&agencyName=" + rbagencyName[x].value;
			break; 
		}
	} 
	if (newURL == "")
	{
		alert("Unable to find information to transfer to Create page.");
		return false;
	}
	document.getElementById("addRole").href = newURL;
	return true;
}