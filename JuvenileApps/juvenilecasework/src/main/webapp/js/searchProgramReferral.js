$(document).ready(function () {
	
	
	$("#serviceProviderId").on("change",function(){
		spinner();
		$('form').attr('action','/JuvenileCasework/displayProgramReferralSearch.do?submitAction=Update SP');
		$('form').submit();
	});
	
	$("#submitBtn").on("click",function(){		
		var offLastName = $("#officerLName").val();
		var offFirstName = $("#officerFName").val();
		var offMiddleName = $("#officerMName").val();
		var spID		 = $("#serviceProviderId").val();
		
		if( spID == '' && offLastName == '' ){			
			alert("Service Provider or Officer Last name is required to serach.");
	    	$("#serviceProviderId").focus();
	    	return false;
		}
	    if( offLastName != "" ){
		    if(offLastName.length<2){
		    	alert("Last Name must be at least 2 valid characters.");
		    	$("#officerLName").focus();
		    	return false;
		    }
	    }
	    if(offFirstName !="" && offLastName == "") 
	    {
	    	alert("Last Name is required .");
	    	$("#officerLName").focus();
	    	return false;
	    }
	    if(offFirstName != ""){
		    if(offFirstName.length<2){
		    	alert("First Name must be at least 2 valid characters.");
		    	$("#officerFName").focus();
		    	return false;
		    }
	    }
	    
	    if ( true ) {
			spinner();
			$('form').attr('action','/JuvenileCasework/displayProgramReferralSearch.do?submitAction=Submit');
			$('form').submit();
		}
	});
	
});