/**
 * FOR FACILITY also Includes javascript for IE11 changes.
 */

//validations for modify admit flow starts
//JQuery on Dom ONLOAD
$(document).ready(function () {
	

	//DETAINED DATE CALENDAR.
	if(typeof  $("#detainedDate") != "undefined"){	
			datePickerSingle($("#detainedDate"),"Detained Date",false);
	}
	
	//ADMIT DATE CALENDAR.
	if(typeof  $("#admitDate") != "undefined"){	
			datePickerSingle($("#admitDate"),"Admit Date",false);
	}
	
	//ORIGINAL ADMIT DATE CALENDAR.
	if(typeof  $("#originalAdmitDate") != "undefined"){		
			datePickerSingle($("#originalAdmitDate"),"Original Admit Date",false);
	}
	
	//RELEASE DATE CALENDAR.
	if(typeof  $("#releaseDate") != "undefined"){	
	
			datePickerSingle($("#releaseDate"),"Release Date",false);
	}
	
	
	//RETURN DATE CALENDAR.
	if(typeof  $("#returnDate") != "undefined"){		
			datePickerSingle($("#returnDate"),"Return Date",false);
	}
	//validation for required fields: on click of next
    $("#updateRec").click(function () {
    	 
    	 var newTransferToFacility = $("#newTransferToFacility").val();
    	 var releasedTo = $("#releasedTo").val();    
   	  	if(newTransferToFacility!=null &&  newTransferToFacility!="" && releasedTo!='NTS')
   	  	{
   	  		alert("Release To does not equal NTS, for Transfer To Facility update.");
   	  		$("#newTransferToFacility").focus();
   	  		return false;
   	  	}
    	
    	  var releaseReason = $("#releaseReason").val();
    	  if(releaseReason!="" && (releaseReason=="R" || releaseReason=="T"))
    	  {
    		 var releaseDate = $("#releaseDate").val();
    		 var releaseTime = $("#releaseTime").val();    		
    		 var releaseAuthority = $("#releaseAuthority").val();
    		 var outcome = $("#outcome").val();
    		 var releasedBy = $("#releasedBy").val();    		
    		 if(releaseDate=="")
    		 {
    			 alert("Release Date is required with current Release Reason.");
    			 $("#releaseDate").focus();
    			 return false;
    		 }
    		
    		 if(releaseTime=="")
    		 {
    			 alert("Release Time is required with current Release Reason.");
    			 $("#releaseTime").focus();
    			 return false;
    		 }
    		 if(releasedTo=="")
    		 {
    			 alert("Release To is required with current Release Reason.");
    			 $("#releasedTo").focus();
    			 return false;
    		 }     		 
    		 if(releaseAuthority=="")
    		 {
    			 alert("Release Authority is required with current Release Reason.");
    			 $("#releaseAuthority").focus();
    			 return false;
    		 }
    		 if(releasedBy=="")
    		 {
    			 alert("Release By is required with current Release Reason.");
    			 $("#releasedBy").focus();
    			 return false;
    		 }
    		 if(releaseReason=="R" && outcome=="")
    		 {
    			 alert("Outcome is required with current Release Reason.");
    			 $("#outcome").focus();
    			 return false;
    		 }
    		 
    		 if(releaseReason=="T" && $("#tempReleaseReason").val()=="")
    		 {
    			 alert("Temporary Release Reason is required with current Release Reason.");
    			 $("#tempReleaseReason").focus();
    			 return false;
    		 }
    	  }
    	  
    	  var returnReason = $("#returnReason").val();
    	  
    	  if($("#bookingReferral").val()!="" && $("#referralNo").val()!="" && $("#bookingReferral").val()!=$("#referralNo").val())
    	  {
    		  alert("BookingReferralNumber on header record is different. Please change admission/release and header records to matching details. Please review the Detention Hearing records and update as needed."); 			
    	  }
    	  if($("#bookingSupervision").val()!="" && $("#bookingSupervisionNum").val()!="" && $("#bookingSupervision").val()!=$("#bookingSupervisionNum").val())
    	  {
    		  alert("BookingSupervisionNumber on header record is different. Please change admission/release and header records to matching details."); 			
    	  }
    	  if($("#activeFacilityCd").val()!="" && $("#facilityCode").val()!="" && $("#activeFacilityCd").val()!=$("#facilityCode").val())
    	  {
    		  alert("DetainedFacility on header record is different. Please change admission/release and header records to matching details."); 			
    	  }
    	  if($("#facilitySeqNum").val()!="" && $("#lastSeqNum").val()!="" && $("#facilitySeqNum").val()!=$("#lastSeqNum").val())
    	  {
    		  alert("SequenceNumber on header record is different. Please change admission/release and header records to matching details."); 			
    	  }
    	
    	 if(confirm('Are you sure you want to update the casefile?')){
    		 spinner();
  			return true;
    	 } else {
  			return false;
    	 }
    	 
    	 
    	
    });
	

});