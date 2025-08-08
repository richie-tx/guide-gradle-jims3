function validateSearchFields(thisForm)
{
	var deptName = Trim(thisForm.searchDepartmentName.value);
	var deptId = Trim(thisForm.searchDepartmentId.value);
	thisForm.searchDepartmentName.value = deptName;
	thisForm.searchDepartmentId.value = deptId;
	if(thisForm.searchDepartmentName.value == "" && thisForm.searchDepartmentId.value=="")
	{
		alert("Either Department Name or Department Code has to be provided as search field.");
		thisForm.searchDepartmentName.focus();		    
	    return false;
	}
    msg = validateNameField("Department Name", deptName);
	if (msg != ""){
       	alert(msg);	
      	thisForm.searchDepartmentName.focus();  
       	return false;
    }  
    msg = validateNameField("Department Code", deptId);
	if (msg != ""){
       	alert(msg);	
      	thisForm.searchDepartmentId.focus(); 
       	return false;
    }	
}
function validateRadioFields()
{
	var rSelects = document.getElementsByName('selectedDepartment');
	for (var i=0; i<rSelects.length;i++)
	{
		if(rSelects[i].checked == true)
		{
			return true
		}
	}
	alert("You must select a department to proceed.");
	return false;	
}
function validateNameField(fldName, fldValue)
{
	var errorMsg = ""
	if (fldValue > "")
	{
		if (fldValue.length < 2)
		{
			errorMsg = fldName + " must be at least 2 characters."
		} 
		else
		{
			if (fldValue.charAt(0) == '*' || fldValue.charAt(1) == '*')
			{
				errorMsg = fldName + " must start with 2 valid characters."
			}
		}	
	}
	return errorMsg;
}