<!-- warrantJOTCreate.js -->
<script language=javascript>

function validateReleaseDecision(theForm) {
   var msg = "";
   if(theForm.elements["releaseDecisionId"].value == "") {
       msg += "Release Decision is required.\n";
   }
   if (msg != ""){
	 	alert(msg);
	   	return false;
	}
	
    return true;
   
}

</script>