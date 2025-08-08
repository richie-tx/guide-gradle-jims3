<!-- roleCreate1.js -->
function validateCreateFields(theForm){
    var msg = "";
    var regexp = /^[a-zA-Z0-9 '-.,/\();\x26]*$/;
    var roleName = Trim(theForm.roleName.value);
    var roleDesc = Trim(theForm.roleDescription.value);
    theForm.roleName.value = roleName;
    theForm.roleDescription.value = roleDesc;
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
       if (roleName.length < 3){
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
    }
    if (roleDesc > ""){
	   result = regexp.test(roleDesc,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description value entered contains invalid characters.\n";	   
	   }
       if (roleDesc.length < 5){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description must be at least 5 characters.\n";
       }
    }    
    
    if (msg == ""){
      return validateRoleForm(theForm);
    }
    alert(msg);     
    return false;
}
function validateCopyFields(theForm){
    var msg = "";
    var regexp = /^[a-zA-Z0-9_\- ]*$/;
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
       if (roleName.length < 3){
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
       msg += "Description is required.\n";
    }
    if (roleDesc > ""){
	   result = regexp.test(roleDesc,regexp);
	   if (result == false){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Description value entered contains invalid characters.\n";	   
	   }
       if (roleDesc.length < 5){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Description must be at least 5 characters.\n";
       }
    } 
   	if (roleName.toUpperCase() == theForm.originalRoleName.value.toUpperCase()){
   		if (msg == ""){
           	theForm.roleName.focus();
       	}
       	msg += "Role Name can not be same as original value.\n";
    }	
    if (msg == ""){
      return true;
    }
    alert(msg);     
    return false;
}