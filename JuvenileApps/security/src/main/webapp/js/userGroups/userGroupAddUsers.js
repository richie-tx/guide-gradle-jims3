<!-- JavaScript for userGroupAddUsers.jsp page only -->
<!-- 05/18/2005  sprakash - this validates the fields with condition in addition to Struts validation -->

function validateSearchFields(theForm){
	var inputFound = false; 
	var msg = "";
	for(i=0; i<theForm.length; i++) {
		if(theForm.elements[i].type == "text"){
			theForm.elements[i].value = Trim(theForm.elements[i].value);
//			theForm.elements[i].value = temp;
			if (theForm.elements[i].value > ""){
				inputFound = true;
			}
		}
	}

	if (inputFound == false){
		var deptId = document.getElementsByName("departmentId");
		if (deptId.length > 0){
		    if (theForm.departmentId.selectedIndex == 0){
			    alert("At least 1 input value required for search.");
			    theForm.lastName.focus();
	    		return false;
	    	}	
	    }
	    if (deptId.length == 0){	
	   	    alert("At least 1 input value required for search.");
		    theForm.lastName.focus();
   			return false;
   		}	
    } 
    
    if (theForm.lastName.value == ""){
    	if (theForm.firstName.value > ""){
	        alert("Last name required if First Name is entered for search.");
    	    theForm.lastName.focus();
        	return false;
        }	
    	if (theForm.middleName.value > ""){
	        alert("Last name required if Middle Name is entered for search.");
    	    theForm.lastName.focus();
        	return false;
        }        
    }  

  	msg = validateWildCardSearchField("Last Name", theForm.lastName.value);
	if (msg != ""){
       alert(msg);	
       theForm.lastName.focus();   
       return false;
    }  
   	msg = validateWildCardSearchField("User ID", theForm.userId.value);
	if (msg != ""){
       alert(msg);	
       theForm.userId.focus();   
       return false;
    }   
    
    var agencyName = document.getElementsByName("searchAgencyName");
    if (agencyName.length > 0){
	    msg = validateWildCardSearchField("Agency Name", theForm.searchAgencyName.value);
		if (msg != ""){
	       	alert(msg);	
    	   	theForm.searchAgencyName.focus();   
       		return false;
    	} 
    }	
    var deptName = document.getElementsByName("department");  
    if (deptName.length > 0){  
	   	msg = validateWildCardSearchField("Department Name", theForm.department.value);
		if (msg != ""){
	   		alert(msg);	
		 	theForm.department.focus();   
    	   	return false;
    	 }  
    } 
    
    return true;
}

function allUsersSelect(el, checkboxName)
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

function uncheckSelectAll(el, aForm)
{	
	var theForm = el.form;
	if (!el.checked){
		theForm.selectAllUsers.checked = false;
	}
}
