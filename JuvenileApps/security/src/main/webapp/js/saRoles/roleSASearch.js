<!-- roleSASearch.js -->

function validateFields(theForm)
{
	theForm.roleName.value = Trim(theForm.roleName.value);
	theForm.agencyName.value = Trim(theForm.agencyName.value); 
	var roleName = theForm.roleName.value;
	var agencyName = theForm.agencyName.value; 
    if ((roleName == "" || roleName == null) &&
       (agencyName == "" || agencyName == null))
    {
       alert("At least 1 input value required for Role search.");
       theForm.roleName.focus();
       return false;	
    }
    if (roleName > "" && agencyName > ""){
       alert("Search by Role or Agency Name only.");
       theForm.roleName.focus();
       return false;	    
    }
    var roleexp = /^[a-zA-Z0-9_\-' *]*$/;
    var agencyexp = /^[a-zA-Z0-9_ '-.,/\();]*$/; 
    var result = false;
    var msg = validateWildCardSearchField("Role Name", roleName);
	if (msg != ""){
       alert(msg);	
       theForm.roleName.focus();   
       return false;
    }     
    result = roleexp.test(roleName,roleexp);
	if (result == false){
       alert("Role Name search value contains invalid characters.\n");	
       theForm.roleName.focus();   
       return false;
    }  
  
    msg = validateWildCardSearchField("Agency Name", agencyName);
	if (msg != ""){
       alert(msg);	
      theForm.agencyName.focus();   
       return false;
    }     

    result = agencyexp.test(agencyName,agencyexp);
	if (result == false){
       alert("Agency Name search value contains invalid characters.\n");	
       theForm.agencyName.focus();   
       return false;
    }  
      
    return true; 
}