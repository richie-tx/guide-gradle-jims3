function validateInput(theForm)
{
	var msg = "";
	var msgRadio = "";
	var selectFound = false;
	var radioSelectedFound = false;
	var cbIndex = -1;
	for (x =0; x<theForm.length; x++){
		if(theForm.elements[x].type == "radio"){
			if (theForm.elements[x].checked == true) {
				radioSelectedFound = true;
			}			
		}
		if (theForm.elements[x].type == "checkbox"){
			if (cbIndex == -1){
				cbIndex = x;
			}	
			if (theForm.elements[x].checked == true) {
				selectFound = true;
			}
		}
	}
	if (!selectFound){
		msg = "At least 1 reason for transfer must be selected.\n";
		if (cbIndex != -1){
			theForm.elements[cbIndex].type
		}
	}	
	if (!radioSelectedFound){
		msgRadio = "The Extended? field must be set to Yes OR No.\n";
	}

	if (selectFound && radioSelectedFound){
		return true;   
	}if(!selectFound && !radioSelectedFound){
		var combinedMessage = msg + "\n" + msgRadio;
		alert(combinedMessage);
	}else if(!selectFound){
		alert(msg);
	}else{	
		alert(msgRadio);
	}
	return false;	
}