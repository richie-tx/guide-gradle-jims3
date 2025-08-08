
//JQuery on Dom ONLOAD
$(document).ready(function () {

	if(typeof  $("#actdate") != "undefined"){	
			datePickerSingle($("#actdate"),"Activation Date",false);
	}
	
	if(typeof  $("#enddate") != "undefined"){	
		datePickerSingle($("#enddate"),"SupervisionEnd Date",false);
	}
	
	if(typeof  $("#createdate") != "undefined"){	
		datePickerSingle($("#createdate"),"Create Date",false);
	}
	if(typeof  $("#closingenddate") != "undefined"){	
		datePickerSingle($("#closingenddate"),"Date",false);
	}
	
});
