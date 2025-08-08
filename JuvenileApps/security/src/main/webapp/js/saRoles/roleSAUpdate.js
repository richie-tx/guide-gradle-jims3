<!-- roleSAUpdate.js -->
function checkSearchInputs(theForm){
	var ftrName = Trim(theForm.featureName.value);
	theForm.featureName.value = ftrName;
	if (ftrName == "" && theForm.featureCategoryId.selectedIndex == 0){
		alert("At least 1 input value required for Feature search.");
		theForm.featureName.focus();
		return false;
	}
    var msg = validateWildCardSearchField("Feature Name", ftrName);
	if (msg != ""){
       alert(msg);	
       theForm.featureName.focus();  
       return false;
    } 	
	return true;
}

function validateFields(theForm){
    var msg = "";
    var featureFound = false;
    var agencyFound = false;
    var agencyElementIndex = -1;
    var regexp = /^[a-zA-Z0-9_ '-.,/\();]*$/;    
    var roleName = Trim(theForm.roleName.value);
    var roleDesc = Trim(theForm.roleDescription.value);    
    if (roleName == ""){
       msg = "Role Name is required.\n";
       theForm.roleName.focus();
    }
    if (roleName > ""){
	   result = regexp.test(roleName,regexp);
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
    }     
    if (roleDesc == ""){
       if (msg == ""){
          theForm.roleDescription.focus();
       }
       msg += "Role Description is required.\n";
    } else {
	   if (roleDesc.length < 5){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description must be at least 5 characters.\n";
       }
    }   
    theForm.roleName.value = roleName;
    theForm.roleDescription.value = roleDesc;	
// if existing agency not found, a new value must be selected  
    if (theForm.currentAgency.value > ""){
      agencyFound = true;
    }  
    if (theForm.currentAgency.value == ""){
        for (var i = 0; i <theForm.length; i++){
			if(theForm.elements[i].type == "radio"){
				if(theForm.elements[i].name == "selectedAgencies"){
				    if (agencyElementIndex == -1 ){
				        agencyElementIndex = i;
				    }
					if(theForm.elements[i].checked == true){
						agencyFound = true;
						break;
					}	
				}
			}     
		}
    }
    if (!agencyFound){
       if (msg == ""){
          theForm.elements[agencyElementIndex].focus();
       }
	   msg += "Agency selection is required.\n";
	}
// at least 1 feature is required       
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
	   msg += "At least 1 Feature must be selected and added to Current/Selected Features List.\n";
	}
    if (msg == ""){
      return true;
    }
    alert(msg);     
    return false;    
}
    
function validateFindFields(theForm){    
    if (theForm.agencyId.selectedIndex == 0 &&
        theForm.agencyTypeId.selectedIndex == 0 &&
        theForm.setcicId.selectedIndex == 0 && 
        theForm.jmcRepId.selectedIndex == 0){
           msg = "At least 1 value needs to be selected to perform Find.\n";
           theForm.agencyId.focus();	
    }
    if (msg == ""){
      return true;
    }
    alert(msg);     
    return false;
}
function setAgencyId(idx){
   idx.form.setcicId.value = idx.value;
   alert(idx.form.setcicId.value);
}
/* function setSelectedAgencyId(theForm){
alert(theForm.roleName.value);

    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].type == "radio"){
		    alert(theForm.elements[i].name + "  " +  theForm.elements[i].value +  "  " + theForm.setcicId.value);
			if(theForm.elements[i].name == "agencyId" && theForm.elements[i].value == document.forms[0].setcicId.value){
				theForm.elements[i].checked = true; 
				break;
			}
		}     
    }
} */
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
///			uncheckSelectAll(el,checkAllName);
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
