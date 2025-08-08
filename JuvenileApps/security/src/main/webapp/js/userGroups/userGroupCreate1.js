<!-- userGroupCreate1.js -->

function validateFields(theForm){
	var msg = "";
	var regexp = /^[a-zA-Z0-9 '-./\\();\x26]*$/;
	var result = false;
	var tfld = Trim(theForm.userGroupName.value);
	theForm.userGroupName.value = tfld;
	tfld = Trim(theForm.userGroupDescription.value); 
	theForm.userGroupDescription.value = tfld;   
	if (theForm.userGroupName.value == ""){
		msg = "User Group Name is required.\n";
		theForm.userGroupName.focus();	
	}
    if (theForm.userGroupName.value > ""){
	   result = regexp.test(theForm.userGroupName.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.userGroupName.focus();
           }
	       msg += "User Group Name contains one or more invalid characters.\n";	   
	   }    
       if (theForm.userGroupName.value.length < 1){
           if (msg == ""){
              theForm.userGroupName.focus();
           }
	       msg += "User Group must be at least 1 character.\n";
       }
       result = checkAllSpaces(theForm.userGroupName.value);
   	   if (result == true){
           if (msg == ""){
              theForm.userGroupName.focus();
           }
	       msg += "User Group Name can not be all spaces.\n";	   
	   }
    }  
   
    if (theForm.userGroupDescription.value == ""){
       if (msg == ""){
           theForm.userGroupDescription.focus();
       }
       msg += "User Group Description is required.\n";
    }
    if (theForm.userGroupDescription.value > ""){
	   result = regexp.test(theForm.userGroupDescription.value,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.userGroupDescription.focus();
           }
	       msg += "User Group Description contains one or more invalid characters.\n";	   
	   }
       if (theForm.userGroupDescription.value.length < 5){
           if (msg == ""){
              theForm.userGroupDescription.focus();
           }
	       msg += "User Group Description must be at least 5 characters.\n";
       }
       result = checkAllSpaces(theForm.userGroupDescription.value);
   	   if (result == true){
           if (msg == ""){
              theForm.userGroupName.focus();
           }
	       msg += "User Group Description can not be all spaces.\n";	   
	   }       
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
