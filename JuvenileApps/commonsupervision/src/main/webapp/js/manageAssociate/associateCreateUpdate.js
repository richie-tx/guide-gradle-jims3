function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

function validateLastNameOrFirstNamePresent(theForm)
{		
	if (theForm["associateName.lastName"].value == "" &&
	    theForm["associateName.firstName"].value == "")
	{		
     	alert("Last Name or First Name is required.");
     	theForm["associateName.lastName"].focus();
     	return false;
   	}
	
   	return true;
}

function validateRelationshipPresent(theForm){
	if(theForm.relationshipId.value==""){
		alert("Relationship is required.");
		theForm.relationshipId.focus();
		return false;
	}
	return true;
}

function validateFields()
{
	var thisForm = document.forms[0];
	trim(thisForm["associateName.lastName"]);
	trim(thisForm["associateName.firstName"]);
	trim(thisForm["associateName.middleName"]);
	trim(thisForm["comments"]);
	trim(thisForm["email"]);
	trim(thisForm["primaryResidenceAddress.streetNumber"]);
	trim(thisForm["primaryResidenceAddress.streetName"]);
	trim(thisForm["primaryResidenceAddress.aptNumber"]);
	trim(thisForm["primaryResidenceAddress.city"]);
	trim(thisForm["primaryResidenceAddress.addressComplexName"]);
	trim(thisForm["primaryResidenceAddress.zipCode"]);
	trim(thisForm["otherAddress.streetNumber"]);
	trim(thisForm["otherAddress.streetName"]);
	trim(thisForm["otherAddress.aptNumber"]);
	trim(thisForm["otherAddress.city"]);
	trim(thisForm["otherAddress.addressComplexName"]);
	trim(thisForm["otherAddress.zipCode"]);
	
	if(validateAssociateForm2(thisForm))
	{
		//	Check if Last Name or First Name is entered.
		if (thisForm["associateName.lastName"].value == "" && thisForm["associateName.firstName"].value == "")
		{		
	     	alert("Last Name or First Name is required.");
	     	thisForm["associateName.lastName"].focus();
	     	return false;
	   	}
	   	
	   	//	Check if relationship is selected.
		if(thisForm.relationshipId.value=="")
		{
			alert("Relationship is required.");
			thisForm.relationshipId.focus();
			return false;
		}  	
	}
	else
		return false;
	return true;
	
}

function validateCreateUpdateFields(){
   var theForm = document.forms[0];

   //Validate home phone	
   if (theForm["homePhone.areaCode"].value > "" && theForm["homePhone.prefix"].value == "") {
      alert("Home Phone Prefix must be entered if Home Phone Area Code is entered.");
      theForm["homePhone.prefix"].focus();
      return false;
   }
   if (theForm["homePhone.prefix"].value > "" && theForm["homePhone.last4Digit"].value == "") {
      alert("Home Phone Last4Digit number must be entered if Home Phone Prefix is entered.");
      theForm["homePhone.last4Digit"].focus();
      return false;
   }
   if (theForm["homePhone.prefix"].value == "" && theForm["homePhone.last4Digit"].value > "") {
      alert("Home Phone Prefix must be entered if Home Phone Last4Digit number is entered.");
      theForm["homePhone.prefix"].focus();
      return false;
     }
    if (theForm["homePhone.areaCode"].value == "" && theForm["homePhone.last4Digit"].value > "") {
      alert("Home Phone Area Code must be entered if Home Phone Last4Digit number is entered.");
      theForm["homePhone.areaCode"].focus();
      return false;
     }
      
   //Validate work phone
   if (theForm["workPhone.areaCode"].value > "" && theForm["workPhone.prefix"].value == "") {
      alert("Work Phone Prefix must be entered if Work Phone Area Code is entered.");
      theForm["workPhone.prefix"].focus();
      return false;
   }
   if (theForm["workPhone.prefix"].value > "" && theForm["workPhone.last4Digit"].value == "") {
      alert("Work Phone Last4Digit number must be entered if Work Phone Prefix is entered.");
      theForm["workPhone.last4Digit"].focus();
      return false;
   }
   if (theForm["workPhone.prefix"].value == "" && theForm["workPhone.last4Digit"].value > "") {
      alert("Work Phone Prefix must be entered if Work Phone Last4Digit number is entered.");
      theForm["workPhone.prefix"].focus();
      return false;
     }
    if (theForm["workPhone.areaCode"].value == "" && theForm["workPhone.last4Digit"].value > "") {
      alert("Work Phone Area Code must be entered if Work Phone Last4Digit number is entered.");
      theForm["workPhone.areaCode"].focus();
      return false;
     }
   
   //Validate cell phone
   if (theForm["cellPhone.areaCode"].value > "" && theForm["cellPhone.prefix"].value == "") {
      alert("Cell Phone Prefix must be entered if Cell Phone Area Code is entered.");
      theForm["cellPhone.prefix"].focus();
      return false;
   }
   if (theForm["cellPhone.prefix"].value > "" && theForm["cellPhone.last4Digit"].value == "") {
      alert("Cell Phone Last4Digit number must be entered if Cell Phone Prefix is entered.");
      theForm["cellPhone.last4Digit"].focus();
      return false;
   }
   if (theForm["cellPhone.prefix"].value == "" && theForm["cellPhone.last4Digit"].value > "") {
      alert("Cell Phone Prefix must be entered if Cell Phone Last4Digit number is entered.");
      theForm["workPhone.prefix"].focus();
      return false;
     }
    if (theForm["cellPhone.areaCode"].value == "" && theForm["cellPhone.last4Digit"].value > "") {
      alert("Cell Phone Area Code must be entered if Cell Phone Last4Digit number is entered.");
      theForm["cellPhone.areaCode"].focus();
      return false;
     }
     
   //Validate pager
   if (theForm["pager.areaCode"].value > "" && theForm["pager.prefix"].value == "") {
      alert("Pager Prefix must be entered if Pager Area Code is entered.");
      theForm["pager.prefix"].focus();
      return false;
   }
   if (theForm["pager.prefix"].value > "" && theForm["pager.last4Digit"].value == "") {
      alert("Pager Last4Digit number must be entered if Pager Prefix is entered.");
      theForm["pager.last4Digit"].focus();
      return false;
   }
   if (theForm["pager.prefix"].value == "" && theForm["pager.last4Digit"].value > "") {
      alert("Pager Prefix must be entered if Pager Last4Digit number is entered.");
      theForm["pager.prefix"].focus();
      return false;
     }
    if (theForm["pager.areaCode"].value == "" && theForm["pager.last4Digit"].value > "") {
      alert("Pager Area Code must be entered if Pager Last4Digit number is entered.");
      theForm["pager.areaCode"].focus();
      return false;
     }
     
   if(theForm["primaryResidenceAddress.additionalZipCode"].value != "" && theForm["primaryResidenceAddress.zipCode"].value == "") {
   		alert("Invalid Residence Zip Code : Residence Additional Zip Code allowed only if Residence Zip Code exists.");
   	 	theForm["primaryResidenceAddress.zipCode"].focus();
   	 	return false;
   	 }
   if(theForm["otherAddress.additionalZipCode"].value != "" && theForm["otherAddress.zipCode"].value == "") {
   	 	alert("Invalid Other Zip Code : Other Additional Zip Code allowed only if Other Zip Code exists.");
   	 	theForm["otherAddress.zipCode"].focus();
   	 	return false;
   	 }		
   
   return true;
}