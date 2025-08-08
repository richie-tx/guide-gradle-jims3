<!-- JavaScript for roleAssignUser.jsp only -->
<!-- 06/06/2005  awidjaja - this validates the fields with condition in addition to Struts validation -->
function validateRoleSearchFields(theForm)
{
 	var input = "";
	theForm.roleName.value = Trim(theForm.roleName.value);
	theForm.roleDescription.value = Trim(theForm.roleDescription.value);
	inputFlds = theForm.roleName.value + theForm.roleDescription.value;
	var agencyName = document.getElementsByName("roleAgencyName");
	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
		inputFlds += agencyName[0].value;
	}
	if (inputFlds == ""){
	    alert("At least 1 input value required for Role search.");
	    theForm.roleName.focus();
	    return false;
    }
   	var msg = validateWildCardSearchField("Role Name", theForm.roleName.value);
	if (msg != ""){
       alert(msg);	
       theForm.roleName.focus();   
       return false;
    }   
   	msg = validateWildCardSearchField("Role Description", theForm.roleDescription.value);
	if (msg != ""){
       alert(msg);	
       theForm.roleDescription.focus();   
       return false;
    }  
	if (agencyName.length > 0){
	   	msg = validateWildCardSearchField("Agency Name", agencyName[0].value);
		if (msg != ""){
			alert(msg);	
			agencyName[0].focus();   
			return false;
		} 
	}      
    return true;
}

function allRolesSelect(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;
 

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = true;
	}else {
///			uncheckSelectAll(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

function checkAddRoles(theForm, checkboxName)
{
	 for (var i = 0; i <theForm.length; i++){
 		if(theForm.elements[i].type == "checkbox"){
			if(theForm.elements[i].name == checkboxName){
				if (theForm.elements[i].checked == true){ 
					return true;
				}
			}
		}     
    }
	
	alert("At least 1 role has to be selected for it to be added to the list");
	return false;
} 
/* This function should be removed when security inquiry is ready */
function nogo(){
  alert("Role Name details display is a future feature.");
  return false;
}
