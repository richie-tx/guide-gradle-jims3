function validateNextInput(theForm)
{
	var msg = "";
	var madeSelect = false;
	var firstCBElement = -1;

	for (x =0; x<theForm.length; x++){
		if (theForm.elements[x].type == "checkbox"){
			firstCBElement = x;
			if (theForm.elements[x].checked == true) {
				madeSelect = true;
				break;
			}
		}	
	}
	if (!madeSelect){
		msg += "Suggested Court Action selection required.\n";
	}	
	if (theForm.create1Comments.value.length > 0){

		var result = validateComments(theForm.create1Comments.value, "Status/Comments and Recommendations entry");
		if (result != "" && msg == ""){
			theForm.create1Comments.focus();
		}
		msg += result;
	}
	if (msg == ""){	
		return true;
	}
	alert(msg);
	if (!madeSelect){
		goNav('#top');
	}
	return false;	
}

function setCheckBoxFocus()
{
	var cbs = document.getElementsByName("selectedSuggestedCourtActionIds");
	if (cbs.length > 0){
		goNav('#top');		
	}	
}