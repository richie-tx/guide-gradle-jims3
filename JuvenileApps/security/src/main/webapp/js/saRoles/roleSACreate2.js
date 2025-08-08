<!-- roleSACreate2.js -->

function validateFields(theForm){
    var msg = "";
    var regexp = /^[a-zA-Z0-9_ '-.,/\();]*$/;
    var result = false;
    var roleName = Trim(theForm.roleName.value);
    var roleDesc = Trim(theForm.roleDescription.value);
    if (roleName == ""){
       msg = "Role Name is required.\n";
       theForm.roleName.focus();	
    }

    if (roleName > ""){
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
    }  
//ER JIMS200022170 description not required and allow free text entry      
//    if (theForm.roleDescription.value == ""){
//       if (msg == ""){
//           theForm.roleDescription.focus();
//       }
//       msg += "Role Description is required.\n";
//    }
    if (roleDesc > ""){
//	   result = regexp.test(theForm.roleDescription.value,regexp);
//	   if (result == false){
//           if (msg == ""){
//              theForm.roleDescription.focus();
//           }
//	       msg += "Role Description value entered contains invalid characters.\n";	   
//	   }
       if (roleDesc.length < 5){
           if (msg == ""){
              theForm.roleDescription.focus();
           }
	       msg += "Role Description must be at least 5 characters.\n";
       }
    }    
    theForm.roleName.value = roleName;
    theForm.roleDescription.value = roleDesc;	    
    if (msg == ""){
       return true;

    }
    alert(msg);     
    return false;
}
