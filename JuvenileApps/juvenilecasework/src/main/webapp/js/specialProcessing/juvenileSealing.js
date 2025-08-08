$(document).ready(function () {
	

	if(typeof $("#sealedDate") != "undefined"){
		datePickerSingle($("#sealedDate"),"Sealed Date",false);
	}
	
	$("#submitBtn").on("click",function(form){
		
		var msg = '';
		var allowUpdate 	=  true;
		var comments 		= $("#comments").val();
		var date    		= $("#sealedDate").val();
		
		var refActive 		= $("#activeRef").val();
		var casActive 		= $("#activeCas").val();
		var facActive 		= $("#activeFac").val();
		
		var newSealDate 	= formatDate(date,0);
		var futureDate 	= formatToday();
		 
		var recordType	= $("#recordTye").val();
		 
	
		 
		if (recordType === "I.JUVENILE") {
			var respond = confirm("Do you wish to continue with sealing the purged juvenile record ?");
			if ( respond == false ){
				return false;
			}
		}
		 
		if( comments == ''){
			
			alert("Seal Comments are require.");
			return false;
		}
		
		if( date == ''){
			
			alert("Sealing Date is required for sealing.");
			return false;
		}else{ 
			if( validateDate(date) && newSealDate > futureDate ){
					
				allowUpdate = false;
				$("#sealedDate").focus();
				alert( "Valid sealed date is required.");
				return false;
			}
		}

		if( refActive != ''){
			
			msg+= "Juvenile has one or more ACTIVE referral numbers.  All referrals must be CLOSED to process sealing.";
			allowUpdate = false;
		}
		
		if( casActive != ''){
			
			msg+= "Juvenile has one or more supervisions not equal to CLOSED.  All casefiles must be CLOSED to process sealing.";
			allowUpdate = false;
		}

		if( facActive != ''){
	
			msg+= "Juvenile is currently booked in a facility.  Juvenile cannot be SEALED.";
			allowUpdate = false;
		}
		
		if( !allowUpdate ){
    		alert( msg );
    		return false;
			
		 }else{
			 
			 $('form').attr('action','/JuvenileCasework/PerformJuvenileSealing.do?submitAction=Submit');
			 $('form').submit();	
			 return true;
		 }
		
	});
	
 	function validateDate( dtValue )
	{
 		var match = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec( dtValue );
		//return dtRegex.test(dtValue);
 		return match;
	}
 	
 	function formatToday() {
 		
 		var formatTD = new Date().toISOString().substr(0, 10);	 		
 		var dateToday = formatTD.replace(/-/g, "");
 		
	    return dateToday;
	}
 	
 	function formatDate(date,add) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
 	
});