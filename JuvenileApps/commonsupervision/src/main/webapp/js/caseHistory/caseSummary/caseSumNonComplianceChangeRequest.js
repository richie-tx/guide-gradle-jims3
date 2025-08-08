function validateNextInput(theForm){
	if (theForm.taskText.value > ""){
		var msg = validateComments(theForm.taskText.value, "Task Text");
		if (msg == ""){
			return true;
		}
	} else {
		msg = "Task Text is required.";
	}	
	alert(msg);
	theForm.taskText.focus();
	return false;	
}
