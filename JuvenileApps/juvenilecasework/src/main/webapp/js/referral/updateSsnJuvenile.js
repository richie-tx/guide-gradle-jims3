$(document).ready(function () {
	
	
	   
	
	$("#nextBtn").click(function(){
		console.log("Next button");
		var ssn1Regex = /^(?!000)([0-9]{3}$)/;
		var ssn2Regex = /^([0-9]{2}$)/;
		var ssn3Regex = /^([0-9]{4}$)/;
		   
		var SSN1 = $.trim($("#SSN1").val());
		var SSN2 = $.trim($("#SSN2").val());
		var SSN3 = $.trim($("#SSN3").val());
		
		 if (SSN1.length == 0 
				 && SSN2.length == 0 
				 && SSN3.length == 0 ) {
			 alert("All of Social Security number must be entered.\n");
			 $("#SSN1").focus();
			 return false;
		 } 
		 
		 if (SSN1.length > 0 || SSN2.length > 0 || SSN3.length > 0)
		   {
				if(ssn1Regex.test(SSN1) == false)
				{
					alert("First 3 digits of SSN must be numeric and not equal 000.\n");
					$("#SSN1").focus();
					return false;
				}
				if(ssn2Regex.test(SSN2) == false)
				{
					alert("Second 2 digits of SSN must be numeric.\n");
					$("#SSN2").focus();
					return false;
				}
				if(ssn3Regex.test(SSN3) == false)
				{
					alert("Last 4 digits of SSN must be numeric.\n");
					$("#SSN3").focus();
					return false;
				}	
		   }
		 
		 if(typeof SSN1!="undefined"){
				
			   if ($("#SSN1").val() != "" && $("#SSN2").val() == "") {
				   alert("All of Social Security number must be entered if there is partial entry.");
				   $("#SSN2").focus();
				   return false;
			   }
			   if ($("#SSN1").val() != "" && $("#SSN3").val() == "") {
			      alert("All of Social Security number must be entered if there is partial entry.");
			      $("#SSN3").focus();
			      return false;
			   }
			   if ($("#SSN2").val()!= "" && $("#SSN1").val() == "") {
				  alert("All of Social Security number must be entered if there is partial entry.");
				  $("#SSN1").focus();
				  return false;
			   }
			   if ($("#SSN2").val() != "" && $("#SSN3").val() == "") {
				  alert("All of Social Security number must be entered if there is partial entry.");
				  $("#SSN3").focus();
				  return false;
			   }
			   if ($("#SSN3").val() !="" && $("#SSN1").val() == "") {
				  alert("All of Social Security number must be entered if there is partial entry.");
				  $("#SSN1").focus();
				  return false;
			   }
			   if ($("#SSN3").val() != "" && $("#SSN2").val() == "") {
				   alert("All of Social Security number must be entered if there is partial entry.");
				   $("#SSN2").focus();
				   return false;
				}
		   }
	})
  
   
   
})