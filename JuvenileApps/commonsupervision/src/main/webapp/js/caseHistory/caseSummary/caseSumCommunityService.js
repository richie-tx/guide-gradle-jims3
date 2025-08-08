function validateNextInput(theForm){
	var msg = validateComments(theForm.create1Comments.value, "Comments");
	if (msg == ""){
		return true;
	}	
	alert(msg);
	theForm.create1Comments.focus();
	return false;	
}