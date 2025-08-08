function validateTextFields(thisForm) {

	//check if atleast one field is entered
	trim(thisForm["userName.firstName"]);
	trim(thisForm["userName.lastName"]);
	trim(thisForm["userName.middleName"]);
	trim(thisForm["requestorName.firstName"]);
	trim(thisForm["requestorName.lastName"]);
	trim(thisForm["SSN.SSN1"]);
	trim(thisForm["SSN.SSN2"]);
	trim(thisForm["SSN.SSN3"]);
	trim(thisForm["logonId"]);
	trim(thisForm["jims2LogonId"]);	
	trim(thisForm["OPID"]);
	trim(thisForm["agencyId"]);
	trim(thisForm["agencyName"]);
	trim(thisForm["departmentId"]);
	trim(thisForm["departmentName"]);
	var mychoice=0;
	for(var i=0; i<thisForm.length;i++)
	{
		if(thisForm.elements[i].type == 'text' && thisForm.elements[i].disabled == false)
		{
			if(thisForm.elements[i].value > " ")
			{
				mychoice=1;
				break;
			}	
		} else if(thisForm.elements[i].selectedIndex>0)
			{
				mychoice=1;
				break;
			}
	}

    if(mychoice==0)
    {
    	alert("At least 1 input field required for User search.");
    	thisForm["logonId"].focus;
    	return false;
    }
	//var thisForm = document.forms[0];
	if(thisForm["jims2LogonId"].value != "" && thisForm["logonId"].value != "" ){
		alert("Only 1 User ID is allowed as search field.");
		thisForm["logonId"].focus();		    
	    return false;
	}
   	if(thisForm["userName.lastName"].value == "" )
   	{	
		if(thisForm["userName.firstName"].value != "" )
		{
			alert("Last Name must be provided to use First Name search field.");
			thisForm["userName.lastName"].focus();		    
		    return false;
		}
		if(thisForm["userName.middleName"].value != "" )
		{
			alert("Last Name must be provided to use Middle Name as search field.");
			thisForm["userName.lastName"].focus();		    
		    return false;
		}
	}	
// full SSN required for search	
	if (thisForm["SSN.SSN1"].value > ""){
		if (thisForm["SSN.SSN2"].value == "") {
      		alert("Full Social Security number must be entered to search.");
			thisForm["SSN.SSN2"].focus();
			return false;
   		}
        if (thisForm["SSN.SSN3"].value == "") {
			alert("Full Social Security number must be entered to search.");
			thisForm["SSN.SSN3"].focus();
			return false;
		}	
   }
	if (thisForm["SSN.SSN2"].value > "") {
		if (thisForm["SSN.SSN1"].value == "") {
			alert("Full Social Security number must be entered to search.");
			thisForm["SSN.SSN1"].focus();
			return false;
   		}
   		if (thisForm["SSN.SSN3"].value == "") {
      		alert("Full Social Security number must be entered to search.");
      		thisForm["SSN.SSN3"].focus();
      		return false;
      	}	
   	}
   	if (thisForm["SSN.SSN3"].value > "") {
   		if (thisForm["SSN.SSN1"].value == "") {
			alert("Full Social Security number must be entered to search.");
			thisForm["SSN.SSN1"].focus();
			return false;
		}
   		if (thisForm["SSN.SSN2"].value == "") {
      		alert("Full Social Security number must be entered to search.");
      		thisForm["SSN.SSN2"].focus();
      		return false;
      	}	
   	}   
   	if(thisForm["requestorName.lastName"].value == "" )
	{	
		if(thisForm["requestorName.firstName"].value != "" )
		{
			alert("Requestor's Last Name must be provided to use First Name as search field.");
			thisForm["requestorName.lastName"].focus();		    
		    return false;
		}
	}
// Search Item compliance edtis
	var msg = validateWildCardSearchField("JIMS User ID", thisForm["logonId"].value);
	if (msg != ""){
    	alert(msg);	
		thisForm["logonId"].focus();		    
		return false;
    }
    msg = validateWildCardSearchField("JIMS2 User ID", thisForm["jims2LogonId"].value);
	if (msg != ""){
    	alert(msg);	
		thisForm["jims2LogonId"].focus();		    
		return false;
    }
    msg = validateWildCardSearchField("Operator ID", thisForm["OPID"].value);
	if (msg != ""){
    	alert(msg);	
		thisForm["OPID"].focus();		    
		return false;
    }     
	msg = validateWildCardSearchField("Last Name", thisForm["userName.lastName"].value);
	if (msg != ""){
    	alert(msg);	
		thisForm["userName.lastName"].focus();		    
		return false;
    } 
// Agency Code, Agency Name, Department Code and Department Name not avaliable to all users for input    
    var agID = document.getElementsByName("agencyId");
    if (agID.length > 0 && agID[0].type == "text"){    
	    msg = validateWildCardSearchField("Agency Code", thisForm["agencyId"].value);
		if (msg != ""){
    		alert(msg);	
			thisForm["agencyId"].focus();		    
			return false;
		}	
    } 
    var agName = document.getElementsByName("agencyName");
    if (agName.length > 0 && agName[0].type == "text"){
	    msg = validateWildCardSearchField("Agency Name", thisForm["agencyName"].value);
		if (msg != ""){
    		alert(msg);	
			thisForm["agencyName"].focus();		    
			return false;
		}	
    }
    var deptId = document.getElementsByName("departmentId");
    if (deptId.length > 0 && deptId[0].type == "text"){    
	    msg = validateWildCardSearchField("Department Code", thisForm["departmentId"].value);
		if (msg != ""){
    		alert(msg);	
			thisForm["departmentId"].focus();		    
			return false;
		}	
    } 
	var deptName = document.getElementsByName("departmentName");
    if (deptName.length > 0 && deptName[0].type == "text"){     
	    msg = validateWildCardSearchField("Department Name", thisForm["departmentName"].value);
		if (msg != ""){
    		alert(msg);	
			thisForm["departmentName"].focus();		    
			return false;
		}	
    } 
	msg = validateWildCardSearchField("Requestor Last Name", thisForm["requestorName.lastName"].value);
	if (msg != ""){
    	alert(msg);	
		thisForm["requestorName.lastName"].focus();		    
		return false;
    }     	
	
	return validateUserProfileSearchForm(thisForm);
	
}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

function validateRadioFields() {
	var rchecks = document.getElementsByName("selectedUser");
	for (var n=0; n<rchecks.length; n++)
	{
		if (rchecks[n].checked){
			return true;
		}
	}
	alert("You must select one of the users");
	return false;
}

function checkWhichRadioFields() {
	
	var thisForm = document.forms[2];
    var myOption = 0;    
	
	for(var i=0; i<thisForm.length;i++)
    {
     	if(thisForm.elements[i].type == 'radio')
     	{
     	  if(thisForm.elements[i].checked)
	       	thisForm.selectedValue.value=thisForm.elements[i].value;	      
     	}
     }
	return true;
}
