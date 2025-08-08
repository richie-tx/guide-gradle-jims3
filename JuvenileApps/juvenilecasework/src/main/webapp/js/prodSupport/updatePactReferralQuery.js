/**
 * 
 */
//JQuery on Dom ONLOAD
$(document).ready(function () {

	if(typeof  $("#pacdate") != "undefined"){	
			datePickerSingle($("#pacdate"),"Pact Date",false);
	}
	//defaults all the radio buttons.
	var selectedVal=$("form input:radio");
	  $.each(selectedVal,function(index){ //loops through all the radio buttons.
		  if(typeof radio !== 'undefined' && $(this).val()!==''){ // 
			  $(this).prop("checked",'checked');
		  }
	  });
	  
	$("#selectRefBtn").on("click",function(){
		var riskneedID = $('input:radio[name=riskneedVal]:checked').val();
 
		if( riskneedID !="" ){     	
			
			$("#tempPactID").val( riskneedID ); //set it in the form
			console.log("tempPactID : " + $("#tempPactID").val() );
    		$('form').attr('action','/JuvenileCasework/UpdatePactReferralQuery.do?clr=U');
    		$('form').submit();
    		 return true;
		 }
	});	
	
	$("#BtnRefresh").on("click",function(){
		//Clear fields on the form
		$("#juvNum").val("");
		$("#refNum").val("");

		});	
	$("#BtnCancelBack").on("click",function(){
		//Clear fields on the form
		$('form').attr('action','/JuvenileCasework/MainMenu.do');
		$('form').submit();
		 return true;

	});	
	
	
	
	
	
	$("#updateRecBtn").on("click",function(){
		
		var curr_stat 		= $("#tempStatusFlag").val();
		var boolCurrent 	= $("#boolCurrent").val();	
		var currentCtr		= $("#currentCtr").val();
		var pactStatus		= $("#pactStatusId").val();
		var newStatus		= $("#status_id").val();
		var newCasefile		= $("#case_Id").val();
		var prevCasefileId  = $("#prevCasefileId").val();
		var casefileId;
		var breakLoop = false;
	   
		//alert("cur stat " + curr_stat + "" + boolCurrent + "" + currentCtr + "" + pactStatus + "" + newStatus);
		//alert("NEW CASEFILE ID= " + newCasefile + "OLD CASEFILE ID= " + prevCasefileId);
		if( newCasefile != prevCasefileId){
			
			var pactReferralsJson = $("#pactReferralsCurr").val();
			$.each(jQuery.parseJSON( pactReferralsJson ), function(idx, obj) {				 		 		
			
			casefileId = obj.casefileID;
			if( casefileId === newCasefile.trim()){
				
				breakLoop = true;
			}
			
			});			
			
		}else{		
		
		    if ( "false" === boolCurrent && (currentCtr == 1 && "CURRENT" === newStatus && newStatus != pactStatus )) {
		    	alert("Please review the current supervision\'s PACT details. Another referral has an identified status equal to CURRENT.");
		    }
		        
		    if ( "true" === boolCurrent && currentCtr > 1 && newStatus === "CURRENT" ) {
		    	alert("Please review the current supervision\'s PACT details. Another referral has an identified status equal to CURRENT.");
		    }
		}
		//alert(" in breakLoop" + breakOut + casefileId + newStatus);
		if( breakLoop && casefileId === newCasefile.trim() && "CURRENT" === newStatus ) {
		    
			alert("Please review the current supervision\'s PACT details. Another referral has an identified status equal to CURRENT.");

		}
		
	    var refid = document.getElementById("tempReferralID").value;
		if(refid == null || refid.length < 4 || !$.isNumeric($("#tempReferralID").val())){
			alert("Invalid Referral Number. Please modify");
			return false;
		}
		var caseid = document.getElementById("case_Id").value;
		if( caseid != '' ){
			if( ( caseid.length > 1 && caseid.length < 8 ) || !$.isNumeric($("#case_Id").val())){
				
				alert("Invalid Casefile ID. Please modify");
				return false;				
			}
			
		}
		if (document.forms[0].newPactDate==null || document.forms[0].newPactDate.value==""){
			alert("PACT date is required");
			return false;
		}
		/*if (document.forms[0].riskLevel.selectedIndex == 0){
			alert("Risk Level is required");
			return false;
		}
		
		if (document.forms[0].needLevel.selectedIndex == 0){
			alert("Need Level is required");
			return false;
		}*/
		if (document.forms[0].newPactStatus.selectedIndex == 0){
			alert("Status is required");
			return false;
		}
				
		else 
		{
			if(confirm('Are you sure you want to update the PACT Referral?')) {
				spinner();
				return true;	
			} else { 
				return false;
			}
		}

	});
	
});
