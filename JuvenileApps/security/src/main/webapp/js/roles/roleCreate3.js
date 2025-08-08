<!-- roleCreate3.js -->
function validateFeatureSelect(theForm){
    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].type == "hidden"){
			if(theForm.elements[i].name == "feature"){
				return true;
			}
		}     
    }
	alert("AT least 1 Feature must be selected and added to Selected Features.");
	theForm.featureName.focus();
	return false;
}

function validateFeatureSearchFields(theForm){
    var msg = "";
    theForm.featureName.value = Trim(theForm.featureName.value);
    if (theForm.featureName.value == "" &&
        theForm.featureCategoryId.selectedIndex == 0 ){
	   	    alert("At least 1 input value required for Feature search.");
    		theForm.featureName.focus();	
    		return false;
    }
    msg = validateWildCardSearchField("Feature Name", theForm.featureName.value);
	if (msg != ""){
       	alert(msg);	
      	theForm.featureName.focus();   
       	return false;
    }     
    return true;
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
function resetParent(el){
	var cid = el.id;
	var sp1 = cid.split("f");    // cid format childOf#+# where first # is parentId
	var sp2 = sp1[1].split("+"); 
	if (el.checked == false){
		var elem = document.getElementById(sp2[0]);
		elem.checked = false;
	}	
}