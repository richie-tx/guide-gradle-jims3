<!-- JavaScript for this page only -->
<!-- 05/19/2005  sprakash - this validates the fields with condition in addition to Struts validation -->

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
	    alert("At least 1 input value required for search.");
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
function radio_button_checker()
{
	// set var radio_choice to false
	var radio_choice = false;

	// Loop from zero to the one minus the number of radio button selections
	for (counter = 0; counter < radio_form.radio_button.length; counter++)
		{
	// If a radio button has been selected it will return true
	// (If not it will return false)
	if (radio_form.radio_button[counter].checked)
		radio_choice = true; 
	}

	if (!radio_choice)
		{
		// If there were no selections made display an alert box 
		alert("Please select a user.")
		return (false);
	}
return (true);
}

<!--function validateRadios(el)
{
	myOption = -1;
	if (el.logonId.length > 1)
	{
		for (i=0; i<el.logonId.length; i++)
		{
			if (el.logonId[i].checked)
			{
				myOption = i;
			}
		}
	if (myOption == -1)
		{
			alert("You must select a User.");
			return false;
		}
	}
return true;
}

function chooseRow(this) {

		var	num = this.value;
		var theForm = this.form;
		//create row id
		id = num;

		var thisRow = document.getElementById(id);
		//checkbox is checked - change background color	- check if alternating row
		if (this.checked){
				if (this.type != "radio"){
						thisRow.className = "selectedRow";
				}else thisRow.className = "unSelectedRow";
		}else thisRow.className = "";
}