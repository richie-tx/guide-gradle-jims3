$(document).ready(function () {	
	
	
	if(typeof $("#newHearingDate") != "undefined"){
		datePickerSingle($("#newHearingDate"),"Date",false);
	}
	
	if(typeof $("#newProbCauseDate") != "undefined"){
		datePickerSingle($("#newProbCauseDate"),"Date",false);
	}
	
	$("body").on('change', '#newSupervisionNum', function(e){		  
		var newSprvNum     = $("#newSupervisionNum").val();
		var hiddenSprvNum  = $("#curBookingSprvNum").val();
		
		if( newSprvNum != hiddenSprvNum ){
			
			if( !confirm( "Header record Booking Supervision Number associated to the Header record does\n not match the most recent admission record. Verify both records for possible update." ) ){
	            e.preventDefault();
	            $("#newSprvNum").val(hiddenSprvNum);
	        } 
		}else{
			$("#newSprvNum").val(newSprvNum);
		}
	 });
	
	$("body").on('change', '#newHearingDate', function(e){		  
		var hearingDateNew = $("#newHearingDate").val();
		//alert("HearingDate:" + hearingDateNew);
		$("#newHearDate").val( hearingDateNew );
	 });
	
	$("body").on('change', '#newProbCauseDate', function(e){		  
		var pcDateNew = $("#newProbCauseDate").val();
		//alert("HearingDate:" + pcDateNew );
		$("#newPCDate").val( pcDateNew );
	 });

	$("body").on('change', '#newStatId', function(e){		  
		var facilityStatus = $("#newStatId").val();
		$("#newStatusCode").val( facilityStatus);
		 jQuery("#newStatusCode").attr('value', facilityStatus);
	 });
	
	$("body").on('change', '#newFacId', function(x){		  
		var facilityCode = $("#newFacId").val();
		jQuery("#newFacilityCode").attr('value', facilityCode);
	 });
	
	$("#submitBtn").on("click",function( e ){
		
		var msg = '';
		var allowUpdate =  true;
		var newRefNum	   = $("#newReferralNum").val();
		var newSeqNum	   = $("#nextSeqNum").val();
		var headSeqNum      = $("#headerSeqNum").val();
		var curSeqNum      = $("#curSequenceNum").val();
		var oldFacilityCd  = $("#curFacilityCd").val();
		var newFacilityCd  = $("#newFacId").val();
		var newFacilityStat= $("#newStatId").val();
		var newHearDate    = $("#newHearingDate").val();
		var newPCDate      =  $("#newProbCauseDate").val();
		
		if((typeof( oldFacilityCd )=="undefined" || oldFacilityCd ==='') && (typeof( newFacilityCd )=="undefined" || newFacilityCd ==='')){
			allowUpdate = false;
			msg = 'Facility Code is Required';
			$("#newFacilityCd").focus();
		}
		if( newSeqNum != headSeqNum ){
			
			if( !confirm( "Last Sequence Number on Header does not match Admission record.\n Please verify details" ) ){
	            e.preventDefault();
	            $("#newSequenceNum").val( curSeqNum );
	            allowUpdate = false;
	        }else{
	        	$("#newSequenceNum").val( newSeqNum );
	        } 
		}	
		
		var juvNum = $("#juvenileNumber").val();
		if ($("#juvenileNumber").val() != "") {
			if ($("#juvenileNumber").val().trim().length < 6) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				allowUpdate = false;
				return false;
			}
			// juvenile Number
			if ($.isNumeric(juvNum) == false) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				allowUpdate = false;
				return false;
			}							
		}
		
 		
		if( !allowUpdate ){
    		if( msg != ''){ 
    			e.preventDefault();
    			alert( msg );    			
        		return false;
    		}			
			
		 }else{
			 spinner();
			 $("#newRefNum").val(newRefNum);
			 $("#newSequenceNum").val( newSeqNum );
			 $("#newSprvNum").val(newSprvNum);
			 $("#newPCDate").val(newPCDate);
			 $("#newHearDate").val(newHearDate);
			 $("#newStatusCode").val(newFacilityStat);
			 $('form').attr('action','/JuvenileCasework/PerformUpdateFacilityHeader.do?submitAction=Submit');
			 $('form').submit();
			 return true;
		 
		 }

	});	
	
	function isEmpty(value) {
		  return typeof value == 'string' && !value.trim() || typeof value == 'undefined' || value === null || value =='';
		}
	
});