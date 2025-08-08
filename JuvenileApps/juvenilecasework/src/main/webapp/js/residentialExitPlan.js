
$(document).ready(function () {
	//RELEASE DATE CALENDAR.
	if(typeof  $("#expectRelDate") != "undefined"){
		datePickerSingle($("#expectRelDate"),"Expected Release Date",false);
	}

	// Limit text on keyup and mouseout
	$("#specialNotes").on('keypress keyup mouseout',function(){
		textLimit($(this),1000);
		return false;
	});
	
  $("#next").click(function() {
	
	//Expected Release Validations
    var expectedReleaseDate = $("#expectRelDate").val();
    if(typeof expectedReleaseDate !="undefined" && expectedReleaseDate == ""){
		alert("Expected Release Date is required.");
		$("#expectRelDate").focus();
		return false;
	}
    
    //Facility
    var facility = $("#facility").val();
    if(facility == "" || typeof facilityReleaseReason =="undefined"){
     	alert("Facility is Required.");
     	return false;
     }

    
    //FacilityReleaseReason
    var facilityReleaseReason = $("#facilityReleaseReason").val();
    if(typeof facilityReleaseReason !="undefined"){
    	if(facilityReleaseReason == ""){
        	alert("Facility Release Reason is Required.");
        	$("#facilityReleaseReason").focus();
    		return false;
    	}
    }
    	
    //Level of care
    var levelOfCare = $("#levelOfCare").val();
    if(typeof levelOfCare !="undefined"){
       	if(levelOfCare == ""){
           	alert("Level Of Care is Required.");
           	$("#levelOfCare").focus();
       		return false;
       	}
    }
    
  //Level of care
    var permanencyPlan = $("#permanencyPlan").val();
    if(typeof permanencyPlan !="undefined"){
       	if(permanencyPlan == ""){
           	alert("Permanency is Required.");
           	$("#permanencyPlan").focus();
       		return false;
       	}
    }
    
  });
	
});