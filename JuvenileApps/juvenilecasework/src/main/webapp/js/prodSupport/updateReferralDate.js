
//JQuery on Dom ONLOAD
$(document).ready(function () {
	
	if(typeof  $("#refBegindate") != "undefined"){	
		datePickerSingle($("#refBegindate"),"Date",false);
	}
	if(typeof  $("#refEnddate") != "undefined"){	
		datePickerSingle($("#refEnddate"),"Date",false);
	}
	if(typeof  $("#refAckdate") != "undefined"){	
		datePickerSingle($("#refAckdate"),"Date",false);
	}
	if(typeof  $("#refSentdate") != "undefined"){	
		datePickerSingle($("#refSentdate"),"Date",false);
	}
	
	if(typeof  $("#programReferralAssignmentDate") != "undefined"){	
		datePickerSingle($("#programReferralAssignmentDate"),"Date",false);
	}
});