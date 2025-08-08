<!-- JavaScript for roleAssignUserGroup.jsp only -->
<!-- 06/06/2005  awidjaja - this validates the fields with condition in addition to Struts validation -->
function validateRoleSearchFields(theForm)
{
 	var roleName = Trim(theForm.roleName.value);
	var roleDesc = Trim(theForm.roleDescription.value);
	theForm.roleName.value = roleName;
	theForm.roleDescription.value = roleDesc;
	var agencyName = document.getElementsByName("userGroupAgencyName");
	var agency = "";
	if (agencyName.length > 0){
		agency = Trim(agencyName[0].value);
	}	
	var inputFlds = roleName + roleDesc + agency;
    if (inputFlds == ""){
	    alert("At least 1 input value required for Role search.");
    	theForm.roleName.focus();
	    return false;    
	}

   	var msg = validateWildCardSearchField("Role Name", roleName);
	if (msg != ""){
       alert(msg);	
       theForm.roleName.focus();   
       return false;
    }   
   	msg = validateWildCardSearchField("Role Description", roleDesc);
	if (msg != ""){
       alert(msg);	
       theForm.roleDescription.focus();   
       return false;
    }  
	if (agencyName.length > 0){
	   	msg = validateWildCardSearchField("Agency", agency);
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

function validateSelect(){
	var roleSel = document.getElementsByName("roleSelected");
	if (roleSel.length == 0){
		alert("At least 1 role required on Current/ Selected Roles List.");
		return false;
	}
	return true;	
}
/* This function should be removed when security inquiry is ready */
function nogo(){
  alert("Role details display is a future feature.");
  return false;
}
