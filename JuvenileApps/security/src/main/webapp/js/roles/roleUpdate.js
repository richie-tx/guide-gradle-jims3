<!-- roleUpdate.js -->
function validateAgencySearchFields(theForm){
    var msg = "";
	var agencyName = document.getElementsByName("agencyName");
	var agencyCode = document.getElementsByName("agencyCode");	
	if (agencyName.length > 0){
		agencyName[0].value = Trim(agencyName[0].value);
		agencyCode[0].value = Trim(agencyCode[0].value);
		var inputFlds = agencyName[0].value + agencyCode[0].value;
	    if (inputFlds  == "" &&
        	theForm.agencyTypeId.selectedIndex == 0 &&
        	theForm.jmcRepId.selectedIndex == 0){
	        alert("At least 1 input value required for Agency search.");
    	    theForm.agencyName.focus();	
        	return false;
        }
	   	msg = validateWildCardSearchField("Agency Name", agencyName[0].value);
		if (msg != ""){
			alert(msg);	
	       	agencyName[0].focus();   
    	   	return false;
    	} 
 
	   	msg = validateWildCardSearchField("Agency Code", agencyCode[0].value);
		if (msg != ""){
			alert(msg);	
	       	agencyCode[0].focus();   
    	   	return false;
    	}    	     	
    }
    return true;
}
function validateFeatureSearchFields(theForm){
    var msg = "";
    var featureName = Trim(theForm.featureName.value);
    theForm.featureName.value = featureName;
    if (theForm.featureName.value == "" &&
        theForm.featureCategoryId.selectedIndex == 0 ){
	        alert("At least 1 input value required for Feature search.");
           	theForm.featureName.focus();
           	return false;	
    }
   	msg = validateWildCardSearchField("Feature Name", featureName);
	if (msg != ""){
       alert(msg);	
       theForm.featureName.focus();   
       return false;
    }     
    return true;
}

function validateRequiredFields(theForm){
    var msg = "";
    var regexp = /^[a-zA-Z0-9_ '-.,/\();]*$/;
    var result = false;
    var agencyFound = false;
    var featureFound = false;
    if (theForm.roleName.value == ""){
       msg = "Role Name is required.\n";
       theForm.roleName.focus();	
    }
    if (theForm.roleName.value > ""){
	   result = regexp.test(theForm.roleName.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.roleName.focus();
           }
	       msg += "Role Name value entered contains invalid characters.\n";	   
	   }    
       if (theForm.roleName.value.length < 3){
           if (msg == ""){
              theForm.roleName.focus();
           }
	       msg += "Role Name must be at least 3 characters.\n";
       }
       result = checkAllSpaces(theForm.roleName.value);
   	   if (result == true){
           if (msg == ""){
              theForm.roleName.focus();
           }
	       msg += "Role Name can not be all spaces.\n";	   
	   }
    }    
    if (theForm.roleDescription.value > ""){
	   result = regexp.test(theForm.roleDescription.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description value entered contains invalid characters.\n";	   
	   }
       if (theForm.roleDescription.value.length < 5){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description must be at least 5 characters.\n";
       }
       result = checkAllSpaces(theForm.roleDescription.value);
   	   if (result == true){
           if (msg == ""){
              theForm.roleName.focus();
           }
	       msg += "Role Description can not be all spaces.\n";	   
	   }       
    }    
    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].type == "hidden"){
			if(theForm.elements[i].name == "agency"){
				agencyFound = true;
				break;
			}
		}     
    }
   /* if (!agencyFound){
       if (msg == ""){
          theForm.agencyName.focus();
       }
       msg += "AT least 1 Agency must be selected and added to Selected Agencies.\n";	 
    }*/
    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].type == "hidden"){
			if(theForm.elements[i].name == "feature"){
				featureFound = true;
				break;
			}
		}     
    }
    if (!featureFound){
       if (msg == ""){
          theForm.featureName.focus();
       }
	   msg += "AT least 1 Feature must be selected and added to Selected Features.\n";
    }
    
    if (msg == ""){
      return true;
    }
    alert(msg);     
    return false;
}

function checkAllSpaces(fldValue){
    var ch = "";
    for (var x = 0; x < fldValue.length; x++){
       ch = fldValue.substring(x,x+1);
       if (ch != " "){
          return false;
       }
    }
    return true;
}

function allAgenciesSelect(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;
 

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes - 1; i++)
			objCheckBoxes[i].checked = true;
	}else {
			uncheckSelectAllAgency(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

function uncheckSelectAllAgency(el, checkAllName)
{	
	var theForm = el.form;
	if (!el.checked){
		theForm.selectAllAgencies.checked = false;
	}
}

function allFeaturesSelect(el, checkboxName)
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
			uncheckSelectAll(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

function uncheckSelectAll(el, checkAllName)
{	
	var theForm = el.form;
	if (!el.checked){
		theForm.selectAllFeatures.checked = false;
	}
}
