function checkAllDepts(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++) {
			objCheckBoxes[i].checked = true;
			}
	}else {
///			uncheckCheckAll(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

function uncheckCheckAll(el, checkAllName)
{	
	var theForm = el.form;
	if (!el.checked){
		theForm.elements[checkAllName].checked = false;
	}
}
function validateDepartmentSelect(theForm){
	if (theForm.uType.value != "LA")
	{
		for (var i = 0; i <theForm.length; i++){
 			if(theForm.elements[i].type == "checkbox"){
				if(theForm.elements[i].name == "selectedDepartments" || theForm.elements[i].name == "selectAll"){
					if (theForm.elements[i].checked == true){ 
						return true;
					}
				}
			}     
   		}
		alert("Agency or at least 1 Department must be selected to continue.");
	}
	if (theForm.uType.value == "LA")
	{
		var cnt = 0;
		for (var i = 0; i <theForm.length; i++){
 			if(theForm.elements[i].type == "checkbox"){
				if(theForm.elements[i].name == "selectAll"){
					if (theForm.elements[i].checked == true){ 
						cnt = 2;
						break;
					}
				}
				if(theForm.elements[i].name == "selectedDepartments"){
					if (theForm.elements[i].checked == true){ 
						cnt += 1;
					}
				}
				
			}     
   		}
		if (cnt == 0)
		{
			alert("A Department must be selected to continue.");
		}	
		if (cnt > 1)
		{
			alert("Only 1 Department can be selected for Liaison.");		
		}
		if (cnt == 1){
			return true;
		}
	}
	theForm.selectAll.focus();
	return false;
}

function setCurrentDepts(){
	var curDepts = document.getElementsByName("currentSelectedDepts");
	var allDepts = "";
	var deptId = "";
	for(x=0; x<curDepts.length; x++){
		deptId = curDepts[x].value;
		if (deptId > ""){
			allDepts = document.getElementById(deptId);
			allDepts.checked = true;
		}	
	}
}