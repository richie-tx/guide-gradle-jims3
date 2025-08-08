<!-- JavaScript for departmnetContactSearch.jsp -->
function checkFindUsersFields(theForm)
{
	var userln = document.getElementsByName("userName.lastName");
	var userfn = document.getElementsByName("userName.firstName");
	userln[0].value = Trim(userln[0].value);
	userfn[0].value = Trim(userfn[0].value);	
	theForm.userLogonId.value = Trim(theForm.userLogonId.value);
	var inputs = userln[0].value + userfn[0].value + theForm.userLogonId.value;
	if (inputs == ""){
		alert("At least 1 search field is required.");
		userln[0].focus();
		return false;
	}		
	return true;
}


function validateDepartmentContacts(theForm){
   if (document.getElementById("contactPhoneNumber.areaCode").value > "" && document.getElementById("contactPhoneNumber.prefix").value == "") {
      alert("Phone Prefix must be entered if Phone Area Code is entered.");
      document.getElementById("contactPhoneNumber.prefix").focus();
      return false;
   }
   if (document.getElementById("contactPhoneNumber.areaCode").value == "") {
      alert("Phone Area Code is a required field.");
      document.getElementById("contactPhoneNumber.areaCode").focus();
      return false;
   }
   if (document.getElementById("contactPhoneNumber.areaCode").value == "" && document.getElementById("contactPhoneNumber.prefix").value > "") {
      alert("Phone Area Code must be entered if Phone Prefix is entered.");
      document.getElementById("contactPhoneNumber.areaCode").focus();
      return false;
   }
   if (document.getElementById("contactPhoneNumber.prefix").value > "" && document.getElementById("contactPhoneNumber.last4Digit").value == "") {
      alert("Phone Main number must be entered if Phone Prefix is entered.");
      document.getElementById("contactPhoneNumber.last4Digit").focus();
      return false;
   }
   if (document.getElementById("contactPhoneNumber.prefix").value == "" && document.getElementById("contactPhoneNumber.last4Digit").value > "") {
      alert("Phone Prefix must be entered if Phone Main number is entered.");
      document.getElementById("contactPhoneNumber.prefix").focus();
      return false;
   }
   if (document.getElementById("contactPhoneNumber.ext").value > "" && document.getElementById("contactPhoneNumber.last4Digit").value == "") {
      alert("Phone Number must be entered if Phone Extension is entered.");
      document.getElementById("contactPhoneNumber.areaCode").focus();
      return false;
   }
 
 	return validateContactUpdateForm(theForm);  
 }
 
  
function checkRadio(theForm)
{
	var firstChar = "";
	var rb = document.getElementsByName("primaryContact");
	for (i=0; i <rb.length; i++){
		if (rb[i].checked == true){
			firstChar = rb[i].value.toUpperCase().substring(0,1);
			if (firstChar == 'Y'){
				if (yesOnList(theForm) == true){
					alert('Only 1 contact as primary can be on the list.');
					var fldName = document.getElementsByName("contactName.lastName")[0];
					fldName.focus();
					return false;
				}
			}
		}	
	}
	if (firstChar == ""){
		alert('Primary Contact selection required.');
		return false;
	}
	return true;
}
function yesOnList(theForm){
	var rb = document.getElementsByName("isPrimary");
	var str = "";
	for (i=0; i <rb.length; i++){
		str = rb[i].value.toUpperCase(); 
		if (str == 'Y' || str == 'YES'){
			return true;
		}	
	}	
	return false;
}
function checkSelectList(theForm){
	if (yesOnList(theForm) == false){
		alert('At least Primary Contact on list must be Yes.');
		return false;
	}
	return true;
}
