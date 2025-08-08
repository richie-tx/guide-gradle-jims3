$(document).ready(function () {
	

	/*if(typeof $("#sealedDate") != "undefined"){
		datePickerSingle($("#sealedDate"),"Sealed Date",false);
	}*/
	
	$("#submitBtn").on("click",function(form){
		
		var msg = '';
		var allowUpdate 	=  true;
		var comments 		= $("#comments").val();
		//var date    		= $("#sealedDate").val();
		
		var refActive 		= $("#activeRef").val();
		var casActive 		= $("#activeCas").val();
		var facActive 		= $("#activeFac").val();
		
		//var newSealDate 	= formatDate(date,0);
		var futureDate 	= formatToday();
		 
		var recordType	= $("#recordTye").val();
		var numericRegExp = /^[0-9]*$/;
		var alphaNumericRegExp = /^[A-Za-z0-9]*$/;
		
		if ($("#purgeSerNum").val().length > 0 ) 
		{
			if ($("#purgeSerNum").val().length ==3 )
			{
				if (alphaNumericRegExp.test($("#purgeSerNum").val(),alphaNumericRegExp) == false) {
					msg+="Purge Series has to be alphanumeric.\n";
					allowUpdate = false;
				}
			}
			else
			{
				msg+="Valid  Purge Series entry is required.  Please verify entry.\n";
				allowUpdate = false;				
			}
		}
		else 
		{
			msg+="Purge Series is required.\n";
				allowUpdate = false;
		}
		if ($("#purgeBoxNum").val().length > 0 ) 
		{
			if ($("#purgeBoxNum").val().length ==3 )
			{
				if (numericRegExp.test($("#purgeBoxNum").val(),numericRegExp) == false) {
					msg+="Purge box has to be numeric.\n";
					allowUpdate = false;
				}
			}
			else
			{
				msg+="Valid  Purge Box entry is required.  Please verify entry.\n";
				allowUpdate = false;				
			}
		}
		else 
		{
			msg+="Purge box is required.\n";
				allowUpdate = false;
		}
		
		/*if( refActive != ''){
			
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
		*/
		if( !allowUpdate ){
    		alert( msg );
    		return false;
			
		 }
		else{
			 var respond = confirm("Juvenile Record Purge is not reversible. Any questions or concerns must be forwarded to Data Corrections.  Do you wish to continue?");
				if ( respond == false ){
					return false;
				}			 
			 $('form').attr('action','/JuvenileCasework/PerformJuvenilePurging.do?submitAction=Submit');
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