<!-- officerSearch.js -->
<script language=javascript>
function validateSearchForm(theForm) {
   if(validateFirstwithLast(theForm)){
	   if((theForm.lastNamePrompt.value==null || theForm.lastNamePrompt.value=="")  && 
	   			(theForm.logonIdPrompt.value==null || theForm.logonIdPrompt.value=="") && 
	   				(theForm.departmentNamePrompt.value==null || theForm.departmentNamePrompt.value=="")){
	   		alert("At least 1 input value required for search.");
	   		return false;
	   }
	   return true;
  }
  else{
  	return false;
  }
}

function validateFirstwithLast(theForm) {
   if((theForm.lastNamePrompt.value==null || theForm.lastNamePrompt.value=="") && (theForm.firstNamePrompt.value!=null && theForm.firstNamePrompt.value!="")){
   		alert("Last Name is required if First Name is entered.");
   		return false;
   }
   return validateOfficerUserForm(theForm);
}
</script>